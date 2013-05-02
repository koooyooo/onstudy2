
package org.onproject.onstudy.ui.swing.network;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.onproject.onstudy.net.data.AnswerPacket;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.net.service.NetworkService;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.question.service.QuestionService;
import org.onproject.onstudy.service.ServiceLocator;
import org.onproject.onstudy.ui.swing.common.UIUtils;
import org.onproject.onstudy.ui.swing.core.controller.AbstractController;
import org.onproject.onstudy.ui.swing.core.controller.ControllerLocator;
import org.onproject.onstudy.ui.swing.core.ui.FrameContext;
import org.onproject.onstudy.ui.swing.core.ui.UI;
import org.onproject.onstudy.ui.swing.core.ui.UILocator;
import org.onproject.onstudy.ui.swing.table.SimpleTable;
import org.onproject.onstudy.ui.swing.table.SimpleTableModel;

/**
 * ����ʂ̃R���g���[��
 * 
 * @author ���c �D�f
 */
public class NetworkController extends AbstractController {
    
    /**
     * �Ή�����UI���擾���܂��B
     * 
     * @return �Ή�����UI
     */
    protected UI getTargetUI() {
        return UILocator.getNetworkUI();
    }
    
    /**
     * ���������s���܂��B
     * 
     */
    @Override
    public void initialize() {
        this.registerQuestionPacketListener();
        this.registerAnswerPacketListener();
        this.reload();
    }
    
    /**
     * �����[�h�������s���܂��B
     * 
     */
    public void reload() {
        this.reloadQuestionTable();
        this.reloadMemberTable();
    }
    
    /**
     * ���e�[�u���������[�h���܂��B
     * 
     */
    private void reloadQuestionTable() {
        QuestionService service = ServiceLocator.getQuestionService();
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        questionTable.removeAllRow();
        for (String id : service.getIdSet()) {
            Question question = service.getQuestionById(id);
            questionTable.addRow(new Object[]{Boolean.FALSE, id, question.getTitle()});
        }
    }
    
    /**
     * �����o�[�e�[�u���������[�h���܂��B
     *
     */
    private void reloadMemberTable() {
    	SimpleTable memberTable = (SimpleTable) super.getComponent("memberTable");
        memberTable.removeAllRow();
        Set<Member> currentMemberSet = ServiceLocator.getNetworkService().getCurrentMemberSet();
        for (Member currentMember : currentMemberSet) {
            memberTable.addRow(new Object[]{Boolean.FALSE, currentMember.getName(), currentMember.getIp(), currentMember.getPort()});
        }
    }
    
    /**
     * ���p�P�b�g���X�i��o�^���܂��B
     *�@
     */
    private void registerQuestionPacketListener() {
        NetworkService networkService = ServiceLocator.getNetworkService();
        networkService.addQuestionPacketListener(new QuestionPacketListener() {
            
            /**
             * ���p�P�b�g����M���܂��B
             * 
             * @param questionPacket ���p�P�b�g
             */
            public void listen(QuestionPacket questionPacket) {
                FrameContext frameContext = getTargetUI().getFrameContext();
                int continueUpdate = JOptionPane.showConfirmDialog(frameContext.getFrame(), 
                        questionPacket.getTransmitter().getName() + " (" + questionPacket.getTransmitter().getIp() + 
                        ") ���A����" + questionPacket.getQuestionList().size() + "���M���܂����B" +
                        System.getProperty("line.separator") + 
                        "�l�b�g���[�N���^�u�ɐ؂�ւ��܂����H");
                if (continueUpdate == JOptionPane.OK_OPTION) {
                	ControllerLocator.getNQController().load();
                    JTabbedPane tabbedPane = (JTabbedPane) frameContext.getFrameComponent("tab");
                    tabbedPane.setSelectedIndex(3);
                }
            }
            
        });
    }
    
    /**
     * �𓚃p�P�b�g���X�i��o�^���܂��B
     *
     */
    private void registerAnswerPacketListener() {
        NetworkService networkService = ServiceLocator.getNetworkService();
        networkService.addAnswerPacketListener(new AnswerPacketListener() {
            
            /**
             * �𓚃p�P�b�g����M���܂��B
             * 
             * @param �𓚃p�P�b�g
             */
            public void listen(AnswerPacket packet) {
                final Set receiveQuestionIdSet = packet.getQuestionIdSet();
                for (Iterator receiveQuestionIdIte = receiveQuestionIdSet.iterator(); receiveQuestionIdIte.hasNext(); ) {
                    this.operateEachAnswer(packet, (String) receiveQuestionIdIte.next());
                }
            }
            
            /**
             * �e�𓚖��̑���ł��B
             * 
             * @param packet �p�P�b�g
             * @param questionId ���ID
             */
            private void operateEachAnswer(AnswerPacket packet, String questionId) {
                final Question question = ServiceLocator.getQuestionService().getQuestionById(questionId);
                boolean answerIsRight = question.isRightAnswer(packet.getAnswerIdSet(questionId));
                final List<String> answerIdList = new ArrayList<String>(packet.getAnswerIdSet(questionId));
                Collections.sort(answerIdList);
                final String displayValue = answerIsRight ? "�� " + answerIdList.toString() : "�~ " + answerIdList.toString();                    
                this.setValueToResultTable(packet, questionId, displayValue);
            }
            
            /**
             * ���ʃe�[�u���ɒl�𔽉f�����܂��B
             * 
             * @param packet �p�P�b�g
             * @param questionId ���ID
             * @param displayValue �\���l
             */
            private void setValueToResultTable(AnswerPacket packet, final String questionId, final String displayValue) {
                final SimpleTable resultTable = (SimpleTable) getComponent("resultTable");
                for (int i = 0; i < resultTable.getRowCount(); i++) {
                    if (!packet.getPanelist().getIp().equals(resultTable.getValueAt(i, 1))) {
                        continue;
                    }
                    for (int j = 2; j < resultTable.getColumnCount(); j++) {
                        if (!resultTable.getColumnName(j).equals(questionId)) {
                            continue;
                        }
                        resultTable.setValueAt(displayValue, i, j);
                    }
                }
            }
            
        });
    }

    
    /**
     * �����[�h�{�^���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void reloadButtonMouseClicked(MouseEvent e) {
    	this.reloadMemberTable();
    }
    
    /**
     * �����o�[�e�[�u���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void memberTableMouseClicked(MouseEvent e) {
    	SimpleTable memberTable = (SimpleTable) super.getComponent("memberTable");
    	UIUtils.reverseCheckValue(memberTable, 0); 
    }
    
    /**
     * ���e�[�u���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void questionTableMouseClicked(MouseEvent e) {
    	
    	UIUtils.reverseCheckValue((JTable) super.getComponent("questionTable"), 0); 
    	
    	this.showSentence(this.getCurrentQuestion().getSentence());
    }
    
    /**
     * ���e�L�X�g�G���A�ɖ���\�����܂��B
     * 
     * @param sentence �\��������
     */
	private void showSentence(String sentence) {
    	JTextArea sentenceArea = (JTextArea) super.getComponent("sentenceArea");
    	sentenceArea.setText(sentence);
    	JScrollPane sentenceSP = (JScrollPane) super.getComponent("sentenceSP");
    	sentenceSP.getViewport().setViewPosition(new Point(0,0));
        sentenceArea.updateUI();
	}
    
    /**
     * ���ݑI������Ă�������擾���܂��B
     * 
     * @return ���ݑI������Ă�����, ������� null
     */
    private Question getCurrentQuestion() {
        final String currentQuestionId = this.getCurrentQuestionId();
        if (currentQuestionId == null) {
            return null;
        }
        return ServiceLocator.getQuestionService().getQuestionById(currentQuestionId);
    }
    
    /**
     * ���ݑI������Ă�����ID���擾���܂��B
     * 
     * @return ���ݑI������Ă�����ID, ������� null
     */
    private String getCurrentQuestionId() {
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        if (questionTable.getSelectedRow() == -1) {
            return null;
        }
        return  (String) questionTable.getValueAt(questionTable.getSelectedRow(), 1);
    }
    
    /**
     * ���M�{�^���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void sendButtonMouseClicked(MouseEvent e) {
        SimpleTableModel resultTableModel = new SimpleTableModel();
        resultTableModel.setAllCellNotEditable();
        resultTableModel.addColumn("���O");
        resultTableModel.addColumn("IP");
        resultTableModel.addColumn("�|�[�g");
        
        final List<Member> selectedMemberList = this.getSelectedMemberList();
        final List<Question> selectedQuestionList = this.getSelectedQuestionList();
        
        
        for (Question question : selectedQuestionList) {
        	resultTableModel.addColumn(question.getId());
        }
        
        SimpleTable resultTable = (SimpleTable) super.getComponent("resultTable");
        resultTable.setModel(resultTableModel);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (Member member : selectedMemberList) {
        	resultTable.addRow(new Object[]{member.getName(), member.getIp(), String.valueOf(member.getPort()), "", "", ""});
        }
        
        ServiceLocator.getNetworkService().sendQuestion(selectedMemberList, selectedQuestionList);
    }
    
    /**
     * �I�����ꂽ�����o�[�̃��X�g���擾���܂��B
     * 
     * @return �����o�[�̃��X�g
     */
    private List<Member> getSelectedMemberList() {
    	final List<Member> memberList = new ArrayList<Member>();
    	SimpleTable memberTable = (SimpleTable) super.getComponent("memberTable");
    	for (int i = 0; i < memberTable.getRowCount(); i++) {
    		boolean isSelected = ((Boolean) memberTable.getValueAt(i, 0)).booleanValue();
    		if (!isSelected) {
    			continue;
    		}
    		Member member = new Member();
    		member.setName((String) memberTable.getValueAt(i, 1));
    		member.setIp((String) memberTable.getValueAt(i, 2));
    		member.setPort((Integer) memberTable.getValueAt(i, 3));
    		memberList.add(member);
    	}
    	return memberList;
    }
    
    /**
     * �I�����ꂽ���̃��X�g���擾���܂��B
     * 
     * @return �I�����ꂽ���̃��X�g
     */
    private List<Question> getSelectedQuestionList() {
    	final List<Question> questionList = new ArrayList<Question>();
    	SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
    	for (int i = 0; i < questionTable.getRowCount(); i++) {
    		boolean isSelected = ((Boolean) questionTable.getValueAt(i, 0)).booleanValue();
    		if (!isSelected) {
    			continue;
    		}
    		final String questionId = (String) questionTable.getValueAt(i, 1);
    		Question question = ServiceLocator.getQuestionService().getQuestionById(questionId);
    		questionList.add(question);
    	}
    	return questionList;
    }
    
    
    
}

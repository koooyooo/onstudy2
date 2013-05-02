
package org.onproject.onstudy.ui.swing.question;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.onproject.onstudy.question.data.Option;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.question.service.QuestionService;
import org.onproject.onstudy.service.ServiceLocator;
import org.onproject.onstudy.ui.swing.common.UIUtils;
import org.onproject.onstudy.ui.swing.core.controller.AbstractController;
import org.onproject.onstudy.ui.swing.core.ui.UI;
import org.onproject.onstudy.ui.swing.core.ui.UILocator;
import org.onproject.onstudy.ui.swing.table.SimpleTable;

/**
 * ����ʂ̃R���g���[��
 * 
 * @author ���c �D�f
 */
public class QuestionController extends AbstractController {
    
    /** ���s���� */
    private static final String BR = System.getProperty("line.separator");
    
    /**
     * �Ή�����UI���擾���܂��B
     * 
     * @return �Ή�����UI
     */
    protected UI getTargetUI() {
        return UILocator.getQuestionUI();
    }
    
    /**
     * �����[�h�������s���܂��B
     * 
     */
    public void reload() {
        this.clearDisplays();
        QuestionService service = this.getQuestionService();
        
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        questionTable.removeAllRow();
        for (String id : service.getIdSet()) {
            Question question = service.getQuestionById(id);
            questionTable.addRow(new Object[]{id, question.getTitle()});
        }
        
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.removeAllRow();
    }
    
    /**
     * ID, �^�C�g��, ���\�������N���A���܂��B
     *
     */
    public void clearDisplays() {
        JTextField idField = (JTextField) super.getComponent("idField");
        idField.setText("");
        
        JTextField titleField = (JTextField) super.getComponent("titleField");
        titleField.setText("");

        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        questionArea.setText("");
        questionArea.setBackground(new Color(80, 80, 80));
    }
    
    /**
     * ���T�[�r�X���擾���܂��B
     * 
     * @return ���T�[�r�X
     */
	protected QuestionService getQuestionService() {
		return ServiceLocator.getQuestionService();
	}
    
    /**
     * ���e�[�u���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void questionTableMouseClicked(MouseEvent e) {
        this.loadQuestionToUI();
    }
    
    /**
     * ����ύX���܂��B
     *
     */
    private void loadQuestionToUI() {
        this.resetQuestionAreaColor();
        final Question question = this.getCurrentQuestion();
        this.showQuestion(question);
        this.showOptions(question.optionIterable());
    }
    
    /**
     * ���e�L�X�g�G���A������F�ɂ��܂��B
     * 
     */
    private void resetQuestionAreaColor() {
        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        questionArea.setBackground(new Color(80, 80, 80));
        questionArea.setForeground(Color.WHITE);
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
        return this.getQuestionService().getQuestionById(currentQuestionId);
    }
    
    /**
     * ���ݑI������Ă�����ID���擾���܂��B
     * 
     * @return ���ݑI������Ă�����ID, ������� null
     */
    protected String getCurrentQuestionId() {
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        if (questionTable.getSelectedRow() == -1) {
            return null;
        }
        return  (String) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
    }
    
    /**
     * ����\�����܂��B
     * 
     * @param question ���
     */
    private void showQuestion(Question question) {
    	
    	JTextField idField = (JTextField) super.getComponent("idField");
    	idField.setText(question.getId());
    	
    	JTextField titleField = (JTextField) super.getComponent("titleField");
    	titleField.setText(question.getTitle());
    	
        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        questionArea.setText(createQuestionAndOptionText(question));
        
        JScrollPane questionPane = (JScrollPane) super.getComponent("questionSP");
        questionPane.getViewport().setViewPosition(new Point(0,0));
        
        questionArea.updateUI();
    }
    
    /**
     * ��蕶�ƑI�����̕�����𐶐����܂��B
     * 
     * @param question ���
     * @return ��蕶�ƑI�����̕�����
     */
    private String createQuestionAndOptionText(Question question) {
        StringBuffer sentence = new StringBuffer();
        sentence.append(question.getSentence()).append(BR);
        sentence.append("�@����(" + question.rightAnswerSize() + ")").append(BR);
        sentence.append(BR);
        for (Option option : question.optionIterable()) {
            sentence.append(option.getId() + " �F " + option.getText()).append(BR);
        }
        return sentence.toString();
    }
    
    /**
     * �I������\�����܂��B
     * 
     * @param optionIte �I�������̔����q
     */
    private void showOptions(Iterable<Option> optionIterable) {
        SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.removeAllRow();
        for (Option option : optionIterable) {
            optionTable.addRow(new Object[]{new Boolean(false), option.getId(), option.getText()});
        }
    }
    
    /**
     * �I�����e�[�u���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void optionTableMouseClicked(MouseEvent e) {
        UIUtils.reverseCheckValue((SimpleTable) super.getComponent("optionTable"), 0);
    }
    
    /**
     * �𓚃{�^���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void answerButtonMouseClicked(MouseEvent e) {
        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        final Question question = this.getCurrentQuestion();
        if (question == null) {
            return;
        }
        boolean answerIsCorrect = question.isRightAnswer(this.getAnswerIdSet());
        if (answerIsCorrect) {
            questionArea.setBackground(new Color(80, 180, 80));
        } else {
            questionArea.setBackground(new Color(180, 80, 80));
        }
        questionArea.setText(this.createQuestionAndOptionWithExpositoryWriting(this.getCurrentQuestion()));
        
        JScrollPane questionPane = (JScrollPane) super.getComponent("questionSP");
        questionPane.getViewport().setViewPosition(new Point(0,0));
        
        questionArea.updateUI();
    }
    
    /**
     * ��蕶�ƑI�����ɉ����������������𐶐����܂��B
     * 
     * @param question ���
     * @return ��蕶�ƑI�����ɉ����������������
     */
    private String createQuestionAndOptionWithExpositoryWriting(Question question) {
        StringBuffer sb = new StringBuffer();
        sb.append("�y����z").append(BR);
        sb.append(BR);
        sb.append(question.getExpositoryWriting()).append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(createQuestionAndOptionText(question)).append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);
        sb.append(BR);

        return sb.toString();
    }

    /**
     * �𓚂��ꂽ��ID��Set���擾���܂��B
     * 
     * @return �𓚂��ꂽID��Set
     */
    protected Set<String> getAnswerIdSet() {
        SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        Set<String> selectedOptionIdSet = new HashSet<String>();
        for (int i = 0; i < optionTable.getRowCount(); i++) {
            boolean checked = ((Boolean) optionTable.getValueAt(i, 0)).booleanValue();
            if (!checked) {
                continue;
            }
            String optionId = (String) optionTable.getValueAt(i, 1);
            selectedOptionIdSet.add(optionId);
        }
        return selectedOptionIdSet;
    }
    
    /**
     * ��ID��Set�Ɛ���ID��Set���ׁA�𓚂����������𔻒肵�܂��B
     * 
     * @param answerIdSet ��ID��Set
     * @param rightAnswerIdSet ����ID��Set
     * @return �S�Đ����ł���� true
     */
    public boolean isRightAnswer(Set<String> answerIdSet, Set<String> rightAnswerIdSet) {
        if (answerIdSet.size() != rightAnswerIdSet.size()) {
            return false;
        }
        return answerIdSet.containsAll(rightAnswerIdSet);
    }
    
    /**
     * �q���g�{�^���������̃C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void hintButtonMouseClicked(MouseEvent e) {
        final Question currentQuestion = this.getCurrentQuestion();
        if (currentQuestion == null) {
            return;
        }
        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        questionArea.setText(this.createQuestionAndOptionWithHint(currentQuestion));
    }
    
    /**
     * ��蕶�ƑI�����Ƀq���g��������������𐶐����܂��B
     * 
     * @param question ���
     * @return ��蕶�ƑI�����Ƀq���g��������������
     */
    private String createQuestionAndOptionWithHint(Question question) {
        StringBuffer sb = new StringBuffer();
        sb.append(createQuestionAndOptionText(question)).append(BR);
        sb.append(BR);
        sb.append(question.getHint());
        return sb.toString();
    }
    
    /**
     * ���փ{�^���������̃C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void nextButtonMouseClicked(MouseEvent e) {
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        int selectedRow = questionTable.getSelectedRow();
        if (questionTable.getRowCount() == selectedRow + 1) {
            return;
        }
        questionTable.changeSelection(selectedRow + 1, 0, false, false);
        this.loadQuestionToUI();
    }
    
}

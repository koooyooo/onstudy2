package org.onproject.onstudy.ui.swing.network;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.onproject.onstudy.ui.swing.common.ImagePanel;
import org.onproject.onstudy.ui.swing.core.ui.AbstractUI;
import org.onproject.onstudy.ui.swing.table.SimpleTable;
import org.onproject.onstudy.ui.swing.table.SimpleTableModel;

/**
 * �l�b�g���[�N��ʂ�UI
 * 
 * @author ���c �D�f
 */
public class NetworkUI extends AbstractUI {
    
    /**
     * �p�l���𐶐����܂��B
     * 
     * @return ���������p�l��
     */
    protected JPanel createPanel() {

        JPanel panel = new ImagePanel("img/gradation1.png", true);
        panel.setLayout(null);
        
        panel.add(this.createReloadButton());
        panel.add(this.createMemberTablePane());
        panel.add(this.createQuestionTablePane());
        panel.add(this.createSentenceTextArea());
        panel.add(this.createSendButton());
        panel.add(this.createResultTablePane());
        
        return panel;
    }
    
    /**
     * ���Z�b�g�{�^�����쐬���܂��B
     * 
     * @return ���Z�b�g�{�^��
     */
    private JButton createReloadButton() {
        final JButton reloadButton = new JButton("�����[�h");
        reloadButton.setName("reloadButton");
        reloadButton.setBounds(370, 15, 90, 20);
        return reloadButton;
    }
    
    /**
     * �����o�[�e�[�u�����쐬���܂��B
     * 
     * @return �����o�[�e�[�u��
     */
    private JScrollPane createMemberTablePane() {
        SimpleTableModel memberTableModel = new SimpleTableModel();
        memberTableModel.addNotEditableCol(1);
        memberTableModel.addNotEditableCol(2);
        memberTableModel.addNotEditableCol(3);
        memberTableModel.addColumn("");
        memberTableModel.addColumn("���O");
        memberTableModel.addColumn("IP");
        memberTableModel.addColumn("�|�[�g");
        
        SimpleTable memberTable = new SimpleTable(memberTableModel);
        memberTable.setName("memberTable");
        memberTable.getTableHeader().setReorderingAllowed(false);
        this.setComponentColor(memberTable);
        memberTable.getColumnModel().getColumn(0).setMaxWidth(30);

        
        JScrollPane memberTablePane = new JScrollPane(memberTable);
        memberTablePane.setBounds(20, 40, 440, 120);
        memberTablePane.setBackground(Color.ORANGE);
        memberTablePane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return memberTablePane;
    }

    /**
     * ���e�[�u�����쐬���܂��B
     * 
     * @return ���e�[�u��
     */
	private JScrollPane createQuestionTablePane() {
        SimpleTableModel questionTableModel = new SimpleTableModel();
        questionTableModel.addNotEditableCol(1);
        questionTableModel.addNotEditableCol(2);
        questionTableModel.addColumn("");
        questionTableModel.addColumn("��");
		questionTableModel.addColumn("�^�C�g��");
		
		SimpleTable questionTable = new SimpleTable(questionTableModel);
        questionTable.setName("questionTable");
        questionTable.getTableHeader().setReorderingAllowed(false);
		this.setComponentColor(questionTable);
		questionTable.getColumnModel().getColumn(0).setMaxWidth(30);
		questionTable.getColumnModel().getColumn(1).setMaxWidth(30);
        
		JScrollPane questionScrollPane = new JScrollPane(questionTable);
		questionScrollPane.setBounds(20, 175, 250, 160);
		questionScrollPane.setBackground(Color.ORANGE);
		questionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return questionScrollPane;
	}
	
    /**
     * ���e�L�X�g�G���A�𐶐����܂��B
     * 
     * @return ���e�L�X�g�G���A
     */
	private JScrollPane createSentenceTextArea() {
		final JTextArea sentenceArea = new JTextArea();
		sentenceArea.setName("sentenceArea");
		sentenceArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		sentenceArea.setCaretColor(Color.LIGHT_GRAY);
		sentenceArea.setEditable(false);
		sentenceArea.setLineWrap(true);
		this.setComponentColor(sentenceArea);
        
		
        JScrollPane sentenceSP = new JScrollPane(sentenceArea);
        sentenceSP.setName("sentenceSP");
        sentenceSP.setBounds(285, 175, 175, 160);
        
		return sentenceSP;
	}
	
    /**
     * ���M�{�^�����쐬���܂��B
     * 
     * @return ���Z�b�g�{�^��
     */
    private JButton createSendButton() {
        final JButton sendButton = new JButton("���M");
        sendButton.setName("sendButton");
        sendButton.setBounds(370, 340, 90, 20);
        return sendButton;
    }
    
    /**
     * �����o�[�e�[�u�����쐬���܂��B
     * 
     * @return �����o�[�e�[�u��
     */
    private JScrollPane createResultTablePane() {
        SimpleTableModel resultTableModel = new SimpleTableModel();
        resultTableModel.addNotEditableCol(1);
        resultTableModel.addNotEditableCol(2);
        resultTableModel.addNotEditableCol(3);
        resultTableModel.addColumn("");
        resultTableModel.addColumn("���O");
        resultTableModel.addColumn("IP");
        resultTableModel.addColumn("�|�[�g");
        
        SimpleTable resultTable = new SimpleTable(resultTableModel);
        resultTable.setName("resultTable");
        resultTable.getTableHeader().setReorderingAllowed(false);
        this.setComponentColor(resultTable);
        resultTable.getColumnModel().getColumn(0).setMaxWidth(30);
        
        JScrollPane resultSP = new JScrollPane(resultTable);
        resultSP.setBounds(20, 370, 440, 120);
        resultSP.setBackground(Color.ORANGE);
        resultSP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return resultSP;
    }

    /**
     * �R���|�[�l���g�ɐF�t�����s���܂��B
     * 
     * @param component �Ώۂ̃R���|�[�l���g
     */
	private void setComponentColor(final JComponent component) {
		component.setBackground(new Color(80, 80, 80));
		component.setForeground(Color.WHITE);
	}
	
}

package org.onproject.onstudy.ui.swing.register;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.onproject.onstudy.ui.swing.common.ImagePanel;
import org.onproject.onstudy.ui.swing.common.UIUtils;
import org.onproject.onstudy.ui.swing.core.ui.AbstractUI;
import org.onproject.onstudy.ui.swing.table.SimpleTable;
import org.onproject.onstudy.ui.swing.table.SimpleTableModel;

/**
 * ����ʂ�UI
 * 
 * @author ���c �D�f
 */
public class RegisterUI extends AbstractUI {
    
    /**
     * �p�l���𐶐����܂��B
     * 
     * @return ���������p�l��
     */
    protected JPanel createPanel() {
        JPanel panel = new ImagePanel("img/gradation1.png", true);
        panel.setLayout(null);
        
        panel.add(this.createMessageLabel());
        
        panel.add(this.createResetButton());
        
        panel.add(this.createIdLabel());
        panel.add(this.createIdField());
        
        panel.add(this.createTitleLabel());
        panel.add(this.createTitleField());
        
        panel.add(this.createSentenceLabel());
        panel.add(this.createSentenceTextArea());
        
        panel.add(this.createHintLabel());
        panel.add(this.createHintTextArea());
        
        panel.add(this.createExpositoryWritingLabel());
        panel.add(this.createExpositoryWritingTextArea());
        
        panel.add(this.createQuestionTablePane());

        panel.add(this.createOptionTablePane());
        
        panel.add(this.createDeleteOptionButton());
        panel.add(this.createAddOptionButton());
        
        panel.add(this.createDeleteLabel());
        panel.add(this.createDeleteCheckBox());

        panel.add(this.createSaveOfUpdateButton());
        
        return panel;
    }
    

    
    /**
     * ���b�Z�[�W���x�����쐬���܂��B
     * 
     * @return ���b�Z�[�W���x��
     */
    private JLabel createMessageLabel() {
        final JLabel msgLabel = new JLabel();
        msgLabel.setName("messageLabel");
        msgLabel.setBounds(20, 15, 200, 20);
        msgLabel.setForeground(new Color(230, 80, 80));
        return msgLabel;
    }
    
    /**
     * ���Z�b�g�{�^�����쐬���܂��B
     * 
     * @return ���Z�b�g�{�^��
     */
    private JButton createResetButton() {
        final JButton resetButton = new JButton("���Z�b�g");
        resetButton.setName("resetButton");
        resetButton.setBounds(370, 15, 90, 20);
        return resetButton;
    }
    
    /**
     * ID�̃��x�����쐬���܂��B
     * 
     * @return ID�̃��x��
     */
    private JLabel createIdLabel() {
        final JLabel idLabel = new JLabel();
        idLabel.setName("idLabel");
        idLabel.setBounds(20, 40, 30, 20);
        idLabel.setText("ID");
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(idLabel);
        idLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return idLabel;
    }
    
    /**
     * ID�̃t�B�[���h���쐬���܂��B
     * 
     * @return ID�̃t�B�[���h
     */
    private JTextField createIdField() {
        final JTextField idField = new JTextField();
        idField.setName("idField");
        idField.setBounds(55, 40, 40, 20);
        idField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        idField.setCaretColor(Color.LIGHT_GRAY);
        this.setComponentColor(idField);
        return idField;
    }
    
    /**
     * �^�C�g���̃��x�����쐬���܂��B
     * 
     * @return �^�C�g���̃��x��
     */
    private JLabel createTitleLabel() {
        final JLabel titleLabel = new JLabel();
        titleLabel.setName("titleLabel");
        titleLabel.setBounds(110, 40, 80, 20);
        titleLabel.setText("�^�C�g��");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(titleLabel);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return titleLabel;
    }
    
    /**
     * �^�C�g���̃t�B�[���h���쐬���܂��B
     * 
     * @return �^�C�g���̃t�B�[���h
     */
    private JTextField createTitleField() {
        final JTextField titleField = new JTextField();
        titleField.setName("titleField");
        titleField.setBounds(195, 40, 265, 20);
        titleField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titleField.setCaretColor(Color.LIGHT_GRAY);
        this.setComponentColor(titleField);
        return titleField;
    }
    
    /**
     * ���̃��x�����쐬���܂��B
     * 
     * @return ���̃��x��
     */
    private JLabel createSentenceLabel() {
        final JLabel sentenceLabel = new JLabel();
        sentenceLabel.setName("sentenceLabel");
        sentenceLabel.setBounds(20, 70, 50, 120);
        sentenceLabel.setText("���");
        sentenceLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(sentenceLabel);
        sentenceLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return sentenceLabel;
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
		this.setComponentColor(sentenceArea);
        
        JScrollPane sentenceSP = new JScrollPane(sentenceArea);
        sentenceSP.setName("sentenceSP");
        sentenceSP.setBounds(75, 70, 385, 120);
        
		return sentenceSP;
	}
    
    /**
     * �q���g�̃��x�����쐬���܂��B
     * 
     * @return �q���g�̃��x��
     */
    private JLabel createHintLabel() {
        final JLabel hintLabel = new JLabel();
        hintLabel.setName("hintLabel");
        hintLabel.setBounds(20, 200, 50, 65);
        hintLabel.setText("�q���g");
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(hintLabel);
        hintLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return hintLabel;
    }
    
    /**
     * �q���g�e�L�X�g�G���A�𐶐����܂��B
     * 
     * @return �q���g�e�L�X�g�G���A
     */
    private JScrollPane createHintTextArea() {
        final JTextArea hintArea = new JTextArea();
        hintArea.setName("hintArea");
        hintArea.setCaretColor(Color.LIGHT_GRAY);
        hintArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setComponentColor(hintArea);
        
        JScrollPane hintSP = new JScrollPane(hintArea);
        hintSP.setName("hintSP");
        hintSP.setBounds(75, 200, 385, 65);
        
        return hintSP;
    }
    
    /**
     * ����̃��x�����쐬���܂��B
     * 
     * @return ����̃��x��
     */
    private JLabel createExpositoryWritingLabel() {
        final JLabel expositoryWritingLabel = new JLabel();
        expositoryWritingLabel.setName("expositoryWritingLabel");
        expositoryWritingLabel.setBounds(20, 275, 50, 65);
        expositoryWritingLabel.setText("���");
        expositoryWritingLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(expositoryWritingLabel);
        expositoryWritingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return expositoryWritingLabel;
    }
    
    /**
     * ����̃e�L�X�g�G���A�𐶐����܂��B
     * 
     * @return �q���g�e�L�X�g�G���A
     */
    private JScrollPane createExpositoryWritingTextArea() {
        final JTextArea expositoryWritingArea = new JTextArea();
        expositoryWritingArea.setName("expositoryWritingArea");

        expositoryWritingArea.setCaretColor(Color.LIGHT_GRAY);
        expositoryWritingArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setComponentColor(expositoryWritingArea);

        JScrollPane expositoryWritingSP = new JScrollPane(expositoryWritingArea);
        expositoryWritingSP.setName("expositoryWritingSP");
        expositoryWritingSP.setBounds(75, 275, 385, 65);
        
        return expositoryWritingSP;
    }

    /**
     * ���e�[�u�����쐬���܂��B
     * 
     * @return ���e�[�u��
     */
	private JScrollPane createQuestionTablePane() {
		SimpleTableModel questionTableModel = new SimpleTableModel();
        questionTableModel.setAllCellNotEditable();
		questionTableModel.addColumn("��");
		questionTableModel.addColumn("�^�C�g��");
		
		SimpleTable questionTable = new SimpleTable(questionTableModel);
        questionTable.setName("questionTable");
        questionTable.getTableHeader().setReorderingAllowed(false);
		this.setComponentColor(questionTable);
		questionTable.getColumnModel().getColumn(0).setMaxWidth(30);
		
		JScrollPane questionScrollPane = new JScrollPane(questionTable);
		questionScrollPane.setBounds(20, 350, 100, 140);
		questionScrollPane.setBackground(Color.ORANGE);
		questionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return questionScrollPane;
	}
	
    /**
     * �𓚃e�[�u�����쐬���܂��B
     * 
     * @return �𓚃e�[�u��
     */
	private JScrollPane createOptionTablePane() {
		SimpleTableModel optionTableModel = new SimpleTableModel();
        optionTableModel.addColumn("�폜");
        optionTableModel.addColumn("����");
		optionTableModel.addColumn("#");
		optionTableModel.addColumn("�𓚕�");
		
		SimpleTable optionTable = new SimpleTable(optionTableModel);
        optionTable.setName("optionTable");
        optionTable.getTableHeader().setReorderingAllowed(false);
		this.setComponentColor(optionTable);
		optionTable.getColumnModel().getColumn(0).setMaxWidth(30);
		optionTable.getColumnModel().getColumn(1).setMaxWidth(30);
        optionTable.getColumnModel().getColumn(2).setMaxWidth(30);
		
		JScrollPane optionScrollPane = new JScrollPane(optionTable);
		optionScrollPane.setBounds(130, 350, 330, 97);
		optionScrollPane.setBackground(Color.ORANGE);
		optionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return optionScrollPane;
	}
	
    /**
     * �𓚍폜�{�^�����쐬���܂��B<BR>
     * 
     * @return �𓚍폜�{�^��
     */
	private JButton createDeleteOptionButton() {
		JButton deleteOptionButton = new JButton("�I�����폜");
        deleteOptionButton.setName("deleteOptionButton");
		deleteOptionButton.setBounds(130, 455, 100, 20);
		return deleteOptionButton;
	}
	
    /**
     * �𓚒ǉ��{�^�����쐬���܂��B<BR>
     * 
     * @return �𓚒ǉ��{�^��
     */
	private JButton createAddOptionButton() {
		JButton addOptionButton = new JButton("�I�����ǉ�");
        addOptionButton.setName("addOptionButton");
		addOptionButton.setBounds(240, 455, 100, 20);
		return addOptionButton;
	}
    
    /**
     * �폜�̃��x�����쐬���܂��B
     * 
     * @return �폜�̃��x��
     */
    private JLabel createDeleteLabel() {
        final JLabel deleteLabel = new JLabel();
        deleteLabel.setName("deleteLabel");
        deleteLabel.setBounds(235, 480, 90, 20);
        deleteLabel.setText("�폜�X�V");
        deleteLabel.setHorizontalAlignment(JLabel.CENTER);
        deleteLabel.setOpaque(true);
        deleteLabel.setBackground(Color.ORANGE);
        deleteLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return deleteLabel;
    }
    
    /**
     * �폜�`�F�b�N�{�b�N�X�𐶐����܂��B
     * 
     * @return �폜�`�F�b�N�{�b�N�X
     */
    private JCheckBox createDeleteCheckBox() {
        JCheckBox deleteCheckBox = new JCheckBox();
        deleteCheckBox.setName("deleteCheckBox");
        deleteCheckBox.setBounds(330, 480, 20, 20);
        deleteCheckBox.setBackground(new Color(80,80,80));
        return deleteCheckBox;
    }
	
    /**
     * �o�^/�X�V�{�^�����쐬���܂��B<BR>
     * 
     * @return �o�^/�X�V�{�^��
     */
	private JButton createSaveOfUpdateButton() {
		JButton saveOrUpdateButton = new JButton("�o�^/�X�V");
        saveOrUpdateButton.setName("saveOrUpdateButton");
		saveOrUpdateButton.setBounds(360, 480, 100, 20);
        saveOrUpdateButton.setForeground(new Color(200, 10, 10));
		return saveOrUpdateButton;
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

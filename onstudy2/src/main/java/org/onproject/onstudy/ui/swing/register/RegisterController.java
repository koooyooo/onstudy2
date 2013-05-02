
package org.onproject.onstudy.ui.swing.register;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.onproject.onstudy.exception.ValidationException;
import org.onproject.onstudy.question.data.Option;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.question.service.QuestionService;
import org.onproject.onstudy.service.ServiceLocator;
import org.onproject.onstudy.ui.swing.core.controller.AbstractController;
import org.onproject.onstudy.ui.swing.core.controller.ControllerLocator;
import org.onproject.onstudy.ui.swing.core.ui.UI;
import org.onproject.onstudy.ui.swing.core.ui.UILocator;
import org.onproject.onstudy.ui.swing.table.SimpleTable;

/**
 * ����ʂ̃R���g���[��
 * 
 * @author ���c �D�f
 */
public class RegisterController extends AbstractController {
    
    /**
     * �Ή�����UI���擾���܂��B
     * 
     * @return �Ή�����UI
     */
    protected UI getTargetUI() {
        return UILocator.getRegisterUI();
    }
    
    /**
     * �����[�h�������s���܂��B
     * 
     */
    public void reload() {
        QuestionService service = ServiceLocator.getQuestionService();
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        questionTable.removeAllRow();
        for (String id : service.getIdSet()) {
            Question question = service.getQuestionById(id);
            questionTable.addRow(new Object[]{id, question.getTitle()});
        }
    }
    
    /**
     * ���Z�b�g�{�^���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void resetButtonMouseClicked(MouseEvent e) {
        ((JTextField) super.getComponent("idField")).setText("");
        ((JTextField) super.getComponent("titleField")).setText("");
        ((JTextArea) super.getComponent("sentenceArea")).setText("");
        ((JTextArea) super.getComponent("hintArea")).setText("");
        ((JTextArea) super.getComponent("expositoryWritingArea")).setText("");
        
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.removeAllRow();
    }
    
    /**
     * ���e�[�u���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
     */
    public void questionTableMouseClicked(MouseEvent e) {
        Question question = this.getCurrentQuestion();
        
        ((JTextField) super.getComponent("idField")).setText(question.getId());
        ((JTextField) super.getComponent("titleField")).setText(question.getTitle());
        
        this.setTextToAreaWithCursorPositionTop("sentenceArea", "sentenceSP", question.getSentence());
        this.setTextToAreaWithCursorPositionTop("hintArea", "hintSP", question.getHint());
        this.setTextToAreaWithCursorPositionTop("expositoryWritingArea", "expositoryWritingSP", question.getExpositoryWriting());
        
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.removeAllRow();
        for (Option option : question.optionIterable()) {
            Boolean isRightAnswer = new Boolean(option.isRightAnswer());
            optionTable.addRow(new Object[]{Boolean.FALSE, isRightAnswer, option.getId(), option.getText()});
        }
    }
    
    /**
     * �e�L�X�g�G���A�Ɏw��̕������\�����A�X�N���[���y�C���̕\������擪�ɍ��킹�܂��B
     * 
     * @param textAreaComponentName �e�L�X�g�G���A�̃R���|�[�l���g��
     * @param scrollPaneComponentName �X�N���[���y�C���̃R���|�[�l���g��
     * @param content �\�����镶����
     */
    private void setTextToAreaWithCursorPositionTop(String textAreaComponentName, String scrollPaneComponentName, String content) {
        JTextArea textArea = (JTextArea) super.getComponent(textAreaComponentName);
        textArea.setText(content);
        JScrollPane scrollPane = (JScrollPane) super.getComponent(scrollPaneComponentName);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
        textArea.updateUI();
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
        return  (String) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
    }
    
    /**
     * �𓚒ǉ��{�^�������C�x���g
     * 
     * @param e �}�E�X�C�x���g
     */
    public void addOptionButtonMouseClicked(MouseEvent e) {
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.addRow(new Object[]{Boolean.FALSE, Boolean.FALSE, optionTable.getRowCount() + 1, ""});
    }
    
    /**
     * �𓚍폜�{�^�������C�x���g
     * 
     * @param e �}�E�X�C�x���g
     */
    public void deleteOptionButtonMouseClicked(MouseEvent e) {
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        for (int i = 0; i < optionTable.getRowCount();) {
            boolean deleteCheck = ((Boolean) optionTable.getValueAt(i, 0)).booleanValue();
            if (deleteCheck) {
                optionTable.removeRow(i);
            } else {
                i++;
            }
        }
    }
    
    /**
     * �o�^�E�X�V�{�^�������C�x���g
     * 
     * @param e �}�E�X�C�x���g
     */
    public void saveOrUpdateButtonMouseClicked(MouseEvent e) {
        
        final JLabel messageLabel = (JLabel) super.getComponent("messageLabel");
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.editingCanceled(null);
        
        final Question question = this.createQuestion();
        try {
            this.validateQuestion(question);
            this.validateOptions(question);
        } catch (ValidationException ve) {
            messageLabel.setText(ve.getMessage());
            return;
        }
        
        JCheckBox deleteCheckBox = (JCheckBox) super.getComponent("deleteCheckBox");
        if (deleteCheckBox.isSelected()) {
            this.executeDeleteOperation(question.getId(), deleteCheckBox);
            return;
        }
        
        if (ServiceLocator.getQuestionService().isValidId(question.getId())) {
            int continueUpdate = JOptionPane.showConfirmDialog(this.getTargetUI().getFrameContext().getFrame(), "ID���d�����Ă��܂��B�X�V���܂����H");
            if (continueUpdate != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "�X�V���L�����Z�����܂���");
                return;
            }
        }
        ServiceLocator.getQuestionService().saveOrUpdateQuestion(question);
        
        JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "�o�^/�X�V���������܂���");
        ControllerLocator.reload();
        messageLabel.setText("");
    }
    
    /**
     * ��ʏ�̏�����ɁA���𐶐����܂��B
     * 
     * @return �������ꂽ���
     */
    private Question createQuestion() {
        final Question question = new Question();
        question.setId(((JTextField) super.getComponent("idField")).getText());
        question.setTitle(((JTextField) super.getComponent("titleField")).getText());
        question.setSentence(((JTextArea) super.getComponent("sentenceArea")).getText());
        question.setHint(((JTextArea) super.getComponent("hintArea")).getText());
        question.setExpositoryWriting(((JTextArea) super.getComponent("expositoryWritingArea")).getText());
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        for (int i = 0; i < optionTable.getRowCount(); i++) {
            Option option = new Option();
            option.setId(optionTable.getValueAt(i, 2).toString());
            option.setText((String) optionTable.getValueAt(i, 3));
            option.setRightAnswer(((Boolean) optionTable.getValueAt(i, 1)).booleanValue());
            question.addOption(option);
        }
        return question;
    }
    
    /**
     * �e�L�X�g�t�B�[���h�ƃe�L�X�g�G���A�̕K�{�`�F�b�N���s���܂��B
     * 
     * @param question ���
     * @throws ValidationException
     */
    private void validateQuestion(Question question) throws ValidationException {
        this.validateTextValue(question.getId(), "ID");
        this.validateTextValue(question.getTitle(), "�^�C�g��");
        this.validateTextValue(question.getSentence(), "���");
        this.validateTextValue(question.getHint(), "�q���g");
        this.validateTextValue(question.getExpositoryWriting(), "���");
    }
    
    /**
     * �𓚂𐸍����܂��B
     * 
     * @param question ���
     * @throws ValidationException 
     */
    private void validateOptions(Question question) throws ValidationException {
        boolean hasRightAnswer = false;
        final Set<String> optionIdSet = new HashSet<String>();
        for (Option option : question.optionIterable()) {
            this.validateTextValue(option.getId(), "��ID");
            this.validateTextValue(option.getText(), "��");
            if (option.isRightAnswer()) {
                hasRightAnswer = true;
            }
            if (optionIdSet.contains(option.getId())) {
                throw new ValidationException("��ID���d�����Ă��܂�");
            }
            optionIdSet.add(option.getId());
        }
        if (!hasRightAnswer) {
            throw new ValidationException("�������p�ӂ���Ă��܂���");
        }
    }
    
    /**
     * �K�{���͂̃o���f�[�V�������s���܂��B
     * 
     * @param target �Ώە���
     * @param inputName �_�����͖�
     * @throws ValidationException
     */
    private void validateTextValue(String target, String inputName) throws ValidationException {
        if ("".equals(target)) {
            throw new ValidationException(inputName + " ���͕K�{�ł�");
        }
    }

    /**
     * �폜���������s���܂��B
     * 
     * @param id ���ID
     * @param deleteCheckBox �폜�`�F�b�N�{�b�N�X
     */
    private void executeDeleteOperation(final String id, JCheckBox deleteCheckBox) {
        int continueUpdate = JOptionPane.showConfirmDialog(this.getTargetUI().getFrameContext().getFrame(), "�����폜���܂����H");
        if (continueUpdate == JOptionPane.OK_OPTION) {
            ServiceLocator.getQuestionService().removeQuestion(id);
            JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "�����폜���܂���");
            deleteCheckBox.setSelected(false);
            ControllerLocator.reload();
        }
    }
    
}

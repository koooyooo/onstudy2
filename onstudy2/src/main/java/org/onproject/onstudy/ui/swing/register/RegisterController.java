
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
 * 問題画面のコントローラ
 * 
 * @author 恩田 好庸
 */
public class RegisterController extends AbstractController {
    
    /**
     * 対応するUIを取得します。
     * 
     * @return 対応するUI
     */
    protected UI getTargetUI() {
        return UILocator.getRegisterUI();
    }
    
    /**
     * リロード処理を行います。
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
     * リセットボタンのマウス押下イベントです。
     * 
     * @param e マウスイベント
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
     * 問題テーブルのマウス押下イベントです。
     * 
     * @param e マウスイベント
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
     * テキストエリアに指定の文字列を表示し、スクロールペインの表示部を先頭に合わせます。
     * 
     * @param textAreaComponentName テキストエリアのコンポーネント名
     * @param scrollPaneComponentName スクロールペインのコンポーネント名
     * @param content 表示する文字列
     */
    private void setTextToAreaWithCursorPositionTop(String textAreaComponentName, String scrollPaneComponentName, String content) {
        JTextArea textArea = (JTextArea) super.getComponent(textAreaComponentName);
        textArea.setText(content);
        JScrollPane scrollPane = (JScrollPane) super.getComponent(scrollPaneComponentName);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
        textArea.updateUI();
    }
    
    /**
     * 現在選択されている問題を取得します。
     * 
     * @return 現在選択されている問題, 無ければ null
     */
    private Question getCurrentQuestion() {
        final String currentQuestionId = this.getCurrentQuestionId();
        if (currentQuestionId == null) {
            return null;
        }
        return ServiceLocator.getQuestionService().getQuestionById(currentQuestionId);
    }
    
    /**
     * 現在選択されている問題IDを取得します。
     * 
     * @return 現在選択されている問題ID, 無ければ null
     */
    private String getCurrentQuestionId() {
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        if (questionTable.getSelectedRow() == -1) {
            return null;
        }
        return  (String) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
    }
    
    /**
     * 解答追加ボタン押下イベント
     * 
     * @param e マウスイベント
     */
    public void addOptionButtonMouseClicked(MouseEvent e) {
        final SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.addRow(new Object[]{Boolean.FALSE, Boolean.FALSE, optionTable.getRowCount() + 1, ""});
    }
    
    /**
     * 解答削除ボタン押下イベント
     * 
     * @param e マウスイベント
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
     * 登録・更新ボタン押下イベント
     * 
     * @param e マウスイベント
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
            int continueUpdate = JOptionPane.showConfirmDialog(this.getTargetUI().getFrameContext().getFrame(), "IDが重複しています。更新しますか？");
            if (continueUpdate != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "更新をキャンセルしました");
                return;
            }
        }
        ServiceLocator.getQuestionService().saveOrUpdateQuestion(question);
        
        JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "登録/更新が完了しました");
        ControllerLocator.reload();
        messageLabel.setText("");
    }
    
    /**
     * 画面上の情報を基に、問題を生成します。
     * 
     * @return 生成された問題
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
     * テキストフィールドとテキストエリアの必須チェックを行います。
     * 
     * @param question 問題
     * @throws ValidationException
     */
    private void validateQuestion(Question question) throws ValidationException {
        this.validateTextValue(question.getId(), "ID");
        this.validateTextValue(question.getTitle(), "タイトル");
        this.validateTextValue(question.getSentence(), "問題");
        this.validateTextValue(question.getHint(), "ヒント");
        this.validateTextValue(question.getExpositoryWriting(), "解説");
    }
    
    /**
     * 解答を精査します。
     * 
     * @param question 問題
     * @throws ValidationException 
     */
    private void validateOptions(Question question) throws ValidationException {
        boolean hasRightAnswer = false;
        final Set<String> optionIdSet = new HashSet<String>();
        for (Option option : question.optionIterable()) {
            this.validateTextValue(option.getId(), "解答ID");
            this.validateTextValue(option.getText(), "解答");
            if (option.isRightAnswer()) {
                hasRightAnswer = true;
            }
            if (optionIdSet.contains(option.getId())) {
                throw new ValidationException("解答IDが重複しています");
            }
            optionIdSet.add(option.getId());
        }
        if (!hasRightAnswer) {
            throw new ValidationException("正解が用意されていません");
        }
    }
    
    /**
     * 必須入力のバリデーションを行います。
     * 
     * @param target 対象文字
     * @param inputName 論理入力名
     * @throws ValidationException
     */
    private void validateTextValue(String target, String inputName) throws ValidationException {
        if ("".equals(target)) {
            throw new ValidationException(inputName + " 欄は必須です");
        }
    }

    /**
     * 削除処理を実行します。
     * 
     * @param id 問題ID
     * @param deleteCheckBox 削除チェックボックス
     */
    private void executeDeleteOperation(final String id, JCheckBox deleteCheckBox) {
        int continueUpdate = JOptionPane.showConfirmDialog(this.getTargetUI().getFrameContext().getFrame(), "問題を削除しますか？");
        if (continueUpdate == JOptionPane.OK_OPTION) {
            ServiceLocator.getQuestionService().removeQuestion(id);
            JOptionPane.showMessageDialog(this.getTargetUI().getFrameContext().getFrame(), "問題を削除しました");
            deleteCheckBox.setSelected(false);
            ControllerLocator.reload();
        }
    }
    
}

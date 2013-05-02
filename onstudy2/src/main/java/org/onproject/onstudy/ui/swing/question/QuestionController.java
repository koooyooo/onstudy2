
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
 * 問題画面のコントローラ
 * 
 * @author 恩田 好庸
 */
public class QuestionController extends AbstractController {
    
    /** 改行文字 */
    private static final String BR = System.getProperty("line.separator");
    
    /**
     * 対応するUIを取得します。
     * 
     * @return 対応するUI
     */
    protected UI getTargetUI() {
        return UILocator.getQuestionUI();
    }
    
    /**
     * リロード処理を行います。
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
     * ID, タイトル, 問題表示欄をクリアします。
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
     * 問題サービスを取得します。
     * 
     * @return 問題サービス
     */
	protected QuestionService getQuestionService() {
		return ServiceLocator.getQuestionService();
	}
    
    /**
     * 問題テーブルのマウス押下イベントです。
     * 
     * @param e マウスイベント
     */
    public void questionTableMouseClicked(MouseEvent e) {
        this.loadQuestionToUI();
    }
    
    /**
     * 問題を変更します。
     *
     */
    private void loadQuestionToUI() {
        this.resetQuestionAreaColor();
        final Question question = this.getCurrentQuestion();
        this.showQuestion(question);
        this.showOptions(question.optionIterable());
    }
    
    /**
     * 問題テキストエリアを既定色にします。
     * 
     */
    private void resetQuestionAreaColor() {
        JTextArea questionArea = (JTextArea) super.getComponent("questionArea");
        questionArea.setBackground(new Color(80, 80, 80));
        questionArea.setForeground(Color.WHITE);
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
        return this.getQuestionService().getQuestionById(currentQuestionId);
    }
    
    /**
     * 現在選択されている問題IDを取得します。
     * 
     * @return 現在選択されている問題ID, 無ければ null
     */
    protected String getCurrentQuestionId() {
        final SimpleTable questionTable = (SimpleTable) super.getComponent("questionTable");
        if (questionTable.getSelectedRow() == -1) {
            return null;
        }
        return  (String) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
    }
    
    /**
     * 問題を表示します。
     * 
     * @param question 問題
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
     * 問題文と選択肢の文字列を生成します。
     * 
     * @param question 問題
     * @return 問題文と選択肢の文字列
     */
    private String createQuestionAndOptionText(Question question) {
        StringBuffer sentence = new StringBuffer();
        sentence.append(question.getSentence()).append(BR);
        sentence.append("　正解数(" + question.rightAnswerSize() + ")").append(BR);
        sentence.append(BR);
        for (Option option : question.optionIterable()) {
            sentence.append(option.getId() + " ： " + option.getText()).append(BR);
        }
        return sentence.toString();
    }
    
    /**
     * 選択肢を表示します。
     * 
     * @param optionIte 選択肢情報の反復子
     */
    private void showOptions(Iterable<Option> optionIterable) {
        SimpleTable optionTable = (SimpleTable) super.getComponent("optionTable");
        optionTable.removeAllRow();
        for (Option option : optionIterable) {
            optionTable.addRow(new Object[]{new Boolean(false), option.getId(), option.getText()});
        }
    }
    
    /**
     * 選択肢テーブルのマウス押下イベントです。
     * 
     * @param e マウスイベント
     */
    public void optionTableMouseClicked(MouseEvent e) {
        UIUtils.reverseCheckValue((SimpleTable) super.getComponent("optionTable"), 0);
    }
    
    /**
     * 解答ボタンのマウス押下イベントです。
     * 
     * @param e マウスイベント
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
     * 問題文と選択肢に解説を加えた文字列を生成します。
     * 
     * @param question 問題
     * @return 問題文と選択肢に解説を加えた文字列
     */
    private String createQuestionAndOptionWithExpositoryWriting(Question question) {
        StringBuffer sb = new StringBuffer();
        sb.append("【解説】").append(BR);
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
     * 解答された解答IDのSetを取得します。
     * 
     * @return 解答されたIDのSet
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
     * 解答IDのSetと正答IDのSetを比べ、解答が正しいかを判定します。
     * 
     * @param answerIdSet 解答IDのSet
     * @param rightAnswerIdSet 正答IDのSet
     * @return 全て正解であれば true
     */
    public boolean isRightAnswer(Set<String> answerIdSet, Set<String> rightAnswerIdSet) {
        if (answerIdSet.size() != rightAnswerIdSet.size()) {
            return false;
        }
        return answerIdSet.containsAll(rightAnswerIdSet);
    }
    
    /**
     * ヒントボタン押下時のイベントです。
     * 
     * @param e マウスイベント
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
     * 問題文と選択肢にヒントを加えた文字列を生成します。
     * 
     * @param question 問題
     * @return 問題文と選択肢にヒントを加えた文字列
     */
    private String createQuestionAndOptionWithHint(Question question) {
        StringBuffer sb = new StringBuffer();
        sb.append(createQuestionAndOptionText(question)).append(BR);
        sb.append(BR);
        sb.append(question.getHint());
        return sb.toString();
    }
    
    /**
     * 次へボタン押下時のイベントです。
     * 
     * @param e マウスイベント
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

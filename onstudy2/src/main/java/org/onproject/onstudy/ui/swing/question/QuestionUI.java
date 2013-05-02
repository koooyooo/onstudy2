package org.onproject.onstudy.ui.swing.question;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
 * 問題画面のUI
 * 
 * @author 恩田 好庸
 */
public class QuestionUI extends AbstractUI {
    
    /**
     * パネルを生成します。
     * 
     * @return 生成したパネル
     */
    protected JPanel createPanel() {
        JPanel panel = new ImagePanel("img/gradation1.png", true);
        // panel.setBackground(new Color(127, 140, 225));
        panel.setLayout(null);
        
        panel.add(this.createIdLabel());
        panel.add(this.createIdField());
        panel.add(this.createTitleLabel());
        panel.add(this.createTitleField());
        panel.add(this.createConsoleTextArea());
        panel.add(this.createQuestionTablePane());
        panel.add(this.createOptionTablePane());
        panel.add(this.createHintButton());
        panel.add(this.createAnswerButton());
        panel.add(this.createNextButton());
        return panel;
    }
    
    /**
     * IDのラベルを作成します。
     * 
     * @return IDのラベル
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
     * IDのフィールドを作成します。
     * 
     * @return IDのフィールド
     */
    private JTextField createIdField() {
        final JTextField idField = new JTextField();
        idField.setName("idField");
        idField.setBounds(55, 40, 40, 20);
        idField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        idField.setCaretColor(Color.LIGHT_GRAY);
        idField.setEditable(false);
        this.setComponentColor(idField);
        return idField;
    }
    
    /**
     * タイトルのラベルを作成します。
     * 
     * @return タイトルのラベル
     */
    private JLabel createTitleLabel() {
        final JLabel titleLabel = new JLabel();
        titleLabel.setName("titleLabel");
        titleLabel.setBounds(110, 40, 80, 20);
        titleLabel.setText("タイトル");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(titleLabel);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return titleLabel;
    }
    
    /**
     * タイトルのフィールドを作成します。
     * 
     * @return タイトルのフィールド
     */
    private JTextField createTitleField() {
        final JTextField titleField = new JTextField();
        titleField.setName("titleField");
        titleField.setBounds(195, 40, 265, 20);
        titleField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titleField.setCaretColor(Color.LIGHT_GRAY);
        titleField.setEditable(false);
        this.setComponentColor(titleField);
        return titleField;
    }

    /**
     * コンソールの問題テキストエリアを生成します。
     * 
     * @return コンソールのテキストエリア
     */
	private JScrollPane createConsoleTextArea() {
		final JTextArea questionArea = new JTextArea();
        questionArea.setName("questionArea");
		questionArea.setEditable(false);
        questionArea.setMargin(new Insets(10, 10, 10, 10));
		this.setComponentColor(questionArea);
        
        JScrollPane questionSP = new JScrollPane(questionArea);
        questionSP.setName("questionSP");
        questionSP.setBounds(20, 75, 440, 260);
        questionSP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
		return questionSP;
	}

    /**
     * 問題テーブルを作成します。
     * 
     * @return 問題テーブル
     */
	private JScrollPane createQuestionTablePane() {
        SimpleTableModel questionTableModel = new SimpleTableModel();
        questionTableModel.setAllCellNotEditable();
        questionTableModel.addColumn("＃");
		questionTableModel.addColumn("タイトル");
		
		SimpleTable questionTable = new SimpleTable(questionTableModel);
        questionTable.setName("questionTable");
		questionTable.getTableHeader().setReorderingAllowed(false);
		this.setComponentColor(questionTable);
		questionTable.getColumnModel().getColumn(0).setMaxWidth(30);
		
		JScrollPane questionScrollPane = new JScrollPane(questionTable);
		questionScrollPane.setBounds(20, 350, 150, 140);
		questionScrollPane.setBackground(Color.ORANGE);
		questionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return questionScrollPane;
	}
	
    /**
     * 選択肢テーブルを作成します。
     * 
     * @return 選択肢テーブル
     */
	private JScrollPane createOptionTablePane() {
		SimpleTableModel optionTableModel = new SimpleTableModel();
		optionTableModel.addNotEditableCol(1);
		optionTableModel.addNotEditableCol(2);
		optionTableModel.addColumn("");
		optionTableModel.addColumn("＃");
		optionTableModel.addColumn("解答");
		
		SimpleTable optionTable = new SimpleTable(optionTableModel);
        optionTable.setName("optionTable");
        optionTable.getTableHeader().setReorderingAllowed(false);
		this.setComponentColor(optionTable);
		optionTable.getColumnModel().getColumn(0).setMaxWidth(30);
		optionTable.getColumnModel().getColumn(1).setMaxWidth(30);
		
		
		JScrollPane optionScrollPane = new JScrollPane(optionTable);
		optionScrollPane.setBounds(180, 350, 280, 105);
		optionScrollPane.setBackground(Color.ORANGE);
		optionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return optionScrollPane;
	}
	
    /**
     * ヒントボタンを作成します。<BR>
     * 
     * @return ヒントボタン
     */
	private JButton createHintButton() {
		JButton hintButton = new JButton("ヒント");
        hintButton.setName("hintButton");
		hintButton.setBounds(180, 470, 80, 20);
		return hintButton;
	}
	
    /**
     * 解答ボタンを作成します。<BR>
     * 
     * @return 解答ボタン
     */
	private JButton createAnswerButton() {
		JButton answerButton = new JButton("解答");
        answerButton.setName("answerButton");
		answerButton.setBounds(290, 470, 80, 20);
		return answerButton;
	}
	
    /**
     * 次へボタンを作成します。<BR>
     * 
     * @return 次へボタン
     */
	private JButton createNextButton() {
		JButton nextButton = new JButton("次へ");
        nextButton.setName("nextButton");
		nextButton.setBounds(380, 470, 80, 20);
		return nextButton;
	}
	
    /**
     * コンポーネントに色付けを行います。
     * 
     * @param component 対象のコンポーネント
     */
	private void setComponentColor(final JComponent component) {
		component.setBackground(new Color(80, 80, 80));
		component.setForeground(Color.WHITE);
	}
	
}

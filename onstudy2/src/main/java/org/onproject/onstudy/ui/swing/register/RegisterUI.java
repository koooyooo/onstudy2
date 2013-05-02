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
 * 問題画面のUI
 * 
 * @author 恩田 好庸
 */
public class RegisterUI extends AbstractUI {
    
    /**
     * パネルを生成します。
     * 
     * @return 生成したパネル
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
     * メッセージラベルを作成します。
     * 
     * @return メッセージラベル
     */
    private JLabel createMessageLabel() {
        final JLabel msgLabel = new JLabel();
        msgLabel.setName("messageLabel");
        msgLabel.setBounds(20, 15, 200, 20);
        msgLabel.setForeground(new Color(230, 80, 80));
        return msgLabel;
    }
    
    /**
     * リセットボタンを作成します。
     * 
     * @return リセットボタン
     */
    private JButton createResetButton() {
        final JButton resetButton = new JButton("リセット");
        resetButton.setName("resetButton");
        resetButton.setBounds(370, 15, 90, 20);
        return resetButton;
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
        this.setComponentColor(titleField);
        return titleField;
    }
    
    /**
     * 問題のラベルを作成します。
     * 
     * @return 問題のラベル
     */
    private JLabel createSentenceLabel() {
        final JLabel sentenceLabel = new JLabel();
        sentenceLabel.setName("sentenceLabel");
        sentenceLabel.setBounds(20, 70, 50, 120);
        sentenceLabel.setText("問題");
        sentenceLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(sentenceLabel);
        sentenceLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return sentenceLabel;
    }
    
    /**
     * 問題テキストエリアを生成します。
     * 
     * @return 問題テキストエリア
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
     * ヒントのラベルを作成します。
     * 
     * @return ヒントのラベル
     */
    private JLabel createHintLabel() {
        final JLabel hintLabel = new JLabel();
        hintLabel.setName("hintLabel");
        hintLabel.setBounds(20, 200, 50, 65);
        hintLabel.setText("ヒント");
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(hintLabel);
        hintLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return hintLabel;
    }
    
    /**
     * ヒントテキストエリアを生成します。
     * 
     * @return ヒントテキストエリア
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
     * 解説のラベルを作成します。
     * 
     * @return 解説のラベル
     */
    private JLabel createExpositoryWritingLabel() {
        final JLabel expositoryWritingLabel = new JLabel();
        expositoryWritingLabel.setName("expositoryWritingLabel");
        expositoryWritingLabel.setBounds(20, 275, 50, 65);
        expositoryWritingLabel.setText("解説");
        expositoryWritingLabel.setHorizontalAlignment(JLabel.CENTER);
        UIUtils.setLabelColor(expositoryWritingLabel);
        expositoryWritingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return expositoryWritingLabel;
    }
    
    /**
     * 解説のテキストエリアを生成します。
     * 
     * @return ヒントテキストエリア
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
		questionScrollPane.setBounds(20, 350, 100, 140);
		questionScrollPane.setBackground(Color.ORANGE);
		questionScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return questionScrollPane;
	}
	
    /**
     * 解答テーブルを作成します。
     * 
     * @return 解答テーブル
     */
	private JScrollPane createOptionTablePane() {
		SimpleTableModel optionTableModel = new SimpleTableModel();
        optionTableModel.addColumn("削除");
        optionTableModel.addColumn("正解");
		optionTableModel.addColumn("#");
		optionTableModel.addColumn("解答文");
		
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
     * 解答削除ボタンを作成します。<BR>
     * 
     * @return 解答削除ボタン
     */
	private JButton createDeleteOptionButton() {
		JButton deleteOptionButton = new JButton("選択肢削除");
        deleteOptionButton.setName("deleteOptionButton");
		deleteOptionButton.setBounds(130, 455, 100, 20);
		return deleteOptionButton;
	}
	
    /**
     * 解答追加ボタンを作成します。<BR>
     * 
     * @return 解答追加ボタン
     */
	private JButton createAddOptionButton() {
		JButton addOptionButton = new JButton("選択肢追加");
        addOptionButton.setName("addOptionButton");
		addOptionButton.setBounds(240, 455, 100, 20);
		return addOptionButton;
	}
    
    /**
     * 削除のラベルを作成します。
     * 
     * @return 削除のラベル
     */
    private JLabel createDeleteLabel() {
        final JLabel deleteLabel = new JLabel();
        deleteLabel.setName("deleteLabel");
        deleteLabel.setBounds(235, 480, 90, 20);
        deleteLabel.setText("削除更新");
        deleteLabel.setHorizontalAlignment(JLabel.CENTER);
        deleteLabel.setOpaque(true);
        deleteLabel.setBackground(Color.ORANGE);
        deleteLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return deleteLabel;
    }
    
    /**
     * 削除チェックボックスを生成します。
     * 
     * @return 削除チェックボックス
     */
    private JCheckBox createDeleteCheckBox() {
        JCheckBox deleteCheckBox = new JCheckBox();
        deleteCheckBox.setName("deleteCheckBox");
        deleteCheckBox.setBounds(330, 480, 20, 20);
        deleteCheckBox.setBackground(new Color(80,80,80));
        return deleteCheckBox;
    }
	
    /**
     * 登録/更新ボタンを作成します。<BR>
     * 
     * @return 登録/更新ボタン
     */
	private JButton createSaveOfUpdateButton() {
		JButton saveOrUpdateButton = new JButton("登録/更新");
        saveOrUpdateButton.setName("saveOrUpdateButton");
		saveOrUpdateButton.setBounds(360, 480, 100, 20);
        saveOrUpdateButton.setForeground(new Color(200, 10, 10));
		return saveOrUpdateButton;
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

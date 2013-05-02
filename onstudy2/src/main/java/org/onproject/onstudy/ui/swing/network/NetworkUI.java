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
 * ネットワーク画面のUI
 * 
 * @author 恩田 好庸
 */
public class NetworkUI extends AbstractUI {
    
    /**
     * パネルを生成します。
     * 
     * @return 生成したパネル
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
     * リセットボタンを作成します。
     * 
     * @return リセットボタン
     */
    private JButton createReloadButton() {
        final JButton reloadButton = new JButton("リロード");
        reloadButton.setName("reloadButton");
        reloadButton.setBounds(370, 15, 90, 20);
        return reloadButton;
    }
    
    /**
     * メンバーテーブルを作成します。
     * 
     * @return メンバーテーブル
     */
    private JScrollPane createMemberTablePane() {
        SimpleTableModel memberTableModel = new SimpleTableModel();
        memberTableModel.addNotEditableCol(1);
        memberTableModel.addNotEditableCol(2);
        memberTableModel.addNotEditableCol(3);
        memberTableModel.addColumn("");
        memberTableModel.addColumn("名前");
        memberTableModel.addColumn("IP");
        memberTableModel.addColumn("ポート");
        
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
     * 問題テーブルを作成します。
     * 
     * @return 問題テーブル
     */
	private JScrollPane createQuestionTablePane() {
        SimpleTableModel questionTableModel = new SimpleTableModel();
        questionTableModel.addNotEditableCol(1);
        questionTableModel.addNotEditableCol(2);
        questionTableModel.addColumn("");
        questionTableModel.addColumn("＃");
		questionTableModel.addColumn("タイトル");
		
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
     * 問題テキストエリアを生成します。
     * 
     * @return 問題テキストエリア
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
     * 送信ボタンを作成します。
     * 
     * @return リセットボタン
     */
    private JButton createSendButton() {
        final JButton sendButton = new JButton("送信");
        sendButton.setName("sendButton");
        sendButton.setBounds(370, 340, 90, 20);
        return sendButton;
    }
    
    /**
     * メンバーテーブルを作成します。
     * 
     * @return メンバーテーブル
     */
    private JScrollPane createResultTablePane() {
        SimpleTableModel resultTableModel = new SimpleTableModel();
        resultTableModel.addNotEditableCol(1);
        resultTableModel.addNotEditableCol(2);
        resultTableModel.addNotEditableCol(3);
        resultTableModel.addColumn("");
        resultTableModel.addColumn("名前");
        resultTableModel.addColumn("IP");
        resultTableModel.addColumn("ポート");
        
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
     * コンポーネントに色付けを行います。
     * 
     * @param component 対象のコンポーネント
     */
	private void setComponentColor(final JComponent component) {
		component.setBackground(new Color(80, 80, 80));
		component.setForeground(Color.WHITE);
	}
	
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/BaseFrameOperator.java,v $
  Version : $Revision: 1.11 $
  Date    : $Date: 2007/07/05 02:34:50 $
******************************************************************************/
package org.onproject.onstudy.ui.swing;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.question.dao.QuestionDAODigesterImpl;
import org.onproject.onstudy.service.ServiceLocator;
import org.onproject.onstudy.ui.swing.core.controller.ControllerLocator;
import org.onproject.onstudy.ui.swing.core.listener.ReflectMouseListener;
import org.onproject.onstudy.ui.swing.core.ui.UILocator;

/**
 * フレーム制御の担当クラス
 * 
 * @author 恩田 好庸
 */
public class BaseFrameOperator {
    
    /** 処理対象のフレーム */
    private final JFrame frame = new JFrame();
    
    /**
     * フレームの設定を行います。
     *
     */
    public void controll() {
    	
//    	if (2009 != Calendar.getInstance().get(Calendar.YEAR)) {
//    		System.out.println("■ Can not invoke OnStudy v2 because of expiration ■");
//    		return;
//    	} // TODO
    	
        try {
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setForeground(new Color(199, 220, 104));
            progressBar.setStringPainted(true);
            
            ProgressObserver.setJProgressBar(progressBar);
            
            final JDialog startUpDialog = this.createStartupDialog(progressBar);
            
            this.initLocator(this.frame);
            
            ProgressObserver.setProgress(100);
            Thread.sleep(500);
            
            startUpDialog.dispose();

            UIManager.put("TabbedPane.selected", new Color(114, 143, 191));
            UIManager.put("TabbedPane.light", new Color(127, 140, 225));
            
            UIManager.put("TabbedPane.selectHighlight", new Color(127, 140, 225));
            UIManager.put("TabbedPane.borderHightlightColor", Color.BLACK);
            UIManager.put("TabbedPane.darkShadow", Color.GRAY);
            
            UIManager.put("Button.shadow", Color.BLACK);
            
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setName("tab");
            tabbedPane.addTab("Main Operation", null, UILocator.getQuestionUI().getPanel(), "main tab");
            tabbedPane.addTab("Register Operation", null, UILocator.getRegisterUI().getPanel(), "register tab");
            tabbedPane.addTab("Network Operation", null, UILocator.getNetworkUI().getPanel(), "network tab");
            tabbedPane.addTab("Network Question", null, UILocator.getNQUI().getPanel(), "network question tab");
            tabbedPane.setForeground(new Color(50, 50, 50));
            
            frame.add(tabbedPane);
            frame.setJMenuBar(this.createMenuBar());
            frame.setResizable(false);
            frame.setIconImage(frame.getToolkit().createImage("./img/logo.png"));
            frame.setBounds(200, 200, 500, 600);
            frame.setVisible(true);
            frame.setTitle("On-Study v2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
    }
    
    /**
     * スタートアップダイアログを作成します。
     * 
     * @return スタートアップダイアログ
     */
    private JDialog createStartupDialog(JProgressBar progressBar) {

        JDialog startUpDialog = new JDialog();
        
        JPanel dialogPanel = new JPanel();        
        ImageIcon icon = new ImageIcon("img/dialog.png");
        
        JLabel dialogLabel = new JLabel();
        dialogLabel.setIcon(icon);
        dialogPanel.add(dialogLabel);
        
        progressBar.setBounds(0, 190, 300, 10);

        startUpDialog.add(progressBar);
        startUpDialog.add(dialogPanel);
        startUpDialog.setTitle("initializing OnStudy v2 ...");
        startUpDialog.setResizable(false);
        startUpDialog.setFocusable(false);        
        startUpDialog.setBounds(300, 350, 300, 230);
        startUpDialog.setVisible(true);
        startUpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        return startUpDialog;
    }
    
    /**
     * 各ロケータの初期化を行います。<BR>
     * 
     * （注）ロケータの初期化順序には依存関係がある
     */
    private void initLocator(JFrame frame) {
        ServiceLocator.init();
        UILocator.init(frame);
        ControllerLocator.init();
    }
    
    /**
     * メニューバーを設定します。
     * 
     * @return メニューバー
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.createConfigMenu());
        return menuBar;
    }
    
    /**
     * 設定メニューを生成します。
     * 
     * @return 設定メニュー
     */
    private JMenu createConfigMenu() {
        JMenu configMenu = new JMenu("設定");
        configMenu.add(this.createLookAndFeelMenu());
        configMenu.add(createImplementsSettingMenu());
        return configMenu;
    }
    
    /**
     * LookAndFeelメニューを生成します。
     * 
     * @return LookAndFeelメニュー
     */
    private JMenu createLookAndFeelMenu() {
        JMenu lookAndFeelItem = new JMenu();
        lookAndFeelItem.setName("lookAndFeelItem");
        lookAndFeelItem.setText("ルック＆フィール");

        JMenuItem metalMenuItem = new JMenuItem();
        metalMenuItem.setName("metalMenuItem");
        metalMenuItem.setText("Swing-Metal");
        metalMenuItem.addMouseListener(new ReflectMouseListener(this));
        
        JMenuItem windowsMenuItem = new JMenuItem();
        windowsMenuItem.setName("windowsMenuItem");
        windowsMenuItem.setText("Windows");
        windowsMenuItem.addMouseListener(new ReflectMouseListener(this));
        
        JMenuItem motifMenuItem = new JMenuItem();
        motifMenuItem.setName("motifMenuItem");
        motifMenuItem.setText("Motif");
        motifMenuItem.addMouseListener(new ReflectMouseListener(this));
        
        lookAndFeelItem.add(metalMenuItem);
        lookAndFeelItem.add(windowsMenuItem);
        lookAndFeelItem.add(motifMenuItem);
        return lookAndFeelItem;
    }
    
    /**
     * 実装毎のメニューを生成します。
     * 
     * @return 実装毎のメニュー
     */
    private JMenu createImplementsSettingMenu() {
        JMenu implementsSetting = new JMenu();
        implementsSetting.setName("implementsSetting");
        implementsSetting.setText("実装毎設定");
        
        JMenu fileImplSetting = new JMenu();
        fileImplSetting.setName("fileImplSetting");
        fileImplSetting.setText("ファイル");
        
        JMenuItem questionDirMenuItem = new JMenuItem();
        questionDirMenuItem.setName("questionDirMenuItem");
        questionDirMenuItem.setText("問題ディレクトリ");
        questionDirMenuItem.addMouseListener(new ReflectMouseListener(this));

        fileImplSetting.add(questionDirMenuItem);
        implementsSetting.add(fileImplSetting);
        
        return implementsSetting;
    }

    /**
     * 問題ディレクトリメニューのマウス開放処理です。
     * 
     * @param e マウスイベント
     */
    public void questionDirMenuItemMouseReleased(MouseEvent e) {
        File selectedFile = this.selectFile();
        if (selectedFile == null) {
            return;
        }
        QuestionDAODigesterImpl impl = (QuestionDAODigesterImpl) ServiceLocator.getRawBean("questionDAODigesterImpl");
        impl.setQuestionStoreDir(selectedFile);
        JOptionPane.showMessageDialog(this.frame, "問題ディレクトリは [" + selectedFile + "]に変更されました。");
        ControllerLocator.reload();
    }
    
    /**
     * ファイル選択ダイアログを表示し、ファイルを指定します。
     * 
     * @return 指定されたファイル
     */
    private File selectFile() {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this.frame);
        return chooser.getSelectedFile();
    }
    
    /**
     * Metal メニューのマウス開放処理です。
     * 
     * @param event マウスイベント
     */
    public void metalMenuItemMouseReleased(MouseEvent event) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            frame.repaint();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
     * Windows メニューのマウス開放処理です。
     * 
     * @param event マウスイベント
     */
    public void windowsMenuItemMouseReleased(MouseEvent event) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            frame.repaint();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
     * Motif メニューのマウス開放処理です。
     * 
     * @param event マウスイベント
     */
    public void motifMenuItemMouseReleased(MouseEvent event) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            frame.repaint();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    

    

    
    

}

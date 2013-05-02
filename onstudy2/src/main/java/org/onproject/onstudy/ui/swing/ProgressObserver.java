/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/ProgressObserver.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/22 06:45:33 $
******************************************************************************/
package org.onproject.onstudy.ui.swing;

import javax.swing.JProgressBar;

/**
 * 進捗監視者
 * 
 * @author 恩田 好庸
 */
public class ProgressObserver {
    
    /** 処理を委譲するプログレスバー */
    private static JProgressBar progressBar;
    
    /**
     * プログラスバーを設定します。
     * 
     * @param bar
     */
    protected static void setJProgressBar(JProgressBar bar) {
        progressBar = bar;
    }
    
    /**
     * 進捗を既存の進捗に追加します。
     * 
     * @param percentage
     */
    public static void addProgress(int percentage) {
        progressBar.setValue(progressBar.getValue() + percentage);
    }
    
    /**
     * 進捗を設定します。
     * 
     * @param percentage
     */
    public static void setProgress(int percentage) {
        progressBar.setValue(percentage);
    }
    
}

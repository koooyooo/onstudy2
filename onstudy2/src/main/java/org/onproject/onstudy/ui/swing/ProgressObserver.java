/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/ProgressObserver.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/22 06:45:33 $
******************************************************************************/
package org.onproject.onstudy.ui.swing;

import javax.swing.JProgressBar;

/**
 * �i���Ď���
 * 
 * @author ���c �D�f
 */
public class ProgressObserver {
    
    /** �������Ϗ�����v���O���X�o�[ */
    private static JProgressBar progressBar;
    
    /**
     * �v���O���X�o�[��ݒ肵�܂��B
     * 
     * @param bar
     */
    protected static void setJProgressBar(JProgressBar bar) {
        progressBar = bar;
    }
    
    /**
     * �i���������̐i���ɒǉ����܂��B
     * 
     * @param percentage
     */
    public static void addProgress(int percentage) {
        progressBar.setValue(progressBar.getValue() + percentage);
    }
    
    /**
     * �i����ݒ肵�܂��B
     * 
     * @param percentage
     */
    public static void setProgress(int percentage) {
        progressBar.setValue(percentage);
    }
    
}

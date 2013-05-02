/******************************************************************************
 Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/controller/Controller.java,v $
 Version : $Revision: 1.3 $
 Date    : $Date: 2007/06/21 06:14:28 $
 ******************************************************************************/
package org.onproject.onstudy.ui.swing.core.controller;


/**
 * コントローラ
 * 
 * 対象のUIよりイベントを受け取り、画面を操作する責務を負います。
 * 
 * @author 恩田 好庸
 */
public interface Controller {
    
    /**
     * 初期化を行います。<BR>
     *
     */
    public void init();
    
    /**
     * ロード処理です。
     *
     */
    public void load();

}
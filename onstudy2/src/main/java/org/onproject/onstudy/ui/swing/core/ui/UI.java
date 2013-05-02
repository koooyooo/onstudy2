/******************************************************************************
 Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/UI.java,v $
 Version : $Revision: 1.2 $
 Date    : $Date: 2007/04/19 07:33:21 $
 ******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 画面UIインターフェイス
 * 
 * @author 恩田 好庸
 */
public interface UI {
    
    /**
     * 初期化処理を行います。<BR>
     *
     */
    public void init(JFrame frame);
    
    /**
     * フレームコンテキストを取得します。<BR>
     * 
     * @return フレームコンテキスト
     */
    public FrameContext getFrameContext();
    
    /**
     * パネルを取得します。
     * 
     * @return 取得したパネル
     */
    public abstract JPanel getPanel();

    /**
     * 名称に対応したコンポーネントを取得します。<BR>
     * 
     * @param componentName
     * @return
     */
    public abstract Component getComponent(String componentName);

    /**
     * コンポーネントの反復子を取得します。<BR>
     * 
     * @return コンポーネントの反復子
     */
    public abstract Iterator<Component> componentIterator();

}
/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/FrameContext.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/04/19 07:33:21 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;

import org.onproject.onstudy.ui.swing.common.UIUtils;

/**
 * フレームの操作を行うクラス
 * 
 * @author 恩田 好庸
 */
public class FrameContext {
    
    /** フレーム */
    private final JFrame frame;
    
    /**
     * コンストラクタ
     * 
     * @param frame フレーム
     */
    public FrameContext(JFrame frame) {
        this.frame = frame;
    }
    
    /**
     * フレームを取得します。
     * 
     * @return フレーム
     */
    public JFrame getFrame() {
        return this.frame;
    }
    
    /**
     * フレームのコンポーネントを取得します。
     * 
     * @param componentName コンポーネント名
     * @return フレームのコンポーネント
     */
    public Component getFrameComponent(String componentName) {
        for (Component eachComponent : UIUtils.getAllComponentList(this.frame)) {
            if (componentName.equals(eachComponent.getName())) {
                return eachComponent;
            }
        }
        return null;
    }
    
    /**
     * フレームコンポーネントの反復子を取得します。
     * 
     * @return フレームコンポーネントの反復子
     */
    public Iterator<Component> frameComponentIterator() {
        return UIUtils.getAllComponentList(this.frame).iterator();
    }
    
}

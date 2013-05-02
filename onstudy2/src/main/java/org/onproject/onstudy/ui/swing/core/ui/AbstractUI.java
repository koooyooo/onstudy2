/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/AbstractUI.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/04/19 07:33:21 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.onproject.onstudy.ui.swing.common.UIUtils;

/**
 * 抽象UIクラス
 * 
 * @author 恩田 好庸
 */
public abstract class AbstractUI implements UI {
    
    /** フレーム */
    private JFrame frame;
    
    /** パネル */
    private JPanel panel;
    
    /**
     * 初期化を行います。
     *
     */
    public void init(JFrame frame) {
        this.frame = frame;
        this.panel = createPanel();
    }
    
    /**
     * フレームコンテキストを取得します。
     * 
     * @return フレームコンテキスト
     */
    public FrameContext getFrameContext() {
        return new FrameContext(this.frame);
    }
    
    /**
     * パネルを作成します。
     * 
     * @return 作成したパネル
     */
    protected abstract JPanel createPanel(); // TODO 変数に入れるタイミングが問題だ。
    
    /**
     * パネルを取得します。
     * 
     * @return 取得したパネル
     */
    public JPanel getPanel() {
        return panel;
    }
    
    /**
     * 名称に対応したコンポーネントを取得します。<BR>
     * 
     * @param componentName
     * @return
     */
    public Component getComponent(String componentName) {
        for (Component eachComponent : UIUtils.getAllComponentList(this.panel)) {
            if (componentName.equals(eachComponent.getName())) {
                return eachComponent;
            }
        }
        return null;
    }
    
    /**
     * コンポーネントの反復子を取得します。<BR>
     * 
     * @return コンポーネントの反復子
     */
    public Iterator<Component> componentIterator() { 
        return UIUtils.getAllComponentList(this.panel).iterator();
    }
    
    /**
     * 文字列表現を取得します。<BR>
     * 
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator componentIterator = this.componentIterator(); componentIterator.hasNext(); ) {
            Component eachComponent = componentIterator().next();
            sb.append(eachComponent.getName() + "(" + eachComponent.getClass().getSimpleName() + ")");
            if (componentIterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

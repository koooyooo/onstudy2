/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/controller/AbstractController.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/21 06:16:59 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.controller;

import java.awt.Component;
import java.util.Iterator;

import org.onproject.onstudy.ui.swing.core.listener.ReflectMouseListener;
import org.onproject.onstudy.ui.swing.core.ui.FrameContext;
import org.onproject.onstudy.ui.swing.core.ui.UI;

/**
 * 抽象コントローラ
 * 
 * 以下の責務を負います。
 * 
 * １）初期化時に、対応するUIの全てのコンポーネントに反響リスナを埋め込みます。
 * ２）名前を指定するとコンポーネントが取得できる機構を用意します。
 * 
 * @author 恩田 好庸
 */
public abstract class AbstractController implements Controller {
    
    /**
     * 初期化処理です。
     * 
     */
    public void init() {
        this.addReflectListeners();
        this.initialize();
    }
    
    /**
     * ロード処理です。
     * 
     */
    public void load() {
    	this.reload();
    }
    
    /**
     * 全てのコンポーネントに反響リスナを追加します。
     * 
     */
    private void addReflectListeners() {
        for (Iterator<Component> componentIte = this.getTargetUI().componentIterator(); componentIte.hasNext(); ) {
            Component component = componentIte.next();
            this.addListener(component);
        }
    }
    
    /**
     * 指定されたコンポーネントに、反響リスナを追加します。
     * 
     * @param component 対象のコンポーネント
     */
    private void addListener(Component component) {
        component.addMouseListener(new ReflectMouseListener(this));
    }
    
    /**
     * コンポーネントを取得します。<BR>
     * 
     * @param componentName コンポーネント名
     */
    protected Component getComponent(String componentName) {
        return this.getTargetUI().getComponent(componentName);
    }
    
    /**
     * フレームコンテキストを取得します。
     * 
     * @return フレームコンテキスト
     */
    protected FrameContext getFrameContext() {
        return this.getTargetUI().getFrameContext();
    }
    
    /**
     * 対応するUIを取得します。<BR>
     * 
     * @return 対応するUI
     */
    protected abstract UI getTargetUI();
    
    /**
     * 初期処理を行います。<BR>
     *
     */
    protected void initialize() {
        this.reload();
    }
    
    /**
     * 初期化後の再読み込み処理を行います。
     * 
     */
    protected abstract void reload();
    
}

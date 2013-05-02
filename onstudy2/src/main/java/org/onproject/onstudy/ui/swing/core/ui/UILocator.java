/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/UILocator.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/06/22 06:45:32 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.lang.reflect.Field;

import javax.swing.JFrame;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.ui.swing.ProgressObserver;
import org.onproject.onstudy.ui.swing.network.NetworkUI;
import org.onproject.onstudy.ui.swing.nq.NQUI;
import org.onproject.onstudy.ui.swing.question.QuestionUI;
import org.onproject.onstudy.ui.swing.register.RegisterUI;

/**
 * UIのロケータ
 * 
 * @author 恩田 好庸
 */
public class UILocator {
    
    /** 問題画面 */
    private static QuestionUI questionUI = new QuestionUI();
    
    /** 登録画面 */
    private static RegisterUI registerUI = new RegisterUI();
    
    /** ネットワーク画面 */
    private static NetworkUI networkUI = new NetworkUI();
    
    /** ネットワーク問題画面 */
    private static NQUI nqUI = new NQUI();
    
    /**
     * 初期化処理を行います。<BR>
     *
     */
    public static void init(JFrame frame) {
        Field[] fields = UILocator.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getName().startsWith("frame") || field.getName().startsWith("class")) {
                    continue;
                }
                UI ui = (UI) field.get(null);
                ui.init(frame);
                
                ProgressObserver.addProgress((int) ((100 / fields.length) * 0.33));
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
     * 問題画面を取得します。
     * 
     * @return 問題画面
     */
    public static QuestionUI getQuestionUI() {
        return questionUI;
    }
    
    /**
     * 登録画面を取得します。
     * 
     * @return 登録画面
     */
    public static RegisterUI getRegisterUI() {
        return registerUI;
    }
    
    /**
     * ネットワーク画面を取得します。
     * 
     * @return ネットワーク画面
     */
    public static NetworkUI getNetworkUI() {
        return networkUI;
    }
    
    /**
     * ネットワーク問題画面を取得します。
     * 
     * @return ネットワーク問題画面
     */
    public static NQUI getNQUI() {
    	return nqUI;
    }
    
}

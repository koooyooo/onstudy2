/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/controller/ControllerLocator.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/22 06:45:33 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.controller;

import java.lang.reflect.Field;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.ui.swing.ProgressObserver;
import org.onproject.onstudy.ui.swing.network.NetworkController;
import org.onproject.onstudy.ui.swing.nq.NQController;
import org.onproject.onstudy.ui.swing.question.QuestionController;
import org.onproject.onstudy.ui.swing.register.RegisterController;

/**
 * コントローラのロケータ
 * 
 * @author 恩田 好庸
 */
public class ControllerLocator {
    
    
    /** 問題コントローラ */
    private static QuestionController questionController = new QuestionController();
    
    /** 登録コントローラ */
    private static RegisterController registerController = new RegisterController();
    
    /** ネットワークコントローラ */
    private static NetworkController networkController = new NetworkController();
    
    /** ネットワーク問題コントローラ */
    private static NQController nqController = new NQController();
    
    /**
     * 初期化処理を行います。<BR>
     *
     */
    public static void init() {
        Field[] fields = ControllerLocator.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getName().startsWith("class")) {
                	continue;
                }
                Controller controller = (Controller) field.get(null);
                controller.init();
                
                ProgressObserver.addProgress((int) ((100 / fields.length) * 0.33));
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
     * リロード処理を行います。<BR>
     *
     */
    public static void reload() {
        Field[] fields = ControllerLocator.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getName().startsWith("class")) {
                	continue;
                }
                Controller controller = (Controller) field.get(null);
                controller.load();
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
     * 問題コントローラを取得します。
     * 
     * @return 問題コントローラ
     */
    public static QuestionController getQuestionController() {
        return questionController;
    }
    
    /**
     * 登録コントローラを取得します。
     * 
     * @return 登録コントローラ
     */
    public static RegisterController getRegisterController() {
        return registerController;
    }
    
    /**
     * ネットワークコントローラを取得します。
     * 
     * @return ネットワークコントローラ
     */
    public static NetworkController getNetworkController() {
        return networkController;
    }
    
    /**
     * ネットワーク問題コントローラを取得します。
     * 
     * @return ネットワーク問題コントローラ
     */
    public static NQController getNQController() {
    	return nqController;
    }
    
}

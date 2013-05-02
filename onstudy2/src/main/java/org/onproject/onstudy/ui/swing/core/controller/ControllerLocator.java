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
 * �R���g���[���̃��P�[�^
 * 
 * @author ���c �D�f
 */
public class ControllerLocator {
    
    
    /** ���R���g���[�� */
    private static QuestionController questionController = new QuestionController();
    
    /** �o�^�R���g���[�� */
    private static RegisterController registerController = new RegisterController();
    
    /** �l�b�g���[�N�R���g���[�� */
    private static NetworkController networkController = new NetworkController();
    
    /** �l�b�g���[�N���R���g���[�� */
    private static NQController nqController = new NQController();
    
    /**
     * �������������s���܂��B<BR>
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
     * �����[�h�������s���܂��B<BR>
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
     * ���R���g���[�����擾���܂��B
     * 
     * @return ���R���g���[��
     */
    public static QuestionController getQuestionController() {
        return questionController;
    }
    
    /**
     * �o�^�R���g���[�����擾���܂��B
     * 
     * @return �o�^�R���g���[��
     */
    public static RegisterController getRegisterController() {
        return registerController;
    }
    
    /**
     * �l�b�g���[�N�R���g���[�����擾���܂��B
     * 
     * @return �l�b�g���[�N�R���g���[��
     */
    public static NetworkController getNetworkController() {
        return networkController;
    }
    
    /**
     * �l�b�g���[�N���R���g���[�����擾���܂��B
     * 
     * @return �l�b�g���[�N���R���g���[��
     */
    public static NQController getNQController() {
    	return nqController;
    }
    
}

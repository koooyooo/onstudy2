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
 * UI�̃��P�[�^
 * 
 * @author ���c �D�f
 */
public class UILocator {
    
    /** ����� */
    private static QuestionUI questionUI = new QuestionUI();
    
    /** �o�^��� */
    private static RegisterUI registerUI = new RegisterUI();
    
    /** �l�b�g���[�N��� */
    private static NetworkUI networkUI = new NetworkUI();
    
    /** �l�b�g���[�N����� */
    private static NQUI nqUI = new NQUI();
    
    /**
     * �������������s���܂��B<BR>
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
     * ����ʂ��擾���܂��B
     * 
     * @return �����
     */
    public static QuestionUI getQuestionUI() {
        return questionUI;
    }
    
    /**
     * �o�^��ʂ��擾���܂��B
     * 
     * @return �o�^���
     */
    public static RegisterUI getRegisterUI() {
        return registerUI;
    }
    
    /**
     * �l�b�g���[�N��ʂ��擾���܂��B
     * 
     * @return �l�b�g���[�N���
     */
    public static NetworkUI getNetworkUI() {
        return networkUI;
    }
    
    /**
     * �l�b�g���[�N����ʂ��擾���܂��B
     * 
     * @return �l�b�g���[�N�����
     */
    public static NQUI getNQUI() {
    	return nqUI;
    }
    
}

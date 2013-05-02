/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/service/ServiceLocator.java,v $
  Version : $Revision: 1.9 $
  Date    : $Date: 2007/06/22 06:45:33 $
******************************************************************************/
package org.onproject.onstudy.service;

import java.util.Arrays;
import java.util.List;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.service.NetworkQuestionService;
import org.onproject.onstudy.net.service.NetworkService;
import org.onproject.onstudy.question.service.QuestionService;
import org.onproject.onstudy.ui.swing.ProgressObserver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �e��T�[�r�X����舵�����P�[�^
 * 
 * @author ���c �D�f
 */
public class ServiceLocator {
    
    /** �A�v���P�[�V�����R���e�L�X�g */
    private static ApplicationContext context = null;
    
    /**
     * ���������s���܂��B
     * 
     * @throws SystemException
     */
    public static void init() {
        context = new ClassPathXmlApplicationContext("/onstudyApplicationContext.xml");

        final List<String> initializableServiceNameList = 
            Arrays.asList(context.getBeanNamesForType(InitializableService.class));
        for (String initServiceName : initializableServiceNameList) {
            InitializableService initializable = (InitializableService) context.getBean(initServiceName);
            initializable.init();
            
            ProgressObserver.addProgress((int) ((100 / initializableServiceNameList.size()) * 0.33));
        }
    }
    
    /**
     * ���T�[�r�X���擾���܂��B<BR>
     * 
     * @return ���T�[�r�X
     */
    public static QuestionService getQuestionService() {
        return (QuestionService) context.getBean("questionServiceImpl");
    }
    
    /**
     * �l�b�g���[�N�T�[�r�X���擾���܂��B<BR>
     * 
     * @return �l�b�g���[�N�T�[�r�X
     */
    public static NetworkService getNetworkService() {
        return (NetworkService) context.getBean("networkServiceImpl");
    }
    
    /**
     * �l�b�g���[�N���T�[�r�X���擾���܂��B<BR>
     * 
     * @return �l�b�g���[�N���T�[�r�X
     */
    public static NetworkQuestionService getNetworkQuestionService() {
    	return getNetworkService().getNetworkQuestionService();
    }
    
    /**
     * ����Bean���擾���܂��B�ʏ�͗p���܂���B
     * 
     * @param contextName �R���e�L�X�g��
     * @return ����Bean
     */
    public static Object getRawBean(String contextName) {
        return context.getBean(contextName);
    }
    
}

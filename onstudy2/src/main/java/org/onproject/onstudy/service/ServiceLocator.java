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
 * 各種サービスを取り扱うロケータ
 * 
 * @author 恩田 好庸
 */
public class ServiceLocator {
    
    /** アプリケーションコンテキスト */
    private static ApplicationContext context = null;
    
    /**
     * 初期化を行います。
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
     * 問題サービスを取得します。<BR>
     * 
     * @return 問題サービス
     */
    public static QuestionService getQuestionService() {
        return (QuestionService) context.getBean("questionServiceImpl");
    }
    
    /**
     * ネットワークサービスを取得します。<BR>
     * 
     * @return ネットワークサービス
     */
    public static NetworkService getNetworkService() {
        return (NetworkService) context.getBean("networkServiceImpl");
    }
    
    /**
     * ネットワーク問題サービスを取得します。<BR>
     * 
     * @return ネットワーク問題サービス
     */
    public static NetworkQuestionService getNetworkQuestionService() {
    	return getNetworkService().getNetworkQuestionService();
    }
    
    /**
     * 生のBeanを取得します。通常は用いません。
     * 
     * @param contextName コンテキスト名
     * @return 生のBean
     */
    public static Object getRawBean(String contextName) {
        return context.getBean(contextName);
    }
    
}

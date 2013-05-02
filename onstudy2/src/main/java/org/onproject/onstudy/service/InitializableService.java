/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/service/InitializableService.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/05/17 04:28:19 $
******************************************************************************/
package org.onproject.onstudy.service;

/**
 * 初期化可能サービスインターフェイス
 * 
 * @author 恩田 好庸
 */
public interface InitializableService {

    /**
     * 初期化を行います。
     *
     */
    public void init();
    
}

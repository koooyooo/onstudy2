/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/MemberListener.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.Member;

/**
 * メンバーリスナ
 * 
 * @author 恩田 好庸
 */
public interface MemberListener {

    /**
     * メンバーデータを知覚します。
     * 
     * @param data メンバーデータ
     */
    public void listen(Member data);
    
}

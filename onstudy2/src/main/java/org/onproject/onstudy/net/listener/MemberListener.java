/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/MemberListener.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.Member;

/**
 * �����o�[���X�i
 * 
 * @author ���c �D�f
 */
public interface MemberListener {

    /**
     * �����o�[�f�[�^��m�o���܂��B
     * 
     * @param data �����o�[�f�[�^
     */
    public void listen(Member data);
    
}

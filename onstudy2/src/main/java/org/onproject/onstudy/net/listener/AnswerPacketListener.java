/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/AnswerPacketListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.AnswerPacket;

/**
 * �񓚃f�[�^�̃��X�i
 * 
 * @author ���c �D�f
 */
public interface AnswerPacketListener {
    
    /**
     * �񓚃f�[�^��m�o���܂��B
     * 
     * @param answerPacket �񓚃f�[�^
     */
    public void listen(AnswerPacket answerPacket);
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/QuestionPacketListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/05/17 04:28:21 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.QuestionPacket;

/**
 * ���f�[�^�̃��X�i
 * 
 * @author ���c �D�f
 */
public interface QuestionPacketListener {
    
    /**
     * ���f�[�^��m�o���܂��B
     * 
     * @param questionPacket ���f�[�^
     */
    public void listen(QuestionPacket questionPacket);
    
}

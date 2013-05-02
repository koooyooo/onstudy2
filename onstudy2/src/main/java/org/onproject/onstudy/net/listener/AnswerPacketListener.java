/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/AnswerPacketListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.AnswerPacket;

/**
 * 回答データのリスナ
 * 
 * @author 恩田 好庸
 */
public interface AnswerPacketListener {
    
    /**
     * 回答データを知覚します。
     * 
     * @param answerPacket 回答データ
     */
    public void listen(AnswerPacket answerPacket);
}

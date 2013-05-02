/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/QuestionPacketListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/05/17 04:28:21 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.QuestionPacket;

/**
 * 問題データのリスナ
 * 
 * @author 恩田 好庸
 */
public interface QuestionPacketListener {
    
    /**
     * 問題データを知覚します。
     * 
     * @param questionPacket 問題データ
     */
    public void listen(QuestionPacket questionPacket);
    
}

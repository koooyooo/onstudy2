/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/MemberPacketListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/05 07:25:09 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.MemberPacket;

/**
 * メンバーリスナ
 * 
 * @author 恩田 好庸
 */
public interface MemberPacketListener {

    /**
     * メンバーパケットデータを知覚します。
     * 
     * @param data メンバーパケットデータ
     */
    public void listen(MemberPacket data);
    
}

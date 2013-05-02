/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/MemberAdapterByteListener.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.data.MemberPacket;
import org.onproject.onstudy.net.listener.ByteDataListener;
import org.onproject.onstudy.net.listener.MemberPacketListener;

/**
 * 存在シグナルのリスナ
 * 
 * @author 恩田 好庸
 */
public class MemberAdapterByteListener implements ByteDataListener {
    
    /** メンバーリスナ */
    private MemberPacketListener memberPacketListener;
    
    /** 
     * メンバーリスナを設定します 
     * 
     * @param memberPacketListener メンバーリスナ
     */
    public void setMemberPacketListener(MemberPacketListener memberPacketListener) {
        this.memberPacketListener = memberPacketListener;
    }
    
    /**
     * バイナリデータを受信します。
     * 
     * @param data 受信データ
     */
    public void listen(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            if (!(object instanceof MemberPacket)) {
                return;
            }
            this.memberPacketListener.listen((MemberPacket) object);
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }
    
}

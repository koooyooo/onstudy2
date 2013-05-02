/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveSignalingThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.MulticastReceiver;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.ByteDataListener;
import org.onproject.onstudy.net.listener.MemberListener;

/**
 * 存在シグナル受信スレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveSignalingThread extends Thread {

    /** マルチキャスト受信 */
    private MulticastReceiver multicastReceiver;
    
    /**
     * メンバーリスナを追加します。
     * 
     * @param listener メンバーリスナ
     */
    public void addMemberListener(MemberListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberListener(listener);
        this.multicastReceiver.addByteDataListener(memberAdapterByteListener);
    }
    
    /**
     * 通知をします。
     * 
     */
    public void run() {
        multicastReceiver.startReceiving();
    }
    
    /**
     * 停止要求を出します。
     *
     */
    public void stopRunning() {
        multicastReceiver.stopReceiving();
    }
    
    /**
     * 存在シグナルのリスナ
     * 
     * @author 恩田 好庸
     */
    private static class MemberAdapterByteListener implements ByteDataListener {
        
        /** メンバーリスナ */
        private MemberListener memberListener;
        
        /** 
         * メンバーリスナを設定します 
         * 
         * @param memberListener メンバーリスナ
         */
        public void setMemberListener(MemberListener memberListener) {
            this.memberListener = memberListener;
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
                if (!(object instanceof Member)) {
                    return;
                }
                this.memberListener.listen((Member) object);
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            }
        }
        
    }
    
    /**
     * マルチキャスト受信オブジェクトを設定します。
     * 
     * @param receiver マルチキャスト受信オブジェクト
     */
    public void setMulticastReceiver(MulticastReceiver receiver) {
        this.multicastReceiver = receiver;
    }
    
}

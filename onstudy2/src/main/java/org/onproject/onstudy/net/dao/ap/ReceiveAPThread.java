/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ap/ReceiveAPThread.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.ap;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPReceiver;
import org.onproject.onstudy.net.data.AnswerPacket;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.ByteDataListener;

/**
 * 解答パケットを受信するスレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveAPThread extends Thread {
    
    /** TCP受信オブジェクト */
    private TCPReceiver tcpReceiver;
    
    /**
     * 処理を実行します。
     */
    public void run() {
        try {
            tcpReceiver.startReceiving();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 受信処理を中止します。
     *
     */
    public void stopRunning() {
        this.tcpReceiver.stopReceiving();
    }
    
    /**
     * 解答パケットリスナコレクションを追加します。
     * 
     * @param apListener 追加する解答パケットリスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener apListener) {
        
        AnswerPacketAdapterByteListener apAdapterByteListener = new AnswerPacketAdapterByteListener();
        apAdapterByteListener.setAnswerPacketListener(apListener);
        
        this.tcpReceiver.addByteDataListener(apAdapterByteListener);
    }
    
    /**
     * TCP受信オブジェクトを設定します。
     * 
     * @param tcpReceiver TCP受信オブジェクト
     */
    public void setTCPReceiver(TCPReceiver tcpReceiver) {
        this.tcpReceiver = tcpReceiver;
    }
    
    /**
     * 解答パケットのリスナアダプタ
     * 
     * @author 恩田 好庸
     */
    public static class AnswerPacketAdapterByteListener implements ByteDataListener {
        
        /** 解答パケットリスナ */
        private AnswerPacketListener apListener;
        
        /**
         * 受信します。
         * 
         * @param data 受信データ
         */
        public void listen(byte[] data) {
            ObjectInputStream ois = null;
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                BufferedInputStream bis = new BufferedInputStream(bais);
                ois = new ObjectInputStream(bis);
                
                Object obj = ois.readObject();
                if (!(obj instanceof AnswerPacket)) {
                    return;
                }
                AnswerPacket packet = (AnswerPacket) obj;
                this.apListener.listen(packet);
                
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(ois);
            }
        }
        
        /**
         * 解答パケットリスナを追加します。
         * 
         * @param apListener 解答パケットリスナ
         */
        public void setAnswerPacketListener(AnswerPacketListener apListener) {
            this.apListener = apListener;
        }
    }
        
}

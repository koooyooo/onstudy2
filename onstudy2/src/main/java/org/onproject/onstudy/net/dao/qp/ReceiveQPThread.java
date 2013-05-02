/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/qp/ReceiveQPThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.qp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPReceiver;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.ByteDataListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;

/**
 * 問題パケットを受信するスレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveQPThread extends Thread {
    
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
     * 問題パケットリスナコレクションを追加します。
     * 
     * @param qpListener 追加する問題パケットリスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener qpListener) {
        
        QuestionPacketAdapterByteListener qpAdapterByteListener = new QuestionPacketAdapterByteListener();
        qpAdapterByteListener.setQuestionpacketListener(qpListener);
        
        this.tcpReceiver.addByteDataListener(qpAdapterByteListener);
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
     * 問題パケットのリスナアダプタ
     * 
     * @author 恩田 好庸
     */
    public static class QuestionPacketAdapterByteListener implements ByteDataListener {
        
        /** 問題パケットリスナ */
        private QuestionPacketListener qpListener;
        
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
                if (!(obj instanceof QuestionPacket)) {
                    return;
                }
                QuestionPacket packet = (QuestionPacket) obj;
                this.qpListener.listen(packet);
                
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(ois);
            }
        }
        
        /**
         * 問題パケットリスナを追加します。
         * 
         * @param qpListener 問題パケットリスナ
         */
        public void setQuestionpacketListener(QuestionPacketListener qpListener) {
            this.qpListener = qpListener;
        }
    }
        
}

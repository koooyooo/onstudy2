/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ReceiveSignalingThread.java,v $
  Version : $Revision: 1.6 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Set;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.ByteDataListener;
import org.onproject.onstudy.net.MulticastReceiver;
import org.onproject.onstudy.net.data.Member;

/**
 * 存在シグナル受信スレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveSignalingThread extends Thread {

    /** マルチキャスト受信 */
    private final MulticastReceiver multicastReceiver = new MulticastReceiver();
    
    /**
     * 初期化処理です。
     * 
     * @param memberSet
     */
    public void init(Set<Member> memberSet) {
        this.multicastReceiver.addByteDataListener(new SignalingDataListener(memberSet));
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
    public void wannaStop() {
        multicastReceiver.stopReceiving();
    }
    
    /**
     * 存在シグナルのリスナ
     * 
     * @author 恩田 好庸
     */
    private static class SignalingDataListener implements ByteDataListener {
        
        /** メンバリスト */
        private final Set<Member> memberSet;
        
        /**
         * コンストラクタ
         * 
         * @param memberSet メンバセット
         */
        public SignalingDataListener(Set<Member> memberSet) {
            this.memberSet = memberSet;
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
                this.memberSet.add((Member) object);
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            }
        }
        
    }
    
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveMulticastSignalingThread.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import org.onproject.onstudy.net.core.MulticastReceiver;
import org.onproject.onstudy.net.listener.MemberPacketListener;

/**
 * マルチキャスト存在シグナル受信スレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveMulticastSignalingThread extends Thread {
    
    /** マルチキャスト受信 */
    private MulticastReceiver multicastReceiver;
    
    /**
     * メンバーリスナを追加します。
     * 
     * @param listener メンバーリスナ
     */
    public void addMemberListener(MemberPacketListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberPacketListener(listener);
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
     * マルチキャスト受信オブジェクトを設定します。
     * 
     * @param receiver マルチキャスト受信オブジェクト
     */
    public void setMulticastReceiver(MulticastReceiver receiver) {
        this.multicastReceiver = receiver;
    }
    
}

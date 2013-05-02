/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveUnicastSignalingThread.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import org.onproject.onstudy.net.core.UnicastReceiver;
import org.onproject.onstudy.net.listener.MemberPacketListener;

/**
 * ユニキャスト存在シグナル受信スレッド
 * 
 * @author 恩田 好庸
 */
public class ReceiveUnicastSignalingThread extends Thread {
    
    /** ユニキャスト受信 */
    private UnicastReceiver unicastReceiver;
    
    /**
     * メンバーリスナを追加します。
     * 
     * @param listener メンバーリスナ
     */
    public void addMemberListener(MemberPacketListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberPacketListener(listener);
        this.unicastReceiver.addByteDataListener(memberAdapterByteListener);
    }
    
    /**
     * 通知をします。
     * 
     */
    public void run() {
        unicastReceiver.startReceiving();
    }
    
    /**
     * 停止要求を出します。
     *
     */
    public void stopRunning() {
        unicastReceiver.stopReceiving();
    }
    
    /**
     * マルチキャスト受信オブジェクトを設定します。
     * 
     * @param receiver マルチキャスト受信オブジェクト
     */
    public void setUnicastReceiver(UnicastReceiver receiver) {
        this.unicastReceiver = receiver;
    }
    
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/SignalingManager.java,v $
  Version : $Revision: 1.6 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.MulticastSender;
import org.onproject.onstudy.net.core.UnicastSender;
import org.onproject.onstudy.net.data.MemberPacket;
import org.onproject.onstudy.net.listener.MemberPacketListener;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * シグナル部分を担当するマネージャ
 * 
 * @author 恩田 好庸
 */
public class SignalingManager {
    
    /** ユニキャスト送信 */
    private UnicastSender unicastSender;
    
    /** マルチキャスト送信 */
    private  MulticastSender multicastSender;
    
    /** ユーザ名 */
    private UserManager userManager;
    
    /** ユーザポート */
    private int userPort;
    
    /** ユニキャストシグナル受信スレッド */
    private ReceiveUnicastSignalingThread receiveUnicastSignalingThread;
    
    /** マルチキャストシグナル受信スレッド */
    private ReceiveMulticastSignalingThread receiveMulticastSignalingThread;
    
    /**
     * ユニキャスト通信をします。
     * 
     * @param ip IPアドレス（255.255.255.255形式）
     * @param port ポート
     */
    public void sendUnicastSignal(String ip, MemberPacket memberPacket) {
        try {
            unicastSender.send(ip, this.createMyMemberPacketByteArray(memberPacket));
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }
    
    /**
     * マルチキャスト通知をします。
     * 
     */
    public void sendMulticastSignal(MemberPacket memberPacket) {
        try {
            multicastSender.send(this.createMyMemberPacketByteArray(memberPacket));
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }
    
    /**
     * 自身のメンバーのバイト配列を作成します。
     * 
     * @return 自身のメンバーのバイト配列
     * @throws Exception
     */
    private byte[] createMyMemberPacketByteArray(MemberPacket memberPacket) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(memberPacket);
        oos.flush();
        
        return bos.toByteArray();
    }
    
    /**
     * シグナル受信を開始します。
     *
     */
    public void startReceiving() {
        this.receiveUnicastSignalingThread.start();
        this.receiveMulticastSignalingThread.start();
    }
    
    /**
     * シグナル受信を停止します。
     *
     */
    public void stopReceiving() {
        this.receiveUnicastSignalingThread.stopRunning();
        this.receiveMulticastSignalingThread.stopRunning();
    }
    
    /**
     * メンバリスナを追加します。
     * 
     * @param listener メンバリスナ
     */
    public void addMemberListener(MemberPacketListener listener) {
        this.receiveUnicastSignalingThread.addMemberListener(listener);
        this.receiveMulticastSignalingThread.addMemberListener(listener);
    }
    
    /**
     * ユニキャストシグナル受信スレッドを設定します。
     * 
     * @param receiveUnicastSignalingThread ユニキャスト受信スレッド
     */
    public void setReceiveUnicastSignalingThread(ReceiveUnicastSignalingThread receiveUnicastSignalingThread) {
        this.receiveUnicastSignalingThread = receiveUnicastSignalingThread;
    }
    
    /**
     * マルチキャストシグナル受信スレッドを設定します。
     * 
     * @param receiveMulticastSignalingThread マルチキャストシグナル受信スレッド
     */
    public void setReceiveMulticastSignalingThread(ReceiveMulticastSignalingThread receiveMulticastSignalingThread) {
        this.receiveMulticastSignalingThread = receiveMulticastSignalingThread;
    }
    
    /**
     * ユーザ名を取得します。
     * 
     * @return ユーザ名
     */
    public String getUserName() {
        return this.userManager.getUserName();
    }
    
    /**
     * ユーザマネージャを取得します。
     * 
     * @return ユーザマネージャ
     */
    public UserManager getUserManager() {
        return userManager;
    }
    
    /**
     * ユーザマネージャを設定します。
     * 
     * @param userManager ユーザマネージャ
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    /**
     * ポートを取得します。
     * 
     * @return ポート
     */
    public int getUserPort() {
        return this.userPort;
    }
    
    /**
     * ユーザの利用するポートを設定します。
     * 
     * @param userPort ユーザの利用するポート
     */
    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }
    
    /**
     * ユニキャスト送信オブジェクトを設定します。
     * 
     * @param unicastSender ユニキャスト送信オブジェクト
     */
    public void setUnicastSender(UnicastSender unicastSender) {
        this.unicastSender = unicastSender;
    }
    
    /**
     * マルチキャスト送信オブジェクトを設定します。
     * 
     * @param multicastSender マルチキャスト送信オブジェクト
     */
    public void setMulticastSender(MulticastSender multicastSender) {
        this.multicastSender = multicastSender;
    }
    
}

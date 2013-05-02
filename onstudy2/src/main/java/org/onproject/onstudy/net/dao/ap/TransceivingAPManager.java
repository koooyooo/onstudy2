/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ap/TransceivingAPManager.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.ap;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPSender;
import org.onproject.onstudy.net.data.AnswerPacket;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * 解答送受信マネージャ
 * 
 * @author 恩田 好庸
 */
public class TransceivingAPManager {
    
    /** TCP送信オブジェクト */
    private TCPSender tcpSender;
    
    /** 解答パケット受信スレッド */
    private ReceiveAPThread receiveAPThread;
    
    /** ユーザマネージャ */
    private UserManager userManager;
    
    /**
     * 解答を送信します。
     * 
     * @param destinationMember 送信先
     * @param answerSet 解答のセット
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet) {

        try {
            
            Member mine = new Member();
            mine.setName(userManager.getUserName());
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(0);
                        
            AnswerPacket packet = new AnswerPacket();
            packet.setPanelst(mine);
            packet.addAnswers(answerSet);
            
            this.tcpSender.send(destinationMember.getIp(), destinationMember.getPort(), this.convertAnswerPacketToByte(packet));

        } catch (IOException ioe) {
            throw new SystemException(ioe.getMessage(), ioe);
        }
    }
    
    /**
     * 解答パケットをバイナリ化します。
     * 
     * @param packet 解答パケット
     * @return
     * @throws IOException
     */
    private byte[] convertAnswerPacketToByte(AnswerPacket packet) throws IOException {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(packet);
            oos.flush();
            
            return baos.toByteArray();
        } finally {
            IOUtils.closeQuietly(oos);
        }
    }
    
    /**
     * 受信を開始します。
     *
     */
    public void startReceiving() {
        this.receiveAPThread.start();
    }
    
    /**
     * 受信を終了します。
     *
     */
    public void stopReceiving() {
        this.receiveAPThread.stopRunning();
    }
    
    /**
     * 回答パケットリスナを追加します。
     * 
     * @param listener 回答パケットリスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.receiveAPThread.addAnswerPacketListener(listener);
    }
    
    /**
     * TCP送信オブジェクトを追加します。
     * 
     * @param sender TCP送信オブジェクト
     */
    public void setTCPSender(TCPSender sender) {
        this.tcpSender = sender;
    }
    
    /**
     * 問題パケット受信スレッドを設定します。
     * 
     * @param receiveAPThread 問題パケット受信スレッド
     */
    public void setReceiveAPThread(ReceiveAPThread receiveAPThread) {
        this.receiveAPThread = receiveAPThread;
    }
    
    /**
     * ユーザマネージャを取得します。<BR>
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

    
}

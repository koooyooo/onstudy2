/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/qp/TransceivingQPManager.java,v $
  Version : $Revision: 1.7 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.qp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPSender;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * 問題送受信マネージャ
 * 
 * @author 恩田 好庸
 */
public class TransceivingQPManager {
    
    /** TCP送信オブジェクト */
    private TCPSender tcpSender;
    
    /** 問題パケット受信スレッド */
    private ReceiveQPThread receiveQPThread;
    
    /** ユーザマネージャ */
    private UserManager userManager;
    
    /** 回答受信用ポート */
    private int replyPort;
    
    /**
     * 問題を送信します。
     * 
     * @param targetMemberList 宛先メンバリスト
     * @param questionList 問題のリスト
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList) {

        try {
            
            Member mine = new Member();
            mine.setName(userManager.getUserName());
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(this.replyPort);
                        
            QuestionPacket packet = new QuestionPacket();
            packet.setTransmitter(mine);
            packet.addAllQuestions(questionList);

            for (Member menber : destinationMemberList) {
                this.tcpSender.send(menber.getIp(), menber.getPort(), this.convertQuestionPacketToByte(packet));
            }

        } catch (IOException ioe) {
            throw new SystemException(ioe.getMessage(), ioe);
        }
    }
    
    /**
     * 問題パケットをバイナリ化します。
     * 
     * @param packet 問題パケット
     * @return
     * @throws IOException
     */
    private byte[] convertQuestionPacketToByte(QuestionPacket packet) throws IOException {
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
        this.receiveQPThread.start();
    }
    
    /**
     * 受信を終了します。
     *
     */
    public void stopReceiving() {
        this.receiveQPThread.stopRunning();
    }
    
    /**
     * 問題パケットリスナを追加します。
     * 
     * @param listener 問題パケットリスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.receiveQPThread.addQuestionPacketListener(listener);
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
     * @param reveiceQPThread 問題パケット受信スレッド
     */
    public void setReceiveQPThread(ReceiveQPThread reveiceQPThread) {
        this.receiveQPThread = reveiceQPThread;
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
    
    /**
     * 回答受信用ポートを取得します。
     * 
     * @param replyPort 回答受信用ポート
     */
    public void setReplyPort(int replyPort) {
        this.replyPort = replyPort;
    }

    
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/NetworkDAOImpl.java,v $
  Version : $Revision: 1.22 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.dao.ap.TransceivingAPManager;
import org.onproject.onstudy.net.dao.qp.TransceivingQPManager;
import org.onproject.onstudy.net.dao.signaling.SignalingManager;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.MemberPacket;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.MemberPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * ネットワークDAOの実装
 * 
 * @author 恩田 好庸
 */
public class NetworkDAOImpl implements NetworkDAO {

    /** 認識しているメンバのリスト */
    private final Set<Member> memberSet = Collections.synchronizedSet(new HashSet<Member>());
    
    /** シグナルマネージャ */
    private SignalingManager signalingManager;
    
    /** 問題パケットマネージャ */
    private TransceivingQPManager transceivingQPManager;
    
    /** 解答マネージャ */
    private TransceivingAPManager transceivingAPManager;
    
    /**
     * 初期化を行います。
     *
     */
    public void init() {
        this.signalingManager.addMemberListener(
            new MemberPacketListener() {
                
                /**
                 * メンバの追加を検知します。
                 * 
                 */
                public void listen(MemberPacket memberPacket) {
                    memberSet.add(memberPacket.getCaller());
                    if (memberPacket.getResponsdent() != null) {
                        memberSet.add(memberPacket.getResponsdent());
                    }
                    if (! this.isMine(memberPacket.getCaller()) && memberPacket.getResponsdent() == null) {
                        try {
                            memberPacket.setResponsdent(createMyMember());
                            signalingManager.sendUnicastSignal(memberPacket.getCaller().getIp(), memberPacket);
                        } catch (Exception e) {
                            throw new SystemException(e.getMessage(), e);
                        }
                    }
                }
                

               
                /**
                 * メンバーが自身であるかをチェックします。
                 * 
                 * @param member チェック対象のメンバ
                 * @return 自分であれば true
                 */
                private boolean isMine(Member member) {
                    try {                        
                        return InetAddress.getLocalHost().getHostAddress().equals(member.getIp())
                                    && signalingManager.getUserPort() == member.getPort();
                    } catch (UnknownHostException uhe) {
                        throw new SystemException(uhe.getMessage(), uhe);
                    }
                }
            });
    }
    
    /**
     * 存在シグナルの受信を開始します。
     * 
     */
    public void startReceiveSignaling() {
        this.signalingManager.startReceiving();
    }
    
    /**
     * 存在シグナルの受信を停止します。
     */
    public void stopReceivingSignaling() {
        this.signalingManager.stopReceiving();
    }
    
    /**
     * 問題パケットの受信を開始します。
     * 
     */
    public void startReceivingQuestionPacket() {
        this.transceivingQPManager.startReceiving();
    }

    /**
     * 問題パケットの受信を停止します。
     * 
     */
    public void stopReceivingQuestionPacket() {
        this.transceivingQPManager.stopReceiving();
    }
    
    /**
     * 解答パケットの受信を開始します。
     *
     */
    public void startReceivingAnswerPacket() {
        this.transceivingAPManager.startReceiving();
    }
    
    /**
     * 解答パケットの受信を停止します。
     *
     */
    public void stopReceivingAnswerPacket() {
        this.transceivingAPManager.stopReceiving();
    }
    
    /**
     * 現在起動中のメンバセットを取得します。
     * 
     * @return 現在起動中のメンバセット
     */
    public synchronized Set<Member> getAwakeMemberSet() {
        
        this.memberSet.clear();
        
        try {
            MemberPacket packet = new MemberPacket();
            packet.setCaller(this.createMyMember());
            
            this.signalingManager.sendMulticastSignal(packet);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
        
        return new HashSet<Member>(this.memberSet);
    }
    
    /**
     * 自身のメンバーを作成します。
     * 
     * @return 自身のメンバー
     * @throws UnknownHostException
     */
    private Member createMyMember() throws UnknownHostException {
        Member mine = new Member();
        mine.setName(signalingManager.getUserName());
        mine.setIp(InetAddress.getLocalHost().getHostAddress());
        mine.setPort(signalingManager.getUserPort());
        return mine;
    }
    
    /**
     * 問題リスナを追加します。
     * 
     * @param listener 問題リスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.transceivingQPManager.addQuestionPacketListener(listener);
    }

    /**
     * 問題を送信します。
     * 
     * @param targetMemberList 宛先メンバリスト
     * @param questionList 問題のリスト
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList) {
        this.transceivingQPManager.sendQuestion(destinationMemberList, questionList);
    }
    
    /**
     * 解答リスナを追加します。
     * 
     * @param listener 解答リスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.transceivingAPManager.addAnswerPacketListener(listener);
    }
    
    /**
     * 解答を送信します。
     * 
     * @param destinationMember 宛先メンバ
     * @param answerSet 解答のセット
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet) {
        this.transceivingAPManager.sendAnswer(destinationMember, answerSet);
    }
    
    /**
     * シグナルマネージャを設定します。
     * 
     * @param signalingManager シグナルマネージャ
     */
    public void setSignalingManager(SignalingManager signalingManager) {
        this.signalingManager = signalingManager;
    }
    
    /**
     * 問題パケットマネージャを設定します。
     * 
     * @param transceivingQPManager 問題パケットマネージャ
     */
    public void setTransceivingQPManager(TransceivingQPManager transceivingQPManager) {
        this.transceivingQPManager = transceivingQPManager;
    }
    
    /**
     * 解答パケットマネージャを設定します。
     * 
     * @param transceivingAPManager 解答パケットマネージャ
     */
    public void setTransceivingAPManager(TransceivingAPManager transceivingAPManager) {
        this.transceivingAPManager = transceivingAPManager;
    }



}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/NetworkDAO.java,v $
  Version : $Revision: 1.10 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * ネットワークDAO
 * 
 * @author 恩田 好庸
 */
public interface NetworkDAO {
    
    /**
     * 初期化を行います。
     *
     */
    public void init();
    
    /**
     * 存在シグナルの受信を開始します。
     *
     */
    public void startReceiveSignaling();
    
    /**
     * 存在シグナルの受信を停止します。
     * 
     */
    public void stopReceivingSignaling();
    
    /**
     * 問題パケットの受信を開始します。
     *
     */
    public void startReceivingQuestionPacket();
    
    /**
     * 問題パケットの受信を終了します。
     *
     */
    public void stopReceivingQuestionPacket();
    
    /**
     * 解答パケットの受信を開始します。
     *
     */
    public void startReceivingAnswerPacket();
    
    /**
     * 解答パケットの受信を停止します。
     *
     */
    public void stopReceivingAnswerPacket();
    
    /**
     * 現在起動中のメンバセットを取得します。
     * 
     * @return 現在起動中のメンバセット
     */
    public Set<Member> getAwakeMemberSet();
    
    /**
     * 問題リスナを追加します。
     * 新規に問題が通知された場合は、各リスナに通知します。
     * 
     * @param listener 問題リスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener listener);
    
    /**
     * 問題を送信します。
     * 
     * @param targetMemberList 宛先メンバリスト
     * @param questionList 問題のリスト
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList);
    
    /**
     * 解答リスナを追加します。
     * 
     * @param listener 解答リスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener listener);
    
    /**
     * 解答を送信します。
     * 
     * @param destinationMember 宛先メンバ
     * @param answerSet 解答のセット
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet);
    
}

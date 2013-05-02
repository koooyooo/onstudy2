/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/service/NetworkService.java,v $
  Version : $Revision: 1.9 $
  Date    : $Date: 2007/06/20 05:22:42 $
******************************************************************************/
package org.onproject.onstudy.net.service;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.service.InitializableService;

/**
 * ネットワーク関係のサービス
 * 
 * @author 恩田 好庸
 */
public interface NetworkService extends InitializableService {
    
    /**
     * 現在通信可能なメンバの一覧を取得します。
     * 
     * @return メンバの一覧
     */
    public Set<Member> getCurrentMemberSet();
    
    /**
     * 問題を送信します。
     * 
     * @param memberList メンバリスト
     * @param questionList 問題のリスト
     */
    public void sendQuestion(List<Member> memberList, List<Question> questionList);
    
    /**
     * 問題のリスナを追加します。
     * 
     * @param listener 問題のリスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener listener);
    
    /**
     * 解答を送信します。
     * 
     * @param member 送信先メンバ
     * @param answerSet 解答セット
     */
    public void sendAnswer(Member member, Set<Answer> answerSet);
    
    /**
     * 解答リスナを追加します。
     * 
     * @param listener 解答リスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener listener);
    
    /**
     * ネットワーク問題サービスを取得します。
     * 
     * @return ネットワーク問題サービス
     */
    public NetworkQuestionService getNetworkQuestionService();
    
}

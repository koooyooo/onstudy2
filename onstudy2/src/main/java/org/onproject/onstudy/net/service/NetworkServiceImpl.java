/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/service/NetworkServiceImpl.java,v $
  Version : $Revision: 1.17 $
  Date    : $Date: 2007/06/21 06:17:08 $
******************************************************************************/
package org.onproject.onstudy.net.service;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.dao.NetworkDAO;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * ネットワークサービスの実装
 * 
 * @author 恩田 好庸
 */
public class NetworkServiceImpl implements NetworkService {
    
    /** ネットワークDAO */
    private NetworkDAO networkDAO;
    
    /** ネットワーク問題サービスの実装 */
    private NetworkQuestionServiceImpl networkQuestionService = new NetworkQuestionServiceImpl();
    
    /**
     * 初期化を行います。
     *
     */
    public void init() {
        this.networkDAO.init();
        this.networkDAO.startReceiveSignaling();
        this.networkDAO.startReceivingQuestionPacket();
        this.networkDAO.startReceivingAnswerPacket();
        this.addQuestionPacketListener(this.networkQuestionService);
    }
    
    /**
     * 現在通信可能なメンバの一覧を取得します。
     * 
     * @return メンバの一覧
     */
    public Set<Member> getCurrentMemberSet() {
        return this.networkDAO.getAwakeMemberSet();
    }

    /**
     * 問題を送信します。
     * 
     * @param memberList メンバリスト
     * @param questionList 問題のリスト
     */
    public void sendQuestion(List<Member> memberList, List<Question> questionList) {
        this.networkDAO.sendQuestion(memberList, questionList);
    }
    
    /**
     * 問題のリスナを追加します。
     * 
     * @param listener 問題のリスナ
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.networkDAO.addQuestionPacketListener(listener);
    }
    
    /**
     * 解答を送信します。
     * 
     * @param member 送信先メンバ
     * @param answerSet 解答セット
     */
    public void sendAnswer(Member member, Set<Answer> answerSet) {
        this.networkDAO.sendAnswer(member, answerSet);
    }
    
    /**
     * 解答リスナを追加します。
     * 
     * @param listener 解答リスナ
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.networkDAO.addAnswerPacketListener(listener);
    }
    
    /**
     * ネットワークDAOを設定します。
     * 
     * @param networkDAO ネットワークDAO
     */
    public void setNetworkDAO(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }
    
    /**
     * ネットワーク問題サービスを取得します。
     * 
     * @return ネットワーク問題サービス
     */
    public NetworkQuestionService getNetworkQuestionService() {
    	return this.networkQuestionService;
    }

}

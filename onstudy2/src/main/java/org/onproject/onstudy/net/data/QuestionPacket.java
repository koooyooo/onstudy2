/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/QuestionPacket.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/05/17 04:28:19 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.onproject.onstudy.question.data.Question;

/**
 * 送信問題情報
 * 
 * @author 恩田 好庸
 */
public class QuestionPacket implements Serializable {
    
    /** シリアルバージョンID */
    private static final long serialVersionUID = 8536642449313231441L;

    /** 送信者 */
    private Member transmitter;
    
    /** 問題リスト */
    private List<Question> questionList = new ArrayList<Question>();

    /**
     * 送信者を取得します。
     * 
     * @return 送信者
     */
    public Member getTransmitter() {
        return this.transmitter;
    }

    /**
     * 送信者を設定します。
     * 
     * @param transmitter 送信者
     */
    public void setTransmitter(Member transmitter) {
        this.transmitter = transmitter;
    }
    
    /**
     * 問題を追加します。
     * 
     * @param question 問題
     */
    public void addQuestion(Question question) {
        this.questionList.add(question);
    }
    
    /**
     * 全ての問題を追加します。
     * 
     * @param questionList 問題リスト
     */
    public void addAllQuestions(List<Question> questionList) {
        this.questionList.addAll(questionList);
    }
    
    /**
     * 問題のリストを取得します。
     * 
     * @return 問題の反復可能子
     */
    public List<Question> getQuestionList() {
        return this.questionList;
    }
    
    /**
     * 当インスタンスの文字列表現です。
     * 
     * @return 当インスタンスの文字列表現
     */
    public String toString() {
        return ClassUtils.getShortClassName(this.getClass()) + this.transmitter.toString() + this.questionList.toString();
    }
    
}

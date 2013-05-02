package org.onproject.onstudy.question.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 解答クラス
 * 
 * @author 恩田 好庸
 */
public class Answer implements Serializable {
    
    /** シリアルバージョンID */
    private static final long serialVersionUID = 9201456716960858989L;
    
    /** 問題ID */
    private String questionId;
    
    /** 解答ID */
    private Set<String> answerIdSet = new HashSet<String>();
    
    /**
     * 問題IDを取得します。
     * 
     * @return 問題ID
     */
    public String getQuestionId() {
        return questionId;
    }
    
    /**
     * 問題IDを設定します。
     * 
     * @param questionId 問題ID
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    
    /**
     * 解答IDセットを取得します。
     * 
     * @return 解答IDセット
     */
    public Set<String> getAnswerIdSet() {
        return new HashSet<String>(this.answerIdSet);
    }
    
    /**
     * 解答IDを追加します。
     * 
     * @param answerId 解答ID
     */
    public void addAnswerId(String answerId) {
        this.answerIdSet.add(answerId);
    }
    
    /**
     * 解答IDを複数追加します。
     * 
     * @param answerIdSet 解答IDのセット
     */
    public void addAnswerIds(Set<String> answerIdSet) {
        this.answerIdSet.addAll(answerIdSet);
    }
    
    /**
     * ハッシュコードを取得します。
     * 
     * @return ハッシュコード
     */
    public int hashCode() {
        return this.questionId.hashCode();
    }
    
    /**
     * 同値であるか判定します。
     * 
     * @param other 判定対象
     * @return 同値であれば true
     */
    public boolean equals(Object other) {
        return other != null
            && (other instanceof Answer)
            && this.questionId.equals(((Answer) other).getQuestionId());
    }
    
    /**
     * 当インスタンスの文字列表現です。
     * 
     * @return 当インスタンスの文字列表現
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}

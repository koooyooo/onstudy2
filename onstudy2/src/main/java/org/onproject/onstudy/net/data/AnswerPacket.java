package org.onproject.onstudy.net.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.onproject.onstudy.question.data.Answer;

/**
 * 解答パケット
 * 
 * @author 恩田 好庸
 */
public class AnswerPacket implements Serializable {
	
	/** シリアルバージョンID */
	private static final long serialVersionUID = 9201456716960858988L;
	
	/** 解答者 */
	private Member panelist;
	
	/** 解答セット */
	private Set<Answer> answerSet = new HashSet<Answer>();
	
	/**
	 * 解答者を取得します。
	 * 
	 * @return 解答者
	 */
	public Member getPanelist() {
		return this.panelist;
	}
	
	/**
	 * 解答者を設定します。
	 * 
	 * @param panelist 解答者
	 */
	public void setPanelst(Member panelist) {
		this.panelist = panelist;
	}
	
    /**
     * 解答を追加します。
     * 
     * @param answer 解答
     */
    public void addAnswer(Answer answer) {
        this.answerSet.add(answer);
    }
    
    /**
     * 解答を追加します。
     * 
     * @param answers 解答
     */
    public void addAnswers(Set<Answer> answers) {
        this.answerSet.addAll(answers);
    }
	
	/**
	 * 問題IDのセットを取得します。
	 * 
	 * @return 問題IDのセット
	 */
	public Set<String> getQuestionIdSet() {
		Set<String> idSet = new HashSet<String>();
		for (Answer answer : this.answerSet) {
			idSet.add(answer.getQuestionId());
		}
		return idSet;
	}
	
	/**
	 * 問題IDに対応した解答IDセットを取得します。
	 * 
	 * @param questionId 問題ID
	 * @return 問題IDに対応した解答IDセット
	 */
	public Set<String> getAnswerIdSet(String questionId) {
		for (Answer answer : this.answerSet) {
			if (!questionId.equals(answer.getQuestionId())) {
				continue;
			}
			return answer.getAnswerIdSet();
		}
		return null;
	}
	
	/**
	 * ハッシュコードを取得します。
	 * 
	 * @return ハッシュコード
	 */
	public int hashCode() {
		int hashCode = this.panelist.hashCode();
		for (Answer answer : this.answerSet) {
			hashCode += answer.hashCode();
		}
		return hashCode;
	}
	
	/**
	 * 同値判定を行います。
	 * 
	 * @param other 判定対象のインスタンス
	 * @return 同値であれば true
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof AnswerPacket)) {
			return false;
		}
		AnswerPacket otherPacket = (AnswerPacket) other;
		if (!this.panelist.equals(otherPacket.getPanelist())) {
			return false;
		}
		if (this.answerSet.size() != otherPacket.getQuestionIdSet().size()) {
			return false;
		}
		for (Answer answer : this.answerSet) {
			if (!otherPacket.getQuestionIdSet().contains(answer.getQuestionId())) {
				return false;
			}
		}
		return true;
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

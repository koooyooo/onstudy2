package org.onproject.onstudy.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 問題クラス
 * 
 * @author 恩田 好庸
 */
public class Question {
	
	/** ID */
	private String id;
	
	/** タイトル */
	private String title;
	
	/** 問題文 */
	private String sentence;
	
	/** 選択肢リスト */
	private List<Option> optionList = new ArrayList<Option>();
	
	/** ヒント */
	private String hint;
	
	/** 解説 */
	private String expositoryWriting;
	
	/**
	 * IDを取得します。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * IDを設定します。
	 * 
	 * @param id ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * タイトルを取得します。
	 * 
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * タイトルを設定します。
	 * 
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 問題文を取得します。
	 * 
	 * @return 問題文
	 */
	public String getSentence() {
		return sentence;
	}
	
	/**
	 * 問題文を設定します。
	 * 
	 * @param sentence 問題文
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	/**
	 * 選択肢を追加します。
	 * 
	 * @param option 選択肢
	 */
	public void addOption(Option option) {
		this.optionList.add(option);
	}
    
    /**
     * 
     * @return
     */
    @Deprecated
    public Collection<Option> getOptions() {
        return new ArrayList<Option>(this.optionList);
    }
    
    /**
     * 選択肢の集合を取得します。
     * 
     * @return 選択肢の集合
     */
    public Iterable<Option> optionIterable() {
        return new ArrayList<Option>(this.optionList);
    }
	
	/**
	 * 選択肢の数を取得します。
	 * 
	 * @return 選択肢の数
	 */
	public int optionSize() {
		return this.optionList.size();
	}
	
	/**
	 * ヒントを取得します。
	 * 
	 * @return ヒント
	 */
	public String getHint() {
		return hint;
	}
	
	/**
	 * ヒントを設定します。
	 * 
	 * @param hint ヒント
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
    
    /**
     * 正答のIDをセットにして取得します。
     * 
     * @return 正答のID
     */
    protected Set<String> getRightAnswerIdSet() {
        Set<String> answerSet = new HashSet<String>();
        for (Option option : this.optionList) {
            if (option.isRightAnswer()) {
                answerSet.add(option.getId());
            }
        }
        return answerSet;
    }
    
	/**
	 * 正解数を取得します。
	 * 
	 * @return 正解数
	 */
	public int rightAnswerSize() {
		return this.getRightAnswerIdSet().size(); // TODO
	}
	
	/**
	 * 正しい解答か否かを判定します。
	 * 
	 * @param answerIdSet 解答IDのセット
	 * @return 正しい解答であれば true
	 */
	public boolean isRightAnswer(Set<String> answerIdSet) {
		if (answerIdSet.size() != this.getRightAnswerIdSet().size()) {
			return false;
		}
		for (String rightAnswer : this.getRightAnswerIdSet()) {
			if (!answerIdSet.contains(rightAnswer)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 解説を取得します。
	 * 
	 * @return 解説
	 */
	public String getExpositoryWriting() {
		return expositoryWriting;
	}
	
	/**
	 * 解説を設定します。
	 * 
	 * @param expositoryWriting 解説
	 */
	public void setExpositoryWriting(String expositoryWriting) {
		this.expositoryWriting = expositoryWriting;
	}
	
	/**
	 * 当インスタンスの文字列表現を取得します。
	 * 
	 * @return 当インスタンスの文字列表現
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Question : ");
		sb.append("id=[" + id + "] ");
		sb.append("title=[" + title + "] ");
		sb.append("sentence=[" + sentence + "] ");
		sb.append("option=[" + this.optionList + "] ");
		sb.append("hint=[" + hint + "] ");
		sb.append("expository-writing=[" + this.expositoryWriting + "] ");
		return sb.toString();
	}
	
}

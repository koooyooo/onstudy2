package org.onproject.onstudy.question.service;

import java.util.Set;

import org.onproject.onstudy.question.data.Question;

/**
 * 問題サービス
 * 
 * @author 恩田 好庸
 */
public interface QuestionService {
	
	/**
	 * IDのSetを取得します。
	 * 
	 * @return IDのセット
	 */
	public Set<String> getIdSet();
	
	/**
	 * IDの指定により、問題を取得します。
	 * 
	 * @param id 指定するID
	 * @return 同じIDを持つQuestion。無ければnull。
	 */
	public Question getQuestionById(String id);
	
	/**
	 * ランダムで問題を取得します。
	 * 
	 * @return ランダムで取得された問題
	 */
	public Question getRandomQuestion();
	
    /**
     * 問題IDが現在も存在するかをチェックします。
     * 
     * @param nextQuestionId 対象となる問題ID
     * @return 現在も存在すれば true
     */
    public boolean isValidId(String nextQuestionId);
    
    /**
     * 問題を登録または更新します。
     * 
     * @question 登録または更新する問題
     */
    public void saveOrUpdateQuestion(Question question);
    
    /**
     * 問題を削除します。
     * 
     * @param id 削除対象の問題ID
     */
    public void removeQuestion(String id);
}

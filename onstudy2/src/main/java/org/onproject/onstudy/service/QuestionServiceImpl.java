package org.onproject.onstudy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.onproject.onstudy.dao.QuestionDAO;
import org.onproject.onstudy.data.Question;

/**
 * 問題サービスの実装
 * 
 * @author 恩田 好庸
 */
public class QuestionServiceImpl implements QuestionService {
	
	/** 問題 DAO */
	private QuestionDAO questionDAO;
	
	/** シャッフルされた問題IDリスト */
	private Iterator<String> shuffledQuestionIdIte;
	
	/**
	 * IDのSetを取得します。
	 * 
	 * @return IDのセット
	 */
	public Set<String> getIdSet() {
		return questionDAO.loadIdSet();
	}
	
	/**
	 * ランダムで問題を取得します。
	 * 
	 * @return ランダムで取得された問題
	 */
	public synchronized Question getRandomQuestion() {
		if (this.shuffledQuestionIdIte == null || !this.shuffledQuestionIdIte.hasNext()) {
			this.shuffledQuestionIdIte = this.createRandomIdIterator();
		}
		String nextQuestionId = this.shuffledQuestionIdIte.next();
		if (!isValidId(nextQuestionId)) {
			this.shuffledQuestionIdIte = this.createRandomIdIterator();
			nextQuestionId = this.shuffledQuestionIdIte.next();
		}
		final Question nextQuestion = this.getQuestionById(nextQuestionId);
		return nextQuestion;
	}
	
	/**
	 * 問題IDが現在も存在するかをチェックします。
	 * 
	 * @param nextQuestionId 対象となる問題ID
	 * @return 現在も存在すれば true
	 */
	public boolean isValidId(String nextQuestionId) {
		return this.getIdSet().contains(nextQuestionId);
	}
	
	/**
	 * ランダムな問題IDの反復子を生成します。
	 * 
	 * @return ランダムな問題IDの反復子
	 */
	protected Iterator<String> createRandomIdIterator() {
		final List<String> idList = new ArrayList<String>(this.getIdSet());
		Collections.shuffle(idList);
		return idList.iterator();
	}
	
	/**
	 * IDの指定により、問題を取得します。
	 * 
	 * @param id 指定するID
	 * @return 同じIDを持つQuestion。無ければnull。
	 */
	public Question getQuestionById(String id) {
		return this.questionDAO.loadQuestionById(id);
	}
	
    /**
     * 問題を登録または更新します。
     * 
     * @question 登録または更新する問題
     */
    public void saveOrUpdateQuestion(Question question) {
        if (this.isValidId(question.getId())) {
            this.questionDAO.updateQuestion(question);
        } else {
            this.questionDAO.registerQuestion(question);
        }
    }
    
    /**
     * 問題を削除します。
     * 
     * @param id 削除対象の問題ID
     */
    public void removeQuestion(String id) {
        this.questionDAO.deleteQuestion(id);
    }
    
	/**
	 * 問題DAOを設定します。
	 * 
	 * @param questionDAO 問題DAO
	 */
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

}

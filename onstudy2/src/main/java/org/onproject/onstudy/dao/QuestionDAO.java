package org.onproject.onstudy.dao;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import org.onproject.onstudy.data.Question;

/**
 * 問題のDAO
 * 
 * @author 恩田 好庸
 */
public interface QuestionDAO {

	/**
	 * IDのSetを取得します。
	 * 
	 * @return IDのセット
	 */
	public Set<String> loadIdSet();

	/**
	 * IDの指定により、問題を取得します。
	 * 
	 * @param id 指定するID
	 * @return 同じIDを持つQuestion。無ければnull。
	 */
	public Question loadQuestionById(String id);

	/**
	 * デフォルトの順序でソートされた問題のSetを取得します。
	 * 
	 * @return デフォルトの順序でソートされた問題のSet
	 */
	public SortedSet<Question> loadQuestionSortedSet();

	/**
	 * 指定された順序でソートされた問題のSetを取得します。
	 * 
	 * @param comparator 問題比較関数
	 * @return 指定された順序でソートされた問題のSet
	 */
	public SortedSet<Question> loadQuestionSortedSet(
			Comparator<Question> comparator);
    
    /**
     * 問題を登録します。
     * 
     * @param question 問題
     */
    public void registerQuestion(Question question);
    
    /**
     * 問題を更新します。
     * 
     * @param question 問題
     */
    public void updateQuestion(Question question);

    /**
     * 問題を削除します。
     * 
     * @param id 問題ID
     */
    public void deleteQuestion(String id);
    
}
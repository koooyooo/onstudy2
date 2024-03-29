package org.onproject.onstudy.data;

import java.util.Comparator;

/**
 * 問題比較クラスの文字列ID実装
 * 
 * @author 恩田 好庸
 */
public class QuestionComparatorStringIDImpl<T extends Question> implements Comparator<T> {
	
	/**
	 * Questionを比較します。
	 * 
	 * @param q1 対象１
	 * @param q2 対象２
	 * @return 対象１が対象２より大きければ 1、等しければ 0、小さければ -1
	 */
	public int compare(T q1, T q2) {
		return q1.getId().compareTo(q2.getId());
	}

}

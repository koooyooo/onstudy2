package org.onproject.onstudy.data;

import java.util.Comparator;

/**
 * ����r�N���X�̕�����ID����
 * 
 * @author ���c �D�f
 */
public class QuestionComparatorStringIDImpl<T extends Question> implements Comparator<T> {
	
	/**
	 * Question���r���܂��B
	 * 
	 * @param q1 �ΏۂP
	 * @param q2 �ΏۂQ
	 * @return �ΏۂP���ΏۂQ���傫����� 1�A��������� 0�A��������� -1
	 */
	public int compare(T q1, T q2) {
		return q1.getId().compareTo(q2.getId());
	}

}

package org.onproject.onstudy.dao;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import org.onproject.onstudy.data.Question;

/**
 * ����DAO
 * 
 * @author ���c �D�f
 */
public interface QuestionDAO {

	/**
	 * ID��Set���擾���܂��B
	 * 
	 * @return ID�̃Z�b�g
	 */
	public Set<String> loadIdSet();

	/**
	 * ID�̎w��ɂ��A�����擾���܂��B
	 * 
	 * @param id �w�肷��ID
	 * @return ����ID������Question�B�������null�B
	 */
	public Question loadQuestionById(String id);

	/**
	 * �f�t�H���g�̏����Ń\�[�g���ꂽ����Set���擾���܂��B
	 * 
	 * @return �f�t�H���g�̏����Ń\�[�g���ꂽ����Set
	 */
	public SortedSet<Question> loadQuestionSortedSet();

	/**
	 * �w�肳�ꂽ�����Ń\�[�g���ꂽ����Set���擾���܂��B
	 * 
	 * @param comparator ����r�֐�
	 * @return �w�肳�ꂽ�����Ń\�[�g���ꂽ����Set
	 */
	public SortedSet<Question> loadQuestionSortedSet(
			Comparator<Question> comparator);
    
    /**
     * ����o�^���܂��B
     * 
     * @param question ���
     */
    public void registerQuestion(Question question);
    
    /**
     * �����X�V���܂��B
     * 
     * @param question ���
     */
    public void updateQuestion(Question question);

    /**
     * �����폜���܂��B
     * 
     * @param id ���ID
     */
    public void deleteQuestion(String id);
    
}
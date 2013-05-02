package org.onproject.onstudy.question.service;

import java.util.Set;

import org.onproject.onstudy.question.data.Question;

/**
 * ���T�[�r�X
 * 
 * @author ���c �D�f
 */
public interface QuestionService {
	
	/**
	 * ID��Set���擾���܂��B
	 * 
	 * @return ID�̃Z�b�g
	 */
	public Set<String> getIdSet();
	
	/**
	 * ID�̎w��ɂ��A�����擾���܂��B
	 * 
	 * @param id �w�肷��ID
	 * @return ����ID������Question�B�������null�B
	 */
	public Question getQuestionById(String id);
	
	/**
	 * �����_���Ŗ����擾���܂��B
	 * 
	 * @return �����_���Ŏ擾���ꂽ���
	 */
	public Question getRandomQuestion();
	
    /**
     * ���ID�����݂����݂��邩���`�F�b�N���܂��B
     * 
     * @param nextQuestionId �ΏۂƂȂ���ID
     * @return ���݂����݂���� true
     */
    public boolean isValidId(String nextQuestionId);
    
    /**
     * ����o�^�܂��͍X�V���܂��B
     * 
     * @question �o�^�܂��͍X�V������
     */
    public void saveOrUpdateQuestion(Question question);
    
    /**
     * �����폜���܂��B
     * 
     * @param id �폜�Ώۂ̖��ID
     */
    public void removeQuestion(String id);
}

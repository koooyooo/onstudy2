package org.onproject.onstudy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.onproject.onstudy.dao.QuestionDAO;
import org.onproject.onstudy.data.Question;

/**
 * ���T�[�r�X�̎���
 * 
 * @author ���c �D�f
 */
public class QuestionServiceImpl implements QuestionService {
	
	/** ��� DAO */
	private QuestionDAO questionDAO;
	
	/** �V���b�t�����ꂽ���ID���X�g */
	private Iterator<String> shuffledQuestionIdIte;
	
	/**
	 * ID��Set���擾���܂��B
	 * 
	 * @return ID�̃Z�b�g
	 */
	public Set<String> getIdSet() {
		return questionDAO.loadIdSet();
	}
	
	/**
	 * �����_���Ŗ����擾���܂��B
	 * 
	 * @return �����_���Ŏ擾���ꂽ���
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
	 * ���ID�����݂����݂��邩���`�F�b�N���܂��B
	 * 
	 * @param nextQuestionId �ΏۂƂȂ���ID
	 * @return ���݂����݂���� true
	 */
	public boolean isValidId(String nextQuestionId) {
		return this.getIdSet().contains(nextQuestionId);
	}
	
	/**
	 * �����_���Ȗ��ID�̔����q�𐶐����܂��B
	 * 
	 * @return �����_���Ȗ��ID�̔����q
	 */
	protected Iterator<String> createRandomIdIterator() {
		final List<String> idList = new ArrayList<String>(this.getIdSet());
		Collections.shuffle(idList);
		return idList.iterator();
	}
	
	/**
	 * ID�̎w��ɂ��A�����擾���܂��B
	 * 
	 * @param id �w�肷��ID
	 * @return ����ID������Question�B�������null�B
	 */
	public Question getQuestionById(String id) {
		return this.questionDAO.loadQuestionById(id);
	}
	
    /**
     * ����o�^�܂��͍X�V���܂��B
     * 
     * @question �o�^�܂��͍X�V������
     */
    public void saveOrUpdateQuestion(Question question) {
        if (this.isValidId(question.getId())) {
            this.questionDAO.updateQuestion(question);
        } else {
            this.questionDAO.registerQuestion(question);
        }
    }
    
    /**
     * �����폜���܂��B
     * 
     * @param id �폜�Ώۂ̖��ID
     */
    public void removeQuestion(String id) {
        this.questionDAO.deleteQuestion(id);
    }
    
	/**
	 * ���DAO��ݒ肵�܂��B
	 * 
	 * @param questionDAO ���DAO
	 */
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

}

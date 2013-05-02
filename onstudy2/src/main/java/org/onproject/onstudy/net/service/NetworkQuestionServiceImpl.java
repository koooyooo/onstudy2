package org.onproject.onstudy.net.service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.service.ServiceLocator;

/**
 * �l�b�g���[�N���T�[�r�X�̎���
 * 
 * @author ���c �D�f
 */
public class NetworkQuestionServiceImpl implements NetworkQuestionService, QuestionPacketListener {
	
	/** ���p�P�b�gQueue TODO ��ԏ�̗v�f�����g���Ă܂��� */
	private final Queue<QuestionPacket> questionPacketQueue = new LinkedList<QuestionPacket>(); 
	
	/**
	 * ID��Set���擾���܂��B
	 * 
	 * @return ID�̃Z�b�g
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getIdSet() {
		if (questionPacketQueue.isEmpty()) {
			return Collections.EMPTY_SET;
		}
		final Set<String> idSet = new LinkedHashSet<String>();
		for (Question question : this.questionPacketQueue.peek().getQuestionList()) {
			idSet.add(question.getId());
		}
		return idSet;
	}
	
	/**
	 * ID�̎w��ɂ��A�����擾���܂��B
	 * 
	 * @param id �w�肷��ID
	 * @return ����ID������Question�B�������null�B
	 */
	public Question getQuestionById(String id) {
		for (Question question : this.questionPacketQueue.peek().getQuestionList()) {
			if (question.getId().equals(id)) {
				return question;
			}
		}
		return null;
	}

	/**
	 * ����ԐM���܂��B
	 * 
	 * @param answerSet �𓚃Z�b�g
	 */
	public synchronized void reply(Set<Answer> answerSet) {
		// TODO ���ʂ̕ԐM�l�b�g���[�N�������K�v
		Member questionSender = this.questionPacketQueue.peek().getTransmitter();
        ServiceLocator.getNetworkService().sendAnswer(questionSender, answerSet);
	}
	
	/**
	 * ���p�P�b�g�̎�M�����m���܂��B
	 * 
	 * @param questionPacket ���p�P�b�g
	 */
	public synchronized void listen(QuestionPacket questionPacket) {
		this.questionPacketQueue.clear(); // TODO
		this.questionPacketQueue.add(questionPacket);
	}

	/**
	 * �����_���Ŗ����擾���܂��B
	 * 
	 * @return �����_���Ŏ擾���ꂽ���
	 */
	public Question getRandomQuestion() {
		throw new UnsupportedOperationException();
	}

    /**
     * ���ID�����݂����݂��邩���`�F�b�N���܂��B
     * 
     * @param nextQuestionId �ΏۂƂȂ���ID
     * @return ���݂����݂���� true
     */
	public boolean isValidId(String nextQuestionId) {
		throw new UnsupportedOperationException();
	}

    /**
     * ����o�^�܂��͍X�V���܂��B
     * 
     * @question �o�^�܂��͍X�V������
     */
	public void saveOrUpdateQuestion(Question question) {
		throw new UnsupportedOperationException();
	}

    /**
     * �����폜���܂��B
     * 
     * @param id �폜�Ώۂ̖��ID
     */
	public void removeQuestion(String id) {
		throw new UnsupportedOperationException();
	}

}

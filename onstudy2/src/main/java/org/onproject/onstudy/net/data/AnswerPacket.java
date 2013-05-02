package org.onproject.onstudy.net.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.onproject.onstudy.question.data.Answer;

/**
 * �𓚃p�P�b�g
 * 
 * @author ���c �D�f
 */
public class AnswerPacket implements Serializable {
	
	/** �V���A���o�[�W����ID */
	private static final long serialVersionUID = 9201456716960858988L;
	
	/** �𓚎� */
	private Member panelist;
	
	/** �𓚃Z�b�g */
	private Set<Answer> answerSet = new HashSet<Answer>();
	
	/**
	 * �𓚎҂��擾���܂��B
	 * 
	 * @return �𓚎�
	 */
	public Member getPanelist() {
		return this.panelist;
	}
	
	/**
	 * �𓚎҂�ݒ肵�܂��B
	 * 
	 * @param panelist �𓚎�
	 */
	public void setPanelst(Member panelist) {
		this.panelist = panelist;
	}
	
    /**
     * �𓚂�ǉ����܂��B
     * 
     * @param answer ��
     */
    public void addAnswer(Answer answer) {
        this.answerSet.add(answer);
    }
    
    /**
     * �𓚂�ǉ����܂��B
     * 
     * @param answers ��
     */
    public void addAnswers(Set<Answer> answers) {
        this.answerSet.addAll(answers);
    }
	
	/**
	 * ���ID�̃Z�b�g���擾���܂��B
	 * 
	 * @return ���ID�̃Z�b�g
	 */
	public Set<String> getQuestionIdSet() {
		Set<String> idSet = new HashSet<String>();
		for (Answer answer : this.answerSet) {
			idSet.add(answer.getQuestionId());
		}
		return idSet;
	}
	
	/**
	 * ���ID�ɑΉ�������ID�Z�b�g���擾���܂��B
	 * 
	 * @param questionId ���ID
	 * @return ���ID�ɑΉ�������ID�Z�b�g
	 */
	public Set<String> getAnswerIdSet(String questionId) {
		for (Answer answer : this.answerSet) {
			if (!questionId.equals(answer.getQuestionId())) {
				continue;
			}
			return answer.getAnswerIdSet();
		}
		return null;
	}
	
	/**
	 * �n�b�V���R�[�h���擾���܂��B
	 * 
	 * @return �n�b�V���R�[�h
	 */
	public int hashCode() {
		int hashCode = this.panelist.hashCode();
		for (Answer answer : this.answerSet) {
			hashCode += answer.hashCode();
		}
		return hashCode;
	}
	
	/**
	 * ���l������s���܂��B
	 * 
	 * @param other ����Ώۂ̃C���X�^���X
	 * @return ���l�ł���� true
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof AnswerPacket)) {
			return false;
		}
		AnswerPacket otherPacket = (AnswerPacket) other;
		if (!this.panelist.equals(otherPacket.getPanelist())) {
			return false;
		}
		if (this.answerSet.size() != otherPacket.getQuestionIdSet().size()) {
			return false;
		}
		for (Answer answer : this.answerSet) {
			if (!otherPacket.getQuestionIdSet().contains(answer.getQuestionId())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ���C���X�^���X�̕�����\���ł��B
	 * 
	 * @return ���C���X�^���X�̕�����\��
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

	
}

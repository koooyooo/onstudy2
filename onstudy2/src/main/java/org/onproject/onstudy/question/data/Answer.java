package org.onproject.onstudy.question.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * �𓚃N���X
 * 
 * @author ���c �D�f
 */
public class Answer implements Serializable {
    
    /** �V���A���o�[�W����ID */
    private static final long serialVersionUID = 9201456716960858989L;
    
    /** ���ID */
    private String questionId;
    
    /** ��ID */
    private Set<String> answerIdSet = new HashSet<String>();
    
    /**
     * ���ID���擾���܂��B
     * 
     * @return ���ID
     */
    public String getQuestionId() {
        return questionId;
    }
    
    /**
     * ���ID��ݒ肵�܂��B
     * 
     * @param questionId ���ID
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    
    /**
     * ��ID�Z�b�g���擾���܂��B
     * 
     * @return ��ID�Z�b�g
     */
    public Set<String> getAnswerIdSet() {
        return new HashSet<String>(this.answerIdSet);
    }
    
    /**
     * ��ID��ǉ����܂��B
     * 
     * @param answerId ��ID
     */
    public void addAnswerId(String answerId) {
        this.answerIdSet.add(answerId);
    }
    
    /**
     * ��ID�𕡐��ǉ����܂��B
     * 
     * @param answerIdSet ��ID�̃Z�b�g
     */
    public void addAnswerIds(Set<String> answerIdSet) {
        this.answerIdSet.addAll(answerIdSet);
    }
    
    /**
     * �n�b�V���R�[�h���擾���܂��B
     * 
     * @return �n�b�V���R�[�h
     */
    public int hashCode() {
        return this.questionId.hashCode();
    }
    
    /**
     * ���l�ł��邩���肵�܂��B
     * 
     * @param other ����Ώ�
     * @return ���l�ł���� true
     */
    public boolean equals(Object other) {
        return other != null
            && (other instanceof Answer)
            && this.questionId.equals(((Answer) other).getQuestionId());
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

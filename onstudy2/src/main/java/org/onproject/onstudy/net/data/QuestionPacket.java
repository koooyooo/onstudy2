/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/QuestionPacket.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/05/17 04:28:19 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.onproject.onstudy.question.data.Question;

/**
 * ���M�����
 * 
 * @author ���c �D�f
 */
public class QuestionPacket implements Serializable {
    
    /** �V���A���o�[�W����ID */
    private static final long serialVersionUID = 8536642449313231441L;

    /** ���M�� */
    private Member transmitter;
    
    /** ��胊�X�g */
    private List<Question> questionList = new ArrayList<Question>();

    /**
     * ���M�҂��擾���܂��B
     * 
     * @return ���M��
     */
    public Member getTransmitter() {
        return this.transmitter;
    }

    /**
     * ���M�҂�ݒ肵�܂��B
     * 
     * @param transmitter ���M��
     */
    public void setTransmitter(Member transmitter) {
        this.transmitter = transmitter;
    }
    
    /**
     * ����ǉ����܂��B
     * 
     * @param question ���
     */
    public void addQuestion(Question question) {
        this.questionList.add(question);
    }
    
    /**
     * �S�Ă̖���ǉ����܂��B
     * 
     * @param questionList ��胊�X�g
     */
    public void addAllQuestions(List<Question> questionList) {
        this.questionList.addAll(questionList);
    }
    
    /**
     * ���̃��X�g���擾���܂��B
     * 
     * @return ���̔����\�q
     */
    public List<Question> getQuestionList() {
        return this.questionList;
    }
    
    /**
     * ���C���X�^���X�̕�����\���ł��B
     * 
     * @return ���C���X�^���X�̕�����\��
     */
    public String toString() {
        return ClassUtils.getShortClassName(this.getClass()) + this.transmitter.toString() + this.questionList.toString();
    }
    
}

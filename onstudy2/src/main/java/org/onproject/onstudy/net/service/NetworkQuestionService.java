package org.onproject.onstudy.net.service;

import java.util.Set;

import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.service.QuestionService;

/**
 * �l�b�g���[�N�z���̖��
 * 
 * @author ���c �D�f
 */
public interface NetworkQuestionService extends QuestionService {
	
    /**
     * ����ԐM���܂��B
     * 
     * @param answerSet �𓚃Z�b�g
     */
    public void reply(Set<Answer> answerSet);
	
}

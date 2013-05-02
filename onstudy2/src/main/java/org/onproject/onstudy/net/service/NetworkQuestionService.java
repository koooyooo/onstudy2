package org.onproject.onstudy.net.service;

import java.util.Set;

import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.service.QuestionService;

/**
 * ネットワーク越しの問題
 * 
 * @author 恩田 好庸
 */
public interface NetworkQuestionService extends QuestionService {
	
    /**
     * 問題を返信します。
     * 
     * @param answerSet 解答セット
     */
    public void reply(Set<Answer> answerSet);
	
}

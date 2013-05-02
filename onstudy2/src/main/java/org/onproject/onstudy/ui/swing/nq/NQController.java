package org.onproject.onstudy.ui.swing.nq;

import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.service.QuestionService;
import org.onproject.onstudy.service.ServiceLocator;
import org.onproject.onstudy.ui.swing.core.ui.UI;
import org.onproject.onstudy.ui.swing.core.ui.UILocator;
import org.onproject.onstudy.ui.swing.question.QuestionController;

/**
 * ネットワーク問題のコントローラ
 * 
 * @author 恩田 好庸
 */
public class NQController extends QuestionController {
	
	/**
	 * 対象となるUIを取得します。
	 * 
	 * @return 対象となるUI
	 */
	@Override
	protected UI getTargetUI() {
		return UILocator.getNQUI();
	}
    
    
	/**
	 * 問題サービスを取得します。
	 * 
	 */
	@Override
	protected QuestionService getQuestionService() {
		return ServiceLocator.getNetworkQuestionService();
	}
    
    /**
     * 解答ボタンのマウス押下イベントです。
     * 
     * @param e マウスイベント
     */
    @Override
    public void answerButtonMouseClicked(MouseEvent e) {
        super.answerButtonMouseClicked(e);
        
        final String currentQuestionId = super.getCurrentQuestionId();
        if (currentQuestionId == null) {
            return;
        }
        
        Answer answer = new Answer();
        answer.setQuestionId(currentQuestionId);
        answer.addAnswerIds(super.getAnswerIdSet());
        
        Set<Answer> answerSet = new HashSet<Answer>();
        answerSet.add(answer);
        
        ServiceLocator.getNetworkQuestionService().reply(answerSet);
    }
    
}

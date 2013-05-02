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
 * �l�b�g���[�N���̃R���g���[��
 * 
 * @author ���c �D�f
 */
public class NQController extends QuestionController {
	
	/**
	 * �ΏۂƂȂ�UI���擾���܂��B
	 * 
	 * @return �ΏۂƂȂ�UI
	 */
	@Override
	protected UI getTargetUI() {
		return UILocator.getNQUI();
	}
    
    
	/**
	 * ���T�[�r�X���擾���܂��B
	 * 
	 */
	@Override
	protected QuestionService getQuestionService() {
		return ServiceLocator.getNetworkQuestionService();
	}
    
    /**
     * �𓚃{�^���̃}�E�X�����C�x���g�ł��B
     * 
     * @param e �}�E�X�C�x���g
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

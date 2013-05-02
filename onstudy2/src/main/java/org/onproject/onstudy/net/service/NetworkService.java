/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/service/NetworkService.java,v $
  Version : $Revision: 1.9 $
  Date    : $Date: 2007/06/20 05:22:42 $
******************************************************************************/
package org.onproject.onstudy.net.service;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.service.InitializableService;

/**
 * �l�b�g���[�N�֌W�̃T�[�r�X
 * 
 * @author ���c �D�f
 */
public interface NetworkService extends InitializableService {
    
    /**
     * ���ݒʐM�\�ȃ����o�̈ꗗ���擾���܂��B
     * 
     * @return �����o�̈ꗗ
     */
    public Set<Member> getCurrentMemberSet();
    
    /**
     * ���𑗐M���܂��B
     * 
     * @param memberList �����o���X�g
     * @param questionList ���̃��X�g
     */
    public void sendQuestion(List<Member> memberList, List<Question> questionList);
    
    /**
     * ���̃��X�i��ǉ����܂��B
     * 
     * @param listener ���̃��X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener listener);
    
    /**
     * �𓚂𑗐M���܂��B
     * 
     * @param member ���M�惁���o
     * @param answerSet �𓚃Z�b�g
     */
    public void sendAnswer(Member member, Set<Answer> answerSet);
    
    /**
     * �𓚃��X�i��ǉ����܂��B
     * 
     * @param listener �𓚃��X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener listener);
    
    /**
     * �l�b�g���[�N���T�[�r�X���擾���܂��B
     * 
     * @return �l�b�g���[�N���T�[�r�X
     */
    public NetworkQuestionService getNetworkQuestionService();
    
}

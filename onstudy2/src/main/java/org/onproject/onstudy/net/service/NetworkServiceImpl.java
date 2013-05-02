/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/service/NetworkServiceImpl.java,v $
  Version : $Revision: 1.17 $
  Date    : $Date: 2007/06/21 06:17:08 $
******************************************************************************/
package org.onproject.onstudy.net.service;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.dao.NetworkDAO;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * �l�b�g���[�N�T�[�r�X�̎���
 * 
 * @author ���c �D�f
 */
public class NetworkServiceImpl implements NetworkService {
    
    /** �l�b�g���[�NDAO */
    private NetworkDAO networkDAO;
    
    /** �l�b�g���[�N���T�[�r�X�̎��� */
    private NetworkQuestionServiceImpl networkQuestionService = new NetworkQuestionServiceImpl();
    
    /**
     * ���������s���܂��B
     *
     */
    public void init() {
        this.networkDAO.init();
        this.networkDAO.startReceiveSignaling();
        this.networkDAO.startReceivingQuestionPacket();
        this.networkDAO.startReceivingAnswerPacket();
        this.addQuestionPacketListener(this.networkQuestionService);
    }
    
    /**
     * ���ݒʐM�\�ȃ����o�̈ꗗ���擾���܂��B
     * 
     * @return �����o�̈ꗗ
     */
    public Set<Member> getCurrentMemberSet() {
        return this.networkDAO.getAwakeMemberSet();
    }

    /**
     * ���𑗐M���܂��B
     * 
     * @param memberList �����o���X�g
     * @param questionList ���̃��X�g
     */
    public void sendQuestion(List<Member> memberList, List<Question> questionList) {
        this.networkDAO.sendQuestion(memberList, questionList);
    }
    
    /**
     * ���̃��X�i��ǉ����܂��B
     * 
     * @param listener ���̃��X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.networkDAO.addQuestionPacketListener(listener);
    }
    
    /**
     * �𓚂𑗐M���܂��B
     * 
     * @param member ���M�惁���o
     * @param answerSet �𓚃Z�b�g
     */
    public void sendAnswer(Member member, Set<Answer> answerSet) {
        this.networkDAO.sendAnswer(member, answerSet);
    }
    
    /**
     * �𓚃��X�i��ǉ����܂��B
     * 
     * @param listener �𓚃��X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.networkDAO.addAnswerPacketListener(listener);
    }
    
    /**
     * �l�b�g���[�NDAO��ݒ肵�܂��B
     * 
     * @param networkDAO �l�b�g���[�NDAO
     */
    public void setNetworkDAO(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }
    
    /**
     * �l�b�g���[�N���T�[�r�X���擾���܂��B
     * 
     * @return �l�b�g���[�N���T�[�r�X
     */
    public NetworkQuestionService getNetworkQuestionService() {
    	return this.networkQuestionService;
    }

}

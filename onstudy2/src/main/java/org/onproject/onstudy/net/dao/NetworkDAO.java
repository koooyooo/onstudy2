/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/NetworkDAO.java,v $
  Version : $Revision: 1.10 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.util.List;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * �l�b�g���[�NDAO
 * 
 * @author ���c �D�f
 */
public interface NetworkDAO {
    
    /**
     * ���������s���܂��B
     *
     */
    public void init();
    
    /**
     * ���݃V�O�i���̎�M���J�n���܂��B
     *
     */
    public void startReceiveSignaling();
    
    /**
     * ���݃V�O�i���̎�M���~���܂��B
     * 
     */
    public void stopReceivingSignaling();
    
    /**
     * ���p�P�b�g�̎�M���J�n���܂��B
     *
     */
    public void startReceivingQuestionPacket();
    
    /**
     * ���p�P�b�g�̎�M���I�����܂��B
     *
     */
    public void stopReceivingQuestionPacket();
    
    /**
     * �𓚃p�P�b�g�̎�M���J�n���܂��B
     *
     */
    public void startReceivingAnswerPacket();
    
    /**
     * �𓚃p�P�b�g�̎�M���~���܂��B
     *
     */
    public void stopReceivingAnswerPacket();
    
    /**
     * ���݋N�����̃����o�Z�b�g���擾���܂��B
     * 
     * @return ���݋N�����̃����o�Z�b�g
     */
    public Set<Member> getAwakeMemberSet();
    
    /**
     * ��胊�X�i��ǉ����܂��B
     * �V�K�ɖ�肪�ʒm���ꂽ�ꍇ�́A�e���X�i�ɒʒm���܂��B
     * 
     * @param listener ��胊�X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener listener);
    
    /**
     * ���𑗐M���܂��B
     * 
     * @param targetMemberList ���惁���o���X�g
     * @param questionList ���̃��X�g
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList);
    
    /**
     * �𓚃��X�i��ǉ����܂��B
     * 
     * @param listener �𓚃��X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener listener);
    
    /**
     * �𓚂𑗐M���܂��B
     * 
     * @param destinationMember ���惁���o
     * @param answerSet �𓚂̃Z�b�g
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet);
    
}

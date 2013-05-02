/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/NetworkDAOImpl.java,v $
  Version : $Revision: 1.22 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.dao.ap.TransceivingAPManager;
import org.onproject.onstudy.net.dao.qp.TransceivingQPManager;
import org.onproject.onstudy.net.dao.signaling.SignalingManager;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.MemberPacket;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.MemberPacketListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;

/**
 * �l�b�g���[�NDAO�̎���
 * 
 * @author ���c �D�f
 */
public class NetworkDAOImpl implements NetworkDAO {

    /** �F�����Ă��郁���o�̃��X�g */
    private final Set<Member> memberSet = Collections.synchronizedSet(new HashSet<Member>());
    
    /** �V�O�i���}�l�[�W�� */
    private SignalingManager signalingManager;
    
    /** ���p�P�b�g�}�l�[�W�� */
    private TransceivingQPManager transceivingQPManager;
    
    /** �𓚃}�l�[�W�� */
    private TransceivingAPManager transceivingAPManager;
    
    /**
     * ���������s���܂��B
     *
     */
    public void init() {
        this.signalingManager.addMemberListener(
            new MemberPacketListener() {
                
                /**
                 * �����o�̒ǉ������m���܂��B
                 * 
                 */
                public void listen(MemberPacket memberPacket) {
                    memberSet.add(memberPacket.getCaller());
                    if (memberPacket.getResponsdent() != null) {
                        memberSet.add(memberPacket.getResponsdent());
                    }
                    if (! this.isMine(memberPacket.getCaller()) && memberPacket.getResponsdent() == null) {
                        try {
                            memberPacket.setResponsdent(createMyMember());
                            signalingManager.sendUnicastSignal(memberPacket.getCaller().getIp(), memberPacket);
                        } catch (Exception e) {
                            throw new SystemException(e.getMessage(), e);
                        }
                    }
                }
                

               
                /**
                 * �����o�[�����g�ł��邩���`�F�b�N���܂��B
                 * 
                 * @param member �`�F�b�N�Ώۂ̃����o
                 * @return �����ł���� true
                 */
                private boolean isMine(Member member) {
                    try {                        
                        return InetAddress.getLocalHost().getHostAddress().equals(member.getIp())
                                    && signalingManager.getUserPort() == member.getPort();
                    } catch (UnknownHostException uhe) {
                        throw new SystemException(uhe.getMessage(), uhe);
                    }
                }
            });
    }
    
    /**
     * ���݃V�O�i���̎�M���J�n���܂��B
     * 
     */
    public void startReceiveSignaling() {
        this.signalingManager.startReceiving();
    }
    
    /**
     * ���݃V�O�i���̎�M���~���܂��B
     */
    public void stopReceivingSignaling() {
        this.signalingManager.stopReceiving();
    }
    
    /**
     * ���p�P�b�g�̎�M���J�n���܂��B
     * 
     */
    public void startReceivingQuestionPacket() {
        this.transceivingQPManager.startReceiving();
    }

    /**
     * ���p�P�b�g�̎�M���~���܂��B
     * 
     */
    public void stopReceivingQuestionPacket() {
        this.transceivingQPManager.stopReceiving();
    }
    
    /**
     * �𓚃p�P�b�g�̎�M���J�n���܂��B
     *
     */
    public void startReceivingAnswerPacket() {
        this.transceivingAPManager.startReceiving();
    }
    
    /**
     * �𓚃p�P�b�g�̎�M���~���܂��B
     *
     */
    public void stopReceivingAnswerPacket() {
        this.transceivingAPManager.stopReceiving();
    }
    
    /**
     * ���݋N�����̃����o�Z�b�g���擾���܂��B
     * 
     * @return ���݋N�����̃����o�Z�b�g
     */
    public synchronized Set<Member> getAwakeMemberSet() {
        
        this.memberSet.clear();
        
        try {
            MemberPacket packet = new MemberPacket();
            packet.setCaller(this.createMyMember());
            
            this.signalingManager.sendMulticastSignal(packet);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
        
        return new HashSet<Member>(this.memberSet);
    }
    
    /**
     * ���g�̃����o�[���쐬���܂��B
     * 
     * @return ���g�̃����o�[
     * @throws UnknownHostException
     */
    private Member createMyMember() throws UnknownHostException {
        Member mine = new Member();
        mine.setName(signalingManager.getUserName());
        mine.setIp(InetAddress.getLocalHost().getHostAddress());
        mine.setPort(signalingManager.getUserPort());
        return mine;
    }
    
    /**
     * ��胊�X�i��ǉ����܂��B
     * 
     * @param listener ��胊�X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.transceivingQPManager.addQuestionPacketListener(listener);
    }

    /**
     * ���𑗐M���܂��B
     * 
     * @param targetMemberList ���惁���o���X�g
     * @param questionList ���̃��X�g
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList) {
        this.transceivingQPManager.sendQuestion(destinationMemberList, questionList);
    }
    
    /**
     * �𓚃��X�i��ǉ����܂��B
     * 
     * @param listener �𓚃��X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.transceivingAPManager.addAnswerPacketListener(listener);
    }
    
    /**
     * �𓚂𑗐M���܂��B
     * 
     * @param destinationMember ���惁���o
     * @param answerSet �𓚂̃Z�b�g
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet) {
        this.transceivingAPManager.sendAnswer(destinationMember, answerSet);
    }
    
    /**
     * �V�O�i���}�l�[�W����ݒ肵�܂��B
     * 
     * @param signalingManager �V�O�i���}�l�[�W��
     */
    public void setSignalingManager(SignalingManager signalingManager) {
        this.signalingManager = signalingManager;
    }
    
    /**
     * ���p�P�b�g�}�l�[�W����ݒ肵�܂��B
     * 
     * @param transceivingQPManager ���p�P�b�g�}�l�[�W��
     */
    public void setTransceivingQPManager(TransceivingQPManager transceivingQPManager) {
        this.transceivingQPManager = transceivingQPManager;
    }
    
    /**
     * �𓚃p�P�b�g�}�l�[�W����ݒ肵�܂��B
     * 
     * @param transceivingAPManager �𓚃p�P�b�g�}�l�[�W��
     */
    public void setTransceivingAPManager(TransceivingAPManager transceivingAPManager) {
        this.transceivingAPManager = transceivingAPManager;
    }



}

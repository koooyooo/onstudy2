/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/qp/TransceivingQPManager.java,v $
  Version : $Revision: 1.7 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.qp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPSender;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * ��著��M�}�l�[�W��
 * 
 * @author ���c �D�f
 */
public class TransceivingQPManager {
    
    /** TCP���M�I�u�W�F�N�g */
    private TCPSender tcpSender;
    
    /** ���p�P�b�g��M�X���b�h */
    private ReceiveQPThread receiveQPThread;
    
    /** ���[�U�}�l�[�W�� */
    private UserManager userManager;
    
    /** �񓚎�M�p�|�[�g */
    private int replyPort;
    
    /**
     * ���𑗐M���܂��B
     * 
     * @param targetMemberList ���惁���o���X�g
     * @param questionList ���̃��X�g
     */
    public void sendQuestion(List<Member> destinationMemberList, List<Question> questionList) {

        try {
            
            Member mine = new Member();
            mine.setName(userManager.getUserName());
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(this.replyPort);
                        
            QuestionPacket packet = new QuestionPacket();
            packet.setTransmitter(mine);
            packet.addAllQuestions(questionList);

            for (Member menber : destinationMemberList) {
                this.tcpSender.send(menber.getIp(), menber.getPort(), this.convertQuestionPacketToByte(packet));
            }

        } catch (IOException ioe) {
            throw new SystemException(ioe.getMessage(), ioe);
        }
    }
    
    /**
     * ���p�P�b�g���o�C�i�������܂��B
     * 
     * @param packet ���p�P�b�g
     * @return
     * @throws IOException
     */
    private byte[] convertQuestionPacketToByte(QuestionPacket packet) throws IOException {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(packet);
            oos.flush();
            
            return baos.toByteArray();
        } finally {
            IOUtils.closeQuietly(oos);
        }
    }
    
    /**
     * ��M���J�n���܂��B
     *
     */
    public void startReceiving() {
        this.receiveQPThread.start();
    }
    
    /**
     * ��M���I�����܂��B
     *
     */
    public void stopReceiving() {
        this.receiveQPThread.stopRunning();
    }
    
    /**
     * ���p�P�b�g���X�i��ǉ����܂��B
     * 
     * @param listener ���p�P�b�g���X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener listener) {
        this.receiveQPThread.addQuestionPacketListener(listener);
    }
    
    /**
     * TCP���M�I�u�W�F�N�g��ǉ����܂��B
     * 
     * @param sender TCP���M�I�u�W�F�N�g
     */
    public void setTCPSender(TCPSender sender) {
        this.tcpSender = sender;
    }
    
    /**
     * ���p�P�b�g��M�X���b�h��ݒ肵�܂��B
     * 
     * @param reveiceQPThread ���p�P�b�g��M�X���b�h
     */
    public void setReceiveQPThread(ReceiveQPThread reveiceQPThread) {
        this.receiveQPThread = reveiceQPThread;
    }
    
    /**
     * ���[�U�}�l�[�W�����擾���܂��B<BR>
     * 
     * @return ���[�U�}�l�[�W��
     */
    public UserManager getUserManager() {
        return userManager;
    }
    
    /**
     * ���[�U�}�l�[�W����ݒ肵�܂��B
     * 
     * @param userManager ���[�U�}�l�[�W��
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    /**
     * �񓚎�M�p�|�[�g���擾���܂��B
     * 
     * @param replyPort �񓚎�M�p�|�[�g
     */
    public void setReplyPort(int replyPort) {
        this.replyPort = replyPort;
    }

    
}

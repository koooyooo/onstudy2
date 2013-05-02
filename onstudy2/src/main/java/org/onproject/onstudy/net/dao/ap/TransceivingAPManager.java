/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ap/TransceivingAPManager.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.ap;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPSender;
import org.onproject.onstudy.net.data.AnswerPacket;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * �𓚑���M�}�l�[�W��
 * 
 * @author ���c �D�f
 */
public class TransceivingAPManager {
    
    /** TCP���M�I�u�W�F�N�g */
    private TCPSender tcpSender;
    
    /** �𓚃p�P�b�g��M�X���b�h */
    private ReceiveAPThread receiveAPThread;
    
    /** ���[�U�}�l�[�W�� */
    private UserManager userManager;
    
    /**
     * �𓚂𑗐M���܂��B
     * 
     * @param destinationMember ���M��
     * @param answerSet �𓚂̃Z�b�g
     */
    public void sendAnswer(Member destinationMember, Set<Answer> answerSet) {

        try {
            
            Member mine = new Member();
            mine.setName(userManager.getUserName());
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(0);
                        
            AnswerPacket packet = new AnswerPacket();
            packet.setPanelst(mine);
            packet.addAnswers(answerSet);
            
            this.tcpSender.send(destinationMember.getIp(), destinationMember.getPort(), this.convertAnswerPacketToByte(packet));

        } catch (IOException ioe) {
            throw new SystemException(ioe.getMessage(), ioe);
        }
    }
    
    /**
     * �𓚃p�P�b�g���o�C�i�������܂��B
     * 
     * @param packet �𓚃p�P�b�g
     * @return
     * @throws IOException
     */
    private byte[] convertAnswerPacketToByte(AnswerPacket packet) throws IOException {
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
        this.receiveAPThread.start();
    }
    
    /**
     * ��M���I�����܂��B
     *
     */
    public void stopReceiving() {
        this.receiveAPThread.stopRunning();
    }
    
    /**
     * �񓚃p�P�b�g���X�i��ǉ����܂��B
     * 
     * @param listener �񓚃p�P�b�g���X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener listener) {
        this.receiveAPThread.addAnswerPacketListener(listener);
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
     * @param receiveAPThread ���p�P�b�g��M�X���b�h
     */
    public void setReceiveAPThread(ReceiveAPThread receiveAPThread) {
        this.receiveAPThread = receiveAPThread;
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

    
}

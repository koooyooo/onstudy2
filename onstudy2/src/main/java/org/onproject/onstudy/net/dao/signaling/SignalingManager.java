/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/SignalingManager.java,v $
  Version : $Revision: 1.6 $
  Date    : $Date: 2007/06/22 08:16:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.MulticastSender;
import org.onproject.onstudy.net.core.UnicastSender;
import org.onproject.onstudy.net.data.MemberPacket;
import org.onproject.onstudy.net.listener.MemberPacketListener;
import org.onproject.onstudy.ui.swing.user.UserManager;

/**
 * �V�O�i��������S������}�l�[�W��
 * 
 * @author ���c �D�f
 */
public class SignalingManager {
    
    /** ���j�L���X�g���M */
    private UnicastSender unicastSender;
    
    /** �}���`�L���X�g���M */
    private  MulticastSender multicastSender;
    
    /** ���[�U�� */
    private UserManager userManager;
    
    /** ���[�U�|�[�g */
    private int userPort;
    
    /** ���j�L���X�g�V�O�i����M�X���b�h */
    private ReceiveUnicastSignalingThread receiveUnicastSignalingThread;
    
    /** �}���`�L���X�g�V�O�i����M�X���b�h */
    private ReceiveMulticastSignalingThread receiveMulticastSignalingThread;
    
    /**
     * ���j�L���X�g�ʐM�����܂��B
     * 
     * @param ip IP�A�h���X�i255.255.255.255�`���j
     * @param port �|�[�g
     */
    public void sendUnicastSignal(String ip, MemberPacket memberPacket) {
        try {
            unicastSender.send(ip, this.createMyMemberPacketByteArray(memberPacket));
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }
    
    /**
     * �}���`�L���X�g�ʒm�����܂��B
     * 
     */
    public void sendMulticastSignal(MemberPacket memberPacket) {
        try {
            multicastSender.send(this.createMyMemberPacketByteArray(memberPacket));
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }
    
    /**
     * ���g�̃����o�[�̃o�C�g�z����쐬���܂��B
     * 
     * @return ���g�̃����o�[�̃o�C�g�z��
     * @throws Exception
     */
    private byte[] createMyMemberPacketByteArray(MemberPacket memberPacket) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(memberPacket);
        oos.flush();
        
        return bos.toByteArray();
    }
    
    /**
     * �V�O�i����M���J�n���܂��B
     *
     */
    public void startReceiving() {
        this.receiveUnicastSignalingThread.start();
        this.receiveMulticastSignalingThread.start();
    }
    
    /**
     * �V�O�i����M���~���܂��B
     *
     */
    public void stopReceiving() {
        this.receiveUnicastSignalingThread.stopRunning();
        this.receiveMulticastSignalingThread.stopRunning();
    }
    
    /**
     * �����o���X�i��ǉ����܂��B
     * 
     * @param listener �����o���X�i
     */
    public void addMemberListener(MemberPacketListener listener) {
        this.receiveUnicastSignalingThread.addMemberListener(listener);
        this.receiveMulticastSignalingThread.addMemberListener(listener);
    }
    
    /**
     * ���j�L���X�g�V�O�i����M�X���b�h��ݒ肵�܂��B
     * 
     * @param receiveUnicastSignalingThread ���j�L���X�g��M�X���b�h
     */
    public void setReceiveUnicastSignalingThread(ReceiveUnicastSignalingThread receiveUnicastSignalingThread) {
        this.receiveUnicastSignalingThread = receiveUnicastSignalingThread;
    }
    
    /**
     * �}���`�L���X�g�V�O�i����M�X���b�h��ݒ肵�܂��B
     * 
     * @param receiveMulticastSignalingThread �}���`�L���X�g�V�O�i����M�X���b�h
     */
    public void setReceiveMulticastSignalingThread(ReceiveMulticastSignalingThread receiveMulticastSignalingThread) {
        this.receiveMulticastSignalingThread = receiveMulticastSignalingThread;
    }
    
    /**
     * ���[�U�����擾���܂��B
     * 
     * @return ���[�U��
     */
    public String getUserName() {
        return this.userManager.getUserName();
    }
    
    /**
     * ���[�U�}�l�[�W�����擾���܂��B
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
     * �|�[�g���擾���܂��B
     * 
     * @return �|�[�g
     */
    public int getUserPort() {
        return this.userPort;
    }
    
    /**
     * ���[�U�̗��p����|�[�g��ݒ肵�܂��B
     * 
     * @param userPort ���[�U�̗��p����|�[�g
     */
    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }
    
    /**
     * ���j�L���X�g���M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param unicastSender ���j�L���X�g���M�I�u�W�F�N�g
     */
    public void setUnicastSender(UnicastSender unicastSender) {
        this.unicastSender = unicastSender;
    }
    
    /**
     * �}���`�L���X�g���M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param multicastSender �}���`�L���X�g���M�I�u�W�F�N�g
     */
    public void setMulticastSender(MulticastSender multicastSender) {
        this.multicastSender = multicastSender;
    }
    
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/SendSignalingThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:48 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import org.onproject.onstudy.net.core.MulticastSender;
import org.onproject.onstudy.net.data.Member;

/**
 * ���݃V�O�i�����M�X���b�h
 * 
 * @author ���c �D�f
 */
public class SendSignalingThread extends Thread {
    
    /** �}���`�L���X�g���M */
    private  MulticastSender multicastSender;
    
    /** ��~�v���t���O */
    private boolean wannaStop = false;
    
    /** ���M�V�O�i���̑��M�Ԋu */
    private int signalingIntervalMilliSec = 1000;
    
    /** ���[�U�� */
    private String userName;
    
    /** ���[�U�|�[�g */
    private int userPort;
    
    /**
     * �ʒm�����܂��B
     */
    public void run() {
        try {
            Member mine = new Member();
            mine.setName(userName);
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(userPort);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(mine);
            oos.flush();
            
            while (!wannaStop) {
                multicastSender.send(bos.toByteArray());
                try {
                    Thread.sleep(signalingIntervalMilliSec);
                } catch (InterruptedException ignore) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
    }
    
    /**
     * ��~�v�����o���܂��B
     *
     */
    public void stopRunning() {
        this.wannaStop = true;
    }
    
    /**
     * ���݃V�O�i���̑��M�Ԋu���_�b�Ŏw�肵�܂��B
     * 
     * @param signalingIntervalMilliSec ���݃V�O�i���̑��M�Ԋu
     */
    public void setSignalingIntervalMilliSec(int signalingIntervalMilliSec) {
        this.signalingIntervalMilliSec = signalingIntervalMilliSec;
    }
    
    /**
     * ���[�U����ݒ肵�܂��B
     * 
     * @param userName ���[�U��
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * �}���`�L���X�g���M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param multicastSender �}���`�L���X�g���M�I�u�W�F�N�g
     */
    public void setMulticastSender(MulticastSender multicastSender) {
        this.multicastSender = multicastSender;
    }
}
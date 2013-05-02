/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveMulticastSignalingThread.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import org.onproject.onstudy.net.core.MulticastReceiver;
import org.onproject.onstudy.net.listener.MemberPacketListener;

/**
 * �}���`�L���X�g���݃V�O�i����M�X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveMulticastSignalingThread extends Thread {
    
    /** �}���`�L���X�g��M */
    private MulticastReceiver multicastReceiver;
    
    /**
     * �����o�[���X�i��ǉ����܂��B
     * 
     * @param listener �����o�[���X�i
     */
    public void addMemberListener(MemberPacketListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberPacketListener(listener);
        this.multicastReceiver.addByteDataListener(memberAdapterByteListener);
    }
    
    /**
     * �ʒm�����܂��B
     * 
     */
    public void run() {
        multicastReceiver.startReceiving();
    }
    
    /**
     * ��~�v�����o���܂��B
     *
     */
    public void stopRunning() {
        multicastReceiver.stopReceiving();
    }
    
    /**
     * �}���`�L���X�g��M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param receiver �}���`�L���X�g��M�I�u�W�F�N�g
     */
    public void setMulticastReceiver(MulticastReceiver receiver) {
        this.multicastReceiver = receiver;
    }
    
}

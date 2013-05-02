/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveUnicastSignalingThread.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import org.onproject.onstudy.net.core.UnicastReceiver;
import org.onproject.onstudy.net.listener.MemberPacketListener;

/**
 * ���j�L���X�g���݃V�O�i����M�X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveUnicastSignalingThread extends Thread {
    
    /** ���j�L���X�g��M */
    private UnicastReceiver unicastReceiver;
    
    /**
     * �����o�[���X�i��ǉ����܂��B
     * 
     * @param listener �����o�[���X�i
     */
    public void addMemberListener(MemberPacketListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberPacketListener(listener);
        this.unicastReceiver.addByteDataListener(memberAdapterByteListener);
    }
    
    /**
     * �ʒm�����܂��B
     * 
     */
    public void run() {
        unicastReceiver.startReceiving();
    }
    
    /**
     * ��~�v�����o���܂��B
     *
     */
    public void stopRunning() {
        unicastReceiver.stopReceiving();
    }
    
    /**
     * �}���`�L���X�g��M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param receiver �}���`�L���X�g��M�I�u�W�F�N�g
     */
    public void setUnicastReceiver(UnicastReceiver receiver) {
        this.unicastReceiver = receiver;
    }
    
}

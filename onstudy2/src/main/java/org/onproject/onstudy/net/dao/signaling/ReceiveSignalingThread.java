/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/signaling/ReceiveSignalingThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.signaling;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.MulticastReceiver;
import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.listener.ByteDataListener;
import org.onproject.onstudy.net.listener.MemberListener;

/**
 * ���݃V�O�i����M�X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveSignalingThread extends Thread {

    /** �}���`�L���X�g��M */
    private MulticastReceiver multicastReceiver;
    
    /**
     * �����o�[���X�i��ǉ����܂��B
     * 
     * @param listener �����o�[���X�i
     */
    public void addMemberListener(MemberListener listener) {
        MemberAdapterByteListener memberAdapterByteListener = new MemberAdapterByteListener();
        memberAdapterByteListener.setMemberListener(listener);
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
     * ���݃V�O�i���̃��X�i
     * 
     * @author ���c �D�f
     */
    private static class MemberAdapterByteListener implements ByteDataListener {
        
        /** �����o�[���X�i */
        private MemberListener memberListener;
        
        /** 
         * �����o�[���X�i��ݒ肵�܂� 
         * 
         * @param memberListener �����o�[���X�i
         */
        public void setMemberListener(MemberListener memberListener) {
            this.memberListener = memberListener;
        }
        
        /**
         * �o�C�i���f�[�^����M���܂��B
         * 
         * @param data ��M�f�[�^
         */
        public void listen(byte[] data) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object object = ois.readObject();
                if (!(object instanceof Member)) {
                    return;
                }
                this.memberListener.listen((Member) object);
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            }
        }
        
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

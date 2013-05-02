/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ReceiveSignalingThread.java,v $
  Version : $Revision: 1.6 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Set;

import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.ByteDataListener;
import org.onproject.onstudy.net.MulticastReceiver;
import org.onproject.onstudy.net.data.Member;

/**
 * ���݃V�O�i����M�X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveSignalingThread extends Thread {

    /** �}���`�L���X�g��M */
    private final MulticastReceiver multicastReceiver = new MulticastReceiver();
    
    /**
     * �����������ł��B
     * 
     * @param memberSet
     */
    public void init(Set<Member> memberSet) {
        this.multicastReceiver.addByteDataListener(new SignalingDataListener(memberSet));
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
    public void wannaStop() {
        multicastReceiver.stopReceiving();
    }
    
    /**
     * ���݃V�O�i���̃��X�i
     * 
     * @author ���c �D�f
     */
    private static class SignalingDataListener implements ByteDataListener {
        
        /** �����o���X�g */
        private final Set<Member> memberSet;
        
        /**
         * �R���X�g���N�^
         * 
         * @param memberSet �����o�Z�b�g
         */
        public SignalingDataListener(Set<Member> memberSet) {
            this.memberSet = memberSet;
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
                this.memberSet.add((Member) object);
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            }
        }
        
    }
    
}

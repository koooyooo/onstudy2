/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/ap/ReceiveAPThread.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.ap;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPReceiver;
import org.onproject.onstudy.net.data.AnswerPacket;
import org.onproject.onstudy.net.listener.AnswerPacketListener;
import org.onproject.onstudy.net.listener.ByteDataListener;

/**
 * �𓚃p�P�b�g����M����X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveAPThread extends Thread {
    
    /** TCP��M�I�u�W�F�N�g */
    private TCPReceiver tcpReceiver;
    
    /**
     * ���������s���܂��B
     */
    public void run() {
        try {
            tcpReceiver.startReceiving();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��M�����𒆎~���܂��B
     *
     */
    public void stopRunning() {
        this.tcpReceiver.stopReceiving();
    }
    
    /**
     * �𓚃p�P�b�g���X�i�R���N�V������ǉ����܂��B
     * 
     * @param apListener �ǉ�����𓚃p�P�b�g���X�i
     */
    public void addAnswerPacketListener(AnswerPacketListener apListener) {
        
        AnswerPacketAdapterByteListener apAdapterByteListener = new AnswerPacketAdapterByteListener();
        apAdapterByteListener.setAnswerPacketListener(apListener);
        
        this.tcpReceiver.addByteDataListener(apAdapterByteListener);
    }
    
    /**
     * TCP��M�I�u�W�F�N�g��ݒ肵�܂��B
     * 
     * @param tcpReceiver TCP��M�I�u�W�F�N�g
     */
    public void setTCPReceiver(TCPReceiver tcpReceiver) {
        this.tcpReceiver = tcpReceiver;
    }
    
    /**
     * �𓚃p�P�b�g�̃��X�i�A�_�v�^
     * 
     * @author ���c �D�f
     */
    public static class AnswerPacketAdapterByteListener implements ByteDataListener {
        
        /** �𓚃p�P�b�g���X�i */
        private AnswerPacketListener apListener;
        
        /**
         * ��M���܂��B
         * 
         * @param data ��M�f�[�^
         */
        public void listen(byte[] data) {
            ObjectInputStream ois = null;
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                BufferedInputStream bis = new BufferedInputStream(bais);
                ois = new ObjectInputStream(bis);
                
                Object obj = ois.readObject();
                if (!(obj instanceof AnswerPacket)) {
                    return;
                }
                AnswerPacket packet = (AnswerPacket) obj;
                this.apListener.listen(packet);
                
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(ois);
            }
        }
        
        /**
         * �𓚃p�P�b�g���X�i��ǉ����܂��B
         * 
         * @param apListener �𓚃p�P�b�g���X�i
         */
        public void setAnswerPacketListener(AnswerPacketListener apListener) {
            this.apListener = apListener;
        }
    }
        
}

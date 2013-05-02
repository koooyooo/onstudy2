/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/qp/ReceiveQPThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:47 $
******************************************************************************/
package org.onproject.onstudy.net.dao.qp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.net.core.TCPReceiver;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.ByteDataListener;
import org.onproject.onstudy.net.listener.QuestionPacketListener;

/**
 * ���p�P�b�g����M����X���b�h
 * 
 * @author ���c �D�f
 */
public class ReceiveQPThread extends Thread {
    
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
     * ���p�P�b�g���X�i�R���N�V������ǉ����܂��B
     * 
     * @param qpListener �ǉ�������p�P�b�g���X�i
     */
    public void addQuestionPacketListener(QuestionPacketListener qpListener) {
        
        QuestionPacketAdapterByteListener qpAdapterByteListener = new QuestionPacketAdapterByteListener();
        qpAdapterByteListener.setQuestionpacketListener(qpListener);
        
        this.tcpReceiver.addByteDataListener(qpAdapterByteListener);
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
     * ���p�P�b�g�̃��X�i�A�_�v�^
     * 
     * @author ���c �D�f
     */
    public static class QuestionPacketAdapterByteListener implements ByteDataListener {
        
        /** ���p�P�b�g���X�i */
        private QuestionPacketListener qpListener;
        
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
                if (!(obj instanceof QuestionPacket)) {
                    return;
                }
                QuestionPacket packet = (QuestionPacket) obj;
                this.qpListener.listen(packet);
                
            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(ois);
            }
        }
        
        /**
         * ���p�P�b�g���X�i��ǉ����܂��B
         * 
         * @param qpListener ���p�P�b�g���X�i
         */
        public void setQuestionpacketListener(QuestionPacketListener qpListener) {
            this.qpListener = qpListener;
        }
    }
        
}

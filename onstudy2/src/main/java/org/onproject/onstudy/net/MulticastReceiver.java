/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/MulticastReceiver.java,v $
  Version : $Revision: 1.8 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collection;

import org.onproject.onstudy.exception.SystemException;

/**
 * UDP�}���`�L���X�g�̎�M��
 *
 * @author ���c �D�f
 */
public class MulticastReceiver {
    
    /** IP �A�h���X [255.255.255.255]�`�� (InetAddress�^�͏�������O�����������String�^) */
    private String ip = "224.0.1.1";
    
    /** �|�[�g�ԍ� */
    private int port = 10000;
    
    /** ��M�o�b�t�@�T�C�Y */
    private int bufferSize = 65507;
    
    /** ��~�v���t���O */
    private boolean wannaStop = false;
    
    /** �f�[�^���X�i�̃R���N�V���� */
    private Collection<ByteDataListener> dataListenerCollection = new ArrayList<ByteDataListener>();
    
    /**
     * ��M���������s���܂��B
     * ��M�����f�[�^�́A�e�f�[�^���X�i�ɔz�z����܂��B
     *
     */
    public synchronized void startReceiving() {
        
        this.wannaStop = false;
        
        final byte[] buffer = new byte[bufferSize];
        MulticastSocket multiSocket = null;

        try {
            multiSocket = new MulticastSocket(port);
            multiSocket.joinGroup(InetAddress.getByName(ip));
            
            while (!this.wannaStop) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);              
                multiSocket.receive(packet);
                this.distributeDataToListener(packet.getData());
            }

        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            if (multiSocket != null) {
                multiSocket.close();
            }
        }
    }
    
    /**
     * ��M�������I�����܂��B
     *
     */
    public void stopReceiving() {
        this.wannaStop = true;
    }
    
    /**
     * �p�P�b�g�̃f�[�^���A�e�f�[�^���X�i�ɕ��z���܂��B
     * 
     * @param data �p�P�b�g�̃f�[�^
     */
    private void distributeDataToListener(byte[] data) {
        for (ByteDataListener listener : dataListenerCollection) {
            listener.listen(data);
        }
    }
    
    /**
     * �}���`�L���X�g���X�i��ǉ����܂��B
     * 
     * @param listener �f�[�^�g���X�i
     */
    public synchronized void addByteDataListener(ByteDataListener listener) {
        this.dataListenerCollection.add(listener);
    }
    
    /**
     * �f�[�^���X�i��S�ď������܂��B
     *
     */
    public synchronized void clearDataListener() {
        this.dataListenerCollection.clear();
    }
    
    /**
     * IP�A�h���X���擾���܂��B
     * 
     * @return IP�A�h���X
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * IP�A�h���X��ݒ肵�܂��B
     * 
     * @param ip IP�A�h���X
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * �|�[�g�ԍ����擾���܂��B
     * 
     * @return �|�[�g�ԍ�
     */
    public int getPort() {
        return this.port;
    }

    /**
     * �|�[�g�ԍ���ݒ肵�܂��B
     * 
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * ��M�o�b�t�@�̃T�C�Y���擾���܂��B
     * 
     * @return ��M�o�b�t�@�̃T�C�Y
     */
    public int getBufferSize() {
        return this.bufferSize;
    }

    /**
     * ��M�o�b�t�@�̃T�C�Y��ݒ肵�܂��B
     * 
     * @param bufferSize ��M�o�b�t�@�̃T�C�Y
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
    
}

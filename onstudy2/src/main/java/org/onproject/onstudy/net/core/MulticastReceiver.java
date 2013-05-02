/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/MulticastReceiver.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/05/17 08:19:28 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.onproject.onstudy.exception.SystemException;

/**
 * UDP�}���`�L���X�g�̎�M��
 *
 * @author ���c �D�f
 */
public class MulticastReceiver extends AbstractReceiver {
    
    /** ��M�o�b�t�@�T�C�Y */
    protected int bufferSize = 65507;
    
    /** IP �A�h���X [255.255.255.255]�`�� */
    protected String ip = "224.0.1.1";
    
    /**
     * �R���X�g���N�^
     *
     */
    public MulticastReceiver() {
        super.port = 10000;
    }
    
    /**
     * ��M���������s���܂��B
     * ��M�����f�[�^�́A�e�f�[�^���X�i�ɔz�z����܂��B
     *
     */
    @Override
    public synchronized void doStartReceiving() {
        
        final byte[] buffer = new byte[this.bufferSize];
        MulticastSocket multiSocket = null;
        
        try {
            multiSocket = new MulticastSocket(port);
            multiSocket.joinGroup(InetAddress.getByName(ip));
            
            while (!this.wannaStop) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);              
                multiSocket.receive(packet);
                super.distributeDataToListener(packet.getData());
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
        
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/UnicastSender.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/05 07:36:37 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.onproject.onstudy.exception.SystemException;

/**
 * UDP�}���`�L���X�g�̑��M��
 * 
 * @author ���c �D�f
 */
public class UnicastSender {
    
    /** �|�[�g�ԍ� */
    protected int port = 10000;
    
    /**
     * ���M���������s���܂��B
     *
     */
    public synchronized void send(String ip, byte[] data) {
        try {
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
            socket.send(packet);
            
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
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
    
}

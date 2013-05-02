/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/MulticastSender.java,v $
  Version : $Revision: 1.6 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * UDP�}���`�L���X�g�̑��M��
 * 
 * @author ���c �D�f
 */
public class MulticastSender {
    
    /** IP �A�h���X [255.255.255.255]�`�� (InetAddress�^�͏�������O�����������String�^) */
    private String ip = "224.0.1.1";
    
    /** �|�[�g�ԍ� */
    private int port = 10000;
    
    /**
     * ���M���������s���܂��B
     *
     */
    public synchronized void send(byte[] data) {
        
        MulticastSocket multiSocket = null;
        try {
            InetAddress address = InetAddress.getByName(ip);
            multiSocket = new MulticastSocket();
            
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);            
            multiSocket.send(packet);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (multiSocket != null) {
                multiSocket.close();
            }
        }
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
    
}

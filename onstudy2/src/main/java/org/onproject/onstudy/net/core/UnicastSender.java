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
 * UDPマルチキャストの送信側
 * 
 * @author 恩田 好庸
 */
public class UnicastSender {
    
    /** ポート番号 */
    protected int port = 10000;
    
    /**
     * 送信処理を実行します。
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
     * ポート番号を取得します。
     * 
     * @return ポート番号
     */
    public int getPort() {
        return this.port;
    }

    /**
     * ポート番号を設定します。
     * 
     * @param port ポート番号
     */
    public void setPort(int port) {
        this.port = port;
    }
    
}

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
 * UDPマルチキャストの送信側
 * 
 * @author 恩田 好庸
 */
public class MulticastSender {
    
    /** IP アドレス [255.255.255.255]形式 (InetAddress型は初期化例外が発生する為String型) */
    private String ip = "224.0.1.1";
    
    /** ポート番号 */
    private int port = 10000;
    
    /**
     * 送信処理を実行します。
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
     * IPアドレスを取得します。
     * 
     * @return IPアドレス
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * IPアドレスを設定します。
     * 
     * @param ip IPアドレス
     */
    public void setIp(String ip) {
        this.ip = ip;
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

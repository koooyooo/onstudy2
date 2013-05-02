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
 * UDPマルチキャストの受信側
 *
 * @author 恩田 好庸
 */
public class MulticastReceiver extends AbstractReceiver {
    
    /** 受信バッファサイズ */
    protected int bufferSize = 65507;
    
    /** IP アドレス [255.255.255.255]形式 */
    protected String ip = "224.0.1.1";
    
    /**
     * コンストラクタ
     *
     */
    public MulticastReceiver() {
        super.port = 10000;
    }
    
    /**
     * 受信処理を実行します。
     * 受信したデータは、各データリスナに配布されます。
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
     * 受信バッファのサイズを取得します。
     * 
     * @return 受信バッファのサイズ
     */
    public int getBufferSize() {
        return this.bufferSize;
    }

    /**
     * 受信バッファのサイズを設定します。
     * 
     * @param bufferSize 受信バッファのサイズ
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
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
        
}

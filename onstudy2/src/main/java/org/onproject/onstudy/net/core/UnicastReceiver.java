/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/UnicastReceiver.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/05/17 08:41:02 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.onproject.onstudy.exception.SystemException;

/**
 * UDPマルチキャストの受信側
 *
 * @author 恩田 好庸
 */
public class UnicastReceiver extends AbstractReceiver {
    
    /** 受信バッファサイズ */
    protected int bufferSize = 65507;
    
    /**
     * コンストラクタ
     *
     */
    public UnicastReceiver() {
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
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            while (!this.wannaStop) {        
                socket.receive(packet);
                super.distributeDataToListener(packet.getData());
            }

        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            if (socket != null) {
                socket.close();
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
        
}

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
 * UDPマルチキャストの受信側
 *
 * @author 恩田 好庸
 */
public class MulticastReceiver {
    
    /** IP アドレス [255.255.255.255]形式 (InetAddress型は初期化例外が発生する為String型) */
    private String ip = "224.0.1.1";
    
    /** ポート番号 */
    private int port = 10000;
    
    /** 受信バッファサイズ */
    private int bufferSize = 65507;
    
    /** 停止要求フラグ */
    private boolean wannaStop = false;
    
    /** データリスナのコレクション */
    private Collection<ByteDataListener> dataListenerCollection = new ArrayList<ByteDataListener>();
    
    /**
     * 受信処理を実行します。
     * 受信したデータは、各データリスナに配布されます。
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
     * 受信処理を終了します。
     *
     */
    public void stopReceiving() {
        this.wannaStop = true;
    }
    
    /**
     * パケットのデータを、各データリスナに分配します。
     * 
     * @param data パケットのデータ
     */
    private void distributeDataToListener(byte[] data) {
        for (ByteDataListener listener : dataListenerCollection) {
            listener.listen(data);
        }
    }
    
    /**
     * マルチキャストリスナを追加します。
     * 
     * @param listener データトリスナ
     */
    public synchronized void addByteDataListener(ByteDataListener listener) {
        this.dataListenerCollection.add(listener);
    }
    
    /**
     * データリスナを全て消去します。
     *
     */
    public synchronized void clearDataListener() {
        this.dataListenerCollection.clear();
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

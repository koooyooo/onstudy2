/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/AbstractReceiver.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/04/19 07:33:20 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.util.ArrayList;
import java.util.Collection;

import org.onproject.onstudy.net.listener.ByteDataListener;

/**
 * 抽象受信クラス
 * 
 * @author 恩田 好庸
 */
public abstract class AbstractReceiver {
    
    /** ポート番号 */
    protected int port;
    
    /** 停止要求フラグ */
    protected boolean wannaStop = false;
    
    /** データリスナのコレクション */
    protected Collection<ByteDataListener> dataListenerCollection = new ArrayList<ByteDataListener>();
    
    /**
     * 受信処理を実行します。
     * 受信したデータは、各データリスナに配布されます。
     * 
     */
    public void startReceiving() {
        this.wannaStop = false;
        this.doStartReceiving();
    }
    
    /**
     * 受信実処理を実行します。
     * 
     * 内部的に、取得したデータを distributeDataToListener で配布する必要があります。
     */
    protected abstract void doStartReceiving();
    
    /**
     * パケットのデータを、各データリスナに分配します。
     * 
     * @param data パケットのデータ
     */
    protected void distributeDataToListener(byte[] data) {
        for (ByteDataListener listener : this.dataListenerCollection) {
            listener.listen(data);
        }
    }
    
    /**
     * マルチキャストリスナを追加します。
     * 
     * @param listener データトリスナ
     */
    public void addByteDataListener(ByteDataListener listener) {
        this.dataListenerCollection.add(listener);
    }
    
    /**
     * データリスナを全て消去します。
     *
     */
    public void clearDataListener() {
        this.dataListenerCollection.clear();
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
     * 受信処理を終了します。
     *
     */
    public void stopReceiving() {
        this.wannaStop = true;
    }
}

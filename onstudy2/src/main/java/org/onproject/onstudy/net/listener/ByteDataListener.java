/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/ByteDataListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/04/16 08:44:18 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

/**
 * バイトデータのリスナ
 * 
 * @author 恩田 好庸
 */
public interface ByteDataListener {
    
    /**
     * バイトデータを知覚します。
     * 
     * @param data バイトデータ
     */
    public void listen(byte[] data);
    
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/ByteDataListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

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

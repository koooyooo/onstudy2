/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/ByteDataListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/04/16 08:44:18 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

/**
 * �o�C�g�f�[�^�̃��X�i
 * 
 * @author ���c �D�f
 */
public interface ByteDataListener {
    
    /**
     * �o�C�g�f�[�^��m�o���܂��B
     * 
     * @param data �o�C�g�f�[�^
     */
    public void listen(byte[] data);
    
}

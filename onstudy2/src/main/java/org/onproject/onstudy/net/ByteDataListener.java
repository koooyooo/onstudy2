/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/ByteDataListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

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

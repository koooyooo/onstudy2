/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/listener/MemberPacketListener.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/05 07:25:09 $
******************************************************************************/
package org.onproject.onstudy.net.listener;

import org.onproject.onstudy.net.data.MemberPacket;

/**
 * �����o�[���X�i
 * 
 * @author ���c �D�f
 */
public interface MemberPacketListener {

    /**
     * �����o�[�p�P�b�g�f�[�^��m�o���܂��B
     * 
     * @param data �����o�[�p�P�b�g�f�[�^
     */
    public void listen(MemberPacket data);
    
}

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
 * UDP�}���`�L���X�g�̎�M��
 *
 * @author ���c �D�f
 */
public class UnicastReceiver extends AbstractReceiver {
    
    /** ��M�o�b�t�@�T�C�Y */
    protected int bufferSize = 65507;
    
    /**
     * �R���X�g���N�^
     *
     */
    public UnicastReceiver() {
        super.port = 10000;
    }
    
    /**
     * ��M���������s���܂��B
     * ��M�����f�[�^�́A�e�f�[�^���X�i�ɔz�z����܂��B
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
     * ��M�o�b�t�@�̃T�C�Y���擾���܂��B
     * 
     * @return ��M�o�b�t�@�̃T�C�Y
     */
    public int getBufferSize() {
        return this.bufferSize;
    }

    /**
     * ��M�o�b�t�@�̃T�C�Y��ݒ肵�܂��B
     * 
     * @param bufferSize ��M�o�b�t�@�̃T�C�Y
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
        
}

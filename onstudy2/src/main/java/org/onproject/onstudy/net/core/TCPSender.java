/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/TCPSender.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/04/19 09:15:20 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.onproject.onstudy.exception.SystemException;

/**
 * TCP�x�[�X�̑��M�N���X
 * 
 * @author ���c �D�f
 */
public class TCPSender {
    
    /** �^�C���A�E�g�~���b */
    private int timeoutMillisec;
    
    /**
     * ���M�������s���܂��B
     * 
     * @param data ���M�f�[�^
     */
    public void send(String ip, int port, byte[] data) {
        Socket socket = null;
        BufferedOutputStream bos = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeoutMillisec);
            bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(data, 0, data.length);
            bos.flush();
            
            socket.shutdownOutput();
        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ignore) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception ignore) {
                }
            }

        }
    }
    
    /**
     * �^�C���A�E�g�~���b��ݒ肵�܂��B
     * 
     * @param timeoutMillisec �^�C���A�E�g�~���b
     */
    public void setTimeoutMillisec(int timeoutMillisec) {
        this.timeoutMillisec = timeoutMillisec;
    }

}

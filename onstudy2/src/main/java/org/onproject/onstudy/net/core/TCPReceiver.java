/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/TCPReceiver.java,v $
  Version : $Revision: 1.4 $
  Date    : $Date: 2007/04/19 07:33:20 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.onproject.onstudy.exception.SystemException;

/**
 * TCP�ʐM�𗘗p�������V�[�o
 * 
 * @author ���c �D�f
 */
public class TCPReceiver extends AbstractReceiver {

    /**
     * ��M���������s���܂��B
     * ��M�����f�[�^�́A�e�f�[�^���X�i�ɔz�z����܂��B
     */
    @Override
    public synchronized void doStartReceiving() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (!this.wannaStop) {
                Socket socket = serverSocket.accept();
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                IOUtils.copy(socket.getInputStream(), baos);
                byte[] byteContent = baos.toByteArray();
                
                super.distributeDataToListener(byteContent);
            }

        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ignore) {
                }
            }
        }

    }

}

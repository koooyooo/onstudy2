/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/Main.java,v $
  Version : $Revision: 1.7 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;

/**
 * �}���`�L���X�g�̃T���v��
 * 
 * @author ���c �D�f
 */
public class Main {
    
    /**
     * �����̑����ƂȂ�main���\�b�h
     * 
     * @param args �v���O���������i���p�����j
     * @throws Exception ��O
     */
    public static void main(String[] args) throws Exception {
        new Thread(new MulticastSenderRunnable()).start();
        new Thread(new MulticastReceiverRunnable()).start();
    }
    
    /**
     * �}���`�L���X�g���M
     * 
     * @author ���c �D�f
     */
    private static class MulticastSenderRunnable implements Runnable {
        
        /**
         * ���������s���܂��B
         */
        public void run() {
            
            MulticastSender sender = new MulticastSender();
            
            while (true) {
                try {
                    Member host = new Member();
                    host.setIp(InetAddress.getLocalHost().getHostAddress());
                    host.setPort(10003);
                    
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(host);
                    
                    sender.send(bos.toByteArray());
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            
        }
    }
    
    /**
     * �}���`�L���X�g��M
     * 
     * @author ���c �D�f
     */
    private static class MulticastReceiverRunnable implements Runnable {
        
        /**
         * ���������s���܂��B
         */
        public void run() {
            MulticastReceiver receiver = new MulticastReceiver();
            receiver.addByteDataListener(new ByteDataListener() {
                
                final Set<Member> memberSet = new HashSet<Member>();
                
                public void listen(byte[] data) {
                    try {
                        ByteArrayInputStream bis = new ByteArrayInputStream(data);
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        
                        Member member = (Member) ois.readObject();
                        memberSet.add(member);
                        
                        System.out.println(memberSet);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver.startReceiving();
        }
    }
    
}

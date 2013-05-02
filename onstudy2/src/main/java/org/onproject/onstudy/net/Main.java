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
 * マルチキャストのサンプル
 * 
 * @author 恩田 好庸
 */
public class Main {
    
    /**
     * 処理の窓口となるmainメソッド
     * 
     * @param args プログラム引数（利用せず）
     * @throws Exception 例外
     */
    public static void main(String[] args) throws Exception {
        new Thread(new MulticastSenderRunnable()).start();
        new Thread(new MulticastReceiverRunnable()).start();
    }
    
    /**
     * マルチキャスト送信
     * 
     * @author 恩田 好庸
     */
    private static class MulticastSenderRunnable implements Runnable {
        
        /**
         * 処理を実行します。
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
     * マルチキャスト受信
     * 
     * @author 恩田 好庸
     */
    private static class MulticastReceiverRunnable implements Runnable {
        
        /**
         * 処理を実行します。
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

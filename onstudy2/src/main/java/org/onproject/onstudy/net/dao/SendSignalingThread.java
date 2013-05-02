/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/dao/SendSignalingThread.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/20 05:22:43 $
******************************************************************************/
package org.onproject.onstudy.net.dao;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import org.onproject.onstudy.net.MulticastSender;
import org.onproject.onstudy.net.data.Member;

/**
 * 存在シグナル送信スレッド
 * 
 * @author 恩田 好庸
 */
public class SendSignalingThread extends Thread {
    
    /** マルチキャスト送信 */
    private final MulticastSender multicastSender = new MulticastSender();
    
    /** 停止要求フラグ */
    private boolean wannaStop = false;
    
    /** 送信シグナルの送信間隔 */
    private int signalingIntervalMilliSec = 1000;
    
    /** ユーザ名 */
    private String userName;
    
    /** ユーザポート */
    private int userPort;
    
    /**
     * 通知をします。
     */
    public void run() {
        try {
            Member mine = new Member();
            mine.setName(userName);
            mine.setIp(InetAddress.getLocalHost().getHostAddress());
            mine.setPort(userPort);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(mine);
            oos.flush();
            
            while (!wannaStop) {
                multicastSender.send(bos.toByteArray());
                try {
                    Thread.sleep(signalingIntervalMilliSec);
                } catch (InterruptedException ignore) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
    }
    
    /**
     * 停止要求を出します。
     *
     */
    public void wannaStop() {
        this.wannaStop = true;
    }
    
    /**
     * 存在シグナルの送信間隔を㍉秒で指定します。
     * 
     * @param signalingIntervalMilliSec 存在シグナルの送信間隔
     */
    public void setSignalingIntervalMilliSec(int signalingIntervalMilliSec) {
        this.signalingIntervalMilliSec = signalingIntervalMilliSec;
    }
    
    /**
     * ユーザ名を設定します。
     * 
     * @param userName ユーザ名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * ユーザの利用するポートを設定します。
     * 
     * @param userPort ユーザの利用するポート
     */
    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }
}
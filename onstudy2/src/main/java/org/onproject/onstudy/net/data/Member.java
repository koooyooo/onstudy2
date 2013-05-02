/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/Member.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/05/17 04:28:19 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;

/**
 * ネットワーク上のメンバ
 * 
 * @author 恩田 好庸
 */
public class Member implements Serializable {
    
    /** シリアルバージョンID */
    private static final long serialVersionUID = 6100463110263518409L;

    /** 名前 */
    public String name;
    
    /** IP アドレス */
    private String ip = "224.0.1.1";
    
    /** ポート番号 */
    private int port = 10000;
    
    /**
     * 名前を取得します。
     * 
     * @return 名前
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 名前を設定します。
     * 
     * @param name 名前
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * IPアドレスを取得します。
     * 
     * @return IPアドレス
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * IPアドレスを設定します。
     * 
     * @param ip IPアドレス
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * ポート番号を取得します。
     * 
     * @return ポート番号
     */
    public int getPort() {
        return this.port;
    }

    /**
     * ポート番号を設定します。
     * 
     * @param port ポート番号
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * 当インスタンスの文字列表現です。
     * 
     * @return 当インスタンスの文字列表現
     */
    public String toString() {
        return "NAME=[" + this.name + "] IP=[" + this.ip + "] PORT=[" + this.port + "]";
    }

    /**
     * ハッシュコードを取得します。
     * 
     * @return ハッシュコード
     */
    public int hashCode() {
        return this.ip.hashCode() + this.port + 3312;
    }

    /**
     * 同値であるかを判定します。
     * 
     * @param 判定対象のオブジェクト
     * @return 同値であれば true
     */
    public boolean equals(Object obj) {
        return obj != null 
                && obj instanceof Member 
                && this.ip.equals(((Member) obj).getIp())
                && this.port == ((Member) obj).getPort();
    }
}

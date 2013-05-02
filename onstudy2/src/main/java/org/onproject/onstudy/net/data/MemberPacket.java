/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/MemberPacket.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;

/**
 * メンバー通信パケット
 * 
 * @author 恩田 好庸
 */
public class MemberPacket implements Serializable {
    
    /** シリアルバージョンID */
    private static final long serialVersionUID = -5884495297776047237L;
    
    /** 送信者 */
    private Member caller;
    
    /** 返信者 */
    private Member responsdent;

    /**
     * 送信者を取得します。
     * 
     * @return 送信者
     */
    public Member getCaller() {
        return this.caller;
    }

    /**
     * 送信者を設定します。
     * 
     * @param caller 送信者
     */
    public void setCaller(Member caller) {
        this.caller = caller;
    }

    /**
     * 返信者を取得します。
     * 
     * @return 返信者
     */
    public Member getResponsdent() {
        return this.responsdent;
    }

    /**
     * 返信者を設定します。
     * 
     * @param responsdent 返信者
     */
    public void setResponsdent(Member responsdent) {
        this.responsdent = responsdent;
    }
    
    
    
    
}

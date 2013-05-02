/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/MemberPacket.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/06/05 07:25:10 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;

/**
 * �����o�[�ʐM�p�P�b�g
 * 
 * @author ���c �D�f
 */
public class MemberPacket implements Serializable {
    
    /** �V���A���o�[�W����ID */
    private static final long serialVersionUID = -5884495297776047237L;
    
    /** ���M�� */
    private Member caller;
    
    /** �ԐM�� */
    private Member responsdent;

    /**
     * ���M�҂��擾���܂��B
     * 
     * @return ���M��
     */
    public Member getCaller() {
        return this.caller;
    }

    /**
     * ���M�҂�ݒ肵�܂��B
     * 
     * @param caller ���M��
     */
    public void setCaller(Member caller) {
        this.caller = caller;
    }

    /**
     * �ԐM�҂��擾���܂��B
     * 
     * @return �ԐM��
     */
    public Member getResponsdent() {
        return this.responsdent;
    }

    /**
     * �ԐM�҂�ݒ肵�܂��B
     * 
     * @param responsdent �ԐM��
     */
    public void setResponsdent(Member responsdent) {
        this.responsdent = responsdent;
    }
    
    
    
    
}

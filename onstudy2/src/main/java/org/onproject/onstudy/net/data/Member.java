/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/data/Member.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/05/17 04:28:19 $
******************************************************************************/
package org.onproject.onstudy.net.data;

import java.io.Serializable;

/**
 * �l�b�g���[�N��̃����o
 * 
 * @author ���c �D�f
 */
public class Member implements Serializable {
    
    /** �V���A���o�[�W����ID */
    private static final long serialVersionUID = 6100463110263518409L;

    /** ���O */
    public String name;
    
    /** IP �A�h���X */
    private String ip = "224.0.1.1";
    
    /** �|�[�g�ԍ� */
    private int port = 10000;
    
    /**
     * ���O���擾���܂��B
     * 
     * @return ���O
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * ���O��ݒ肵�܂��B
     * 
     * @param name ���O
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * IP�A�h���X���擾���܂��B
     * 
     * @return IP�A�h���X
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * IP�A�h���X��ݒ肵�܂��B
     * 
     * @param ip IP�A�h���X
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * �|�[�g�ԍ����擾���܂��B
     * 
     * @return �|�[�g�ԍ�
     */
    public int getPort() {
        return this.port;
    }

    /**
     * �|�[�g�ԍ���ݒ肵�܂��B
     * 
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * ���C���X�^���X�̕�����\���ł��B
     * 
     * @return ���C���X�^���X�̕�����\��
     */
    public String toString() {
        return "NAME=[" + this.name + "] IP=[" + this.ip + "] PORT=[" + this.port + "]";
    }

    /**
     * �n�b�V���R�[�h���擾���܂��B
     * 
     * @return �n�b�V���R�[�h
     */
    public int hashCode() {
        return this.ip.hashCode() + this.port + 3312;
    }

    /**
     * ���l�ł��邩�𔻒肵�܂��B
     * 
     * @param ����Ώۂ̃I�u�W�F�N�g
     * @return ���l�ł���� true
     */
    public boolean equals(Object obj) {
        return obj != null 
                && obj instanceof Member 
                && this.ip.equals(((Member) obj).getIp())
                && this.port == ((Member) obj).getPort();
    }
}

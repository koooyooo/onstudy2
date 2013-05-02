/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/core/AbstractReceiver.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/04/19 07:33:20 $
******************************************************************************/
package org.onproject.onstudy.net.core;

import java.util.ArrayList;
import java.util.Collection;

import org.onproject.onstudy.net.listener.ByteDataListener;

/**
 * ���ێ�M�N���X
 * 
 * @author ���c �D�f
 */
public abstract class AbstractReceiver {
    
    /** �|�[�g�ԍ� */
    protected int port;
    
    /** ��~�v���t���O */
    protected boolean wannaStop = false;
    
    /** �f�[�^���X�i�̃R���N�V���� */
    protected Collection<ByteDataListener> dataListenerCollection = new ArrayList<ByteDataListener>();
    
    /**
     * ��M���������s���܂��B
     * ��M�����f�[�^�́A�e�f�[�^���X�i�ɔz�z����܂��B
     * 
     */
    public void startReceiving() {
        this.wannaStop = false;
        this.doStartReceiving();
    }
    
    /**
     * ��M�����������s���܂��B
     * 
     * �����I�ɁA�擾�����f�[�^�� distributeDataToListener �Ŕz�z����K�v������܂��B
     */
    protected abstract void doStartReceiving();
    
    /**
     * �p�P�b�g�̃f�[�^���A�e�f�[�^���X�i�ɕ��z���܂��B
     * 
     * @param data �p�P�b�g�̃f�[�^
     */
    protected void distributeDataToListener(byte[] data) {
        for (ByteDataListener listener : this.dataListenerCollection) {
            listener.listen(data);
        }
    }
    
    /**
     * �}���`�L���X�g���X�i��ǉ����܂��B
     * 
     * @param listener �f�[�^�g���X�i
     */
    public void addByteDataListener(ByteDataListener listener) {
        this.dataListenerCollection.add(listener);
    }
    
    /**
     * �f�[�^���X�i��S�ď������܂��B
     *
     */
    public void clearDataListener() {
        this.dataListenerCollection.clear();
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
     * ��M�������I�����܂��B
     *
     */
    public void stopReceiving() {
        this.wannaStop = true;
    }
}

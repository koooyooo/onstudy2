/******************************************************************************
 Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/UI.java,v $
 Version : $Revision: 1.2 $
 Date    : $Date: 2007/04/19 07:33:21 $
 ******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ���UI�C���^�[�t�F�C�X
 * 
 * @author ���c �D�f
 */
public interface UI {
    
    /**
     * �������������s���܂��B<BR>
     *
     */
    public void init(JFrame frame);
    
    /**
     * �t���[���R���e�L�X�g���擾���܂��B<BR>
     * 
     * @return �t���[���R���e�L�X�g
     */
    public FrameContext getFrameContext();
    
    /**
     * �p�l�����擾���܂��B
     * 
     * @return �擾�����p�l��
     */
    public abstract JPanel getPanel();

    /**
     * ���̂ɑΉ������R���|�[�l���g���擾���܂��B<BR>
     * 
     * @param componentName
     * @return
     */
    public abstract Component getComponent(String componentName);

    /**
     * �R���|�[�l���g�̔����q���擾���܂��B<BR>
     * 
     * @return �R���|�[�l���g�̔����q
     */
    public abstract Iterator<Component> componentIterator();

}
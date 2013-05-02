/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/FrameContext.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/04/19 07:33:21 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;

import org.onproject.onstudy.ui.swing.common.UIUtils;

/**
 * �t���[���̑�����s���N���X
 * 
 * @author ���c �D�f
 */
public class FrameContext {
    
    /** �t���[�� */
    private final JFrame frame;
    
    /**
     * �R���X�g���N�^
     * 
     * @param frame �t���[��
     */
    public FrameContext(JFrame frame) {
        this.frame = frame;
    }
    
    /**
     * �t���[�����擾���܂��B
     * 
     * @return �t���[��
     */
    public JFrame getFrame() {
        return this.frame;
    }
    
    /**
     * �t���[���̃R���|�[�l���g���擾���܂��B
     * 
     * @param componentName �R���|�[�l���g��
     * @return �t���[���̃R���|�[�l���g
     */
    public Component getFrameComponent(String componentName) {
        for (Component eachComponent : UIUtils.getAllComponentList(this.frame)) {
            if (componentName.equals(eachComponent.getName())) {
                return eachComponent;
            }
        }
        return null;
    }
    
    /**
     * �t���[���R���|�[�l���g�̔����q���擾���܂��B
     * 
     * @return �t���[���R���|�[�l���g�̔����q
     */
    public Iterator<Component> frameComponentIterator() {
        return UIUtils.getAllComponentList(this.frame).iterator();
    }
    
}

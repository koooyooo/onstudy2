/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/ui/AbstractUI.java,v $
  Version : $Revision: 1.2 $
  Date    : $Date: 2007/04/19 07:33:21 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.ui;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.onproject.onstudy.ui.swing.common.UIUtils;

/**
 * ����UI�N���X
 * 
 * @author ���c �D�f
 */
public abstract class AbstractUI implements UI {
    
    /** �t���[�� */
    private JFrame frame;
    
    /** �p�l�� */
    private JPanel panel;
    
    /**
     * ���������s���܂��B
     *
     */
    public void init(JFrame frame) {
        this.frame = frame;
        this.panel = createPanel();
    }
    
    /**
     * �t���[���R���e�L�X�g���擾���܂��B
     * 
     * @return �t���[���R���e�L�X�g
     */
    public FrameContext getFrameContext() {
        return new FrameContext(this.frame);
    }
    
    /**
     * �p�l�����쐬���܂��B
     * 
     * @return �쐬�����p�l��
     */
    protected abstract JPanel createPanel(); // TODO �ϐ��ɓ����^�C�~���O����肾�B
    
    /**
     * �p�l�����擾���܂��B
     * 
     * @return �擾�����p�l��
     */
    public JPanel getPanel() {
        return panel;
    }
    
    /**
     * ���̂ɑΉ������R���|�[�l���g���擾���܂��B<BR>
     * 
     * @param componentName
     * @return
     */
    public Component getComponent(String componentName) {
        for (Component eachComponent : UIUtils.getAllComponentList(this.panel)) {
            if (componentName.equals(eachComponent.getName())) {
                return eachComponent;
            }
        }
        return null;
    }
    
    /**
     * �R���|�[�l���g�̔����q���擾���܂��B<BR>
     * 
     * @return �R���|�[�l���g�̔����q
     */
    public Iterator<Component> componentIterator() { 
        return UIUtils.getAllComponentList(this.panel).iterator();
    }
    
    /**
     * ������\�����擾���܂��B<BR>
     * 
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator componentIterator = this.componentIterator(); componentIterator.hasNext(); ) {
            Component eachComponent = componentIterator().next();
            sb.append(eachComponent.getName() + "(" + eachComponent.getClass().getSimpleName() + ")");
            if (componentIterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

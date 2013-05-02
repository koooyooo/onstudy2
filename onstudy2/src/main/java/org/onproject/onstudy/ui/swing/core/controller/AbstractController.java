/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/core/controller/AbstractController.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/21 06:16:59 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.core.controller;

import java.awt.Component;
import java.util.Iterator;

import org.onproject.onstudy.ui.swing.core.listener.ReflectMouseListener;
import org.onproject.onstudy.ui.swing.core.ui.FrameContext;
import org.onproject.onstudy.ui.swing.core.ui.UI;

/**
 * ���ۃR���g���[��
 * 
 * �ȉ��̐Ӗ��𕉂��܂��B
 * 
 * �P�j���������ɁA�Ή�����UI�̑S�ẴR���|�[�l���g�ɔ������X�i�𖄂ߍ��݂܂��B
 * �Q�j���O���w�肷��ƃR���|�[�l���g���擾�ł���@�\��p�ӂ��܂��B
 * 
 * @author ���c �D�f
 */
public abstract class AbstractController implements Controller {
    
    /**
     * �����������ł��B
     * 
     */
    public void init() {
        this.addReflectListeners();
        this.initialize();
    }
    
    /**
     * ���[�h�����ł��B
     * 
     */
    public void load() {
    	this.reload();
    }
    
    /**
     * �S�ẴR���|�[�l���g�ɔ������X�i��ǉ����܂��B
     * 
     */
    private void addReflectListeners() {
        for (Iterator<Component> componentIte = this.getTargetUI().componentIterator(); componentIte.hasNext(); ) {
            Component component = componentIte.next();
            this.addListener(component);
        }
    }
    
    /**
     * �w�肳�ꂽ�R���|�[�l���g�ɁA�������X�i��ǉ����܂��B
     * 
     * @param component �Ώۂ̃R���|�[�l���g
     */
    private void addListener(Component component) {
        component.addMouseListener(new ReflectMouseListener(this));
    }
    
    /**
     * �R���|�[�l���g���擾���܂��B<BR>
     * 
     * @param componentName �R���|�[�l���g��
     */
    protected Component getComponent(String componentName) {
        return this.getTargetUI().getComponent(componentName);
    }
    
    /**
     * �t���[���R���e�L�X�g���擾���܂��B
     * 
     * @return �t���[���R���e�L�X�g
     */
    protected FrameContext getFrameContext() {
        return this.getTargetUI().getFrameContext();
    }
    
    /**
     * �Ή�����UI���擾���܂��B<BR>
     * 
     * @return �Ή�����UI
     */
    protected abstract UI getTargetUI();
    
    /**
     * �����������s���܂��B<BR>
     *
     */
    protected void initialize() {
        this.reload();
    }
    
    /**
     * ��������̍ēǂݍ��ݏ������s���܂��B
     * 
     */
    protected abstract void reload();
    
}

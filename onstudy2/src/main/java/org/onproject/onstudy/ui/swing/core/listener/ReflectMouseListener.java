package org.onproject.onstudy.ui.swing.core.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

/**
 * �ΏۂƂȂ�I�u�W�F�N�g�̓���̃��\�b�h���R�[������锽���}�E�X���X�i�B
 * 
 * @author ���c �D�f
 */
public class ReflectMouseListener implements MouseListener {
    
    /** ����ΏۂƂȂ�I�u�W�F�N�g */
    private final Object targetObj;
    
    /**
     * �R���X�g���N�^
     * 
     * @param targetObj ����ΏۂƂȂ�I�u�W�F�N�g
     */
    public ReflectMouseListener(Object targetObj) {
        this.targetObj = targetObj;
    }
    
    /**
     * �}�E�X�N���b�N�����m���܂��B
     * 
     * @param event �C�x���g
     */
	public void mouseClicked(MouseEvent event) {
        this.reflectEvent("mouseClicked", event);
	}
	
    /**
     * �}�E�X���������m���܂��B
     * 
     * @param event �C�x���g
     */
	public void mousePressed(MouseEvent event) {
        this.reflectEvent("mousePressed", event);
	}
	
    /**
     * �}�E�X�����[�X�����m���܂��B
     * 
     * @param event �C�x���g
     */
	public void mouseReleased(MouseEvent event) {
        this.reflectEvent("mouseReleased", event);
	}
    
	/**
     * �}�E�X�N�������m���܂��B
     * 
     * @param event �C�x���g
	 */
	public void mouseEntered(MouseEvent event) {
        this.reflectEvent("mouseEntered", event);
	}

    /**
     * �}�E�X�ޏo�����m���܂��B
     * 
     * @param event �C�x���g
     */
	public void mouseExited(MouseEvent event) {
        this.reflectEvent("mouseExited", event);
	}
    
    /**
     * ���m�����C�x���g���A�ڕW�̃I�u�W�F�N�g�ɓ`�d���܂��B
     * ���\�b�h�����ueventPrefix + targetObject�v������MouseEvent ��
     * ���\�b�h���R�[������܂��B
     * 
     * @param eventPrefix �C�x���g���̐ړ���
     * @param event �C�x���g
     */
    private void reflectEvent(String eventPrefix, MouseEvent event) {
        if (event.getComponent().getName() == null) {
            return;
        }
        final String invokeMethodName = this.createMethodName(eventPrefix, event.getComponent());
        try {
            Class targetClass = targetObj.getClass();
            
            for (Method method = null; targetClass != null; targetClass = targetClass.getSuperclass()) {
            	method = getTargetMethod(invokeMethodName, targetClass);
            	if (method != null) {
                    method.invoke(targetObj, new Object[]{event});
                    break;
            	}
            }

        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
    }
    
    /**
     * �Y�����\�b�h��T���܂��B
     * 
     * �i���j���\�b�h�𖼎w���Ŏw�肷��ƁAInvokationTargetException�������B
     * �@�@�@�Y�����\�b�h�������ꍇ�͑����̂Łi�f�t�H���g�ł͖����j���ł�
     *       ��O�𔭐����Ȃ��l�ɓ����\�b�h�𗘗p����B
     * 
     * @param clazz �Ώۂ̃N���X
     * @return �Y�����\�b�h�A������� nulls
     */
    private Method getTargetMethod(String methodName, Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method eachMethod = methods[i];
            if (!eachMethod.getName().equals(methodName)) {
                continue;
            }
            if (eachMethod.getParameterTypes().length != 1) {
                continue;
            }
            if (!eachMethod.getParameterTypes()[0].equals(MouseEvent.class)) {
                continue;
            }
            return eachMethod;
        }
        return null;
    }

    /**
     * �C�x���g�� + �R���|�[�l���g���i�擪�啶���j�ŁA���\�b�h���𐶐����܂��B<BR>
     * 
     * @param eventPrefix �C�x���g��
     * @param component �R���|�[�l���g
     * @return ���\�b�h��
     */
    private String createMethodName(String eventPrefix, Component component) {
        return component.getName() + StringUtils.capitalize(eventPrefix);
    }

}

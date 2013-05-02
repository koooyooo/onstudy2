package org.onproject.onstudy.ui.swing.core.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

/**
 * 対象となるオブジェクトの特定のメソッドがコールされる反響マウスリスナ。
 * 
 * @author 恩田 好庸
 */
public class ReflectMouseListener implements MouseListener {
    
    /** 操作対象となるオブジェクト */
    private final Object targetObj;
    
    /**
     * コンストラクタ
     * 
     * @param targetObj 操作対象となるオブジェクト
     */
    public ReflectMouseListener(Object targetObj) {
        this.targetObj = targetObj;
    }
    
    /**
     * マウスクリックを感知します。
     * 
     * @param event イベント
     */
	public void mouseClicked(MouseEvent event) {
        this.reflectEvent("mouseClicked", event);
	}
	
    /**
     * マウス押下を感知します。
     * 
     * @param event イベント
     */
	public void mousePressed(MouseEvent event) {
        this.reflectEvent("mousePressed", event);
	}
	
    /**
     * マウスリリースを感知します。
     * 
     * @param event イベント
     */
	public void mouseReleased(MouseEvent event) {
        this.reflectEvent("mouseReleased", event);
	}
    
	/**
     * マウス侵入を感知します。
     * 
     * @param event イベント
	 */
	public void mouseEntered(MouseEvent event) {
        this.reflectEvent("mouseEntered", event);
	}

    /**
     * マウス退出を感知します。
     * 
     * @param event イベント
     */
	public void mouseExited(MouseEvent event) {
        this.reflectEvent("mouseExited", event);
	}
    
    /**
     * 感知したイベントを、目標のオブジェクトに伝播します。
     * メソッド名が「eventPrefix + targetObject」引数がMouseEvent の
     * メソッドがコールされます。
     * 
     * @param eventPrefix イベント毎の接頭語
     * @param event イベント
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
     * 該当メソッドを探します。
     * 
     * （注）メソッドを名指しで指定すると、InvokationTargetExceptionが発生。
     * 　　　該当メソッドが無い場合は多いので（デフォルトでは無い）無闇に
     *       例外を発生しない様に当メソッドを利用する。
     * 
     * @param clazz 対象のクラス
     * @return 該当メソッド、無ければ nulls
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
     * イベント名 + コンポーネント名（先頭大文字）で、メソッド名を生成します。<BR>
     * 
     * @param eventPrefix イベント名
     * @param component コンポーネント
     * @return メソッド名
     */
    private String createMethodName(String eventPrefix, Component component) {
        return component.getName() + StringUtils.capitalize(eventPrefix);
    }

}

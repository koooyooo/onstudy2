package org.onproject.onstudy.ui.swing.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * UI�Ɋւ��郆�[�e�B���e�B
 * 
 * @author ���c �D�f
 */
public class UIUtils {

    /**
     * �`�F�b�N�l�𔽓]���܂��B
     * 
     * @param table �ΏۂƂȂ�e�[�u��
     * @param checkBoxColumnIndex �`�F�b�N�{�b�N�X�J�����̃C���f�b�N�X�i�O�J�n�j
     */
    public static void reverseCheckValue(JTable table, int checkBoxColumnIndex) {
        final int CHECK_BOX_COLUMN_INDEX = 0;
        if (table.getSelectedColumn() == CHECK_BOX_COLUMN_INDEX) {
            return;
        }
        final int[] selectedRows = table.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            final int selectedRow = selectedRows[i];
            Boolean currentValue = (Boolean) table.getValueAt(selectedRow, CHECK_BOX_COLUMN_INDEX);
            Boolean nextValue = (currentValue.booleanValue()) ? Boolean.FALSE : Boolean.TRUE;
            table.setValueAt(nextValue, selectedRow, CHECK_BOX_COLUMN_INDEX);            
        }
    }
    
    /**
     * �S�ẴR���|�[�l���g�̃��X�g���擾���܂��B
     * 
     * @param container �R���e�i
     * @return �S�ẴR���|�[�l���g�̃��X�g
     */
    public static List<Component> getAllComponentList(Container container) {
        List<Component> componentList = new ArrayList<Component>();
        Component[] components = container.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            componentList.add(component);
            if (component instanceof Container) {
                componentList.addAll(getAllComponentList((Container) component)); 
            }
        }
        return componentList;
    }
    
    /**
     * ���x���J���[��ݒ肵�܂��B
     * 
     * @param label ���x��
     */
    public static void setLabelColor(JLabel label) {
        label.setOpaque(true);
//        label.setBackground(new Color(230, 255, 230));
        label.setBackground(new Color(210, 235, 210));
    }
	
}

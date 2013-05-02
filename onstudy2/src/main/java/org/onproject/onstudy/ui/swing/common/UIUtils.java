package org.onproject.onstudy.ui.swing.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * UIに関するユーティリティ
 * 
 * @author 恩田 好庸
 */
public class UIUtils {

    /**
     * チェック値を反転します。
     * 
     * @param table 対象となるテーブル
     * @param checkBoxColumnIndex チェックボックスカラムのインデックス（０開始）
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
     * 全てのコンポーネントのリストを取得します。
     * 
     * @param container コンテナ
     * @return 全てのコンポーネントのリスト
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
     * ラベルカラーを設定します。
     * 
     * @param label ラベル
     */
    public static void setLabelColor(JLabel label) {
        label.setOpaque(true);
//        label.setBackground(new Color(230, 255, 230));
        label.setBackground(new Color(210, 235, 210));
    }
	
}

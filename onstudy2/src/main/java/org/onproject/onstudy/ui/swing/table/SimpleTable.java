package org.onproject.onstudy.ui.swing.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 単純なJTableの拡張
 * 
 * @author 恩田 好庸
 */
public class SimpleTable extends JTable {

	/** シリアルバージョンID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ
	 * 
	 * @param model テーブルモデル
	 */
	public SimpleTable(SimpleTableModel model) {
		super(model);
	}
    
    /**
     * シンプルテーブルモデルを取得します。<BR>
     * 
     * @return シンプルテーブルモデル
     */
    public SimpleTableModel getSimpleTableModel() {
        return (SimpleTableModel) super.getModel();
    }
	
	/** 
	* 指定されたカラムに格納されているデータの型を返す。 
	* @return 指定されたカラムに格納されているデータの型。 
	*/ 
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		if (this.getRowCount() == 0) {
			return Object.class;
		}
		return (getValueAt(0, c) == null) ? String.class : getValueAt(0, c).getClass();
	}
    
    /**
     * 行を追加します。
     * 
     * @param row 追加する行
     */
    public void addRow(Object[] row) {
        this.getSimpleTableModel().addRow(row);
    }
    
    /**
     * 行を削除します。
     * 
     * @param rowNum 行番号
     */
    public void removeRow(int rowNum) {
        this.getSimpleTableModel().removeRow(rowNum);
    }
    
    /**
     * 全ての行を削除します。
     *
     */
    public void removeAllRow() {
        DefaultTableModel optionTableModel = this.getSimpleTableModel();
        while (this.getRowCount() != 0) {
            optionTableModel.removeRow(0);
        }
    }
	
	/**
	 * 現在選択中のセルを取得します。<BR>
	 * 
	 * @return 現在選択中のセル
	 */
	public Cell getCurrentSelectedCell() {
		return new Cell(super.getSelectedRow(), super.getSelectedColumn());
	}
	
	/**
	 * セルを表現するクラス
	 * 
	 * @author 恩田 好庸
	 */
	private class Cell {
		
		private int row = -1;
		private int column = -1;
		
		public Cell() {
		}
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}
		
		public int getRow() {
			return this.row;
		}
		
		public int getColumn() {
			return this.column;
		}
		
		public boolean equals(Object other) {
			if (other == null) {
				return false;
			}
			if (!(other instanceof Cell)) {
				return false;
			}
			Cell otherCell = (Cell) other;
			if (this.row != otherCell.getRow()) {
				return false;
			}
			if (this.column != otherCell.getColumn()) {
				return false;
			}
			return true;
		}
		
		public int hashCode() {
			return (this.row * 11) + (this.column * 33);
		}
		
		public String toString() {
			return "Cell row=[" + this.row + "] column=[" + this.column + "]";
		}
	}

}

package org.onproject.onstudy.ui.swing.table;

import java.util.HashSet;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

/**
 * 単純なテーブルモデル
 * 
 * @author 恩田 好庸
 */
public class SimpleTableModel extends DefaultTableModel {
	
	/** シリアル番号 */
	private static final long serialVersionUID = 2L;
	
    /** 全て編集不可 */
    private boolean isNonEditableTable = false;
    
	/** 編集不可能な行のセット */
	private Set<Integer> notEditableRowSet = new HashSet<Integer>();
	
	/** 編集不可能な列のセット */
	private Set<Integer> notEditableColSet = new HashSet<Integer>();
	
	/** 編集不可能なセルのセット */
	private Set<NotEditableCell> notEditableCellSet = new HashSet<NotEditableCell>();
	
    /**
     * 全てのセルを編集不可に設定します。
     *
     */
	public void setAllCellNotEditable() {
	    this.isNonEditableTable = true;
    }
	
	/**
	 * 編集不可能な行を追加します。
	 * 
	 * @param row 編集不可能な行
	 */
	public void addNotEditableRow(int row) {
		this.notEditableRowSet.add(new Integer(row));
	}
	
	/**
	 * 編集不可能な列を追加します。
	 * 
	 * @param col 編集不可能な列
	 */
	public void addNotEditableCol(int col) {
		this.notEditableColSet.add(new Integer(col));
	}
	
	/**
	 * 編集不可能なセルを追加します。
	 * 
	 * @param row 対象セルの行
	 * @param col 対象セルの列
	 */
	public void addNotEditableCell(int row, int col) {
		this.notEditableCellSet.add(new NotEditableCell(row, col));
	}
	
	/**
	 * セルが編集可能であるかを判断します。
	 * 
	 * @param row 対象行
	 * @param col 対象列
	 * @return セルが編集可能であれば true
	 */
	public boolean isCellEditable(int row, int col) {
        if (this.isNonEditableTable) {
            return false;
        }
		for (Integer notEditableRow : this.notEditableRowSet) {
			if (notEditableRow.intValue() == row) {
				return false;
			}
		}
		for (Integer notEditableCol : this.notEditableColSet) {
			if (notEditableCol.intValue() == col) {
				return false;
			}
		}
		for (NotEditableCell notEditableCell : this.notEditableCellSet) {
			if (notEditableCell.getTargetRow() == row && notEditableCell.getTargetCol() == col) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 編集不可能なセルを表現するクラス
	 * 
	 * @author 恩田 好庸
	 */
	private static class NotEditableCell {

		/** 対象行 */
		private int targetRow;
		
		/** 対象列 */
		private int targetCol;
		
		/**
		 * コンストラクタ
		 * 
		 * @param targetRow 対象行
		 * @param targetCol 対象列
		 */
		protected NotEditableCell(int targetRow, int targetCol) {
			this.targetRow = targetRow;
			this.targetCol = targetCol;
		}
		
		/**
		 * 対象行を取得します。
		 * 
		 * @return 対象行
		 */
		protected int getTargetRow() {
			return targetRow;
		}
		
		/**
		 * 対象列を取得します。
		 * 
		 * @return 対象列
		 */
		protected int getTargetCol() {
			return targetCol;
		}
		
		/**
		 * 同値であるかを判定します。
		 * 
		 * @param 対象となるオブジェクト
		 * @return 同値であれば true
		 */
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof NotEditableCell)) {
				return false;
			}
			NotEditableCell targetCell = (NotEditableCell) obj;
			if (this.targetRow != targetCell.getTargetRow()) {
				return false;
			}
			if (this.targetCol != targetCell.getTargetCol()) {
				return false;
			}
			return true;
		}
		
		/**
		 * ハッシュコードを取得します。
		 * 
		 * @return ハッシュコード
		 */
		public int hashCode() {
			return this.targetCol + this.targetRow  + 71;
		}
	}

}

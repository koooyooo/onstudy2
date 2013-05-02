package org.onproject.onstudy.ui.swing.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * �P����JTable�̊g��
 * 
 * @author ���c �D�f
 */
public class SimpleTable extends JTable {

	/** �V���A���o�[�W����ID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param model �e�[�u�����f��
	 */
	public SimpleTable(SimpleTableModel model) {
		super(model);
	}
    
    /**
     * �V���v���e�[�u�����f�����擾���܂��B<BR>
     * 
     * @return �V���v���e�[�u�����f��
     */
    public SimpleTableModel getSimpleTableModel() {
        return (SimpleTableModel) super.getModel();
    }
	
	/** 
	* �w�肳�ꂽ�J�����Ɋi�[����Ă���f�[�^�̌^��Ԃ��B 
	* @return �w�肳�ꂽ�J�����Ɋi�[����Ă���f�[�^�̌^�B 
	*/ 
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		if (this.getRowCount() == 0) {
			return Object.class;
		}
		return (getValueAt(0, c) == null) ? String.class : getValueAt(0, c).getClass();
	}
    
    /**
     * �s��ǉ����܂��B
     * 
     * @param row �ǉ�����s
     */
    public void addRow(Object[] row) {
        this.getSimpleTableModel().addRow(row);
    }
    
    /**
     * �s���폜���܂��B
     * 
     * @param rowNum �s�ԍ�
     */
    public void removeRow(int rowNum) {
        this.getSimpleTableModel().removeRow(rowNum);
    }
    
    /**
     * �S�Ă̍s���폜���܂��B
     *
     */
    public void removeAllRow() {
        DefaultTableModel optionTableModel = this.getSimpleTableModel();
        while (this.getRowCount() != 0) {
            optionTableModel.removeRow(0);
        }
    }
	
	/**
	 * ���ݑI�𒆂̃Z�����擾���܂��B<BR>
	 * 
	 * @return ���ݑI�𒆂̃Z��
	 */
	public Cell getCurrentSelectedCell() {
		return new Cell(super.getSelectedRow(), super.getSelectedColumn());
	}
	
	/**
	 * �Z����\������N���X
	 * 
	 * @author ���c �D�f
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

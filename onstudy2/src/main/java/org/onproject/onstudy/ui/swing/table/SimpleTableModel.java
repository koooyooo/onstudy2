package org.onproject.onstudy.ui.swing.table;

import java.util.HashSet;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

/**
 * �P���ȃe�[�u�����f��
 * 
 * @author ���c �D�f
 */
public class SimpleTableModel extends DefaultTableModel {
	
	/** �V���A���ԍ� */
	private static final long serialVersionUID = 2L;
	
    /** �S�ĕҏW�s�� */
    private boolean isNonEditableTable = false;
    
	/** �ҏW�s�\�ȍs�̃Z�b�g */
	private Set<Integer> notEditableRowSet = new HashSet<Integer>();
	
	/** �ҏW�s�\�ȗ�̃Z�b�g */
	private Set<Integer> notEditableColSet = new HashSet<Integer>();
	
	/** �ҏW�s�\�ȃZ���̃Z�b�g */
	private Set<NotEditableCell> notEditableCellSet = new HashSet<NotEditableCell>();
	
    /**
     * �S�ẴZ����ҏW�s�ɐݒ肵�܂��B
     *
     */
	public void setAllCellNotEditable() {
	    this.isNonEditableTable = true;
    }
	
	/**
	 * �ҏW�s�\�ȍs��ǉ����܂��B
	 * 
	 * @param row �ҏW�s�\�ȍs
	 */
	public void addNotEditableRow(int row) {
		this.notEditableRowSet.add(new Integer(row));
	}
	
	/**
	 * �ҏW�s�\�ȗ��ǉ����܂��B
	 * 
	 * @param col �ҏW�s�\�ȗ�
	 */
	public void addNotEditableCol(int col) {
		this.notEditableColSet.add(new Integer(col));
	}
	
	/**
	 * �ҏW�s�\�ȃZ����ǉ����܂��B
	 * 
	 * @param row �ΏۃZ���̍s
	 * @param col �ΏۃZ���̗�
	 */
	public void addNotEditableCell(int row, int col) {
		this.notEditableCellSet.add(new NotEditableCell(row, col));
	}
	
	/**
	 * �Z�����ҏW�\�ł��邩�𔻒f���܂��B
	 * 
	 * @param row �Ώۍs
	 * @param col �Ώۗ�
	 * @return �Z�����ҏW�\�ł���� true
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
	 * �ҏW�s�\�ȃZ����\������N���X
	 * 
	 * @author ���c �D�f
	 */
	private static class NotEditableCell {

		/** �Ώۍs */
		private int targetRow;
		
		/** �Ώۗ� */
		private int targetCol;
		
		/**
		 * �R���X�g���N�^
		 * 
		 * @param targetRow �Ώۍs
		 * @param targetCol �Ώۗ�
		 */
		protected NotEditableCell(int targetRow, int targetCol) {
			this.targetRow = targetRow;
			this.targetCol = targetCol;
		}
		
		/**
		 * �Ώۍs���擾���܂��B
		 * 
		 * @return �Ώۍs
		 */
		protected int getTargetRow() {
			return targetRow;
		}
		
		/**
		 * �Ώۗ���擾���܂��B
		 * 
		 * @return �Ώۗ�
		 */
		protected int getTargetCol() {
			return targetCol;
		}
		
		/**
		 * ���l�ł��邩�𔻒肵�܂��B
		 * 
		 * @param �ΏۂƂȂ�I�u�W�F�N�g
		 * @return ���l�ł���� true
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
		 * �n�b�V���R�[�h���擾���܂��B
		 * 
		 * @return �n�b�V���R�[�h
		 */
		public int hashCode() {
			return this.targetCol + this.targetRow  + 71;
		}
	}

}

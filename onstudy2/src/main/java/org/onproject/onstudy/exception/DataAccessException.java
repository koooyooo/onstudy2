package org.onproject.onstudy.exception;

/**
 * �f�[�^�A�N�Z�X�Ɋւ����O
 * 
 * @author ���c �D�f
 */
public class DataAccessException extends SystemException {
	
	/** �V���A���o�[�W�����ԍ� */
	private static final long serialVersionUID = 2L;

	/**
	 * �R���X�g���N�^
	 *
	 */
	public DataAccessException() {
		super();
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 */
	public DataAccessException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param cause �����ƂȂ�����O
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}

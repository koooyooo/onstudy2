package org.onproject.onstudy.exception;

/**
 * �V�X�e����O�̊��N���X
 * 
 * @author ���c �D�f
 */
public class SystemException extends RuntimeException {
	
	/** �V���A���o�[�W�����ԍ� */
	private static final long serialVersionUID = 1L;

	/**
	 * �R���X�g���N�^
	 *
	 */
	public SystemException() {
		super();
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 */
	public SystemException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param cause �����ƂȂ�����O
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

}

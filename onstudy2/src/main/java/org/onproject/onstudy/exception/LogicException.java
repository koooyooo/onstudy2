package org.onproject.onstudy.exception;

/**
 * ���W�b�N��O
 * 
 * @author ���c �D�f
 */
public class LogicException extends Exception {
	
	/** �V���A���o�[�W�����ԍ� */
	private static final long serialVersionUID = 1L;

	/**
	 * �R���X�g���N�^
	 *
	 */
	public LogicException() {
		super();
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 */
	public LogicException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param cause �����ƂȂ�����O
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}
}

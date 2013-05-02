package org.onproject.onstudy.exception;

/**
 * システム例外の基底クラス
 * 
 * @author 恩田 好庸
 */
public class SystemException extends RuntimeException {
	
	/** シリアルバージョン番号 */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 *
	 */
	public SystemException() {
		super();
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public SystemException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param cause 原因となった例外
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

}

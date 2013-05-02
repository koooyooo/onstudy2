package org.onproject.onstudy.exception;

/**
 * ロジック例外
 * 
 * @author 恩田 好庸
 */
public class LogicException extends Exception {
	
	/** シリアルバージョン番号 */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 *
	 */
	public LogicException() {
		super();
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public LogicException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param cause 原因となった例外
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}
}

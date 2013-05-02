package org.onproject.onstudy.exception;

/**
 * データアクセスに関する例外
 * 
 * @author 恩田 好庸
 */
public class DataAccessException extends SystemException {
	
	/** シリアルバージョン番号 */
	private static final long serialVersionUID = 2L;

	/**
	 * コンストラクタ
	 *
	 */
	public DataAccessException() {
		super();
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public DataAccessException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param cause 原因となった例外
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}

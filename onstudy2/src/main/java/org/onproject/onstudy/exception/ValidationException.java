/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/exception/ValidationException.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/02/06 08:46:32 $
******************************************************************************/
package org.onproject.onstudy.exception;

/**
 * 精査例外
 * 
 * @author 恩田 好庸
 */
public class ValidationException extends LogicException {
    
    /** シリアルバージョン番号 */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     */
    public ValidationException() {
        super();
    }
    
    /**
     * コンストラクタ
     * 
     * @param message メッセージ
     */
    public ValidationException(String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message メッセージ
     * @param cause 原因となった例外
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * コンストラクタ
     * 
     * @param cause 原因となった例外
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}

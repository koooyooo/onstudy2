/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/exception/ValidationException.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/02/06 08:46:32 $
******************************************************************************/
package org.onproject.onstudy.exception;

/**
 * ������O
 * 
 * @author ���c �D�f
 */
public class ValidationException extends LogicException {
    
    /** �V���A���o�[�W�����ԍ� */
    private static final long serialVersionUID = 1L;

    /**
     * �R���X�g���N�^
     *
     */
    public ValidationException() {
        super();
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param message ���b�Z�[�W
     */
    public ValidationException(String message) {
        super(message);
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param message ���b�Z�[�W
     * @param cause �����ƂȂ�����O
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param cause �����ƂȂ�����O
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}

/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/QuestionListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

import org.onproject.onstudy.data.Question;

/**
 * ���f�[�^�̃��X�i
 * 
 * @author ���c �D�f
 */
public interface QuestionListener {
    
    /**
     * ���f�[�^��m�o���܂��B
     * 
     * @param question ���f�[�^
     */
    public void receive(Question question);
    
}

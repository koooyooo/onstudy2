/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/net/QuestionListener.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:45 $
******************************************************************************/
package org.onproject.onstudy.net;

import org.onproject.onstudy.data.Question;

/**
 * 問題データのリスナ
 * 
 * @author 恩田 好庸
 */
public interface QuestionListener {
    
    /**
     * 問題データを知覚します。
     * 
     * @param question 問題データ
     */
    public void receive(Question question);
    
}

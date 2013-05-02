/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/data/QuestionComparatorNumericalStringIDImpl.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.data;

import java.util.Comparator;

/**
 * –â‘è”äŠrƒNƒ‰ƒX‚Ì”š•¶š—ñIDÀ‘•
 * 
 * @author ‰¶“c D—f
 */
public class QuestionComparatorNumericalStringIDImpl<T extends Question> implements Comparator<T> {

    /**
     * Question‚ğ”äŠr‚µ‚Ü‚·B
     * 
     * @param q1 ‘ÎÛ‚P
     * @param q2 ‘ÎÛ‚Q
     * @return ‘ÎÛ‚P‚ª‘ÎÛ‚Q‚æ‚è‘å‚«‚¯‚ê‚Î 1A“™‚µ‚¯‚ê‚Î 0A¬‚³‚¯‚ê‚Î -1
     */
    public int compare(T q1, T q2) {
        final int q1IdValue = Integer.parseInt(q1.getId());
        final int q2IdValue = Integer.parseInt(q2.getId());
        return q1IdValue - q2IdValue;
    }
    
}

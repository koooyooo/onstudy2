/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/data/QuestionComparatorNumericalStringIDImpl.java,v $
  Version : $Revision: 1.3 $
  Date    : $Date: 2007/06/20 05:22:44 $
******************************************************************************/
package org.onproject.onstudy.data;

import java.util.Comparator;

/**
 * 問題比較クラスの数字文字列ID実装
 * 
 * @author 恩田 好庸
 */
public class QuestionComparatorNumericalStringIDImpl<T extends Question> implements Comparator<T> {

    /**
     * Questionを比較します。
     * 
     * @param q1 対象１
     * @param q2 対象２
     * @return 対象１が対象２より大きければ 1、等しければ 0、小さければ -1
     */
    public int compare(T q1, T q2) {
        final int q1IdValue = Integer.parseInt(q1.getId());
        final int q2IdValue = Integer.parseInt(q2.getId());
        return q1IdValue - q2IdValue;
    }
    
}

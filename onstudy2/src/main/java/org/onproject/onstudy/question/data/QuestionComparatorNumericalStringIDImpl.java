/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/question/data/QuestionComparatorNumericalStringIDImpl.java,v $
  Version : $Revision: 1.1 $
  Date    : $Date: 2007/04/19 07:38:07 $
******************************************************************************/
package org.onproject.onstudy.question.data;

import java.util.Comparator;

/**
 * ����r�N���X�̐���������ID����
 * 
 * @author ���c �D�f
 */
public class QuestionComparatorNumericalStringIDImpl<T extends Question> implements Comparator<T> {

    /**
     * Question���r���܂��B
     * 
     * @param q1 �ΏۂP
     * @param q2 �ΏۂQ
     * @return �ΏۂP���ΏۂQ���傫����� 1�A��������� 0�A��������� -1
     */
    public int compare(T q1, T q2) {
        final int q1IdValue = Integer.parseInt(q1.getId());
        final int q2IdValue = Integer.parseInt(q2.getId());
        return q1IdValue - q2IdValue;
    }
    
}

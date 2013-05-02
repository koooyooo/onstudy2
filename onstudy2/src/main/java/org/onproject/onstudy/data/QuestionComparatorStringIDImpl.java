package org.onproject.onstudy.data;

import java.util.Comparator;

/**
 * –â‘è”äŠrƒNƒ‰ƒX‚Ì•¶š—ñIDÀ‘•
 * 
 * @author ‰¶“c D—f
 */
public class QuestionComparatorStringIDImpl<T extends Question> implements Comparator<T> {
	
	/**
	 * Question‚ğ”äŠr‚µ‚Ü‚·B
	 * 
	 * @param q1 ‘ÎÛ‚P
	 * @param q2 ‘ÎÛ‚Q
	 * @return ‘ÎÛ‚P‚ª‘ÎÛ‚Q‚æ‚è‘å‚«‚¯‚ê‚Î 1A“™‚µ‚¯‚ê‚Î 0A¬‚³‚¯‚ê‚Î -1
	 */
	public int compare(T q1, T q2) {
		return q1.getId().compareTo(q2.getId());
	}

}

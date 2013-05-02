package org.onproject.onstudy.ui.console;

/**
 * ユーザからの要求を表現する TypeSafeEnum
 * 
 * @author 恩田 好庸
 */
public enum Operation {
	
	/** 問題の表示 */
	SHOW_QUESTION, 
	
	/** ヒントの表示 */
	SHOW_HINT, 
	
	/** 正答の表示 */
	SHOW_RIGHT_ANSWER, 
	
	/** 解答結果の表示 */
	SHOW_ANSWER_RESULT
	
}

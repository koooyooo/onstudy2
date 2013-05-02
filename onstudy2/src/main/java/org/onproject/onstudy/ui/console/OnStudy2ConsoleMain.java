package org.onproject.onstudy.ui.console;

import java.util.HashSet;
import java.util.Set;

/**
 * コンソールベースUIのメインクラス
 * 
 * @author 恩田 好庸
 */
public class OnStudy2ConsoleMain {

	/**
	 * メインメソッド
	 * 
	 * @param args
	 * 		第１引数：問題ID、「?」ならばランダムでのID
	 * 		第２引数：解答ID、「-a」ならば解答、「-h」はヒント
	 */
	public static void main(String[] args) throws Exception {
		if (0 == args.length || 2 < args.length ) {
			System.err.println("arg1=[question number] arg2=[answer or options]");
		}
		final String questionId = args[0];
		final String answerOrOption = (args.length == 2) ? args[1]: null;
		TextPresenter presenter = new TextPresenter();
		presenter.execute(questionId, createOperationSet(args), answerOrOption);
	}
	
	/**
	 * 処理対象を選択します。
	 * 
	 * @param args プログラム引数
	 * @return 処理対象のセット
	 */
	private static Set<Operation> createOperationSet(String[] args) {
		final Set<Operation> operationSet = new HashSet<Operation>();
		
		if (args.length == 1) {
			operationSet.add(Operation.SHOW_QUESTION);
			return operationSet;
		}
		
		if ("-a".equals(args[1])) {
			operationSet.add(Operation.SHOW_QUESTION);
			operationSet.add(Operation.SHOW_RIGHT_ANSWER);
		} else if ("-h".equals(args[1])) {
			operationSet.add(Operation.SHOW_QUESTION);
			operationSet.add(Operation.SHOW_HINT);
		} else {
			operationSet.add(Operation.SHOW_QUESTION);
			operationSet.add(Operation.SHOW_ANSWER_RESULT);
		}
		return operationSet;
	}
	
}

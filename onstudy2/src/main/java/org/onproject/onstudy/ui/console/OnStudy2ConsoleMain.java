package org.onproject.onstudy.ui.console;

import java.util.HashSet;
import java.util.Set;

/**
 * �R���\�[���x�[�XUI�̃��C���N���X
 * 
 * @author ���c �D�f
 */
public class OnStudy2ConsoleMain {

	/**
	 * ���C�����\�b�h
	 * 
	 * @param args
	 * 		��P�����F���ID�A�u?�v�Ȃ�΃����_���ł�ID
	 * 		��Q�����F��ID�A�u-a�v�Ȃ�Ή𓚁A�u-h�v�̓q���g
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
	 * �����Ώۂ�I�����܂��B
	 * 
	 * @param args �v���O��������
	 * @return �����Ώۂ̃Z�b�g
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

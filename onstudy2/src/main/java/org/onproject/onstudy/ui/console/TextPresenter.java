package org.onproject.onstudy.ui.console;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.onproject.onstudy.question.data.Option;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.question.service.QuestionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �R���\�[���^UI�̕����\���N���X
 * 
 * @author ���c �D�f
 */
public class TextPresenter {
	
	/**
	 * �\�����������s���܂��B
	 * 
	 * @param questionId ���ID
	 * @param operationSet �����Set
	 * @param userAnswer ���[�U�̉𓚁i������� null�j
	 * @throws Exception
	 */
	public void execute(String questionId, Set<Operation> operationSet, String userAnswer) throws Exception {
		
        ApplicationContext context = new ClassPathXmlApplicationContext("/onstudyApplicationContext.xml");
        QuestionService service = (QuestionService) context.getBean("questionServiceImpl");
        Question question = null;
        
        if ("?".equals(questionId)) {
        	question = service.getRandomQuestion();
        } else {
        	question = service.getQuestionById(questionId);
        }
		
		if (operationSet.contains(Operation.SHOW_QUESTION)) {
			this.showTitle(question);
			this.showSentence(question);
			this.showOptions(question);
		}
		
		if (operationSet.contains(Operation.SHOW_ANSWER_RESULT)) {
			showAnswerResult(question, userAnswer);
		}
		
		if (operationSet.contains(Operation.SHOW_HINT)) {
			this.outputln("�q���g:" + question.getHint());
		}
		
		if (operationSet.contains(Operation.SHOW_RIGHT_ANSWER)) {
			this.showRightAnswer(question);
			this.showExpositoryWriting(question);
		}
	}
	
	/**
	 * �^�C�g����\�����܂��B
	 * 
	 * @param question ���
	 */
	private void showTitle(Question question) {
		this.output(" [" + question.getId() + " " + question.getTitle() + "] ");
	}
	
	/**
	 * ��蕶��\�����܂��B
	 * 
	 * @param question ���
	 */
	private void showSentence(Question question) {
		this.outputln(question.getSentence());
	}
	
	/**
	 * �I������\�����܂��B
	 * 
	 * @param question �I����
	 */
	private void showOptions(Question question) {
		for (Option option :  question.optionIterable()) {
			outputln("    " + option.getId() + " : " + option.getText());
		}
		this.outputln();
	}
	
	/**
	 * �𓚌��ʂ�\�����܂��B
	 * 
	 * @param question ���
	 * @param userAnswer ���[�U�̉�
	 */
	private void showAnswerResult(Question question, String userAnswer) {
		final Set<String> answerIdSet = new HashSet<String>();
		for (StringTokenizer tokenizer = new StringTokenizer(userAnswer, ","); tokenizer.hasMoreTokens();) {
			answerIdSet.add(tokenizer.nextToken());
		}
		String result = (question.isRightAnswer(answerIdSet)) ? "��" : "�~";
		this.outputln("  ���ʁF" + result);
		this.outputln();
	}
	
	/**
	 * ������\�����܂��B
	 * 
	 * @param question ���
	 */
	private void showRightAnswer(Question question) {
		this.output("  �𓚁F");
		for (Option option : question.optionIterable()) {
		    if (!option.isRightAnswer()) {
		        continue;
            }
			this.output(option.getId());
			this.output(" ");
		}
		this.outputln();
	}
	
	/**
	 * �����\�����܂��B
	 * 
	 * @param question ���
	 */
	private void showExpositoryWriting(Question question) {
		this.outputln("  ����F" + question.getExpositoryWriting());
		this.outputln();
	}
	
	/**
	 * ��s��\�����܂��B
	 *
	 */
	private void outputln() {
		this.getPrintStream().println();
	}
	
	/**
	 * �R���e���c��\�����A�����ɉ��s�����܂��B
	 * 
	 * @param content �R���e���c
	 */
	private void outputln(String content) {
		this.getPrintStream().println(content);
	}
	
	/**
	 * �R���e���c��\�����܂��B
	 * 
	 * @param content �R���e���c
	 */
	private void output(String content) {
		this.getPrintStream().print(content);
	}
	
	/**
	 * �o�͐�� PrintStream��񋟂��܂��B
	 * 
	 * @return �o�͐�� PrintStream
	 */
	private PrintStream getPrintStream() {
		return System.out;
	}
	
}

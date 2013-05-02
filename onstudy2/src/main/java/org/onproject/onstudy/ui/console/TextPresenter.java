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
 * コンソール型UIの文字表示クラス
 * 
 * @author 恩田 好庸
 */
public class TextPresenter {
	
	/**
	 * 表示処理を実行します。
	 * 
	 * @param questionId 問題ID
	 * @param operationSet 操作のSet
	 * @param userAnswer ユーザの解答（無ければ null）
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
			this.outputln("ヒント:" + question.getHint());
		}
		
		if (operationSet.contains(Operation.SHOW_RIGHT_ANSWER)) {
			this.showRightAnswer(question);
			this.showExpositoryWriting(question);
		}
	}
	
	/**
	 * タイトルを表示します。
	 * 
	 * @param question 問題
	 */
	private void showTitle(Question question) {
		this.output(" [" + question.getId() + " " + question.getTitle() + "] ");
	}
	
	/**
	 * 問題文を表示します。
	 * 
	 * @param question 問題
	 */
	private void showSentence(Question question) {
		this.outputln(question.getSentence());
	}
	
	/**
	 * 選択肢を表示します。
	 * 
	 * @param question 選択肢
	 */
	private void showOptions(Question question) {
		for (Option option :  question.optionIterable()) {
			outputln("    " + option.getId() + " : " + option.getText());
		}
		this.outputln();
	}
	
	/**
	 * 解答結果を表示します。
	 * 
	 * @param question 問題
	 * @param userAnswer ユーザの解答
	 */
	private void showAnswerResult(Question question, String userAnswer) {
		final Set<String> answerIdSet = new HashSet<String>();
		for (StringTokenizer tokenizer = new StringTokenizer(userAnswer, ","); tokenizer.hasMoreTokens();) {
			answerIdSet.add(tokenizer.nextToken());
		}
		String result = (question.isRightAnswer(answerIdSet)) ? "○" : "×";
		this.outputln("  結果：" + result);
		this.outputln();
	}
	
	/**
	 * 正答を表示します。
	 * 
	 * @param question 問題
	 */
	private void showRightAnswer(Question question) {
		this.output("  解答：");
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
	 * 解説を表示します。
	 * 
	 * @param question 問題
	 */
	private void showExpositoryWriting(Question question) {
		this.outputln("  解説：" + question.getExpositoryWriting());
		this.outputln();
	}
	
	/**
	 * 空行を表示します。
	 *
	 */
	private void outputln() {
		this.getPrintStream().println();
	}
	
	/**
	 * コンテンツを表示し、末尾に改行を入れます。
	 * 
	 * @param content コンテンツ
	 */
	private void outputln(String content) {
		this.getPrintStream().println(content);
	}
	
	/**
	 * コンテンツを表示します。
	 * 
	 * @param content コンテンツ
	 */
	private void output(String content) {
		this.getPrintStream().print(content);
	}
	
	/**
	 * 出力先の PrintStreamを提供します。
	 * 
	 * @return 出力先の PrintStream
	 */
	private PrintStream getPrintStream() {
		return System.out;
	}
	
}

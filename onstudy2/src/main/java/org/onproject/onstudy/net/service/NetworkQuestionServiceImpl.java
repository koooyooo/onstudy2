package org.onproject.onstudy.net.service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.onproject.onstudy.net.data.Member;
import org.onproject.onstudy.net.data.QuestionPacket;
import org.onproject.onstudy.net.listener.QuestionPacketListener;
import org.onproject.onstudy.question.data.Answer;
import org.onproject.onstudy.question.data.Question;
import org.onproject.onstudy.service.ServiceLocator;

/**
 * ネットワーク問題サービスの実装
 * 
 * @author 恩田 好庸
 */
public class NetworkQuestionServiceImpl implements NetworkQuestionService, QuestionPacketListener {
	
	/** 問題パケットQueue TODO 一番上の要素しか使ってません */
	private final Queue<QuestionPacket> questionPacketQueue = new LinkedList<QuestionPacket>(); 
	
	/**
	 * IDのSetを取得します。
	 * 
	 * @return IDのセット
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getIdSet() {
		if (questionPacketQueue.isEmpty()) {
			return Collections.EMPTY_SET;
		}
		final Set<String> idSet = new LinkedHashSet<String>();
		for (Question question : this.questionPacketQueue.peek().getQuestionList()) {
			idSet.add(question.getId());
		}
		return idSet;
	}
	
	/**
	 * IDの指定により、問題を取得します。
	 * 
	 * @param id 指定するID
	 * @return 同じIDを持つQuestion。無ければnull。
	 */
	public Question getQuestionById(String id) {
		for (Question question : this.questionPacketQueue.peek().getQuestionList()) {
			if (question.getId().equals(id)) {
				return question;
			}
		}
		return null;
	}

	/**
	 * 問題を返信します。
	 * 
	 * @param answerSet 解答セット
	 */
	public synchronized void reply(Set<Answer> answerSet) {
		// TODO 結果の返信ネットワーク処理が必要
		Member questionSender = this.questionPacketQueue.peek().getTransmitter();
        ServiceLocator.getNetworkService().sendAnswer(questionSender, answerSet);
	}
	
	/**
	 * 問題パケットの受信を感知します。
	 * 
	 * @param questionPacket 問題パケット
	 */
	public synchronized void listen(QuestionPacket questionPacket) {
		this.questionPacketQueue.clear(); // TODO
		this.questionPacketQueue.add(questionPacket);
	}

	/**
	 * ランダムで問題を取得します。
	 * 
	 * @return ランダムで取得された問題
	 */
	public Question getRandomQuestion() {
		throw new UnsupportedOperationException();
	}

    /**
     * 問題IDが現在も存在するかをチェックします。
     * 
     * @param nextQuestionId 対象となる問題ID
     * @return 現在も存在すれば true
     */
	public boolean isValidId(String nextQuestionId) {
		throw new UnsupportedOperationException();
	}

    /**
     * 問題を登録または更新します。
     * 
     * @question 登録または更新する問題
     */
	public void saveOrUpdateQuestion(Question question) {
		throw new UnsupportedOperationException();
	}

    /**
     * 問題を削除します。
     * 
     * @param id 削除対象の問題ID
     */
	public void removeQuestion(String id) {
		throw new UnsupportedOperationException();
	}

}

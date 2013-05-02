package org.onproject.onstudy.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.io.FilenameUtils;
import org.onproject.onstudy.data.Question;
import org.onproject.onstudy.exception.DataAccessException;

/**
 * QuestionDAOのファイル実装
 * 
 * @author 恩田 好庸
 */
public class QuestionDAODigesterImpl implements QuestionDAO {
	
	/** ダイジェスタインスタンス */
	private final Digester digester = DigesterLoader.createDigester(QuestionDAODigesterImpl.class.getResource("/digester/question-rule.xml"));
	
	/** 問題ファイルを配置するディレクトリ */
	protected File questionStoreDir;
    
	/** 既定の問題比較関数 */
	private Comparator<Question> defaultQuestionComparator;

	/**
	 * IDのSetを取得します。
	 * 
	 * @return IDのセット
	 */
	public Set<String> loadIdSet() {
		final Set<String> idSet = new LinkedHashSet<String>();
		for (Question question : this.loadQuestionSortedSet()) {
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
	public Question loadQuestionById(String id) {
		for (Question question : this.loadQuestionSortedSet()) {
			if (question.getId().equals(id)) {
				return question;
			}
		}
		return null;
	}
	
	/**
	 * デフォルトの順序でソートされた問題のSetを取得します。
	 * 
	 * @return デフォルトの順序でソートされた問題のSet
	 */
	public SortedSet<Question> loadQuestionSortedSet() {
		return this.doLoadQuestionSortedSet(this.questionStoreDir, this.defaultQuestionComparator);
	}
	
	/**
	 * 指定された順序でソートされた問題のSetを取得します。
	 * 
	 * @param comparator 問題比較関数
	 * @return 指定された順序でソートされた問題のSet
	 */
	public SortedSet<Question> loadQuestionSortedSet(Comparator<Question> comparator) {
		return this.doLoadQuestionSortedSet(this.questionStoreDir, comparator);
	}
	
	/**
	 * 問題XMLディレクトリより、問題を全てロードします。
	 * 
	 * @param directory 問題XMLディレクトリ
	 * @return ディレクトリ配下の全ての問題
	 * @throws DataAccessException
	 */
	protected SortedSet<Question> doLoadQuestionSortedSet(File directory, Comparator<Question> comparator) {
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory + " is not directory");
		}
		final SortedSet<Question> questionSet = new TreeSet<Question>(comparator);
		final List<File> questionFileList = Arrays.asList(directory.listFiles(new QuestionFileFilter()));
		for (File questionFile : questionFileList) {
			questionSet.add(this.loadQuestion(questionFile));
		}
		return questionSet;
	}
	
	/**
	 * 問題XMLファイルより、問題をロードします。
	 * 
	 * @param file 問題XMLファイル
	 * @return 問題オブジェクト
	 */
	protected Question loadQuestion(File file) {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			return (Question) digester.parse(in);
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignore) {
				}
			}
		}
	}
	
	/**
	 * 問題ファイルフィルタ
	 * 
	 * @author 恩田 好庸
	 */
	private static class QuestionFileFilter implements FileFilter {
		/**
		 * 許可判断
		 * 
		 * @param file 判断対象のファイル
		 * @param 問題ファイルと認定されれば true
		 */
		public boolean accept(File file) {
			if (!FilenameUtils.getExtension(file.getName()).equals("xml")) {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * 問題ファイルを配置するディレクトリを指定します。
	 * 
	 * @param questionStoreDir 問題ファイルを配置するディレクトリ
	 */
	public void setQuestionStoreDir(File questionStoreDir) {
		this.questionStoreDir = questionStoreDir;
	}
    
	/**
	 * 問題比較関数を設定します。
	 * 
	 * @param questionComparator 問題比較関数
	 */
	public void setQuestionComparator(Comparator<Question> questionComparator) {
		this.defaultQuestionComparator = questionComparator;
	}

    /**
     * 問題を登録します。
     * 
     * @param question 問題
     */
    public void registerQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    /**
     * 問題を更新します。
     * 
     * @param question 問題
     */
    public void updateQuestion(Question question) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * 問題を削除します。
     * 
     * @param id 問題ID
     */
    public void deleteQuestion(String id) {
        throw new UnsupportedOperationException();
    }
	
}

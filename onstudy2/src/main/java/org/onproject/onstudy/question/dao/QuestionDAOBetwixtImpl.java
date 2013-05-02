package org.onproject.onstudy.question.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.onproject.onstudy.exception.DataAccessException;
import org.onproject.onstudy.exception.SystemException;
import org.onproject.onstudy.question.dao.xml.XmlAgent;
import org.onproject.onstudy.question.data.Question;

/**
 * QuestionDAOのファイル実装
 * 
 * @author 恩田 好庸
 */
public class QuestionDAOBetwixtImpl extends QuestionDAODigesterImpl {
	
    /** XML操作エージェント */
    private final XmlAgent agent = XmlAgent.getInstance();
    
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
			return (Question) agent.convertToObject(in, Question.class);
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
     * 問題を登録します。
     * 
     * @param question 問題
     */
    public void registerQuestion(Question question) {
        this.outputToXml(new File(super.questionStoreDir + "/q-" + question.getId() + ".xml"), question);
    }

    /**
     * 問題を更新します。
     * 
     * @param question 問題
     */
    public void updateQuestion(Question question) {
        this.outputToXml(new File(super.questionStoreDir + "/q-" + question.getId() + ".xml"), question);
    }
    
    /**
     * 問題を削除します。
     * 
     * @param id 問題ID
     */
    public void deleteQuestion(String id) {
        new File(super.questionStoreDir + "/q-" + id + ".xml").delete();
    }
    
    /**
     * 指定されたXMLへ情報を出力します。
     * 
     * @param path 指定されたパス
     * @param question 問題
     */
    private void outputToXml(File path, Question question) {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(path));
            this.agent.convertToXml(out, "UTF-8", question);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
	
}

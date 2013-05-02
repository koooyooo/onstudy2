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
 * QuestionDAO�̃t�@�C������
 * 
 * @author ���c �D�f
 */
public class QuestionDAOBetwixtImpl extends QuestionDAODigesterImpl {
	
    /** XML����G�[�W�F���g */
    private final XmlAgent agent = XmlAgent.getInstance();
    
	/**
	 * ���XML�t�@�C�����A�������[�h���܂��B
	 * 
	 * @param file ���XML�t�@�C��
	 * @return ���I�u�W�F�N�g
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
     * ����o�^���܂��B
     * 
     * @param question ���
     */
    public void registerQuestion(Question question) {
        this.outputToXml(new File(super.questionStoreDir + "/q-" + question.getId() + ".xml"), question);
    }

    /**
     * �����X�V���܂��B
     * 
     * @param question ���
     */
    public void updateQuestion(Question question) {
        this.outputToXml(new File(super.questionStoreDir + "/q-" + question.getId() + ".xml"), question);
    }
    
    /**
     * �����폜���܂��B
     * 
     * @param id ���ID
     */
    public void deleteQuestion(String id) {
        new File(super.questionStoreDir + "/q-" + id + ".xml").delete();
    }
    
    /**
     * �w�肳�ꂽXML�֏����o�͂��܂��B
     * 
     * @param path �w�肳�ꂽ�p�X
     * @param question ���
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

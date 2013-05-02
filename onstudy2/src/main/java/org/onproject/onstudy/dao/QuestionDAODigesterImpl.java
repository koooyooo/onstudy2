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
 * QuestionDAO�̃t�@�C������
 * 
 * @author ���c �D�f
 */
public class QuestionDAODigesterImpl implements QuestionDAO {
	
	/** �_�C�W�F�X�^�C���X�^���X */
	private final Digester digester = DigesterLoader.createDigester(QuestionDAODigesterImpl.class.getResource("/digester/question-rule.xml"));
	
	/** ���t�@�C����z�u����f�B���N�g�� */
	protected File questionStoreDir;
    
	/** ����̖���r�֐� */
	private Comparator<Question> defaultQuestionComparator;

	/**
	 * ID��Set���擾���܂��B
	 * 
	 * @return ID�̃Z�b�g
	 */
	public Set<String> loadIdSet() {
		final Set<String> idSet = new LinkedHashSet<String>();
		for (Question question : this.loadQuestionSortedSet()) {
			idSet.add(question.getId());
		}
		return idSet;
	}
	
	/**
	 * ID�̎w��ɂ��A�����擾���܂��B
	 * 
	 * @param id �w�肷��ID
	 * @return ����ID������Question�B�������null�B
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
	 * �f�t�H���g�̏����Ń\�[�g���ꂽ����Set���擾���܂��B
	 * 
	 * @return �f�t�H���g�̏����Ń\�[�g���ꂽ����Set
	 */
	public SortedSet<Question> loadQuestionSortedSet() {
		return this.doLoadQuestionSortedSet(this.questionStoreDir, this.defaultQuestionComparator);
	}
	
	/**
	 * �w�肳�ꂽ�����Ń\�[�g���ꂽ����Set���擾���܂��B
	 * 
	 * @param comparator ����r�֐�
	 * @return �w�肳�ꂽ�����Ń\�[�g���ꂽ����Set
	 */
	public SortedSet<Question> loadQuestionSortedSet(Comparator<Question> comparator) {
		return this.doLoadQuestionSortedSet(this.questionStoreDir, comparator);
	}
	
	/**
	 * ���XML�f�B���N�g�����A����S�ă��[�h���܂��B
	 * 
	 * @param directory ���XML�f�B���N�g��
	 * @return �f�B���N�g���z���̑S�Ă̖��
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
	 * ���XML�t�@�C�����A�������[�h���܂��B
	 * 
	 * @param file ���XML�t�@�C��
	 * @return ���I�u�W�F�N�g
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
	 * ���t�@�C���t�B���^
	 * 
	 * @author ���c �D�f
	 */
	private static class QuestionFileFilter implements FileFilter {
		/**
		 * �����f
		 * 
		 * @param file ���f�Ώۂ̃t�@�C��
		 * @param ���t�@�C���ƔF�肳���� true
		 */
		public boolean accept(File file) {
			if (!FilenameUtils.getExtension(file.getName()).equals("xml")) {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * ���t�@�C����z�u����f�B���N�g�����w�肵�܂��B
	 * 
	 * @param questionStoreDir ���t�@�C����z�u����f�B���N�g��
	 */
	public void setQuestionStoreDir(File questionStoreDir) {
		this.questionStoreDir = questionStoreDir;
	}
    
	/**
	 * ����r�֐���ݒ肵�܂��B
	 * 
	 * @param questionComparator ����r�֐�
	 */
	public void setQuestionComparator(Comparator<Question> questionComparator) {
		this.defaultQuestionComparator = questionComparator;
	}

    /**
     * ����o�^���܂��B
     * 
     * @param question ���
     */
    public void registerQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    /**
     * �����X�V���܂��B
     * 
     * @param question ���
     */
    public void updateQuestion(Question question) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * �����폜���܂��B
     * 
     * @param id ���ID
     */
    public void deleteQuestion(String id) {
        throw new UnsupportedOperationException();
    }
	
}

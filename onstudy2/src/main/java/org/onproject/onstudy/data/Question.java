package org.onproject.onstudy.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ���N���X
 * 
 * @author ���c �D�f
 */
public class Question {
	
	/** ID */
	private String id;
	
	/** �^�C�g�� */
	private String title;
	
	/** ��蕶 */
	private String sentence;
	
	/** �I�������X�g */
	private List<Option> optionList = new ArrayList<Option>();
	
	/** �q���g */
	private String hint;
	
	/** ��� */
	private String expositoryWriting;
	
	/**
	 * ID���擾���܂��B
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * ID��ݒ肵�܂��B
	 * 
	 * @param id ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * �^�C�g�����擾���܂��B
	 * 
	 * @return �^�C�g��
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * �^�C�g����ݒ肵�܂��B
	 * 
	 * @param title �^�C�g��
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * ��蕶���擾���܂��B
	 * 
	 * @return ��蕶
	 */
	public String getSentence() {
		return sentence;
	}
	
	/**
	 * ��蕶��ݒ肵�܂��B
	 * 
	 * @param sentence ��蕶
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	/**
	 * �I������ǉ����܂��B
	 * 
	 * @param option �I����
	 */
	public void addOption(Option option) {
		this.optionList.add(option);
	}
    
    /**
     * 
     * @return
     */
    @Deprecated
    public Collection<Option> getOptions() {
        return new ArrayList<Option>(this.optionList);
    }
    
    /**
     * �I�����̏W�����擾���܂��B
     * 
     * @return �I�����̏W��
     */
    public Iterable<Option> optionIterable() {
        return new ArrayList<Option>(this.optionList);
    }
	
	/**
	 * �I�����̐����擾���܂��B
	 * 
	 * @return �I�����̐�
	 */
	public int optionSize() {
		return this.optionList.size();
	}
	
	/**
	 * �q���g���擾���܂��B
	 * 
	 * @return �q���g
	 */
	public String getHint() {
		return hint;
	}
	
	/**
	 * �q���g��ݒ肵�܂��B
	 * 
	 * @param hint �q���g
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
    
    /**
     * ������ID���Z�b�g�ɂ��Ď擾���܂��B
     * 
     * @return ������ID
     */
    protected Set<String> getRightAnswerIdSet() {
        Set<String> answerSet = new HashSet<String>();
        for (Option option : this.optionList) {
            if (option.isRightAnswer()) {
                answerSet.add(option.getId());
            }
        }
        return answerSet;
    }
    
	/**
	 * ���𐔂��擾���܂��B
	 * 
	 * @return ����
	 */
	public int rightAnswerSize() {
		return this.getRightAnswerIdSet().size(); // TODO
	}
	
	/**
	 * �������𓚂��ۂ��𔻒肵�܂��B
	 * 
	 * @param answerIdSet ��ID�̃Z�b�g
	 * @return �������𓚂ł���� true
	 */
	public boolean isRightAnswer(Set<String> answerIdSet) {
		if (answerIdSet.size() != this.getRightAnswerIdSet().size()) {
			return false;
		}
		for (String rightAnswer : this.getRightAnswerIdSet()) {
			if (!answerIdSet.contains(rightAnswer)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ������擾���܂��B
	 * 
	 * @return ���
	 */
	public String getExpositoryWriting() {
		return expositoryWriting;
	}
	
	/**
	 * �����ݒ肵�܂��B
	 * 
	 * @param expositoryWriting ���
	 */
	public void setExpositoryWriting(String expositoryWriting) {
		this.expositoryWriting = expositoryWriting;
	}
	
	/**
	 * ���C���X�^���X�̕�����\�����擾���܂��B
	 * 
	 * @return ���C���X�^���X�̕�����\��
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Question : ");
		sb.append("id=[" + id + "] ");
		sb.append("title=[" + title + "] ");
		sb.append("sentence=[" + sentence + "] ");
		sb.append("option=[" + this.optionList + "] ");
		sb.append("hint=[" + hint + "] ");
		sb.append("expository-writing=[" + this.expositoryWriting + "] ");
		return sb.toString();
	}
	
}

package org.onproject.onstudy.data;

/**
 * �I�����N���X
 * 
 * @author ���c �D�f
 */
public class Option {
	
	/** ID */
	private String id;
	
	/** �{�� */
	private String text;
    
    /** ���� */
    private boolean rightAnswer;
	
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
	 * �{�����擾���܂��B
	 * 
	 * @return �{��
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * �{����ݒ肵�܂��B
	 * 
	 * @param text �{��
	 */
	public void setText(String text) {
		this.text = text;
	}
    
    /**
     * �����ł��邩���擾���܂��B
     * 
     * @return �����ł���� true
     */
    public boolean isRightAnswer() {
        return this.rightAnswer;
    }
    
    /**
     * �����ł��邩��ݒ肵�܂��B
     * 
     * @param rightAnswer �����ł���� true
     */
    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
    /**
     * �����ł��邩��ݒ肵�܂��B
     * 
     * @param rightAnswer �����ł���� true
     */
    @Deprecated
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = Boolean.valueOf(rightAnswer);
    }
	
	/**
	 * ���C���X�^���X�̕�����\�����擾���܂��B
	 * 
	 * @return ���C���X�^���X�̕�����\��
	 */
	public String toString() {
		return "Option : id=[" + this.id + "] text=[" + this.text + "]";
	}
}

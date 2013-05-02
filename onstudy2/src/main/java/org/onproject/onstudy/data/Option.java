package org.onproject.onstudy.data;

/**
 * 選択肢クラス
 * 
 * @author 恩田 好庸
 */
public class Option {
	
	/** ID */
	private String id;
	
	/** 本文 */
	private String text;
    
    /** 正解 */
    private boolean rightAnswer;
	
	/**
	 * IDを取得します。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * IDを設定します。
	 * 
	 * @param id ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 本文を取得します。
	 * 
	 * @return 本文
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * 本文を設定します。
	 * 
	 * @param text 本文
	 */
	public void setText(String text) {
		this.text = text;
	}
    
    /**
     * 正解であるかを取得します。
     * 
     * @return 正解であれば true
     */
    public boolean isRightAnswer() {
        return this.rightAnswer;
    }
    
    /**
     * 正解であるかを設定します。
     * 
     * @param rightAnswer 正解であれば true
     */
    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
    /**
     * 正解であるかを設定します。
     * 
     * @param rightAnswer 正解であれば true
     */
    @Deprecated
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = Boolean.valueOf(rightAnswer);
    }
	
	/**
	 * 当インスタンスの文字列表現を取得します。
	 * 
	 * @return 当インスタンスの文字列表現
	 */
	public String toString() {
		return "Option : id=[" + this.id + "] text=[" + this.text + "]";
	}
}

package org.okboom.wukong.common.sequence.exception;

/**
 * 序列号生成异常
 * @author tookbra
 */
public class SeqException extends RuntimeException {

	private static final long serialVersionUID = 8912523969808834536L;

	public SeqException(String message) {
		super(message);
	}

	public SeqException(Throwable cause) {
		super(cause);
	}

}

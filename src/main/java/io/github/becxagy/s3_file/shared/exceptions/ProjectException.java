package io.github.becxagy.s3_file.shared.exceptions;

public abstract class ProjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final int code;

	public ProjectException(final int code, final String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

}

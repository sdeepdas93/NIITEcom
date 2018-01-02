package com.niit.collaboration.model;

public class FileInfo {
	private String fileName;
	 private long fileSize;
	 private String errorMessage;
	 private String errorCode;

	 public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getFileName() {
	  return fileName;
	 }

	 public void setFileName(String fileName) {
	  this.fileName = fileName;
	 }

	 public long getFileSize() {
	  return fileSize;
	 }

	 public void setFileSize(long fileSize) {
	  this.fileSize = fileSize;
	 }

}

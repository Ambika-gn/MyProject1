package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectFilesModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String entityReferenceType;
	private String fileName;
	private long lastModifiedTimestamp;
	private String mimeType;
	private String referenceUrn;
	private long timestamp;
	private String urn;

	public String getEntityReferenceType() {
		return entityReferenceType;
	}

	public void setEntityReferenceType(String entityReferenceType) {
		this.entityReferenceType = entityReferenceType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getReferenceUrn() {
		return referenceUrn;
	}

	public void setReferenceUrn(String referenceUrn) {
		this.referenceUrn = referenceUrn;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

}

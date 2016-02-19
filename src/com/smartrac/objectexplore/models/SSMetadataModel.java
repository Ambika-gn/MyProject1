package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSMetadataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataType;
	private String entityReferenceType;
	private String key;
	private long lastModifiedTimestamp;
	private String rawValue;
	private String referenceUrn;
	private String urn;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getEntityReferenceType() {
		return entityReferenceType;
	}

	public void setEntityReferenceType(String entityReferenceType) {
		this.entityReferenceType = entityReferenceType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}

	public String getReferenceUrn() {
		return referenceUrn;
	}

	public void setReferenceUrn(String referenceUrn) {
		this.referenceUrn = referenceUrn;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

}

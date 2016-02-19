package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectHashtagsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String entityReferenceType;
	private long lastModifiedTimestamp;
	private String referenceUrn;
	private String urn;
	private SSTagsModel tag;

	public SSTagsModel getTag() {
		return tag;
	}

	public void setTag(SSTagsModel tag) {
		this.tag = tag;
	}

	public String getEntityReferenceType() {
		return entityReferenceType;
	}

	public void setEntityReferenceType(String entityReferenceType) {
		this.entityReferenceType = entityReferenceType;
	}

	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
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

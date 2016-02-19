package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectGeospatialModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String entityReferenceType;
	private long lastModifiedTimestamp;

	private String referenceUrn;

	private String relatedEntityReferenceType;

	private String relatedReferenceUrn;

	private String type;
	private String urn;

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

	public String getRelatedEntityReferenceType() {
		return relatedEntityReferenceType;
	}

	public void setRelatedEntityReferenceType(String relatedEntityReferenceType) {
		this.relatedEntityReferenceType = relatedEntityReferenceType;
	}

	public String getRelatedReferenceUrn() {
		return relatedReferenceUrn;
	}

	public void setRelatedReferenceUrn(String relatedReferenceUrn) {
		this.relatedReferenceUrn = relatedReferenceUrn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

}

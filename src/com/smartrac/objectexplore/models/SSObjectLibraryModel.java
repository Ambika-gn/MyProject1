package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectLibraryModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urn;
	private String libraryName;
	private String relatedEntityReferenceType;
	private String entityReferenceType;
	private String type;
	private String referenceUrn;
	private long lastModifiedTimestamp;
	private String relatedReferenceUrn;

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getRelatedEntityReferenceType() {
		return relatedEntityReferenceType;
	}

	public void setRelatedEntityReferenceType(String relatedEntityReferenceType) {
		this.relatedEntityReferenceType = relatedEntityReferenceType;
	}

	public String getEntityReferenceType() {
		return entityReferenceType;
	}

	public void setEntityReferenceType(String entityReferenceType) {
		this.entityReferenceType = entityReferenceType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReferenceUrn() {
		return referenceUrn;
	}

	public void setReferenceUrn(String referenceUrn) {
		this.referenceUrn = referenceUrn;
	}

	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public String getRelatedReferenceUrn() {
		return relatedReferenceUrn;
	}

	public void setRelatedReferenceUrn(String relatedReferenceUrn) {
		this.relatedReferenceUrn = relatedReferenceUrn;
	}

}

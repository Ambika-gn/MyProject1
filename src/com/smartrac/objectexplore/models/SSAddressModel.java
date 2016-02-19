package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSAddressModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean activeFlag;
	private String description;
	private long lastModifiedTimestamp;
	private String name;
	private String objectUrn;
	private String type;
	private String urn;
	public boolean isActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}
	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjectUrn() {
		return objectUrn;
	}
	public void setObjectUrn(String objectUrn) {
		this.objectUrn = objectUrn;
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

package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Boolean activeFlag;
	String description;
	String Name;
	long timeStamp;
	String objectUrn;
	String type;
	String urn;

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
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

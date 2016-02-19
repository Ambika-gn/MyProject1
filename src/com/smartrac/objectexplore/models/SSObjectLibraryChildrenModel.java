package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectLibraryChildrenModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urn;
	private boolean childAvailable;
	private String parent;
	private String name;
	private String description;
	private String libraryElementType;
	private String type;
	private long lastModifiedTimestamp;
	private boolean activeFlag;

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public boolean isChildAvailable() {
		return childAvailable;
	}

	public void setChildAvailable(boolean childAvailable) {
		this.childAvailable = childAvailable;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibraryElementType() {
		return libraryElementType;
	}

	public void setLibraryElementType(String libraryElementType) {
		this.libraryElementType = libraryElementType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}

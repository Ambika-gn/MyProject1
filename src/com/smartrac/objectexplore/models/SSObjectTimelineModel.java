package com.smartrac.objectexplore.models;

import java.io.Serializable;
import java.util.Comparator;

public class SSObjectTimelineModel implements Serializable,
		Comparator<SSObjectTimelineModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urn;
	private String entityReferenceType;
	private String name;
	private String description;
	private boolean highlightFlag;
	private String referenceUuid;
	private long lastModifiedTimestamp;
	private boolean activeFlag;
	private boolean visibleFlag;
	private long recordedTimestamp;

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getEntityReferenceType() {
		return entityReferenceType;
	}

	public void setEntityReferenceType(String entityReferenceType) {
		this.entityReferenceType = entityReferenceType;
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

	public boolean isHighlightFlag() {
		return highlightFlag;
	}

	public void setHighlightFlag(boolean highlightFlag) {
		this.highlightFlag = highlightFlag;
	}

	public String getReferenceUuid() {
		return referenceUuid;
	}

	public void setReferenceUuid(String referenceUuid) {
		this.referenceUuid = referenceUuid;
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

	public boolean isVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(boolean visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	public long getRecordedTimestamp() {
		return recordedTimestamp;
	}

	public void setRecordedTimestamp(long recordedTimestamp) {
		this.recordedTimestamp = recordedTimestamp;
	}

	@Override
	public int compare(SSObjectTimelineModel t1, SSObjectTimelineModel t2) {
		
		if(t1.getLastModifiedTimestamp() > t2.getLastModifiedTimestamp())
			return 1;
		else if (t1.getLastModifiedTimestamp() < t2.getLastModifiedTimestamp()) {
			return -1;
		}else{
			return 0;
		}
	}

}

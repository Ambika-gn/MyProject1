package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSObjectTotalCountModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String referedobjectUrn;
	private String referredObjectName;
	private int relationshipCount;
	public String getReferedobjectUrn() {
		return referedobjectUrn;
	}
	public void setReferedobjectUrn(String referedobjectUrn) {
		this.referedobjectUrn = referedobjectUrn;
	}
	public String getReferredObjectName() {
		return referredObjectName;
	}
	public void setReferredObjectName(String referredObjectName) {
		this.referredObjectName = referredObjectName;
	}
	public int getRelationshipCount() {
		return relationshipCount;
	}
	public void setRelationshipCount(int relationshipCount) {
		this.relationshipCount = relationshipCount;
	}

}
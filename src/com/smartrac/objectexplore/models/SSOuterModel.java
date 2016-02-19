package com.smartrac.objectexplore.models;

import java.io.Serializable;

public class SSOuterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String relationshipUrn;
	private String relationshipType;

	public String getRelationshipUrn() {
		return relationshipUrn;
	}

	public void setRelationshipUrn(String relationshipUrn) {
		this.relationshipUrn = relationshipUrn;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

}

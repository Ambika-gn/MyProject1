package com.smartrac.objectexplore.common;

public class SSConstants {

	public static final String PREFS_NAME = "LOGIN_PREFS";
	public static final String PREFS_USER = "user";
	public static final String PREFS_PASSWORD = "password";
	public static final String PREFS_INSTANCE1 = "instance1";
	public static final String PREFS_INSTANCE2 = "instance2";

	public static String serverURLPath = null;
	public static String accountURL = "/rest/account";
	public static String objectURL = "/rest/objects";
	public static String fileURL = "/rest/files/Object/";
	public static String hashtagsURL = "/rest/tags?entityReferenceType=Object&referenceUrn=";
	public static String metadataURL = "/rest/metadata/Object/";
	public static String geospatialURL = "/rest/relationships/Object/";
	public static String relationshipsURL = "/rest/relationships/Object/";
	public static String interatctionsURL = "/rest/relationships/Object/";
	public static String addressURL = "/rest/objects/object/";
	public static String timelineURL = "/rest/plugin/timelines";
	public static String libraryURL = "/rest/plugin/library/Object/";

	// object attributes
	public static final String TAG_OBJECT = "";
	public static final String TAG_OBJECT_STATUS = "activeFlag";
	public static final String TAG_OBJECT_DESCRIPTION = "description";
	public static final String TAG_OBJECT_NAME = "name";
	public static final String TAG_OBJECT_TIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_OBJECTURN = "objectUrn";
	public static final String TAG_OBJECT_TYPE = "type";
	public static final String TAG_OBJECT_URN = "urn";

	// files attributes
	public static final String TAG_OBJECT_FILE = "";
	public static final String TAG_OBJECT_FILE_REFERENCETYPE = "entityReferenceType";
	public static final String TAG_OBJECT_FILE_FILENAME = "fileName";
	public static final String TAG_OBJECT_FILE_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_FILE_MIMETYPE = "mimeType";
	public static final String TAG_OBJECT_FILE_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_FILE_TIMESTAMP = "timestamp";
	public static final String TAG_OBJECT_FILE_URN = "urn";

	// Hashtags attributes
	public static final String TAG_OBJECT_HASHTAGS = "";
	public static final String TAG_OBJECT_HASHTAGS_REFERENCETYPE = "entityReferenceType";
	public static final String TAG_OBJECT_HASHTAGS_TAG = "tag";
	public static final String TAG_OBJECT_HASHTAGS_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_HASHTAGS_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_HASHTAGS_URN = "urn";
	// inside Hashtags tag attributes
	public static final String TAG_OBJECT_HASHTAGS_TAG_ACTIVEFLAG = "activeFlag";
	public static final String TAG_OBJECT_HASHTAGS_TAG_DESCRIPTION = "description";
	public static final String TAG_OBJECT_HASHTAGS_TAG_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_HASHTAGS_TAG_NAME = "name";
	public static final String TAG_OBJECT_HASHTAGS_TAG_URN = "urn";

	// Metadata attributes
	public static final String TAG_OBJECT_METADATA = "";
	public static final String TAG_OBJECT_METADATA_DATATYPE = "dataType";
	public static final String TAG_OBJECT_METADATA_REFERENCETYPE = "entityReferenceType";
	public static final String TAG_OBJECT_METADATA_KEY = "key";
	public static final String TAG_OBJECT_METADATA_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_METADATA_RAWVALUE = "rawValue";
	public static final String TAG_OBJECT_METADATA_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_METADATA_URN = "urn";

	// geospatial attributes
	public static final String TAG_OBJECT_GEOSPATIAL = "";
	public static final String TAG_OBJECT_GEOSPATIAL_REFERENCETYPE = "entityReferenceType";
	public static final String TAG_OBJECT_GEOSPATIAL_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_GEOSPATIAL_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_GEOSPATIAL_RELATEDREFERENCETYPE = "relatedEntityReferenceType";
	public static final String TAG_OBJECT_GEOSPATIAL_RELATEDREFERENCEURN = "relatedReferenceUrn";
	public static final String TAG_OBJECT_GEOSPATIAL_TYPE = "type";
	public static final String TAG_OBJECT_GEOSPATIAL_URN = "urn";

	// relationships attributes
	public static final String TAG_OBJECT_RELATIONSHIPS = "";
	public static final String TAG_OBJECT_RELATIONSHIPS_REFERENCETYPE = "entityReferenceType";
	public static final String TAG_OBJECT_RELATIONSHIPS_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_RELATIONSHIPS_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_RELATIONSHIPS_RELATEDREFERENCETYPE = "relatedEntityReferenceType";
	public static final String TAG_OBJECT_RELATIONSHIPS_RELATEDREFERENCEURN = "relatedReferenceUrn";
	public static final String TAG_OBJECT_RELATIONSHIPS_TYPE = "type";
	public static final String TAG_OBJECT_RELATIONSHIPS_URN = "urn";

	// relationships attributes
	public static final String TAG_OBJECT_RELATIONSHIPS1 = "";
	public static final String TAG_OBJECT_RELATIONSHIPS1_RELATIONSHIPDATA = "relationshipData";
	public static final String TAG_OBJECT_RELATIONSHIPS1_REFEREDOBJECTURN = "referedobjectUrn";
	public static final String TAG_OBJECT_RELATIONSHIPS1_REFEREDOBJECTNAME = "referredObjectName";
	public static final String TAG_OBJECT_RELATIONSHIPS1_RELATIONSHIPCOUNT = "relationshipCount";
	public static final String TAG_OBJECT_RELATIONSHIPS1_RELATIONSHIPTYPEDATA = "relationshipTypesData";
	public static final String TAG_OBJECT_RELATIONSHIPS1_TOTALCOUNT = "totalCount";

	// two object related relationships attributes
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1 = "";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPDATA = "relationshipData";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPTYPESDATA = "relationshipTypesData";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPURN = "relationshipUrn";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_REFEREDOBJECTTYPE = "relationshipType";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPPROPERTIESCOUNT = "relationshipPropertiesCount";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPHASHTAG = "Hashtag";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPMETADATA = "Metadata";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPFILE = "Files";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPGEOSPATIAL = "Geospatial";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPTIMELINE = "Timeline";
	public static final String TAG_OBJECT_INTERRELATIONSHIPS1_TOTALCOUNT = "totalCount";

	// interactions attributes
	public static final String TAG_OBJECT_INTERACTIONS = "";
	public static final String TAG_OBJECT_INTERACTIONS_INTERACTIONSDATA = "interactionsData";
	public static final String TAG_OBJECT_INTERACTIONS_REFEREDOBJECTURN = "referedobjectUrn";
	public static final String TAG_OBJECT_INTERACTIONS_REFEREDOBJECTNAME = "referredObjectName";
	public static final String TAG_OBJECT_INTERACTIONS_INTERACTIONCOUNT = "interactionCount";
	public static final String TAG_OBJECT_INTERACTIONS_INTERACTIONTYPEDATA = "interactionTypesData";
	public static final String TAG_OBJECT_INTERACTIONS_TOTALCOUNT = "totalCount";

	// two object related interactions attributes
	public static final String TAG_OBJECT_INTERINTERACTIONS = "";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONDATA = "interactionsData";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONTYPESDATA = "interactionTypesData";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONURN = "interactionUrn";
	public static final String TAG_OBJECT_INTERINTERACTIONS_REFEREDOBJECTTYPE = "interactionType";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONPROPERTIESCOUNT = "interactionPropertiesCount";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONHASHTAG = "Hashtag";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONMETADATA = "Metadata";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONFILE = "Files";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONGEOSPATIAL = "Geospatial";
	public static final String TAG_OBJECT_INTERINTERACTIONS_INTERACTIONTIMELINE = "Timeline";
	public static final String TAG_OBJECT_INTERINTERACTIONS_TOTALCOUNT = "totalCount";
	// Address attributes
	public static final String TAG_OBJECT_ADDRESS = "";
	public static final String TAG_OBJECT_ADDRESS_CITY = "city";
	public static final String TAG_OBJECT_ADDRESS_COUNTRYABBR = "countryAbbreviation";
	public static final String TAG_OBJECT_ADDRESS_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_ADDRESS_LINE1 = "line1";
	public static final String TAG_OBJECT_ADDRESS_LINE2 = "line2";
	public static final String TAG_OBJECT_ADDRESS_OBJECT = "object";
	public static final String TAG_OBJECT_ADDRESS_POSTALCODE = "postalCode";
	public static final String TAG_OBJECT_ADDRESS_STATEPROVINCE = "stateProvince";
	public static final String TAG_OBJECT_ADDRESS_TIMESTAMP = "timestamp";
	public static final String TAG_OBJECT_ADDRESS_TYPE = "type";
	public static final String TAG_OBJECT_ADDRESS_URN = "urn";

	// inside address tag attributes
	public static final String TAG_OBJECT_ADDRESS_OBJECT_ACTIVEFLAG = "activeFlag";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_DESCRIPTION = "description";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_NAME = "name";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_OBJECTURN = "objectUrn";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_TYPE = "type";
	public static final String TAG_OBJECT_ADDRESS_OBJECT_URN = "urn";

	// library attributes
	public static final String TAG_OBJECT_LIBRARY_OBJECT = "libraryElementData";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_URN = "urn";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_LIBRARYNAME = "libraryName";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_RELATEDENTITYREFTYPE = "relatedEntityReferenceType";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_ENTITYREFTYPE = "entityReferenceType";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_OBJECTTYPE = "type";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_REFERENCEURN = "referenceUrn";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_RELATEDREFERENCEURN = "relatedReferenceUrn";

	// library children attributes
	public static final String TAG_OBJECT_LIBRARY_CHILDREN_OBJECT = "libraryData";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_URN = "urn";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_CHILDAVAILABLE = "isChildAvailable";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_PARENT = "parent";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_NAME = "name";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_DESCRIPTION = "description";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_LIBRARYELEMENTTYPE = "libraryElementType";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_TYPE = "type";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_ACTIVEFLAG = "activeFlag";

	// timeline attributes
	public static final String TAG_OBJECT_TIMELINE_OBJECT = "timeLineData";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_URN = "urn";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_ENTITYREFTYPE = "entityReferenceType";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_NAME = "name";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_DESC = "description";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_HIGHLIGHTFLAG = "highlightFlag";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_REFERENCEUUID = "referenceUuid";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_LASTMODIFIEDTIMESTAMP = "lastModifiedTimestamp";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_ACTIVEFLAG = "activeFlag";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_VISIBLEFLAG = "visibleFlag";
	public static final String TAG_OBJECT_TIMELINE_OBJECT_RECORDTIMESTAMP = "recordedTimestamp";

}

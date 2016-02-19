package com.smartrac.objectexplore.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSDataListAdapter;
import com.smartrac.objectexplore.adapter.SSOuterListAdapter;
import com.smartrac.objectexplore.common.SSConnectionDetector;
import com.smartrac.objectexplore.common.SSConstants;
import com.smartrac.objectexplore.dialog.SSAddressDialog;
import com.smartrac.objectexplore.dialog.SSFilesDialog;
import com.smartrac.objectexplore.dialog.SSGeospatialDialog;
import com.smartrac.objectexplore.dialog.SSHashtagsDialog;
import com.smartrac.objectexplore.dialog.SSLibraryDialog;
import com.smartrac.objectexplore.dialog.SSLoginErrorDialog;
import com.smartrac.objectexplore.dialog.SSMetadataDialog;
import com.smartrac.objectexplore.dialog.SSNetworkErrorDialog;
import com.smartrac.objectexplore.dialog.SSObjectAddressEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectFilesEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectGeospatialEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectHashtagsEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectInteractionsEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectLibraryEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectListEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectMetadataEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectRelationShipsEmptyDialog;
import com.smartrac.objectexplore.dialog.SSObjectTimelineEmptyDialog;
import com.smartrac.objectexplore.dialog.SSTimelineDialog;
import com.smartrac.objectexplore.interfaces.SSOnListItemDisplayListener;
import com.smartrac.objectexplore.models.SSAddressModel;
import com.smartrac.objectexplore.models.SSLoginModel;
import com.smartrac.objectexplore.models.SSMetadataModel;
import com.smartrac.objectexplore.models.SSObjectAddressModel;
import com.smartrac.objectexplore.models.SSObjectFilesModel;
import com.smartrac.objectexplore.models.SSObjectGeospatialModel;
import com.smartrac.objectexplore.models.SSObjectHashtagsModel;
import com.smartrac.objectexplore.models.SSObjectLibraryModel;
import com.smartrac.objectexplore.models.SSObjectTimelineModel;
import com.smartrac.objectexplore.models.SSObjectTotalCountModel;
import com.smartrac.objectexplore.models.SSOuterModel;
import com.smartrac.objectexplore.models.SSTagsModel;
import com.smartrac.objectexplore.services.SSServiceHandler;

public class SSObjectDetailFragment extends Fragment implements OnClickListener {

	ImageView icon_list, icon_profile, imgv_center;
	TextView selected_object, object_selection;

	TextView[] circleImage;
	TextView[] circleText;
	ImageView[] outerImage;
	TextView[] outerText;

	View view;
	RelativeLayout main;
	Dialog dialog = null;

	SSLoginModel loginhandler;
	String username, password, instance;
	String objectURN, selectedObject;
	String selectedListItem;

	ArrayList<SSObjectFilesModel> objectFilesList;
	ArrayList<SSObjectHashtagsModel> objectHashtagsList;
	ArrayList<SSObjectLibraryModel> objectLibraryList;
	ArrayList<SSObjectTimelineModel> objectTimelineList;
	ArrayList<SSMetadataModel> objectMetadataList;
	ArrayList<SSObjectGeospatialModel> objectGeospatialList;
	ArrayList<SSObjectTotalCountModel> objectTotalCountList;
	ArrayList<SSOuterModel> objectOuterList;
	ArrayList<SSObjectAddressModel> objectAddressList;
	ArrayList<SSAddressModel> addressDetails;
	ArrayList<SSTagsModel> tagList;
	ArrayList<String> relationsDataList;

	String objectFiles = null;
	String objectHashtags = null;
	String objectMetadata = null;
	String objectGeospatial = null;
	String objectAddress = null;
	String objectRelationships = null;
	String objectInteractions = null;
	String objectInterInteractions = null;
	String relationshipsData = null;
	String objectLibrary = null;
	String objectTimeline = null;
	String basicAuth;

	int totalCount = 0;
	String objectRefName, objectRefUrn;

	SSOnListItemDisplayListener mCallback;
	private SSConnectionDetector cd;
	private Boolean isInternetPresent = false;

	private boolean listFlag = false;
	private boolean profileFlag = false;

	private int[] mImages = { R.drawable.icon_metadata,
			R.drawable.icon_relationships, R.drawable.icon_files,
			R.drawable.icon_timeline, R.drawable.icon_address,
			R.drawable.icon_library, R.drawable.icon_hashtags,
			R.drawable.icon_geospatial, R.drawable.icon_interactions };
	private String[] mTitles = { "Metadata", "Relationships", "Files",
			"Timeline", "Address", "Library", "Hashtags", "Geospatial",
			"Interactions" };

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (SSOnListItemDisplayListener) activity;

		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnListItemDisplayListenerer");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		cd = new SSConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		loginhandler = new SSLoginModel();
		username = loginhandler.getUsername();
		password = loginhandler.getPassword();
		basicAuth = loginhandler.validateUser(username, password);

		objectGeospatialList = new ArrayList<SSObjectGeospatialModel>();
		objectAddressList = new ArrayList<SSObjectAddressModel>();
		addressDetails = new ArrayList<SSAddressModel>();

		circleImage = new TextView[9];
		circleText = new TextView[9];

		outerImage = new ImageView[10];
		outerText = new TextView[10];

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.fragment_object, container, false);

		icon_list = (ImageView) view.findViewById(R.id.icon_list_detail);
		icon_profile = (ImageView) view.findViewById(R.id.icon_profile_detail);
		selected_object = (TextView) view.findViewById(R.id.textv_center);
		object_selection = (TextView) view.findViewById(R.id.object_text);
		imgv_center = (ImageView) view.findViewById(R.id.imgv_center);
		imgv_center.setOnClickListener(this);
		object_selection.setText(selectedObject);
		selected_object.setText(selectedObject);

		icon_list.setOnClickListener(this);
		icon_profile.setOnClickListener(this);

		main = (RelativeLayout) view.findViewById(R.id.mainCircle);
		createCenterView();

		return view;
	}

	private View createCenterView() {
		int numViews = 9;
		for (int i = 0; i < numViews; i++) {

			circleImage[i] = new TextView(getActivity());

			RelativeLayout.LayoutParams lpImage = new RelativeLayout.LayoutParams(
					100, 100);
			circleImage[i].setId(i);
			circleImage[i].setMaxWidth(90);
			circleImage[i].setMaxHeight(90);

			circleImage[i].setBackgroundResource(mImages[i]);

			lpImage.addRule(RelativeLayout.CENTER_IN_PARENT);
			circleImage[i].setLayoutParams(lpImage);

			circleText[i] = new TextView(getActivity());

			RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(
					100, 100);
			circleText[i].setText(mTitles[i]);
			circleText[i].setTextColor(Color.parseColor("#3ae014"));
			circleText[i].setTextSize(16);

			lpText.addRule(RelativeLayout.CENTER_IN_PARENT);

			circleText[i].setLayoutParams(lpText);

			float angleDeg = i * 360.0f / numViews - 90.0f;
			float angleRad = (float) (angleDeg * Math.PI / 180.0f);

			circleImage[i].setTranslationX(180 * (float) Math.cos(angleRad));
			circleImage[i].setTranslationY(180 * (float) Math.sin(angleRad));

			circleText[i]
					.setTranslationX(180 * (float) Math.cos(angleRad) + 20);
			circleText[i]
					.setTranslationY(180 * (float) Math.sin(angleRad) + 90);

			main.addView(circleImage[i]);
			main.addView(circleText[i]);
			circleImage[i].setOnClickListener(this);
		}
		return view;
	}

	private void createRelationshipsView(int count) {

		int numViews = (count < 11) ? count : 10;

		for (int i = 0; i < numViews; i++) {

			outerImage[i] = new ImageView(getActivity());

			RelativeLayout.LayoutParams lpImage = new RelativeLayout.LayoutParams(
					100, 100);
			outerImage[i].setId(i);
			outerImage[i].setMaxWidth(70);
			outerImage[i].setMaxHeight(70);

			outerImage[i].setImageResource(R.drawable.icon_object_ir);

			lpImage.addRule(RelativeLayout.CENTER_IN_PARENT);
			outerImage[i].setLayoutParams(lpImage);

			outerText[i] = new TextView(getActivity());

			RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(
					100, 100);
			objectRefName = objectTotalCountList.get(i).getReferredObjectName();

			outerText[i].setText(objectRefName);

			outerText[i].setTextColor(Color.parseColor("#3ae014"));
			outerText[i].setTextSize(16);

			lpText.addRule(RelativeLayout.CENTER_IN_PARENT);

			outerText[i].setLayoutParams(lpText);
			float angleDeg = 0.0f;
			float angleRad = 0.0f;

			if (selectedId == 1) {
				angleDeg = i * 360.0f / 10 - 90.0f;

				angleRad = (float) (angleDeg * Math.PI / 180.0f);
			}
			if (selectedId == 8) {
				angleDeg = (9 - i) * 360.0f / 10 - 90.0f;

				angleRad = (float) (angleDeg * Math.PI / 180.0f);
			}

			outerImage[i].setTranslationX(260 * (float) Math.cos(angleRad));
			outerImage[i].setTranslationY(260 * (float) Math.sin(angleRad));

			outerText[i].setTranslationX(260 * (float) Math.cos(angleRad) + 30);
			outerText[i].setTranslationY(260 * (float) Math.sin(angleRad) + 90);

			main.addView(outerImage[i]);
			main.addView(outerText[i]);
			outerImage[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (isInternetPresent) {

						objectRefUrn = objectTotalCountList.get(v.getId())
								.getReferedobjectUrn();
						objectRefName = objectTotalCountList.get(v.getId())
								.getReferredObjectName();
						if (selectedId == 1) {
							new RelationshipsRetrieve(v.getId())
									.execute(SSConstants.serverURLPath
											+ "/rest/plugin/relationships/Object/"
											+ objectURN
											+ "?relatedReferenceUrn="
											+ objectRefUrn
											+ "&countView=true&rowNumber=0&pageRows=25");
						}
						if (selectedId == 8) {
							new InteractionsRetrieve(v.getId())
									.execute(SSConstants.serverURLPath
											+ "/rest/plugin/interactions?objectUrn="
											+ objectURN
											+ "&referedobjectUrn="
											+ objectRefUrn
											+ "&countView=true&rowNumber=0&pageRows=30");
						}

					} else {

						dialog = new SSNetworkErrorDialog(getActivity());
						dialog.show();

					}
				}

			});
		}

	}

	// Value -1 means No item is selected
	int selectedId = -1;

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.icon_list_detail:
			listFlag = !listFlag;
			mCallback.removeListItem(listFlag);
			break;

		case R.id.icon_profile_detail:
			profileFlag = !profileFlag;
			mCallback.removeProfileItem(profileFlag);
			break;
		case R.id.imgv_center:

			mCallback.addCentralView(objectURN, selectedObject);
			break;

		case 0:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectMetadataRetrieve(selectedListItem)
							.execute(SSConstants.serverURLPath
									+ "/rest/metadata/Object/" + objectURN);
				} else {
					dialog = new SSMetadataDialog(getActivity(),
							objectMetadataList, selectedObject, selectedId,
							selectedListItem);

					dialog.show();
				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}
			break;
		case 1:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					for (int i = 0; i < 9; i++) {
						float angleDeg = i * 360.0f / 9 - 90.0f;
						float angleRad = (float) (angleDeg * Math.PI / 180.0f);
						circleImage[i].setScaleX(0.9f);
						circleImage[i].setScaleY(0.9f);

						circleImage[i].setTranslationX(160 * (float) Math
								.cos(angleRad));
						circleImage[i].setTranslationY(160 * (float) Math
								.sin(angleRad));

						circleText[i].setTranslationX(160 * (float) Math
								.cos(angleRad) + 20);

						circleText[i].setTranslationY(160 * (float) Math
								.sin(angleRad) + 90);
						circleText[i].setTextColor(Color.parseColor("#ffffff"));

					}

					new ObjectRelationshipRetrieve()
							.execute(SSConstants.serverURLPath
									+ "/rest/plugin/relationships/Object/"
									+ objectURN
									+ "?countView=true&rowNumber=0&pageRows=25");
					// ec2-52-11-184-204.us-west-2.compute.amazonaws.com:8080/rest/plugin/relationships/Object/Urn:Audi?countView=true&rowNumber=0&pageRows=25

				} else {

					createRelationshipsView(totalCount);

				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}

			break;

		case 2:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectFilesRetrieve(selectedListItem)
							.execute(SSConstants.serverURLPath
									+ "/rest/files/Object/" + objectURN);
				} else {
					dialog = new SSFilesDialog(getActivity(), objectFilesList,
							selectedId, selectedListItem);
					dialog.show();
				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}
			break;
		case 3:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectTimelineRetrieve(selectedListItem, selectedId)
							.execute(SSConstants.serverURLPath
									+ "/rest/plugin/timelines");
				} else {
					dialog = new SSTimelineDialog(getActivity(),
							objectTimelineList,
							objectTimelineList.get(selectedId), selectedObject,
							selectedListItem);
					dialog.show();
				}

			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}

			break;
		case 4:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectAddressRetrieve()
							.execute(SSConstants.serverURLPath
									+ "/rest/objects/object/" + objectURN
									+ "/address?view=Standard&count=10");
				} else {
					dialog = new SSAddressDialog(getActivity(),
							objectAddressList, addressDetails, selectedObject);
					dialog.show();
				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}

			break;
		case 5:
			if (isInternetPresent) {
				new ObjectLibraryRetrieve().execute(SSConstants.serverURLPath
						+ "/rest/plugin/library/Object/" + objectURN);

			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}

			break;

		case 6:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectHashtagsRetrieve(selectedListItem)
							.execute(SSConstants.serverURLPath
									+ "/rest/tags?entityReferenceType=Object&referenceUrn="
									+ objectURN);
				} else {
					dialog = new SSHashtagsDialog(getActivity(),
							objectHashtagsList, tagList, selectedObject,
							selectedId, selectedListItem);

					dialog.show();

				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}
			break;
		case 7:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();
					new ObjectGeospatialRetrieve(selectedListItem)
							.execute(SSConstants.serverURLPath
									+ "/rest/relationships/Object/" + objectURN);
				} else {
					dialog = new SSGeospatialDialog(getActivity(),
							objectGeospatial, selectedObject, selectedId,
							selectedListItem);
					dialog.show();
				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}
			break;
		case 8:
			if (isInternetPresent) {
				if (selectedId != view.getId()) {
					selectedId = view.getId();

					for (int i = 0; i < 9; i++) {

						float angleDeg = i * 360.0f / 9 - 90.0f;
						float angleRad = (float) (angleDeg * Math.PI / 180.0f);
						circleImage[i].setScaleX(0.9f);
						circleImage[i].setScaleY(0.9f);

						circleImage[i].setTranslationX(160 * (float) Math
								.cos(angleRad));
						circleImage[i].setTranslationY(160 * (float) Math
								.sin(angleRad));

						circleText[i].setTranslationX(160 * (float) Math
								.cos(angleRad) + 20);

						circleText[i].setTranslationY(160 * (float) Math
								.sin(angleRad) + 80);
						circleText[i].setTextColor(Color.parseColor("#ffffff"));

					}

					new ObjectInteractionsRetrieve()
							.execute(SSConstants.serverURLPath
									+ "/rest/plugin/interactions?objectUrn="
									+ objectURN
									+ "&countView=true&rowNumber=0&pageRows=30");

				} else {

					createRelationshipsView(totalCount);

				}
			} else {

				dialog = new SSNetworkErrorDialog(getActivity());
				dialog.show();

			}

			break;

		default:
			break;
		}

	}

	public void updateView(String object, String name) {

		objectURN = object;
		selectedObject = name;

	}

	private class ObjectHashtagsRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		String category;

		public ObjectHashtagsRetrieve(String listItem) {
			// TODO Auto-generated constructor stub
			this.category = listItem;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectHashtags = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectHashtags != null) {
				if (!objectHashtags.equals("401")) {
					objectHashtagsList = new ArrayList<SSObjectHashtagsModel>();
					tagList = new ArrayList<SSTagsModel>();
					try {
						JSONArray objects = new JSONArray(objectHashtags);

						if (objects.length() != 0 || objects != null) {

							// looping through All objects
							for (int i = 0; i < objects.length(); i++) {
								JSONObject jsonObject = objects
										.getJSONObject(i);

								String hashTagsRefType = jsonObject
										.getString(SSConstants.TAG_OBJECT_HASHTAGS_REFERENCETYPE);

								long hashTagsLastTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_FILE_LASTMODIFIEDTIMESTAMP);

								String hashTagsRefUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_HASHTAGS_REFERENCEURN);

								String hashTagsUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_HASHTAGS_URN);

								SSObjectHashtagsModel hashtagsModel = new SSObjectHashtagsModel();
								hashtagsModel
										.setEntityReferenceType(hashTagsRefType);

								hashtagsModel
										.setLastModifiedTimestamp(hashTagsLastTimestamp);

								hashtagsModel.setReferenceUrn(hashTagsRefUrn);

								hashtagsModel.setUrn(hashTagsUrn);

								// parse nested json object
								String tag = jsonObject
										.getString(SSConstants.TAG_OBJECT_HASHTAGS_TAG);

								if (tag != null) {

									JSONObject tagJSON = jsonObject
											.getJSONObject(SSConstants.TAG_OBJECT_HASHTAGS_TAG);

									boolean tagFlag = tagJSON
											.getBoolean(SSConstants.TAG_OBJECT_HASHTAGS_TAG_ACTIVEFLAG);
									String tagDesc = tagJSON
											.getString(SSConstants.TAG_OBJECT_HASHTAGS_TAG_DESCRIPTION);
									long tagLastTimeStamp = tagJSON
											.getLong(SSConstants.TAG_OBJECT_HASHTAGS_TAG_LASTMODIFIEDTIMESTAMP);
									String tagName = tagJSON
											.getString(SSConstants.TAG_OBJECT_HASHTAGS_TAG_NAME);

									String tagUrn = tagJSON
											.getString(SSConstants.TAG_OBJECT_HASHTAGS_TAG_URN);

									SSTagsModel tagModel = new SSTagsModel();
									tagModel.setActiveFlag(tagFlag);
									tagModel.setDescription(tagDesc);
									tagModel.setLastModifiedTimestamp(tagLastTimeStamp);
									tagModel.setName(tagName);
									tagModel.setUrn(tagUrn);
									if (tagModel != null) {
										tagList.add(tagModel);
									}

								}

								if (hashtagsModel != null) {
									objectHashtagsList.add(hashtagsModel);
								}

							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectHashtags;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					dialog = new SSHashtagsDialog(getActivity(),
							objectHashtagsList, tagList, selectedObject,
							selectedId, category);

					dialog.show();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectHashtagsEmptyDialog(getActivity());
				dialog.show();
				Log.d("Exception", "No data Found");
			}
		}
	}

	private class ObjectMetadataRetrieve extends
			AsyncTask<String, String, JSONArray> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray objects;
		String category;

		public ObjectMetadataRetrieve(String listItem) {
			// TODO Auto-generated constructor stub
			this.category = listItem;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected JSONArray doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectMetadata = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectMetadata != null) {
				if (!objectMetadata.equals("401")) {
					objectMetadataList = new ArrayList<SSMetadataModel>();
					try {
						objects = new JSONArray(objectMetadata);

						if (objects != null) {

							// looping through All objects
							for (int i = 0; i < objects.length(); i++) {
								JSONObject jsonObject = objects
										.getJSONObject(i);

								String metadataType = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_DATATYPE);

								String metadataRefType = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_REFERENCETYPE);
								String metadataKey = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_KEY);

								long metadataLastTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_METADATA_LASTMODIFIEDTIMESTAMP);
								String metadataRawValue = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_RAWVALUE);
								String metadataRefUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_REFERENCEURN);

								String metadataUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_METADATA_URN);

								SSMetadataModel metadataModel = new SSMetadataModel();
								metadataModel.setDataType(metadataType);
								metadataModel
										.setEntityReferenceType(metadataRefType);
								metadataModel.setKey(metadataKey);
								metadataModel
										.setLastModifiedTimestamp(metadataLastTimestamp);
								metadataModel.setRawValue(metadataRawValue);
								metadataModel.setReferenceUrn(metadataRefUrn);

								metadataModel.setUrn(metadataUrn);

								if (metadataModel != null) {
									objectMetadataList.add(metadataModel);
								}

							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objects;

		}

		@Override
		protected void onPostExecute(JSONArray result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			if (result.length() != 0) {

				dialog = new SSMetadataDialog(getActivity(),
						objectMetadataList, selectedObject, selectedId,
						category);

				dialog.show();
			} else {
				dialog = new SSObjectMetadataEmptyDialog(getActivity());
				dialog.show();
			}

		}
	}

	private class ObjectGeospatialRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		String category;

		public ObjectGeospatialRetrieve(String listItem) {
			// TODO Auto-generated constructor stub
			this.category = listItem;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectGeospatial = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectGeospatial != null) {
				if (!objectGeospatial.equals("401")) {
					try {
						JSONArray objects = new JSONArray(objectGeospatial);
						// .getJSONArray(SSConstants.TAG_OBJECT);

						// looping through All objects
						for (int i = 0; i < objects.length(); i++) {
							JSONObject jsonObject = objects.getJSONObject(i);

							String geospatialRefType = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_REFERENCETYPE);

							long geospatialLastTimestamp = jsonObject
									.getLong(SSConstants.TAG_OBJECT_GEOSPATIAL_LASTMODIFIEDTIMESTAMP);
							String geospatialRefUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_REFERENCEURN);
							String geospatialRelatedRefType = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_RELATEDREFERENCETYPE);

							String geospatialRelatedRefUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_RELATEDREFERENCEURN);
							String geospatialType = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_TYPE);
							String geospatialUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_GEOSPATIAL_URN);

							SSObjectGeospatialModel geospatialModel = new SSObjectGeospatialModel();
							geospatialModel
									.setEntityReferenceType(geospatialRefType);
							geospatialModel
									.setLastModifiedTimestamp(geospatialLastTimestamp);
							geospatialModel.setReferenceUrn(geospatialRefUrn);
							geospatialModel
									.setRelatedEntityReferenceType(geospatialRelatedRefType);
							geospatialModel
									.setRelatedReferenceUrn(geospatialRelatedRefUrn);
							geospatialModel.setType(geospatialType);
							geospatialModel.setUrn(geospatialUrn);

							if (geospatialModel != null) {
								objectGeospatialList.add(geospatialModel);
							}

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectGeospatial;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					dialog = new SSGeospatialDialog(getActivity(),
							objectGeospatial, selectedObject, selectedId,
							category);
					dialog.show();

				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}
			} else {
				dialog = new SSObjectGeospatialEmptyDialog(getActivity());
				dialog.show();
			}

		}
	}

	private class ObjectFilesRetrieve extends
			AsyncTask<String, String, JSONArray> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray objects;
		String category;

		public ObjectFilesRetrieve(String listItem) {
			// TODO Auto-generated constructor stub
			this.category = listItem;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected JSONArray doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectFiles = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectFiles != null) {
				if (!objectFiles.equals("401")) {
					objectFilesList = new ArrayList<SSObjectFilesModel>();
					try {
						objects = new JSONArray(objectFiles);
						// .getJSONArray(SSConstants.TAG_OBJECT);
						if (objects != null) {
							// looping through All objects
							for (int i = 0; i < objects.length(); i++) {
								JSONObject jsonObject = objects
										.getJSONObject(i);

								String fileRefType = jsonObject
										.getString(SSConstants.TAG_OBJECT_FILE_REFERENCETYPE);

								String filename = jsonObject
										.getString(SSConstants.TAG_OBJECT_FILE_FILENAME);

								long fileLastTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_FILE_LASTMODIFIEDTIMESTAMP);
								String mimeType = jsonObject
										.getString(SSConstants.TAG_OBJECT_FILE_MIMETYPE);
								String fileRefUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_FILE_REFERENCEURN);
								long fileTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_FILE_TIMESTAMP);
								String fileUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_FILE_URN);

								SSObjectFilesModel filesModel = new SSObjectFilesModel();
								filesModel.setEntityReferenceType(fileRefType);
								filesModel.setFileName(filename);
								filesModel
										.setLastModifiedTimestamp(fileLastTimestamp);
								filesModel.setMimeType(mimeType);
								filesModel.setReferenceUrn(fileRefUrn);
								filesModel.setTimestamp(fileTimestamp);
								filesModel.setUrn(fileUrn);

								if (filesModel != null) {
									objectFilesList.add(filesModel);
								}

							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objects;

		}

		@Override
		protected void onPostExecute(JSONArray result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result.length() != 0) {
				dialog = new SSFilesDialog(getActivity(), objectFilesList,
						selectedId, category);
				dialog.show();

			} else {
				dialog = new SSObjectFilesEmptyDialog(getActivity());
				dialog.show();
			}
		}
	}

	private class ObjectAddressRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray address_object;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectAddress = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectAddress != null) {
				if (!objectAddress.equals("401")) {
					try {
						address_object = new JSONArray(objectAddress);
						// .getJSONArray(SSConstants.TAG_OBJECT);
						if (address_object != null) {
							// looping through All objects
							for (int i = 0; i < address_object.length(); i++) {
								JSONObject jsonObject = address_object
										.getJSONObject(i);

								String city = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_CITY);

								String countryAbbr = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_COUNTRYABBR);

								long addressLastTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_ADDRESS_LASTMODIFIEDTIMESTAMP);
								String addressLine1 = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_LINE1);
								String addressLine2 = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_LINE2);
								String postalCode = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_POSTALCODE);
								String stateProvince = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_STATEPROVINCE);
								long addressTimestamp = jsonObject
										.getLong(SSConstants.TAG_OBJECT_ADDRESS_TIMESTAMP);
								String addressType = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_TYPE);
								String addressUrn = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_URN);

								SSObjectAddressModel addressModel = new SSObjectAddressModel();
								addressModel.setCity(city);
								addressModel
										.setCountryAbbreviation(countryAbbr);
								addressModel
										.setLastModifiedTimestamp(addressLastTimestamp);
								addressModel.setLine1(addressLine1);
								addressModel.setLine2(addressLine2);
								addressModel.setPostalCode(postalCode);
								addressModel.setStateProvince(stateProvince);
								addressModel.setTimestamp(addressTimestamp);
								addressModel.setType(addressType);
								addressModel.setUrn(addressUrn);

								String addressObject = jsonObject
										.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT);

								if (addressObject != null) {

									JSONObject addressObjectJSON = jsonObject
											.getJSONObject(SSConstants.TAG_OBJECT_ADDRESS_OBJECT);

									boolean addressObjectFlag = addressObjectJSON
											.getBoolean(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_ACTIVEFLAG);
									String addressObjectDesc = addressObjectJSON
											.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_DESCRIPTION);
									long addressObjectLastTimeStamp = addressObjectJSON
											.getLong(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_LASTMODIFIEDTIMESTAMP);
									String addressObjectName = addressObjectJSON
											.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_NAME);
									String addressObjectObjectUrn = addressObjectJSON
											.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_OBJECTURN);
									String addressObjectType = addressObjectJSON
											.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_TYPE);

									String addressObjectUrn = addressObjectJSON
											.getString(SSConstants.TAG_OBJECT_ADDRESS_OBJECT_URN);

									SSAddressModel addressObjectModel = new SSAddressModel();
									addressObjectModel
											.setActiveFlag(addressObjectFlag);
									addressObjectModel
											.setDescription(addressObjectDesc);
									addressObjectModel
											.setLastModifiedTimestamp(addressObjectLastTimeStamp);
									addressObjectModel
											.setName(addressObjectName);
									addressObjectModel
											.setObjectUrn(addressObjectObjectUrn);
									addressObjectModel
											.setType(addressObjectType);
									addressObjectModel.setUrn(addressObjectUrn);
									if (addressObjectModel != null) {
										addressDetails.add(addressObjectModel);
									}

								}
								if (addressModel != null) {
									objectAddressList.add(addressModel);
								}

							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectAddress;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					dialog = new SSAddressDialog(getActivity(),
							objectAddressList, addressDetails, selectedObject);
					dialog.show();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectAddressEmptyDialog(getActivity());
				dialog.show();
			}
		}

	}

	private class ObjectRelationshipRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;

		JSONArray relationsData;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectRelationships = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);

			if (objectRelationships != null) {
				if (!objectRelationships.equals("401")) {
					Log.d("Response: ", "> " + objectRelationships);
					objectTotalCountList = new ArrayList<SSObjectTotalCountModel>();
					try {
						JSONObject relations = new JSONObject(
								objectRelationships);
						relationsData = relations
								.getJSONArray(SSConstants.TAG_OBJECT_RELATIONSHIPS1_RELATIONSHIPDATA);

						for (int i = 0; i < relationsData.length(); i++) {
							JSONObject jsonObject = relationsData
									.getJSONObject(i);
							String refObjectUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_RELATIONSHIPS1_REFEREDOBJECTURN);
							String refObjectName = jsonObject
									.getString(SSConstants.TAG_OBJECT_RELATIONSHIPS1_REFEREDOBJECTNAME);
							int relCount = jsonObject
									.getInt(SSConstants.TAG_OBJECT_RELATIONSHIPS1_RELATIONSHIPCOUNT);
							SSObjectTotalCountModel relationsModel = new SSObjectTotalCountModel();
							relationsModel.setReferedobjectUrn(refObjectUrn);
							relationsModel.setReferredObjectName(refObjectName);
							relationsModel.setRelationshipCount(relCount);
							if (objectTotalCountList != null) {
								objectTotalCountList.add(relationsModel);
							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectRelationships;

		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {

					totalCount = relationsData.length();
					String numberCount = Integer.toString(totalCount);

					circleImage[selectedId]
							.setBackgroundResource(R.drawable.black_circle);
					circleImage[selectedId].setTextColor(Color
							.parseColor("#3ae014"));
					circleImage[selectedId].setText(numberCount);
					circleImage[selectedId].setTextSize(20);
					circleImage[selectedId].setGravity(Gravity.CENTER);
					circleImage[selectedId].setScaleX(0.7f);
					circleImage[selectedId].setScaleY(0.7f);

					ObjectAnimator anim = (ObjectAnimator) AnimatorInflater
							.loadAnimator(getActivity(), R.anim.flipping);
					anim.setTarget(circleImage[selectedId]);
					anim.start();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectRelationShipsEmptyDialog(getActivity());
				dialog.show();
			}

		}
	}

	private class RelationshipsRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray relTypesData;
		int itemPos;

		public RelationshipsRetrieve(int selectedItemId) {
			// TODO Auto-generated constructor stub
			itemPos = selectedItemId;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			relationshipsData = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);

			if (relationshipsData != null) {
				if (!relationshipsData.equals("401")) {
					Log.d("Response: ", "> " + relationshipsData);
					try {
						JSONObject objectRel = new JSONObject(relationshipsData);
						// String relData = objectRel
						// .getString(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPDATA);
						relTypesData = objectRel
								.getJSONArray(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPTYPESDATA);
						objectOuterList = new ArrayList<SSOuterModel>();
						relationsDataList = new ArrayList<String>();
						for (int i = 0; i < relTypesData.length(); i++) {

							JSONObject jsonObject = relTypesData
									.getJSONObject(i);
							String relationshipUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPURN);
							String relationshipType = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_REFEREDOBJECTTYPE);
							String relationshipPropertiesCount = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPPROPERTIESCOUNT);

							if (relationshipPropertiesCount != null) {
								JSONObject relTypes = jsonObject
										.getJSONObject(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPPROPERTIESCOUNT);

								if (relTypes
										.has(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPHASHTAG)) {

									relationsDataList.add("Hashtag");

								}

								if (relTypes
										.has(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPMETADATA)) {

									relationsDataList.add("Metadata");

								}

								if (relTypes
										.has(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPFILE)) {

									relationsDataList.add("Files");

									if (relTypes
											.has(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPGEOSPATIAL)) {

										relationsDataList.add("Geospatial");

									}

									if (relTypes
											.has(SSConstants.TAG_OBJECT_INTERRELATIONSHIPS1_RELATIONSHIPTIMELINE)) {

										relationsDataList.add("Timeline");

									}

								}

								SSOuterModel relModel = new SSOuterModel();
								relModel.setRelationshipType(relationshipType);
								relModel.setRelationshipUrn(relationshipUrn);

								if (objectOuterList != null) {

									objectOuterList.add(relModel);
								}

							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return relationshipsData;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {

					if (relTypesData.length() != 0) {

						showPopup(outerImage[itemPos]);

					}
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectListEmptyDialog(getActivity());
				dialog.show();
			}
		}

	}

	private class ObjectInteractionsRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray interactionArray;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectInteractions = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);

			if (objectInteractions != null) {
				if (!objectInteractions.equals("401")) {
					Log.d("Response: ", "> " + objectInteractions);
					objectTotalCountList = new ArrayList<SSObjectTotalCountModel>();
					try {
						JSONObject relations = new JSONObject(
								objectInteractions);
						interactionArray = relations
								.getJSONArray(SSConstants.TAG_OBJECT_INTERACTIONS_INTERACTIONSDATA);

						for (int i = 0; i < interactionArray.length(); i++) {
							JSONObject jsonObject = interactionArray
									.getJSONObject(i);
							String refObjectUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERACTIONS_REFEREDOBJECTURN);
							String refObjectName = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERACTIONS_REFEREDOBJECTNAME);
							int interCount = jsonObject
									.getInt(SSConstants.TAG_OBJECT_INTERACTIONS_INTERACTIONCOUNT);
							SSObjectTotalCountModel interactionsModel = new SSObjectTotalCountModel();
							interactionsModel.setReferedobjectUrn(refObjectUrn);
							interactionsModel
									.setReferredObjectName(refObjectName);
							interactionsModel.setRelationshipCount(interCount);
							if (objectTotalCountList != null) {
								objectTotalCountList.add(interactionsModel);
							}
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectInteractions;

		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					totalCount = interactionArray.length();
					String numberCount = Integer.toString(totalCount);

					circleImage[selectedId]
							.setBackgroundResource(R.drawable.black_circle);
					circleImage[selectedId].setTextColor(Color.WHITE);
					circleImage[selectedId].setText(numberCount);
					circleImage[selectedId].setTextSize(20);
					circleImage[selectedId].setGravity(Gravity.CENTER);
					circleImage[selectedId].setScaleX(0.7f);
					circleImage[selectedId].setScaleY(0.7f);

					ObjectAnimator anim = (ObjectAnimator) AnimatorInflater
							.loadAnimator(getActivity(), R.anim.flipping);
					anim.setTarget(circleImage[selectedId]);
					anim.start();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectInteractionsEmptyDialog(getActivity());
				dialog.show();
			}

		}
	}

	private class InteractionsRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		JSONArray interTypesData;
		int itemPos;

		public InteractionsRetrieve(int viewId) {
			// TODO Auto-generated constructor stub
			itemPos = viewId;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectInterInteractions = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);

			if (objectInterInteractions != null) {
				if (!objectInterInteractions.equals("401")) {
					Log.d("Response: ", "> " + objectInterInteractions);
					try {
						JSONObject objectInter = new JSONObject(
								objectInterInteractions);

						interTypesData = objectInter
								.getJSONArray(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONTYPESDATA);

						objectOuterList = new ArrayList<SSOuterModel>();

						for (int i = 0; i < interTypesData.length(); i++) {
							JSONObject jsonObject = interTypesData
									.getJSONObject(i);
							String interactionsUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONURN);
							String interactionsType = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERINTERACTIONS_REFEREDOBJECTTYPE);
							String interactionsPropertiesCount = jsonObject
									.getString(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONPROPERTIESCOUNT);
							relationsDataList = new ArrayList<String>();
							if (interactionsPropertiesCount != null) {
								JSONObject interTypes = jsonObject
										.getJSONObject(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONPROPERTIESCOUNT);

								if (interTypes
										.has(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONHASHTAG)) {

									relationsDataList.add("Hashtag");
								}

								if (interTypes
										.has(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONMETADATA)) {

									relationsDataList.add("Metadata");
								}

								if (interTypes
										.has(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONFILE)) {

									relationsDataList.add("Files");
								}

								if (interTypes
										.has(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONGEOSPATIAL)) {

									relationsDataList.add("Geospatial");
								}

								if (interTypes
										.has(SSConstants.TAG_OBJECT_INTERINTERACTIONS_INTERACTIONTIMELINE)) {

									relationsDataList.add("Timeline");
								}

								SSOuterModel interModel = new SSOuterModel();
								interModel
										.setRelationshipType(interactionsType);
								interModel.setRelationshipUrn(interactionsUrn);
								if (objectOuterList != null) {

									objectOuterList.add(interModel);
								}

							}

						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectInterInteractions;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					if (interTypesData.length() != 0) {

						showPopup(outerImage[itemPos]);

					}
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectListEmptyDialog(getActivity());
				dialog.show();
			}
		}
	}

	public void showPopup(View selectedView) {
		// TODO Auto-generated method stub

		final PopupWindow popupWindow;
		ListView mainList;
		TextView relTitle, relations_main_center;
		final View selectedItem = selectedView;

		View popupView = getActivity().getLayoutInflater().inflate(
				R.layout.object_relationships_main_dialog, null);
		mainList = (ListView) popupView
				.findViewById(R.id.relationship_main_list);
		relTitle = (TextView) popupView.findViewById(R.id.relations_main_title);
		relations_main_center = (TextView) popupView
				.findViewById(R.id.relations_main_center);

		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setContentView(popupView);

		popupWindow.setWidth(380);
		if (objectOuterList.size() >= 5) {
			RelativeLayout mainLayout = (RelativeLayout) popupView
					.findViewById(R.id.relationships_main_title);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, 200);
			params.addRule(RelativeLayout.BELOW, mainLayout.getId());

			mainList.setLayoutParams(params);

			popupWindow.setHeight(300);
		}

		popupWindow.setFocusable(true);

		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		int location[] = new int[2];
		selectedView.getLocationOnScreen(location);
		int OFFSET_X = 90;
		int OFFSET_Y = popupView.getHeight() / 2;

		Point p = new Point();
		p.x = location[0];
		p.y = location[1];

		popupWindow.showAtLocation(selectedView, Gravity.NO_GRAVITY, p.x
				+ OFFSET_X, p.y + OFFSET_Y);

		relations_main_center.setText("Send object\t" + objectRefName
				+ "\tto center");
		relations_main_center.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View centerView) {
				// TODO Auto-generated method stub
				mCallback.addCentralView(objectRefUrn, objectRefName);
				popupWindow.dismiss();

			}
		});
		relTitle.setText(selectedObject + "-" + objectRefName + "\tInteraction");

		SSOuterListAdapter relAdapter = new SSOuterListAdapter(getActivity(),
				selectedObject, objectRefName, objectOuterList);
		mainList.setAdapter(relAdapter);
		mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (relationsDataList != null) {

					TextView tv = (TextView) view
							.findViewById(R.id.text_rel_object);

					String category = tv.getText().toString();
					showPopupItemSelected(selectedItem, category);

				}
				popupWindow.dismiss();

			}
		});

	}

	public void showPopupItemSelected(View listSelectedItem, final String item) {
		// TODO Auto-generated method stub
		final PopupWindow popupWindow1;
		ListView relationshipList;
		TextView relTitle;
		ImageView previous_bar;

		final View listSelected = listSelectedItem;
		View popupView = getActivity().getLayoutInflater().inflate(
				R.layout.object_relationships_dialog, null);

		popupWindow1 = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);

		popupWindow1.setContentView(popupView);

		popupWindow1.setWidth(350);
		if (relationsDataList.size() >= 5) {
			popupWindow1.setHeight(250);
		}

		popupWindow1.setFocusable(true);

		popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		int location[] = new int[2];
		listSelectedItem.getLocationOnScreen(location);
		int OFFSET_X = 90;
		int OFFSET_Y = popupView.getHeight() / 2;

		Point p = new Point();
		p.x = location[0];
		p.y = location[1];

		popupWindow1.showAtLocation(listSelectedItem, Gravity.NO_GRAVITY, p.x
				+ OFFSET_X, p.y + OFFSET_Y);

		relTitle = (TextView) popupView.findViewById(R.id.relations_title);
		previous_bar = (ImageView) popupView
				.findViewById(R.id.icon_relationships_previous);
		relTitle.setText(item);
		previous_bar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopup(listSelected);
				popupWindow1.dismiss();

			}
		});

		relationshipList = (ListView) popupView
				.findViewById(R.id.relationship_list);

		SSDataListAdapter dataAdapter = new SSDataListAdapter(getActivity(),
				relationsDataList);
		relationshipList.setAdapter(dataAdapter);

		relationshipList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (relationsDataList.size() > 0) {

							TextView tv = (TextView) view
									.findViewById(R.id.relationships_object_name);

							String listItem = tv.getText().toString();
							if (listItem.equals("Hashtag")) {
								new ObjectHashtagsRetrieve(item)
										.execute(SSConstants.serverURLPath
												+ "/rest/tags?entityReferenceType=Object&referenceUrn="
												+ objectURN);
							}
							if (listItem.equals("Metadata")) {
								new ObjectMetadataRetrieve(item)
										.execute(SSConstants.serverURLPath
												+ "/rest/metadata/Object/"
												+ objectURN);
							}
							if (listItem.equals("Files")) {
								new ObjectFilesRetrieve(item)
										.execute(SSConstants.serverURLPath
												+ "/rest/files/Object/"
												+ objectURN);
							}
							if (listItem.equals("Geospatial")) {
								new ObjectGeospatialRetrieve(item)
										.execute(SSConstants.serverURLPath
												+ "/rest/relationships/Object/"
												+ objectURN);
							}
							if (listItem.equals("timeline")) {
								new ObjectTimelineRetrieve(item, selectedId)
										.execute(SSConstants.serverURLPath
												+ "/rest/plugin/timelines");
							}
						} else {
							return;
						}

					}
				});

	}

	private class ObjectLibraryRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectLibrary = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectLibrary != null) {
				Log.d("Response", objectLibrary);
				if (!objectLibrary.equals("401")) {

					objectLibraryList = new ArrayList<SSObjectLibraryModel>();

					try {
						JSONObject libraryObject = new JSONObject(objectLibrary);
						JSONArray libraryData = libraryObject
								.getJSONArray(SSConstants.TAG_OBJECT_LIBRARY_OBJECT);

						for (int i = 0; i < libraryData.length(); i++) {
							JSONObject jsonObject = libraryData
									.getJSONObject(i);
							String libraryUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_URN);
							String libraryName = jsonObject
									.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_LIBRARYNAME);
							String relRefURN = jsonObject
									.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_RELATEDREFERENCEURN);
							SSObjectLibraryModel libraryModel = new SSObjectLibraryModel();
							libraryModel.setUrn(libraryUrn);
							libraryModel.setLibraryName(libraryName);
							libraryModel.setRelatedReferenceUrn(relRefURN);
							objectLibraryList.add(libraryModel);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectLibrary;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					dialog = new SSLibraryDialog(getActivity(),
							objectLibraryList, selectedObject);

					dialog.show();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectLibraryEmptyDialog(getActivity());
				dialog.show();
				Log.d("Exception", "No data Found");
			}
		}
	}

	private class ObjectTimelineRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		String category;
		int index;

		public ObjectTimelineRetrieve(String selectedListItem, int selectedId) {
			// TODO Auto-generated constructor stub
			category = selectedListItem;
			index = selectedId;

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			servicehandler = new SSServiceHandler();

			objectTimeline = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (objectTimeline != null) {
				if (!objectTimeline.equals("401")) {
					Log.d("Response", objectTimeline);
					objectTimelineList = new ArrayList<SSObjectTimelineModel>();

					try {
						JSONObject timelineObject = new JSONObject(
								objectTimeline);
						JSONArray timelineArray = timelineObject
								.getJSONArray(SSConstants.TAG_OBJECT_TIMELINE_OBJECT);

						for (int i = 0; i < timelineArray.length(); i++) {
							JSONObject jsonObject = timelineArray
									.getJSONObject(i);
							String timelineUrn = jsonObject
									.getString(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_URN);
							String entityRefType = jsonObject
									.getString(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_ENTITYREFTYPE);
							String placeName = jsonObject
									.getString(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_NAME);
							String timelineDesc = jsonObject
									.getString(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_DESC);
							boolean highLightFlag = jsonObject
									.getBoolean(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_HIGHLIGHTFLAG);
							String referenceUuid = jsonObject
									.getString(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_REFERENCEUUID);
							long timestamp = jsonObject
									.getLong(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_LASTMODIFIEDTIMESTAMP);
							//String dateString = convertTime(timestamp);
							boolean activeFlag = jsonObject
									.getBoolean(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_ACTIVEFLAG);
							boolean visibleFlag = jsonObject
									.getBoolean(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_VISIBLEFLAG);
							long recordTimestamp = jsonObject
									.getLong(SSConstants.TAG_OBJECT_TIMELINE_OBJECT_RECORDTIMESTAMP);
							SSObjectTimelineModel timelineModel = new SSObjectTimelineModel();
							timelineModel.setUrn(timelineUrn);
							timelineModel.setEntityReferenceType(entityRefType);
							timelineModel.setName(placeName);
							timelineModel.setDescription(timelineDesc);
							timelineModel.setHighlightFlag(highLightFlag);
							timelineModel.setReferenceUuid(referenceUuid);
							timelineModel.setLastModifiedTimestamp(timestamp);
							timelineModel.setActiveFlag(activeFlag);
							timelineModel.setVisibleFlag(visibleFlag);
							timelineModel.setRecordedTimestamp(recordTimestamp);
							objectTimelineList.add(timelineModel);

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return objectTimeline;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (result != null) {
				if (!result.equals("401")) {
					dialog = new SSTimelineDialog(getActivity(),
							objectTimelineList, objectTimelineList.get(index),
							selectedObject, category);

					dialog.show();
				} else {
					dialog = new SSLoginErrorDialog(getActivity());
					dialog.show();
				}

			} else {
				dialog = new SSObjectTimelineEmptyDialog(getActivity());
				dialog.show();
				Log.d("Exception", "No data Found");
			}
		}
	}

//	@SuppressLint("SimpleDateFormat")
//	public String convertTime(long time) {
//		Date date = new Date(time);
//		Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		return dateFormat.format(date);
//
//	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}
}

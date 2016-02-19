package com.smartrac.objectexplore.fragments;

import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSObjectListAdapter;
import com.smartrac.objectexplore.common.SSConnectionDetector;
import com.smartrac.objectexplore.common.SSConstants;
import com.smartrac.objectexplore.dialog.SSNetworkErrorDialog;
import com.smartrac.objectexplore.dialog.SSObjectListEmptyDialog;
import com.smartrac.objectexplore.interfaces.SSOnListItemDisplayListener;
import com.smartrac.objectexplore.models.SSLoginModel;
import com.smartrac.objectexplore.models.SSObjectModel;
import com.smartrac.objectexplore.services.SSServiceHandler;

public class SSObjectListFragment extends Fragment {
	// private OnSwipeTouchListener onSwipeTouchListener;

	ArrayList<SSObjectModel> objectListValues;
	ListView objectList;
	String username, password, instance;
	SSLoginModel loginCredential;
	EditText search;
	SSObjectListAdapter objectAdapter;
	SSObjectModel objectModel;
	TextView object_list_more;
	RelativeLayout itemsMore;
	public OnTouchListener listener;
	String jsonObjects;
	Dialog dialog = null;

	SSOnListItemDisplayListener mCallback;
	private SSConnectionDetector cd;
	private Boolean isInternetPresent = false;

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

		loginCredential = new SSLoginModel();
		username = loginCredential.getUsername();
		password = loginCredential.getPassword();
		instance = loginCredential.getInstance();
		objectModel = new SSObjectModel();
		
		if (isInternetPresent) {
			new ObjectRetrieve().execute(SSConstants.serverURLPath
					+ "/rest/objects");
		} else {

			dialog = new SSNetworkErrorDialog(getActivity());
			dialog.show();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_object_list, container,
				false);
		// itemsMore = (RelativeLayout) view.findViewById(R.id.object_more);
		// object_list_more = (TextView)
		// view.findViewById(R.id.object_list_more);

		objectList = (ListView) view.findViewById(R.id.object_list);

		objectList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						String item = objectListValues.get(position)
								.getObjectUrn();
						String object_selected_name = objectListValues.get(
								position).getName();

						mCallback.addCentralView(item, object_selected_name);
					}
				});

		search = (EditText) view.findViewById(R.id.search);

		return view;
	}

	private class ObjectRetrieve extends AsyncTask<String, String, String> {

		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			SSServiceHandler servicehandler = new SSServiceHandler();
			String basicAuth = loginCredential.validateUser(username, password);

			jsonObjects = servicehandler.makeServiceCall(urls[0],
					SSServiceHandler.GET, basicAuth);
			objectListValues = new ArrayList<SSObjectModel>();

			if (jsonObjects != null) {
				if (!jsonObjects.equals("401")) {
				try {

					Log.d("Response: ", "> " + jsonObjects);

					JSONArray objects = new JSONArray(jsonObjects);

					// looping through All objects
					for (int i = 0; i < objects.length(); i++) {
						JSONObject jsonObject = objects.getJSONObject(i);

						boolean objectStatus = jsonObject
								.getBoolean(SSConstants.TAG_OBJECT_STATUS);

						String objectDescription = jsonObject
								.getString(SSConstants.TAG_OBJECT_DESCRIPTION);

						long objectTimestamp = jsonObject
								.getLong(SSConstants.TAG_OBJECT_TIMESTAMP);
						String objectName = jsonObject
								.getString(SSConstants.TAG_OBJECT_NAME);
						String objectUrn = jsonObject
								.getString(SSConstants.TAG_OBJECT_OBJECTURN);
						String objectType = jsonObject
								.getString(SSConstants.TAG_OBJECT_TYPE);
						String urn = jsonObject
								.getString(SSConstants.TAG_OBJECT_URN);

						SSObjectModel objectModel = new SSObjectModel();
						objectModel.setActiveFlag(objectStatus);
						objectModel.setDescription(objectDescription);
						objectModel.setName(objectName);
						objectModel.setTimeStamp(objectTimestamp);
						objectModel.setObjectUrn(objectUrn);
						objectModel.setType(objectType);
						objectModel.setUrn(urn);

						if (objectModel != null) {
							objectListValues.add(objectModel);
						}

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				}
			} else {
				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return jsonObjects;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();

			if (objectListValues.size() > 0) {
				objectAdapter = new SSObjectListAdapter(getActivity(),
						objectListValues, listener);

				objectList.setAdapter(objectAdapter);
				/*
				 * objectList.setOnScrollListener(new OnScrollListener() {
				 * private int currentVisibleItemCount; private int
				 * currentScrollState; private int currentFirstVisibleItem;
				 * private int totalItem;
				 * 
				 * @Override public void onScrollStateChanged(AbsListView view,
				 * int scrollState) { // TODO Auto-generated method stub
				 * this.currentScrollState = scrollState;
				 * this.isScrollCompleted();
				 * 
				 * }
				 * 
				 * @Override public void onScroll(AbsListView view, int
				 * firstVisibleItem, int visibleItemCount, int totalItemCount) {
				 * // TODO Auto-generated method stub
				 * this.currentFirstVisibleItem = firstVisibleItem;
				 * this.currentVisibleItemCount = visibleItemCount;
				 * this.totalItem = totalItemCount; int count1 = totalItem -
				 * currentVisibleItemCount; object_list_more.setText(count1 +
				 * "\tmore"); }
				 * 
				 * private void isScrollCompleted() { if (totalItem -
				 * currentFirstVisibleItem == currentVisibleItemCount &&
				 * this.currentScrollState == SCROLL_STATE_IDLE) {
				 * if(this.currentScrollState==SCROLL_STATE_IDLE) {
				 * 
				 * int count = objectList.getLastVisiblePosition() -
				 * objectList.getFirstVisiblePosition(); int count1 = totalItem
				 * - currentVisibleItemCount; System.out.println("count:" +
				 * count); System.out.println("count1:" + count1);
				 * 
				 * object_list_more.setText(count1 + "\tmore");
				 * 
				 * } else {
				 * 
				 * itemsMore.setVisibility(View.GONE); } }
				 * 
				 * });
				 */

				search.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1,
							int arg2, int arg3) {
						// When user changed the Text

						String text = search.getText().toString()
								.toLowerCase(Locale.getDefault());
						objectAdapter.filter(text);

					}

					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						String text = search.getText().toString()
								.toLowerCase(Locale.getDefault());
						objectAdapter.filter(text);
					}
				});
			} else {
				dialog = new SSObjectListEmptyDialog(getActivity());
				dialog.show();
			}

		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;

	}
}

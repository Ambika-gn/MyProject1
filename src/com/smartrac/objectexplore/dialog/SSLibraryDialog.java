package com.smartrac.objectexplore.dialog;

import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSLibraryListAdapter;
import com.smartrac.objectexplore.common.SSConnectionDetector;
import com.smartrac.objectexplore.common.SSConstants;
import com.smartrac.objectexplore.models.SSLoginModel;
import com.smartrac.objectexplore.models.SSObjectLibraryChildrenModel;
import com.smartrac.objectexplore.models.SSObjectLibraryModel;
import com.smartrac.objectexplore.services.SSServiceHandler;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSLibraryDialog extends Dialog implements
		android.view.View.OnClickListener {

	ListView libraryList;
	EditText librarySearch;
	SSLibraryListAdapter libraryAdapter;
	ArrayList<SSObjectLibraryModel> objectLibraryList;
	ArrayList<SSObjectLibraryChildrenModel> objectLibraryNestedList;
	SSObjectActivity mContext;
	ImageView btn_close;
	Dialog dialog = null;
	TextView libraryTitle;
	String objectName;
	private SSConnectionDetector cd;
	private Boolean isInternetPresent = false;
	String libraryJson = null;
	SSLoginModel loginhandler;
	String username, password;
	String basicAuth = null;

	public SSLibraryDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSLibraryDialog(Context context,
			ArrayList<SSObjectLibraryModel> objectLibraryList, String objectName) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectLibraryList = objectLibraryList;
		this.objectName = objectName;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_library);
		cd = new SSConnectionDetector(mContext.getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		loginhandler = new SSLoginModel();
		username = loginhandler.getUsername();
		password = loginhandler.getPassword();
		basicAuth = loginhandler.validateUser(username, password);

		libraryTitle = (TextView) findViewById(R.id.library_title_text);
		libraryTitle.setText(objectName + "\tLibrary");
		libraryList = (ListView) findViewById(R.id.object_library);
		librarySearch = (EditText) findViewById(R.id.library_search_text);
		btn_close = (ImageView) findViewById(R.id.btn_library_close);
		btn_close.setOnClickListener(this);

		libraryList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						TextView tv = (TextView) view
								.findViewById(R.id.library_name);

						String listItemSelected = tv.getText().toString();
						if (isInternetPresent) {
							String relRefUrn = objectLibraryList.get(position)
									.getRelatedReferenceUrn();
							new ObjectLibraryChildrenRetrieve(listItemSelected)
									.execute(SSConstants.serverURLPath
											+ "/rest/plugin/library/children/"
											+ relRefUrn);
							

						} else {

							dialog = new SSNetworkErrorDialog(mContext);
							dialog.show();

						}

					}
				});

		if (objectLibraryList != null) {

			libraryAdapter = new SSLibraryListAdapter(getContext(),
					objectLibraryList);

			libraryList.setAdapter(libraryAdapter);

			librarySearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text
					String text = librarySearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					libraryAdapter.filter(text);

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					String text = librarySearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					libraryAdapter.filter(text);
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_library_close:

			this.dismiss();

			break;

		default:
			break;
		}
	}

	private class ObjectLibraryChildrenRetrieve extends
			AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;
		String category;

		public ObjectLibraryChildrenRetrieve(String listItemSelected) {
			// TODO Auto-generated constructor stub
			category=listItemSelected;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(mContext);
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
			if (basicAuth != null) {
				Log.d("basicAuth in library", basicAuth);
			}
			libraryJson = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);
			if (libraryJson != null) {
				Log.d("Response", libraryJson);
				if (!libraryJson.equals("401")) {

					objectLibraryNestedList = new ArrayList<SSObjectLibraryChildrenModel>();

					try {
						JSONObject libraryChildObject = new JSONObject(
								libraryJson);
						JSONArray libraryChildData = libraryChildObject
								.getJSONArray(SSConstants.TAG_OBJECT_LIBRARY_CHILDREN_OBJECT);

						for (int i = 0; i < libraryChildData.length(); i++) {
							JSONObject jsonObject = libraryChildData
									.getJSONObject(i);
							String urn=jsonObject.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_URN);
							boolean isChildAvailable = jsonObject
									.getBoolean(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_CHILDAVAILABLE);
							String childName = jsonObject
									.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_NAME);
							String parent = jsonObject
									.getString(SSConstants.TAG_OBJECT_LIBRARY_OBJECT_CHILDREN_PARENT);
							SSObjectLibraryChildrenModel childModel = new SSObjectLibraryChildrenModel();
							childModel.setUrn(urn);
							childModel.setChildAvailable(isChildAvailable);
							childModel.setName(childName);
							childModel.setParent(parent);
							objectLibraryNestedList.add(childModel);

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {

				Log.e("SSServiceHandler", "Couldn't get any data from the url");
			}

			return libraryJson;

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
					dialog = new SSLibraryChildrenDialog(mContext,
							objectLibraryNestedList, objectName,category);
					dialog.show();

				} else {
					dialog = new SSLoginErrorDialog(mContext);
					dialog.show();
				}

			} else {
				dialog = new SSObjectLibraryEmptyDialog(mContext);
				dialog.show();
				Log.d("Exception", "No data Found");
			}
		}
	}

}

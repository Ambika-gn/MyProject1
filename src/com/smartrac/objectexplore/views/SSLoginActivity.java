package com.smartrac.objectexplore.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.common.SSConnectionDetector;
import com.smartrac.objectexplore.common.SSConstants;
import com.smartrac.objectexplore.dialog.SSConnectionErrorDialog;
import com.smartrac.objectexplore.dialog.SSLoginErrorDialog;
import com.smartrac.objectexplore.dialog.SSNetworkErrorDialog;
import com.smartrac.objectexplore.fragments.SSInstanceFragment;
import com.smartrac.objectexplore.fragments.SSInstanceFragment.OnInstancelistListener;
import com.smartrac.objectexplore.models.SSLoginModel;
import com.smartrac.objectexplore.services.SSServiceHandler;

public class SSLoginActivity extends FragmentActivity implements
		OnClickListener, OnInstancelistListener {

	private ImageView btn_pickerview;
	private TextView btn_enter;
	private EditText uservalue, passwordValue, instanceValue;
	Dialog dialog = null;

	SharedPreferences sharedPrefs;
	SharedPreferences values;
	String userValidation = null;
	SSLoginModel loginhandler;
	SSInstanceFragment instanceFrag;
	FrameLayout instanceLayout;
	SharedPreferences.Editor preferencesEditor;

	String username, password, instance, shUsername, shPassword, shInstance;

	ArrayList<String> instanceList;
	private SSConnectionDetector cd;
	private Boolean isInternetPresent = false;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		loginhandler = new SSLoginModel();

		getActionBar().hide();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		cd = new SSConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		instanceList = new ArrayList<String>();

		uservalue = (EditText) findViewById(R.id.userValue);
		passwordValue = (EditText) findViewById(R.id.passwordValue);
		instanceValue = (EditText) findViewById(R.id.instanceValue);
		instanceLayout = (FrameLayout) findViewById(R.id.instancelayout);

		sharedPrefs = getSharedPreferences(SSConstants.PREFS_NAME, MODE_PRIVATE);

		btn_pickerview = (ImageView) findViewById(R.id.btn_pickerview);
		btn_pickerview.setOnClickListener(this);
		btn_enter = (TextView) findViewById(R.id.btn_enter);
		btn_enter.setOnClickListener(this);

	}

	private void savePreferences(String user, String pass, String instance) {
		// TODO Auto-generated method stub
		preferencesEditor = sharedPrefs.edit();
		preferencesEditor.putString("user", user);
		preferencesEditor.putString("password", pass);
		preferencesEditor.putString("instance2", instance);
		preferencesEditor.putString("instance1", instance);
		preferencesEditor.commit();

	}

	private void loadPreferences() {

		values = getSharedPreferences(SSConstants.PREFS_NAME, MODE_PRIVATE);
		shUsername = values.getString(SSConstants.PREFS_USER, null);
		shPassword = values.getString(SSConstants.PREFS_PASSWORD, null);
		shInstance = values.getString(SSConstants.PREFS_INSTANCE2, null);
		if (shUsername != null && shPassword != null && shInstance != null) {
			uservalue.setText(shUsername);
			passwordValue.setText(shPassword);
			instanceValue.setText(shInstance);

		} else {
			uservalue.setText("user@demo.com");
			passwordValue.setText("demo");
			instanceValue
					.setText("http://ec2-52-11-184-204.us-west-2.compute.amazonaws.com:8080");
			// preferencesEditor.putString("instance1", instanceValue.getText()
			// .toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_pickerview:
			instanceFrag = new SSInstanceFragment();
			shInstance = values.getString(SSConstants.PREFS_INSTANCE1, null);
			if (shInstance != null) {
				instanceLayout.setVisibility(View.VISIBLE);
				instanceList.add(shInstance);
				Set<String> hs = new HashSet<>();
				hs.addAll(instanceList);
				instanceList.clear();
				instanceList.addAll(hs);

				instanceFrag.setInstances(instanceList);
				getSupportFragmentManager().beginTransaction()
						.add(R.id.instancelayout, instanceFrag).commit();

			}

			break;

		case R.id.btn_enter:
			if (isInternetPresent) {

				username = uservalue.getText().toString();
				password = passwordValue.getText().toString();
				instance = instanceValue.getText().toString();
				if (instance.length() > 0
						&& instance.charAt(instance.length() - 1) == '/') {
					instance = instance.substring(0, instance.length() - 1);
				}
				if (!username.equals("") && !password.equals("")
						&& !instance.equals("") && instance.endsWith(":8080")) {
					savePreferences(username, password, instance);
					loginhandler.setUsername(username);
					loginhandler.setPassword(password);
					loginhandler.setInstance(instance);

					SSConstants.serverURLPath = loginhandler.getInstance();

					new LoginVerify().execute(SSConstants.serverURLPath
							+ "/rest/account");
				} else {
					dialog = new SSLoginErrorDialog(SSLoginActivity.this);
					dialog.show();

				}

			} else {

				dialog = new SSNetworkErrorDialog(SSLoginActivity.this);
				dialog.show();
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		loadPreferences();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		savePreferences(username, password, instance);
	}

	private class LoginVerify extends AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		SSServiceHandler servicehandler;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(SSLoginActivity.this);
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

			username = loginhandler.getUsername();
			password = loginhandler.getPassword();

			String basicAuth = loginhandler.validateUser(username, password);

			userValidation = servicehandler.makeServiceCall(url[0],
					SSServiceHandler.GET, basicAuth);

			if (userValidation != null) {
				Log.d("response:", userValidation);
				if (!userValidation.equals("401")) {
					try {

						JSONObject jsonObj = new JSONObject(userValidation);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			return userValidation;

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
					Intent intent = new Intent(getApplicationContext(),
							SSObjectActivity.class);
					Date date = new Date();
					intent.putExtra("lastLogin", date.toString());
					intent.putExtra("currentUser", username);
					intent.putExtra("currentInstance", instance);
					startActivity(intent);
				} else {
					dialog = new SSLoginErrorDialog(SSLoginActivity.this);
					dialog.show();
				}

			} else {
				dialog = new SSConnectionErrorDialog(SSLoginActivity.this);
				dialog.show();
			}
		}
	}

	@Override
	public void addInstances(int position, ArrayList<String> instances) {
		// TODO Auto-generated method stub
		instanceValue.setText(instances.get(position));
		instanceLayout.setVisibility(View.GONE);
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

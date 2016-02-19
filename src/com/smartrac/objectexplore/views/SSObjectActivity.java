package com.smartrac.objectexplore.views;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSObjectListAdapter;
import com.smartrac.objectexplore.fragments.SSObjectDetailFragment;
import com.smartrac.objectexplore.fragments.SSObjectListFragment;
import com.smartrac.objectexplore.fragments.SSProfileFragment;
import com.smartrac.objectexplore.interfaces.SSOnListItemDisplayListener;
import com.smartrac.objectexplore.models.SSLoginModel;
import com.smartrac.objectexplore.models.SSObjectModel;

public class SSObjectActivity extends FragmentActivity implements OnClickListener,
		SSOnListItemDisplayListener {

	ImageView icon_list;
	ArrayList<SSObjectModel> objectListValues;
	String username, password, instance;
	SSLoginModel loginCredential;
	SSObjectModel objectModel;
	SSObjectListAdapter objectAdapter;

	SSObjectListFragment objectListFragment;
	SSObjectDetailFragment objectDetailFragment;
	SSProfileFragment profileFragment;

	FrameLayout listFrame, detailFrame, profileFrame;
	String currentUser, currentInstance, currentDate;
	ImageView icon_profile, list_icon;
	boolean listFlag = false;
	boolean profileFlag = false;
	boolean detailFrameFlag = false;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_object);
		Bundle bundle = getIntent().getExtras();
		currentUser = bundle.getString("currentUser");
		currentInstance = bundle.getString("currentInstance");
		currentDate = bundle.getString("lastLogin");

		getActionBar().hide();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		listFrame = (FrameLayout) findViewById(R.id.object_listcontainer);
		detailFrame = (FrameLayout) findViewById(R.id.object_detailcontainer);
		list_icon = (ImageView) findViewById(R.id.icon_list_detail);
		icon_profile = (ImageView) findViewById(R.id.icon_profile_detail);
		list_icon.setOnClickListener(this);
		icon_profile.setOnClickListener(this);

		profileFrame = (FrameLayout) findViewById(R.id.profile_container);
		detailFrame.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				detailFrameFlag = true;
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					if (detailFrameFlag)
					{
						if(listFrame.isShown())
						{
							listFrame.setVisibility(View.GONE);
						}
						else
						{
						listFrame.setVisibility(View.VISIBLE);
						}
						detailFrameFlag=false;
					}
						
				}
				return true;
			}
		});

		if (findViewById(R.id.object_listcontainer) != null) {
			if (savedInstanceState != null) {
				return;
			}

			objectListValues = new ArrayList<SSObjectModel>();

			objectListFragment = new SSObjectListFragment();

			objectListFragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
					.add(R.id.object_listcontainer, objectListFragment)
					.commit();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void addCentralView(String obURN, String obName) {
		// TODO Auto-generated method stub

		listFrame.setVisibility(View.GONE);
		objectDetailFragment = new SSObjectDetailFragment();

		objectDetailFragment.updateView(obURN, obName);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.object_detailcontainer, objectDetailFragment)
				.commit();

	}

	@Override
	public boolean removeListItem(boolean flag) {
		// TODO Auto-generated method stub
		if (flag) {

			listFrame.setVisibility(View.VISIBLE);
			if (profileFrame.isShown()) {
				profileFrame.setVisibility(View.GONE);
			}

			flag = false;

		} else {
			listFrame.setVisibility(View.GONE);

			flag = true;
		}
		return flag;
	}

	@Override
	public boolean removeProfileItem(boolean flag) {
		// TODO Auto-generated method stub
		profileFragment = new SSProfileFragment();
		profileFragment.setUserDetails(currentUser, currentInstance,
				currentDate);
		android.view.ViewGroup.LayoutParams params = detailFrame
				.getLayoutParams();
		if (flag) {
			if (listFrame.isShown()) {
				listFrame.setVisibility(View.GONE);
			}
			params.width = 785;
			profileFrame.setVisibility(View.VISIBLE);

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.profile_container, profileFragment).commit();

			flag = false;
		} else {
			profileFrame.setVisibility(View.GONE);
			params.width = 1285;
			flag = true;
		}

		return flag;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.icon_list_detail:
			listFlag = !listFlag;
			addAndShowList(listFlag);
			break;

		case R.id.icon_profile_detail:
			profileFlag = !profileFlag;
			addAndShowProfile(profileFlag);
			break;
		case R.id.object_detailcontainer:
			break;
		default:
			break;
		}
	}

	private boolean addAndShowList(boolean flag) {
		if (flag) {

			listFrame.setVisibility(View.VISIBLE);
			if (profileFrame.isShown()) {
				profileFrame.setVisibility(View.GONE);
			}

			flag = false;

		} else {
			listFrame.setVisibility(View.GONE);

			flag = true;
		}
		return flag;
	}

	private boolean addAndShowProfile(boolean flag) {

		profileFragment = new SSProfileFragment();
		profileFragment.setUserDetails(currentUser, currentInstance,
				currentDate);
		android.view.ViewGroup.LayoutParams params = detailFrame
				.getLayoutParams();
		if (flag) {
			if (listFrame.isShown()) {
				listFrame.setVisibility(View.GONE);
			}
			params.width = 785;
			profileFrame.setVisibility(View.VISIBLE);

			getSupportFragmentManager().beginTransaction()
					.add(R.id.profile_container, profileFragment).commit();

			flag = false;
		} else {
			profileFrame.setVisibility(View.GONE);
			params.width = 1285;
			flag = true;
		}

		return flag;
	}

}

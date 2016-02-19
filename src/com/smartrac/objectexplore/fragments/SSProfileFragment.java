package com.smartrac.objectexplore.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.dialog.SSTutorialNADialog;
import com.smartrac.objectexplore.views.SSLoginActivity;

public class SSProfileFragment extends Fragment implements OnClickListener {
	String profileUserValue, profileInstancValue, profileCurrentDate;
	TextView profileUser, profileInstance, profile_last_login;
	ImageView icon_tutorial, icon_feedback, icon_about, icon_close_session;
	Dialog dialog = null;
	FrameLayout aboutFragment, feedbackFragment;
	ImageView aboutFragmentView, feedback_selected_image1,
			feedback_selected_image2;
	boolean aboutFlag = false;
	boolean feedbackFlag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_profile_details,
				container, false);
		aboutFragment = (FrameLayout) view.findViewById(R.id.aboutFragment);
		aboutFragmentView = (ImageView) view
				.findViewById(R.id.aboutFragmentView);
		feedbackFragment = (FrameLayout) view
				.findViewById(R.id.feedbackFragment);
		feedback_selected_image1 = (ImageView) view
				.findViewById(R.id.feedback_selected_image1);
		feedback_selected_image2 = (ImageView) view
				.findViewById(R.id.feedback_selected_image2);
		profileUser = (TextView) view.findViewById(R.id.profile_user);
		profileInstance = (TextView) view.findViewById(R.id.profile_instance);
		profile_last_login = (TextView) view
				.findViewById(R.id.profile_last_login);
		icon_tutorial = (ImageView) view.findViewById(R.id.icon_tutorial);
		icon_feedback = (ImageView) view.findViewById(R.id.icon_feedback);
		icon_about = (ImageView) view.findViewById(R.id.icon_about);
		icon_close_session = (ImageView) view
				.findViewById(R.id.icon_close_session);

		icon_tutorial.setOnClickListener(this);
		icon_feedback.setOnClickListener(this);
		icon_about.setOnClickListener(this);
		icon_close_session.setOnClickListener(this);
		// aboutFragmentView.setOnClickListener(this);
		// feedback_selected_image1.setOnClickListener(this);
		// feedback_selected_image2.setOnClickListener(this);

		profileUser.setText(profileUserValue);
		profileInstance.setText(profileInstancValue);
		profile_last_login.setText("Last login:" + profileCurrentDate);
		return view;
	}

	public void setUserDetails(String currentUser, String currentInstance,
			String currentDate) {
		// TODO Auto-generated method stub
		profileUserValue = currentUser;
		profileInstancValue = currentInstance;
		profileCurrentDate = currentDate;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.icon_tutorial:
			dialog = new SSTutorialNADialog(getActivity());
			dialog.show();

			break;
		case R.id.icon_feedback:

			feedbackFlag = !feedbackFlag;
			setFeedbackLayout(feedbackFlag);
			break;
		
		case R.id.icon_about:
			aboutFlag = !aboutFlag;
			setAboutLayout(aboutFlag);
			break;

		case R.id.aboutFragmentView:

			Uri uri = Uri.parse("https://licensing.smartcosmos.net/objects/");
			Intent uriIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(uriIntent);

			break;

		case R.id.icon_close_session:
			Intent closeIntent = new Intent(getActivity(), SSLoginActivity.class);
			startActivity(closeIntent);

			break;

		default:
			break;
		}

	}

	private boolean setAboutLayout(boolean flag) {
		// TODO Auto-generated method stub
		SSProfileAboutFragment aboutFrag = new SSProfileAboutFragment();

		if (flag) {
			aboutFragment.setVisibility(View.VISIBLE);
			getFragmentManager().beginTransaction()
					.add(R.id.aboutFragment, aboutFrag).commit();
			flag = false;
		} else {
			getFragmentManager().beginTransaction().remove(aboutFrag).commit();

			aboutFragment.setVisibility(View.GONE);

			flag = true;
		}
		return flag;

	}

	private boolean setFeedbackLayout(boolean flag) {
		SSProfileFeedbackFragment feedbackFrag = new SSProfileFeedbackFragment();
		if (flag) {
			feedbackFragment.setVisibility(View.VISIBLE);
			getFragmentManager().beginTransaction()
					.replace(R.id.feedbackFragment, feedbackFrag).commit();
			flag = false;
		} else {
			getFragmentManager().beginTransaction().remove(feedbackFrag)
					.commit();
			feedbackFragment.setVisibility(View.GONE);
			flag = true;
		}
		return flag;
	}

}

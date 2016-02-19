package com.smartrac.objectexplore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smartrac.objectexplore.R;

public class SSProfileFeedbackFragment extends Fragment implements
		OnClickListener {

	ImageView feedback_selected_image1, feedback_selected_image2;

	boolean feedbackFlag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater
				.inflate(R.layout.feedback_layout, container, false);

		feedback_selected_image1 = (ImageView) view
				.findViewById(R.id.feedback_selected_image1);
		feedback_selected_image2 = (ImageView) view
				.findViewById(R.id.feedback_selected_image2);

		feedback_selected_image1.setOnClickListener(this);
		feedback_selected_image2.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.feedback_selected_image1:
			Intent emailIntent1 = new Intent(Intent.ACTION_SEND);

			emailIntent1.setType("message/rfc822");
			emailIntent1.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "api@smartrac-group.com" });
			emailIntent1.putExtra(Intent.EXTRA_SUBJECT, "OBJECTS-Feedback");
			String emailBody1 = "Thanks for sending your comment."
					+ "\n\nIt is very important for us reading your comments about Objects for Android tab,it could be possible "
					+ "that we don't answer all emails because of the amount of users but you can be sure that your "
					+ "feedback is a great support for us as we believe that knowing about your experience is the best"
					+ "way for keeping improving."
					+ "\n\nSo please,tell us what you enjoyed:";

			emailIntent1.putExtra(Intent.EXTRA_TEXT, emailBody1);

			startActivity(Intent.createChooser(emailIntent1, getResources()
					.getString(R.string.emailSend)));
			break;
		case R.id.feedback_selected_image2:
			Intent emailIntent2 = new Intent(Intent.ACTION_SEND);

			emailIntent2.setType("message/rfc822");
			emailIntent2.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "api@smartrac-group.com" });
			emailIntent2.putExtra(Intent.EXTRA_SUBJECT, "OBJECTS-Feedback");
			String emailBody2 = "Thanks for sending your comment."
					+ "\n\nIt is very important for us reading your comments about Objects for Android tab,it could be possible "
					+ "that we don't answer all emails because of the amount of users but you can be sure that your "
					+ "feedback is a great support for us as we believe that knowing about your experience is the best"
					+ "way for keeping improving."
					+ "\n\nSo please,tell us what you think we should improve:";
			emailIntent2.putExtra(Intent.EXTRA_TEXT, emailBody2);

			startActivity(Intent.createChooser(emailIntent2, getResources()
					.getString(R.string.emailSend)));
			break;

		default:
			break;
		}

	}

}

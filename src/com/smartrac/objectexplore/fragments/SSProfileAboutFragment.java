package com.smartrac.objectexplore.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smartrac.objectexplore.R;

public class SSProfileAboutFragment extends Fragment implements OnClickListener {

	ImageView aboutFragmentView;

	boolean aboutFlag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.about_layout, container, false);
		aboutFragmentView = (ImageView) view
				.findViewById(R.id.aboutFragmentView);

		aboutFragmentView.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.aboutFragmentView:

			Uri uri = Uri.parse("https://licensing.smartcosmos.net/objects/");
			Intent uriIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(uriIntent);

			break;
		default:
			break;
		}

	}

}

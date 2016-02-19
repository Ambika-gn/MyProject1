package com.smartrac.objectexplore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSTutorialNADialog extends Dialog implements
		android.view.View.OnClickListener {

	SSObjectActivity mContext;
	TextView btn_accept;

	@SuppressWarnings("static-access")
	public SSTutorialNADialog(Context context) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_tutorial_na);
		btn_accept = (TextView) findViewById(R.id.tutorial_na_accept_button);
		btn_accept.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tutorial_na_accept_button:
			this.dismiss();
			break;

		default:
			break;
		}
	}

}

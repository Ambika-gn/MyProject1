package com.smartrac.objectexplore.dialog;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.views.SSObjectActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SSObjectFilesEmptyDialog extends Dialog implements
		android.view.View.OnClickListener {

	SSObjectActivity mContext;
	TextView filesNotFoundTitle,description,btn_accept;
	

	public SSObjectFilesEmptyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = (SSObjectActivity) context;
		this.getWindow();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_object_entity_empty);
		filesNotFoundTitle = (TextView) findViewById(R.id.object_entity_empty_title);
		description = (TextView) findViewById(R.id.object_entity_empty_description);
		btn_accept=(TextView)findViewById(R.id.object_entity_accept_button);
		btn_accept.setOnClickListener(this);
		filesNotFoundTitle.setText(R.string.empty_file_title);
		description.setText(R.string.empty_file_desc);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.object_entity_accept_button:
			this.dismiss();
			break;

		default:
			break;
		}

	}

}

package com.smartrac.objectexplore.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSGeospatialDialog extends Dialog implements
		android.view.View.OnClickListener {

	SSObjectActivity mContext;
	ImageView btn_close;
	TextView geospatial_json, selectedGeospatialObject, copy_json_value;
	String objectGeospatial;
	RelativeLayout background, copy_json;
	int selectedId = -1;
	String objectName, category;

	public SSGeospatialDialog(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSGeospatialDialog(Context context, String objectGeospatial,
			String objectName, int selectedId, String category) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectGeospatial = objectGeospatial;
		this.objectName = objectName;
		this.selectedId = selectedId;
		this.category = category;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_geospatial);
		background = (RelativeLayout) findViewById(R.id.geospatial_title);
		copy_json = (RelativeLayout) findViewById(R.id.copy_json);

		selectedGeospatialObject = (TextView) findViewById(R.id.selectedGeospatialObject);
		selectedGeospatialObject.setText(objectName);

		geospatial_json = (TextView) findViewById(R.id.object_geospatial_json);
		copy_json_value = (TextView) findViewById(R.id.copy_json_value);
		if (selectedId == 1 || selectedId == 8) {
			selectedGeospatialObject.setText(category);
			background.setBackgroundColor(Color.parseColor("#99CCFF"));
			copy_json
					.setBackgroundResource(R.drawable.textview_border_geospatial);
			copy_json_value.setTextColor(Color.parseColor("#99CCFF"));
		}
		copy_json_value.setOnClickListener(this);

		btn_close = (ImageView) findViewById(R.id.btn_geospatial_close);
		geospatial_json.setText(objectGeospatial);
		btn_close.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.copy_json_value:

			ClipboardManager cm = (ClipboardManager) mContext
					.getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("copied json",
					geospatial_json.getText().toString());
			cm.setPrimaryClip(clip);

			Toast.makeText(mContext, "Copied to clipboard", Toast.LENGTH_SHORT)
					.show();

			break;
		case R.id.btn_geospatial_close:

			this.dismiss();

			break;

		default:
			break;
		}
	}

}

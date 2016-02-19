package com.smartrac.objectexplore.dialog;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSMetadataListAdapter;
import com.smartrac.objectexplore.models.SSMetadataModel;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSMetadataDialog extends Dialog implements
		android.view.View.OnClickListener {

	SSMetadataListAdapter metadataAdapter;
	ArrayList<SSMetadataModel> objectMetadataList;
	SSObjectActivity mContext;
	ImageView btn_close;
	String objectName;
	ListView metadata_list;
	TextView selectedMetadataObject;
	int selectedId = -1;
	RelativeLayout background;
	String category;

	public SSMetadataDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSMetadataDialog(Context context,
			ArrayList<SSMetadataModel> objectMetadataList, String objectName,
			int selectedId,String category) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectMetadataList = objectMetadataList;
		this.objectName = objectName;
		this.selectedId = selectedId;
		this.category=category;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_metadata);
		background = (RelativeLayout) findViewById(R.id.metadata_title);
		selectedMetadataObject = (TextView) findViewById(R.id.selectedMetadataObject);
		selectedMetadataObject.setText(objectName);
		if (selectedId == 1||selectedId==8) {
			background.setBackgroundColor(Color.parseColor("#99CCFF"));
			selectedMetadataObject.setText(category);
		}
		metadata_list = (ListView) findViewById(R.id.metadata_list);

		btn_close = (ImageView) findViewById(R.id.icon_metadata_close);
		btn_close.setOnClickListener(this);

		if (objectMetadataList != null) {
			metadataAdapter = new SSMetadataListAdapter(getContext(),
					objectMetadataList);
			metadata_list.setAdapter(metadataAdapter);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.icon_metadata_close:

			this.dismiss();

			break;

		default:
			break;
		}
	}

}

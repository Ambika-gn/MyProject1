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
import com.smartrac.objectexplore.adapter.SSHashtagsListAdapter;
import com.smartrac.objectexplore.models.SSObjectHashtagsModel;
import com.smartrac.objectexplore.models.SSTagsModel;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSHashtagsDialog extends Dialog implements
		android.view.View.OnClickListener {

	ArrayList<SSObjectHashtagsModel> objectHashtagsList;
	ArrayList<SSTagsModel> tagList;
	SSObjectActivity mContext;
	ImageView btn_close;
	ListView hashtagsList;
	TextView selectedHashatagObject;
	RelativeLayout background;
	String category, objectName;
	int selectedId = -1;

	public SSHashtagsDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSHashtagsDialog(Context context,
			ArrayList<SSObjectHashtagsModel> objectHashtagsList,
			ArrayList<SSTagsModel> tagList, String objectName, int selectedId,
			String category) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectHashtagsList = objectHashtagsList;
		this.tagList = tagList;
		this.objectName = objectName;
		this.selectedId = selectedId;
		this.category = category;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_hashtags);
		background = (RelativeLayout) findViewById(R.id.hashtags_title);
		selectedHashatagObject = (TextView) findViewById(R.id.selectedHashtagsObject);
		selectedHashatagObject.setText(objectName);
		if (selectedId == 1 || selectedId == 8) {
			background.setBackgroundColor(Color.parseColor("#99CCFF"));
			selectedHashatagObject.setText(category);
		}
		hashtagsList = (ListView) findViewById(R.id.object_hashtags);

		btn_close = (ImageView) findViewById(R.id.btn_hashtags_close);
		btn_close.setOnClickListener(this);

		if (objectHashtagsList != null) {

			if (tagList != null) {
				SSHashtagsListAdapter hashtagsAdapter = new SSHashtagsListAdapter(
						mContext, tagList);
				hashtagsList.setAdapter(hashtagsAdapter);

			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_hashtags_close:
			this.dismiss();

			break;

		default:
			break;
		}
	}

}

package com.smartrac.objectexplore.dialog;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSFileListAdapter;
import com.smartrac.objectexplore.models.SSObjectFilesModel;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSFilesDialog extends Dialog implements
		android.view.View.OnClickListener {

	ListView fileList;
	EditText fileSearch;
	SSFileListAdapter fileAdapter;
	ArrayList<SSObjectFilesModel> objectFilesList;
	SSObjectActivity mContext;
	ImageView btn_close;
	String category;
	int selectedId = -1;
	RelativeLayout background;

	public SSFilesDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSFilesDialog(Context context,
			ArrayList<SSObjectFilesModel> objectFilesList, int selectedId,
			String category) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectFilesList = objectFilesList;
		this.selectedId = selectedId;
		this.category = category;
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_files);
		background = (RelativeLayout) findViewById(R.id.files_title);
		if (selectedId == 1 || selectedId == 8) {
			background.setBackgroundColor(Color.parseColor("#99CCFF"));
		}
		fileList = (ListView) findViewById(R.id.object_files_list);
		fileSearch = (EditText) findViewById(R.id.search);
		btn_close = (ImageView) findViewById(R.id.btn_files_close);
		btn_close.setOnClickListener(this);

		if (objectFilesList != null) {

			fileAdapter = new SSFileListAdapter(getContext(), objectFilesList);

			fileList.setAdapter(fileAdapter);

			fileSearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text
					String text = fileSearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					fileAdapter.filter(text);

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					String text = fileSearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					fileAdapter.filter(text);
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_files_close:
			this.dismiss();
			break;

		default:
			break;
		}
	}

}

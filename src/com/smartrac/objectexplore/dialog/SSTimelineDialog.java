package com.smartrac.objectexplore.dialog;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSTimelineAdapter;
import com.smartrac.objectexplore.adapter.SSTimelinePopupAdapter;
import com.smartrac.objectexplore.customviews.HorizontalListView;
import com.smartrac.objectexplore.models.SSObjectTimelineModel;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSTimelineDialog extends Dialog implements
		android.view.View.OnClickListener {

	EditText timelineSearch;
	SSTimelineAdapter timelineAdapter;
	ArrayList<SSObjectTimelineModel> objectTimelineList;
	ArrayList<SSObjectTimelineModel> objectTimelineFilterlist;
	ArrayList<String> timelineFilterList;
	SSObjectActivity mContext;
	ImageView btn_close;
	TextView timelineTitle;
	String objectName;
	SSObjectTimelineModel timelineModel;
	String category;
	HorizontalListView horizontalList;
	ArrayList<SSObjectTimelineModel> arrayList = null;

	public SSTimelineDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSTimelineDialog(Context context,
			ArrayList<SSObjectTimelineModel> objectTimelineList,
			SSObjectTimelineModel timelineModel, String objectName,
			String category) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectTimelineList = objectTimelineList;
		this.timelineModel = timelineModel;
		this.objectName = objectName;
		this.category = category;
		timelineFilterList = new ArrayList<String>();
		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_timeline);
		objectTimelineFilterlist = new ArrayList<SSObjectTimelineModel>();
		timelineTitle = (TextView) findViewById(R.id.timeline_title_text);
		timelineTitle.setText(objectName + "\tTimeline");

		timelineSearch = (EditText) findViewById(R.id.timeline_search_text);
		btn_close = (ImageView) findViewById(R.id.btn_timeline_close);
		btn_close.setOnClickListener(this);
		horizontalList = (HorizontalListView) findViewById(R.id.timeline_list);

		Collections.sort(objectTimelineList, new SSObjectTimelineModel());
		ArrayList<ArrayList<SSObjectTimelineModel>> arrayListMain = new ArrayList<>();
		String[] datePre = new String[objectTimelineList.size()];
		SSObjectTimelineModel iterable_element = new SSObjectTimelineModel();
		for (int i = 0; i < objectTimelineList.size(); i++) {

			iterable_element = objectTimelineList.get(i);
			Date date = new Date(objectTimelineList.get(i)
					.getLastModifiedTimestamp());
			Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			datePre[i] = dateFormat.format(date);
			// for 0th position
			if (i == 0) {
				arrayList = new ArrayList<>();
				arrayList.add(iterable_element);
			} else if (i != 0 && datePre[i].equals(datePre[i - 1])) {
				arrayList.add(iterable_element);

			} else {
				arrayList = new ArrayList<>();
				arrayList.add(iterable_element);
			}
			arrayListMain.add(arrayList);
			timelineFilterList.add(datePre[i]);
			Set<String> hs = new HashSet<>();
			hs.addAll(timelineFilterList);
			timelineFilterList.clear();
			timelineFilterList.addAll(hs);

		}
		

		// ArrayList<ArrayList<SSObjectTimelineModel>> arrayListMain = new
		// ArrayList<>();
		//
		// for (SSObjectTimelineModel iterable_element : objectTimelineList) {
		// String datePre = null;
		//
		// Date date = new Date(iterable_element.getLastModifiedTimestamp());
		// Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// if (datePre == null) {
		// datePre = dateFormat.format(date);
		// arrayList = new ArrayList<>();
		// }
		//
		// if (datePre.equals(dateFormat.format(date))) {
		// arrayList.add(iterable_element);
		// // arrayListMain.add(arrayList);
		// datePre = dateFormat.format(date);
		// arrayList = new ArrayList<>();
		// }
		//
		// arrayList.add(iterable_element);
		// arrayListMain.add(arrayList);
		//
		// }

		timelineAdapter = new SSTimelineAdapter(mContext, timelineFilterList);

		horizontalList.setAdapter(timelineAdapter);
		horizontalList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						showPopup(view, arrayList);
					}
				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_timeline_close:
			this.dismiss();
			break;

		default:
			break;
		}
	}

	public void showPopup(View selectedView,
			ArrayList<SSObjectTimelineModel> arrayList2) {
		// TODO Auto-generated method stub
		this.objectTimelineFilterlist = arrayList2;
		final PopupWindow popupWindow;
		ListView popupTimelineList;
		final View listSelected = selectedView;

		View popupView = mContext.getLayoutInflater().inflate(
				R.layout.object_timeline_popup, null);
		popupTimelineList = (ListView) popupView
				.findViewById(R.id.timeline_popup_list);

		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setContentView(popupView);

		popupWindow.setWidth(220);
		popupWindow.setHeight(200);

		popupWindow.setFocusable(true);

		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		int location[] = new int[2];
		listSelected.getLocationOnScreen(location);

		int OFFSET_X = 200;
		int OFFSET_Y = 100;

		Point p = new Point();
		p.x = location[0];
		p.y = location[1];
		Log.d("x", Integer.toString(p.x));
		Log.d("y", Integer.toString(p.y));

		popupWindow.showAtLocation(listSelected, Gravity.NO_GRAVITY, p.x
				- OFFSET_X, p.y + OFFSET_Y);
		SSTimelinePopupAdapter popupTimelineAdapter = new SSTimelinePopupAdapter(
				mContext, objectTimelineFilterlist);

		popupTimelineList.setAdapter(popupTimelineAdapter);

	}

}

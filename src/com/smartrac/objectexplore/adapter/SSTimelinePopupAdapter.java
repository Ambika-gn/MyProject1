package com.smartrac.objectexplore.adapter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSObjectTimelineModel;

public class SSTimelinePopupAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<SSObjectTimelineModel> objectFilterList;
	ViewHolder holder;

	public SSTimelinePopupAdapter(Context context,
			ArrayList<SSObjectTimelineModel> objectFilterList) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.objectFilterList = objectFilterList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectFilterList.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	private static class ViewHolder {
		TextView textView1;
		TextView textView2;

	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ViewHolder();
		long date = objectFilterList.get(pos).getLastModifiedTimestamp();
		String timelineName = objectFilterList.get(pos).getName();
		String timeString = convertTime(date);
		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(
					R.layout.object_timeline_popup_items, parent, false);

			TextView timelineHour = (TextView) convertView
					.findViewById(R.id.timeline_hour);
			TextView timelineDesc = (TextView) convertView
					.findViewById(R.id.timeline_desc);

			holder.textView1 = timelineHour;
			holder.textView2 = timelineDesc;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(timeString + "\thrs");
		holder.textView2.setText(timelineName);

		return convertView;
	}

	@SuppressLint("SimpleDateFormat")
	public String convertTime(long time) {
		Date date = new Date(time);
		Format dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);

	}

}

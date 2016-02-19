package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;

public class SSTimelineAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<String> timelineFilterList;
	ViewHolder holder;

	public SSTimelineAdapter(Context context,
			ArrayList<String> timelineFilterList) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.timelineFilterList = timelineFilterList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return timelineFilterList.size();
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

	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ViewHolder();
		String dateString = timelineFilterList.get(pos);

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_timeline_items,
					parent, false);

			TextView name = (TextView) convertView
					.findViewById(R.id.timeline_name);

			holder.textView1 = name;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(dateString);

		return convertView;
	}

	

}

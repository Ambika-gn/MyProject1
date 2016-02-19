package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSMetadataModel;

public class SSMetadataListAdapter extends BaseAdapter {

	Context context;
	ArrayList<SSMetadataModel> objectMetadataListValues;
	LayoutInflater inflater;
	ViewHolder holder;

	public SSMetadataListAdapter(Context context,
			ArrayList<SSMetadataModel> objectMetadataListValues) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;

		this.objectMetadataListValues = objectMetadataListValues;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectMetadataListValues.size();
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
		TextView textView3;

	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ViewHolder();

		String keyValue = objectMetadataListValues.get(pos).getKey();
		String dataTypeValue = objectMetadataListValues.get(pos).getDataType();
		String rawValue = objectMetadataListValues.get(pos).getRawValue();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_metadata_items,
					parent, false);

			TextView key = (TextView) convertView
					.findViewById(R.id.metadata_keyValue);
			TextView dataType = (TextView) convertView
					.findViewById(R.id.metadata_datatypeValue);
			TextView raw = (TextView) convertView
					.findViewById(R.id.metadata_valueValue);
			holder.textView1 = key;
			holder.textView2 = dataType;
			holder.textView3 = raw;
			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(keyValue);
		holder.textView2.setText(dataTypeValue);
		holder.textView3.setText(rawValue);

		return convertView;
	}

}

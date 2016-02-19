package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;

public class SSInstanceListAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> instanceListValues;
	LayoutInflater inflater;
	ViewHolder holder;

	public SSInstanceListAdapter(Context context,
			ArrayList<String> instanceListValues) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.instanceListValues = instanceListValues;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return instanceListValues.size();
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

		String instanceValues = instanceListValues.get(pos);

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.instance_dialog_items,
					parent, false);

			TextView shInstance = (TextView) convertView
					.findViewById(R.id.instances);

			holder.textView1 = shInstance;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(instanceValues);

		return convertView;
	}

}

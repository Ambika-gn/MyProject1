package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSTagsModel;

public class SSHashtagsListAdapter extends BaseAdapter {

	Context context;
	ArrayList<SSTagsModel> objectHashtagsListValues;
	LayoutInflater inflater;
	ViewHolder holder;

	public SSHashtagsListAdapter(Context context,
			ArrayList<SSTagsModel> objectHashtagsListValues) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;

		this.objectHashtagsListValues = objectHashtagsListValues;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectHashtagsListValues.size();
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

		String tagName = objectHashtagsListValues.get(pos).getName();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_hashtags_items,
					parent, false);

			TextView tag = (TextView) convertView.findViewById(R.id.tag_name);

			holder.textView1 = tag;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();
		StringBuilder sb = new StringBuilder("#");
		sb.append(tagName);

		holder.textView1.setText(sb);

		return convertView;
	}

}

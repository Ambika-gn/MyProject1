package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSOuterModel;

public class SSOuterListAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	ArrayList<SSOuterModel> objectInterRelationshipList;
	ViewHolder holder;
	String objectRelName, selectedObject;

	public SSOuterListAdapter(Context context, String selectedObject,
			String objectRelName,
			ArrayList<SSOuterModel> objectInterRelationshipList) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.selectedObject = selectedObject;
		this.objectRelName = objectRelName;
		this.objectInterRelationshipList = objectInterRelationshipList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectInterRelationshipList.size();
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
		String relType = objectInterRelationshipList.get(pos)
				.getRelationshipType();
		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(
					R.layout.object_relationships_main_list_items, parent,
					false);
			TextView mainObject = (TextView) convertView
					.findViewById(R.id.text_rel_object);

			holder.textView1 = mainObject;

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		holder.textView1.setText(selectedObject + "\t" + relType + "\t"
				+ objectRelName);

		return convertView;

	}

}

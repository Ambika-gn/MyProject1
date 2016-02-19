package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSObjectLibraryChildrenModel;

public class SSLibraryChildListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<SSObjectLibraryChildrenModel> objectLibraryChildList;
	ViewHolder holder;
	private boolean isChild = false;

	public SSLibraryChildListAdapter(Context context,
			ArrayList<SSObjectLibraryChildrenModel> objectLibraryChildList) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.objectLibraryChildList = objectLibraryChildList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectLibraryChildList.size();
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

		String libraryName = objectLibraryChildList.get(pos).getName();
		isChild = objectLibraryChildList.get(pos).isChildAvailable();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_library_child_items,
					parent, false);

			TextView name = (TextView) convertView
					.findViewById(R.id.library_name_child);
			TextView arrow = (TextView) convertView
					.findViewById(R.id.library_arrow_child);

			holder.textView1 = name;
			holder.textView2 = arrow;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(libraryName);
		if (isChild) {
			holder.textView2.setVisibility(View.VISIBLE);

		}

		return convertView;
	}

}

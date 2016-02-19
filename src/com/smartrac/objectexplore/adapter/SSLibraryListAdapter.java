package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSObjectLibraryModel;

public class SSLibraryListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<SSObjectLibraryModel> objectLibraryList;
	ArrayList<SSObjectLibraryModel> objectLibraryListValues;
	ViewHolder holder;

	public SSLibraryListAdapter(Context context,
			ArrayList<SSObjectLibraryModel> objectLibraryListValues) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.objectLibraryListValues = objectLibraryListValues;
		this.objectLibraryList = new ArrayList<SSObjectLibraryModel>();
		this.objectLibraryList.addAll(objectLibraryListValues);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectLibraryListValues.size();
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

		String libraryName = objectLibraryListValues.get(pos).getLibraryName();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_library_items,
					parent, false);

			TextView name = (TextView) convertView
					.findViewById(R.id.library_name);

			holder.textView1 = name;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(libraryName);

		return convertView;
	}

	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		objectLibraryListValues.clear();
		if (charText.length() == 0) {
			objectLibraryListValues.addAll(objectLibraryList);
		} else {

			for (SSObjectLibraryModel object : objectLibraryList) {
				if (object.getLibraryName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					objectLibraryListValues.add(object);
				}
			}

		}
		this.notifyDataSetChanged();
	}

}

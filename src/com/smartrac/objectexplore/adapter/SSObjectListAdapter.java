package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSObjectModel;

public class SSObjectListAdapter extends BaseAdapter {

	Context context;
	ArrayList<SSObjectModel> objectListValues;
	LayoutInflater inflater;
	private ArrayList<SSObjectModel> objectList;
	ImageView infoButton;
	ObjectListViewHolder holder;

	public SSObjectListAdapter(Context context,
			ArrayList<SSObjectModel> objectListValues, OnTouchListener listener) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.objectListValues = objectListValues;
		this.objectList = new ArrayList<SSObjectModel>();
		this.objectList.addAll(objectListValues);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectListValues.size();
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

	public static class ObjectListViewHolder {
		TextView textView;
		ImageView imageView;
		ImageView info;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ObjectListViewHolder();

		boolean flag = objectListValues.get(pos).getActiveFlag();
		String name = objectListValues.get(pos).getName();
		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.fragment_object_list_items,
					parent, false);

			TextView objectName = (TextView) convertView
					.findViewById(R.id.object_name);
			ImageView objectStatus = (ImageView) convertView
					.findViewById(R.id.object_status_image);
			infoButton = (ImageView) convertView.findViewById(R.id.btn_info);
			holder.textView = objectName;
			holder.imageView = objectStatus;
			holder.info = infoButton;
			convertView.setTag(holder);
		}

		else
			holder = (ObjectListViewHolder) convertView.getTag();

		holder.textView.setText(name);

		if (flag) {

			holder.imageView.setImageResource(R.drawable.active);
		} else {
			holder.imageView.setImageResource(R.drawable.inactive);

		}
		/*convertView.setOnTouchListener(new OnSwipeTouchListener(context) {
			public void onSwipeTop() {
				Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
			}

			public void onSwipeRight() {
				Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
				holder.info.setVisibility(View.VISIBLE);
			}

			public void onSwipeLeft() {
				Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
			}

			public void onSwipeBottom() {
				Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
			}

		});*/

		return convertView;
	}

	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		objectListValues.clear();
		if (charText.length() == 0) {
			objectListValues.addAll(objectList);
		} else {
			for (SSObjectModel object : objectList) {
				if (object.getName().toLowerCase(Locale.getDefault())
						.startsWith(charText)) {
					objectListValues.add(object);
				}
			}
		}
		this.notifyDataSetChanged();
	}

}

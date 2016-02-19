package com.smartrac.objectexplore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;

public class SSDataListAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	ArrayList<String> relationsDataList;
	ViewHolder holder;

	public SSDataListAdapter(Context context,
			ArrayList<String> relationsDataList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.relationsDataList = relationsDataList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (relationsDataList.size() == 0)
			return 1;
		else
			return relationsDataList.size();
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
		ImageView imageView1;

	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ViewHolder();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(
					R.layout.object_relationships_list_items, parent, false);
			TextView dataType = (TextView) convertView
					.findViewById(R.id.relationships_object_name);
			ImageView dataImage = (ImageView) convertView
					.findViewById(R.id.relationships_object_status_image);

			holder.textView1 = dataType;
			holder.imageView1 = dataImage;

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		if (relationsDataList.size() == 0) {
			holder.imageView1.setVisibility(View.GONE);
			holder.textView1.setText("No Data Found");
		} else {
			checkDataAvailiability(pos);
		}

		return convertView;

	}

	private void checkDataAvailiability(int pos) {
		// TODO Auto-generated method stub

		if (relationsDataList.get(pos).equals("Metadata")) {
			setDataImageIconText("Metadata");
		}
		if (relationsDataList.get(pos).equals("Hashtag")) {
			setDataImageIconText("Hashtag");
		}

		if (relationsDataList.get(pos).equals("Files")) {
			setDataImageIconText("Files");
		}
		if (relationsDataList.get(pos).equals("Geospatial")) {
			setDataImageIconText("Geospatial");
		}

		if (relationsDataList.get(pos).equals("Timeline")) {
			setDataImageIconText("Timeline");
		}

	}

	private void setDataImageIconText(String imageType) {
		// TODO Auto-generated method stub
		switch (imageType) {
		case "Metadata":
			holder.imageView1.setImageResource(R.drawable.icon_metadata);
			holder.textView1.setText("Metadata");
			break;

		case "Files":
			holder.imageView1.setImageResource(R.drawable.icon_files);
			holder.textView1.setText("Files");
			break;

		case "Hashtag":
			holder.imageView1.setImageResource(R.drawable.icon_hashtags);
			holder.textView1.setText("Hashtag");
			break;

		case "Geospatial":
			holder.imageView1.setImageResource(R.drawable.icon_geospatial);
			holder.textView1.setText("Geospatial");
			break;

		case "Timeline":
			holder.imageView1.setImageResource(R.drawable.icon_timeline);
			holder.textView1.setText("Timeline");
			break;

		default:
			break;
		}

	}

}

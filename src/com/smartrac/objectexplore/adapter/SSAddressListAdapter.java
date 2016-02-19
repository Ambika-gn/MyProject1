package com.smartrac.objectexplore.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.models.SSAddressModel;
import com.smartrac.objectexplore.models.SSObjectAddressModel;

@SuppressLint("SimpleDateFormat")
public class SSAddressListAdapter extends BaseAdapter {

	Context context;
	ArrayList<SSObjectAddressModel> objectAddressListValues;
	private ArrayList<SSObjectAddressModel> objectAddressList;
	ArrayList<SSAddressModel> addressDetails;
	LayoutInflater inflater;
	ViewHolder holder;

	public SSAddressListAdapter(Context context,
			ArrayList<SSObjectAddressModel> objectAddressListValues,
			ArrayList<SSAddressModel> addressDetails) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.objectAddressListValues = objectAddressListValues;
		this.addressDetails = addressDetails;
		this.objectAddressList = new ArrayList<SSObjectAddressModel>();
		this.objectAddressList.addAll(objectAddressListValues);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectAddressListValues.size();
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

		long timeStamp = objectAddressListValues.get(pos).getTimestamp();
		Date timeStampDate = new Date(timeStamp);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormat.format(timeStampDate);

		String city = objectAddressListValues.get(pos).getCity();
		String name = addressDetails.get(pos).getName();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_address_items,
					parent, false);

			TextView date = (TextView) convertView
					.findViewById(R.id.address_date);
			TextView addressMessage = (TextView) convertView
					.findViewById(R.id.address);

			holder.textView1 = date;
			holder.textView2 = addressMessage;

			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();

		holder.textView1.setText(dateString);
		holder.textView2.setText(name + "\twas at\t" + city);

		return convertView;
	}

	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		objectAddressListValues.clear();
		if (charText.length() == 0) {
			objectAddressListValues.addAll(objectAddressList);
		} else {

			for (SSObjectAddressModel object : objectAddressList) {
				if (object.getCity().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					objectAddressListValues.add(object);
				}
			}

		}
		this.notifyDataSetChanged();
	}

}

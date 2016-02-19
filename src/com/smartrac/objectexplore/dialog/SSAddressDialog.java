package com.smartrac.objectexplore.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSAddressListAdapter;
import com.smartrac.objectexplore.fragments.SSAddressMapDialogFragment;
import com.smartrac.objectexplore.models.SSAddressModel;
import com.smartrac.objectexplore.models.SSObjectAddressModel;
import com.smartrac.objectexplore.views.SSObjectActivity;

public class SSAddressDialog extends Dialog implements
		android.view.View.OnClickListener {

	ListView addressList;
	EditText addressSearch;
	SSAddressListAdapter addressAdapter;
	ArrayList<SSObjectAddressModel> objectAddressList;
	ArrayList<SSAddressModel> addressDetails;
	SSObjectActivity mContext;
	ImageView btn_close;
	TextView addressTitle;
	String objectName;
	ArrayList<SSAddressModel> objectAddress;

	public SSAddressDialog(Context context) {
		super(context);
	}

	@SuppressWarnings("static-access")
	public SSAddressDialog(Context context,
			ArrayList<SSObjectAddressModel> objectAddressList,
			ArrayList<SSAddressModel> addressDetails, String objectName) {
		super(context);
		this.mContext = (SSObjectActivity) context;
		this.objectAddressList = objectAddressList;
		this.addressDetails = addressDetails;
		this.objectName = objectName;

		this.requestWindowFeature(this.getWindow().FEATURE_NO_TITLE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.object_address);

		addressTitle = (TextView) findViewById(R.id.address_title_text);
		addressTitle.setText(objectName + "\tAddress");
		addressList = (ListView) findViewById(R.id.object_address_list);
		addressSearch = (EditText) findViewById(R.id.address_search);
		btn_close = (ImageView) findViewById(R.id.btn_address_close);
		btn_close.setOnClickListener(this);

		addressList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@SuppressLint("SimpleDateFormat")
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						List<String> unselectAddress = new ArrayList<String>();
						String location = objectAddressList.get(position)
								.getCity();
						long timeStamp = objectAddressList.get(position)
								.getTimestamp();
						Date timeStampDate = new Date(timeStamp);
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"dd/MM/yyyy");
						String dateString = dateFormat.format(timeStampDate);
						FragmentManager fragManager = mContext
								.getSupportFragmentManager();
						SSAddressMapDialogFragment dialog = new SSAddressMapDialogFragment(
								mContext, objectName);
						for (int i = 0; i < objectAddressList.size(); i++) {
							unselectAddress.add(objectAddressList.get(i)
									.getCity());
						}
						dialog.setLocationValues(location, dateString,
								unselectAddress);
						dialog.show(fragManager, "");

					}
				});

		if (objectAddressList != null) {

			addressAdapter = new SSAddressListAdapter(getContext(),
					objectAddressList, addressDetails);

			addressList.setAdapter(addressAdapter);

			addressSearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text
					String text = addressSearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					addressAdapter.filter(text);

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					String text = addressSearch.getText().toString()
							.toLowerCase(Locale.getDefault());
					addressAdapter.filter(text);
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_address_close:
			this.dismiss();
			break;

		default:
			break;
		}
	}

}

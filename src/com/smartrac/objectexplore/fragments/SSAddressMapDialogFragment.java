package com.smartrac.objectexplore.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.views.SSObjectActivity;

@SuppressLint("ValidFragment")
public class SSAddressMapDialogFragment extends DialogFragment implements
		OnClickListener, OnMapReadyCallback {
	SSObjectActivity mContext;
	String location;
	GoogleMap googleMap;
	String selectedLocation, selectedDate;
	SupportMapFragment mMapFragment;
	FragmentManager mapFragment;

	LatLng p1 = null;
	Context context;
	ImageView btn_close;
	String objectName;
	List<Address> addresses;
	List<Address> selectedAddress;
	List<String> unselectAddr;
	List<List<Address>> addressList = new ArrayList<List<Address>>();
	List<LatLng> ll, latLongList;

	public SSAddressMapDialogFragment(Context context, String objectName) {
		// TODO Auto-generated constructor stub
		this.mContext = (SSObjectActivity) context;
		this.objectName = objectName;
		mMapFragment = SupportMapFragment.newInstance();
		selectedAddress=new ArrayList<Address>();
		latLongList=new ArrayList<LatLng>();

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}

	public void setLocationValues(String location, String date,
			List<String> unselectAddr) {
		selectedLocation = location;
		selectedDate = date;
		this.unselectAddr = unselectAddr;
	}

	public List<LatLng> getLocationFromAddress(String selectAddr,
			List<String> unselectAddr) {
		if (Geocoder.isPresent()) {
			try {

				Geocoder gc = new Geocoder(getActivity());
				for (int i = 0; i < unselectAddr.size(); i++) {
					if ((unselectAddr.get(i)).equals(selectAddr)) {
						selectedAddress = gc.getFromLocationName(
								unselectAddr.get(i), 10);
					}
					addresses = gc.getFromLocationName(unselectAddr.get(i), 10);
					addressList.add(addresses);
				}
				ll = new ArrayList<LatLng>(addressList.size());
				for (List<Address> addrList : addressList) {
					for (Address a : addrList) {
						if (a.hasLatitude() && a.hasLongitude()) {
							ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
						}
					}
				}
			} catch (IOException e) {
				// handle the exception
			}
		}
		return ll;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_address_map, container, false);
		TextView mapAddressTitle = (TextView) view
				.findViewById(R.id.address_map_title_text);
		mapAddressTitle.setText(objectName + "\tAddress");
		TextView mapAddressObject = (TextView) view
				.findViewById(R.id.address_map_text);
		String mapAddressText = objectName + "\t was at this Address";
		mapAddressObject.setText(mapAddressText);
		btn_close = (ImageView) view.findViewById(R.id.btn_address_map_close);
		btn_close.setOnClickListener(this);

		FragmentTransaction fragmentTransaction = getChildFragmentManager()
				.beginTransaction();
		fragmentTransaction.add(R.id.mapAddress, mMapFragment);
		fragmentTransaction.commit();

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_address_map_close:
			getDialog().dismiss();
			break;

		default:
			break;
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		latLongList = getLocationFromAddress(selectedLocation, unselectAddr);
		mMapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		LatLng selectedLatLong = null;
		for (Address selectedAddr : selectedAddress) {
			if (selectedAddr.hasLatitude() && selectedAddr.hasLongitude()) {
				selectedLatLong = new LatLng(selectedAddr.getLatitude(),
						selectedAddr.getLongitude());
			}
		}
		for (int i = 0; i < latLongList.size(); i++) {

			if (latLongList.get(i).equals(selectedLatLong)) {
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
						latLongList.get(i), 13));
				googleMap.addMarker(new MarkerOptions()
						.position(latLongList.get(i))
						.title(selectedDate)
						.snippet(selectedLocation)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.icon_pin1)));
			} else {
				googleMap.addMarker(new MarkerOptions()
						.position(latLongList.get(i))
						.title(selectedDate)
						.snippet(selectedLocation)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.icon_pin2)));
			}
		}

	}

}

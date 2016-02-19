package com.smartrac.objectexplore.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.adapter.SSInstanceListAdapter;

public class SSInstanceFragment extends Fragment {
	ListView instance_list;
	ArrayList<String> instanceValues;

	OnInstancelistListener mCallback;

	public interface OnInstancelistListener {
		public void addInstances(int position, ArrayList<String> instances);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnInstancelistListener) activity;

		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnInstancelistListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater
				.inflate(R.layout.instance_dialog, container, false);
		instance_list = (ListView) view.findViewById(R.id.instance_list);
		if (instanceValues != null) {
			SSInstanceListAdapter instanceAdapter = new SSInstanceListAdapter(
					getActivity(), instanceValues);

			instance_list.setAdapter(instanceAdapter);
			instance_list
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							mCallback.addInstances(position, instanceValues);

						}
					});
		}
		return view;
	}

	public void setInstances(ArrayList<String> instanceValues) {
		this.instanceValues = instanceValues;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;

	}

}

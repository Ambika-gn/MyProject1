package com.smartrac.objectexplore.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartrac.objectexplore.R;
import com.smartrac.objectexplore.common.SSConstants;
import com.smartrac.objectexplore.models.SSObjectFilesModel;

public class SSFileListAdapter extends BaseAdapter {

	Context context;
	ArrayList<SSObjectFilesModel> objectFileListValues;
	LayoutInflater inflater;
	private ArrayList<SSObjectFilesModel> objectFileList;
	ViewHolder holder;

	public SSFileListAdapter(Context context,
			ArrayList<SSObjectFilesModel> objectFileListValues) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;

		this.objectFileListValues = objectFileListValues;
		this.objectFileList = new ArrayList<SSObjectFilesModel>();
		this.objectFileList.addAll(objectFileListValues);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectFileListValues.size();
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
		ImageView imageView;
		TextView textView;

	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = new ViewHolder();

		String filename = objectFileListValues.get(pos).getFileName();

		final String filenameShort = filename.length() <= 15 ? filename
				: filename.substring(filename.length() - 15);
		String mimeType = objectFileListValues.get(pos).getMimeType();
		final String fileDownload = objectFileListValues.get(pos).getUrn();

		View convertView = view;
		if (view == null) {
			convertView = inflater.inflate(R.layout.object_files_items, parent,
					false);

			TextView fileName = (TextView) convertView
					.findViewById(R.id.filename);
			ImageView fileIcon = (ImageView) convertView
					.findViewById(R.id.file_icon);
			holder.textView = fileName;
			holder.imageView = fileIcon;
			convertView.setTag(holder);
		}

		else
			holder = (ViewHolder) convertView.getTag();
		setFileIcon(mimeType);

		holder.textView.setText(filenameShort);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String fileDownloadPath = SSConstants.serverURLPath
						+ "/rest/files/" + fileDownload + "/contents";
				Log.d("filepath:", fileDownloadPath);

				Intent fileIntent = new Intent(Intent.ACTION_SEND);

				fileIntent.setType("message/rfc822");

				fileIntent.putExtra(Intent.EXTRA_SUBJECT, filenameShort);
				fileIntent.putExtra(Intent.EXTRA_STREAM,
						Uri.fromFile(new File(fileDownloadPath)));

				context.startActivity(Intent.createChooser(fileIntent,
						"Share File Using"));
			}
		});

		return convertView;
	}

	private void setFileIcon(String mimeType) {
		// TODO Auto-generated method stub

		switch (mimeType) {
		case "image/jpg":
			holder.imageView.setImageResource(R.drawable.icon_jpg);
			break;

		case "image/gif":
			holder.imageView.setImageResource(R.drawable.icon_jpg);
			break;
		case "image/pdf":
			holder.imageView.setImageResource(R.drawable.icon_pdf);
			break;
		case "image/ai":
			holder.imageView.setImageResource(R.drawable.icon_ai);
			break;
		case "image/xls":
			holder.imageView.setImageResource(R.drawable.icon_xls);
			break;
		case "image/bmp":
			holder.imageView.setImageResource(R.drawable.icon_bmp);
			break;
		case "image/doc":
			holder.imageView.setImageResource(R.drawable.icon_doc);
			break;
		case "image/png":
			holder.imageView.setImageResource(R.drawable.icon_png);
			break;
		case "image/ppt":
			holder.imageView.setImageResource(R.drawable.icon_ppt);
			break;
		case "image/xml":
			holder.imageView.setImageResource(R.drawable.icon_xml);
			break;

		default:
			break;
		}

	}

	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		objectFileListValues.clear();
		if (charText.length() == 0) {
			objectFileListValues.addAll(objectFileList);
		} else {
			for (SSObjectFilesModel object : objectFileList) {
				if (object.getFileName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					objectFileListValues.add(object);
				}
			}
		}
		this.notifyDataSetChanged();
	}

}

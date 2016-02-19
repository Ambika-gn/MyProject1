package com.smartrac.objectexplore.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

public class SSServiceHandler {

	String response = null;
	public static final int GET = 1;
	public final static int POST = 2;
	static InputStream is = null;

	Context mContext;
	Dialog dialog = null;

	public SSServiceHandler() {

	}

	public String makeServiceCall(String url, int method, String auth) {
		return this.makeServiceCall(url, method, auth, null);
	}

	public String makeServiceCall(String url, int method, String auth,
			List<NameValuePair> params) {
		try {

			HttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			int statusCode = 0;

			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 15000;
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);

			int timeoutSocket = 15000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

			// DefaultHttpClient httpClient = new
			// DefaultHttpClient(httpParameters);

			if (method == POST) {

				HttpURLConnection connection = (HttpURLConnection) new URL(url)
						.openConnection();
				connection.setRequestProperty("Authorization", auth);
				connection.setUseCaches(false);
				connection.connect();
				HttpPost httpPost = new HttpPost(url);

				if (params != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}

				httpResponse = httpClient.execute(httpPost);

			} else if (method == GET) {

				if (params != null) {
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
				}
				HttpGet httpGet = new HttpGet(url);
				// "http://ec2-52-11-184-204.us-west-2.compute.amazonaws.com:8080/rest/objects"
				Log.d("url", url);
				httpGet.addHeader("Authorization", auth);
				httpGet.setHeader("Accept", "application/json");

				httpResponse = httpClient.execute(httpGet);
				if (httpResponse != null) {
					statusCode = httpResponse.getStatusLine().getStatusCode();
					if (statusCode == 200) {
						httpEntity = httpResponse.getEntity();

						if (httpEntity != null) {
							InputStream instream = httpEntity.getContent();
							response = convertStreamToString(instream);
							instream.close();
						}
					}
					if (statusCode == 401) {
						response = Integer.toString(statusCode);

					}
					if (statusCode == 204) {
						response = null;
						Log.d("Response:", "No content");

					}
					if (statusCode == 400) {
						response = null;
						Log.d("Response:", "Bad Request");
					}
				} else {
					response = null;
				}

			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		return response;

	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}

package com.example.retrofitwithsoap;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.SimpleXMLConverter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

public class MethodClass {
	private Context cnt;
	RestAdapter adapter;
	Gson gson;

	MyRetrofit myretro;
	Result inter;
	RequestEnvelope envelope, envelopse;

	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	public MethodClass(Context cnt, Result inter) {

		try {
			this.cnt = cnt;
			this.inter = inter;
			Strategy strategy = new AnnotationStrategy();
			Serializer serializer = new Persister(strategy);
			OkHttpClient okHttpClient = new OkHttpClient();
			adapter = new RestAdapter.Builder().setEndpoint(URLS.COMMONURL)
					.setClient(new OkClient(okHttpClient))
					.setConverter(new SimpleXMLConverter(serializer))
					.setLogLevel(RestAdapter.LogLevel.FULL).build();
			myretro = adapter.create(MyRetrofit.class);
			RequestBody body = new RequestBody();
			userdetails GetUserDetails = new userdetails("http://tempuri.org/",
					"pankaj.arora686@gmail.com", "abc123");
			body.setDetails(GetUserDetails);
			envelope = new RequestEnvelope();
			envelope.setBody(body);
			byte[] bytes = envelope.toString().getBytes();
			OutputStream os = new ByteArrayOutputStream();
			try {
				os.write(bytes);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			serializer.write(envelope, os);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void makerequest() {
		Dialogclass.getDialog(cnt);
		myretro.getdata(envelope, new Callback<String>() {

			@Override
			public void success(String arg0, Response response) {
				Dialogclass.Logout();
				try {
					System.out.println("inside success");
					InputStream is = response.getBody().in();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					is.close();
					if (sb.toString() != null) {
						System.out.println("json response" + sb);
						String responsee = doxmlparsing(sb.toString());
						inter.getdata(responsee);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void failure(RetrofitError arg0) {
				Dialogclass.Logout();
				System.out.println("inside failure");
			}
		});
	}
	public String doxmlparsing(String data) {
		String resposne = null;
		try {

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(data));
			int eventType = parser.getEventType();
			String tagname = "";
			String text = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.TEXT:
					text = parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("GetUserDetailsResult")) {
						resposne = text;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resposne;
	}

}

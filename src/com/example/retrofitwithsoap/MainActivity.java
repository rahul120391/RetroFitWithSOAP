package com.example.retrofitwithsoap;

import java.io.StringReader;
import java.util.Locale;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements Result {

	Button btn_click, btn_change_language;
    public SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle(getString(R.string.app_name));
		final MethodClass classs = new MethodClass(MainActivity.this, this);
		btn_click = (Button) findViewById(R.id.btn_click);
		btn_click.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				classs.makerequest();
			}
		});
		btn_change_language = (Button) findViewById(R.id.btn_change_language);
		btn_change_language.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setLocale("es");
			}
		});
	}

	@Override
	public void getdata(String result) {
		try {
			JSONObject jobject = new JSONObject(result);
			System.out.println("jobject" + jobject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	private void setLocale(String localeCode) {
		Locale locale = new Locale(localeCode);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
		getApplicationContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
		getApplicationContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
		onCreate(null);
		onConfigurationChanged(config);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setTitle(getString(R.string.app_name));
	}
}

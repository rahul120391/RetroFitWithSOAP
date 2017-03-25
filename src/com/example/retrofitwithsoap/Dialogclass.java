package com.example.retrofitwithsoap;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialogclass {
	public static ProgressDialog dialog;
	public static void getDialog(Context cnt)
	{
		if(dialog==null || !dialog.isShowing())
		{
		dialog=new ProgressDialog(cnt, ProgressDialog.THEME_HOLO_LIGHT);
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		dialog.setIndeterminate(true);
		dialog.show();
		}
	}
	public static void Logout()
	{
		if(dialog!=null || dialog.isShowing())
		{
			dialog.dismiss();
		}
	}

}

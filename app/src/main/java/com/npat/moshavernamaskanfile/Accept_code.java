package com.npat.moshavernamaskanfile;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Accept_code extends Activity {
	private Handler mHandler;
	boolean continue_or_stop = true;
	boolean createthread=true;
	private EditText acceptcode;
	private Button btnSendAcceptcode;
	private TextView tvRefreshCode;
	private TextView tvTimer;
	private TextView tvPhoneNumber;
	private DatabaseHelper dbh;
	private SQLiteDatabase db;
	private IntentFilter intentFilter;
	private int counter=59;
	private String UserName;
	private String Cookie="";
	private String Password="";

	private BroadcastReceiver intentReciever=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                acceptcode.setText(intent.getExtras().getString("sms"));
                intentReciever.abortBroadcast();
            }
        };

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accept_code);
		try
		{

			UserName = getIntent().getStringExtra("UserName");
		}
		catch (Exception ex)
		{
			UserName="0";
		}
		intentFilter=new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
		int GET_MY_PERMISSION = 1;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED)
			{
				if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_SMS))
				{
					//do nothing
				}
				else{
					ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_SMS},GET_MY_PERMISSION);
				}
			}
		}

		dbh=new DatabaseHelper(getApplicationContext());
		try {

			dbh.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			dbh.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;
		}
//		//Typeface FontMitra = Typeface.createFromAsset(getAssets(), "font/vazir.ttf");//set font for page
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remive page title
		acceptcode=(EditText)findViewById(R.id.etAcceptcode);
		btnSendAcceptcode=(Button)findViewById(R.id.btnSendAcceptCode);
		tvRefreshCode=(TextView) findViewById(R.id.tvRefreshCode);
		tvTimer=(TextView) findViewById(R.id.tvTimer);
		tvPhoneNumber=(TextView) findViewById(R.id.tvPhoneNumber);
		tvPhoneNumber.setText(UserName + "ارسال خواهد شد");
		//set font for element
//		acceptcode.setTypeface(FontMitra);
		//Start Counter Second
		startCountAnimation();
//		btnSendAcceptcode.setTypeface(FontMitra);
		btnSendAcceptcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				InternetConnection ic=new InternetConnection(getApplicationContext());
				if(ic.isConnectingToInternet()){
					if(checkInsert().compareTo("0")!=0)//کنترل وارد کردن کد تاییدیه در تکست باکس
					{
						Send_AcceptCode();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "اتصال به شبکه را چک نمایید.", Toast.LENGTH_LONG).show();
				}
			}
		});
		tvRefreshCode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try
				{
					if(!db.isOpen())
					{
						db = dbh.getReadableDatabase();
					}
				}
				catch (Exception ex)
				{
					db = dbh.getReadableDatabase();
				}
				Cursor cursor = db.rawQuery("SELECT * FROM Login", null);
				if (cursor.getCount() > 0) {
					cursor.moveToNext();
					Cookie = cursor.getString(cursor.getColumnIndex("Cookie"));
					Password = cursor.getString(cursor.getColumnIndex("Password"));
					if (Cookie.compareTo("0") == 0 || Password.compareTo("0") == 0) {
						Cookie = "";
						Password = "";
						if (!cursor.isClosed()) {
							cursor.close();
						}

						if (db.isOpen()) {
							db.close();
						}

						SendUserNameAnaPassword sendUserNameAnaPassword = new SendUserNameAnaPassword(Accept_code.this, UserName, Password, Cookie, true);
						sendUserNameAnaPassword.AsyncExecute();
					} else {
						if (!cursor.isClosed()) {
							cursor.close();
						}

						if (db.isOpen()) {
							db.close();
						}
						SendUserNameAnaPassword sendUserNameAnaPassword = new SendUserNameAnaPassword(Accept_code.this, UserName, Password, Cookie, true);
						sendUserNameAnaPassword.AsyncExecute();
					}
				}

				counter=59;
				startCountAnimation();
			}
		});
		SMSReseiver.bindListener(new SmsListener() {
			@Override
			public void onMessageReceived(String messageText) {
				Log.e("Text",messageText);
				acceptcode.setText("");
				acceptcode.setText(messageText);
				Send_AcceptCode();
			}
		});
	}

	@Override
	public boolean onKeyDown( int keyCode, KeyEvent event )  {
		if ( keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 ) {
//			countDownTimer.cancel();
			LoadActivity(Login.class,"karbarCode","0");
		}
		return super.onKeyDown( keyCode, event );
	}
	public void LoadActivity(Class<?> Cls, String VariableName, String VariableValue)
	{
		if(intentReciever.isOrderedBroadcast())
		{
			intentReciever.abortBroadcast();
		}
		Intent intent = new Intent(getApplicationContext(),Cls);
		intent.putExtra(VariableName, VariableValue);
		Accept_code.this.startActivity(intent);
	}
	public String checkInsert()
	{
		String Acceptcode = acceptcode.getText().toString();
		if(Acceptcode.compareTo("")==0) {
			Toast.makeText(getApplicationContext(), "لطفا کد تایید را وارد نمایید.", Toast.LENGTH_LONG).show();
			return  "0";
		}
		else
		{
			return Acceptcode;
		}
	}
	public void Send_AcceptCode()
	{
		if(intentReciever.isOrderedBroadcast())
		{
			intentReciever.abortBroadcast();
		}
		try
		{
			if(!db.isOpen())
			{
				db = dbh.getReadableDatabase();
			}
		}
		catch (Exception ex)
		{
			db = dbh.getReadableDatabase();
		}
		Cursor cursor = db.rawQuery("SELECT * FROM Login", null);
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			Cookie = cursor.getString(cursor.getColumnIndex("Cookie"));
			if (!cursor.isClosed()) {
				cursor.close();
			}

			if (db.isOpen()) {
				db.close();
			}
			AndroidAcceptCodeSend androidAcceptCodeSend = new AndroidAcceptCodeSend(Accept_code.this, UserName, acceptcode.getText().toString(), Cookie);
			androidAcceptCodeSend.AsyncExecute();
		}
	}

	public void startCountAnimation() {
		continue_or_stop=true;
		if(createthread) {
			mHandler = new Handler();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (continue_or_stop) {
						try {
							Thread.sleep(1000); // every 60 seconds
							mHandler.post(new Runnable() {
								@Override
								public void run() {
										if(counter!=0)
										{
											counter-=1;
											tvTimer.setText(String.valueOf(counter)+ " ثانیه");
											tvRefreshCode.setVisibility(View.GONE);
										}
										else
										{
											continue_or_stop = false;
											createthread = true;
											tvRefreshCode.setVisibility(View.VISIBLE);
										}
								}
							});
						} catch (Exception e) {
							throw new Error("Unable to run counter");
						}
					}
				}
			}).start();

			createthread = false;

		}
	}
	public void onDestroy() {
		super.onDestroy();
		intentReciever.abortBroadcast();
		continue_or_stop=false;
	}
}

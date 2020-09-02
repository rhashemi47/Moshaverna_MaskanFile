package com.npat.moshavernamaskanfile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;

public class SendUserNameAndPassword {

	//Primary Variable
	DatabaseHelper dbh;
	SQLiteDatabase db;
	PublicVariable PV;
    InternetConnection IC;
	private Activity activity;

	private String UserName;
	private String Password;
	private String Cookie;
	private String WsResponse;
	private boolean CuShowDialog=true;
	private boolean Refresh=false;
	//Contractor
	public SendUserNameAndPassword(Activity activity, String UserName, String Password , String Cookie, boolean Refresh) {
		this.activity = activity;

		this.UserName=UserName;
		this.Password=Password;
		this.Cookie=Cookie;
		this.Refresh=Refresh;
		IC = new InternetConnection(this.activity.getApplicationContext());
		PV = new PublicVariable();

	}
	
	public void AsyncExecute()
	{
		if(IC.isConnectingToInternet()==true)
		{
			try
			{
				AsyncCallWS task = new AsyncCallWS(this.activity);
				task.execute();
			}	
			 catch (Exception e) {

	            e.printStackTrace();
			 }
		}
		else
		{
			Toast.makeText(this.activity.getApplicationContext(), "لطفا ارتباط شبکه خود را چک کنید", Toast.LENGTH_SHORT).show();
		}
	}
	
	//Async Method
	private class AsyncCallWS extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog;
		private Activity activity;
		
		public AsyncCallWS(Activity activity) {
		    this.activity = activity;
		    this.dialog = new ProgressDialog(activity);
		    this.dialog.setCanceledOnTouchOutside(false);
		}
		
        @Override
        protected String doInBackground(String... params) {
        	String result = null;
        	try
        	{
        		CallWsMethod("AndroidLogin");
        	}
	    	catch (Exception e) {
	    		result = e.getMessage().toString();
			}
	        return result;
        }
 
        @Override
        protected void onPostExecute(String result) {
        	if(result == null)
        	{
	            if(WsResponse.toString().compareTo("ER") == 0)
	            {
	            	Toast.makeText(this.activity.getApplicationContext(), "خطا در ارتباط با سرور", Toast.LENGTH_LONG).show();
	            }
	            else if(WsResponse.toString().compareTo("0") == 0)
	            {
					Toast.makeText(this.activity.getApplicationContext(), "نام کاربری و یا رمز عبور اشتباه است.", Toast.LENGTH_LONG).show();
	            }
				else if(WsResponse.toString().compareTo("-2") == 0)
				{
					Toast.makeText(this.activity.getApplicationContext(), "این کاربری برروی دستگاه دیگری فعال می باشد.", Toast.LENGTH_LONG).show();
				}
	            else
	            {
	            	InsertDataFromWsToDb(WsResponse);
	            }
        	}
        	else
        	{
        		//Toast.makeText(this.activity, "ط®ط·ط§ ط¯ط± ط§طھطµط§ظ„ ط¨ظ‡ ط³ط±ظˆط±", Toast.LENGTH_SHORT).show();
        	}
            try
            {
            	if (this.dialog.isShowing()) {
            		this.dialog.dismiss();
            	}
            }
            catch (Exception e) {}
        }
 
        @Override
        protected void onPreExecute() {
        	if(CuShowDialog)
        	{
        		this.dialog.setMessage("در حال پردازش");
        		this.dialog.show();
        	}
        }
 
        @Override
        protected void onProgressUpdate(Void... values) {
        }
        
    }
	
	public void CallWsMethod(String METHOD_NAME) {
	    //Create request
	    SoapObject request = new SoapObject(PV.NAMESPACE, METHOD_NAME);
	    //*****************************************************
		PropertyInfo UserNamePI = new PropertyInfo();
		//Set Name
		UserNamePI.setName("UserName");
		//Set Value
		UserNamePI.setValue(this.UserName);
		//Set dataType
		UserNamePI.setType(String.class);
		//Add the property to request object
		request.addProperty(UserNamePI);
		//*****************************************************
		PropertyInfo PasswordPI = new PropertyInfo();
		//Set Name
		PasswordPI.setName("Password");
		//Set Value
		PasswordPI.setValue(this.Password);
		//Set dataType
		PasswordPI.setType(String.class);
		//Add the property to request object
		request.addProperty(PasswordPI);
		//*****************************************************
		PropertyInfo CookiePI = new PropertyInfo();
		//Set Name
		CookiePI.setName("pCookie");
		//Set Value
		CookiePI.setValue(this.Cookie);
		//Set dataType
		CookiePI.setType(String.class);
		//Add the property to request object
		request.addProperty(CookiePI);
	    //Create envelope
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
	            SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    //Set output SOAP object
	    envelope.setOutputSoapObject(request);
	    //Create HTTP call object
	    HttpTransportSE androidHttpTransport = new HttpTransportSE(PV.URL);
	    try {
	        //Invoke web service
	        androidHttpTransport.call("http://tempuri.org/"+METHOD_NAME, envelope);
	        //Get the response
	        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
	        //Assign it to FinalResultForCheck static variable
	        WsResponse = response.toString();	
	        if(WsResponse == null) WsResponse="ER";
	    } catch (Exception e) {
	    	WsResponse = "ER";
	    	e.printStackTrace();
	    }
	}
	
	
	public void InsertDataFromWsToDb(String AllRecord)
    {
		dbh=new DatabaseHelper(this.activity.getApplicationContext());
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
		try { if(!db.isOpen()) { db = dbh.getWritableDatabase();}}	catch (Exception ex){	db = dbh.getWritableDatabase();	}	try { if(!db.isOpen()) { db = dbh.getWritableDatabase();}}	catch (Exception ex){	db = dbh.getWritableDatabase();	}
		String spStrWsResponse[] =  WsResponse.split("\\[##\\]");
		if(spStrWsResponse[0].compareTo("-1") == 0) {
			db.execSQL("UPDATE login SET Cookie='" + spStrWsResponse[1] + "' , Phone = '" + UserName + "'");
		}
		if(db.isOpen())
		{
			db.close();
		}
		if(!Refresh) {
			LoadActivity(Accept_code.class, "UserName", UserName);
		}
	}
	public void LoadActivity(Class<?> Cls, String VariableName, String VariableValue)
	{
		Intent intent = new Intent(activity,Cls);
		intent.putExtra(VariableName, VariableValue);
		activity.startActivity(intent);
	}
}

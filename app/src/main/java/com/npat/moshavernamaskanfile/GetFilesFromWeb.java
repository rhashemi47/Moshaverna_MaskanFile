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

public class GetFilesFromWeb {

	//Primary Variable
	DatabaseHelper dbh;
	SQLiteDatabase db;
	PublicVariable PV;
    InternetConnection IC;
	private Activity activity;

	private String StartPage;
	private String EndPage;
	private String WhereStr;
	private String WsResponse;
	private boolean CuShowDialog=true;
	private boolean Refresh=false;
	//Contractor
	public GetFilesFromWeb(Activity activity, String StartPage, String EndPage , String WhereStr) {
		this.activity = activity;

		this.StartPage=StartPage;
		this.EndPage=EndPage;
		this.WhereStr=WhereStr;
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
        		CallWsMethod("AndroidGetFile");
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
		PropertyInfo StartPagePI = new PropertyInfo();
		//Set Name
		StartPagePI.setName("StartPage");
		//Set Value
		StartPagePI.setValue(this.StartPage);
		//Set dataType
		StartPagePI.setType(String.class);
		//Add the property to request object
		request.addProperty(StartPagePI);
		//*****************************************************
		PropertyInfo EndPagePI = new PropertyInfo();
		//Set Name
		EndPagePI.setName("EndPage");
		//Set Value
		EndPagePI.setValue(this.EndPage);
		//Set dataType
		EndPagePI.setType(String.class);
		//Add the property to request object
		request.addProperty(EndPagePI);
		//*****************************************************
		PropertyInfo WhereStrPI = new PropertyInfo();
		//Set Name
		WhereStrPI.setName("WhereStr");
		//Set Value
		WhereStrPI.setValue(this.WhereStr);
		//Set dataType
		WhereStrPI.setType(String.class);
		//Add the property to request object
		request.addProperty(WhereStrPI);
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
		LoadActivity(MainActivity.class, "StrFiles", WsResponse);
	}
	public void LoadActivity(Class<?> Cls, String VariableName, String VariableValue)
	{
		Intent intent = new Intent(activity,Cls);
		intent.putExtra(VariableName, VariableValue);
		activity.startActivity(intent);
	}
}

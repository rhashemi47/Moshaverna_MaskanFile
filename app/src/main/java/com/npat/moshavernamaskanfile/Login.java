package com.npat.moshavernamaskanfile;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Login extends AppCompatActivity {
    private DatabaseHelper dbh;
    private SQLiteDatabase db;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //******Description *******************
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSend = (Button) findViewById(R.id.btnSend);
        //******Connect To Database ***********
        dbh=new DatabaseHelper(this.getApplicationContext());
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
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = etUserName.getText().toString();
                String Password = etPassword.getText().toString();
                String Cookie="";
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
                    if(Cookie.compareTo("0") == 0)
                    {
                        Cookie="";
                    }
                    if(!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                if(db.isOpen()) {
                    db.close();
                }
                SendUserNameAnaPassword sendUserNameAnaPassword =new SendUserNameAnaPassword(Login.this,UserName,Password,Cookie);
                sendUserNameAnaPassword.AsyncExecute();
            }
        });
    }
}

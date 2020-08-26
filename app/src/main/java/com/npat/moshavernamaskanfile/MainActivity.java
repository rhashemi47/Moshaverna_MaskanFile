package com.npat.moshavernamaskanfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.npat.moshavernamaskanfile.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String StrFiles;
    private ArrayList<DummyContent.DummyItem> valuse=new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {

            StrFiles = getIntent().getStringExtra("StrFiles");
        }
        catch (Exception ex)
        {
            StrFiles="";
        }
        AdapterFilesItemRecyclerViewAdapter adapterFilesItemRecyclerViewAdapter =new AdapterFilesItemRecyclerViewAdapter(valuse);
    }
}
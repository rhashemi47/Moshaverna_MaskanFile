package com.npat.moshavernamaskanfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String StrFiles;
    private RecyclerViewAdapter adapter;
    private RecyclerView recycler_file;
    private Handler mHandler;
    private boolean continue_or_stop=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_file = (RecyclerView) findViewById(R.id.recycler_file);
        try
        {

            StrFiles = getIntent().getStringExtra("StrFiles");
        }
        catch (Exception ex)
        {
            StrFiles="";
        }
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        adapter = new RecyclerViewAdapter(getFile(),this);
        recycler_file.setAdapter(adapter);
        recycler_file.setLayoutManager(layoutManager);
        recycler_file.setItemAnimator(new DefaultItemAnimator());
        recycler_file.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1))
                {
//                    Toast.makeText(MainActivity.this,String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
                    String Start = String.valueOf(adapter.getItemCount());
                    String End = String.valueOf(adapter.getItemCount() + 100);
                    GetFilesFromWebRefresh getFilesFromWebRefresh=new GetFilesFromWebRefresh(MainActivity.this,Start,End,"");
                    getFilesFromWebRefresh.AsyncExecute();
                    continue_or_stop=true;
                    Run_thered();
                }
            }
        });
        
    }
    private void Run_thered() {
        mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (continue_or_stop) {
                    try {
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                if(PublicVariable.Content_Files !="") {
                                    adapter = new RecyclerViewAdapter(getFile(), getApplicationContext());
                                    recycler_file.setAdapter(adapter);
                                    PublicVariable.Content_Files ="";
                                    continue_or_stop=false;
                                }
                            }
                        });

                        Thread.sleep(1000); // every 5 seconds
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }
    private List<Files> getFile() {
        ArrayList<Files> files =new ArrayList<>();
        if(PublicVariable.Content_Files !="") {
            StrFiles = StrFiles  + PublicVariable.Content_Files;
        }
        String spFile[] = StrFiles.split("\\[##\\]");
        for(int i=0; i<spFile.length ; i++) {
            String spDetailFile[] = spFile[i].split("\\[#\\]");
            int CountSpDetailFile = spDetailFile.length;
            if (CountSpDetailFile > 0) {
                files.add(new Files(spDetailFile[0]
                        ,spDetailFile[1]
                        ,spDetailFile[2]
                        ,spDetailFile[3]
                        ,spDetailFile[4]
                        ,spDetailFile[5]
                        ,spDetailFile[6]
                        ,spDetailFile[7]
                        ,spDetailFile[8]
                        ,spDetailFile[9]
                        ,spDetailFile[10]
                        ,spDetailFile[11]
                        ,spDetailFile[12]
                        ,spDetailFile[13]
                        ,spDetailFile[14]
                        ,spDetailFile[15]
                        ,spDetailFile[16]
                        ,spDetailFile[17]
                        ,spDetailFile[18]));
            }
        }
        return files;
    }
}
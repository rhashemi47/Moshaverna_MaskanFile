package com.npat.moshavernamaskanfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String StrFiles;
    private RecyclerViewAdapter adapter;
    private RecyclerView recycler_file;
    private  CardView cardView;

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
    }

    private List<Files> getFile() {
        ArrayList<Files> files =new ArrayList<>();
        String spFile[] = StrFiles.split("\\[##\\]");
        for(int i=0; i<spFile.length ; i++) {
            String spDetailFile[] = spFile[i].split("\\[#\\]");
            int CountSpDetailFile = spDetailFile.length;
            if (CountSpDetailFile > 0) {
                files.add(new Files(spDetailFile[CountSpDetailFile-17],
                        spDetailFile[CountSpDetailFile-16],
                        spDetailFile[CountSpDetailFile-15],
                        spDetailFile[CountSpDetailFile-14],
                        spDetailFile[CountSpDetailFile-13],
                        spDetailFile[CountSpDetailFile-12],
                        spDetailFile[CountSpDetailFile-11],
                        spDetailFile[CountSpDetailFile-10],
                        spDetailFile[CountSpDetailFile-9],
                        spDetailFile[CountSpDetailFile-8],
                        spDetailFile[CountSpDetailFile-7],
                        spDetailFile[CountSpDetailFile-6],
                        spDetailFile[CountSpDetailFile-5],
                        spDetailFile[CountSpDetailFile-4],
                        spDetailFile[CountSpDetailFile-3],
                        spDetailFile[CountSpDetailFile-2],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1],
                        spDetailFile[CountSpDetailFile-1]));
            }
        }
        return files;
    }
}
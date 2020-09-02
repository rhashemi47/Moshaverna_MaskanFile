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
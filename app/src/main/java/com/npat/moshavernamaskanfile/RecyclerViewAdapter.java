package com.npat.moshavernamaskanfile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    List<Files> filesList;
    Context  context;

    public RecyclerViewAdapter(List<Files> filesList, Context context) {
        this.filesList = filesList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(filesList.get(position).getId()));
        holder.txtContent.setText(filesList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtID,txtContent;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtID=(TextView) itemView.findViewById(R.id.txtID);
            txtContent=(TextView) itemView.findViewById(R.id.txtContent);
        }
    }
}

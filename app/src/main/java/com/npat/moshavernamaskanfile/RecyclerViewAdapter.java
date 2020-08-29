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
        holder.txtID.setText(String.valueOf(filesList.get(position).getCodeFile()));
        holder.txtDateFile.setText(filesList.get(position).getCalendarOrder());
        holder.txtTypeFile.setText(filesList.get(position).getBuilding_Type());
        holder.txtCurrncyTitle.setText(filesList.get(position).getCurrency_Title());
        holder.txtCurrncy.setText(filesList.get(position).getCurrency());
        holder.txtAddress.setText(filesList.get(position).getAddress());
        holder.txtDocument.setText(filesList.get(position).getDocument());
        holder.txtTime.setText(filesList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtID,txtDateFile,txtTypeFile,txtCurrncyTitle,txtCurrncy,txtAddress,txtDocument,txtTime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtID=(TextView) itemView.findViewById(R.id.txtID);
            txtDateFile=(TextView) itemView.findViewById(R.id.txtDateFile);
            txtTypeFile=(TextView) itemView.findViewById(R.id.txtTypeFile);
            txtCurrncyTitle=(TextView) itemView.findViewById(R.id.txtCurrncyTitle);
            txtCurrncy=(TextView) itemView.findViewById(R.id.txtCurrncy);
            txtAddress=(TextView) itemView.findViewById(R.id.txtAddress);
            txtDocument=(TextView) itemView.findViewById(R.id.txtDocument);
            txtTime=(TextView) itemView.findViewById(R.id.txtTime);
        }
    }
}

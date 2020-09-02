package com.npat.moshavernamaskanfile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        holder.txtCode.setText(String.valueOf(filesList.get(position).getCodeFile()));
        holder.txtCreated.setText(filesList.get(position).getCreated());
        holder.txtType.setText(filesList.get(position).getType());
        holder.txtSellTitle.setText("فروش");
        holder.txtCurrncySell.setText(filesList.get(position).getPriceSell());
        holder.txtMortgageTitle.setText("رهن");
        holder.txtCurrncyMortgage.setText(filesList.get(position).getPriceMortgage());
        holder.txtRentTitle.setText("اجاره");
        holder.txtCurrncyMortgage.setText(filesList.get(position).getPriceRent());
        holder.txtAddress.setText(filesList.get(position).getAddress());
        holder.txtDocument.setText(filesList.get(position).getDocType());
        holder.txtTime.setText(filesList.get(position).getYear());
        holder.txtAmlakFileStatus.setText(filesList.get(position).getAmlakFileStatus());
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtCode,txtCreated,txtType,txtSellTitle,txtCurrncySell,txtMortgageTitle,txtCurrncyMortgage,txtRentTitle,txtCurrncyRent,txtAddress,txtDocument,txtTime,txtAmlakFileStatus;
        LinearLayout LinearSell,LinearMortgage,LinearRent;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode=(TextView) itemView.findViewById(R.id.txtCode);
            txtCreated=(TextView) itemView.findViewById(R.id.txtCreated);
            txtType=(TextView) itemView.findViewById(R.id.txtType);
            txtSellTitle=(TextView) itemView.findViewById(R.id.txtSellTitle);
            txtCurrncySell=(TextView) itemView.findViewById(R.id.txtCurrncySell);
            txtAddress=(TextView) itemView.findViewById(R.id.txtAddress);
            txtDocument=(TextView) itemView.findViewById(R.id.txtDocument);
            txtTime=(TextView) itemView.findViewById(R.id.txtTime);
            txtMortgageTitle=(TextView) itemView.findViewById(R.id.txtMortgageTitle);
            txtCurrncyMortgage=(TextView) itemView.findViewById(R.id.txtCurrncyMortgage);
            txtRentTitle=(TextView) itemView.findViewById(R.id.txtRentTitle);
            txtCurrncyRent=(TextView) itemView.findViewById(R.id.txtCurrncyRent);
            txtAmlakFileStatus=(TextView) itemView.findViewById(R.id.txtAmlakFileStatus);
            LinearSell=(LinearLayout) itemView.findViewById(R.id.LinearSell);
            LinearMortgage=(LinearLayout) itemView.findViewById(R.id.LinearMortgage);
            LinearRent=(LinearLayout) itemView.findViewById(R.id.LinearRent);
        }
    }
}

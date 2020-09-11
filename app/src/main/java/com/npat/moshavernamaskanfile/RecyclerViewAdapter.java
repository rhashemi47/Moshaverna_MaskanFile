package com.npat.moshavernamaskanfile;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> implements InterfaceDataModelFiles {
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
        final Files file_object = filesList.get(position);
        holder.txtCode.setText(String.valueOf(file_object.getCodeFile()));
        holder.txtCreated.setText(file_object.getCreated());
        holder.txtType.setText(file_object.getType());
        if (file_object.getType().compareTo("رهن و اجاره") == 0) {
            holder.LinearMortgage.setVisibility(View.VISIBLE);
            holder.LinearRent.setVisibility(View.VISIBLE);
            holder.LinearSell.setVisibility(View.GONE);
            holder.txtMortgageTitle.setText("رهن");
            holder.txtRentTitle.setText("اجاره");
            holder.txtCurrncyMortgage.setText(file_object.getPriceMortgage());
            holder.txtCurrncyMortgage.setText(file_object.getPriceRent());
        }
        else {
            holder.LinearMortgage.setVisibility(View.GONE);
            holder.LinearRent.setVisibility(View.GONE);
            holder.LinearSell.setVisibility(View.VISIBLE);
            holder.txtSellTitle.setText("فروش");
            holder.txtCurrncySell.setText(file_object.getPriceSell());
        }
        holder.txtAddress.setText(file_object.getAddress());
        holder.txtDocument.setText(file_object.getDocType());
        holder.txtTime.setText(file_object.getYear()+ "سال ساخت");
        holder.txtAmlakFileStatus.setText(file_object.getAmlakFileStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ShowFile.class);
                intent.putExtra(CodeFile_InterFace,String.valueOf(file_object.getCodeFile()));
                intent.putExtra(Created_InterFace,file_object.getCreated());
                intent.putExtra(Type_InterFace,file_object.getType());
                intent.putExtra(PriceSell_InterFace,file_object.getPriceSell());
                intent.putExtra(PriceMortgage_InterFace,file_object.getPriceMortgage());
                intent.putExtra(PriceRent_InterFace,file_object.getPriceRent());
                intent.putExtra(Address_InterFace,file_object.getAddress());
                intent.putExtra(DocType_InterFace,file_object.getDocType());
                intent.putExtra(Year_InterFace,file_object.getYear());
                intent.putExtra(AmlakFileStatus_InterFace,file_object.getAmlakFileStatus());
                context.startActivity(intent);
            }
        });
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

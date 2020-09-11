package com.npat.moshavernamaskanfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowFile extends AppCompatActivity  implements  InterfaceDataModelFiles{
    TextView txtCode,txtCreated,txtType,txtSellTitle,txtCurrncySell,txtMortgageTitle,txtCurrncyMortgage,txtRentTitle,txtCurrncyRent,txtAddress,txtDocument,txtTime,txtAmlakFileStatus;
    LinearLayout LinearSell,LinearMortgage,LinearRent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_file);
        cast();
        ViewContent();
    }
    private void cast() {
        txtCode=(TextView) findViewById(R.id.txtCode);
        txtCreated=(TextView) findViewById(R.id.txtCreated);
        txtType=(TextView) findViewById(R.id.txtType);
        txtSellTitle=(TextView) findViewById(R.id.txtSellTitle);
        txtCurrncySell=(TextView) findViewById(R.id.txtCurrncySell);
        txtAddress=(TextView) findViewById(R.id.txtAddress);
        txtDocument=(TextView) findViewById(R.id.txtDocument);
        txtTime=(TextView) findViewById(R.id.txtTime);
        txtMortgageTitle=(TextView) findViewById(R.id.txtMortgageTitle);
        txtCurrncyMortgage=(TextView) findViewById(R.id.txtCurrncyMortgage);
        txtRentTitle=(TextView) findViewById(R.id.txtRentTitle);
        txtCurrncyRent=(TextView) findViewById(R.id.txtCurrncyRent);
        txtAmlakFileStatus=(TextView) findViewById(R.id.txtAmlakFileStatus);
        LinearSell=(LinearLayout) findViewById(R.id.LinearSell);
        LinearMortgage=(LinearLayout) findViewById(R.id.LinearMortgage);
        LinearRent=(LinearLayout) findViewById(R.id.LinearRent);
    }
    private void ViewContent() {
        Intent intent=getIntent();
         txtCode.setText(intent.getStringExtra(CodeFile_InterFace));
         txtCreated.setText( intent.getStringExtra(Created_InterFace));
         txtType.setText( intent.getStringExtra(Type_InterFace));
         txtAddress.setText( intent.getStringExtra(Address_InterFace));
         txtDocument.setText( intent.getStringExtra(DocType_InterFace));
         txtTime.setText(intent.getStringExtra(Year_InterFace) + "سال ساخت");
         txtAmlakFileStatus.setText( intent.getStringExtra(AmlakFileStatus_InterFace));
         String strTyp =  intent.getStringExtra(Type_InterFace);
        if (strTyp.compareTo("رهن و اجاره") == 0) {
            LinearMortgage.setVisibility(View.VISIBLE);
            LinearRent.setVisibility(View.VISIBLE);
            LinearSell.setVisibility(View.GONE);
            txtMortgageTitle.setText("رهن");
            txtRentTitle.setText("اجاره");
            txtCurrncyMortgage.setText( intent.getStringExtra(PriceMortgage_InterFace));
            txtCurrncyMortgage.setText( intent.getStringExtra(PriceRent_InterFace));
        }
        else {
            LinearMortgage.setVisibility(View.GONE);
            LinearRent.setVisibility(View.GONE);
            LinearSell.setVisibility(View.VISIBLE);
            txtSellTitle.setText("فروش");
            txtCurrncySell.setText( intent.getStringExtra(PriceSell_InterFace));
        }
    }
}

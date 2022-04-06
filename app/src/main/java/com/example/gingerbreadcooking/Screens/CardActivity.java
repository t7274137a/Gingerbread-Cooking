package com.example.gingerbreadcooking.Screens;

import static com.example.gingerbreadcooking.Utils.Constant.setPremiumStatus;
import static com.example.gingerbreadcooking.Utils.Constant.validateCardExpireDate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gingerbreadcooking.R;

public class CardActivity extends AppCompatActivity {
    EditText card_number,card_exp_date,card_cvv,first_name,last_name,zipcode,search_city_text;
    TextView btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initView();
        first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validate();
            }
        });
        last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validate();
            }
        });
        card_cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validate();
            }
        });
        card_exp_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(card_exp_date.getText().toString().length()==5||card_exp_date.getText().toString().length()==4){
                    validate();
                }
            }
        });
        // set the format of card number
        card_number.addTextChangedListener(new com.example.creditcard.Utils.Format(card_number, "####-####-####-####"));
        card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String card = card_number.getText().toString().replaceAll("-", "");
                if(card.length()>=12){
                    validate();
                }
            }
        });
        zipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(zipcode.getText().toString().length()==3||zipcode.getText().toString().length()==4){
                    validate();
                }
            }
        });
        // set the format of expiration date
        card_exp_date.addTextChangedListener(new com.example.creditcard.Utils.Format(card_exp_date, "##/##"));

        search_city_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validate();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show the alert for payment successful
                 showAlert(CardActivity.this);

            }
        });

    }
    public  void showAlert(Context context){
         AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage("Payment was successful")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setPremiumStatus(CardActivity.this,"yes");
                        finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Alert");
        alert.show();
    }

    public void initView(){
        card_number=findViewById(R.id.card_number);
        card_exp_date=findViewById(R.id.card_exp_date);
        card_cvv=findViewById(R.id.card_cvv);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        zipcode=findViewById(R.id.zipcode);
        btn_submit=findViewById(R.id.btn_submit);
        search_city_text=findViewById(R.id.search_city_text);
        btn_submit.setEnabled(false);
    }
    // function that will validate the from data according to requirement
    public void validate(){
        if(first_name.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return ;
        }
        if(last_name.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(zipcode.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(search_city_text.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(card_cvv.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(card_exp_date.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(card_number.getText().toString().trim().isEmpty()){
            setBtnDisable();
            return;
        }
        if(card_exp_date.getText().toString().trim().length()!=5){
            setBtnDisable();
            return;
        }
        if(zipcode.getText().toString().trim().length()==3){
            setBtnDisable();
            return;
        }
        // validate the expiration date
        if(!validateCardExpireDate( CardActivity.this ,card_exp_date.getText().toString().trim())){
            setBtnDisable();
            return;
        }
        if(!card_number.getText().toString().trim().isEmpty()){
            String card = card_number.getText().toString().trim().replaceAll("-", "");
            // validate the card of visa card requirement
            if(card.length()==16){
                setBtnAble();
                return;
            }
            setBtnDisable();
            return;
        }
        setBtnAble();
    }
    // change the btn design and set it non clickable
    public void setBtnDisable(){
        btn_submit.setEnabled(false);
        btn_submit.setTextColor(getResources().getColor(R.color.black));
        btn_submit.setBackground(getResources().getDrawable(R.drawable.btn_bg));
    }
    // change the btn design and set it to clickable
    public void setBtnAble(){
        btn_submit.setEnabled(true);
        btn_submit.setTextColor(getResources().getColor(R.color.white));
        btn_submit.setBackground(getResources().getDrawable(R.drawable.btn_bg1));
    }
}
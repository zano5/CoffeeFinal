package com.example.projava.coffeefinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount, tvThank, tvAmnt,tvResult;
    private Button btnadd, btnSub,btnOrder;
    private TextView views;
    private int quantity=0;
    private static final int price = 5;
    private EditText etName;
    private CheckBox ckCream, ckChocolate;
    private TextView tvCream, tvChocolate;
    private int amnt;
    private boolean validChocolate = false;
    private boolean validCream = false;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = (TextView) findViewById(R.id.tvCount);
        tvThank = (TextView) findViewById(R.id.tvThank);
        btnadd  = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSubtract);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        tvAmnt = (TextView) findViewById(R.id.tvAmnt);
        tvResult = (TextView) findViewById(R.id.tvResult);
        views =  (TextView) findViewById(R.id.views);
        ckChocolate = (CheckBox) findViewById(R.id.ckChocolate);
        ckCream = (CheckBox) findViewById(R.id.ckCream);
        etName = (EditText) findViewById(R.id.etName);
        tvCream = (TextView) findViewById(R.id.tvCream);
        tvChocolate = (TextView) findViewById(R.id.tvChocolate);


    }

    public void add()
    {
        quantity+=1;
        tvCount.setText("" +quantity);
    }

    public void sub()
    {
        quantity-=1;
        tvCount.setText("" +quantity);

    }



    public void onOrder(View view)
    {

        int id = view.getId();

        switch (id)
        {

            case R.id.btnAdd:
                add();
                break;
            case R.id.btnSubtract:
                sub();
                break;
        }
    }


    public void onCheck()
    {

        if(ckCream.isChecked() && ckChocolate.isChecked())
        {


            amnt = quantity * price + 2;
                validCream = true;
            validChocolate = true;
        }else if(ckChocolate.isChecked())
        {

            amnt = quantity * price + 1;
            validChocolate = true;
        }else if(ckCream.isChecked())
        {


            amnt = quantity * price + 1;
            validCream = true;
        }else if(!ckCream.isChecked() && !ckChocolate.isChecked())
        {
            validChocolate = false;
            validCream = false;
        }else if(!ckCream.isChecked())
        {

            validCream = false;
        }else if(!ckChocolate.isChecked())
        {
            validChocolate = false;
        }

    }



    public void order(View view)
    {


        if(quantity < 0)
        {
            Toast.makeText(MainActivity.this,"The quanity can not be less then 0", Toast.LENGTH_LONG).show();

        }else {

            onCheck();
            tvAmnt.setText(NumberFormat.getCurrencyInstance().format(amnt));
            tvThank.setVisibility(View.VISIBLE);
            tvCream.setVisibility(View.INVISIBLE);
            tvChocolate.setVisibility(View.INVISIBLE);

            if (!etName.getText().toString().equals("")) {
                tvThank.setText(etName.getText().toString());

                   name= etName.getText().toString();
                if (validCream == true && validChocolate == true) {
                    tvCream.setVisibility(View.VISIBLE);
                    tvChocolate.setVisibility(View.VISIBLE);

                } else if (validCream == true) {
                    tvCream.setVisibility(View.VISIBLE);
                } else if (validChocolate == true) {

                    tvChocolate.setVisibility(View.VISIBLE);
                } else if(validChocolate == false && validCream ==false)
                {
                    tvCream.setVisibility(View.GONE);
                    tvChocolate.setVisibility(View.GONE);

                }else if(validCream == false)
                {
                    tvCream.setVisibility(View.GONE);
                }else if(validChocolate == false)
                {
                    tvChocolate.setVisibility(View.GONE);
                }


                String message = "Name:" + name + "\n" + "Add whipped Cream? " + validCream + "\n" + "Added chocolate?" +validChocolate+
                        "\n"+ "Quantity:" + quantity + "\n" + "Total:"+ NumberFormat.getCurrencyInstance().format(amnt) + "\n" +"Thank You!";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java for " +name );
                intent.putExtra(Intent.EXTRA_TEXT, message);
                if(intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                }

            }

        }




    }
}

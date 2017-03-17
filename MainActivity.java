package com.exampl.andriod.justjava;
import java.util.*;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.checked;
import static android.R.string.no;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int noOfCoffee=0;
    int price=0;
    String mess=null;
    String mess1=null;
    boolean checked1;
    boolean checked2;
    String newmessage;
    String newmessage1;
    String emial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        price=calculatePrice();
        EditText editText = (EditText) findViewById(R.id.album_description_view);
         newmessage = editText.getText().toString();

         newmessage1 = newmessage+"\n Quantity"+noOfCoffee+"\n Wiped topping : "+mess+"\n Chocolate : "+mess1+"\n Total :"+price+"\n Thankyou";
        emial="harshaldesale45@gmail.com";
        composeEmail();

    }

    public void submitOrder1(View view) {
        int noOfCoffee = 0;
        display(noOfCoffee);
        displayPrice(noOfCoffee);

    }

    public int increment(View view) {
        while (noOfCoffee < 10) {
            noOfCoffee = noOfCoffee + 1;
            display(noOfCoffee);
            return noOfCoffee;
        }
    return 0;}

    public int decrement(View view) {
      while(noOfCoffee >0) {
          noOfCoffee = noOfCoffee - 1;
          display(noOfCoffee);
          return noOfCoffee;
      }
   return 0; }

    public int calculatePrice()
    {
        if(checked1 && checked2)
        return (noOfCoffee * (5+2+1));
       else if(checked1)
        return ( noOfCoffee * (5+1));
        else if(checked2)
        return( noOfCoffee *(5+2));

        else
        return 0;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    public String onCheckboxClicked(View view) {
        // Is the view now checked?
         checked1 = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

                if (checked1)
                { // Put some meat on the sandwich
                 mess= "added";
                return(mess);
                }
                else {
                    mess = "not added";
                    return(mess);
                }
        }
    public String onCheckboxClicked1(View view) {
        // Is the view now checked?
         checked2 = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked2)
        { // Put some meat on the sandwich
            mess1= "added";
            return(mess1);
        }
        else {
            mess1 = "not added";
            return(mess1);
        }
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emial);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for :"+newmessage);
        intent.putExtra(Intent.EXTRA_TEXT, newmessage1);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0; //initialize quantity variable to 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
////
////    /**
////     * This method displays the given price on the screen.
////     */
  private void displayPrice(int number) {
    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
       priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
   }


    private String createOrderSummary( String name, int priceOrder, boolean hasWhippedCream, boolean hasChocolate) {
        String orderMsg = "Name: " + name;
        orderMsg = orderMsg + "\nAdd whipped cream? " + hasWhippedCream;
        orderMsg += "\nAdd chocolate? " + hasChocolate;
        orderMsg = orderMsg + "\nQuantity: " + quantity;
        orderMsg = orderMsg +"\nPrice: $" + priceOrder;
        orderMsg = orderMsg + "\nThank You";
        return orderMsg;
    }
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_Cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream =whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(value, price, hasWhippedCream, hasChocolate);

//        displayPrice(price);
//

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java for" + value);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
//        int whippedCream, chocolate = 0;
        //Price of 1 cupOfCoffee
      int basePrice = 5;

      //add $1 if suer wants whipped cream
      if(addWhippedCream) {
          basePrice+= 1;
      }
      //add $2 if user wantfs chocolate
      if(addChocolate) {
          basePrice += 2;
      }
        //calculate total order price bt multiplying quantity
        int price = quantity * basePrice;

        return price;
    }

    /**
     * Calculates the price of the order based on the quantity ordered.
     *
     * @param quantity is the number of cups of coffee ordered
     * @param pricePerCup is the price for each cup
     */

    private void calculatePrice(int quantity,int pricePerCup) {
        int price = pricePerCup * quantity;
    }

    /**
     * Calculates the price of the order.
     *
     */

//    private void calculatePrice(){
//        int price = 5;
//    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if(quantity == 100) {
            //exit the method early
            return;
        }
            quantity++;
            displayQuantity(quantity);

    }


    public void decrement(View view) {
        if(quantity == 0) {
            //exit the method early
            return;
        }
            quantity--;
            displayQuantity(quantity);
    }


//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}
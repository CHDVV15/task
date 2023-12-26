package dvv.example.samstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import dvv.example.samstore.R;

public class pay extends AppCompatActivity {

    private EditText editCardNumber,editCVV;
    ProgressBar progressBar;
    Button btnMakePayment;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        editCardNumber = findViewById(R.id.editCardNumber);
        editCVV = findViewById(R.id.editCVV);
        progressBar = findViewById(R.id.progressBar);
        btnMakePayment = findViewById(R.id.btnMakePayment);

        handler = new Handler();

        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdetails();
            }
        });
    }

    private void showToast(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(pay.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkdetails() {
        if (editCardNumber.getText().toString().length() != 16) {
            showToast("Enter Valid Card Number");
        } else if (editCVV.getText().toString().length() != 3) {
            showToast("Enter Valid CVV Number");
        } else {
            startPaymentProcess();
        }
    }

    private void startPaymentProcess() {
        progressBar.setVisibility(View.VISIBLE);
        handler.post(new Runnable() {
            @Override
            public void run() {
                d1();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        d2();
                        progressBar.setVisibility(View.GONE);
                        Intent i=new Intent(pay.this,thank.class);
                        startActivity(i);

                    }
                }, 3000);
                progressBar.incrementProgressBy(50);
            }
        });
    }



    private void d1() {
        showToast("Initiated Transaction");
    }

    private void d2() {
        showToast("Transaction Successful");
    }
}

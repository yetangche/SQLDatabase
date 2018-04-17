package com.hermeticstatic.rquesenberry.sqldatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertCustomer extends AppCompatActivity {
    private Button addCustButton;
    private String name;
    private Integer phone;
    private String email;
    private Integer cardnum;
    private Integer cardexp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_customer);
        final SQLiteDatabase AppointmentDatabase = getBaseContext().openOrCreateDatabase("appointment.db", MODE_PRIVATE, null);

        addCustButton = (Button) findViewById(R.id.button_addCustomer);
        addCustButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editName = findViewById(R.id.editText_name);
                        EditText editPhone = findViewById(R.id.editText_phone);
                        EditText editEmail = findViewById(R.id.editText_email);
                        EditText editCardnum = findViewById(R.id.editText_cardnum);
                        EditText editCardexp = findViewById(R.id.editText_cardexp);

                        name = editName.getText().toString();
                        phone = Integer.parseInt(editPhone.getText().toString());
                        email = editEmail.getText().toString();
                        cardnum = Integer.parseInt(editCardnum.getText().toString());
                        cardexp = Integer.parseInt(editCardexp.getText().toString());

                        AppointmentDatabase.execSQL("INSERT INTO customers VALUES(name,phone,email,cardnum,cardexp,0)");
                    }
                }
        );
    }
}

package com.hermeticstatic.rquesenberry.sqldatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase AppointmentDatabase = getBaseContext().openOrCreateDatabase("appointment.db", MODE_PRIVATE, null);
        Cursor query = AppointmentDatabase.rawQuery("SELECT * from contacts", null);
        if(query.moveToFirst()) {
            String name = query.getString(0);
            Integer phone = query.getInt(1);
            String email = query.getString(2);
            Integer cardnum = query.getInt(3);
            Integer cardexp = query.getInt(4);
            Integer sessions = query.getInt(5);
            customerList.add(new Customer(name, phone, email, cardnum, cardexp, sessions));
        } else {
            AppointmentDatabase.execSQL("CREATE TABLE customers(name TEXT, phone INTEGER, email TEXT, cardnum INTEGER, cardexp INTEGER, sessions INTEGER);");
            AppointmentDatabase.execSQL("INSERT INTO customers VALUES('John Smith', 5551212, 'jsmith@fakemail/com', 1234567891011121, 0520, 0)");
            AppointmentDatabase.execSQL("INSERT INTO customers VALUES('Jane Nemo', 5552232, 'jnemo@fakemail/com', 1817161514131211, 0819, 0)");
        }
        AppointmentDatabase.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(customerList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CustomerHolder extends RecyclerView.ViewHolder {
        public CustomerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_customer, parent, false));
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<CustomerHolder>{
        private List<Customer> mCustomerList;

        public MyAdapter(List<Customer> customerList) {
            mCustomerList = customerList;
        }

        @Override
        public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());

            return new CustomerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CustomerHolder holder, int position){}

        @Override
        public int getItemCount() {
            return mCustomerList.size();
        }

    }

    private static class Customer{
        String name;
        Integer phone;
        String email;
        Integer cardnum;
        Integer cardexp;
        Integer sessions;

        public Customer(String name, Integer phone, String email, Integer cardnum, Integer cardexp, Integer sessions) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.cardnum = cardnum;
            this.cardexp = cardexp;
            this.sessions = sessions;
        }
    }
}


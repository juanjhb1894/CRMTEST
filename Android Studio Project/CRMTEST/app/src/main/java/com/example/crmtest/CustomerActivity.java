package com.example.crmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.crmtest.adapters.AddressAdapter;
import com.example.crmtest.adapters.CustomerAdapter;
import com.example.crmtest.webservice.Address;
import com.example.crmtest.webservice.Customers;

import org.json.JSONException;

import java.io.IOException;

public class CustomerActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone;
    ListView lstAddresses;
    private static AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Intent intent =getIntent();
        String id = intent.getStringExtra("id");
        // Remove action bar
        getSupportActionBar().hide();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        lstAddresses = (ListView)findViewById(R.id.lstAddresses);
        etName = (EditText)findViewById(R.id.etAddressName);
        etEmail = (EditText)findViewById(R.id.etAddressEmail);
        etPhone = (EditText)findViewById(R.id.etAddressPhone);

        etName.setText( intent.getStringExtra("name"));
        etEmail.setText( intent.getStringExtra("email"));
        etPhone.setText( intent.getStringExtra("phone"));

        try
        {
            adapter= new AddressAdapter(new Address().getAllAdresses(id),getApplicationContext());
            lstAddresses.setAdapter(adapter);

            lstAddresses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
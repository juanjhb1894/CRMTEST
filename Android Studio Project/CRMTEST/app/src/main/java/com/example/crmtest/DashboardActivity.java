package com.example.crmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.crmtest.adapters.CustomerAdapter;
import com.example.crmtest.webservice.BaseParameters;
import com.example.crmtest.webservice.Customers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DashboardActivity extends AppCompatActivity {

    ListView lstCustomer;
    EditText etFilter;
    FloatingActionButton fabSearch, fabAdd;
    private static CustomerAdapter adapter;

    View inflatedLayout;
    FrameLayout container;
    ImageView imgCloseProfile;
    EditText etProfilePhone, etProfileEmail, etProfileName;
    Button btnUpdateProfile;
    boolean profileShown;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Remove action bar
        getSupportActionBar().hide();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        lstCustomer = (ListView) findViewById(R.id.lstCustomer);
        etFilter = (EditText) findViewById(R.id.etFilter);

        container = (FrameLayout) findViewById(R.id.flProfileContainer);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!profileShown)
                {
                    profileShown = true;
                    inflatedLayout = getLayoutInflater().inflate(R.layout.user_profile, null, false);
                    Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    inflatedLayout.setAnimation(slide_up);
                    container.addView(inflatedLayout);

                    imgCloseProfile = (ImageView) inflatedLayout.findViewById(R.id.imgCloseProfile);
                    etProfileName = (EditText) inflatedLayout.findViewById(R.id.etProfileName);
                    etProfilePhone = (EditText) inflatedLayout.findViewById(R.id.etProfilePhone);
                    etProfileEmail= (EditText) inflatedLayout.findViewById(R.id.etProfileEmail);
                    btnUpdateProfile = (Button) inflatedLayout.findViewById(R.id.btnUpdateProfile);

                    imgCloseProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            container.removeAllViews();
                            profileShown = false;
                        }
                    });

                    btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            post();
                        }
                    });
                }
                else
                {

                }
            }
        });

        try
        {
            adapter= new CustomerAdapter(new Customers().getAllCustomers(),getApplicationContext());
            lstCustomer.setAdapter(adapter);

            lstCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView ID = view.findViewById(R.id.costumerID);
                    TextView Name = view.findViewById(R.id.costumerName);
                    TextView Email = view.findViewById(R.id.costumerEmail);
                    TextView Phone = view.findViewById(R.id.costumerPhone);

                    Intent mIntent = new Intent(DashboardActivity.this, CustomerActivity.class);
                    mIntent.putExtra("id", ID.getText().toString());
                    mIntent.putExtra("name", Name.getText().toString());
                    mIntent.putExtra("email", Email.getText().toString());
                    mIntent.putExtra("phone", Phone.getText().toString());
                    startActivity(mIntent);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFilter.getText().toString().length()>=3)
                {
                    adapter.clear();
                    lstCustomer.setAdapter(adapter);
                    try
                    {
                        adapter= new CustomerAdapter(new Customers().getFilteredCustomers(etFilter.getText().toString()),getApplicationContext());
                        lstCustomer.setAdapter(adapter);

                        lstCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                TextView ID = view.findViewById(R.id.costumerID);
                                Intent mIntent = new Intent(DashboardActivity.this, CustomerActivity.class);
                                mIntent.putExtra("id", ID.getText().toString());
                                startActivity(mIntent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(etFilter.getText().toString().length()==0)
                {
                    adapter.clear();
                    lstCustomer.setAdapter(adapter);
                    try
                    {
                        adapter= new CustomerAdapter(new Customers().getAllCustomers(),getApplicationContext());
                        lstCustomer.setAdapter(adapter);

                        lstCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                TextView ID = view.findViewById(R.id.costumerID);
                                Intent mIntent = new Intent(DashboardActivity.this, CustomerActivity.class);
                                mIntent.putExtra("id", ID.getText().toString());
                                startActivity(mIntent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.at_least_three_characters), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void post()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", etProfileName.getText().toString());
            postData.put("email", etProfileEmail.getText().toString());
            postData.put("phone", etProfilePhone.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                new BaseParameters().baseURL + "customers/add", postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Response: "+response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


}
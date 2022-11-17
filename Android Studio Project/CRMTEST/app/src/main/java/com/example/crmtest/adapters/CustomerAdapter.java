package com.example.crmtest.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crmtest.R;
import com.example.crmtest.datamodel.CustomerData;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<CustomerData> implements View.OnClickListener, Filterable {

    private ArrayList<CustomerData> dataSet;
    Context mContext;

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CustomerData dataModel=(CustomerData) object;

        switch (v.getId())
        {

        }
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtEmail;
        TextView txtPhone;
        TextView txtID;
    }

    public CustomerAdapter(ArrayList<CustomerData> data, Context context) {
        super(context, R.layout.item_costumer, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomerData dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_costumer, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.costumerName);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.costumerEmail);
            viewHolder.txtPhone = (TextView) convertView.findViewById(R.id.costumerPhone);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.costumerID);
            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtEmail.setText(dataModel.getEmail());
        viewHolder.txtPhone.setText(dataModel.getPhone());
        viewHolder.txtID.setText(String.valueOf(dataModel.getId()));
        // Return the completed view to render on screen
        return convertView;
    }


}

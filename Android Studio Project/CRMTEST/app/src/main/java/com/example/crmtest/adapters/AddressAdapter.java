package com.example.crmtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.crmtest.R;
import com.example.crmtest.datamodel.AddressData;
import com.example.crmtest.datamodel.CustomerData;

import java.util.ArrayList;

public class AddressAdapter extends ArrayAdapter<AddressData> implements View.OnClickListener, Filterable {

    private ArrayList<AddressData> dataSet;
    Context mContext;

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        AddressData dataModel=(AddressData) object;

        switch (v.getId())
        {

        }
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtAddress;
        TextView txtCity;
        TextView txtCountry;
        TextView txtMain;
    }

    public AddressAdapter(ArrayList<AddressData> data, Context context) {
        super(context, R.layout.item_costumer, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AddressData dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_address, parent, false);

            viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.addressLocation);
            viewHolder.txtCity = (TextView) convertView.findViewById(R.id.addressCity);
            viewHolder.txtCountry = (TextView) convertView.findViewById(R.id.addressCounty);
            viewHolder.txtMain = (TextView) convertView.findViewById(R.id.addressMain);
            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtAddress.setText(dataModel.getAddress());
        viewHolder.txtCity.setText(dataModel.getCity());
        viewHolder.txtCountry.setText(dataModel.getCountry());
        if(dataModel.getMain() == 1)
        {
            viewHolder.txtMain.setText(mContext.getString(R.string.main_address));
        }
        else
        {
            viewHolder.txtMain.setText(mContext.getString(R.string.secundary_address));
        }
        // Return the completed view to render on screen
        return convertView;
    }


}

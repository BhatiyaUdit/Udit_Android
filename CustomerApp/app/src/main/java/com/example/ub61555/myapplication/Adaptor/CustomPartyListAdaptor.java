package com.example.ub61555.myapplication.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.Controller.PartyController;
import com.example.ub61555.myapplication.MainActivity;
import com.example.ub61555.myapplication.R;

import java.util.List;

public class CustomPartyListAdaptor extends ArrayAdapter<PartyBean> implements Filterable {

    Context mContext;
    int mResource;
    PartyController partyController;

    public CustomPartyListAdaptor(@NonNull Context context, int resource, @NonNull List<PartyBean> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        partyController = new PartyController();
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getPartyName();
        PartyBean partyBean = new PartyBean(name);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView partyNameView = convertView.findViewById(R.id.PartyDetailListItem);
        ImageView partyImage = convertView.findViewById(R.id.PartyImage);
        partyNameView.setText(name);
        if (position % 2 == 0) {
            partyImage.setImageResource(R.drawable.addcustomer);
        } else {
            partyImage.setImageResource(R.drawable.udit);
        }
        ImageView deletePartyImg = convertView.findViewById(R.id.deleteParty);
        deletePartyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Party Passed" + getItem(position).getPartyName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                partyController.deleteParty(getItem(position).getPartyId());
                MainActivity activity = (MainActivity) mContext;
                activity.displayAllParties();
            }
        });
        return convertView;
    }


}

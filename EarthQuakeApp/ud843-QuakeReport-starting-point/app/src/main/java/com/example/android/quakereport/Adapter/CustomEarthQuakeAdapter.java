package com.example.android.quakereport.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Bean.EarthQuakeBean;
import com.example.android.quakereport.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CustomEarthQuakeAdapter extends ArrayAdapter<EarthQuakeBean> {

    Context mContext;
    int mResource;
    String primaryLocation;
    String nearLocation;
    public final String LOCATION_SEPARATOR = "of";
    public final String NEAR_LOCATION_TXT= "Near ";

    public CustomEarthQuakeAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Double magnitude = getItem(position).getMagnitude();
        String location  = getItem(position).getLocation();
        String dateOfEQ = getItem(position).getDate();
        String timeOfEQ = getItem(position).getTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent, false);
        TextView magnitudeTV =  convertView.findViewById(R.id.magnitude);
        TextView locationTV = convertView.findViewById(R.id.location);
        TextView dateOfEQTV = convertView.findViewById(R.id.dateOfEQ);
        TextView timeOfEQTV = convertView.findViewById(R.id.TimeOfEQ);
        TextView nearLocationTV = convertView.findViewById(R.id.near_location);

        splitLocation(location);

        magnitudeTV.setText (round(magnitude,1).toString());
        locationTV.setText(primaryLocation);
        dateOfEQTV.setText(dateOfEQ);
        timeOfEQTV.setText(timeOfEQ);
        nearLocationTV.setText(nearLocation);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTV.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(magnitude));
        return convertView;
    }

    private void splitLocation(String location) {
        if(location.contains(LOCATION_SEPARATOR)){
            String[] parts = location.split(LOCATION_SEPARATOR);
            nearLocation = parts[0]+LOCATION_SEPARATOR;
            primaryLocation = parts[1].trim();
        }else {
            primaryLocation = location;
            nearLocation=NEAR_LOCATION_TXT;
        }
    }

    public   Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

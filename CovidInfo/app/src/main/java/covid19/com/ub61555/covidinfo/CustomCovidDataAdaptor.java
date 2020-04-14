package covid19.com.ub61555.covidinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import covid19.com.ub61555.covidinfo.DataSync.GetFlag.GetFlagAsyncTask;
import covid19.com.ub61555.covidinfo.DataSync.GetFlag.GetFlagInterface;

public class CustomCovidDataAdaptor extends ArrayAdapter<CovidDataBean>{
    Context mContext;
    Integer mResourceId;

    public CustomCovidDataAdaptor(@NonNull Context context, int resource, @NonNull List<CovidDataBean> objects) {
        super(context, resource, objects);
        mContext=context;
        mResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView= layoutInflater.inflate(mResourceId,parent,false);


        String country = getItem(position).getCountry();
        Long totalCases = getItem(position).getTotalCases();
        Long totalDeaths = getItem(position).getTotalDeaths();
        Long totalNewCases = getItem(position).getTotalNewCases();
        Long totalRecovered = getItem(position).getTotalRecovered();
        String flagUrl =  getItem(position).getFlagUrl();


        TextView countryTV = convertView.findViewById(R.id.country);
        TextView totalCasesTV =  convertView.findViewById(R.id.totalCases);
        TextView totalDeathsTV = convertView.findViewById(R.id.totalDeaths);
        TextView totalNewCasesTV = convertView.findViewById(R.id.totalNewCases);
        TextView totalRecoveredTV =  convertView.findViewById(R.id.totalRecovered);
        ImageView countryFlagIMGV = convertView.findViewById(R.id.flag_image);


        Picasso.with(mContext).load(flagUrl).error(R.drawable.in_flag).into(countryFlagIMGV);
        countryTV.setText(country);
        totalCasesTV.setText(totalCases.toString());
        totalDeathsTV.setText(totalDeaths.toString());
        totalNewCasesTV.setText(totalNewCases.toString());
        totalRecoveredTV.setText(totalRecovered.toString());

        return convertView;
    }
}

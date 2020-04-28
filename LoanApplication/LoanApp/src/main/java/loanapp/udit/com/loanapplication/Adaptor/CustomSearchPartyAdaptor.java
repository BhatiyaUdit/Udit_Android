package loanapp.udit.com.loanapplication.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import loanapp.udit.com.loanapplication.R;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;

public class CustomSearchPartyAdaptor extends ArrayAdapter<SearchPartyBean> {

    Context mContext;
    int mResource;

    public CustomSearchPartyAdaptor(@NonNull Context context, int resource, @NonNull List<SearchPartyBean> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String fullname  = getItem(position).getPartyFullName();
        String dob = getItem(position).getDateOfBirth();
        String kyc = getItem(position).getKyc();
        String dateOfBirth = null;
        if(dob!=null){
            String[] date =dob.split("T");
            dateOfBirth = formatDate(date[0]);
        }


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView partyNameView = convertView.findViewById(R.id.PartyName);
        TextView partyDOBView = convertView.findViewById(R.id.PartyDOB);
        TextView partyKYC = convertView.findViewById(R.id.PartyKYC);
        partyNameView.setText(fullname);
        partyDOBView.setText(dateOfBirth);
        partyKYC.setText(kyc);
        return convertView;
    }

    private String formatDate(String s) {
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String zz =dateFormat.format(d1);
            return zz;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

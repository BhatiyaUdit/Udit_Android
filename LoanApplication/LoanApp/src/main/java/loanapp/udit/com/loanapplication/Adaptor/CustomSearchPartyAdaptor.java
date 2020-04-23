package loanapp.udit.com.loanapplication.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView partyNameView = convertView.findViewById(R.id.PartyDetailListItem);
        partyNameView.setText(fullname);
        return convertView;
    }
}

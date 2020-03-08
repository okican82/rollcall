package okayyildirim.com.rollcall.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import okayyildirim.com.rollcall.EntityModels.RollCall;
import okayyildirim.com.rollcall.R;

public class RollCallAdapter extends ArrayAdapter<RollCall> {

    private Context _context;


    public RollCallAdapter(Context context, ArrayList<RollCall> weigthArrayList) {
        super(context, R.layout.roll_call_item, weigthArrayList);
        _context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RollCall item = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.roll_call_item,parent,false);



        TextView ListName = convertView.findViewById(R.id.ListName);
        TextView ListCount = convertView.findViewById(R.id.ListCount);
        TextView ListDate = convertView.findViewById(R.id.ListDate);


        ListName.setText(item.getTitle());
        ListCount.setText(String.valueOf(item.getAttempCount()));
        ListDate.setText(item.getRollDate());


        return convertView;
    }
}

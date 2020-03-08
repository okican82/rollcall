package okayyildirim.com.rollcall.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


import okayyildirim.com.rollcall.EntityModels.TeamMate;
import okayyildirim.com.rollcall.R;

public class TeamMateAdapter extends ArrayAdapter<TeamMate> {

    private Context _context;


    public TeamMateAdapter(Context context, ArrayList<TeamMate> weigthArrayList) {
        super(context, R.layout.name_list_item, weigthArrayList);
        _context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TeamMate item = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.name_list_item,parent,false);



        TextView ListName = convertView.findViewById(R.id.ListName);
        TextView ListCount = convertView.findViewById(R.id.ListCount);
        TextView ListDate = convertView.findViewById(R.id.ListDate);


        ListName.setText(item.getName());




        return convertView;
    }
}

package okayyildirim.com.rollcall.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import okayyildirim.com.rollcall.Adapters.RollCallAdapter;
import okayyildirim.com.rollcall.Util.Util;
import okayyildirim.com.rollcall.DB.DB;
import okayyildirim.com.rollcall.EntityModels.RollCall;
import okayyildirim.com.rollcall.Fragments.addFragment;
import okayyildirim.com.rollcall.R;

public class MainActivity extends AppCompatActivity {
    private RollCallAdapter rollCallAdapter;
    private ListView rollcall_list;
    ArrayList<RollCall> rollCallArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollCallArrayList = DB.getInstance(getApplicationContext()).getRollCallList();

        rollCallAdapter = new RollCallAdapter(getApplicationContext(),rollCallArrayList);
        rollcall_list = findViewById(R.id.rollcall_list);

        rollcall_list.setAdapter(rollCallAdapter);

        rollcall_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                openRollCallTeamMateList(position);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addNew:
                openNewRollCall();
                return true;
            case R.id.teamMateList:
                openTeamMateList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openNewRollCall()
    {
        addFragment cc = new addFragment(this);
        cc.setDialogResult(new addFragment.OnMyDialogResult() {
            public void finish(String result) {
                // yeni eklenecek. bu işlem sonrasında sayfa yenilenecek.

                RollCall item = new RollCall(0,Util.getInstance().getCurrentDateString(),0,result);

                DB.getInstance(getApplicationContext()).addNewRollCall(item);

                rollCallAdapter.notifyDataSetChanged();

            }
        });
        cc.show();

    }

    private void openTeamMateList()
    {
        startActivity(new Intent(this,RollCallList.class));

    }

    private void openRollCallTeamMateList(int position)
    {
        RollCall item = rollCallArrayList.get(position);

        Intent rollCallTeamMateIntent = new Intent(this,RollCallTeamMate.class);
        startActivity(rollCallTeamMateIntent);
    }
}

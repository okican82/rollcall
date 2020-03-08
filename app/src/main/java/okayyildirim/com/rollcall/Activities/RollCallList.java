package okayyildirim.com.rollcall.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import okayyildirim.com.rollcall.Adapters.TeamMateAdapter;
import okayyildirim.com.rollcall.DB.DB;
import okayyildirim.com.rollcall.EntityModels.RollCall;
import okayyildirim.com.rollcall.EntityModels.TeamMate;
import okayyildirim.com.rollcall.Fragments.addFragment;
import okayyildirim.com.rollcall.R;
import okayyildirim.com.rollcall.Util.Util;

public class RollCallList extends AppCompatActivity {

    private ListView teamMateList;
    private TeamMateAdapter teamMateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call_list);


        ArrayList<TeamMate> teamMateArrayList = DB.getInstance(getApplicationContext()).getTeamMateList();

        teamMateAdapter = new TeamMateAdapter(getApplicationContext(),teamMateArrayList);
        teamMateList = findViewById(R.id.teamMateList);

        teamMateList.setAdapter(teamMateAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tema_mate_list_menu_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addNewTeamMate:
                addNewTeamMate();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewTeamMate()
    {
        addFragment cc = new addFragment(this);
        cc.setDialogResult(new addFragment.OnMyDialogResult() {
            public void finish(String result) {
                // yeni eklenecek. bu işlem sonrasında sayfa yenilenecek.

                TeamMate item = new TeamMate(result,0);

                DB.getInstance(getApplicationContext()).addNewTeamMate(item);

            }
        });
        cc.show();


    }
}

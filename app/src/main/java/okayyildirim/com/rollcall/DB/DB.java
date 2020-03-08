package okayyildirim.com.rollcall.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import okayyildirim.com.rollcall.EntityModels.RollCall;
import okayyildirim.com.rollcall.EntityModels.TeamMate;

public class DB extends SQLiteOpenHelper {

    private static DB dbInstance = null;
    private final static String databaseName = "rollCallDB";
    private final static int databaseVersion = 1;
    private String rollCallList = "ROLLCALL_LIST";
    private String teamMateList = "TEMAMATE_LIST";
    private String rollCallTeamMateList = "ROLCALL_TEAMMATE_LIST";


    public synchronized static DB getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DB(context.getApplicationContext());
        }
        return dbInstance;
    }

    public void onCreate(SQLiteDatabase db)
    {
        String RollCallQuery = "CREATE TABLE " + rollCallList + " ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " ATTEMP_COUNT INT,"
                + " DATE_TEXT TEXT,"
                + " TITLE TEXT"
                + " )";

        db.execSQL(RollCallQuery);


        String TeamMateQuery = "CREATE TABLE " + teamMateList + " ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " NAME TEXT"
                + " )";

        db.execSQL(TeamMateQuery);

        String rollCallTeamMateQuery = "CREATE TABLE " + rollCallTeamMateList + " ("
                + " ROLLCALL_ID INTEGER,"
                + " TEAM_MATE_ID TEXT"
                + " )";

        db.execSQL(rollCallTeamMateQuery);

    }

    public void addNewRollCall(int rollCallId,int teamMateId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ROLLCALL_ID",rollCallId);
        values.put("TEAM_MATE_ID",teamMateId);

        db.insert(rollCallTeamMateList,null,values);
        db.close();
    }


    public void addNewRollCall(RollCall item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put("ATTEMP_COUNT",item.getAttempCount());
        values.put("DATE_TEXT",item.getRollDate());
        values.put("TITLE",String.valueOf(item.getTitle()));


        db.insert(rollCallList,null,values);
        db.close();
    }

    public void addNewTeamMate(TeamMate item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME",item.getName());

        db.insert(teamMateList,null,values);
        db.close();
    }

    public ArrayList<RollCall> getRollCallList()
    {
        ArrayList<RollCall> resultList = new ArrayList<RollCall>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";
        selectQuery = " SELECT * FROM " + rollCallList + " ORDER BY 1 DESC " ;

        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();

        if(cursor.moveToFirst())
        {
            do
            {
                resultList.add(new RollCall(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("DATE_TEXT")),
                        cursor.getInt(cursor.getColumnIndex("ATTEMP_COUNT")),
                        cursor.getString(cursor.getColumnIndex("TITLE"))
                ));
            }
            while (cursor.moveToNext());
        }

        db.close();

        return resultList;
    }

    public ArrayList<TeamMate> getTeamMateList()
    {
        ArrayList<TeamMate> resultList = new ArrayList<TeamMate>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";
        selectQuery = " SELECT * FROM " + teamMateList + " ORDER BY 1 DESC " ;

        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();

        if(cursor.moveToFirst())
        {
            do
            {
                resultList.add(new TeamMate(
                        cursor.getString(cursor.getColumnIndex("NAME")),
                        cursor.getInt(cursor.getColumnIndex("ID"))
                ));
            }
            while (cursor.moveToNext());
        }

        db.close();

        return resultList;
    }


    public DB(Context context)
    {
        super(context,databaseName,null,databaseVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {

    }

}

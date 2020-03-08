package okayyildirim.com.rollcall.Util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static Util utilInstance = null;

    public synchronized static Util getInstance()
    {
        if(utilInstance == null)
        {
            utilInstance = new Util();
        }
        return utilInstance;
    }

    public String getCurrentDateString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String dateString = sdf.format(new Date());


        return dateString;
    }

    public String convertTimeSecondsTo(long time)
    {
        long hour = Math.abs(time/3600);
        long minute = Math.abs ((time%3600)/60);
        long second = time%60;

        String result = "";

        if(hour>0)
        {
            result += String.valueOf(hour);
            result += ":";
            result += ((minute<10)? "0" + String.valueOf(minute) : String.valueOf(minute));
            result += ":";

        }
        else
        {
            if(minute>0)
            {
                result += String.valueOf(minute);
                result += ":";
            }
            else
            {
                result += "00:";
            }
        }


        result += ((second<10)? "0" + String.valueOf(second) : String.valueOf(second));

        return result;
    }

    public String convertIntArrayToString(int[] intArray)
    {
        String intString = "";

        for(int i = 0;i<intArray.length;i++)
        {
            intString += String.valueOf(intArray[i]) + ",";
        }

        return intString;

    }

    public int[] convertStringToIntArray(String s)
    {
        String[] items = s.split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++)
        {
            results[i] = Integer.parseInt(items[i]);
        }

        return results;

    }
}

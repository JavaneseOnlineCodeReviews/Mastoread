package house.winkleak.mastoreader.util;


import android.text.format.DateUtils;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelativeTimeConverter {

    public static String getRelativeTime(String createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date1 = sdf.parse(createdAt);
            long time = date1.getTime();
            Time now = new Time();
            now.setToNow();
            return DateUtils.getRelativeTimeSpanString(time,
                    now.toMillis(true),
                    DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return createdAt;
    }
}

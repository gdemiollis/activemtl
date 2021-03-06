package com.nurun.activemtl.data;

import android.database.Cursor;

public class EventCursorReader {

    public static String getEventId(Cursor cursor) {
        return CursorReader.getString(cursor, EventDbAdapter.KEY_ID);
    }
    
    public static String getName(Cursor cursor) {
        return CursorReader.getString(cursor, EventDbAdapter.KEY_NAME);
    }

    public static String getPictureUrl(Cursor cursor) {
        return CursorReader.getString(cursor, EventDbAdapter.KEY_PICTURE_URL);
    }

    public static Object getId(Cursor cursor) {
        return CursorReader.getId(cursor);
    }

    public static double getLatitude(Cursor cursor) {
        return CursorReader.getDouble(cursor, EventDbAdapter.KEY_LATITUDE);
    }
    
    public static double getLongitude(Cursor cursor) {
        return CursorReader.getDouble(cursor, EventDbAdapter.KEY_LONGITUDE);
    }

}

package com.nurun.activemtl.data;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import com.nurun.activemtl.model.parse.Event;

public class EventDbAdapter extends DbAdapter<Event> {

    public static final String KEY_ID = Event.class.getSimpleName() + "Id";
    public static final String KEY_NAME = Event.class.getSimpleName() + "Name";
    public static final String VIEW_NAME = Event.class.getSimpleName() + "View";
    public static final String TABLE_NAME = Event.class.getSimpleName() + "Table";
    public static final String KEY_LATITUDE = Event.class.getSimpleName() + "Latitude";
    public static final String KEY_LONGITUDE = Event.class.getSimpleName() + "Longitude";
    public static final String KEY_PICTURE_URL = Event.class.getSimpleName() + "PictureUrl";

    private static EventDbAdapter instance;

    private EventDbAdapter(Context context) {
        super(context);
    }

    public static EventDbAdapter getInstance(Context context) {
        if (instance == null) {
            instance = new EventDbAdapter(context);
        }
        return instance;
    }

    @Override
    protected ContentValues getValues(Event court) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, court.getEventId());
        initialValues.put(KEY_NAME, court.getTitle());
        initialValues.put(KEY_LATITUDE, court.getLatLng()[0]);
        initialValues.put(KEY_LONGITUDE, court.getLatLng()[1]);
        initialValues.put(KEY_PICTURE_URL, court.getPictureUrl());
        return initialValues;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    public static Map<String, String> getAttributs() {
        Map<String, String> attributs = new HashMap<String, String>();
        attributs.put(KEY_ID, "text not null");
        attributs.put(KEY_NAME, "text not null");
        attributs.put(KEY_LATITUDE, "int not null");
        attributs.put(KEY_LONGITUDE, "int not null");
        attributs.put(KEY_PICTURE_URL, "text");
        return attributs;
    }

    @Override
    protected String getExistsColumn() {
        return KEY_ID;
    }

    public Cursor listOrderByDistance() {
        Cursor cursor = new QueryBuilder(getTableView()).columns(BaseColumns._ID, EventDbAdapter.KEY_ID, EventDbAdapter.KEY_NAME,
                EventDbAdapter.KEY_PICTURE_URL, EventDbAdapter.KEY_LATITUDE, EventDbAdapter.KEY_LONGITUDE).request(mDb);
        return cursor;
    }

    private String getTableView() {
        return EventDbAdapter.TABLE_NAME;
    }

    public Cursor search(String search) {
        Cursor cursor = new QueryBuilder(getTableName()).wherelike(KEY_NAME, search).or().wherelike(KEY_PICTURE_URL, search).request(mDb);
        Log.i(getClass().getSimpleName(), cursor.getCount() + " billets pour la recherche " + search);
        return cursor;
    }

    @Override
    public long insert(Event element) {
        throw new RuntimeException("Do not use this method, use insertOrUpdate instead");
    }
}

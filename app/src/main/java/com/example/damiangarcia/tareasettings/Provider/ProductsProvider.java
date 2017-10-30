package com.example.damiangarcia.tareasettings.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.damiangarcia.tareasettings.database.DataBaseHandler;
import com.example.damiangarcia.tareasettings.database.ItemProductControl;

/**
 * Created by Damian Garcia on 10/22/2017.
 */

public class ProductsProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final String AUTHORITY =  "com.example.damiangarcia.tareasettings.Provider";
    private static final String PROVIDER_NAME=  AUTHORITY + ".ProductsProvider";

    static {
        sUriMatcher.addURI(AUTHORITY, "productos", 1);
        sUriMatcher.addURI(AUTHORITY, "productos/#", 2);
        //sUriMatcher.addURI(AUTHORITY, "ProductsProvider", 3);

        //sUriMatcher.addURI("com.example.damiangarcia.tareasettings.provider", "table3/#", 2);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DataBaseHandler dh = DataBaseHandler.getInstance(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        return true;    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String order) {
        ItemProductControl control = new ItemProductControl();
        DataBaseHandler h = DataBaseHandler.getInstance(getContext());
        Cursor c = null;
        switch (sUriMatcher.match(uri)){
            case  1:
                c = control.getProductsCursorWhere(null,  order, h);
                break;
            case  2:
                int _id = Integer.parseInt(uri.getLastPathSegment());
                c = control.getCursorProductById(_id,h);
                return c;
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        ItemProductControl control = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        long id = control.addItemProductFromValues(contentValues,dh);
        return Uri.parse(uri.toString()+"/"+id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int id = Integer.parseInt(strings[0]);
        ItemProductControl control = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        int affected  = control.deleteProduct(id, dh);
        return affected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int id = Integer.parseInt(strings[0]);
        ItemProductControl control = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        int affected  = control.updateProduct(contentValues, dh);
        return affected;
    }
}

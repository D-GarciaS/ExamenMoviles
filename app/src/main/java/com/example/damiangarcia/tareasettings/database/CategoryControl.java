package com.example.damiangarcia.tareasettings.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.damiangarcia.tareasettings.Beans.Category;

import java.util.ArrayList;

public class CategoryControl {
    public ArrayList<Category> getAllCategories(DataBaseHandler dh) {
        ArrayList<Category> categories= new ArrayList<>();

        String selectQuery = "SELECT S." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "S." + DataBaseHandler.KEY_CATEGORY_NAME
                + " FROM "
                + DataBaseHandler.TABLE_CATEGORY + " S ";

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Category category= new Category();
            category.setIdCategory(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        db = null;
        cursor = null;
// return store
        return categories;
    }
}

package com.example.s15012.pbl_teamapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by s15012 on 16/08/10.
 */
public class memberData extends SQLiteOpenHelper {
    private static final String DB_NAME = "member.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Members";

    private final static String set_CREATE_TABLE =      //データベースのテーブル作成
            "CREATE TABLE " + TABLE_NAME + "(" +
                    Columns.NAME + " TEXT," +
                    Columns.ADDRESS + " TEXT," +
                    Columns.ID + " TEXT PRIMARY KEY," +
                    Columns.PASSWORD + " TEXT)";

    public interface Columns extends BaseColumns {      //データベースの行

        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String ID = "id";
        public static final String PASSWORD = "password";
    }


    public memberData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);      //初回起動時にデータベースの有無確認
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL(set_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}



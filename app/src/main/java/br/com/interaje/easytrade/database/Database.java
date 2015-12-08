package br.com.interaje.easytrade.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by charles on 21/11/15.
 */
public class Database {

    private ProdutoDatabaseHelper databaseManager;
    private SQLiteDatabase sqld;
    public static final String DATABASE_NAME = "euvendo.db";
    public static final int DATABASE_VERSION = 9;

    public Database(ProdutoDatabaseHelper databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void open() {
        sqld = databaseManager.getWritableDatabase();
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        databaseManager.close();
    }

}

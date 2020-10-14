package id.rezayds.tedi.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import id.rezayds.tedi.model.Users;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "USERS.DB";
    private static final String TABLE_NAME = "USERS";
    private static final String COLUMN_ID = "userId";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_AVA = "ava";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAMA + " TEXT, "
                + COLUMN_AVA + " TEXT " + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void add(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, users.getNama());
        values.put(COLUMN_AVA, users.getAva());

        db.insert(TABLE_NAME, null, values);
    }

    public Users get(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT*FROM " + TABLE_NAME + " WHERE userId" + userId;
        Cursor cursor = db.rawQuery(query, null);

        Users selectedUser = new Users();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            selectedUser.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)));
            selectedUser.setAva(cursor.getString(cursor.getColumnIndex(COLUMN_AVA)));
        }

        return selectedUser;
    }

    public String getName() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT nama FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public String getAva() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT ava FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public String getUserId() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT userId FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public List<Users> getAll() {
        List<Users> usersList = new LinkedList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();

                users.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                users.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)));

                usersList.add(users);
            } while (cursor.moveToNext());
        }

        return usersList;
    }

    public int getCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public int update(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, users.getNama());
        values.put(COLUMN_AVA, users.getAva());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(users.getUserId())});
    }

    public void delete(int userId, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)});
        db.close();
    }

}

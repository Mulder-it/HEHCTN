package be.heh.hehctn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String ID = "id";
    public static final String NAME = "Nom";
    public static final String FIRSTNAME = "Prenom";
    public static final String LOGIN = "Login";
    public static final String PWD = "Password";
    public static final String ROLE = "Role";
    public static final String TABLE_NAME = "User";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    FIRSTNAME + " TEXT NOT NULL, " +
                    LOGIN + " TEXT NOT NULL, " +
                    PWD + " TEXT NOT NULL, " +
                    ROLE + " TEXT NOT NULL )";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(TABLE_CREATE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }
}

package be.heh.hehctn.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class UserAccessDB {
    private static final int VERSION = 1;
    private static final String DB_NAME = "HehctnDB";
    private static final String  TABLE_USER = "User";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "Nom";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_FIRSTNAME = "Prenom";
    private static final int NUM_COL_FIRSTNAME = 2;
    private static final String COL_LOGIN = "Login";
    private static final int NUM_COL_LOGIN = 3;
    private static final String COL_PASSWORD = "Password";
    private static final int NUM_COL_PASSWORD = 4;
    private static final String COL_ROLE = "Role";
    private static final int NUM_COL_ROLE = 5;
    private SQLiteDatabase db;
    private Database userdb;

    public UserAccessDB(Context c){
        userdb = new Database(c, DB_NAME, null, VERSION);
    }

    public void openForWrite(){
        db = userdb.getWritableDatabase();
    }

    public void openToRead(){
        db = userdb.getReadableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    public long insertUser(User u){

        ContentValues content = new ContentValues();
        content.put(COL_NAME, u.getNom());
        content.put(COL_FIRSTNAME, u.getPrenom());
        content.put(COL_LOGIN, u.getLogin());
        content.put(COL_PASSWORD, u.getPassword());
        content.put(COL_ROLE, u.getRole());
        return db.insert(TABLE_USER, null, content);
    }

    public long updateUser(int i, User u){

        ContentValues content = new ContentValues();
        content.put(COL_NAME, u.getNom());
        content.put(COL_FIRSTNAME, u.getPrenom());
        content.put(COL_LOGIN, u.getLogin());
        content.put(COL_PASSWORD, u.getPassword());
        content.put(COL_ROLE, u.getRole());
        return db.update(TABLE_USER,content, COL_ID + " = " + i, null);
    }

    public ArrayList<User> getAllUser(){
        Cursor c = db.query(TABLE_USER, new String[]{COL_ID,COL_NAME, COL_FIRSTNAME,COL_LOGIN, COL_PASSWORD, COL_ROLE}, null, null,null, null, COL_ID);
        ArrayList<User>tabUser = new ArrayList<>();
        if(c.getCount() == 0){

            c.close();
            return tabUser;
        }
        while (c.moveToNext()){

            User user1 = new User();
            user1.setId(c.getInt(NUM_COL_ID));
            user1.setNom(c.getString(NUM_COL_NAME));
            user1.setPrenom(c.getString(NUM_COL_FIRSTNAME));
            user1.setLogin(c.getString(NUM_COL_LOGIN));
            user1.setPassword(c.getString(NUM_COL_PASSWORD));
            user1.setRole(c.getString(NUM_COL_ROLE));
            tabUser.add(user1);
        }

        c.close();
        return tabUser;
    }
}

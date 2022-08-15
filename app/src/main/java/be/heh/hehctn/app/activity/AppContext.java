package be.heh.hehctn.app.activity;

import android.app.Application;
import android.content.ClipData;

import java.lang.reflect.Array;

public class AppContext extends Application {

    private int idLoginConnected;
    private String userRole;

    public int getIdLoginConnected() { return idLoginConnected; }
    public void setIdLoginConnected (int id){ idLoginConnected = id; }

    public String getUserRole(){ return userRole; }
    public void setUserRole(String id){ userRole = id; }


    @Override
    public void onCreate(){
        super.onCreate();
        idLoginConnected = -1;
        userRole = "READ";

    }
}

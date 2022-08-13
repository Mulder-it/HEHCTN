package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import be.heh.hehctn.R;
import be.heh.hehctn.db.User;
import be.heh.hehctn.db.UserAccessDB;

public class HomeActivity extends AppCompatActivity {

    private Button bt_supervision;
    private Button bt_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_supervision = (Button) findViewById(R.id.bt_home_supervision);
        bt_users = (Button) findViewById(R.id.bt_home_users);
    }
    public void onMainClickManager(View v) {


        switch (v.getId()) {
            case R.id.bt_home_supervision:
                Intent intentSupervion = new Intent(this, SupervisionActivity.class);
                startActivity(intentSupervion);
                break;

            case R.id.bt_home_users:
                Intent intentUserList = new Intent(this, ProfilActivity.class);
                startActivity(intentUserList);
                break;
        }
    }

}
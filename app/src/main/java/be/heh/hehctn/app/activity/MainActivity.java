package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;

import be.heh.hehctn.R;
import be.heh.hehctn.db.User;
import be.heh.hehctn.db.UserAccessDB;

public class MainActivity extends AppCompatActivity {

    private EditText et_main_login;
    private EditText et_main_password;
    private Button bt_login;
    private Button bt_register;
    private AppContext ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = (AppContext) this.getApplicationContext();


        et_main_login = (EditText) findViewById(R.id.et_main_login);
        et_main_password = (EditText) findViewById(R.id.et_main_password);
        bt_login = (Button) findViewById(R.id.bt_main_login);
        bt_register = (Button) findViewById(R.id.bt_main_register);

        bt_login.setEnabled(false);

        et_main_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bt_login.setEnabled(s.toString().length() > 3);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onMainClickManager(View v) {


        switch (v.getId()) {
            case R.id.bt_main_login:

                boolean connexion = false;

                UserAccessDB userDB = new UserAccessDB(this);
                userDB.openToRead();
                ArrayList<User> tab_user = userDB.getAllUser();

                EditText et_main_login=(EditText) findViewById(R.id.et_main_login);
                String sLogin = et_main_login.getText().toString();

                EditText et_main_password = (EditText)findViewById(R.id.et_main_password);
                String sPassword = et_main_password.getText().toString();

                for(User u : tab_user){
                    if (u.getLogin().equals(sLogin) && u.getPassword().equals(sPassword)) {
                        connexion = true;
                        ctx.setIdLoginConnected(u.getId());
                        ctx.setUserRole(u.getRole());
                        break;
                    }
                }
                if (connexion == true) {
                    Toast.makeText(this, "Vous êtes connecté !", Toast.LENGTH_LONG).show();

                    Intent intentHome = new Intent(this, HomeActivity.class);
                    startActivity(intentHome);
                }
                else {
                    Toast.makeText(this,"Echec de la connexion", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.bt_main_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
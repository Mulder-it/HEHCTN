package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import be.heh.hehctn.R;
import be.heh.hehctn.db.User;
import be.heh.hehctn.db.UserAccessDB;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_name;
    private EditText register_firstname;
    private EditText register_login;
    private EditText register_password;
    private EditText register_password_confirm;
    private Button bt_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = (EditText) findViewById(R.id.et_register_name);
        register_firstname = (EditText) findViewById(R.id.et_register_firstname);
        register_login = (EditText) findViewById(R.id.et_register_email);
        register_password = (EditText) findViewById(R.id.et_register_password);
        register_password = (EditText) findViewById(R.id.et_register_password_confirm);
        bt_submit = (Button) findViewById(R.id.bt_register_create);
    }

    public void onMainClickManager(View v){
        if(testName() && testFirstname() && testLogin() && testPassword() && testPasswordConfirm()){

            EditText register_name= (EditText) findViewById(R.id.et_register_name);
            String sName = register_name.getText().toString();

            EditText register_firstname= (EditText) findViewById(R.id.et_register_firstname);
            String sFirstname = register_firstname.getText().toString();

            EditText register_login= (EditText) findViewById(R.id.et_register_email);
            String sLogin = register_login.getText().toString();

            EditText register_password= (EditText) findViewById(R.id.et_register_password);
            String sPassword = register_password.getText().toString();

            UserAccessDB userDB = new UserAccessDB(this);
            userDB.openToRead();
            ArrayList<User> tab_user = userDB.getAllUser();
            userDB.closeDb();
            userDB.openForWrite();
            User u;

            if (tab_user.isEmpty()){
                u = new User(sName, sFirstname, sLogin, sPassword, "SUPERUSER");
            }
            else {
                u = new User(sName,sFirstname,sLogin,sPassword, "BASIC");
            }

            userDB.insertUser(u);
            userDB.closeDb();

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
    }

    public boolean testName() {
        EditText register_name=findViewById(R.id.et_register_name);
        String sName = register_name.getText().toString();
        if (sName.matches("")) {
            register_name.setError("Enter your Name");
            return false;
        }
        else{
            return true;
            }

    }
    public boolean testFirstname(){
        EditText register_firstname=findViewById(R.id.et_register_firstname);
        String sFirstname = register_firstname.getText().toString();
        if(sFirstname.matches("")){
            register_firstname.setError("Enter your firstname");
            return false;
        }
        else{
            return true;
        }
    }

    public boolean testLogin(){
        EditText register_login=findViewById(R.id.et_register_email);
        String sLogin = register_login.getText().toString();
        if (sLogin.matches("")){
            register_login.setError("Enter you email");
            return false;
        }
        else{
           int i=0;
           UserAccessDB userDB = new UserAccessDB(this);
           userDB.openToRead();
            ArrayList<User> tab_user = userDB.getAllUser();
            for(User u : tab_user){
                if (u.getLogin().equals(sLogin)){
                    i=1;
                    break;
                }
            }
            userDB.closeDb();
            if (i==1){
                register_login.setError("Email already exist");
                return false;
            }
            else{
                return true;
            }
        }
    }

    public boolean testPassword(){
        EditText register_password=findViewById(R.id.et_register_password);
        String sPassword = register_password.getText().toString();
        if (sPassword.matches("")){
            register_password.setError("Enter your password");
            return false;
        }
        else{
            if (sPassword.length()<4){
                register_password.setError("Password must contain 4 characters minimum");
                return false;
            }
            else{
                return true;
            }
        }
    }

    public boolean testPasswordConfirm(){
        EditText register_password = (EditText) findViewById(R.id.et_register_password);
        EditText register_password_confirm = (EditText) findViewById(R.id.et_register_password_confirm);
        String sPassword = register_password.getText().toString();
        String sPasswordConfirm = register_password_confirm.getText().toString();

        if (sPasswordConfirm.matches("")){
            register_password_confirm.setError("Entrer your password confirmation");
            return false;
        }
        else{
            if(!(sPasswordConfirm.equals(sPassword))){
                register_password_confirm.setError("Passwords do not match");
                return false;
            }
            else {
                return true;
            }
        }

    }
}
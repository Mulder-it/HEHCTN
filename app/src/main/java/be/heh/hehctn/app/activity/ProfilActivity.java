package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import be.heh.hehctn.R;

public class ProfilActivity extends AppCompatActivity {

    private AppContext ctx;
    String CompareValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ctx = (AppContext) this.getApplicationContext();


        Spinner role_spinner = (Spinner) findViewById(R.id.sp_profil_role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role_spinner.setAdapter(adapter);

       CompareValue = ctx.getUserRole();

       if(CompareValue !=null){
           int spinnerPosition = adapter.getPosition(CompareValue);
           role_spinner.setSelection(spinnerPosition);
       }
/*
        if (ctx.getUserRole().equals("SuperAdmin")){
            role_spinner.setEnabled(true);

        }
        else {
            role_spinner.setEnabled(false);
            if (ctx.getUserRole().equals("BASIC")){
                role_spinner.setSelection(3);
            }
        }*/




    }
}
package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.heh.hehctn.R;
import be.heh.hehctn.app.thread.DBpills;
import be.heh.hehctn.app.thread.PlcHandler;
import be.heh.hehctn.app.thread.PlcReader;
import be.heh.hehctn.app.thread.PlcWriter;


public class PillsActivity extends AppCompatActivity {
    LinearLayout li_comprime_read;
    LinearLayout li_comprime_write;

    PlcWriter plcWriter;

    private AppContext ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);

        Intent intent = getIntent();
        if (intent != null) {

            String sIp = "";
            if (intent.hasExtra("et_Ip")) {
                sIp = intent.getStringExtra("et_Ip");
            }

            String sRack = "";
            if (intent.hasExtra("et_Rack")) {
                sRack = intent.getStringExtra("et_Rack");
            }
            int nRack = new Integer(sRack).intValue();

            String sSlot = "";
            if (intent.hasExtra("et_Slot")) {
                sSlot = intent.getStringExtra("et_Slot");
            }
            int nSlot = new Integer(sSlot).intValue();


            initComponent();

            byte[] data = new byte[512];

            PlcReader plcReader = new PlcReader(
                    (LinearLayout) findViewById(R.id.li_pills_read),
                    DBpills.class,
                    new PlcHandler(),
                    (TextView) findViewById(R.id.t_pills_plc), data, sIp, nRack, nSlot);

            Thread threadRead = new Thread(plcReader);
            threadRead.start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            plcWriter = new PlcWriter(data, sIp, nRack, nSlot);

            Thread threadWrite = new Thread(plcWriter);
            threadWrite.start();
        }
    }

    public void initComponent() {
        li_comprime_read = findViewById(R.id.li_pills_read);
        li_comprime_write = findViewById(R.id.li_pills_write);

        ctx = (AppContext) this.getApplicationContext();

        if (ctx.getUserRole().equals("READ")) {
            li_comprime_write.setVisibility(View.GONE);
        }
    }

    public void OnClickUpdateByteManager(View view) {
        String tag = view.getTag().toString();
        EditText editText = new EditText(this);
        switch (tag) {
            case "DBB5":
                editText = (EditText) findViewById(R.id.et_pills_dbb5);
                break;
            case "DBB6":
                editText = (EditText) findViewById(R.id.et_pills_dbb6);
                break;
            case "DBB7":
                editText = (EditText) findViewById(R.id.et_pills_dbb7);
                break;
            case "DBB8":
                editText = (EditText) findViewById(R.id.et_pills_dbb8);
                break;
        }

        try {
            DBpills enumPillsDB = Enum.valueOf(DBpills.class, tag);
            int db = enumPillsDB.getDb();
            plcWriter.setWrite(db, Byte.parseByte(editText.getText().toString(), 2));
            Toast.makeText(this, "Modification Réussi !", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, " Erreur: La valeur doit être un byte", Toast.LENGTH_LONG).show();
        }
    }

    public void OnClickUpdateWordManager(View view) {
        String tag = view.getTag().toString();
        EditText editText = new EditText(this);
        switch (tag) {
            case "DBW18":
                editText = (EditText) findViewById(R.id.et_pills_dbw18);
                break;

        }
        DBpills enumPillsDB = Enum.valueOf(DBpills.class, tag);
        int db = enumPillsDB.getDb();
        try {
            int value = Integer.parseInt(editText.getText().toString());
            if (value >= -32768 && value <= 32767) {
                plcWriter.setWrite(db, Integer.parseInt(editText.getText().toString()));
                Toast.makeText(this, "Modification Réussie !", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Erreur: La valeur doit être un entier entre -32768 et 32767",
                        Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this,
                    "Erreur: La valeur doit être un entier entre -32768 et 32767",
                    Toast.LENGTH_LONG).show();
        }
    }
}
package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.heh.hehctn.R;


public class SupervisionActivity extends AppCompatActivity {

    private AppContext ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision);

        EditText ipok = (EditText) findViewById(R.id.et_supervision_ip);
        EditText rackok = (EditText) findViewById(R.id.et_supervision_rack);
        EditText slotok = (EditText) findViewById(R.id.et_supervision_slot);

        ipok.setText("192.168.10.127");
        rackok.setText("0");
        slotok.setText("1");


    }


    public void onMainClickManager(View view)
    {

        switch (view.getId()) {

            case R.id.bt_supervision_infopills:
                Intent intentBrowserPills = new Intent(Intent.ACTION_VIEW, Uri.parse("https://192.168.10.119"));
                startActivity(intentBrowserPills);

            case R.id.bt_supervision_inforegulation:
                Intent intentBrowserRegulation = new Intent(Intent.ACTION_VIEW, Uri.parse("https://192.168.10.114"));
                startActivity(intentBrowserRegulation);
                break;

            case R.id.bt_supervision_pills:
                EditText et_Ip = (EditText) findViewById(R.id.et_supervision_ip);
                String sIp = et_Ip.getText().toString();

                EditText et_Rack = (EditText) findViewById(R.id.et_supervision_rack);
                String sRack = et_Rack.getText().toString();

                EditText et_Slot = (EditText) findViewById(R.id.et_supervision_slot);
                String sSlot = et_Slot.getText().toString();

                Intent intentPills = new Intent(this, PillsActivity.class);
                intentPills.putExtra("et_Ip",sIp);
                intentPills.putExtra("et_Rack",sRack);
                intentPills.putExtra("et_Slot",sSlot);
                startActivity(intentPills);
                break;


            case R.id.bt_supervision_regulation:

                EditText et_Ip_Regu = (EditText) findViewById(R.id.et_supervision_ip);
                String rIp = et_Ip_Regu.getText().toString();

                EditText et_Rack_Regu = (EditText) findViewById(R.id.et_supervision_rack);
                String rRack = et_Rack_Regu.getText().toString();

                EditText et_Slot_Regu = (EditText) findViewById(R.id.et_supervision_slot);
                String rSlot = et_Slot_Regu.getText().toString();

                Intent intentRegulation = new Intent(this, RegulationActivity.class);
                intentRegulation.putExtra("et_Ip_Regu",rIp);
                intentRegulation.putExtra("et_Rack_Regu",rRack);
                intentRegulation.putExtra("et_Slot_Regu",rSlot);
                startActivity(intentRegulation);
                break;

        }
    }
}
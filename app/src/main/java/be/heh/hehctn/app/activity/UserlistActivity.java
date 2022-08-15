package be.heh.hehctn.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import be.heh.hehctn.R;
import be.heh.hehctn.db.User;
import be.heh.hehctn.db.UserAccessDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserlistActivity extends AppCompatActivity {

    private int Id;
    private String Name;

    TextView tv_userslist_data;
    ListView lv_userlist_list;
    AppContext ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        tv_userslist_data = (TextView) findViewById(R.id.tv_userslist_data);
        lv_userlist_list = (ListView) findViewById(R.id.lv_userlist_list);
        ctx = (AppContext) this.getApplicationContext();

        UserAccessDB userDB = new UserAccessDB(this);
        userDB.openToRead();
        ArrayList<User> tab_user = userDB.getAllUser();

        User user = new User();

        Id = user.getId();
        Name = user.getNom();
        if(tab_user.isEmpty())Toast.makeText(this, "Aucun utilisateur",
                Toast.LENGTH_SHORT).show();
        else{ ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tab_user);
            lv_userlist_list.setAdapter(adapter); }

        Adapter adapter = new Adapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };

        userDB.closeDb();

    }
}
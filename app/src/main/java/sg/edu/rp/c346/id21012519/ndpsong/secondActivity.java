package sg.edu.rp.c346.id21012519.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    ArrayList<Song> al;
    ListView lv;
    Button btnShowFiveStar;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShowFiveStar = (Button) findViewById(R.id.btnFive);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList<Song>();

        DBhelper db = new DBhelper(secondActivity.this);
        al.clear();
        al.addAll(db.getAllSongs());
        db.close();

        aa = new ArrayAdapter(secondActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent gothirdActivity = new Intent(secondActivity.this, thirdActivity.class);

                Song data = al.get(position);

                gothirdActivity.putExtra("data" , data);
                startActivity(gothirdActivity);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            lv = (ListView)findViewById(R.id.lv);
            al = new ArrayList<Song>();
            DBhelper dbh = new DBhelper(secondActivity.this);
            al.clear();
            al.addAll(dbh.getAllSongs());
            dbh.close();
            aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1,al);

            lv.setAdapter(aa);
        }
    }
}
package sg.edu.rp.c346.id21012519.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rb;
    Button btnInsert;
    Button btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = (EditText) findViewById(R.id.etSongTitle);
        etSingers = (EditText) findViewById(R.id.etSongSinger);
        etYear = (EditText) findViewById(R.id.etYear);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                if (!songTitle.equals("") || !singers.equals("") || !year.equals("")){

                    DBhelper db = new DBhelper(MainActivity.this);
                    db.getWritableDatabase();
                    db.insertSong(songTitle, singers
                            , Integer.parseInt(year)
                            , Integer.parseInt(rb.getText().toString()));

                    Toast.makeText(MainActivity.this, "Song inserted", Toast.LENGTH_LONG).show();
                    etSongTitle.setText("");
                    etSingers.setText("");
                    etYear.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosecondActivity = new Intent(MainActivity.this, secondActivity.class);
                startActivity(gosecondActivity);
            }
        });
    }
}
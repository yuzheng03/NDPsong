package sg.edu.rp.c346.id21012519.ndpsong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class thirdActivity extends AppCompatActivity {

    EditText songID;
    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    RadioGroup rgStars;
    RadioButton rb;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        songID = findViewById(R.id.songID);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSongSinger);
        etYear = findViewById(R.id.etYear);
        rgStars =findViewById(R.id.rgStars);
        rb1 =  findViewById(R.id.radio1);
        rb2 =  findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        rb5 = findViewById(R.id.radio5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel =findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        songID.setText(String.valueOf(data.getId()) );
        etSongTitle.setText(data.getTitle());
        etSingers.setText(data.getSinger());
        etYear.setText(String.valueOf(data.getYear()));

        if(data.getStars() == 5) {
            rb5.setChecked(true);
        } else if (data.getStars() == 4) {
            rb4.setChecked(true);
        } else if (data.getStars() == 3) {
            rb3.setChecked(true);
        } else if (data.getStars() == 2) {
            rb2.setChecked(true);
        } else {
            rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh = new DBhelper(thirdActivity.this);

                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                data.setTitle(etSongTitle.getText().toString());
                data.setSinger(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(Integer.parseInt(rb.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh = new DBhelper(thirdActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
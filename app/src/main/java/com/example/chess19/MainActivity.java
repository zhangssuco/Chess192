package com.example.chess19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText t1=(EditText)findViewById(R.id.editText);
        final drawingboard db=(drawingboard)findViewById(R.id.board);
        Button b=(Button)findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                playsound(R.raw.hawking01);
                db.reset(Integer.parseInt(t1.getText().toString()));
            }
        });

        db.setEventListener(new MyEventListener() {

            @Override
            public void onEventOccurred(Point p) {

                // TODO Auto-generated method stub
                    t1.setText(String.valueOf(p.x)+String.valueOf(p.y));

            }
        });


    }

    void playsound(int id)
    {
        //http://www.wavsource.com/snds_2018-06-03_5106726768923853/people/famous/hawking01.wav
        final MediaPlayer mp = MediaPlayer.create(this, id);
        mp.start();

    }

    public void showrule(View view)
    {
        Intent browsing=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.chess.com/"));

        startActivity(browsing);
    }

}

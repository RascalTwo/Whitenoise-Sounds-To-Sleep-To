package kenthackenough.whitenoise;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.media.MediaPlayer;
import android.view.MenuItem;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class mainPage extends Activity{
    Handler handler = new Handler();
    MediaPlayer mediaPlayer;
    int soundsSelected = R.raw.nature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startLoop(final View v){
        try{
            mediaPlayer.release();
        }
        catch(Exception e){
            System.out.println("Expected Much...");
        }
        mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
        mediaPlayer.start();
        handler.postDelayed(new Runnable(){
            public void run(){
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
                mediaPlayer.start();
            }
        }, 265000);
        handler.postDelayed(new Runnable(){
            public void run(){
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
                mediaPlayer.start();
            }
        }, 265000);
        playNoise();
    }

    public void playNoise(){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
        mediaPlayer.start();
        handler.postDelayed(new Runnable() {
            public void run() {
                playTroll();
            }
        }, 265000);
    }

    public void playTroll(){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.troll);
        mediaPlayer.start();
        handler.postDelayed(new Runnable(){
            public void run(){
                playNoise();
            }
        }, 95000);
    }
}

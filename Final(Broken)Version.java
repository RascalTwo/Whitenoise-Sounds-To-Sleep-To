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
    Date counterStartTime = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        try {

            ArrayList<Integer> counts = new ArrayList<Integer>();

            int natureCount = Integer.getInteger(readSharedPrefrences("Integer", "natureCount"));
            int whiteCount = Integer.getInteger(readSharedPrefrences("Integer", "whiteCount"));
            int pinkCount = Integer.getInteger(readSharedPrefrences("Integer", "pinkCount"));
            int whaleCount = Integer.getInteger(readSharedPrefrences("Integer", "whaleCount"));

            Collections.addAll(counts, natureCount, whiteCount, pinkCount, whaleCount);
            int hightestCount = counts.get(0);

            for (int i = 1; i < counts.size(); i++) {
                if (counts.get(i) > hightestCount) {
                    hightestCount = counts.get(i);
                }
            }
            if (hightestCount == counts.get(1)) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.whiteRadio);
                radioButton.setChecked(true);
            } else if (hightestCount == counts.get(2)) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.pinkRadio);
                radioButton.setChecked(true);
            } else if (hightestCount == counts.get(3)) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.whaleRadio);
                radioButton.setChecked(true);
            } else if (hightestCount == counts.get(0)) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.natureRadio);
                radioButton.setChecked(true);
            }

            ArrayList<String> currentSoundTimes = getSoundTimes();

            TextView natureText = (TextView) findViewById(R.id.natureText);
            TextView whiteText = (TextView) findViewById(R.id.whiteText);
            TextView pinkText = (TextView) findViewById(R.id.pinkText);
            TextView whaleText = (TextView) findViewById(R.id.whaleText);

            ArrayList<Integer> processedSoundTimes = new ArrayList<Integer>();
            ArrayList<String> processedTimeLetters = new ArrayList<String>();

            for (int i = 0; i < 4; i++) {
                if (IntFromCst(currentSoundTimes, i) >= 60) {
                    processedSoundTimes.add(IntFromCst(currentSoundTimes, i) / 60);
                    processedTimeLetters.add(" M");
                } else if (IntFromCst(currentSoundTimes, i) >= 3600) {
                    processedSoundTimes.add(IntFromCst(currentSoundTimes, i) / 3600);
                    processedTimeLetters.add(" H");
                } else {
                    processedSoundTimes.add(IntFromCst(currentSoundTimes, i));
                    processedTimeLetters.add(" S");
                }
            }

            natureText.setText("Listened To For: " + processedSoundTimes.get(0) + processedTimeLetters.get(0));
            whiteText.setText("Listened To For: " + processedSoundTimes.get(1) + processedTimeLetters.get(1));
            pinkText.setText("Listened To For: " + processedSoundTimes.get(2) + processedTimeLetters.get(2));
            whaleText.setText("Listened To For: " + processedSoundTimes.get(3) + processedTimeLetters.get(3));
        }
        catch(Exception e){

        }
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

    public Integer IntFromCst(ArrayList<String> currentSoundTimes, Integer position){
         return Integer.getInteger(currentSoundTimes.get(0));
    }

    public void readRadioButtons(View v){
        ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
        Collections.addAll(radioButtons, (RadioButton) v.findViewById(R.id.natureRadio), (RadioButton) v.findViewById(R.id.whiteRadio), (RadioButton) v.findViewById(R.id.pinkRadio), (RadioButton) v.findViewById(R.id.whaleRadio));
        for(RadioButton radiobutton : radioButtons){
            if(radiobutton.isChecked()){
                int natureCount = 0, whiteCount = 0,  pinkCount = 0, whaleCount = 0;

                if(radiobutton.getId() == R.id.natureRadio){
                    soundsSelected = R.raw.nature;
                    natureCount++;
                }
                else if(radiobutton.getId() == R.id.whiteRadio){
                    soundsSelected = R.raw.white;
                    whiteCount++;
                }
                else if(radiobutton.getId() == R.id.pinkRadio){
                    soundsSelected = R.raw.pink;
                    pinkCount++;
                }
                else if(radiobutton.getId() == R.id.whaleRadio){
                    soundsSelected = R.raw.whale;
                    whaleCount++;
                }

                natureCount =+ Integer.getInteger(readSharedPrefrences("Integer", "natureCount"));
                whiteCount =+ Integer.getInteger(readSharedPrefrences("Integer", "whiteCount"));
                pinkCount =+ Integer.getInteger(readSharedPrefrences("Integer", "pinkCount"));
                whaleCount =+ Integer.getInteger(readSharedPrefrences("Integer", "whaleCount"));

                writeSharedPrefrences("Integer", "natureCount", natureCount + "");
                writeSharedPrefrences("Integer", "whiteCount", whiteCount + "");
                writeSharedPrefrences("Integer", "pinkCount", pinkCount + "");
                writeSharedPrefrences("Integer", "whaleCount", whaleCount + "");
            }
        }
    }

    public void changeVisibility(View v, Integer state, String type, Integer id, Integer milisecondDelay){
        if(milisecondDelay != 0){
            handler.postDelayed(new Runnable(){
                public void run(){
                    System.out.println("Just Waiting...");
                }
            }, milisecondDelay);
        }
        if(type.equals("Button")){
            Button button = (Button) v.findViewById(id);
            button.setVisibility(state);
        }
        else if(type == "RadioGroup"){
            RadioGroup radioGroup = (RadioGroup) v.findViewById(id);
            radioGroup.setVisibility(state);
        }
        else if(type == "Switch"){
            Switch switchA = (Switch) v.findViewById(id);
            switchA.setVisibility(state);
        }
    }

    public String readSharedPrefrences(String type, String key){
        SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
        if(type.equals("String")){
            return userDetails.getString(key, "");
        }
        else if(type.equals("Integer")){
            return userDetails.getInt(key, 0) + "";
        }
        else{
            return null;
        }
    }

    public void writeSharedPrefrences(String type, String key, String value){
        SharedPreferences.Editor edit = getSharedPreferences("userdetails", MODE_PRIVATE).edit();
        if(type.equals("String")){
            edit.putString(key, value);
        }
        else if(type.equals("Integer")){
            edit.putInt(key, Integer.getInteger(value));
        }
        edit.commit();
    }

    public ArrayList<String> getSoundTimes(){
        ArrayList<String> currentSoundTimes = new ArrayList<String>();
        currentSoundTimes.add(readSharedPrefrences("String", "natureTime"));
        currentSoundTimes.add(readSharedPrefrences("String", "whiteTime"));
        currentSoundTimes.add(readSharedPrefrences("String", "pinkTime"));
        currentSoundTimes.add(readSharedPrefrences("String", "whaleTime"));
        return currentSoundTimes;
    }

    public void endCounter(){
        long recentTime = new Date().getTime() - counterStartTime.getTime() * 1000;
        ArrayList<String> currentSoundTimes = getSoundTimes();
        switch(soundsSelected){
            case R.raw.nature:
                writeSharedPrefrences("Integer", "natureTime", currentSoundTimes.get(0) + recentTime);
                break;

            case R.raw.white:
                writeSharedPrefrences("Integer", "whiteTime", currentSoundTimes.get(1) + recentTime);
                break;

            case R.raw.pink:
                writeSharedPrefrences("Integer", "pinkTime", currentSoundTimes.get(2) + recentTime);
                break;

            case R.raw.whale:
                writeSharedPrefrences("Integer", "whaleTime", currentSoundTimes.get(3) + recentTime);
                break;
        }
    }

    public void startLoop(final View v){
        counterStartTime = new Date();
        readRadioButtons(v);
        changeVisibility(v, View.GONE, "Button", R.id.startButton, 0);
        changeVisibility(v, View.VISIBLE, "Button", R.id.stopButton, 1000);
        mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
        mediaPlayer.start();
        int targetTime = 900000;
        int clipLength = mediaPlayer.getDuration();
        targetTime -= clipLength;
        for(int i = 0; i < targetTime;){
            handler.postDelayed(new Runnable(){
                public void run(){
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
                    mediaPlayer.start();
                }
            }, clipLength+1000);
            targetTime -= clipLength;
        }
        playTroll();
    }

    public void stopLoop(final View v){
        if(mediaPlayer.isPlaying()){
            endCounter();
            mediaPlayer.stop();
            mediaPlayer.release();
            changeVisibility(v, View.GONE, "Button", R.id.stopButton, 0);
            changeVisibility(v, View.VISIBLE, "Button", R.id.startButton, 1000);
        }
    }

    public void playNoise(){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getBaseContext(), soundsSelected);
        mediaPlayer.start();
        handler.postDelayed(new Runnable() {
            public void run() {
                playTroll();
            }
        }, mediaPlayer.getDuration()+1000);
    }

    public void playTroll(){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.troll);
        mediaPlayer.start();
        handler.postDelayed(new Runnable(){
            public void run(){
                playNoise();
            }
        }, mediaPlayer.getDuration()+1000);
    }
}

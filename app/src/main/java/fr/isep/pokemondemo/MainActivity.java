package fr.isep.pokemondemo;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * The trick here is that although the radio group looks absent from landscapemode, it is present except invisible.
 *
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MA", "On Create");
    }

    /**
     * Called when activity is killed on rotation
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MA", "onSaveInstanceState");

        RadioGroup radiogaga = findViewById(R.id.radiogaga);
        int sel = radiogaga.getCheckedRadioButtonId();
        outState.putInt("sel", sel); // saving selection into the bundle

        if( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("MA", "onSaveInstanceState PORTRAIT");
         }
        else{
            Log.d("MA", "onSaveInstanceState LAND");
        }
    }

    /**
     * Used when activity is recreated
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("MA","onRestoreInstanceState");
        int sel = savedInstanceState.getInt("sel",0); // getting selection from bundle

        RadioButton button = findViewById(sel);
        button.setChecked(true);
        button.callOnClick();

        if( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // landscape mode
            // changePicture(sel);
            TextView detail = findViewById(R.id.detail);
            String s = "desc_"+sel;
            switch (sel){
                case R.id.bulb:
                    s = getStringResourceByName("desc_0");
                    break;
                case R.id.charm:
                    s = getStringResourceByName("desc_1");
                    break;
                case R.id.squi:
                    s = getStringResourceByName("desc_2");
                    break;
            }
            detail.setText(s);
        }
    }

    public void changePicture(int id){
        Log.d("MA", "changePicture " +id );
        ImageView imageView = findViewById(R.id.photo) ;
        imageView.setImageResource(id);
    }

    public void onClickBulb(View v){
        changePicture(R.drawable.bulbasaur);
    }

    public void onClickCharan(View v){
        changePicture(R.drawable.charmander);
    }

    public void onClickSquirtle(View v){
        changePicture(R.drawable.squirtle);
    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

}
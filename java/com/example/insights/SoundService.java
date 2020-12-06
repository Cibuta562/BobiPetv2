package com.example.insights;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
public class SoundService extends Service {


    MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {
        //mediaPlayer = MediaPlayer.create(this, R.raw.piesa_autist); //select music file
        //mediaPlayer.setLooping(true); //set looping
       // mediaPlayer.start();


}
}




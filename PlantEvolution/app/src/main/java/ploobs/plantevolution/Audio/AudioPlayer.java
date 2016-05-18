package ploobs.plantevolution.Audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.HashMap;
import java.util.Map;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.R;

/**
 * Created by Bruno on 10/02/2016.
 */
public class AudioPlayer {

    private Map<String,MediaPlayer> _audio = new HashMap<String,MediaPlayer>();



private MediaPlayer _player;
    private boolean playSound=true;

    private AudioPlayer(){


    }
    public boolean getSoundState()
    {
        return playSound;

    }

    public void toogleSound() {
        playSound = !playSound;

        if (!playSound)
            pausePlayer();
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        private static final AudioPlayer INSTANCE = new AudioPlayer();
    }

    public static AudioPlayer getInstance() {
        return SingletonHolder.INSTANCE;
    }

public void addAudio(String alias, int resourceid, boolean loop)
{
    if (!_audio.containsKey(alias))
    {

        MediaPlayer mediaPlayer = MediaPlayer.create(GraphicFactory.getInstance().getGraphicContext(), resourceid);
        mediaPlayer.setLooping(loop);

        _audio.put(alias,mediaPlayer);

    }
}

    public void changeVolume(String soundAlias, int volume)
    {
        MediaPlayer mtemp;
        if (_audio.containsKey(soundAlias))
        {
            mtemp = _audio.get(soundAlias);
            mtemp.setVolume((float) volume / 100.0f, (float) volume / 100.0f);

        }

    }


    public void playAudio(String soundAlias)
    {
        if(playSound) {
            MediaPlayer mtemp;
            if (_audio.containsKey(soundAlias)) {
                mtemp = _audio.get(soundAlias);

                mtemp.start();

            }
        }

    }

    public void stopAudio(String soundAlias)
    {
        MediaPlayer mtemp;
        if (_audio.containsKey(soundAlias))
        {
            mtemp = _audio.get(soundAlias);
            if (mtemp.isPlaying())
                mtemp.stop();

        }

    }

    public void pauseAudio(String soundAlias)
    {
        MediaPlayer mtemp;
        if (_audio.containsKey(soundAlias))
        {
            mtemp = _audio.get(soundAlias);
            if (mtemp.isPlaying())
                mtemp.pause();

        }

    }

    public void pausePlayer()
    {
        for (MediaPlayer m:_audio.values()
                ) {
            m.pause();

        }

    }


    public void resumePlayer()
    {

    }


    public void closePlayer()
    {
        for (MediaPlayer m:_audio.values()
             ) {
            m.release();

        }

    }



}

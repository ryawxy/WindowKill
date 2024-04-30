package Sound;

import View.Settings.SettingsFrame;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip backgroundMusic;
    private FloatControl volumeControl;
    private FloatControl volumeControl2;

    public void playBackgroundMusic() {
        try {
            File file = new File("src/Sound/Hedwig_s-Theme.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            volumeControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);

            if(SettingsFrame.getChosenSound()==0) setVolume(0);
            else if(SettingsFrame.getChosenSound()==50) setVolume(30);
             else if(SettingsFrame.getChosenSound()==100) setVolume(100);

                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public void playSoundEffect(String filePath) {

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            Clip soundEffect = AudioSystem.getClip();

            soundEffect.open(audioInputStream);
            volumeControl2 = (FloatControl) soundEffect.getControl(FloatControl.Type.MASTER_GAIN);

            if(SettingsFrame.getChosenSound()==0) setVolume(0);
            else if(SettingsFrame.getChosenSound()==50) setVolume(30);
            else if(SettingsFrame.getChosenSound()==100) setVolume(100);

            soundEffect.start();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void stopBackgroundMusic() {

        if (backgroundMusic != null) {

            backgroundMusic.stop();
            backgroundMusic.close();
        }

    }

    public void setVolume(int volumePercent) {
        if (volumeControl != null && volumeControl2!= null) {
            if (volumePercent < 0) {
                volumePercent = 0;
            } else if (volumePercent > 100) {
                volumePercent = 100;
            }

            float volume = volumePercent / 100.0f;
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
            volumeControl2.setValue(dB);
        }

    }


    }


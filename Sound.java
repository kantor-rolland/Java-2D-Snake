import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    Clip clip;
    String[] path;

    public Sound() {

        path = new String[3];
        path[0] = "snake_music.wav";
        path[1] = "eat.wav";
        path[2] = "game_over.wav";

        //this.SetSound(0);
    }

    //unmute
    public void Play() {
        clip.start();
    }

    //unmute
    public void Stop() {
        clip.stop();
    }

    public void Loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void SetSound(int x) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path[x]));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }

    }
}

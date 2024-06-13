package main;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private String soundFileName;

    public SoundEffect(String wavfile) {
        soundFileName = wavfile;
    }

    public void play() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
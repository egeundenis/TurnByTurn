package GUI;

import main.SoundEffect;

public class Utility {

	public Utility() {}
	
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void playThisTimes(SoundEffect SE, int thisTimes) {
		for(int i = 0; i < thisTimes; i++) {
			SE.play();
		}
	}
	
	public static void playThisTimesWithSleep(SoundEffect SE, int thisTimes, int ms) {
		for(int i = 0; i < thisTimes; i++) {
			SE.play();
			sleep(ms);
		}
	}
	
	

}

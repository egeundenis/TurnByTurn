package main;

import java.util.Random;

import Fighters.Fighter;

public class Spell {

	public int currentCooldown = 0;
	public int cooldown;
	public int effectTurn;
	public boolean doesSkip = true;
	public int useCount = 0;
	public String name;

	public Spell(String name){
		this.name = name;
		
		switch (name) {
		case "Static Shock":
			cooldown = 25;
			effectTurn = -1;
			break;
		case "Redemption":
			cooldown = 15;
			effectTurn = -1;
			break;
		case "Heart of Steel":
			cooldown = 20;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Glacial Gale":
			cooldown = 25;
			effectTurn = -1;
			break;
		case "Electric Storm":
			cooldown = 20;
			effectTurn = -1;
			break;
		case "Guardian":
			cooldown = 25;
			effectTurn = -1;
			break;
		case "Prixie":
			cooldown = 10;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Last Strike":
			cooldown = 30;
			effectTurn = -1;
			break;
		case "Demonic Cuteness":
			cooldown = 20;
			effectTurn = 0;
			doesSkip = false;
			break;
		case "Mirror":
			cooldown = 35;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Meditation Medication":
			cooldown = 25;
			effectTurn = 0;
			break;
		case "Laser Rain":
			cooldown = 10;
			effectTurn = -1;
			useCount = 0;
			break;
			
		}
		
	}

	public static void doSpell(Fighter user, Fighter enemy, Spell spell) {
		
	    Random ran = new Random();
	    
		switch (spell.name) {
		
		case "Static Shock":
			SoundManager.static_damage.play();
			int num = user.SuperCharge;
			if (num >= 100) {
				enemy.changeSTATUS(Status.Stunned);
				SoundManager.static_stun.play();
			}
			enemy.changeHP((int)(-num*0.5));
			user.changeCHARGE(-num);
			break;
		case "Redemption":
			SoundManager.redemption1.play();
			SoundManager.redemption2.play();
			user.changeHP(40);
			enemy.changeHP(-40);
			break;
			
		case "Heart of Steel":
			SoundManager.Heartsteel.play();
			enemy.changeHP( (int)(user.HP * -0.10) );
			break;
			
		case "Glacial Gale":
			SoundManager.GlacialGale.play();
			enemy.changeHP(-20);
			enemy.changeSTATUS(Status.Frosty);
			
			if(ran.nextInt(0,2) == 1) {
				enemy.changeSTATUS(Status.Stunned);
				enemy.changeHP(-40);
				SoundManager.GlacialGaleStun.play();
			}
			break;
			
		case "Electric Storm":
			enemy.changeHP(-25);
			SoundManager.electro1.play();
					
			if(ran.nextInt(0,3) % 2 == 0) {
				try {Thread.sleep(300);}catch(InterruptedException e){}
				enemy.changeHP(-50);
				SoundManager.electro2.play();
				
				if(ran.nextInt(0,3) == 1) {
					try {Thread.sleep(300);}catch(InterruptedException e){}
					enemy.changeHP(-100);
					SoundManager.electro3.play();
				}
			}
			break;
			
		case "Guardian":
			SoundManager.guardian.play();
			user.changeSHIELD(125);
			break;
			
		case "Prixie":
			if (ran.nextInt(0,2) == 0) {
				SoundManager.prixieheal.play();
				user.changeHP(20);
				user.changeSHIELD(20);
			}
			else {
				SoundManager.prixiedmg.play();
				enemy.changeHP(-20);
				enemy.changeSTATUS(Status.Weakened); 
			}
			break;
		
		case "Last Strike":
			SoundManager.laststrikeREADY.play();
			try {Thread.sleep(500);}catch(InterruptedException e){}
			int count = 0;
			for(int i = 0; i < 20; i++) 
				if(ran.nextInt(0,2) == 0)
					count++;
			
			for(int i = 0; i < count; i++) {				
				switch(ran.nextInt(0,3)) {
				case 0: SoundManager.laststrike1.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				case 1: SoundManager.laststrike2.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				case 2: SoundManager.laststrike3.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				}
				enemy.changeHP(-10);	
			}
				
			break;	
		
		case "Demonic Cuteness":
			SoundManager.demonic_cuteness.play();
			user.spell.effectTurn = 5;
			break;
			
		case "Mirror":
			SoundManager.mirror.play();
			if(enemy.spell.name == "Mirror") {
				try {Thread.sleep(355);}catch(InterruptedException e){}
				SoundManager.mirror.play();
			}
			else {				
				user.spell.name = enemy.spell.name;
				doSpell(user, enemy, spell);
			}
			break;
			
		case "Meditation Medication":
			SoundManager.medmed.play();
			user.spell.effectTurn = 5;
			break;
		case "Laser Rain":
			
			for(int i = -1; i < user.spell.useCount; i++) {
				SoundManager.laser_rain.play();
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				enemy.changeHP(-20);
			}			
			
			user.spell.useCount++;
			
			break;
			
			
		//end of switch
		}
	}
	
	
}

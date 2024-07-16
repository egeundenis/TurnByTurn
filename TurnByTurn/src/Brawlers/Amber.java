package Brawlers;

import java.util.Random;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Amber extends Brawler {

	SoundEffect amber_attack1 = new SoundEffect("res/audio/amber_attack1.wav");
	SoundEffect amber_attack2 = new SoundEffect("res/audio/amber_attack2.wav");
	SoundEffect amber_attack3 = new SoundEffect("res/audio/amber_attack3.wav");
	SoundEffect amber_attack4 = new SoundEffect("res/audio/amber_attack4.wav");

	SoundEffect amber_super1 = new SoundEffect("res/audio/amber_super1.wav");
	SoundEffect amber_super2 = new SoundEffect("res/audio/amber_super2.wav");
	SoundEffect amber_super3 = new SoundEffect("res/audio/amber_super3.wav");
	SoundEffect amber_super4 = new SoundEffect("res/audio/amber_super4.wav");
	
	public ImageIcon amber_gun1 = new ImageIcon("res/images/amber_pistol.png");
	public ImageIcon amber_gun2 = new ImageIcon("res/images/amber_netshooter.png");
	public ImageIcon amber_gun3 = new ImageIcon("res/images/amber_shotgun.png");
	public ImageIcon amber_gun4 = new ImageIcon("res/images/amber_soulstealer.png");
	
	
	public int currentWeapon = 1;
	/*
	 1: Rapidfire // Fiercefire
	 2: Clencher // Double Squeeze
	 3: Harsh Pierce // Gashopener
	 4: Soulfire // Soulless Strike
	 * */
	
	Random ran = new Random();
	 
	
    public Amber(Build build) {
        super(build);
        this.build = build;
        this.name = "Amber";
        this.HP = 500;
        this.AttackDamage = 120;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 120;
        this.regen = 3;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Skilled";
        this.spell = new Spell(build.spellChoise);

        if ("HP GEAR".equals(build.gearChoise))
            this.HP *= 1.1;
        if ("POTION GEAR".equals(build.gearChoise))
            this.potionCount += 1;
        if ("GADGET GEAR".equals(build.gearChoise))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
    	switch (currentWeapon) {
    	
    	case 1:
    		//Rapidfire // Fiercefire
    		amber_attack1.play();
    		enemy.changeHP(-this.AttackDamage*statper/6);
    		this.changeCHARGE(this.AttackDamage*statper/6);
    			int randint = ran.nextInt(0,120);
    			
    			if(randint < 90) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			
    			if(randint < 60) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			
    			if(randint < 40) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			
    			if(randint < 30) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			
    			if(randint < 15) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			
    			if(randint < 10) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_attack1.play();
    				enemy.changeHP(-this.AttackDamage*statper/12);
    	    		this.changeCHARGE(this.AttackDamage*statper/12);
    			}
    			this.changeSHIELD(5);
    			currentWeapon = 2;
    		break;
    		
    	case 2:
    		//Clencher - Double Squeeze
    		amber_attack2.play();
    		enemy.changeHP(-this.AttackDamage*statper/8);
    		this.changeCHARGE(this.AttackDamage*statper/8);
    			if(enemy.stat == Status.Weakened || enemy.stat == Status.Stunned) {
    				
    				try {Thread.sleep(300);}catch(InterruptedException e){}
    				amber_attack2.play();
    				enemy.changeSTATUS(Status.Stunned);
    				enemy.changeHP(-this.AttackDamage*statper/8);
    	    		this.changeCHARGE(this.AttackDamage*statper/8);
    	    		
    			}else 
    				enemy.changeSTATUS(Status.Weakened);
    			
    			this.changeSHIELD(5);
    			currentWeapon = 3;
    		break;
    		
    	case 3:
    		//Harsh Pierce - Gashopener
    		amber_attack3.play();
    		enemy.changeHP(-this.AttackDamage*statper/3);
    		this.changeCHARGE(this.AttackDamage*statper/3);
    		
    		if(enemy.HP < enemy.newInstance().HP * 0.50) {
    			
    			try {Thread.sleep(300);}catch(InterruptedException e){}
    			amber_attack3.play();
        		enemy.changeHP(-this.AttackDamage*statper/3);
        		this.changeCHARGE(this.AttackDamage*statper/3);
        		
    		}
    			this.changeSHIELD(5);
    			currentWeapon = 4;
    		break;
    		
    	case 4:
    		//Soulfire - Soulless Strike
    		amber_attack4.play();
    		
    		double change = enemy.HP * 0.075 * statper;
    		change += this.AttackDamage/8;
    		enemy.changeHP(-change);
    		this.changeHP(change);
    		this.changeCHARGE(change);
    		
    		this.changeSHIELD(5);
    		currentWeapon = 1;
    		break;
    	}
    	
    	

    //end of attack
    }

    @Override
	public
    void superAbility(Brawler enemy) {

    	switch (currentWeapon) {
    	
    	case 1:
    		//Rapidfire // Fiercefire
    		amber_super1.play();
    		enemy.changeHP(-this.AttackDamage/4.8);
    			int randint = ran.nextInt(0,100);
    			
    			if(randint < 95) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_super1.play();
    				enemy.changeHP(-this.AttackDamage/8);
    			}
    			
    			if(randint < 85) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_super1.play();
    				enemy.changeHP(-this.AttackDamage/8);
    			}
    			
    			if(randint < 75) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_super1.play();
    				enemy.changeHP(-this.AttackDamage/8);
    			}
    			
    			if(randint < 65) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_super1.play();
    				enemy.changeHP(-this.AttackDamage/8);
    			}
    			
    			if(randint < 55) {
    				try {Thread.sleep(200);}catch(InterruptedException e){}
    				amber_super1.play();
    				enemy.changeHP(-this.AttackDamage/8);
    			}
    			
    			this.changeSHIELD(5);
    			currentWeapon = 2;
    		break;
    		
    	case 2:
    		//Clencher - Double Squeeze
    		amber_super2.play();
    		enemy.changeHP(-this.AttackDamage/4);
    		
    			if(enemy.stat == Status.Stunned) {
    				
    				try {Thread.sleep(300);}catch(InterruptedException e){}
    				amber_attack2.play();
    				enemy.changeSTATUS(Status.Stunned);
    				enemy.changeHP(-this.AttackDamage/2.66);
    	    		
    			}else 
    				enemy.changeSTATUS(Status.Stunned);
    		
    			this.changeSHIELD(5);
    			currentWeapon = 3;	
    		break;
    		
    	case 3:
    		//Harsh Pierce - Gashopener
    		amber_super3.play();
    		enemy.changeHP(-this.AttackDamage/2);
    		
    		if(enemy.HP < enemy.newInstance().HP * 0.50) {
    			
    			try {Thread.sleep(300);}catch(InterruptedException e){}
    			amber_super3.play();
        		enemy.changeHP(-this.AttackDamage/2);
        		
    		}
    			
    			this.changeSHIELD(5);
    			currentWeapon = 4;
    		break;
    		
    	case 4:
    		//Soulfire - Soulless Strike
    		amber_attack4.play();
    		
    		double change = enemy.HP * 0.125;
    		change += this.AttackDamage/6;
    		enemy.changeHP(-change);
    		this.changeHP(change);
    		enemy.changeSTATUS(Status.Scarred);
    		
    			this.changeSHIELD(5);
    			currentWeapon = 1;
    		break;
    	}
    	
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			int temp = currentWeapon;
			attackAbility(enemy);
			currentWeapon = temp;
		}
		else {
			this.changeSHIELD(5);
			currentWeapon++;
			if(currentWeapon == 5)
				currentWeapon = 1;
			attackAbility(enemy);
		}	
		
	}

    public Brawler newInstance() {
        return new Amber(build);
    }

    public void reset() {
        this.HP = this.newInstance().HP;
        this.regen = this.newInstance().regen;
        this.SuperCharge = this.newInstance().SuperCharge;
        this.HyperCharge = this.newInstance().HyperCharge;
        this.gadgetCount = this.newInstance().gadgetCount;
        this.AttackDamage = this.newInstance().AttackDamage;
        this.SuperDamage = this.newInstance().SuperDamage;
        this.stat = this.newInstance().stat;
        this.potionCount = this.newInstance().potionCount;
        currentWeapon = 1;
    }

    public void changeATK(int x) {
        this.AttackDamage += x;
    }

    public void changeSUPATK(int x) {
        this.SuperDamage += x;
    }

    public void changeREGEN(int x) {
        this.regen += x;
    }

    public void changeGADCNT(int x) {
        this.gadgetCount += x;
    }

    public void changePOTCNT(int x) {
        this.potionCount += x;
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }

	@Override
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		sb.append("Amber is a fighter with a lot of cool weapons in her arsenal! "
				+ "She uses them in a perfect order! And sometimes, recklessly!");
		return sb.toString();
	}

}
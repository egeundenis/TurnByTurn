package Brawlers;

import java.util.Random;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Qirale extends Brawler {

	public int passive = 0;
	// 0 -> water, 1-> fire, 2 -> earth, 3 -> air
	
	SoundEffect qirale_passive = new SoundEffect("res/audio/qirale_passive.wav");
	
    SoundEffect qirale_normal_air = new SoundEffect("res/audio/qirale_air_attack.wav");
    SoundEffect qirale_normal_water = new SoundEffect("res/audio/qirale_water_attack.wav");
    SoundEffect qirale_normal_fire = new SoundEffect("res/audio/qirale_fire_attack.wav");
    SoundEffect qirale_normal_earth = new SoundEffect("res/audio/qirale_rock_attack.wav");
    
    SoundEffect qirale_super_air = new SoundEffect("res/audio/qirale_air_super.wav");
    SoundEffect qirale_super_water = new SoundEffect("res/audio/qirale_water_super.wav");
    SoundEffect qirale_super_fire = new SoundEffect("res/audio/qirale_fire_super.wav");
    SoundEffect qirale_super_earth = new SoundEffect("res/audio/qirale_rock_super.wav");
    
    public ImageIcon qirale_water = new ImageIcon("res/images/qirale_water.png");
    public ImageIcon qirale_air = new ImageIcon("res/images/qirale_air.png");
    public ImageIcon qirale_fire = new ImageIcon("res/images/qirale_fire.png");
    public ImageIcon qirale_earth = new ImageIcon("res/images/qirale_rock.png");
    public ImageIcon qirale_specific = new ImageIcon("res/images/qiraleSpecific.png");

    public Qirale(Build build) {
        super(build);
        this.build = build;
        this.name = "Qirale";
        this.HP = 550;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Elemental";
        this.spell = new Spell(build.spellChoise);
        this.hak = 10;

        if ("HP GEAR".equals(build.gearChoise))
            this.HP *= 1.1;
        if ("POTION GEAR".equals(build.gearChoise))
            this.potionCount += 1;
        if ("GADGET GEAR".equals(build.gearChoise))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    Random ran = new Random();
    
    @Override
    public boolean brawlerSpecificActivity(Brawler enemy) {
    	changeElement();
    	this.hak--;
    	return false;
    }
    
    public void changeElement() {
    	qirale_passive.play();
    	this.passive += 1;
    	if(this.passive == 4)
    		this.passive = 0;
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
    	
    	switch(passive) {
    	case 0:
    		qirale_normal_water.play();
    		enemy.changeHP(-this.AttackDamage*statper-5);
            this.changeCHARGE(this.AttackDamage*statper+5);
            this.changeHP(10);
    		break;
    	case 1:
    		qirale_normal_fire.play();
    		enemy.changeHP(-this.AttackDamage*statper-15);
            this.changeCHARGE(this.AttackDamage*statper+15);
            enemy.changeREGEN(-1);
    		break;
    	case 2:
    		qirale_normal_earth.play();
    		enemy.changeHP(-this.AttackDamage*statper-10);
            this.changeCHARGE((this.AttackDamage*statper+10)*1.5);
    		break;
    	case 3:
    		qirale_normal_air.play();
    		enemy.changeHP(-this.AttackDamage*statper);
            this.changeCHARGE(this.AttackDamage*statper);
            if(ran.nextInt(0,3) == 0) {
            	 try {Thread.sleep(300);} catch (InterruptedException e) {}
            	 attackAbility(enemy);
            }
            	
    		break;   				
    	}
    	
    }

    @Override
	public
    void superAbility(Brawler enemy) {
    	
    	enemy.changeHP(-30);

    	switch(passive) {
    	case 0:
    		qirale_super_water.play();
    		enemy.changeHP(-this.SuperDamage);
    		this.changeHP(this.SuperDamage);
    		break;
    	case 1:
    		qirale_super_fire.play();
    		enemy.changeHP(-this.SuperDamage);
    		enemy.changeREGEN(-this.SuperDamage/10);
    		break;
    	case 2:
    		qirale_super_earth.play();
    		enemy.changeHP(-this.SuperDamage);
    		this.changeSHIELD(this.SuperDamage*2);
    		break;
    	case 3:
    		qirale_super_air.play();
    		enemy.changeHP(-this.SuperDamage);
    		enemy.changeSTATUS(Status.Confused);
    		break;   				
    	}
    
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST")
			changeElement();
		else {
			switch(passive) {
	    	case 0:
	    		enemy.changeSTATUS(Status.Weakened);
	    		break;
	    	case 1:
	    		enemy.changeHP(-20);
	    		enemy.changeREGEN(-2);
	    		break;
	    	case 2:
	    		this.changeSHIELD(50);
	    		break;
	    	case 3:
	    		enemy.changeSTATUS(Status.Confused);
	    		break;   				
	    	}
		}
		
	}

    public Brawler newInstance() {
        return new Qirale(build);
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
        this.passive = 0;
        hak = 5;
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
        if (shield > 400) shield = 400;
    }

    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Qirale is a very skillful mage that was thaught to always pick an elemental side and perfect it. But,"
   				+ " there was no need for that! She was already perfect at everything!"); //here
   		return sb.toString();
   	}
	
}
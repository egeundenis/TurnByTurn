package Fighters;

import java.util.Random;
import javax.swing.ImageIcon;
import GUI.Utility;
import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Aboa extends Fighter {

	static SoundEffect aboa_reload = new SoundEffect("res/audio/aboa_reload.wav");
	static SoundEffect aboa_super = new SoundEffect("res/audio/aboa_super.wav");
	static SoundEffect aboa_fairyGain = new SoundEffect("res/audio/aboa_fairyGain.wav");
    static SoundEffect aboa_weaknormal = new SoundEffect("res/audio/aboa_weaknormal.wav");
    static SoundEffect aboa_strongnormal1 = new SoundEffect("res/audio/aboa_strongnormal1.wav");
    static SoundEffect aboa_strongnormal2 = new SoundEffect("res/audio/aboa_strongnormal2.wav");
    
    public ImageIcon aboa_specific = new ImageIcon("res/images/aboaSpecific.png");
    
    public int fairyCount = 10;

    public Aboa(Build build) {
        super(build);
        this.build = build;
        this.name = "Aboa";
        this.HP = 500;
        this.AttackDamage = 15;
        this.AttackCharge = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 20;
        this.regen = 0;
        this.passiveHypercharge = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Fairy Bladesman";
        this.spell = new Spell(build.spellChoise);
        this.hak = 1;

        if ("HP GEAR".equals(build.gearChoise))
            this.HP *= 1.1;
        if ("POTION GEAR".equals(build.gearChoise))
            this.potionCount += 1;
        if ("GADGET GEAR".equals(build.gearChoise))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }
    
    public void eachTurnChecks(Fighter enemy) {
    	
    	if(this.HP < 0) {
    		Utility.playThisTimes(aboa_super, fairyCount);
    		this.changeHP(fairyCount*10);
    		this.fairyCount = 0;
    	}
    	
    }
    
    @Override
    public boolean fighterSpecificActivity(Fighter enemy) {
    	
    	for(int i = 0; i < new Random().nextInt(4,8); i++) {
    		aboa_reload.play();
    		Utility.sleep(200);
        	aboa_fairyGain.play();
        	Utility.sleep(300);
        	this.fairyCount++;
        	this.changeHP(3);
        	
        	if(this.fairyCount > 19) {
        		return true;
        	}
        	
    	}
    	
    	return true;
   }

    
    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
    	if(this.fairyCount > 0) {
    		
    		if(new Random().nextBoolean())
    			aboa_strongnormal1.play();
    		else
    			aboa_strongnormal2.play();
    		
    		this.fairyCount--;
            enemy.changeHP(-this.AttackDamage*statper*3);   
            
    	} else {
    		aboa_weaknormal.play();
            enemy.changeHP(-this.AttackDamage*statper);
    	}
    	
    	this.changeCHARGE(this.AttackCharge);
         
    }
    
    @Override
	public void superAbility(Fighter enemy) {

    	Utility.playThisTimesWithSleep(aboa_super, fairyCount, 300);
    	this.changeHP(this.SuperDamage*this.fairyCount* (isHypercharged ? 1.5 : 1));
  	
    }
    
	@Override
	public
	void gadgetAbility(Fighter enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			
			if(this.fairyCount > 0) {
				this.fairyCount--;
				this.changeCHARGE(20);
			}
				
		}
		else {
			
			if(this.fairyCount > 0) {
				this.fairyCount--;
				this.changeSHIELD(40);
			}
			
		}
		
	}

    public Fighter newInstance() {
        return new Aboa(build);
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
        this.fairyCount = 10;
    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Aboa is a swordsman born alongside the fairies all around him! The fairies help him in any way: attack, defend and heal!"); //here
   		return sb.toString();
   	}


}
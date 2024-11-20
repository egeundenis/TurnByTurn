package Fighters;

import main.Build;
import main.SoundEffect;
import main.SoundManager;
import main.Spell;
import main.Status;

public class Light extends Fighter {

	SoundEffect light_attack = new SoundEffect("res/audio/light_attack.wav");  
    SoundEffect light_super = new SoundEffect("res/audio/light_super.wav");   

    public int lightInt = -1;
    
    public Light(Build build) {
        super(build);
        this.build = build;
        this.name = "Light";
        this.HP = 450;
        this.AttackDamage = 20;
        this.AttackCharge = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 5;
        this.regen = 5;
        this.gadgetCount = 3;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Unexplainable";
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
    
    public void eachTurnChecks(Fighter enemy) {
    	if(this.lightInt == 0) {
			SoundManager.light_superdeath.play();
			enemy.HP = -9999;
		}
		if(this.lightInt != -1) {
			this.lightInt--;
		}
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	light_attack.play();
    	
    	
        enemy.changeHP(-AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        this.changeGADCNT(1);
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        light_super.play();
        if(lightInt == -1) {
        	 lightInt = 30;
        }
        else {
        	this.changeHP(100);
        }
       
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

			if (this.build.gadgetChoise == "FIRST") {
				this.changeHP(10);
			}
			else {
				enemy.changeHP(-10);
			}
		
	}

    public Fighter newInstance() {
        return new Light(build);
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
        lightInt = -1;
    }

    public void changeGADCNT(int x) {
        this.gadgetCount += x;
        if(newInstance().gadgetCount < this.gadgetCount)
        	this.gadgetCount = newInstance().gadgetCount;

    }

    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Light is a weird fighter. He mostly keeps it to himself. But when he's ready..."
   				+ " the final blow will be just."); //here
   		return sb.toString();
   	}
	
}
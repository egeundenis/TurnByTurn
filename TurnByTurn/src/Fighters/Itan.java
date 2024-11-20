package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Itan extends Fighter {
	
	public int BeurcHP = 0;
	public static SoundEffect itan_super_attack = new SoundEffect("res/audio/itan_super_attack.wav");
	public SoundEffect itan_super_call = new SoundEffect("res/audio/itan_super_call.wav");
	public SoundEffect itan_attack = new SoundEffect("res/audio/itan_attack.wav");

    public Itan(Build build) {
        super(build);
        this.build = build;
        this.name = "Itan";
        this.HP = 400;
        this.AttackDamage = 15;
        this.AttackCharge = 10;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 20;
        this.regen = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Summoner";
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
    	
    	if(this.BeurcHP > 0) {
			Itan.itan_super_attack.play();
			enemy.changeHP(-this.SuperDamage);
			this.changeHP(5);
		}
    	
    }

    @Override
	public void attackAbility(Fighter enemy) {
    	
    	double statper = per();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        itan_attack.play();
        
        if(BeurcHP > 0) 
        	BeurcHP += 5;
        if(BeurcHP > 200)
        	BeurcHP = 200;
        	
             
    }

    @Override
	public void superAbility(Fighter enemy) {
        BeurcHP = 200;
        itan_super_call.play();
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			enemy.changeSTATUS(Status.Weakened);
			if(BeurcHP > 0)
				enemy.changeSTATUS(Status.Stunned);
		}
		else {
			this.changeHP(25);
			if(BeurcHP > 0)
				if(BeurcHP > 0) {
					this.changeHP(25);
					BeurcHP += 50;
					if(BeurcHP > 200)
			        	BeurcHP = 200;
				}
					
		}
		
    }

    public Fighter newInstance() {
        return new Itan(build);
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
    }
    
    public void changeHP(double x) {

        if (x < 0 && shield != 0) {
            shield += x;
            x = 0;
        }

        if (shield < 0) {
            x = shield;
            shield = 0;
        }

        if (x > 0) {
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1)* (this.stat == Status.Poisoned ? 0.5 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else {
            	if(BeurcHP > 0) {
            		BeurcHP += (int) (x/2);
            		this.HP += (int) (x/2);
            	} else           		
            		this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
            }
                
        }
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
   		sb.append("Itan is a naturalist that can summon his animal friend to help him in battle! He himself can't really do much though..."); //here
   		return sb.toString();
   	}
}
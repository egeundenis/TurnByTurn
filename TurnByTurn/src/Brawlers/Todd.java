package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.SoundManager;
import main.Spell;
import main.Status;

public class Todd extends Brawler {

    SoundEffect todd_normal = new SoundEffect("res/audio/todd_normal.wav");
    SoundEffect todd_super = new SoundEffect("res/audio/todd_super.wav");
    
    public int superTurns = -1;

    public Todd(Build build) {
        super(build);
        this.build = build;
        this.name = "Todd";
        this.HP = 700;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 30;
        this.regen = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 100;
        this.stat = Status.Normal;
        this.title = "the Tanker";
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

    Random ran = new Random();
    
    public void eachTurnChecks(Brawler enemy) {
    	
    	if(this.superTurns == 0) {					
			enemy.changeSTATUS(Status.Stunned);
			enemy.changeHP(-this.SuperDamage);
			SoundManager.shieldbash.play();
		}			
		if(this.superTurns >= 0)
			this.superTurns--;		
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
    	todd_normal.play();
        enemy.changeHP(-this.AttackDamage*statper*(this.superTurns > 0 ? 0.5 : 1)* ( (this.superTurns > 0 && this.isHypercharged) ? 2 : 1));
        this.changeCHARGE(this.AttackDamage*statper*(this.superTurns > 0 ? 0.5 : 1)* ( (this.superTurns > 0 && this.isHypercharged) ? 2 : 1));
        
    }

    @Override
	public
    void superAbility(Brawler enemy) {

    	SoundManager.guarded.play();
    	this.superTurns = 6 + (this.isHypercharged ? 3 : 0);
    	

    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST")
			this.changeREGEN(3);
		else {
			this.changeATK(3);
			this.changeSTATUS(Status.Enraged);
		}
		
	}

    public Brawler newInstance() {
        return new Todd(build);
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
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1) * (this.superTurns > 0 ? 0.5 : 1) * ( (this.superTurns > 0 && this.isHypercharged) ? 0.5 : 1) );
        }
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
        this.shield += 100;
        this.superTurns = -1;
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
   		sb.append("This trusty brawler is called Todd. He's the most resistant man out here! Just sit behind him and"
   				+ " watch as he takes on an army by himself!"); //here
   		return sb.toString();
   	}
	
}
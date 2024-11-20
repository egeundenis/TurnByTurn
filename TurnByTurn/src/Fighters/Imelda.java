package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Imelda extends Fighter {
	
	SoundEffect imelda_attack1 = new SoundEffect("res/audio/imelda_attack1.wav");
	SoundEffect imelda_attack2 = new SoundEffect("res/audio/imelda_attack2.wav");
	SoundEffect imelda_attack3 = new SoundEffect("res/audio/imelda_attack3.wav");
	SoundEffect imelda_attack4 = new SoundEffect("res/audio/imelda_attack4.wav");
	SoundEffect imelda_super = new SoundEffect("res/audio/imelda_super.wav");

    public Imelda(Build build) {
        super(build);
        this.build = build;
        this.name = "Imelda";
        this.HP = 650;
        this.AttackDamage = 20;
        this.AttackCharge = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Determined";
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
    void attackAbility(Fighter enemy) {
    	
    	if(this.AttackDamage > 120) {
    		imelda_attack4.play();
    	} else if (this.AttackDamage > 80) {
    		imelda_attack3.play();
    	}else if (this.AttackDamage > 50) {
    		imelda_attack2.play();
    	}else if (this.AttackDamage > 10) {
    		imelda_attack1.play();
    	}
    	
    	double statper = per();
    	
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
    }

    @Override
	public
    void superAbility(Fighter enemy) {
    	imelda_super.play(); 
        int lostHP = (int) (this.HP * 0.1);
        this.changeHP(lostHP * -2);
        this.changeATK((int) (lostHP / 1.6));
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			int lostHP = (int) (this.HP * 0.02);
			this.changeHP(lostHP*-2);
			this.changeATK(lostHP/2);
		}
		else {
			int lostHP = (int) (this.HP * 0.02);
			this.changeHP(lostHP*-2);
			this.changeREGEN(lostHP/2);
		}	
	}

    public Fighter newInstance() {
        return new Imelda(build);
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
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1) * (this.stat == Status.Poisoned ? 0.5 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
        }

        if (this.HP < newInstance().HP * 0.45)
            this.stat = Status.Enraged;
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

    public void changeSTATUS(Status x) {
        if (this.stat != Status.Enraged || x == Status.Enraged)
            this.stat = x;
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		 sb.append("This warior is not here to play around! Her anger is channeling through his veins "
   		 		+ " and cutting them deep. But, her muscles are getting stronger and stronger!"); //here
   		return sb.toString();
   	}

}
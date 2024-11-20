package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Ignace extends Fighter {

    static SoundEffect ignace_attack = new SoundEffect("res/audio/ignace_attack.wav");
    static SoundEffect ignace_super = new SoundEffect("res/audio/ignace_super.wav");

    public Ignace(Build build) {
        super(build);
        this.build = build;
        this.name = "Ignace";
        this.HP = 500;
        this.AttackDamage = 25;
        this.AttackCharge = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 60;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Scorched";
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
    	
    	double statper = per();
    	
        ignace_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        enemy.changeSTATUS(Status.Weakened);
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        ignace_super.play();
        enemy.changeHP(-this.SuperDamage);
        enemy.changeREGEN(-5);
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

		if (this.build.gadgetChoise == "FIRST") {
				enemy.changeHP(-10);
		if(enemy.stat == Status.Weakened) {
				enemy.changeHP(-30);
				enemy.changeSTATUS(Status.Stunned);
			}
		}
		else 
			enemy.changeREGEN(-3);
		if(enemy.stat == Status.Weakened) {
				enemy.changeREGEN(-3);
				enemy.changeSTATUS(Status.Stunned);
			}
			
	}

    public Fighter newInstance() {
        return new Ignace(build);
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
   		 sb.append("This fireborn will tire you and burn you until you're dead! How hot... TOO HOT!"); //here
   		return sb.toString();
   	}
	
}
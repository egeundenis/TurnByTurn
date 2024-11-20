package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Missy extends Fighter{

	static SoundEffect missy_normal = new SoundEffect("res/audio/missy_attack.wav");
	static SoundEffect missy_super = new SoundEffect("res/audio/missy_super.wav");
	
	public Missy(Build build) {
        super(build);
        this.build = build;
        this.name = "Missy";
        this.HP = 425;
        this.AttackDamage = 20;
        this.AttackCharge = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 10;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Regener";
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
    	
		if(this.regen < 0)
			this.changeREGEN(1);
    	
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
        missy_normal.play();
        enemy.changeHP(-AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        missy_super.play();
		this.changeREGEN( (int) (this.SuperDamage * (isHypercharged ? 1.25 : 1)) );
		this.changeATK( (int) (this.regen * 0.10 * (isHypercharged ? 1.25 : 1) ));
    }

    @Override
	public
	void gadgetAbility(Fighter enemy) {
		
		if (this.build.gadgetChoise == "FIRST") 
			this.changeREGEN(4);
		else
			enemy.changeHP(this.regen * -2);
		
	}
    
	public Fighter newInstance() {
		return new Missy(build);
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
	
	public void changeATK(int x) {this.AttackDamage += x;}
	public void changeSUPATK(int x) {this.SuperDamage += x;}
	public void changeREGEN(int x) {this.regen += x;}
	public void changeGADCNT(int x) {this.gadgetCount += x;}
	public void changePOTCNT(int x) {this.potionCount += x;}
	public void changeSHIELD(int x) {this.shield += x; if(shield > 400) shield = 400;}

	@Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Missy is a cute girl in a cute world where nothing goes wrong!"
   				+ " Her energetic nature gives her an swift regeneration!"); //here
   		return sb.toString();
   	}
	
	
}

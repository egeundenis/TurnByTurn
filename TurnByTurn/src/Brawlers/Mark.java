package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Mark extends Brawler {
	
	SoundEffect mark_attack = new SoundEffect("res/audio/mark_attack.wav");
    SoundEffect mark_super = new SoundEffect("res/audio/mark_super.wav");

	public Mark(Build build) {
        super(build);
        this.build = build;
        this.name = "Mark";
        this.HP = 500;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 40;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Bloodlover";
        this.spell = new Spell(build.spellChoise);

        if ("HP GEAR".equals(build.gearChoise))
            this.HP *= 1.1;
        if ("POTION GEAR".equals(build.gearChoise))
            this.potionCount += 1;
        if ("GADGET GEAR".equals(build.gearChoise))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.changeREGEN(3);
    }
	
	public void eachTurnChecks(Brawler enemy) {
    	
		this.changeREGEN(-1);
    	
    }
	
	@Override
	public
    void attackAbility(Brawler enemy) {
		
		double statper = per();
		
        mark_attack.play();
        enemy.changeHP(-AttackDamage*statper);
        this.changeCHARGE(AttackDamage*statper);
        this.changeHP(AttackDamage*statper * (enemy.stat == Status.Scarred ? 1.25 : 1));
        
        if(enemy.stat == Status.Scarred) {
        	enemy.changeHP(-10);
            this.changeCHARGE(10);
            this.changeHP(12);
        }
        
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        mark_super.play();
        
		this.changeHP(this.SuperDamage * (enemy.stat == Status.Scarred ? 1.25 : 1));
		
		enemy.changeHP(-this.SuperDamage);
		enemy.changeSTATUS(Status.Scarred);
		
		this.changeATK(5);
		this.changeSUPATK(10);

    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST") {
			this.changeHP(50);
			this.changeREGEN(-3);
		}
		else {
			enemy.changeHP(-30);
			enemy.changeREGEN(-3);
			enemy.changeSTATUS(Status.Scarred);
		}

    }

    public Brawler newInstance() {
        return new Mark(build);
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
    	
    	if(x < 0) {
    		this.regen += x;
    		this.changeATK(-x);
    		this.changeSUPATK(-x);
    	} else {
    		this.changeATK(x);
    		this.changeSUPATK(x);
    	}
    		
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
   		sb.append("Mark is a vampire. BORING! He sucks blood out of his enemies to get stronger, yet more unstable! But"
   				+ " He can not stop bleeding himself... It feels so good!"); //here
   		return sb.toString();
   	}
}
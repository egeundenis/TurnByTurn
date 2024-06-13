package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Olea extends Brawler {
	
	SoundEffect olea_attack = new SoundEffect("res/audio/olea_attack.wav");
	SoundEffect olea_attack_strong = new SoundEffect("res/audio/olea_attack_strong.wav");
	SoundEffect olea_super = new SoundEffect("res/audio/olea_super.wav");
	
	public int superTurns = 0;

    public Olea(Build build) {
        super(build);
        this.build = build;
        this.name = "Olea";
        this.HP = 400;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 25;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Toxicating";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise == "HP GEAR")
            this.HP *= 1.1;
        if (build.gearChoise == "POTION GEAR")
            this.potionCount += 1;
        if (build.gearChoise == "GADGET GEAR")
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
        
    }

    @Override
	public void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
    	if(superTurns < 1) {
    		olea_attack.play();
    		enemy.changeHP(-this.AttackDamage*statper);
        	this.changeCHARGE(statper*this.AttackDamage);
    	} else {
    		olea_attack_strong.play();
    		enemy.changeHP(-this.AttackDamage*statper-10);
        	this.changeCHARGE(statper*this.AttackDamage+10);
    	}   	
     
    }

    @Override
	public void superAbility(Brawler enemy) {
    	olea_super.play();
    	this.superTurns += 5 + (this.isHypercharged ? 1 : 0);
        
    }
    
    @Override
	public
    void gadgetAbility(Brawler enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			this.changeHP(30);
			if(this.stat == Status.Intoxicated)
				this.changeHP(30);
		} else {
				this.superTurns += 2;		
			} 
		
    }
    
    public Brawler newInstance() {
        return new Olea(build);
    }

    public void reset() {
        this.HP = this.newInstance().HP;
        this.regen = this.newInstance().regen;
        this.SuperCharge = this.newInstance().SuperCharge;
        this.gadgetCount = this.newInstance().gadgetCount;
        this.AttackDamage = this.newInstance().AttackDamage;
        this.SuperDamage = this.newInstance().SuperDamage;
        this.HyperCharge = this.newInstance().HyperCharge;
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
            this.HP += (int) (x * (this.build.gearChoise == "HEAL GEAR" ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
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
		 sb.append("Olea is an annoying archer that's hated by all! He loves all things TOXIC and DRAMA! He brings them wherever he is and "
		 		+ "He'd love to be in all!");    
		return sb.toString();
	}
}
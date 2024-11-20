package Fighters;

import java.util.ArrayList;
import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

class Poison {
	
	int duration;
	double damage;
	Fighter enemy;
	
	Poison(int duration, double damage, Fighter enemy) {
		this.duration = duration;
		this.damage = damage;
		this.enemy = enemy;
	}
	
}

public class Gash extends Fighter {

    SoundEffect gash_attack = new SoundEffect("res/audio/gash_attack.wav");
    SoundEffect gash_poison = new SoundEffect("res/audio/gash_poison.wav");
    SoundEffect gash_super = new SoundEffect("res/audio/gash_super.wav");
    
    public ArrayList<Poison> poisons = new ArrayList<Poison>();

    public Gash(Build build) {
        super(build);
        this.build = build;
        this.name = "Gash";
        this.HP = 400;
        this.AttackDamage = 5;
        this.AttackCharge = 5;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Unbalanced";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise.equals("HP GEAR"))
            this.HP *= 1.1;
        if (build.gearChoise.equals("POTION GEAR"))
            this.potionCount += 1;
        if (build.gearChoise.equals("GADGET GEAR"))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }
    
    public void eachTurnChecks(Fighter enemy) {
    	
    	this.inflictPoison(enemy);
    	
    	if(this.HP < 0) {
			enemy.changeHP(-this.poisons.size()*20);
			this.poisons.removeAll(poisons);
		}
    }
    
    public void inflictPoison(Fighter enemy) {
    	if(!this.poisons.isEmpty()) {
    		
    		ArrayList<Poison> removals = new ArrayList<Poison>();
    		
    		for(Poison p : this.poisons) {
    			
    			if(p.duration > 0) {
    				p.enemy.changeHP(-p.damage);
    				this.changeCHARGE(this.AttackCharge);   				
    				p.duration--;
    			}
    			
    			if(p.duration == 0)
    				removals.add(p);
    			
    		}
    		
    		for(Poison p : removals) {
    			gash_poison.play();
    			this.poisons.remove(p);
    		}
    		
    		
    	}
    	
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
        gash_attack.play();
        
        Poison p = new Poison(10 + (this.isNormalModified ? 10 : 0), this.AttackDamage*statper, enemy);
        this.isNormalModified = false;
        poisons.add(p);

    }

    @Override
	public
    void superAbility(Fighter enemy) {

    	gash_super.play();
    	this.changeHP(this.SuperDamage);
    	enemy.changeHP(-this.SuperDamage);
    	
    	if(this.isHypercharged) {
    		this.changeSTATUS(Status.Strengthened);
    		enemy.changeSTATUS(Status.Weakened);
    	}
    	
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
			for(Poison p : this.poisons) {
				p.duration += 3;
			}
		}
		else {
			this.isNormalModified = true;
		}
		
	}

    public Fighter newInstance() {
        return new Gash(build);
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
        this.poisons.removeAll(poisons);
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
		 sb.append("Gash is annoying little turd that poisons others! He likes to annoy them with his slowburning poisons that he himself love"
		 		+ " to inhale... What a weirdo."); //here
		return sb.toString();
	}
	
}
package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.SoundManager;
import main.Spell;
import main.Status;

public class Ritz extends Brawler {

    SoundEffect ritz_attack = new SoundEffect("res/audio/ritz_attack.wav");
    SoundEffect ritz_attack_strong = new SoundEffect("res/audio/ritz_attack_strong.wav");
    SoundEffect ritz_super = new SoundEffect("res/audio/ritz_super.wav");
    
    public int passive = -1;
    
    public Ritz(Build build) {
        super(build);
        this.build = build;
        this.name = "Ritz";
        this.HP = 750;
        this.AttackDamage = 30;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 3;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Uncontrollable";
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
    
    public void eachTurnChecks(Brawler enemy) {
    	
    	if(this.passive >= 0)
			this.passive--;			
    	
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
    	if(this.passive > 0)
    		ritz_attack_strong.play();
    	else
    		ritz_attack.play();
    	
        enemy.changeHP(-this.AttackDamage*statper * (this.passive > 0 ? 2 : 1));
        this.changeCHARGE(this.AttackDamage*statper);
        
        if(new Random().nextBoolean() && new Random().nextBoolean()) {
        	this.changeSTATUS(Status.Enraged);
        }
        
    }

    @Override
	public
    void superAbility(Brawler enemy) {
    	
    	this.passive += 10 + (this.isHypercharged ? 5 : 0);		
    	ritz_super.play();
        
    }

    @Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST")
			if(this.passive > 0)
				this.passive += 3;
		else {
			this.changeSTATUS(Status.Enraged);
		}
    
    }
    
    public Brawler newInstance() {
        return new Ritz(build);
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
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1)  * (this.passive > 0 ? 0.5 : 1));
        }
    }
    
    public void changeSTATUS(Status x) {
    	  
    	if(x.isNegative(x))
    		if(this.passive > 0) {
    			if(x == Status.Stunned) {
    				this.passive = -1;
    			}
    			else 
    				return;
    		}
    			
    		  	
        this.stat = x;
    
     }
    
    public void changeCHARGE(double x) {
    	
    	if(this.passive > 0 && x > 0)
    		return;

    	if (this.SuperCharge - x < 100 && this.SuperCharge + x > 99 && this.SuperCharge < 100)
            SoundManager.charged.play();

        this.SuperCharge += x;
        if (this.SuperCharge > 200)
            this.SuperCharge = 200;
        if (this.SuperCharge < 0)
            this.SuperCharge = 0;

        if (x > 0)
            changeHYPERCHARGE(x / 3);

    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Ritz is a member of the Ursinelikes! If he becomes angry he becomes VERY strong, yet very fragile! So don't make him angry!"); //here
   		return sb.toString();
   	}
}
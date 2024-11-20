package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Anvaa extends Fighter {

    int PassiveCount = 1;
    SoundEffect anvaa_attack = new SoundEffect("res/audio/anvaa_attack.wav");
    SoundEffect anvaa_super = new SoundEffect("res/audio/anvaa_super.wav");
    SoundEffect anvaa_atkstrong = new SoundEffect("res/audio/anvaa_strongatk.wav");

    public Anvaa(Build build) {
        super(build);
        this.build = build;
        this.name = "Anvaa";
        this.HP = 525;
        this.AttackDamage = 30;
        this.AttackCharge = 30;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Frostborn";
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
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per(); 
    	
        anvaa_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);

        // Normal's passive
        if (enemy.stat == Status.Frosty) {
            anvaa_atkstrong.play();
            enemy.changeHP(-20);
            this.changeHP(20);
        }
        
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        anvaa_super.play();

        enemy.changeHP(-this.SuperDamage);
        if (enemy.stat == Status.Frosty) {
            enemy.changeHP(-this.SuperDamage);
        }
        enemy.changeSTATUS(Status.Frosty);
    }
    
    @Override
	public
    void gadgetAbility(Fighter enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			if(enemy.stat == Status.Frosty) {
				enemy.changeSTATUS(Status.Stunned);
				enemy.changeHP(-15);
		}
			}
		
		else {
			this.changeSTATUS(Status.Frosty);
			this.changeHP(50);
		}
    } 
    
    public Fighter newInstance() {
        return new Anvaa(build);
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
        PassiveCount = 1;
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
            this.HP += (int) (x * (this.build.gearChoise == "HEAL GEAR" ? 1.2 : 1) * (this.stat == Status.Poisoned ? 0.5 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
        }

        if (this.HP < 0 && PassiveCount == 1) {
            this.HP = 30;
            this.shield = 100;
            this.stat = Status.Stunned;
            PassiveCount--;
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
		 sb.append("Anvaa is a very chill fighter... Really! She can summon snow storms that makes the"
		 		+ " enemy FROZEN! Her abilities are stronger against frozen enemies too!"); //here
		return sb.toString();
	}
}
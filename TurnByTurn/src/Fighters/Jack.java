package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;
import main.Trait;

public class Jack extends Fighter {

    SoundEffect jack_attack = new SoundEffect("res/audio/jack_normal.wav");
    SoundEffect jack_super = new SoundEffect("res/audio/jack_super.wav");
    SoundEffect jack_passive_1 = new SoundEffect("res/audio/jack_passive_1.wav");
    SoundEffect jack_passive_2 = new SoundEffect("res/audio/jack_passive_2.wav");
    SoundEffect jack_passive_3 = new SoundEffect("res/audio/jack_passive_3.wav");
    
    public int passive = 10;

    public Jack(Build build) {
        super(build);
        this.build = build;
        this.name = "Jack";
        this.HP = 1000;
        this.AttackDamage = 5;
        this.AttackCharge = 5;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Prickly";
        this.spell = new Spell(build.spellChoise);
        this.trait = Trait.Tank;
        this.tankTraitRatio = 0.50;

        if (build.gearChoise.equals("HP GEAR"))
            this.HP *= 1.1;
        if (build.gearChoise.equals("POTION GEAR"))
            this.potionCount += 1;
        if (build.gearChoise.equals("GADGET GEAR"))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }
    
    void throwPrick() {
    	this.enemy.changeHP(-this.passive);
    	
    	try {Thread.sleep(150);} catch (InterruptedException e) {}
    	
    	if(this.passive < 31)
    		jack_passive_1.play();
    	else if (this.passive < 51)
    		jack_passive_2.play();
    	else if (this.passive > 51)
    		jack_passive_3.play();
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	jack_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
    }

    @Override
	public
    void superAbility(Fighter enemy) {

    	jack_super.play();
    	this.passive += 10;
    	
    	if(this.isHypercharged) {
    		throwPrick();
    		throwPrick(); 
    	}
    	
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
			throwPrick();
		}
		else {
			this.passive -= 5;
			this.changeHP(50);
		}
		
	}

    public Fighter newInstance() {
        return new Jack(build);
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
        this.passive = 10;
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
            	
            	this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
            	if(this.trait == Trait.Tank)
            		this.changeCHARGE(-x*tankTraitRatio);
            	throwPrick();
            	
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
		 sb.append("Jack is a stingy little dude that does not like hugs at all! He can not attack properly and when "
		 		+ " he feels threatened he activates his defense mechanism of releasing his stingers!"); //here
		return sb.toString();
	}
	
}
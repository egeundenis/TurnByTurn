package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Vollie extends Brawler {

    SoundEffect stack1 = new SoundEffect("res/audio/1stacks.wav");
    SoundEffect stack2 = new SoundEffect("res/audio/2stacks.wav");
    SoundEffect stack3 = new SoundEffect("res/audio/3stacks.wav");
    SoundEffect stack4 = new SoundEffect("res/audio/4stacks.wav");
    SoundEffect stack5 = new SoundEffect("res/audio/5stacks.wav");
    SoundEffect vollie_attack = new SoundEffect("res/audio/vollie_attack.wav");
    SoundEffect vollie_super = new SoundEffect("res/audio/vollie_super.wav");
    public int runicStacks = 0;

    public Vollie(Build build) {
        super(build);
        this.build = build;
        this.name = "Vollie";
        this.HP = 750;
        this.AttackDamage = 10;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 40;
        this.regen = 5;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.stat = Status.Normal;
        this.title = "the Wildclaw";
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
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
        vollie_attack.play();
        switch (runicStacks) {
            case 0:
                stack1.play();
                break;
            case 1:
                stack2.play();
                break;
            case 2:
                stack3.play();
                break;
            case 3:
                stack4.play();
                break;
            case 4:
                stack5.play();
                break;
        }

        if (runicStacks > 5)
            runicStacks = 5;

        int damage = this.AttackDamage + (5 * runicStacks++);
        enemy.changeHP(-damage*statper);
        this.changeCHARGE(damage*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        vollie_super.play();
        int damage = this.SuperDamage + (5 * runicStacks);
        enemy.changeHP(-damage);
        if (enemy.stat == Status.Scarred)
            this.changeHP(damage);
        else
            enemy.changeSTATUS(Status.Scarred);
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			enemy.changeHP(-20);
			enemy.changeSTATUS(Status.Scarred);
		}
		else {
			enemy.changeHP(-60);
		}	
	}

    public Brawler newInstance() {
        return new Vollie(build);
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
        runicStacks = 0;
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
            this.HP += (int) (x * ("HEAL GEAR".equals(this.build.gearChoise) ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else {
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
            }
        }

        if (x < 0)
            changeSHIELD((int) (x * -0.15) * runicStacks);

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
        this.stat = x;
        if (x == Status.Stunned)
            runicStacks = 0;
    }

    public void changeSHIELD(int x) {
    	this.shield += x; 
    	if(shield > 400) 
    		shield = 400;
    	
    }
    
	int getStacks() {
		return runicStacks;	
	}

	@Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Vollie is a snow leo. Of course He really cares about his momentum in battle! "
   				+ "As he's fighting he can not get distracted, or he will lose his power, resistance and dignity!"); //here
   		return sb.toString();
   	}
	
}

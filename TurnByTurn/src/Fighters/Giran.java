package Fighters;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Giran extends Fighter {

    static SoundEffect giran_attack = new SoundEffect("res/audio/giran_attack.wav");
    static SoundEffect giran_super = new SoundEffect("res/audio/giran_super.wav");
    static SoundEffect giran_passive = new SoundEffect("res/audio/giran_passive.wav");
    int stacks = 0;
    public int superstack = 1;

    public Giran(Build build) {
        super(build);
        this.build = build;
        this.name = "Giran";
        this.HP = 700;
        this.AttackDamage = 15;
        this.AttackCharge = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 40;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Pesky";
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

        giran_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);

        if (new Random().nextInt(0, 2) == 0)
            this.changeCHARGE(this.AttackCharge);

        if (stacks == 2) {
            stacks = 0;
            giran_passive.play();
            giran_passive.play();
            attackAbility(enemy);
            attackAbility(enemy);
            stacks = 0;
        }
        stacks++;

    }

    @Override
	public
    void superAbility(Fighter enemy) {

        for (int i = 0; i < superstack; i++) {
            giran_super.play();
            enemy.changeHP(-this.SuperDamage * (isHypercharged ? (1 + ( (i+1)/10 ) ) : 1));
            
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
            }
        }
        superstack++;
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			this.changeHP(50);
		}
		else {
			for(int i = 0; i < 2; i++) {
				try {Thread.sleep(525);} catch(InterruptedException e){}
				attackAbility(enemy);					
			}
		}

	}

    public Fighter newInstance() {
        return new Giran(build);
    }

    public void reset() {
        stacks = 0;
        superstack = 1;
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
		 sb.append("The pesky bird has escaped the golden cage! As he smashes the enemy with his wand, "
		 		+ "he will not hold back on the explosives!"); //here
		return sb.toString();
	}

	
}
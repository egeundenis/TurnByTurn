package Fighters;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Susan extends Fighter {

    SoundEffect susan_attack1 = new SoundEffect("res/audio/susan_attack1.wav");
    SoundEffect susan_attack2 = new SoundEffect("res/audio/susan_attack2.wav");
    SoundEffect susan_attack3 = new SoundEffect("res/audio/susan_attack3.wav");
    SoundEffect susan_super = new SoundEffect("res/audio/susan_super.wav");
    Random ran = new Random();

    public Susan(Build build) {
        super(build);
        this.build = build;
        this.name = "Susan";
        this.HP = 525;
        this.AttackDamage = 30;
        this.AttackCharge = 30;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 30;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Stunner";
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

        switch (ran.nextInt(0, 3)) {
            case 0:
                susan_attack1.play();
                break;
            case 1:
                susan_attack2.play();
                break;
            case 2:
                susan_attack3.play();
                break;
        }
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        
        //passive
        if( Status.isNegative(enemy.stat) || enemy.stat == Status.Stunned){
			enemy.changeHP(-5*statper);
		}
        
    }

    @Override
	public
    void superAbility(Fighter enemy) {

        switch (ran.nextInt(0, 3)) {
            case 0:
                susan_attack1.play();
                break;
            case 1:
                susan_attack2.play();
                break;
            case 2:
                susan_attack3.play();
                break;
        }
        susan_super.play();
        enemy.changeSTATUS(Status.Stunned);
        enemy.changeHP(-this.SuperDamage * (this.isHypercharged ? 2 : 1));
        
        if(this.isHypercharged) {
        	try {Thread.sleep(400);} catch (InterruptedException e) {}
        	switch (ran.nextInt(0, 3)) {
            case 0:
                susan_attack1.play();
                break;
            case 1:
                susan_attack2.play();
                break;
            case 2:
                susan_attack3.play();
                break;
        	}
        	susan_super.play();
        }
        
    }

    @Override
	public
	void gadgetAbility(Fighter enemy) {
		if (this.build.gadgetChoise == "FIRST")
			this.changeHP(50);
		else {
			enemy.changeHP(-this.AttackDamage*1.5);
		}
    
    }
    
    public Fighter newInstance() {
        return new Susan(build);
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
   		sb.append("Susan is a fighter that trained her legs beyond the possible limit. She will not"
   				+ " hesitate to kick you in the head and leave you squirming on the ground."); //here
   		return sb.toString();
   	}
}
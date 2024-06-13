package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Simon extends Brawler {

    Random ran = new Random();
    SoundEffect simon_attack = new SoundEffect("res/audio/simon_attack.wav");
    SoundEffect simon_super = new SoundEffect("res/audio/simon_super.wav");

    public Simon(Build build) {
        super(build);
        this.build = build;
        this.name = "Simon";
        this.HP = 550;
        this.AttackDamage = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 20;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 2;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Sorcerer";
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

        simon_attack.play();
        double damage = ran.nextInt(15, 35) * statper;

        if (damage > 14 && damage < 21)
            this.changeCHARGE(damage);
        if (damage > 20 && damage < 26)
            this.changeREGEN(1);
        if (damage > 25 && damage < 31)
            enemy.changeCHARGE((int) (damage / -3));
        if (damage > 31 && damage < 36)
            this.changeHP(damage);

        enemy.changeHP(-damage);
        this.changeCHARGE(damage);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        simon_super.play();
        enemy.changeSTATUS(Status.Stunned);
        enemy.changeHP(-this.SuperDamage);
    }

	@Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST") {
		this.changePOTCNT(1);
		
		}
		else {
			enemy.changeHP(-30);
			enemy.changeREGEN(-3);
		}
		
	}
    
    public Brawler newInstance() {
        return new Simon(build);
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

    public void changeSTATUS(Status x) {
        Status stat = Status.Normal;
        if (!stat.isNegative(x))
            this.stat = x;
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Simon is a wizard that fell into an ice cold black magic. He will freeze everyone until... "
   				+ "Until he finds his wife. His wife..?"); //here
   		return sb.toString();
   	}


}
package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Hassan extends Brawler {

    static SoundEffect hassan_attack = new SoundEffect("res/audio/hassan_attack.wav");
    static SoundEffect hassan_super = new SoundEffect("res/audio/hassan_super.wav");

    public Hassan(Build build) {
        super(build);
        this.build = build;
        this.name = "Hassan";
        this.HP = 600;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Brutal";
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
    	
        hassan_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);

        int hassandmg = (int) ((enemy.newInstance().HP - enemy.HP) * 0.095);
        this.changeSHIELD((int) (hassandmg * 0.80));
        enemy.changeHP(-hassandmg*statper);
        this.changeCHARGE(hassandmg*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        hassan_super.play();
        enemy.changeHP(-(this.SuperDamage + ((int) (enemy.HP * 0.1))));
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST") 
			enemy.changeREGEN(-7);
		else 
			this.changeREGEN(7);
		
	}

    public Brawler newInstance() {
        return new Hassan(build);
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
		 sb.append("Hassan may not be handsome, but he for sure is brutal! His dual weapon was "
		 		+ "carefully crafted by the most skillful engineers! Wait, what's that on his head?"); //here
		return sb.toString();
	}

	
}
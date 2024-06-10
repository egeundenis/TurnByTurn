package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Finn extends Brawler {

    static SoundEffect finn_attack = new SoundEffect("res/audio/finn_attack.wav");
    static SoundEffect finn_super = new SoundEffect("res/audio/finn_super.wav");
    int attackCount = 0;
    SoundEffect finn_passive = new SoundEffect("res/audio/finn_passive.wav");

    public Finn(Build build) {
        super(build);
        this.build = build;
        this.name = "Finn";
        this.HP = 600;
        this.AttackDamage = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 5;
        this.gadgetCount = 3;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Soul Stealer";
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
    	
        finn_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);

        attackCount++;
        if (attackCount == 3) {
            this.changeHP(50);
            attackCount = 0;
            finn_passive.play();
        }
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        finn_super.play();

        int stolenAD = (int) ((enemy.AttackDamage) * 0.20);
        this.changeATK(stolenAD);
        enemy.changeATK(stolenAD * -1);
        int stolenReg = (int) (enemy.regen * 0.25);
        this.changeREGEN(stolenReg);
        enemy.changeREGEN(-stolenReg);
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			int stolenHP = (int)( enemy.HP * 0.10);
			this.changeHP(stolenHP);
			enemy.changeHP(-stolenHP);	
		}
		else {
			int stolenCharge = (int) (enemy.SuperCharge * 0.15);
			this.changeCHARGE(stolenCharge);
			enemy.changeCHARGE(-stolenCharge);
		}
    }

    public Brawler newInstance() {
        return new Finn(build);
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
		 sb.append("Finn the ghoul is no joke. As he terrifies the enemy with his slaves, "
		 		+ "He will make sure the enemy will no longer be the same after him."); //here
		return sb.toString();
	}
}
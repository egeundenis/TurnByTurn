package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Kasse extends Brawler {

    static SoundEffect kasse_normal = new SoundEffect("res/audio/kasse_attack.wav");
    static SoundEffect kasse_super = new SoundEffect("res/audio/kasse_super.wav");

    public Kasse(Build build) {
        super(build);
        this.build = build;
        this.name = "Kasse";
        this.HP = 425;
        this.AttackDamage = 5;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 99999;
        this.regen = 10;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Ender";
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
    	
        kasse_normal.play();
        int missing = this.newInstance().HP - this.HP;
        int dmg = missing / 100;
        dmg += this.AttackDamage;
        enemy.changeHP(-dmg*statper);
        this.changeCHARGE(dmg*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        kasse_super.play();
        enemy.HP = -this.SuperDamage;
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST") {
			this.changeCHARGE(10);
			enemy.changeSTATUS(Status.Weakened);
			//weakSE.play();
		}
		else {
			this.changeCHARGE(15);
			//weakSE.play();
			this.changeSTATUS(Status.Weakened);
		}
    }

    public Brawler newInstance() {
        return new Kasse(build);
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
   		sb.append("Kasse is here..? The end is here. No need to fight now. It will be slow. But he"
   				+ " is inevitible. Unless you're fast enough."); //here
   		return sb.toString();
   	}
}
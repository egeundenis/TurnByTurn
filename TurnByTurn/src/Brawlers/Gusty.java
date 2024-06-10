package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Gusty extends Brawler {

    SoundEffect gus_attack = new SoundEffect("res/audio/gusattack.wav");
    SoundEffect gus_super = new SoundEffect("res/audio/gussuper.wav");
    SoundEffect shieldbash = new SoundEffect("res/audio/shieldbash.wav");

    public Gusty(Build build) {
        super(build);
        this.build = build;
        this.name = "Gusty";
        this.HP = 425;
        this.AttackDamage = 30;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Lost?";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise.equals("HP GEAR"))
            this.HP *= 1.1;
        if (build.gearChoise.equals("POTION GEAR"))
            this.potionCount += 1;
        if (build.gearChoise.equals("GADGET GEAR"))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
        gus_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);

        if (this.shield > 0) {
            shieldbash.play();
            enemy.changeHP(-5*statper);
            this.changeCHARGE(5*statper);
        }
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        gus_super.play();
        this.changeSHIELD((int) (225 * (isHypercharged ? 1.2 : 1)) );
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			int anger = (int)((this.newInstance().HP - this.HP) * 0.125);
			enemy.changeHP(-30 - anger);
		}
		else {
			enemy.changeHP((int) (this.shield * -0.55) );
		}

	}

    public Brawler newInstance() {
        return new Gusty(build);
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
		 sb.append("Is Gusty a ghost..? Regardless, the balloon he's holding is no ghost! "
		 		+ "It WILL hurt and it WILL protect Gusty from hurt."); //here
		return sb.toString();
	}

	
}
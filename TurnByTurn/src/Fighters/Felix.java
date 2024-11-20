package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Felix extends Fighter {

    SoundEffect felix_attack = new SoundEffect("res/audio/felix_attack.wav");
    SoundEffect felix_super = new SoundEffect("res/audio/felix_super.wav");
    SoundEffect shieldbash = new SoundEffect("res/audio/shieldbash.wav");

    public Felix(Build build) {
        super(build);
        this.build = build;
        this.name = "Felix";
        this.HP = 600;
        this.AttackDamage = 20;
        this.AttackCharge = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Duelist";
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
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
        felix_attack.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        this.changeATK(1);
    }

    @Override
	public
    void superAbility(Fighter enemy) {

    	felix_super.play();
        if (enemy.shield > 0) {
            enemy.shield = 0;
            shieldbash.play();
        }

        enemy.changeHP(-this.SuperDamage);
        if (enemy.stat != Status.Normal || this.stat != Status.Normal) {
            enemy.changeSTATUS(Status.Stunned);
            this.changeSTATUS(Status.Normal);
            enemy.changeHP(-20);
        }
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
		this.changeSTATUS(Status.Guarded);
		}
		else {
			enemy.changeSTATUS(Status.Enraged);
			enemy.changeHP(-20);
		}
		
	}

    public Fighter newInstance() {
        return new Felix(build);
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
		 sb.append("Felix's dull sword is ready to get sharpened, on the enemy's flesh! And if anyone"
		 		+ " tries to interrupt their blade, They'll make sure to 'Surprise' them."); //here
		return sb.toString();
	}
	
}
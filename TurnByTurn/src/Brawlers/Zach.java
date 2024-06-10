package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Zach extends Brawler {

    static SoundEffect zach_attack = new SoundEffect("res/audio/zach_attack.wav");
    static SoundEffect zach_super = new SoundEffect("res/audio/zach_super.wav");

    public Zach(Build build) {
        super(build);
        this.build = build;
        this.name = "Zach";
        this.HP = 800;
        this.AttackDamage = 0;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.stat = Status.Normal;
        this.title = "the Selfweaponer";
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
    	
        zach_attack.play();
        int damage = (int) (this.HP * 0.105);
        enemy.changeHP(-damage*statper);
        this.changeHP(-damage*statper);
        this.changeCHARGE(damage*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        zach_super.play();
        this.changeHP((int) (this.newInstance().HP * 0.175));
    }

    @Override
	public
    void gadgetAbility(Brawler enemy) {

        if ("FIRST".equals(this.build.gadgetChoise)) {

            int zac = (int) (this.HP * 0.01);
            this.changeREGEN((int) (zac * 1.5));
            this.changeHP(zac * -15);

        } else
            this.changeHP((int) ((this.newInstance().HP - this.HP) * 0.25));
    }

    public Brawler newInstance() {
        return new Zach(build);
    }

    public void reset() {
        this.HP = this.newInstance().HP;
        this.regen = this.newInstance().regen;
        this.SuperCharge = this.newInstance().SuperCharge;
        this.gadgetCount = this.newInstance().gadgetCount;
        this.AttackDamage = this.newInstance().AttackDamage;
        this.SuperDamage = this.newInstance().SuperDamage;
        this.HyperCharge = this.newInstance().HyperCharge;
        this.stat = this.newInstance().stat;
        this.potionCount = this.newInstance().potionCount;
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
        } else if (x <= 0) {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else {
                if (new Random().nextInt(0, 20) != 0)
                    this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
                else {
                    this.HP -= x;
                    new SoundEffect("res/audio/zach_passive.wav").play();
                }
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
   		sb.append("Zach is... Zach! And more importantly an experiment gone wrong! He takes out a part of himself"
   				+ " and throws it at enemy's at battle. Come on now..."); //here
   		return sb.toString();
   	}
}
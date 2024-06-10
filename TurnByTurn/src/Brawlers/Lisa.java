package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Lisa extends Brawler {

    static SoundEffect lisa_attack = new SoundEffect("res/audio/lisa_attack.wav");
    static SoundEffect lisa_super = new SoundEffect("res/audio/lisa_super.wav");
    static SoundEffect lisa_gadget2 = new SoundEffect("res/audio/lisa_gadget2.wav");

    public Lisa(Build build) {
        super(build);
        this.build = build;
        this.name = "Lisa";
        this.HP = 400;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 5;
        this.regen = 5;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Ramper";
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
    	
        lisa_attack.play();
        enemy.changeHP(-AttackDamage*statper);
        this.changeCHARGE(AttackDamage*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        lisa_super.play();
        this.changeATK((int) (this.AttackDamage * 0.75));
        this.changeREGEN((int) (this.regen * 0.75));
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		if (this.build.gadgetChoise == "FIRST")
			this.changeCHARGE(10);
		else {
			this.changeCHARGE((int)(this.HP * 0.09));
			this.changeHP((int)(this.HP * -0.15));
			lisa_gadget2.play();
		}
    }

    public Brawler newInstance() {
        return new Lisa(build);
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
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1)) * (this.SuperCharge >= 100 ? 0.9 : 1);
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
   		sb.append("Lisa has fallen down to the mortal realm from the heavens. She will regain her"
   				+ " strength. Her body may crumble, but her will is eternal."); //here
   		return sb.toString();
   	}
    
}
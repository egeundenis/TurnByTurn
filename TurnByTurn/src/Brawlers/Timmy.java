package Brawlers;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Timmy extends Brawler {

    static SoundEffect timmy_attack = new SoundEffect("res/audio/timmy_attack.wav");
    static SoundEffect timmy_super = new SoundEffect("res/audio/timmy_super.wav");
    static SoundEffect timmy_passive = new SoundEffect("res/audio/timmy_passive.wav");
    
    public ImageIcon timmy_dragon = new ImageIcon("res/images/timmy_dragon.png");

    public Timmy(Build build) {
        super(build);
        this.build = build;
        this.name = "Timmy";
        this.HP = 475;
        this.AttackDamage = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 30;
        this.regen = 5;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Tankkiller";
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
    	
		int tim = (int) (enemy.HP * 0.1);
		tim += 15;
    	
        timmy_attack.play();
        if (this.HP < (int) (this.newInstance().HP * 0.30)) {
            timmy_passive.play();
            enemy.changeHP(-(tim + 25)*statper);
        } else {
            enemy.changeHP(-tim*statper);
        }
        this.changeCHARGE(tim*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        timmy_super.play();

        int DamageAtimmy = (int) (enemy.HP * -0.35) - this.SuperDamage;
        DamageAtimmy += 30;
        enemy.changeREGEN(enemy.regen / -2);
        enemy.changeHP(DamageAtimmy);
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			enemy.changeSTATUS(Status.Stunned);
		}
		else {
			this.changeHP((int)((enemy.newInstance().HP - enemy.HP) * 0.10));
		}
    }

    public Brawler newInstance() {
        return new Timmy(build);
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
   		sb.append("Timmy and his friend 'Big Boy' are going on 'adventures' and bringing 'justice' to all"
   				+ " in a time of need! They like to make things a little more equal during the fights though!"); //here
   		return sb.toString();
   	}
}
package Fighters;

import java.util.Random;

import main.Build;
import main.Game;
import main.Main;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Jester extends Fighter {

    static SoundEffect jester_normal = new SoundEffect("res/audio/jester_attack.wav");
    static SoundEffect jester_explodesuper = new SoundEffect("res/audio/jester_explodesuper.wav");
    static SoundEffect jester_stunsuper = new SoundEffect("res/audio/jester_stunsuper.wav");
    static SoundEffect jester_rockrainsuper = new SoundEffect("res/audio/jester_rockrainsuper.wav");
    static SoundEffect jester_healsuper = new SoundEffect("res/audio/jester_healsuper.wav");
    static SoundEffect jester_spellsuper = new SoundEffect("res/audio/jester_spellSuper.wav");

    Main main = new Main();

    static Random ran = new Random();
    public int normalAttackCounter = 1;

    public Jester(Build build) {
        super(build);
        this.build = build;
        this.name = "Jester";
        this.HP = 550;
        this.AttackDamage = 20;
        this.AttackCharge = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = ran.nextInt(-5, 10);
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Surpriser!";
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

        jester_normal.play();

        if (this.HP < (int) (newInstance().HP * 0.3)) {
        	
        	 normalAttackCounter =  ran.nextInt(1, 5);
            int dmg = -this.AttackDamage * normalAttackCounter;
            enemy.changeHP(dmg*statper);
            this.changeCHARGE(this.AttackCharge * normalAttackCounter);
            
        } else {

            enemy.changeHP(-normalAttackCounter * this.AttackDamage*statper);
            this.changeCHARGE(normalAttackCounter * this.AttackCharge);
            normalAttackCounter++;
            
            if (normalAttackCounter == 5)
                normalAttackCounter = 1;

        }
    }

    @SuppressWarnings("static-access")
    @Override
	public
    void superAbility(Fighter enemy) {
        int retu = ran.nextInt(0, 5);
        switch (retu) {
            case 0:
                jester_healsuper.play();
                this.changeHP(125);
                break;
            case 1:
                jester_explodesuper.play();
                enemy.changeHP(-100);
                break;
            case 2:
                jester_rockrainsuper.play();
                enemy.changeHP(-50);
                enemy.changeREGEN(-3);
                enemy.changeSTATUS(Status.Weakened);
                break;
            case 3:
                jester_stunsuper.play();
                enemy.changeHP(-10);
                enemy.changeSTATUS(Status.Stunned);
                break;
            case 4:
                jester_spellsuper.play();
                Jester placeholder = new Jester(new Build(new Random()));
                Game.spell(this, enemy, placeholder.spell); // KINDA BAD LOL
                break;
        }

    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
    	
		int randomInt = ran.nextInt(-30,60);
		if (this.build.gadgetChoise == "FIRST") {
			this.changeHP(randomInt / 2);
			this.changeREGEN(ran.nextInt(-10,20));
		}
		else {
			int randomInt2 = ran.nextInt(10,60);
			this.changeCHARGE(randomInt2);
		}
		
    }

    public Fighter newInstance() {
        return new Jester(build);
    }

    public void reset() {
        normalAttackCounter = 1;
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
   		 sb.append("BIG REVEAL! This jokester and his unpredictable nature will bore you one hundered"
   		 		+ " percent. Just be careful if he's holding 4 of his bells at once."); //here
   		return sb.toString();
   	}
    
}
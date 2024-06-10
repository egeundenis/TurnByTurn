package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class John extends Brawler {

    static SoundEffect john_normal = new SoundEffect("res/audio/john_attack.wav");
    static SoundEffect john_super = new SoundEffect("res/audio/john_super.wav");

    public John(Build build) {
        super(build);
        this.build = build;
        this.name = "John";
        this.HP = 325;
        this.AttackDamage = 60;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Glasscannon";
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
    	
        john_normal.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        john_super.play();
        int DamageAjohn = this.SuperDamage;
        DamageAjohn += (int) (this.HP * 0.65);
        DamageAjohn += (isHypercharged ? (enemy.HP * 0.2) : 0);
        enemy.changeHP(-DamageAjohn);
    }
    
	@Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			
		this.changeHP(30);
		this.changeSTATUS(Status.Weakened);
		}
		else {
			this.changeHP(-30);
			this.changeSTATUS(Status.Strengthened);
		}
		
	}

    public Brawler newInstance() {
        return new John(build);
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
   		sb.append("How elegant. This skinny male may look like an easy bite. But he will blow "
   				+ "your mind using his big shotgun... WAIT THAT CAME OUT WRONG"); //here
   		return sb.toString();
   	}


}
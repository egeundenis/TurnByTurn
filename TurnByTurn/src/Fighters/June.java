package Fighters;

import java.util.ArrayList;
import java.util.Random;

import GUI.Utility;
import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

class JuneData{
	int juneHP;
	int enemyHP;
	public JuneData(int juneHP, int enemyHP) {
		this.juneHP = juneHP;
		this.enemyHP = enemyHP;
	}
}

public class June extends Fighter {
	
	static SoundEffect june_attack = new SoundEffect("res/audio/june_attack.wav");
	static SoundEffect june_attackstrong = new SoundEffect("res/audio/june_attackstrong.wav");
	static SoundEffect june_ready = new SoundEffect("res/audio/june_ready.wav");
	static SoundEffect june_super = new SoundEffect("res/audio/june_super.wav");
	 
	public int normalStack = 1;
	private ArrayList<JuneData> juneDataHolder = new ArrayList<JuneData>();
	
    public June(Build build) {
        super(build);
        this.build = build;
        this.name = "June";
        this.HP = 600;
        this.AttackDamage = 30;
        this.AttackCharge = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 3;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Time Traveler";
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
    
    public void eachTurnChecks(Fighter enemy) {
		
    	juneDataHolder.add(new JuneData(this.HP, enemy.HP));
    	
    }

    @Override
	public void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
    	enemy.changeHP(-this.AttackDamage*statper* (this.isNormalModified ? 3 : 1));
        this.changeCHARGE(this.AttackCharge);   
        
        if(this.normalStack == 3) {
        	
        	enemy.changeHP(-15*statper* (this.isNormalModified ? 3 : 1));
            this.normalStack = 0;
            june_attackstrong.play();
            
        } else {
        	
        	june_attack.play();
        	
        }
        
        this.normalStack++;
        
        if(this.isNormalModified) 
    		this.isNormalModified = false;
    	

    }

    @Override
	public void superAbility(Fighter enemy) {

    	int juneDamage = 0;
    	int randomTurn = new Random().nextInt(5,10);
    	JuneData chosenData = this.juneDataHolder.get(juneDataHolder.size() - randomTurn);
    	
    	june_ready.play();
    	Utility.sleep(500);
    	
    	juneDamage += (chosenData.juneHP - this.HP);
    	juneDamage += (chosenData.enemyHP - enemy.HP);
    	juneDamage /= 2;
    	if(this.isHypercharged)
    		juneDamage *= 2;
    	
    	this.HP = chosenData.juneHP;
    	enemy.HP = chosenData.enemyHP;
    	
    	
    	june_super.play();
    	
    	enemy.changeHP(-juneDamage);
    		    	
    }
    
    @Override
	public void gadgetAbility(Fighter enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {

			this.isNormalModified = true;
			
		}
		else {
			
			this.HP = this.juneDataHolder.get(juneDataHolder.size() - 2).juneHP;
			
		}
		
	}

    public Fighter newInstance() {
        return new June(build);
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
        normalStack = 1;
    	this.juneDataHolder.clear();
    }

	@Override
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		 sb.append("June is the time traveler everyone hates! She goes around messing with people and then rewinding time to get advantage! What a brat..."); //here
		return sb.toString();
	}
	
}
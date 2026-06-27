/*
 * Character.java
 * Defines the base Character class and all playable character subclasses.
 * Includes character stat management, skills, equipment system, and status displays.
 * Subclasses: Swordsman, Priest, WanderingMan, Alchemist
 */

// Base character class representing playable characters
public abstract class Character extends Unit{

    String weapon;
    int weaponATK;
    String armor;
    int armorDEF;
    
    // Modifies character health with damage mitigation based on defense
    // Returns -1 if defeated, 0 otherwise
    public int modifyHP(int hpChange) {
    	//Attack
    	if(hpChange > 0) this.currentHP -= hpChange * (100.0 / (100 + this.DEF));
    	//Cure 
    	else if(hpChange < 0) this.currentHP += hpChange;
    	
    	//Adjust
    	if(currentHP > HPlimit) {
    		currentHP = HPlimit;
    		return 0;
    	}
    	else if(currentHP <= 0) {
    		currentHP = 0;
    		return -1;
    	}
    	return 0;
    }
    
    // Modifies character mana points and ensures it stays within limits
    public void modifyMP(int mpChange) {
    	this.currentMP += mpChange;
    	if(currentMP > MPlimit) currentMP = MPlimit;
    	else if(currentMP <= 0) currentMP = 0;
    }
    
    // Calculates total attack power including equipped weapon bonus
    public int attack() {
    	return ATK + weaponATK;
    }
    
    public String skill() {
    	return "Skill";
    }

    public String skill2() {
    	return "Skill2";
    }

    // Displays character details including stats and equipped items
    public void showStatus() {
    	Main.clearConsole();
        System.out.println("🏷 Name: " + name);
        System.out.println("👤 Appearance: " + appearance);
        System.out.println("❤️ Health point: " + currentHP);
        System.out.println("🔮 Magic point: " + currentMP);
        System.out.println("⚔️ Attack: " + ATK);
        System.out.println("🛡️ Defence: " + DEF);
        System.out.println("⏱️ Speed: " + SPD);
        System.out.println("----------------");
        System.out.println("🗡️ Weapon: " + weapon);
        System.out.println("🛡️ Armor: " + armor);
        System.out.println();
    }
}

// High HP and Defense warrior specializing in melee combat
class Swordsman extends Character {

    public Swordsman() {
        name = "Souma";
        appearance = "🪖";
        HPlimit = 120;
        currentHP = HPlimit;
        MPlimit = 30;
        currentMP = MPlimit;
        ATK = 18;
        DEF = 12;
        SPD = 12;
        weapon = null;
        weaponATK = 0;
        armor = null;
        armorDEF = 0;
    }

    public String skill() {
        this.modifyMP(-15);
    	return "Heavy Strike";//Deal 150% damage
    }

    public String skill2() {
        this.modifyMP(-10);
    	return "Guard Stance";//Reduce damage taken by 50% for 1 turn
    }
}

// Support character with healing and buff abilities
class Priest extends Character {

    public Priest() {
        name = "Ren";
        appearance = "👘";
        HPlimit = 80;
        currentHP = HPlimit;
        MPlimit = 100;
        currentMP = MPlimit;
        ATK = 8;
        DEF = 8;
        SPD = 8;
        weapon = null;
        weaponATK = 0;
        armor = null;
        armorDEF = 0;
    }

    public String skill() {
        this.modifyMP(-50);
    	return "Healing";//Cure 30 HP to all allies
    }

    public String skill2() {
        this.modifyMP(-30);
    	return "Blessing";//Increase all allies' defence for 2 turns
    }
}

// Agile character with crowd control and speed boost abilities
class WanderingMan extends Character {

    public WanderingMan() {
        name = "Yui";
        appearance = "🥷";
        HPlimit = 100;
        currentHP = HPlimit;
        MPlimit = 40;
        currentMP = MPlimit;
        ATK = 15;
        DEF = 10;
        SPD = 15;
        weapon = null;
        weaponATK = 0;
        armor = null;
        armorDEF = 0;
    }

     public String skill() {
        this.modifyMP(-10);
    	return "Thorns everywhere"; //Slow one enmy for 2 turns
    }

    public String skill2() {
        this.modifyMP(-20);
    	return "Shadow Step";//Increase speed by 50% for 3 turns and increase attack by 20% for 2 turns
    }
}

class Alchemist extends Character {

    public Alchemist() {
        name = "Mizuki";
        appearance = "👨‍🔬";
        HPlimit = 90;
        currentHP = HPlimit;
        MPlimit = 80;
        currentMP = MPlimit;
        ATK = 12;
        DEF = 9;
        SPD = 10;
        weapon = null;
        weaponATK = 0;
        armor = null;
        armorDEF = 0;
    }

     public String skill() {
        this.modifyMP(-20);
    	return "Fire Potion"; //vulnerable for 2 turns
    }

    public String skill2() {
        this.modifyMP(-20);
    	return "Poison Mist";//weak for 2 turns
    }
}
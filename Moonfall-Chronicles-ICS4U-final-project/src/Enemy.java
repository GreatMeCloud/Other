/*
 * Enemy.java
 * Defines the base Enemy class and all enemy subclasses for different encounter types.
 * Includes enemy stat definitions for Mob, EliteMob, Ghost, Bat, Python, and DemonKing.
 */

// Base enemy class for all enemy types
abstract class Enemy extends Unit{
    
    public void showStatus() {
    	Main.clearConsole();
        System.out.println("🏷 Name: " + name);
        System.out.println("👤 Appearance: " + appearance);
        System.out.println("❤️ Health point: " + currentHP);
        System.out.println("🔮 Magic point: " + currentMP);
        System.out.println("⚔️ Attack: " + ATK);
        System.out.println("🛡️ Defence: " + DEF);
        System.out.println("⏱️ Speed: " + SPD);
        System.out.println();
    }
}

class Mob extends Enemy {

    public Mob() {
        name = "Mob";
        appearance = "👾";
        HPlimit = currentHP = 40;
        MPlimit = currentMP = 0;
        ATK = 8;
        DEF = 3;
        SPD = 7;
    }
}

class EliteMob extends Enemy {

    public EliteMob() {
        name = "Elite Mob";
        appearance = "👹";
        HPlimit = currentHP = 80;
        MPlimit = currentMP = 20;
        ATK = 14;
        DEF = 6;
        SPD = 10;
    }
}

class Ghost extends Enemy {

    public Ghost() {
        name = "Ghost";
        appearance = "👻";
        HPlimit = currentHP = 180;
        MPlimit = currentMP = 100;
        ATK = 18;
        DEF = 5;
        SPD = 13;
    }
}

class Bat extends Enemy {

    public Bat() {
        name = "Giant Bat";
        appearance = "🦇";
        HPlimit = currentHP = 250;
        MPlimit = currentMP = 40;
        ATK = 24;
        DEF = 8;
        SPD = 20;
    }
}

class Python extends Enemy {

    public Python() {
        name = "Ancient Python";
        appearance = "🐍";
        HPlimit = currentHP = 400;
        MPlimit = currentMP = 30;
        ATK = 28;
        DEF = 15;
        SPD = 6;
    }
}

class DemonKing extends Enemy {

    public DemonKing() {
        name = "Demon King";
        appearance = "😈";
        HPlimit = currentHP = 800;
        MPlimit = currentMP = 200;
        ATK = 40;
        DEF = 20;
        SPD = 18;
    }
}

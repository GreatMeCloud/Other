
/*
 * Unit.java
 * Base class for all game units (characters and enemies) containing common attributes.
 * Defines health points, mana points, attack, defense, and speed statistics.
 */

// Base class for all units (characters and enemies) with stats
public class Unit {
	String name;
    String appearance;
    int HPlimit;
    int currentHP;
    int MPlimit;
    int currentMP;
    int ATK;
    int DEF;
    int SPD;
}

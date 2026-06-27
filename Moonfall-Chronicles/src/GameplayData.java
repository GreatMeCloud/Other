/*
 * GameplayData.java
 * Central repository for all game data including weapons, armor, and medicines.
 * Stores item names, attack/defense values, prices, and descriptions for game balance.
 */

// Stores all game data: weapons, armor, medicines, and their stats
public class GameplayData {
	//Weapons##########################################################################
	public static String[] weaponName = {
	    "archmage's staff",
	    "berserker giant axe",
	    "copper sword",
	    "dagger",
	    "demon blade",
	    "double-Edged Dagger",
	    "dragonlance",
	    "elemental codex",
	    "elf Bow",
	    "hunting Bow",
	    "iron sword",
	    "longbow",
	    "magic Sword",
	    "magic Wand",
	    "sage's Staff",
	    "shadow Strike Short Sword",
	    "tachi",
	    "warhammer",
	    "wooden Staff",
	    "wooden sword"
	};
	
	public static int[] weaponAtk = {
	    50,  // archmage's staff
	    42,  // berserker giant axe
	    8,   // copper sword
	    6,   // dagger
	    55,  // demon blade
	    12,  // double-Edged Dagger
	    45,  // dragonlance
	    60,  // elemental codex
	    24,  // elf Bow
	    10,  // hunting Bow
	    14,  // iron sword
	    18,  // longbow
	    30,  // magic Sword
	    12,  // magic Wand
	    22,  // sage's Staff
	    28,  // shadow Strike Short Sword
	    20,  // tachi
	    38,  // warhammer
	    5,   // wooden Staff
	    4    // wooden sword
	};
	
	public static int[] weaponPrice = {
	    6000, // archmage's staff
	    4500, // berserker giant axe
	    120,  // copper sword
	    80,   // dagger
	    8000, // demon blade
	    250,  // double-Edged Dagger
	    5000, // dragonlance
	    10000,// elemental codex
	    1200, // elf Bow
	    150,  // hunting Bow
	    250,  // iron sword
	    600,  // longbow
	    2000, // magic Sword
	    180,  // magic Wand
	    1000, // sage's Staff
	    1800, // shadow Strike Short Sword
	    800,  // tachi
	    3500, // warhammer
	    50,   // wooden Staff
	    40    // wooden sword
	};
	
	public static String[] weaponDescription = {
	    "A legendary staff wielded by the greatest archmages.",
	    "A massive axe that grants immense power at the cost of speed.",
	    "A basic sword forged from copper. Reliable for beginners.",
	    "A small blade favored by thieves and scouts.",
	    "A cursed blade infused with demonic energy.",
	    "A sharp dagger with two deadly edges.",
	    "A mighty lance said to pierce dragon scales.",
	    "An ancient tome containing the secrets of the elements.",
	    "A finely crafted bow made by elven artisans.",
	    "A simple bow used by hunters across the land.",
	    "A sturdy iron sword trusted by many adventurers.",
	    "A larger bow capable of powerful long-range shots.",
	    "A blade enchanted with magical power.",
	    "A wand that channels magical energy with ease.",
	    "A staff carried by wise sages and scholars.",
	    "A swift sword designed for surprise attacks.",
	    "A traditional curved blade known for its precision.",
	    "A heavy hammer capable of crushing armor.",
	    "A simple wooden staff often used by novice mages.",
	    "A crude wooden sword used for training."
	};
	
	
	//Armors##########################################################################
	
	public static String[] armorName = {
	    "Adamantite Armor",
	    "Armor of Eternity",
	    "bronze ring",
	    "Celestial Armor",
	    "chain armor",
	    "cloth clothing",
	    "cloth hat",
	    "Dragon Scale Armor",
	    "Hero's Armor",
	    "iron helmet",
	    "leather armor",
	    "leather hat",
	    "Leviathan Scale Mail",
	    "lucky amulet",
	    "magic pendant",
	    "silver ring",
	    "steel armor",
	    "Worldbreaker Armor"
	};
	
	public static int[] armorDef = {
	    30, // Adamantite Armor
	    60, // Armor of Eternity
	    3,  // bronze ring
	    50, // Celestial Armor
	    12, // chain armor
	    4,  // cloth clothing
	    2,  // cloth hat
	    35, // Dragon Scale Armor
	    40, // Hero's Armor
	    8,  // iron helmet
	    10, // leather armor
	    4,  // leather hat
	    45, // Leviathan Scale Mail
	    6,  // lucky amulet
	    8,  // magic pendant
	    5,  // silver ring
	    18, // steel armor
	    55  // Worldbreaker Armor
	};
	
	public static int[] armorPrice = {
	    5000,  // Adamantite Armor
	    12000, // Armor of Eternity
	    120,   // bronze ring
	    9000,  // Celestial Armor
	    500,   // chain armor
	    80,    // cloth clothing
	    40,    // cloth hat
	    6000,  // Dragon Scale Armor
	    7000,  // Hero's Armor
	    300,   // iron helmet
	    350,   // leather armor
	    100,   // leather hat
	    8000,  // Leviathan Scale Mail
	    600,   // lucky amulet
	    1000,  // magic pendant
	    300,   // silver ring
	    1200,  // steel armor
	    10000  // Worldbreaker Armor
	};
	
	public static String[] armorDescription = {
	    "Armor forged from the legendary adamantite metal.",
	    "An eternal relic said to transcend time itself.",
	    "A simple bronze ring worn by novice adventurers.",
	    "Armor blessed by the power of the stars.",
	    "Interlocking chains provide balanced protection.",
	    "Basic clothing made from simple cloth.",
	    "A lightweight hat offering minimal protection.",
	    "Armor crafted from the scales of a mighty dragon.",
	    "The armor worn by heroes of ancient legend.",
	    "A sturdy iron helmet that protects the head.",
	    "Armor crafted from hardened leather.",
	    "A leather cap favored by travelers.",
	    "Scale mail made from the hide of Leviathan.",
	    "An amulet believed to bring good fortune.",
	    "A pendant infused with magical energy.",
	    "A finely crafted ring made of silver.",
	    "Heavy steel armor trusted by veteran warriors.",
	    "Armor capable of withstanding world-shattering forces."
	};
	
	
	//Medicine##########################################################################
	
	public static String[] medicineName = {
	    "herbs",
	    "mana Potion",
	    "panacea",
	    "resurrection Feather",
	    "special Medicine",
	    "super Magic Potion"
	};
	
	public static int[] medicinePrice = {
	    30,    // herbs
	    80,    // mana Potion
	    500,   // panacea
	    1000,  // resurrection Feather
	    200,   // special Medicine
	    300    // super Magic Potion
	};
	
	public static String[] medicineType = {
	    "HP",
	    "MP",
	    "CURE",
	    "REVIVE",
	    "HP",
	    "MP"
	};
	
	public static int[] medicineEffect = {
	    50,   // herbs
	    30,   // mana Potion
	    0,    // panacea
	    50,   // revive with 50% HP
	    200,  // special Medicine
	    100   // super Magic Potion
	};
	
	public static String[] medicineDescription = {
	    "A bundle of medicinal herbs that restores a small amount of HP.",
	    "A potion that restores magical energy.",
	    "A miraculous remedy that cures all status ailments.",
	    "A sacred feather capable of reviving the fallen.",
	    "A refined medicine that restores a large amount of HP.",
	    "A powerful potion that greatly restores MP."
	};
}

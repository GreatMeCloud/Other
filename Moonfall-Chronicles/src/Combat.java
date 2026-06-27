import java.util.*;

/*
 * Combat.java
 * Manages all combat mechanics including battles, turn sequences, enemy encounters, and combat effects.
 * Handles combat initialization, displaying combat stages, character/enemy actions, and victory/defeat conditions.
 */

// Manages combat mechanics and battle system
public class Combat {
	Scanner sc = new Scanner(System.in);
	
	int[][] stageContent = {
			//amount: mob, elite mob, ghost, bat, python, demon king
			//scene
			/*mob*/{1, 0, 0, 0, 0, 0},
			/*elite mob*/{1, 1, 0, 0, 0, 0},
			/*ghost*/{4, 0, 1, 0, 0, 0},
			/*bat*/{0, 2, 0, 1, 0, 0},
			/*python*/{4, 0, 0, 0, 1, 0},
			/*demon king*/{0, 0, 1, 1, 1, 1}
	};
	
	public String[] enemyName = {
			"mob",
			"elite mob",
			"ghost",
			"bat",
			"python",
			"demon king"
	};
	
	String[] effect = {
			"slow",//Decrease character/enemy speed by 50% for 2 turns
			"speed up",//Increase character/enemy speed by 50% for 3 turns
			"strength",//Increase damage dealt by 50% for 2 turns
			"guard",//Reduce damage taken by 50% for 1 turn
			"weak",//Decrease damage dealt by 50% for 2 turns
			"vulnerable",//Increase damage taken by 50% for 2 turns
			"low HP",
			"low MP",
			"defeated"
	};
	
	String[] effectSymbols = {
			"🐌",
			"⏭️",
			"💪",
			"🛡️",
			"🥀",
			"❤️‍🩹",
			"💔",
			"🪫",
			"😵"
	};
	
	LinkedList<Enemy> currentEnemy = new LinkedList<>();

	LinkedList<LinkedList<String>> enemyEffects = new LinkedList<>();
	LinkedList<LinkedList<String>> characterEffects = new LinkedList<>();

	Queue combatSequence = new Queue(20);
	
	// Manages the entire combat flow from setup to victory/defeat
	public int combatManager(String currentSpot, LinkedList<Character> currentCharacter, Inventory inventory) {
		Main.clearConsole();
		int index = combatPreload(currentSpot);
		if(index == -1) return -1;
		while(true){
			//Pre-selection
			System.out.println("◆ 1.⚔️ Enter battle directly");
			System.out.println("◆ 2.🎭 Modify character equipment");
			System.out.print("\nSelect a choice (type \'exit\' to exit):");
			String choice = sc.nextLine();
			if(choice.equals("exit")) return -1;
			if(choice.equals("1")) break;
			if(choice.equals("2")) {
				ModifyEquipment.modifyEquipment(currentCharacter, inventory, sc);
				break;
			}
		}
		
		initializeTurnSequence(currentCharacter);
		
		while(true) {
			Main.clearConsole();
			if(allEnemiesDefeated()) {
				System.out.println("🎊 Congratulation! You defeated all the enemies 🎊");
				Random rand = new Random();
				int bonus = rand.nextInt(500, 20000);
				Main.asset += bonus;
				System.out.println("You get " + bonus + " gold 💰");
				System.out.print("\nPress \'Enter\' to continue:");
				sc.nextLine();
				break;
			}
			if(allCharactersDefeated(currentCharacter)) {
				System.out.println("😵‍💫 Opps, you are defeated by enemy 😵‍💫");
				System.out.print("Press \'Enter\' to continue:");
				sc.nextLine();
				break;
			}
			
			displayStage(currentEnemy, currentCharacter, index);
			
			boolean isPlayerTurn = false;
			int playerIndex = -1;
			for(int i = 0; i < currentCharacter.size(); i++) {
				if(currentCharacter.get(i).name.equals(combatSequence.front())) {
					isPlayerTurn = true;
					playerIndex = i;
					break;
				}
			}
			
			if(isPlayerTurn) {
				System.out.println("\n\n◆ 1.Attack");
				System.out.println("◆ 2.Escape");
				System.out.print("Select a choice:");
				String choice = sc.nextLine();
				if(choice.equals("1")) {
					combatSequence.enqueue(combatSequence.dequeue());//Change the combat Sequence
					normalAttack(currentCharacter.get(playerIndex), currentEnemy);
				}
				else if(choice.equals("2")) {
					combatSequence.enqueue(combatSequence.dequeue());//Change the combat Sequence
					break;
				}
				else continue;
			}
			else {
				int enemyIndex = -1;
				for(int i = 0; i < currentEnemy.size(); i++) {
					if(currentEnemy.get(i).currentHP > 0) {
						enemyIndex = i;
						break;
					}
				}
				if(enemyIndex != -1) {
					combatSequence.enqueue(combatSequence.dequeue());
					enemyAction(currentEnemy.get(enemyIndex), currentCharacter);
					System.out.println("\nPress \'Enter\' to continue:");
					sc.nextLine();
				}
			}
		}
		
		//Delete combat information
		currentEnemy.clear();		
		return 1;
	}
	
	// Loads enemies based on the current combat encounter
	public int combatPreload(String currentSpot) {
		//Get stage/enemy index
		int index = -1;
		for(int i = 0, l = enemyName.length; i < l; i++) {
			if(currentSpot.equals(enemyName[i])) index = i;
		}
		if(index == -1) return index;
		
		// Load enemies based on encounter type
		//mob, elite mob, ghost, bat, python, demon king
		if(stageContent[index][0] != 0) {
			for(int i = 0, l = stageContent[index][0]; i < l; i++) {
				Enemy enemy = new Mob();
				currentEnemy.add(enemy);
			}
		}
		if(stageContent[index][1] != 0) {
			for(int i = 0, l = stageContent[index][1]; i < l; i++) {
				Enemy enemy = new EliteMob();
				currentEnemy.add(enemy);
			}
		}
		if(stageContent[index][2] != 0) {
			for(int i = 0, l = stageContent[index][2]; i < l; i++) {
				Enemy enemy = new Ghost();
				currentEnemy.add(enemy);
			}
		}
		if(stageContent[index][3] != 0) {
			for(int i = 0, l = stageContent[index][3]; i < l; i++) {
				Enemy enemy = new Bat();
				currentEnemy.add(enemy);
			}
		}
		if(stageContent[index][4] != 0) {
			for(int i = 0, l = stageContent[index][4]; i < l; i++) {
				Enemy enemy = new Python();
				currentEnemy.add(enemy);
			}
		}
		if(stageContent[index][5] != 0) {
			for(int i = 0, l = stageContent[index][5]; i < l; i++) {
				Enemy enemy = new DemonKing();
				currentEnemy.add(enemy);
			}
		}
		
		return index;
	}
	
	// Displays current battle state with all characters and enemies
	public void displayStage(LinkedList<Enemy> currentEnemy, LinkedList<Character> currentCharacter, int index) {
		System.out.println("Combat against " + enemyName[index]);
		
		//Display enemy
		System.out.print("\n---------------------------------\n");
		for(int i = 0, l = currentEnemy.size(); i < l; i ++) System.out.print(currentEnemy.get(i).appearance + "\t\t");
		System.out.println();
		for(int i = 0, l = currentEnemy.size(); i < l; i ++) System.out.print(currentEnemy.get(i).currentHP + "/" + currentEnemy.get(i).HPlimit + "\t\t");
		System.out.println();

		//Interval
		System.out.println("\n\n\n\n\n");
		//Display Character
		for(int i = 0, l = currentCharacter.size(); i < l; i ++) System.out.print(currentCharacter.get(i).appearance + "\t\t");
		//Display Character status
		System.out.println();
		for(int i = 0, l = currentCharacter.size(); i < l; i ++) System.out.print(currentCharacter.get(i).currentHP + "/" + currentCharacter.get(i).HPlimit + "\t\t");
		System.out.println();
		
		System.out.println("---------------------------------");

		//Display combat sequence
		System.out.print("\n🔄 Combat sequence: ");
		for(int i = 0, l = combatSequence.size(); i < l; i++) {
			String name = (String) combatSequence.dequeue();
			System.out.print(name + " ⬅ ");
			combatSequence.enqueue(name);
		}
		
	}
	
	private void initializeTurnSequence(LinkedList<Character> currentCharacter) {
	    // Clear old sequence
	    while(combatSequence.size() > 0) {
	        combatSequence.dequeue();
	    }

	    // Create list of all participants
	    LinkedList<Unit> participants = new LinkedList<>();

	    for(Character c : currentCharacter) {
	        participants.add(c);
	    }

	    for(Enemy e : currentEnemy) {
	        participants.add(e);
	    }

	    // Sort by speed (descending)
	    for(int i = 0; i < participants.size() - 1; i++) {
	        for(int j = 0; j < participants.size() - 1 - i; j++) {

	            int speed1 = participants.get(j).SPD;
	            int speed2 = participants.get(j + 1).SPD;

	            if(speed1 < speed2) {
	                Unit temp = participants.get(j);

	                participants.set(j, participants.get(j + 1));
	                participants.set(j + 1, temp);
	            }
	        }
	    }

	    // Enqueue to combat sequence
	    for(Unit p : participants) {
	        combatSequence.enqueue(p.name);
	    }
	}
	
	private void normalAttack(Character attacker, LinkedList<Enemy> enemies) {
		// Select target
		System.out.println("\nSelect target:");
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).currentHP > 0) {
				System.out.println((i+1) + ". " + enemies.get(i).appearance + " " + enemies.get(i).name + " (" + enemies.get(i).currentHP + "/" + enemies.get(i).HPlimit + ")");
			}
		}
		
		int targetIndex;
		while(true) {
		    try {
		    	System.out.print("Select target (1-" + enemies.size() + "): ");
		        targetIndex = Integer.parseInt(sc.nextLine()) - 1;
		        break;
		    }
		    catch(NumberFormatException e) {
		        System.out.println("Please enter a valid number");
		    }
		}
		if(targetIndex < 0 || targetIndex >= enemies.size() || enemies.get(targetIndex).currentHP <= 0) {
			System.out.println("Invalid target!");
			normalAttack(attacker, enemies);
			return;
		}
		
		Enemy target = enemies.get(targetIndex);
		int damage = attacker.attack();
		
		target.currentHP -= damage;
		if(target.currentHP < 0) target.currentHP = 0;
		
		System.out.println("\n" + attacker.name + " attacks " + target.name + " for " + damage + " damage!");
		
		if(target.currentHP <= 0) {
			System.out.println(target.name + " was defeated!");
			System.out.println("\nPress \'Enter\' to continue:");
			sc.nextLine();
		}
	}
	
	private void enemyAction(Enemy enemy, LinkedList<Character> characters) {
		System.out.println("\n" + enemy.name + "'s turn!");
		Random rand = new Random();
		
		// Select random character target
		int targetIndex;
		Character target;
		while(true) {
			targetIndex = rand.nextInt(characters.size());
			target = characters.get(targetIndex);
			if(target.currentHP > 0) break;
		}
		
		int damage = enemy.ATK;
		
		target.modifyHP(-damage);
		System.out.println(enemy.name + " attacks " + target.name + " for " + damage + " damage!");
		
		if(target.currentHP <= 0) {
			System.out.println(target.name + " was defeated!");
			System.out.println("\nPress \'Enter\' to continue:");
			sc.nextLine();
		}
	}
	
	private boolean allEnemiesDefeated() {
		for(Enemy e : currentEnemy) {
			if(e.currentHP > 0) return false;
		}
		return true;
	}
	
	private boolean allCharactersDefeated(LinkedList<Character> characters) {
		for(Character c : characters) {
			if(c.currentHP > 0) return false;
		}
		return true;
	}
}

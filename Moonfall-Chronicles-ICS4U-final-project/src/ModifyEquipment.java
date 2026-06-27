import java.util.LinkedList;
import java.util.Scanner;

/*
 * ModifyEquipment.java
 * Allows players to equip weapons and armor on their characters from their inventory.
 * Handles item validation, equipment swapping, and inventory updates.
 */

// Allows players to equip weapons and armor on characters
public class ModifyEquipment {
	public static void modifyEquipment(LinkedList<Character> currentCharacter, Inventory inventory, Scanner sc) {
		while(true) {
			Main.clearConsole();
			System.out.println("Character:");
			for(int i = 0; i < currentCharacter.size(); i++) {
				System.out.println("◆ " + (i + 1) + "." + currentCharacter.get(i).appearance + currentCharacter.get(i).name);
			}
			System.out.print("\nPlease enter number 🔢 to modify character equipment 🎭 (Enter \'exit\' to exit ↩️):");
			String choice = sc.nextLine();
			
			int characterNum = 10;
			if(choice.equals("exit")) break;
			//Souma
			else if(choice.equals("1")) characterNum = 0;
			//Ren
			else if(choice.equals("2")) characterNum = 1;
			//Yui
			else if(choice.equals("3")) characterNum = 2;
			//Mizuki
			else if(choice.equals("4")) characterNum = 3;
			//other input
			else continue;
			
			//Character not exit
			if(characterNum + 1 > currentCharacter.size()) continue;
			else {
				while(true) {
					Main.clearConsole();
					inventory.displayInventory();
					System.out.println("\n👤 Character: " + currentCharacter.get(characterNum).name + currentCharacter.get(characterNum).appearance);
					System.out.println("🗡️ Weapon: " + currentCharacter.get(characterNum).weapon);
					System.out.println("🛡️ Armor: " + currentCharacter.get(characterNum).armor);
					
					System.out.print("Enter equipment name to install (Enter \'exit\' to exit ↩️):");
					choice = sc.nextLine();
					if(choice.equals("exit")) break;
					//Exist in inventory?
					boolean foundItem = false;
					for (int i = 0; i < inventory.content.size(); i++) {
						//Found item in inventory
					    if (choice.trim().equalsIgnoreCase(inventory.content.get(i).itemName.trim())) {
					    	foundItem = true;
					    	boolean equipped = false;
					    	LinkedList<String> tempInventory = new LinkedList <>();
					    	//Recognize item type
					    	//Weapon?
					    	for(int j = 0, l = GameplayData.weaponName.length; j < l; j++) {
					    		if (choice.trim().equalsIgnoreCase(GameplayData.weaponName[j].trim())) {
					    			//Add character's armor back to inventory
					    			if(currentCharacter.get(characterNum).weapon != null) {
						    			tempInventory.add(currentCharacter.get(characterNum).weapon);
						    			inventory.setInventory(tempInventory);
					    			}
					    			//delete selected armor
					    			inventory.content.remove(i);
					    			//Equip selected armor
					    			currentCharacter.get(characterNum).weapon = GameplayData.weaponName[j];
					    			System.out.println("Selected weapon has been equipped");
					    			System.out.print("Press \'Enter\' to continue");
									sc.nextLine();
									equipped = true;
					    			break;
					    		}
					    	}
					    	
					    	//Armor?
					    	for(int j = 0, l = GameplayData.armorName.length; j < l; j++) {
					    		if (choice.trim().equalsIgnoreCase(GameplayData.armorName[j].trim())) {
					    			//Add character's armor back to inventory
					    			if(currentCharacter.get(characterNum).armor != null) {
						    			tempInventory.add(currentCharacter.get(characterNum).armor);
						    			inventory.setInventory(tempInventory);
					    			}
					    			//delete selected armor
					    			inventory.content.remove(i);
					    			//Equip selected armor
					    			currentCharacter.get(characterNum).armor = GameplayData.armorName[j];
					    			System.out.println("Selected armor has been equipped");
					    			System.out.print("Press \'Enter\' to continue");
									sc.nextLine();
									equipped = true;
					    			break;
					    		}
					    	}
					    	
					    	//Medicine or other stuff
					    	if(!equipped) {
						    	System.out.println("Selected item cannot be equipped");
				    			System.out.print("Press \'Enter\' to continue");
								sc.nextLine();
					    	}
					    }
					}
					if(!foundItem) {
						System.out.println("Item not found");
						System.out.print("Press \'Enter\' to continue");
						sc.nextLine();
					}
					Main.clearConsole();
				}
			}
			
		}
	}
}

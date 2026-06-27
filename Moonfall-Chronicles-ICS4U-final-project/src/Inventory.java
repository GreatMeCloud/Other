import java.util.LinkedList;

/*
 * Inventory.java
 * Manages player inventory system including item storage, sorting, and manipulation.
 * Handles adding/removing items and displays inventory contents to the player.
 */

// Manages player inventory and items
public class Inventory {
	public LinkedList<InventoryUnit> content = new LinkedList<>();
	
	// Shows all items in player's inventory
	public void displayInventory() {
		System.out.println("📂 Your inventory\n");
		for(InventoryUnit i : content) {
			System.out.println("◆ " + i.amount + " " + i.itemName);
		}
	}
	
	// Adds new items to inventory, handling duplicates and sorting
	public void setInventory(LinkedList<String> tempInventory) {
		//Iterate all the item gained from the other event 
		for(int i = 0, l = tempInventory.size(); i < l; i++) {
			boolean itemExist = false;
			//Search if player already have the item, otherwise push a new InventoryUnit
			for(int j = 0, l2 = content.size(); j < l2; j++) {
				if(tempInventory.get(i).equalsIgnoreCase(content.get(j).itemName)) {
					content.get(j).amount++;
					itemExist = true;
					break;
				}
			}
			//Item is not in player's invnetory, add a new InventoryUnit
			if(!itemExist) {
				InventoryUnit newItem = new InventoryUnit(tempInventory.get(i), 1);
				content.add(newItem);
			}
		}
		sort();
	}

	// Debug function: fills inventory with all available weapons and armor
	public void cheatInventory() {
		for(int i = 0, l = GameplayData.weaponName.length; i < l; i++) {
			LinkedList<String> tempInventory = new LinkedList<>();
			String newItem = GameplayData.weaponName[i];
			tempInventory.add(newItem);
			setInventory(tempInventory);
		}
		
		for(int i = 0, l = GameplayData.armorName.length; i < l; i++) {
			LinkedList<String> tempInventory = new LinkedList<>();
			String newItem = GameplayData.armorName[i];
			tempInventory.add(newItem);
			setInventory(tempInventory);
		}
		
		for(int i = 0, l = GameplayData.medicineName.length; i < l; i++) {
			LinkedList<String> tempInventory = new LinkedList<>();
			String newItem = GameplayData.medicineName[i];
			tempInventory.add(newItem);
			setInventory(tempInventory);
		}
	}
	
	// Sorts inventory items alphabetically using bubble sort
	public void sort() {
	    for (int i = 0; i < content.size() - 1; i++) {
	        for (int j = 0; j < content.size() - 1 - i; j++) {

	            if (content.get(j).itemName.compareTo(content.get(j + 1).itemName) > 0) {

	                InventoryUnit temp = content.get(j);
	                content.set(j, content.get(j + 1));
	                content.set(j + 1, temp);

	            }
	        }
	    }
	}
}

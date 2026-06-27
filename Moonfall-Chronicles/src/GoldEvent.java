import java.util.Scanner;

/*
 * GoldEvent.java
 * Manages the market system where players can buy and sell items using gold currency.
 * Handles item pricing, inventory transactions, and player asset management.
 */

// Manages market interactions and item selling
public class GoldEvent {
	Scanner sc = new Scanner(System.in);
	
	Inventory inventory;
	
	String[] weaponName;
	int[] weaponAtk;
	int[] weaponPrice;
	String[] weaponDescription;
	
	String[] armorName;
	int[] armorDef;
	int[] armorPrice;
	String[] armorDescription;
	
	String[] medicineName;
	int[] medicinePrice;
	String[] medicineType;
	int[] medicineEffect;
	String[] medicineDescription;
	
	public GoldEvent(Inventory inventory) {
		this.inventory = inventory;
		
		// Load weapon data from GameplayData
		weaponName = GameplayData.weaponName;
		weaponAtk = GameplayData.weaponAtk;
		weaponPrice = GameplayData.weaponPrice;
		weaponDescription = GameplayData.weaponDescription;
		
		// Load armor data from GameplayData
		armorName = GameplayData.armorName;
		armorDef = GameplayData.armorDef;
		armorPrice = GameplayData.armorPrice;
		armorDescription = GameplayData.armorDescription;
				
		// Load medicine data from GameplayData
		medicineName = GameplayData.medicineName;
		medicinePrice = GameplayData.medicinePrice;
		medicineType = GameplayData.medicineType;
		medicineEffect = GameplayData.medicineEffect;
		medicineDescription = GameplayData.medicineDescription;
	}

	// Searches for an item price across all item categories
	public int getItemPrice(String itemName) {
		for(int i = 0, l = weaponName.length; i < l; i++) {
			if(itemName.equals(weaponName[i])) {
				return weaponPrice[i];
			}
		}
		
		for(int i = 0, l = armorName.length; i < l; i++) {
			if(itemName.equals(armorName[i])) {
				return armorPrice[i];
			}
		}
		
		for(int i = 0, l = medicineName.length; i < l; i++) {
			if(itemName.equals(medicineName[i])) {
				return medicinePrice[i];
			}
		}
		
		return -1;//Item not found
	}
	
	// Main market interface for buying and selling items
	public void market(Inventory inventory) {
		System.out.println("Market");
		System.out.println("🤑 You can sell items here 🤑");
		
		//If inventory is empty
		if(inventory.content.size() == 0) {
			System.out.println("\nYour inventory is empty, you have nothing to sell.");
			System.out.print("Press \'enter\' to exit the market:");
			sc.nextLine();
			return;
		}

		while(true) {
			Main.clearConsole();
			inventory.displayInventory();
			//Display asset
			System.out.println("\n💰 Asset: " + Main.asset + " gold");
			System.out.print("\nEnter the name of the item you want to sell (or type \'exit\' to leave):");
			String itemToSell = sc.nextLine();

			if(itemToSell.equals("exit")) {
				return;
			}
			
			boolean found = false;

			for (int i = 0; i < inventory.content.size(); i++) {
			    if (itemToSell.trim().equalsIgnoreCase(inventory.content.get(i).itemName.trim())) {

			        found = true;

			        int amountToSell;
			        System.out.println("How many " + itemToSell + " do you want to sell? (You have " + inventory.content.get(i).amount + "):");

			        try {
			            amountToSell = sc.nextInt();
			            sc.nextLine(); 
			        } catch (Exception e) {
			            break;
			        }

			        if (amountToSell > 0 && amountToSell <= inventory.content.get(i).amount) {

			            Main.asset += getItemPrice(itemToSell) * amountToSell;
			            inventory.content.get(i).amount -= amountToSell;

			            if (inventory.content.get(i).amount == 0) {
			                inventory.content.remove(i);
			            }
			        }
			        else {
			        	System.out.println("Amount exceeds the range");
			        	System.out.print("Press \'enter\' to continue:");
						sc.nextLine();
			        }

			        break;
			    }
			}

			if (!found) {
			    System.out.println("You don't have this item in your inventory.");
			    System.out.println("Press 'enter' to continue:");
			    sc.nextLine();
			}
		}
	}
}

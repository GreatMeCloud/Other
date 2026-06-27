import java.util.*;

/*
 * InventoryEvent.java
 * Handles item acquisition events such as treasure drops, special encounters, and event rewards.
 * Manages different item pools for doctors, weapon dealers, armor merchants, and treasure chests.
 */

// Handles inventory events: treasures, drops, and special items
public class InventoryEvent {
	Random rand = new Random();
	Scanner sc = new Scanner(System.in);
	
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
	
	public InventoryEvent(GameplayData gameplayData) {
		//Weapon
		weaponName = GameplayData.weaponName;
		weaponAtk = GameplayData.weaponAtk;
		weaponPrice = GameplayData.weaponPrice;
		weaponDescription = GameplayData.weaponDescription;
		
		//Armor
		armorName = GameplayData.armorName;
		armorDef = GameplayData.armorDef;
		armorPrice = GameplayData.armorPrice;
		armorDescription = GameplayData.armorDescription;
				
		//Medicine
		medicineName = GameplayData.medicineName;
		medicinePrice = GameplayData.medicinePrice;
		medicineType = GameplayData.medicineType;
		medicineEffect = GameplayData.medicineEffect;
		medicineDescription = GameplayData.medicineDescription;
	}
	
	String[] doctorItem = {
			"herbs",
			"special Medicine",
			"mana Potion",
			"super Magic Potion",
			"panacea"
	};
	
	String[] weaponDealerItem = {
			"wooden sword",
			"copper sword",
			"iron sword",
			"wooden Staff",
			"magic Wand",
			"sage's Staff",
			"hunting Bow",
			"longbow",
			"elf Bow",
			"dagger",
			"double-Edged Dagger",
			"shadow Strike Short Sword"
	};
	
	String[] armorMerchantItem = {
			"cloth hat",
			"leather hat",
			"iron helmet",
			"cloth clothing",
			"leather armor",
			"chain armor",
			"steel armor",
			"bronze ring",
			"silver ring",
			"lucky amulet",
			"magic pendant",
	};
	
	String[] treasureChestRareItem = {
			"tachi",
			"magic Sword",
			"demon blade",
			"dragonlance",
			"berserker giant axe",
			"warhammer",
			"archmage's staff",
			"elemental codex"
	};
	
	String[] treasureChestCommonItem = {
			"herbs",
			"wooden sword",
			"copper sword",
			"cloth hat",
			"leather hat",
			"bronze ring",
	};
	
	String[] eventName = {
			"weapon dealer",
			"armor merchant",
			"doctor",
			"treasure chest"
	};
	
	public LinkedList<String> eventManager(String event) {
		int index = 0;
		
		for(String item : eventName) {
			if(event.equals(item)) break;
			index++;
		}
		
		switch(index) {
			case 0:
				return trade(0);
			case 1:
				return trade(1);
			case 2:
				return trade(2);
			case 3:
				return treasure();
			default:
				return null;
		}
	}
	
	public LinkedList<String> trade(int tradeType) {
		//0 means weapons dealer
		//1 means armor merchant
		//2 means doctor

		LinkedList<String> item = new LinkedList<>();
		
		String[] tradeTitle = {
				"Trade with weapon dealer🗡️\n\n👨🏼‍🏭️: Heheheha, want some weapons? you are at the right place\n",
				"Trade with armor merchant🛡️\n\n💂‍️: Defense maxed out!!!\n",
				"Trade with doctor💊\n\n👨🏻‍⚕️: Get some medicine will help you a lot\n"
		};
		
		//Fake merchant
		boolean fakeMerchantExist = false;
		if(rand.nextInt(6) == 0) {
			fakeMerchantExist = true;
		}
		
		while(true){
			Main.clearConsole();
			if(tradeType == 0 || tradeType == 1 || tradeType == 2) System.out.println(tradeTitle[tradeType]);
			//Display items
			//Weapon
			if(tradeType == 0) {
				for(int j = 0, l = weaponDealerItem.length; j < l; j++) {
					System.out.println((j + 1) + ". " + weaponDealerItem[j]);
				}
			}
			//Armor
			else if(tradeType == 1) {
				for(int j = 0, l = armorMerchantItem.length; j < l; j++) {
					System.out.println((j + 1) + ". " + armorMerchantItem[j]);
				}
			}
			//Medicine
			else if(tradeType == 2) {
				for(int j = 0, l = doctorItem.length; j < l; j++) {
					System.out.println((j + 1) + ". " + doctorItem[j]);
				}
			}
		
			//Choose one for detail
			int itemNum;
			try {
		        System.out.print("\nEnter the item number 🔢 to get detail 🔍, other to quit ↩️: ");
		        itemNum = Integer.parseInt(sc.nextLine());
		    } catch (NumberFormatException e) {
		        break;
		    }
			
			//Check the validation of the item number
			if(tradeType == 0) {
				if(itemNum > weaponDealerItem.length || itemNum < 1) continue;
			}
			else if(tradeType == 1) {
				if(itemNum > armorMerchantItem.length || itemNum < 1) continue;
			}
			else if(tradeType == 2) {
				if(itemNum > doctorItem.length || itemNum < 1) continue;
			}
			Main.clearConsole();
			
			
			//Display detail
			int price = 0;
			String itemName = null;
			//Weapon
			if(tradeType == 0) {
				String currentItemName = weaponDealerItem[itemNum - 1];
				//Get the index of the weapon in gameplay data
				int index = 0;
				boolean foundItem = false;
				for(String name : weaponName) {
					if(name.equals(currentItemName)) {
						foundItem = true;
						break;
					}
					index++;
				}
				
				if(foundItem) {
					System.out.println("🏷️ Name: " + weaponName[index]);
					System.out.println("📜 Description: " + weaponDescription[index]);
					System.out.println("🗡️ Attack: " + weaponAtk[index]);
					System.out.println("💰 cost: " + weaponPrice[index] + " gold");
					price = weaponPrice[index];
					itemName = weaponName[index];
				}
				else {
					System.out.println("Unknown item");
				}
			}
			//Armor
			else if(tradeType == 1) {
				String currentItemName = armorMerchantItem[itemNum - 1];
				//Get the index of the weapon in gameplay data
				int index = 0;
				boolean foundItem = false;
				for(String name : armorName) {
					if(name.equals(currentItemName)) {
						foundItem = true;
						break;
					}
					index++;
				}
				
				if(foundItem) {
					System.out.println("🏷️ Name: " + armorName[index]);
					System.out.println("📜 Description: " + armorDescription[index]);
					System.out.println("🛡️ Defense: " + armorDef[index]);
					System.out.println("💰 cost: " + armorPrice[index] + " gold");
					price = armorPrice[index];
					itemName = armorName[index];
				}
				else {
					System.out.println("Unknown item");
				}
			}
			//Medicine
			else if(tradeType == 2) {
				String currentItemName = doctorItem[itemNum - 1];
				//Get the index of the weapon in gameplay data
				int index = 0;
				boolean foundItem = false;
				for(String name : medicineName) {
					if(name.equals(currentItemName)) {
						foundItem = true;
						break;
					}
					index++;
				}
				
				if(foundItem) {
					System.out.println("🏷️ Name: " + medicineName[index]);
					System.out.println("📜 Description: " + medicineDescription[index]);
					System.out.println("🔬 Medicine type: " + medicineType[index]);
					System.out.println("📌 Medicine effect: " + medicineEffect[index]);
					System.out.println("💰 cost: " + medicinePrice[index] + " gold");
					price = medicinePrice[index];
					itemName = medicineName[index];
				}
				else {
					System.out.println("Unknown item");
				}
			}
			//Item not found
			if(price == 0) {
			    continue;
			}
			//Purchase
			while(true) {
				System.out.println("\n❗ You can buy " + Main.asset / price + " of " + itemName + " ❗");
				System.out.print("\n⚖️ How many you want to buy?\n(Enter the amount you want to buy🔢, press any another key #️⃣ to return to the transaction ↩️)\nYour input:");
				String input = sc.nextLine();
				int numberOfItem = parseInt(input);
				
				if(numberOfItem == -1) break; //Input is a string, break loop and back to transaction
				else if (numberOfItem <= 0 || numberOfItem > Main.asset / price){
					System.out.println("Number is out of range");//Input is a number but out of bound, let user enter again
				}
				else {
					Main.asset -= numberOfItem * price;
					if(!fakeMerchantExist) { //Real merchant
						for(int i = 0; i < numberOfItem; i++) item.push(itemName);
					}
					else { //Fake merchant
						for(int i = 0; i < numberOfItem; i++) {
							if(rand.nextInt(2) == 0) item.push(itemName);
						}
					}
					break;
				}
				Main.clearConsole();
			}
			
		}
		
		if(fakeMerchantExist) System.out.println("You encouter fake merchant 🦹🏻‍♂️");
		
		return item;
	}
	
	public LinkedList<String> treasure() {
		LinkedList<String> items = new LinkedList<>();
		
		System.out.println("You found a treasure chest 🎁✨\n\nYou found:");
		for(int i = 0; i < 3; i++) {
			if(rand.nextInt(5) == 0) items.push(treasureChestRareItem[rand.nextInt(treasureChestRareItem.length - 1)]);
			else items.push(treasureChestCommonItem[rand.nextInt(treasureChestCommonItem.length - 1)]);
		}
		
		for(String item : items) {
			System.out.println("◆ " + item);
		}
		
		System.out.print("\nPress \'Enter\' to continue:");
		sc.nextLine();
		return items;
	}
	
	public static int parseInt(String numberString){
		int number = 0;
		int sign = 1;
		int startIndex = 0;
		//Convert to int
		for(int i = startIndex; i < numberString.length(); i++) {
			if (numberString.charAt(i) >= '0' && numberString.charAt(i) <= '9') {
				number = number * 10 + (numberString.charAt(i) - '0');
			}
			else return -1;//Not a int
		}
		return number * sign;
	}
}

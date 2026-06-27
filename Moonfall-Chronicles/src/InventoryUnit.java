
/*
 * InventoryUnit.java
 * Represents a single item stack in the inventory with its name and quantity.
 * Used by the Inventory class to store and manage individual items.
 */

// Represents a single item in the inventory
public class InventoryUnit {
	public String itemName;
	public int amount;
	
	public InventoryUnit(String itemName, int amount) {
		this.itemName = itemName;
		this.amount = amount;
	}
}

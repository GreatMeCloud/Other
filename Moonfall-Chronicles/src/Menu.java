import java.util.*;

/*
 * Menu.java
 * Displays in-game menu options and manages player settings including inventory view,
 * character equipment, cheat codes, save/load, and quit functions.
 */

// Handles in-game menu and settings options
public class Menu {
	Scanner sc = new Scanner(System.in);
	
	String[] menu = {
			"📂 Check inventory",
			"🎭 Modify character equipment",
			"🔐 Cheat code",
			"📥 Load a game",
			"💾 Save the game",
			"🛑 Quit the game",
			"🟢 Resume the game"
	};
	
	// Displays menu options and returns player's selection
	public String menuManager() {
		Main.clearConsole();
		
		String option;
		while(true) {
			displaySetting();
			System.out.print("\nSelect one option: ");
			option = sc.nextLine();
			if(option.equals("1")) return "display inventory";
			else if(option.equals("2")) return "character equipment";
			else if(option.equals("3")) return "cheat code";
			else if(option.equals("4")) return "load a game";
			else if(option.equals("5")) return "save the game";
			else if(option.equals("6")) return "quit the game";
			else if(option.equals("7")) return "resume the game";
			Main.clearConsole();
		}
	}
	
	// Shows available menu options to player
	public void displaySetting() {
		System.out.println("⚙️ Setting\n");
		for(int i = 0, l = menu.length; i < l; i++) {
			System.out.println("◆ " + (i + 1) + "." + menu[i]);
		}
	}
}

import java.util.List;
import java.util.ArrayList;

public class Player {
	private Location position;
	private int HP;
	private List<Item> inventory = new ArrayList<Item>();
	private List<WearableItem> clothing = new ArrayList<WearableItem>();

	public Player(Location position) {
		this.position = position;
		this.HP = 100;
	}

	public void doCommand(String command) {

		if (position.checkPath(command) == true) {
			setLocation(position.getPath(command));

		} else if (command.equalsIgnoreCase("inventory")) {
			showInventory();

		} else {
			position.doCommand(command, this);

		}
	}

	public void showInventory() {
		if (inventory.size() > 0) {
			System.out.println("Ditt inventory: ");
			inventoryList();
		} else {
			System.out.println("Ditt inventory är tomt");
		}
		showClothing();
	}

	public void showClothing() {
		if (clothing.size() > 0) {
			System.out.println("Du har på dig: ");
			clothingList();
		} else {
			System.out.println("Du är naken");
		}
	}

	public void inventoryList() {
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).getName() + " ");
		}
	}
	
	public ArrayList<Item> getInventory(){
		return (ArrayList<Item>) inventory;
	}

	public void clothingList() {
		for (int i = 0; i < clothing.size(); i++) {
			System.out.println(clothing.get(i).getName() + " ");
		}
	}

	public void addToInventory(Item item) {
		inventory.add(item);
		System.out.println(item.getName() + " har lagts till i ditt inventory");

	}

	public void removeFromInventory(Item item) {
		inventory.remove(item);
		System.out.println(item.getName() + " har tagits bort från ditt inventory");
	}

	public void addToClothing(WearableItem item) {
		clothing.add(item);
		System.out.println("Du har på dig " + item.getName());

	}

	public int getHP() {
		return HP;
	}

	public void addHP(int i) {
		HP += i;
	}

	public Location getLocation() {
		return position;
	}

	public void setLocation(Location position) {
		this.position = position;
		getLocation().describeLocation();
	}

}

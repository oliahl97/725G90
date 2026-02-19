import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Location {
	private String name;
	private String description;
	private boolean visited;
	private Map<String, Location> paths = new HashMap<String, Location>();
	private List<Item> locationItems = new ArrayList<Item>();
	private boolean access;

	public Location(String name, String description, Boolean access) {
		this.name = name;
		this.description = description;
		this.visited = false;
		this.access = access;
	}

	public abstract void describeYourself();

	public abstract void shortDescription();

	public void doCommand(String command, Player p) {

		String[] parts = command.split(" ");

		if (parts.length == 2 && parts[0].equalsIgnoreCase("take")) {
			String itemName = parts[1];
			takeItem(itemName, p);
		}

		else if (command.equalsIgnoreCase("look")) {
			describeYourself();
		} else {

			for (Item item : locationItems) {
                item.doCommand(command, p);
            }

		}

	}

	public void describeLocation() {
		if (!visited) {
			visited = true;
			{
				describeYourself();
			}
		} else {
			shortDescription();
		}
	}

	public void placeItem(Item item) {
		locationItems.add(item);
	}

	public void printLocationItems() {
		if (locationItems.size() > 0) {
			System.out.println("Föremål på platsen: ");
			for (int i = 0; i < locationItems.size(); i++) {
				System.out.println(locationItems.get(i).getName() + " ");
			}

		} else {
			System.out.println("Det finns inga föremål på platsen");
		}

	}

	public void takeItem(String name, Player p) {

		Item toTake = null;

		for (Item i : locationItems) {
			if (i.getName().equals(name)) {
				toTake = i;
				break;
			}
		}

		if (toTake != null) {
			p.addToInventory(toTake);
			locationItems.remove(toTake);
		} else {
			System.out.println("Det finns inget föremål att ta");
		}

	}

	public boolean checkPath(String s) {
		if (getPaths().containsKey(s) && getPath(s).getAccess()) {
			return true;
		} else if (getPaths().containsKey(s)) {
			System.out.println("Hit kan du inte gå!");
			return false;
		} else {
			return false;
		}

	}

	public void setAccess(Boolean access) {
		this.access = access;
	}

	public Boolean getAccess() {
		return access;
	}

	public void addPath(String s, Location l) {
		getPaths().put(s, l);
	}

	public Location getPath(String s) {
		return getPaths().get(s);

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Location> getPaths() {
		return paths;
	}
}
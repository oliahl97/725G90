
public abstract class Item {
	private String name;

	public Item(String name) {
		this.name = name;

	}

	public abstract void use(Player player);

	public String getName() {
		return name;
	}
	
	public void doCommand(String command, Player p) {
	    String[] parts = command.split(" ");

	    if (parts.length == 2 && parts[0].equalsIgnoreCase("use")) {
	        String itemName = parts[1];
	        for (Item item : p.getInventory()) {
	            if (item.getName().equalsIgnoreCase(itemName)) {
	                useItem(itemName, p);
	                return; // Avsluta loopen när föremålet har hittats och använts
	            }
	        }
	        System.out.println("Du har inte detta föremål");
	    } else {
	        System.out.println("Det där gick inte så bra va? >:) Försök igen!");
	    }
	}
	
	public void useItem(String name, Player p) {

		Item toUse = null;

		for (Item i : p.getInventory()) {
			if (i.getName().equals(name)) {
				toUse = i;
				break;
			}
		}

		if (toUse != null) {
			toUse.use(p);
		} else {
			System.out.println("Du har inte detta föremål");
		}
	}
	
	public boolean isCommandForThisItem(String commandItemName) {
        return commandItemName.equalsIgnoreCase(name);

}}

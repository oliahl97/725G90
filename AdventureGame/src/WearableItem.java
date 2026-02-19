
public class WearableItem extends Item {
	
	WearableItem(String name) {
		super(name);
	}

	@Override
	public void use(Player player) {

		System.out.println("Du tar på dig " + getName() + " och känner dig ascool");
		player.addHP(1000000);
		System.out.println("Ditt HP är nu " + player.getHP());
		player.addToClothing(this);
		player.removeFromInventory(this);

	}

}

public class SuperSecretUSB extends Tool {

	private Location access;

	SuperSecretUSB(String name, Location access) {
		super(name);
		this.access = access;

	}

	@Override
	public void use(Player player) {
		if (player.getLocation() == access) {
			player.removeFromInventory(this);
			System.out.println("Phew! Emma har nu fått USB stickan och ditt betyg i Java är säkrat :) <3");
			System.out.println("Du vann!!");
			System.exit(0);
		} else {
			System.out.println("Emma är inte här");
		}

	}

}


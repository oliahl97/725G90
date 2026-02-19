public class Food extends Item {
	private int raiseHP;

	Food(String name, int raiseHP) {
		super(name);
		this.raiseHP = raiseHP;

	}

	@Override
	public void use(Player player) {
		System.out.println("Du har fått energi!");
		player.addHP(raiseHP);
		System.out.println("Ditt HP är nu " + player.getHP());
		player.removeFromInventory(this);

	}

}
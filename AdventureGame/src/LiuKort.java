public class LiuKort extends Tool {

	private Location giveAccess;

	LiuKort(String name, Location giveAccess) {
		super(name);

		this.giveAccess = giveAccess;
	}

	@Override
	public void use(Player player) {
		System.out.println("WOW!! Cydpolen är nu öppen");
		giveAccess.setAccess(true);

	}

}

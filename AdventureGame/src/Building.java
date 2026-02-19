
public class Building extends Location {

	Building(String name, String description, Boolean access) {
		super(name, description, access);

	}

	Building(String name, String description) {
		this(name, description, true);
	}

	@Override
	public void describeYourself() {
		System.out.println("Du är i " + getName() + " " + getDescription());
		printLocationItems(); 
		printPaths();
	}
	
	public void printPaths() {
		System.out.println("Det finns vägar:");
		for (String direction : getPaths().keySet()) {
			System.out.println(direction);
		}
	}

	@Override
	public void shortDescription() {
		System.out.println("Du är tbx i " + getName() + " yayyyyy!!!!! ");
		printPaths();
	}

}
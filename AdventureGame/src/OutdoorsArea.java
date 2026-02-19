public class OutdoorsArea extends Location {
	private String weather;

	OutdoorsArea(String name, String description, Boolean access) {
		super(name, description, access);
		this.weather = getWeather();

	}
	
	OutdoorsArea(String name, String description, String weather) {
		this(name, description, true);
	}

	@Override
	public void describeYourself() {
		System.out.println("Du är i " + getName() + ". " + getWeather() + ". " + getDescription());
		printLocationItems();
		printPaths();
	}

	@Override
	public void shortDescription() {
		System.out.println("Du är tbx i " + getName() + " yayyyyy!!!!! " + getWeather());
		printPaths();
	}

	public String getWeather() {
		double whatWeather = Math.floor(Math.random() * 3 + 1);
		if (whatWeather == 1.0) {
			weather = "Det är jävulskt kallt och snöstorm";
		} else if (whatWeather == 2.0) {
			weather = "Solen skiner och det svider i ögonen";
		} else {
			weather = "Det spöregnar, jävla skit!";
		}
		return weather;
	}

	public void printPaths() {
		System.out.println("Det finns vägar:");
		for (String direction : getPaths().keySet()) {
			System.out.println(direction);
		}
	}

}
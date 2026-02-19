import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	private Player player;
	private List<Location> campus = new ArrayList<Location>();
	private List<Item> items = new ArrayList<Item>();

	public Game() {

	} 

	public void run() {
		String name;

		// skapar platser och lägger in dem i en lista
		Location markesbacken = new OutdoorsArea("Märkesbacken",
				"Cyklarna susar förbi, festeristerna hoppar och jublar. \nDet finns en väg east och en väg west.",
				true);
		campus.add(markesbacken);
		Location karallen = new Building("Kårallen",
				"Högtalarna donar med asbra musik från Forte! Fulla studenter vinglar omkring på dansgolvet. \nDet finns en dörr west och en dörr south.");
		campus.add(karallen);
		Location cydpolen = new Building("Cydpolen",
				"Lampan är släckt och alla dataingejörer som sitter i datasalen har fått fyrkantiga ögon. Emma lutar mot väggen med monster i handen. \nDet finns en dörr east och en dörr south.",
				false);
		campus.add(cydpolen);
		Location pressbyran = new Building("Pressbyrån",
				"Kön till Piccadelisalladen har aldrig varit så lång och kassören har aldrig varit så långsam. \nDet finns en dörr west och en dörr north.");
		campus.add(pressbyran);
		Location parken = new OutdoorsArea("Parken",
				"Ungar röker braj utanför fritidsgården. Tjackisen står och dyrkar nått av låsen. \nDet finns en väg north och en väg east.",
				true);
		campus.add(parken);

		// lista med items

		Item kir = new Food("kir", 40);
		Item korv = new Food("korv", 10);
		Item USBSticka = new SuperSecretUSB("USB-sticka", cydpolen);
		Item liuKort = new LiuKort("liukort", cydpolen);
		Item ovve = new WearableItem("ovve");

		items.add(liuKort);
		items.add(USBSticka);
		items.add(kir);
		items.add(korv);
		items.add(ovve);

		karallen.placeItem(kir);
		parken.placeItem(liuKort);
		markesbacken.placeItem(ovve);
		markesbacken.placeItem(USBSticka);
		pressbyran.placeItem(korv);
		

		// Skapar vägar mellan platser
		karallen.addPath("south", pressbyran);
		karallen.addPath("west", markesbacken);
		cydpolen.addPath("east", markesbacken);
		cydpolen.addPath("south", parken);
		parken.addPath("east", pressbyran);
		parken.addPath("north", cydpolen);
		pressbyran.addPath("west", parken);
		pressbyran.addPath("north", karallen);
		markesbacken.addPath("west", cydpolen);
		markesbacken.addPath("east", karallen);

		Scanner keyboard = new Scanner(System.in);

		System.out.print("Välkommen till Liu adventure game!\nVad heter du? ");
		name = keyboard.nextLine();
		player = new Player(campus.get(0));
		System.out.print("Hej " + name + ". Det är jag som är Magnus och jag behöver din hjälp!"
				+ "\nHär har jag en superhemlig, superviktig USB sticka med top secret super java kod som måste levereras till Emmma innan dagens slut. \n"
				+ "Jag hinner inte själv men litar på att du kirrar biffen. \n"
				+ "Magnus räcker ut handen och säger \"ta USB stickan\"\n"
				+ "Du kan ta dig runt genom att skriva north/south/west/east. \n"
				+ "Du kommer lära dig mer kommandon senare!" + " (Tips: det finns ett kommando \"help\").\n");

		player.getLocation().describeLocation();

		while (true) {
			String command;

			System.out.println("Vad vill du göra?");
			System.out.print("> ");
			command = keyboard.nextLine();
			doCommand(command);

		}
	}

	public void doCommand(String command) {
		if (command.equalsIgnoreCase("help")) {
			printInstructions();
		} else {
			player.doCommand(command);
		}

	}

	public void printInstructions() {
		System.out.println("----- MENY -----");
		System.out.println("north - Gå norrut");
		System.out.println("south - Gå söderut");
		System.out.println("west - Gå västerut");
		System.out.println("east - Gå österut");
		System.out.println("look - Titta på platsen");
		System.out.println("take - Ta ett föremål");
		System.out.println("use - Använd ett föremål");
		System.out.println("inventory - Visa inventory");
		System.out.println("help - Visa denna meny");
		System.out.println("-----------------");

	}

}

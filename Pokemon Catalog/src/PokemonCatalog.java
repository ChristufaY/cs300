import java.util.NoSuchElementException;

/**
 * This class provides a series of calls to the PokemonTree, to demonstrate its usage and help
 * you guide your testing and development.
 *
 */
public class PokemonCatalog {

	public static void main(String[] args) {
		PokemonTree bst = new PokemonTree();
		System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
		System.out.println(bst);
		bst.addPokemon(new Pokemon("Pikachu", "1,2,3"));
		bst.addPokemon(new Pokemon("Eevee", "2,2,4"));
		System.out.println("==============================================================");
		System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
		System.out.println(bst);
		bst.addPokemon(new Pokemon("Snorlax", "4,4,8"));
		System.out.println("==============================================================");
		System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
		System.out.println(bst);
		System.out.println("The Least Powerful Pokemon: " + bst.getLeastPowerfulPokemon());
		System.out.println("The Most Powerful Pokemon: " + bst.getMostPowerfulPokemon());
		bst.addPokemon(new Pokemon("Charmander", "3,2,1"));
		bst.addPokemon(new Pokemon("Mewtwo", "9,9,9"));
		System.out.println("==============================================================");
		System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
		System.out.println(bst);
		System.out.println("The Least Powerful Pokemon: " + bst.getLeastPowerfulPokemon());
		System.out.println("The Most Powerful Pokemon: " + bst.getMostPowerfulPokemon());
		bst.addPokemon(new Pokemon("Bulbasaur", "2,2,3"));
		bst.addPokemon(new Pokemon("Lapras", "7,3,5"));
		bst.addPokemon(new Pokemon("Rayquaza", "8,2,3"));
		bst.addPokemon(new Pokemon("Squirtle", "3,1,2"));
		System.out.println("==============================================================");
		System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
		System.out.println(bst);
		System.out.println("The Least Powerful Pokemon: " + bst.getLeastPowerfulPokemon());
		System.out.println("The Most Powerful Pokemon: " + bst.getMostPowerfulPokemon());
		System.out.println("==============================================================");
		try {
			System.out.println("Lookup query: search for the Pokemon whose CP is 123");
			System.out.println("Search Results: " + bst.lookup(123));
			System.out.println("Lookup query: search for the Pokemon whose CP is 256");
			System.out.println("Search Results: " + bst.lookup(256));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

}

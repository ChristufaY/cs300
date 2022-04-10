/**
 * This class models a simple Pokemon, based on name and CP.
 *
 */
public class Pokemon implements Comparable<Pokemon> {

	private String name; // name of the pokemon
	private final int CP; // combat power value of the pokemon

	/**
	 * Creates a new Pokemon with given attributes
	 * 
	 * @param combatPower CP value represents a function of combination of its
	 *                    attack, stamina and defense stats
	 * @throws IllegalArgumentException if the format of the combatPower is
	 *                                  incorrect.
	 */
	public Pokemon(String name, String stats) {
		// split the provided stats with respect to ","
		String[] parts = stats.split(",");
		// get the attack, stamina and defense
		if (parts.length != 3)
			throw new IllegalArgumentException("Incorrect format of stats.");
		this.name = name;

		int attack = Integer.parseInt(parts[0]);
		int stamina = Integer.parseInt(parts[1]);
		int defense = Integer.parseInt(parts[2]);
		this.CP = attack * 100 + stamina * 10 + defense;
	}

	/**
	 * Gets the name of the Pokemon
	 * 
	 * @return the name of the Pokemon
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets combat power value of the Pokemon
	 * 
	 * @return the combat power of the Pokemon
	 */
	public int getCP() {
		return this.CP;
	}

	/**
	 * Gets a String representation of the stats of the Pokemon
	 * 
	 * @return the stats of this Pokemon as a String in the format
	 * 
	 */
	public String getStringOfCP() {
		int att = this.CP / 100;
		int rem = this.CP % 100;
		int stm = rem / 10;
		int def = rem % 10;
		return "A:" + att + " S:" + stm + " D:" + def;
	}

	/**
	 * Returns a String representation of this Pokemon 
	 * For instance "[Pikachu CP:123 (A:1 S:2 D:3)]"
	 * 
	 * @return a String representation of this Pokemon
	 * 
	 */
	@Override
	public String toString() {
		return "[" + name + " CP:" + CP + " (" + getStringOfCP() + ")]";
	}

	/**
	 * Compares two Pokemons for ordering with respect to the combat power
	 * 
	 * @returns the value 0 if the argument otherPokemon has the same CP value as
	 *          this Pokemon; a value less than 0 if this Pokemon is less powerful
	 *          than the otherPokemon; and a value greater than 0 if this Pokemon is
	 *          more powerful than otherPokemon.
	 */
	@Override
	public int compareTo(Pokemon otherPokemon) {
		return this.CP - otherPokemon.CP;
	}

	/**
	 * Checks whether this Pokemon equals some other Pokemon
	 * 
	 * @return true if this Pokemon equals the input argument o and false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		return (o != null && o instanceof Pokemon && compareTo((Pokemon) o) == 0);
	}
}

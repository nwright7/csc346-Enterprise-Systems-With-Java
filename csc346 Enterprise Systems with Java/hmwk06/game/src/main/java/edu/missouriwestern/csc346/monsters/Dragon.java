package edu.missouriwestern.csc346.monsters;

/**
 * @author Nick Wright
 * @since April 2022
 *
 * The abstract class I have decided to add is dragons.
 * Dragons will either be a wyvern, or a hydra.
 *
 * Dragons are aggressive creatures, as such they will have a maxed aggressiveness value.
 *
 * Dragons are more than likely going to win, this is because a slime, giant rat, cook etc. does
 * not stand much of a chance against a giant fire breathing lizard.
 */

public abstract class Dragon extends Critter implements Defender {

    public Dragon(String species, double weight, String character){
        super(species, weight, 1.0, 1.0, 1.0, character);
        setImageCharacter(character);
    }

    @Override
    public String toString() {
        String result = getImageCharacter() + super.toString();
        return result;
    }

}//end of Dragon

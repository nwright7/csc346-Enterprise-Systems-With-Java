package edu.missouriwestern.csc346.monsters;

/**
 * @author Nick Wright
 * @since April 2022
 *
 * The first class I decided to add was slime.
 * Slimes are common in rpg's and are notorious for being
 * underwhelming as they are usually among the first enemies you fight.
 */

public class Slime extends Critter{
    public Slime(){
        super("Slime", 1.0, 0.3, 0.5, 0.3, "\uD83D\uDCA7");
        setImageCharacter("\uD83D\uDCA7");
    }

    @Override
    protected String getNoAttackMessage() { return "bounces harmlessly"; }

    @Override
    public void attack() {
        setAttackMessage("bounces with intent to kill");
        setAttackEffectiveness(0.1);
    }
}

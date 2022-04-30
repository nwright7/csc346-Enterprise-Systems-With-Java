import edu.missouriwestern.csc346.monsters.*;

import java.util.ArrayList;

/**
 * Driver for console version of fight game
 *
 * @author Nick Wright
 * @since April 2022
 */

public class App {
    public static void main(String[] args){

        char ch = '\uD83D';
        char ch2='\uDC35';
        String animal = "\uD83D\uDC35";

        //creating players
        //creating cooks
        Player cook1 = new Player ("Ptomain Tony", new Cook());
        Player cook2 = new Player("Broccoli Spears", new Cook());
        //creating rats
        Player rat1 = new Player("Ricky", new GiantRat());
        Player rat2 = new Player("Randy", new GiantRat());
        //creating slimes
        Player slime1 = new Player("Peter", new Slime());
        Player slime2 = new Player("Paul", new Slime());
        //creating hydras
        Player hydra1 = new Player("Draco", new Hydra());
        Player hydra2 = new Player("Seviper", new Hydra());
        //creating wyverns
        Player wyvern1 = new Player("Azure", new Wyvern());
        Player wyvern2 = new Player("Cerulean", new Wyvern());

        //adding all players to roster
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(cook1);
        roster.add(cook2);
        roster.add(rat1);
        roster.add(rat2);
        roster.add(slime1);
        roster.add(slime2);
        roster.add(hydra1);
        roster.add(hydra2);
        roster.add(wyvern1);
        roster.add(wyvern2);

        GameManager game = new GameManager();
        game.contest(roster);

        System.out.println("\nDone!");
    }
}

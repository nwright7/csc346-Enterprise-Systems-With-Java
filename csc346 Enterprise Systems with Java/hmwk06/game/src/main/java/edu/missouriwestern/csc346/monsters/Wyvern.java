package edu.missouriwestern.csc346.monsters;

/**
 * @author Nick Wright
 * @since April 2022
 *
 * Wyverns are very nimble, so they will dodge (aka defend) more effectively as their health is lowered.
 * (see defend method)
 */
public class Wyvern extends Dragon{
    public Wyvern(){
        super("Wyvern", 1000.0, "\uD83D\uDC09"+"\uD83D\uDC26");
    }

    @Override
    protected String getNoAttackMessage() {
        return "spreads its wings and lets out a might roar";
    }

    @Override
    protected void attack() {
        setAttackMessage("attacks with frightening speed.");
        setAttackEffectiveness(1.3*Math.random());
    }

    //the wyvern's defense will be better the lower its health is, essentially it will "dodge" better
    //the more it feels in danger.
    @Override
    public void defend() {
        if(getHealth()>0.8){
            setDefenseEffectiveness(0.6*Math.random());
        }else if(getHealth()>0.6 && getHealth()<=0.8){
            setDefenseEffectiveness(0.7*Math.random());
        }else if(getHealth()>0.4 && getHealth()<=0.6){
            setDefenseEffectiveness(0.8*Math.random());
        }else if(getHealth()>0.2 && getHealth()<=0.4){
            setDefenseEffectiveness(0.9*Math.random());
        }else{
            setDefenseEffectiveness(1.5*Math.random());
        }//end of if else
    }
}

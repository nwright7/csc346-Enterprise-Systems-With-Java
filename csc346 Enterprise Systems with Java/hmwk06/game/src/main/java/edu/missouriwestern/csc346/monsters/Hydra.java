package edu.missouriwestern.csc346.monsters;

/**
 * @author Nick Wright
 * @since April 2022
 *
 * Hydras will multiply heads when their health is lowered.
 * The more heads they have, the more effective their attacks will be.
 * (see attack method)
 */
public class Hydra extends Dragon{
    //numHeads holds the amount of heads the hydra has, they start with one head
    private int numHeads = 1;
    public Hydra(){
        super("Hydra", 1814.369, "\uD83D\uDC09" + "\uD83D\uDC0D");
        setNumOfHeads(numHeads);
    }

    @Override
    protected String getNoAttackMessage() {
        return "flails its " + getNumOfHeads() + " head(s).";
    }

    //the hydra's attack will be more effective the lower its health is, it will have more heads the lower its health is
    //dragons will have a high attack effectiveness since they are giant fire breathing lizards... enough said.
    @Override
    protected void attack() {
        if(getHealth()>0.8){
            setNumOfHeads(1);
            setAttackEffectiveness(1.2*Math.random());
        }else if(getHealth()>0.6 && getHealth()<=0.8){
            setNumOfHeads(2);
            setAttackEffectiveness(1.4*Math.random());
        }else if(getHealth()>0.4 && getHealth()<=0.6){
            setNumOfHeads(4);
            setAttackEffectiveness(1.6*Math.random());
        }else if(getHealth()>0.2 && getHealth()<=0.4){
            setNumOfHeads(8);
            setAttackEffectiveness(1.8*Math.random());
        }else{
            setNumOfHeads(16);
            setAttackEffectiveness(2*Math.random());
        }//end of if else
        setAttackMessage("attacks with its " + getNumOfHeads() + " head(s).");
    }

    @Override
    public void defend() {
        setDefenseMessage("Blocks with its " + getNumOfHeads() + " head(s).");
        setDefenseEffectiveness(0.5 + Math.random());
    }

    public int getNumOfHeads() {
        return numHeads;
    }

    public void setNumOfHeads(int numOfHeads) {
        this.numHeads = numOfHeads;
    }
}

package racoonslingermod.orbs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class BlastBurn extends AbstractOrb {

    public BlastBurn() {
        this.ID = "BlastBurn";
        this.name = "Blast Burn";
        this.evokeAmount = 8;
        this.passiveAmount = 3;

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "Deals damage when evoked.";
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public void onStartOfTurn() {
        // What happens at the start of your turn
    }

    @Override
    public void onEndOfTurn() {
        // What happens at the end of your turn
    }

    @Override
    public void triggerEvokeAnimation() {
        this.bobEffect.y = 0.0F;
    }

    @Override
    public void playChannelSFX() {

    }

    @Override
    public void applyFocus() {
        // How Focus affects your orb
    }

    @Override
    public AbstractOrb makeCopy() {
        return new BlastBurn();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }
}


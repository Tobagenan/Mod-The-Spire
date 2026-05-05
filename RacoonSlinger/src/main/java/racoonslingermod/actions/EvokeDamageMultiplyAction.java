package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


public class EvokeDamageMultiplyAction extends AbstractGameAction {
    private int multiplier;

    public EvokeDamageMultiplyAction(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (!p.orbs.isEmpty()) {
            AbstractOrb orb = p.orbs.get(0);

            int original = orb.evokeAmount;
            orb.evokeAmount = original * multiplier;

            p.evokeOrb();

            orb.evokeAmount = original;
        }

        isDone = true;
    }


}


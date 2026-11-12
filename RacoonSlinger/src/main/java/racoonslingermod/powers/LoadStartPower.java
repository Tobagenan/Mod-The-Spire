
package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.PierceB;

import static racoonslingermod.BasicMod.makeID;

public class LoadStartPower extends AbstractPower {

    public static final String POWER_ID = makeID(LoadStartPower.class.getSimpleName());

    public LoadStartPower(com.megacrit.cardcrawl.core.AbstractCreature owner, int amount) {
        this.name = "Load Start";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        loadRegion("loop"); //the icon needs to be replaced
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();

        for (int i = 0; i < amount; i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player != null) {
                        addToBot(new ChannelAction(new PierceB()));
                    }

                    isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        this.description = "At the start of your turn, channel "
                + amount + " Pierce B orb(s).";
    }
}

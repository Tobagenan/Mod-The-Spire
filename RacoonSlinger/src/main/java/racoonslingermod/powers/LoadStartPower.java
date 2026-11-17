
package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.PierceB;

import static racoonslingermod.BasicMod.makeID;

public class LoadStartPower extends AbstractPower {

    public static final String POWER_ID = makeID(LoadStartPower.class.getSimpleName());

    private static final PowerStrings powerStrings =
            CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

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
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}

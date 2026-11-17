
package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.util.HogWildTracker;

import static racoonslingermod.BasicMod.makeID;

public class HogWildPower extends AbstractPower {

    public static final String POWER_ID = makeID(HogWildPower.class.getSimpleName());

    private static final PowerStrings powerStrings =
            CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HogWildPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
        this.loadRegion("storm");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

        card.returnToHand = false;

        if (card.type == AbstractCard.CardType.ATTACK
                && card.costForTurn == 0
                && HogWildTracker.useReturn()) {

            this.flash();
            card.returnToHand = true;

            this.amount = HogWildTracker.getReturnsAvailable();
            this.updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        HogWildTracker.reset();
        this.amount = HogWildTracker.getReturnsAvailable();
        this.updateDescription();
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

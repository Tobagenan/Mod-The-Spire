
package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.BasicB;

public class PowerPlayPower extends AbstractPower {

    public static final String POWER_ID = "PowerPlayPower";

    public PowerPlayPower(AbstractCreature owner, int amount) {
        this.name = "";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("storm");
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER && this.amount > 0) {
            this.flash();

            for(int i = 0; i < this.amount; ++i) {
                this.addToBot(new ChannelAction(new BasicB()));
            }
        }

    }

    public void updateDescription() {
        this.description = "Load 1 Bullet when a Power is played";
    }
}

package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.util.MyTags;

import static racoonslingermod.BasicMod.makeID;

public class SliceandDicePower extends AbstractPower {
    public static final String POWER_ID = makeID(SliceandDicePower.class.getSimpleName());

    public SliceandDicePower(AbstractCreature owner, int amount) {
        this.name = "Slice and Dice";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        this.loadRegion("accuracy");

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(MyTags.BLADE)) {
            flash();
            addToBot(new GainEnergyAction(amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = "Gain " + this.amount + " [E] whenever you play a Blade card.";
    }

}


package racoonslingermod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.util.MyTags;

public class BladeCounterPower extends AbstractPower {
    public static final String POWER_ID = "RacoonslingerMod:BladeCounter";

    public BladeCounterPower(AbstractCreature owner) {
        this.name = "Blade Counter";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 0;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        loadRegion("accuracy");
        updateDescription();
    }

    public void increment() {
        this.amount++;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "Blade cards played this combat: " + amount + ".";
    }
}


package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.BasicB;

public class RebuttalPower extends AbstractPower {

    public static final String POWER_ID = "BasicBPower";

    public RebuttalPower(AbstractCreature owner, int orbAmount) {
        this.name = "";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = orbAmount;

        this.updateDescription();
        this.loadRegion("static_discharge"); //needs to be replaced with custom icon
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        if (info.type != DamageType.THORNS
                && info.type != DamageType.HP_LOSS
                && info.owner != null
                && info.owner != this.owner
                && damageAmount > 0) {

            this.flash();

            for (int i = 0; i < this.amount; ++i) {
                this.addToTop(
                        new ChannelAction(new BasicB())
                );
            }
        }

        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = "Channel " + this.amount
                + " Basic B orb when attacked.";
    }
}

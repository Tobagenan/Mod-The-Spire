package racoonslingermod.util;

import basemod.interfaces.OnCardUseSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import racoonslingermod.powers.BladeCounterPower;

public class BladeTracker implements OnCardUseSubscriber {

    @Override
    public void receiveCardUsed(AbstractCard card) {
        if (card.hasTag(MyTags.BLADE)) {

            if (AbstractDungeon.player.hasPower(BladeCounterPower.POWER_ID)) {
                ((BladeCounterPower) AbstractDungeon.player
                        .getPower(BladeCounterPower.POWER_ID)).increment();
            } else {
                BladeCounterPower power =
                        new BladeCounterPower(AbstractDungeon.player);
                power.amount = 1;

                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(
                                AbstractDungeon.player,
                                AbstractDungeon.player,
                                power
                        )
                );
            }
        }
    }
}
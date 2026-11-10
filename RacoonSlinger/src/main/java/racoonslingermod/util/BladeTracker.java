package racoonslingermod.util;

import basemod.interfaces.OnCardUseSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BladeTracker implements OnCardUseSubscriber {

    public static int bladeCount = 0;

    @Override
    public void receiveCardUsed(AbstractCard card) {
        if (card.hasTag(MyTags.BLADE)) {
            bladeCount++;
        }
    }

    public static void reset() {
        bladeCount = 0;
    }
}
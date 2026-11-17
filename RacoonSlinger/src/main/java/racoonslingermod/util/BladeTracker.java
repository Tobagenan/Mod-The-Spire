package racoonslingermod.util;

import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BladeTracker implements OnCardUseSubscriber, PostBattleSubscriber {

    public static int bladeCount = 0;

    @Override
    public void receiveCardUsed(AbstractCard card) {
        if (card.hasTag(MyTags.BLADE)) {
            bladeCount++;
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom room) {
        reset();
    }

    public static void reset() {
        bladeCount = 0;
    }
}

package racoonslingermod.util;

import basemod.interfaces.PostBattleSubscriber;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class LoadTracker implements PostBattleSubscriber {

    public static int loadCount = 0;

    public static void onOrbChanneled(AbstractOrb orb) {
        loadCount++;
    }

    public static void reset() {
        loadCount = 0;
    }

    @Override
    public void receivePostBattle(AbstractRoom room) {
        reset();
    }
}

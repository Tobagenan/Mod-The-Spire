package racoonslingermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.util.LoadTracker;

public class OrbChannelPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "channelOrb"
    )
    public static class ChannelOrbPatch {

        public static void Postfix(AbstractPlayer __instance, AbstractOrb orb) {
            LoadTracker.onOrbChanneled(orb);
        }
    }
}
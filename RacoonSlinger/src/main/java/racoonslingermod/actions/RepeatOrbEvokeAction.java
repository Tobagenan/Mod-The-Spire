package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class RepeatOrbEvokeAction extends AbstractGameAction {
    private AbstractOrb savedOrb;
    private int times;

    public RepeatOrbEvokeAction(int times, AbstractOrb savedOrb) {
        this.times = times;
        this.savedOrb = savedOrb;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (savedOrb == null || times <= 0) {
            isDone = true;
            return;
        }

        if (!p.orbs.isEmpty()) {
            p.evokeOrb();
        }

        for (int i = 1; i < times; i++) {
            savedOrb.makeCopy().onEvoke();
        }

        isDone = true;
    }
}
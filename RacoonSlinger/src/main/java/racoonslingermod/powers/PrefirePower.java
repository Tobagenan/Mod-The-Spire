package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.ManualPassive;

public class PrefirePower extends AbstractPower {

    public static final String POWER_ID = "RacoonslingerMod:Prefire";

    public PrefirePower(com.megacrit.cardcrawl.core.AbstractCreature owner, int amount) {
        this.name = "Prefire";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        loadRegion("loop"); //needs to be replaced with custom icon
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();

        for (int i = 0; i < amount; i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.player.orbs.isEmpty()) {
                        AbstractOrb orb = AbstractDungeon.player.orbs.get(0);

                        if (orb instanceof ManualPassive) {
                            ((ManualPassive) orb).triggerPassive();
                        }
                    }

                    isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        this.description = "At the start of your turn, trigger the first orb's passive " + amount + " times.";
    }
}
package racoonslingermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import racoonslingermod.orbs.ManualPassive;

import static racoonslingermod.BasicMod.makeID;

public class PrefirePower extends AbstractPower {

    public static final String POWER_ID = makeID(PrefirePower.class.getSimpleName());

    private static final PowerStrings powerStrings =
            CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

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
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
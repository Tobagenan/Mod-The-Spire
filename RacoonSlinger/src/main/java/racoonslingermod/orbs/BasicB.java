
package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;

import static racoonslingermod.BasicMod.makeID;

public class BasicB extends CustomOrb implements ManualPassive{

    public static final String ID = makeID(BasicB.class.getSimpleName());

    public BasicB() {
        super(ID, "Basic B",
                3, 3,
                "No passive effect",
                "Deals damage when evoked"
        );
    }

    @Override
    public void updateDescription() {
        this.description = "Deals " + this.evokeAmount + " damage when evoked.";
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target == null) return;

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        target,
                        new DamageInfo(
                                AbstractDungeon.player,
                                this.evokeAmount,
                                DamageInfo.DamageType.NORMAL
                        )
                )
        );
    }

    public void triggerPassive() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target == null) return;

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        target,
                        new DamageInfo(
                                AbstractDungeon.player,
                                this.evokeAmount,
                                DamageInfo.DamageType.NORMAL
                        )
                )
        );
    }

    @Override
    public void onStartOfTurn() {
        // No passive effect
    }

    @Override
    public void onEndOfTurn() {
        // No passive damage
    }

    @Override
    public void triggerEvokeAnimation() {
        this.bobEffect.y = 0.0F;
    }

    @Override
    public void playChannelSFX() {
    }

    @Override
    public void applyFocus() {
        int focus = 0;

        if (AbstractDungeon.player != null &&
                AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
            focus = AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount;
        }

        this.evokeAmount = Math.max(0, this.baseEvokeAmount + focus);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new BasicB();
    }
}

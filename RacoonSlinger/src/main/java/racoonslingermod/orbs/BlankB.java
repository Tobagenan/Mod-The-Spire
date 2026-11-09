package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static racoonslingermod.BasicMod.makeID;

public class BlankB extends CustomOrb {
    public static final String ID = makeID(BlankB.class.getSimpleName());

    public BlankB() {
        super(ID, "Blank",
                1, 1,
                "Evoke to fire a blank",
                "Changes enemy intent when evoked"
        );
    }

    @Override
    public void updateDescription() {
        this.description = "Applies weak and frail when evoked.";
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target == null) return;


        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        target,
                        AbstractDungeon.player,
                        new WeakPower(target, this.evokeAmount, false),
                        this.evokeAmount
                )
        );

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        target,
                        AbstractDungeon.player,
                        new FrailPower(target, this.evokeAmount, false),
                        this.evokeAmount
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
        return new BlankB();
    }
}

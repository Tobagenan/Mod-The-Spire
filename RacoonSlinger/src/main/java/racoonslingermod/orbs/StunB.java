package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;

import static racoonslingermod.BasicMod.makeID;

public class StunB extends CustomOrb implements ManualPassive{

    public static final String ID = makeID(StunB.class.getSimpleName());

    public StunB() {
        super(ID, "Blank",
                0, 0,
                "Stores stun energy",
                "Stuns an enemy when evoked"
        );
    }

    @Override
    public void updateDescription() {
        this.description = "Stuns enemy when evoked.";
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target != null) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            target,
                            AbstractDungeon.player,
                            new StunMonsterPower(target, 1),
                            this.evokeAmount
                    )
            );
        }
    }

    public void triggerPassive() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target != null) return;

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        target,
                        AbstractDungeon.player,
                        new StunMonsterPower(target, 1),
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
    protected void renderText(com.badlogic.gdx.graphics.g2d.SpriteBatch sb) {
        // Don't render orb numbers
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
        return new StunB();
    }
}

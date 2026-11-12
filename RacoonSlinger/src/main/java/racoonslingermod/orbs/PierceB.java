package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;

import static racoonslingermod.BasicMod.makeID;

public class PierceB extends CustomOrb implements ManualPassive {

    public static final String ID = makeID(PierceB.class.getSimpleName());

    public PierceB() {
        super(ID, "Pierce B",
                3, 3,
                "Deals damage to all enemies",
                "Deals damage to all enemies when evoked"
        );
    }

    @Override
    public void updateDescription() {
        this.description = "Deals " + this.evokeAmount + " damage to ALL enemies.";
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(
                        AbstractDungeon.player,
                        DamageInfo.createDamageMatrix(this.evokeAmount, true),
                        DamageInfo.DamageType.NORMAL,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                )
        );
    }

    public void triggerPassive() {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(
                        AbstractDungeon.player,
                        DamageInfo.createDamageMatrix(this.evokeAmount, true),
                        DamageInfo.DamageType.NORMAL,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                )
        );
    }

    @Override
    public void onStartOfTurn() {
        // No start of turn effect
    }

    @Override
    public void onEndOfTurn() {
        // No end of turn effect
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
        return new PierceB();
    }
}
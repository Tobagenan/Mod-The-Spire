
package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static racoonslingermod.BasicMod.makeID;

public class FFlash extends CustomOrb {

    public static final String ID = makeID(FFlash.class.getSimpleName());

    private int strengthReduction;

    public FFlash(){
        this(3,1);
    }
    public FFlash(int damage, int strengthReduction) {
        super(ID, "Frostflash",
                damage, damage,
                "Freezes enemies with concentrated energy",
                "Deals damage and reduces enemy Strength when evoked"
        );

        this.strengthReduction = strengthReduction;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "Deals " + this.evokeAmount
                + " damage and reduces enemy Strength by "
                + Math.abs(this.strengthReduction)
                + " when evoked.";
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

//        int damage = Math.round(this.evokeAmount * 1.5f);

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

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        target,
                        AbstractDungeon.player,
                        new StrengthPower(
                                target,
                                this.strengthReduction
                        ),
                        this.strengthReduction
                )
        );
    }

    @Override
    public void onStartOfTurn() {
    }

    @Override
    public void onEndOfTurn() {
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
        return new FFlash(
                this.baseEvokeAmount,
                this.strengthReduction
        );
    }
}

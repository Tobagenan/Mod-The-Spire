
package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.FocusPower;

import static racoonslingermod.BasicMod.makeID;

public class BBurn extends CustomOrb {

    public static final String ID = makeID(BBurn.class.getSimpleName());

    private int poisonAmount;

    public BBurn(int damage, int poison) {
        super(ID, "Blast Burn",
                damage, damage,
                "Builds up burning energy",
                "Deals increased damage and applies Poison when evoked"
        );

        this.poisonAmount = poison;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "Deals " + this.evokeAmount
                + " damage and applies "
                + this.poisonAmount + " Poison when evoked.";
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();

        if (target == null) return;

//        int damage = Math.round(this.evokeAmount * 1.5f);

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
                        new PoisonPower(
                                target,
                                AbstractDungeon.player,
                                this.poisonAmount
                        ),
                        this.poisonAmount
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
        return new BBurn(this.baseEvokeAmount, this.poisonAmount);
    }
}

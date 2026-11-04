package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;

import static racoonslingermod.BasicMod.makeID;

public class BigOne extends CustomOrb {



    public static final String ID = makeID(BigOne.class.getSimpleName());

    public BigOne(int damage) {
        super(ID, "The Big One",
                damage, damage,
                "Accumulates damage each turn",
                "Deals accumulated damage when evoked"
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

        float damage = this.evokeAmount;

        if (target.hasPower(LockOnPower.POWER_ID)) {
            damage *= 1.5f;
        }

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        target,
                        new DamageInfo(
                                AbstractDungeon.player,
                                Math.round(damage),
                                DamageInfo.DamageType.NORMAL
                        )
                )
        );
    }

    @Override
    public void onStartOfTurn() {
        // What happens at the start of your turn
    }

    @Override
    public void onEndOfTurn() {
        // What happens at the end of your turn
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
        return new BigOne(this.baseEvokeAmount);
    }

//    @Override
//    public void render(SpriteBatch spriteBatch) {
//
//    }
}

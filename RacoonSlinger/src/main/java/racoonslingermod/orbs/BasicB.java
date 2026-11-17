
package racoonslingermod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import racoonslingermod.powers.PiercePower;

import static racoonslingermod.BasicMod.makeID;

public class BasicB extends CustomOrb implements ManualPassive {

    public static final String ID = makeID(BasicB.class.getSimpleName());

    public BasicB() {
        super(ID, "Basic B",
                3, 3,
                "No passive effect",
                "Deals damage when evoked"
        );
        applyFocus();
    }

    @Override
    public void updateDescription() {
        this.description = "Deals " + this.evokeAmount + " damage when evoked.";
    }

    private boolean hasPierce() {
        return AbstractDungeon.player != null &&
                AbstractDungeon.player.hasPower(PiercePower.POWER_ID);
    }

    private void dealDamage() {
        if (hasPierce()) {
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDeadOrEscaped()) {
                    AbstractDungeon.actionManager.addToBottom(
                            new DamageAction(
                                    monster,
                                    new DamageInfo(
                                            AbstractDungeon.player,
                                            this.evokeAmount,
                                            DamageInfo.DamageType.NORMAL
                                    ),
                                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                            )
                    );
                }
            }
        } else {
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
    }

    @Override
    public void onEvoke() {
        dealDamage();
    }

    @Override
    public void triggerPassive() {
        dealDamage();
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
        updateDescription();
    }

    @Override
    public AbstractOrb makeCopy() {
        return new BasicB();
    }
}

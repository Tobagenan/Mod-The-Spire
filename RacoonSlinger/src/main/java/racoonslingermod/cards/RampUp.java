
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.powers.BladeCounterPower;

public class RampUp extends BaseCard {
    public static final String ID = makeID(RampUp.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public RampUp() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int bladeCount = 0;

        if (p.hasPower(BladeCounterPower.POWER_ID)) {
            bladeCount = p.getPower(BladeCounterPower.POWER_ID).amount;
        }

        if (bladeCount > 0) {
            addToBot(new ApplyPowerAction(
                    p,
                    p,
                    new StrengthPower(p, bladeCount),
                    bladeCount
            ));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RampUp();
    }
}


package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class AllIn extends BaseCard {

    public static final String ID = makeID(AllIn.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public AllIn() {
        super(ID, info);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int slotsLost = p.maxOrbs;

        if (slotsLost > 0) {
            int strengthGain = slotsLost * this.magicNumber;

            this.addToBot(new ApplyPowerAction(
                    p,
                    p,
                    new StrengthPower(p, strengthGain),
                    strengthGain
            ));

            for (int i = 0; i < slotsLost; i++) {
                this.addToBot(new com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction(1));
            }
        }
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AllIn();
    }
}

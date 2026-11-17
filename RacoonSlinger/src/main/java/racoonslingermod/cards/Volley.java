
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.orbs.BasicB;
import racoonslingermod.powers.PiercePower;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class Volley extends BaseCard {

    public static final String ID = makeID(Volley.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Volley() {
        super(ID, info);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(MyTags.LOAD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(PiercePower.POWER_ID)) {
            this.addToBot(
                    new ApplyPowerAction(
                            p,
                            p,
                            new PiercePower(p)
                    )
            );
        }

        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new ChannelAction(new BasicB()));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Volley();
    }
}

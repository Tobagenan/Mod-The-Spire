package racoonslingermod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.actions.DrawTaggedCardsAction;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class PotofGreed extends BaseCard{

    public static final String ID = makeID(racoonslingermod.cards.PotofGreed.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public PotofGreed() {
        super(ID, info);
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawTaggedCardsAction(p, this.magicNumber, MyTags.LOAD));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {return new PotofGreed();}
}

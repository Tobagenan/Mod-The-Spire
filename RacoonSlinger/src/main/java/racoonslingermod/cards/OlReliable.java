package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.unique.MulticastAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class OlReliable extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.OlReliable.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );

    public OlReliable() {
        super(ID,info);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MulticastAction(p, this.energyOnUse, this.upgraded, this.freeToPlayOnce));
    }



    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {return new OlReliable();}


}

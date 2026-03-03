package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.actions.CardSelectAction;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import java.util.List;

public class Mulligan extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.Mulligan.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Mulligan() {
        super(ID,info);

        this.exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CardSelectAction() {
            @Override
            protected void handleSelectedCards(List<AbstractCard> cards) {
                for (AbstractCard c : cards) {
                    AbstractDungeon.player.hand.moveToExhaustPile(c);
                }

                addToTop(new DrawCardAction(cards.size()));
            }
        });
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseCost = 1;
        }
    }

    @Override
    public AbstractCard makeCopy() {return new Mulligan();}

}

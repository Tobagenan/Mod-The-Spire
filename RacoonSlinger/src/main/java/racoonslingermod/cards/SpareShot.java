
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

import java.util.ArrayList;

public class SpareShot extends BaseCard {

    public static final String ID = makeID(SpareShot.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public SpareShot() {
        super(ID, info);

        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard randomLoad = getRandomLoadCard();

        if (randomLoad != null) {
            AbstractCard cardCopy = randomLoad.makeStatEquivalentCopy();

            cardCopy.setCostForTurn(0);

            this.addToBot(new MakeTempCardInHandAction(cardCopy));
        }
    }

    public static AbstractCard getRandomLoadCard() {
        ArrayList<AbstractCard> loadCards = new ArrayList<>();

        for (AbstractCard card : AbstractDungeon.srcCommonCardPool.group) {
            if (card.hasTag(MyTags.LOAD)) {
                loadCards.add(card);
            }
        }

        for (AbstractCard card : AbstractDungeon.srcUncommonCardPool.group) {
            if (card.hasTag(MyTags.LOAD)) {
                loadCards.add(card);
            }
        }

        for (AbstractCard card : AbstractDungeon.srcRareCardPool.group) {
            if (card.hasTag(MyTags.LOAD)) {
                loadCards.add(card);
            }
        }

        if (loadCards.isEmpty()) {
            return null;
        }

        return loadCards.get(
                AbstractDungeon.cardRandomRng.random(loadCards.size() - 1)
        );
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpareShot();
    }
}

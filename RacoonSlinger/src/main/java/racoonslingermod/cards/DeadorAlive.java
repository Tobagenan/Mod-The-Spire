package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.actions.CardSelectAction;
import racoonslingermod.actions.RepeatOrbEvokeAction;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import java.util.List;

import java.util.ArrayList;



public class DeadorAlive extends BaseCard {
    public static final String ID = makeID(DeadorAlive.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );

    private AbstractOrb orbCopy;

    public DeadorAlive() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            AbstractOrb orbCopy = p.orbs.isEmpty()
                    ? null
                    :p.orbs.get(0).makeCopy();

            this.addToBot(new CardSelectAction() {
                @Override
                protected void handleSelectedCards(List<AbstractCard> cards) {
                    super.handleSelectedCards(cards);

                    if (orbCopy != null && !cards.isEmpty()) {
                        this.addToTop(new RepeatOrbEvokeAction(
                                cards.size(),
                                orbCopy
                        ));
                    }
                }
            });

        } else {
            AbstractOrb orbCopy = null;

            if (!p.orbs.isEmpty()) {
                orbCopy = p.orbs.get(0).makeCopy();
            }

            ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>(p.hand.group);

            for (AbstractCard card : cardsToExhaust) {
                this.addToBot(new ExhaustSpecificCardAction(card, p.hand));
            }

            if (orbCopy != null && !cardsToExhaust.isEmpty()) {
                this.addToBot(new RepeatOrbEvokeAction(
                        cardsToExhaust.size() - 1,
                        orbCopy
                ));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(1);
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeadorAlive();
    }
}
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

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

    public DeadorAlive() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>(p.hand.group);

        int exhaustCount = cardsToExhaust.size();

        AbstractOrb orb = null;

        if (!p.orbs.isEmpty()) {
            orb = p.orbs.get(p.orbs.size() - 1);
        }

        for (AbstractCard card : cardsToExhaust) {
            this.addToBot(new ExhaustSpecificCardAction(card, p.hand));
        }

        if (orb != null) {
            AbstractOrb savedOrb = orb;

            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (int i = 0; i < exhaustCount; i++) {
                        savedOrb.onEvoke();
                    }

                    p.evokeOrb();
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(1);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeadorAlive();
    }
}
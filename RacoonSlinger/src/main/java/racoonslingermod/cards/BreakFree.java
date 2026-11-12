package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

import java.util.ArrayList;

public class BreakFree extends BaseCard {
    public static final String ID = makeID(BreakFree.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );

    public BreakFree() {
        super(ID, info);
        this.baseDamage = 8;
        this.damage = baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> statusCards = new ArrayList<>();

        for (AbstractCard card : p.hand.group) {
            if (card.type == CardType.STATUS) {
                statusCards.add(card);
            }
        }

        for (AbstractCard card : p.drawPile.group) {
            if (card.type == CardType.STATUS) {
                statusCards.add(card);
            }
        }

        for (AbstractCard card : p.discardPile.group) {
            if (card.type == CardType.STATUS) {
                statusCards.add(card);
            }
        }

        for (AbstractCard card : statusCards) {
            if (p.hand.contains(card)) {
                this.addToBot(new ExhaustSpecificCardAction(card, p.hand));
            } else if (p.drawPile.contains(card)) {
                this.addToBot(new ExhaustSpecificCardAction(card, p.drawPile));
            } else if (p.discardPile.contains(card)) {
                this.addToBot(new ExhaustSpecificCardAction(card, p.discardPile));
            }
        }

        for (int i = 0; i < statusCards.size(); i++) {
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster();

                    if (target != null) {
                        addToTop(new DamageAction(
                                target,
                                new DamageInfo(p, damage, damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HEAVY
                        ));
                    }

                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(3);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BreakFree();
    }
}
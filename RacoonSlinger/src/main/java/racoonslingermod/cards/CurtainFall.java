package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class CurtainFall extends BaseCard {
    public static final String ID = makeID(CurtainFall.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            5
    );

    public CurtainFall() {
        super(ID, info);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbCount = p.orbs.size();

        for (int i = 0; i < orbCount * 2; i++) {
            this.addToBot(new EvokeOrbAction(1));
        }
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.cost > 0) {
            this.updateCost(-1);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(4);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CurtainFall();
    }
}
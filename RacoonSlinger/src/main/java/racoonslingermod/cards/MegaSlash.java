
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class MegaSlash extends BaseCard {
    public static final String ID = makeID(MegaSlash.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    public MegaSlash() {
        super(ID, info);
        this.baseDamage = 18;
        this.tags.add(MyTags.BLADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(
                m,
                new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HEAVY
        ));

        MegaSlash copy = new MegaSlash();
        copy.updateCost(-2);

        addToBot(new MakeTempCardInDiscardAction(copy, 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(7);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MegaSlash();
    }
}

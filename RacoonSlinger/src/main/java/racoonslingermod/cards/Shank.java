package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction; import com.megacrit.cardcrawl.actions.common.DamageAction; import com.megacrit.cardcrawl.actions.defect.GashAction; import com.megacrit.cardcrawl.cards.AbstractCard; import com.megacrit.cardcrawl.cards.DamageInfo; import com.megacrit.cardcrawl.characters.AbstractPlayer; import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.actions.ShankAction;
import racoonslingermod.character.MyCharacter; import racoonslingermod.util.MyTags; import racoonslingermod.util.CardStats;

public class Shank extends BaseCard { public static final String ID = makeID(Shank.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    public Shank() {
        super(ID, info);
        this.baseDamage = 3;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(MyTags.BLADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(
                m,
                new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        ));

        this.addToBot(new ShankAction(this, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shank();
    }


}
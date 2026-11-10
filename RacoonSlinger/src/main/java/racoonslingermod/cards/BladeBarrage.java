package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;
import racoonslingermod.util.BladeTracker;

public class BladeBarrage extends BaseCard {
    public static final String ID = makeID(BladeBarrage.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public BladeBarrage() {
        super(ID, info);
        this.baseDamage = 3;
        this.tags.add(MyTags.BLADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int bladeCount = BladeTracker.bladeCount;

        int totalDamage = this.damage * bladeCount;

        addToBot(new DamageAction(
                m,
                new DamageInfo(p, totalDamage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL
        ));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(2);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BladeBarrage();
    }
}
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction; import com.megacrit.cardcrawl.actions.common.DamageAction; import com.megacrit.cardcrawl.cards.AbstractCard; import com.megacrit.cardcrawl.cards.DamageInfo; import com.megacrit.cardcrawl.characters.AbstractPlayer; import com.megacrit.cardcrawl.dungeons.AbstractDungeon; import com.megacrit.cardcrawl.monsters.AbstractMonster; import racoonslingermod.character.MyCharacter; import racoonslingermod.util.CardStats;

public class WildWest extends BaseCard { public static final String ID = makeID(WildWest.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            0
    );

    public WildWest() {
        super(ID, info);
        this.baseDamage = 3;
        this.damage = baseDamage;
    }

    @Override
    public void applyPowers() {
        int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();

        this.baseDamage = 3 * cardsPlayed;

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + cardStrings.EXTENDED_DESCRIPTION[1];

        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        int totalDamage = 3 * cardsPlayed;

        this.addToBot(new DamageAction(
                m,
                new DamageInfo(p, totalDamage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY
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
        return new WildWest();
    }


}
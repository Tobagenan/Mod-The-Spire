package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class Disarm extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.Disarm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    public Disarm() {
        super(ID, info);
        this.baseDamage = 3;
        this.baseMagicNumber= 1;
        this.magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(m, p, new FrailPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Disarm();
    }

}

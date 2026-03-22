package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class MagDump extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.MagDump.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public MagDump() {
        super(ID, info);
        this.baseDamage = 26;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new RemoveAllOrbsAction());
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(8);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new MagDump();}

}

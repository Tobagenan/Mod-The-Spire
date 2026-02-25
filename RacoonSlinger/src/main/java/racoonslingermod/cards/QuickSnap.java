package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class QuickSnap extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.QuickSnap.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public QuickSnap() {
        super(ID,info);
        this.baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new Frost()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new QuickSnap();
    }


}

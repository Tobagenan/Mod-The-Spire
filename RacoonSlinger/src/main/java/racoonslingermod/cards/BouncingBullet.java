
package racoonslingermod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.actions.RepeatOrbEvokeAction;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class BouncingBullet extends BaseCard {

    public static final String ID = makeID(BouncingBullet.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public BouncingBullet() {
        super(ID, info);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.orbs.isEmpty()) {
            AbstractOrb nextOrb = p.orbs.get(0);

            this.addToBot(new RepeatOrbEvokeAction(this.magicNumber, nextOrb));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BouncingBullet();
    }
}

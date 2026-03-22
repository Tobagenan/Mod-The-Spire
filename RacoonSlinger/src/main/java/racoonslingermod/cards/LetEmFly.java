package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

import java.util.ArrayList;

public class LetEmFly extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.LetEmFly.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    public LetEmFly() {
        super(ID, info);
    }

    //Need to make it so it evokes all then channels lightning, then evokes all and rechannels orbs
    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbCount = p.orbs.size();

        if (!this.upgraded) {
            for (int i = 0; i < orbCount; i++) {
                this.addToBot(new EvokeOrbAction(1));
            }

            for (int i = 0; i < orbCount; i++) {
                this.addToBot(new ChannelAction(new Lightning()));
            }
        } else {
            ArrayList<AbstractOrb> orbCopies = new ArrayList<>();

            for (AbstractOrb orb : p.orbs) {
                orbCopies.add(orb.makeCopy());
            }

            for (int i = 0; i < orbCopies.size(); i++) {
                this.addToBot(new EvokeOrbAction(1));
            }

            for (AbstractOrb orb : orbCopies) {
                this.addToBot(new ChannelAction(orb));
            }
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new LetEmFly();}

}

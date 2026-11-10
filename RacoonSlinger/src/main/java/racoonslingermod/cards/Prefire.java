package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.orbs.ManualPassive;
import racoonslingermod.powers.PrefirePower;
import racoonslingermod.util.CardStats;

public class Prefire extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.Prefire.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Prefire() {
        super(ID,info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(
                p,
                p,
                new PrefirePower(p, this.magicNumber),
                this.magicNumber
        ));
    }

    public void triggerOrbPassive(AbstractPlayer p) {
        if (p.orbs.isEmpty()) return;

        AbstractOrb orb = p.orbs.get(0);

        if (orb instanceof ManualPassive) {
            ((ManualPassive) orb).triggerPassive();
        }
    }

//    Function that calls the loop
//    public void atStartOfTurn() {
//        if (!AbstractDungeon.player.orbs.isEmpty()) {
//            this.flash();
//
//            for(int i = 0; i < this.amount; ++i) {
//                ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onStartOfTurn();
//                ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onEndOfTurn();
//            }
//        }
//
//    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new Prefire();}

}

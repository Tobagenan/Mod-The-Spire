package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.orbs.BigOne;
import racoonslingermod.actions.IncreaseOrbDamageAction;
import racoonslingermod.util.MyTags;

public class TheBigOne extends BaseCard{



    public static final String ID = makeID(racoonslingermod.cards.TheBigOne.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public TheBigOne() {
        super(ID, info);
        this.misc = 0;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(MyTags.LOAD);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        int increase = this.upgraded ? 3 : 2;

//        System.out.println("UUID: " + this.uuid);
//        System.out.println("Misc: " + this.misc);
//        System.out.println("Base Magic: " + this.baseMagicNumber);

        int orbDamage = this.baseMagicNumber + this.misc + increase;

        System.out.println("Calculated Orb Damage: " + orbDamage);

        this.addToBot(new IncreaseOrbDamageAction(this.uuid, increase));
        this.addToBot(new ChannelAction(new BigOne(orbDamage)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new TheBigOne();}
}

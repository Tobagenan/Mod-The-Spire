package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.StormPower;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class PowerPlay extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.PowerPlay.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public PowerPlay() {
        super(ID,info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //currently uses stormpower action, I want to replace this with orb loading power class
        this.addToBot(new ApplyPowerAction(p, p, new StormPower(p, this.magicNumber), this.magicNumber));
    }
//    THIS is the function storm uses not too bad would need to replace just the new lightning()
//    can change it to channel random, can change it once have different names for the bullets
//    animation effects need to change too
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == CardType.POWER && this.amount > 0) {
//            this.flash();
//
//            for(int i = 0; i < this.amount; ++i) {
//                this.addToBot(new ChannelAction(new Lightning()));
//            }
//        }
//
//    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new PowerPlay();}

}

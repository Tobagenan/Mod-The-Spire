package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.powers.SliceandDicePower;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class SliceandDice extends BaseCard{
    public static final String ID = makeID(racoonslingermod.cards.SliceandDice.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public SliceandDice() {
        super(ID, info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SliceandDicePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SliceandDice();
    }

}

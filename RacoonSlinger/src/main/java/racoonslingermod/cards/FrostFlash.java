package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.orbs.FFlash;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class FrostFlash extends BaseCard{

    public static final String ID = makeID(FrostFlash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public FrostFlash() {
        super(ID, info);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.baseDamage = 3;
        this.damage = this.baseDamage;

        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(MyTags.LOAD);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new FFlash(this.damage, -this.magicNumber)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }

    }

    public AbstractCard makeCopy() {
        return new FrostFlash();
    }
}

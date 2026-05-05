package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

public class PhotonBlaster extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.PhotonBlaster.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public PhotonBlaster() {
        super(ID, info);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(MyTags.LOAD);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new ChannelAction(new Plasma()));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(4);
            this.upgradeMagicNumber(1);
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {return new PhotonBlaster();}

}

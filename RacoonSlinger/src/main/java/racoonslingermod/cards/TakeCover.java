package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class TakeCover extends BaseCard {
    public static final String ID = makeID(racoonslingermod.cards.TakeCover.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public TakeCover() {
        super(ID,info);
        this.baseBlock = 0;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,p, this.block));

//        int orbCount = 0;
//
//        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
//            ++orbCount;
//        }

//        this.baseBlock = orbCount * this.magicNumber;
//        this.calculateCardDamage((AbstractMonster)null);

//        if (Settings.FAST_MODE) {
//            this.addToBot(new VFXAction(new BlizzardEffect(orbCount, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
//        } else {
//            this.addToBot(new VFXAction(new BlizzardEffect(orbCount, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
//        }
    }

    public void applyPowers() {
        int orbCount = 0;

        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            ++orbCount; //orb count = orbs channeled
        }

        this.baseBlock = orbCount * this.magicNumber;

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION;

        if (orbCount > 0) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();

        this.isBlockModified = this.block != this.baseBlock;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public AbstractCard makeCopy() {return new TakeCover();}

}

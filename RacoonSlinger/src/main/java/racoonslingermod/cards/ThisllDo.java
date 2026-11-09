package racoonslingermod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.*;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.orbs.*;
import racoonslingermod.util.CardStats;
import racoonslingermod.util.MyTags;

import java.util.ArrayList;

public class ThisllDo extends BaseCard{

    public static final String ID = makeID(ThisllDo.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public ThisllDo() {
        super(ID, info);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;

        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(MyTags.LOAD);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(ThisllDo.getRandomCustomOrb(true)));
    }

    public static AbstractOrb getRandomCustomOrb(boolean useCardRng) {
        ArrayList<AbstractOrb> orbs = new ArrayList();
        orbs.add(new BasicB());
        orbs.add(new BBurn());
        orbs.add(new BigOne());
        orbs.add(new FFlash());
        orbs.add(new StunB());
        orbs.add(new BlankB());
        return useCardRng ? (AbstractOrb)orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1)) : (AbstractOrb)orbs.get(MathUtils.random(orbs.size() - 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new ThisllDo();
    }

}

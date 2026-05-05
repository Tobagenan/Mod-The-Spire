package racoonslingermod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import racoonslingermod.actions.EvokeDamageMultiplyAction;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class JudgementDay extends BaseCard {
    public static final String ID = makeID(JudgementDay.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public JudgementDay() {
        super(ID, info);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EvokeDamageMultiplyAction(this.magicNumber));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        AbstractPlayer p = AbstractDungeon.player;

        if (!p.orbs.isEmpty()) {
            int base = p.orbs.get(0).evokeAmount;
            this.rawDescription = cardStrings.DESCRIPTION +
                    " NL Next Evoke: " + (base * this.magicNumber);
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new JudgementDay();
    }
}
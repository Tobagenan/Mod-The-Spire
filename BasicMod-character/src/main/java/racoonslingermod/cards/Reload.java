package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class Reload extends BaseCard {

    public static final String ID = makeID(Reload.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

        public Reload() {
            super(ID, info);
            this.showEvokeValue = true;
            this.showEvokeOrbCount = 2;
            this.baseMagicNumber = 2;
            this.magicNumber = this.baseMagicNumber;
        }


        public void use(AbstractPlayer p, AbstractMonster m) {
            for(int i = 0; i < this.magicNumber; ++i) {
                this.addToBot(new ChannelAction(new Lightning()));
            }

        }

        public void upgrade() {
            if (!this.upgraded) {
                this.upgradeName();
                this.upgradeBaseCost(0);
            }

        }

        public AbstractCard makeCopy() {
            return new Reload();
        }

}

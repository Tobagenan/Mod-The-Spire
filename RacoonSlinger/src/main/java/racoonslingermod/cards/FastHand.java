package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

    public class FastHand extends BaseCard {
        public static final String ID = makeID(racoonslingermod.cards.FastHand.class.getSimpleName()); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
        private static final CardStats info = new CardStats(
                MyCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
                CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
                CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
                CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
                1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
        );

    public FastHand() {
        super(ID, info);
        this.showEvokeValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeWithoutRemovingOrbAction(1));
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeOrbAction(1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }


    }

    public AbstractCard makeCopy() {
        return new FastHand();
    }
}

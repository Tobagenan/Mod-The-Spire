package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import racoonslingermod.character.MyCharacter;
import racoonslingermod.util.CardStats;

public class Fester extends BaseCard {
    public static final String ID = makeID(Fester.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );

    public Fester() {
        super(ID, info);
        this.baseMagicNumber = 1;
        this.magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int attackCount = 0;

        for (AbstractCard card : p.hand.group) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                attackCount++;
            }
        }

        for (int i = 0; i < attackCount; i++) {
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster();

            if (target != null) {
                this.addToBot(new ApplyPowerAction(
                        target,
                        p,
                        new VulnerablePower(target, magicNumber, false),
                        magicNumber
                ));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.exhaust = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fester();
    }
}
package racoonslingermod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
        int enemyCount = AbstractDungeon.getMonsters().monsters.size();

        for (int i = 0; i < enemyCount; i++) {
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster();

                    if (target != null) {
                        addToTop(new ApplyPowerAction(
                                target,
                                p,
                                new VulnerablePower(target, magicNumber, false),
                                magicNumber
                        ));
                    }

                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fester();
    }
}
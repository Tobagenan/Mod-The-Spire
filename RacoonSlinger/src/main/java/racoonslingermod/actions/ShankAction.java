package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction; import com.megacrit.cardcrawl.cards.AbstractCard; import com.megacrit.cardcrawl.dungeons.AbstractDungeon; import racoonslingermod.cards.Shank;

public class ShankAction extends AbstractGameAction { private AbstractCard card;

    public ShankAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    @Override
    public void update() {
        this.card.baseDamage += this.amount;
        this.card.applyPowers();

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof Shank) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof Shank) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Shank) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        this.isDone = true;
    }


}
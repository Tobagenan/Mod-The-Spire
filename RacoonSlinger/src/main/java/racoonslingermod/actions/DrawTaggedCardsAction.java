package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class DrawTaggedCardsAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard.CardTags tag;

    public DrawTaggedCardsAction(AbstractPlayer p, int amount, AbstractCard.CardTags tag) {
        this.p = p;
        this.amount = amount;
        this.tag = tag;
    }

    @Override
    public void update() {
        int drawn = 0;

        for (int i = p.drawPile.size() - 1; i >= 0 && drawn < amount; i--) {
            AbstractCard c = p.drawPile.group.get(i);

            if (c.hasTag(tag)) {
                p.drawPile.moveToHand(c);
                drawn++;
            }
        }

        isDone = true;
    }
}
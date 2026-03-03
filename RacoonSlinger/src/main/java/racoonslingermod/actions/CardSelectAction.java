package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.List;

public class CardSelectAction extends AbstractGameAction {

    private boolean screenOpened = false;
    private ArrayList<AbstractCard> selectedCards = new ArrayList<>();

    @Override
    public void update() {
        if (!screenOpened) {
            screenOpened = true;
            AbstractDungeon.handCardSelectScreen.open(
                    "Select cards",
                    AbstractDungeon.player.hand.size(),
                    true,  // any number
                    true   // can pick zero
            );
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                selectedCards.add(c);
            }

            handleSelectedCards(selectedCards);

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();

            isDone = true;
        }
    }

    protected void handleSelectedCards(List<AbstractCard> cards) {
        // default: exhaust all selected cards
        for (AbstractCard c : cards) {
            AbstractDungeon.player.hand.moveToExhaustPile(c);
        }
    }

    public int getNumberSelected() {
        return selectedCards.size();
    }
}


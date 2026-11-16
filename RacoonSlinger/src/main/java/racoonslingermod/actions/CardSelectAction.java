package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CardSelectAction extends AbstractGameAction {

    private boolean screenOpened = false;
    private ArrayList<AbstractCard> selectedCards = new ArrayList<>();
    private Consumer<List<AbstractCard>> onComplete;

    public CardSelectAction() {
        this.onComplete = null;
    }

    public CardSelectAction(Consumer<List<AbstractCard>> onComplete) {
        this.onComplete = onComplete;
    }

    @Override
    public void update() {
        if (!screenOpened) {
            screenOpened = true;

            AbstractDungeon.handCardSelectScreen.open(
                    "Select cards",
                    AbstractDungeon.player.hand.size(),
                    true,
                    true
            );
            return;
        }

        if (AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            return;
        }

        for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
            selectedCards.add(c);
        }

        AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        AbstractDungeon.handCardSelectScreen.selectedCards.clear();

        handleSelectedCards(selectedCards);

        if (onComplete != null) {
            onComplete.accept(selectedCards);
        }

        isDone = true;
    }

    protected void handleSelectedCards(List<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            AbstractDungeon.player.hand.moveToExhaustPile(c);
        }
    }

    public int getNumberSelected() {
        return selectedCards.size();
    }
}
package racoonslingermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class IncreaseOrbDamageAction extends AbstractGameAction {

    private final UUID uuid;
    private final int increase;

    public IncreaseOrbDamageAction(UUID uuid, int increase) {
        this.uuid = uuid;
        this.increase = increase;
    }

    @Override
    public void update() {

        java.util.Set<AbstractCard> updated =
                java.util.Collections.newSetFromMap(
                        new java.util.IdentityHashMap<>());

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(uuid) && updated.add(c)) {
                c.misc += increase;
                c.applyPowers();

                System.out.println("Updated master deck: " + c.misc);
            }
        }

        for (AbstractCard c : GetAllInBattleInstances.get(uuid)) {
            if (updated.add(c)) {
                c.misc += increase;
                c.applyPowers();

                System.out.println("Updated battle instance: " + c.misc);
            }
        }

        this.isDone = true;
    }
}
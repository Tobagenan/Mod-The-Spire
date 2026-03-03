package racoonslingermod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;

import static racoonslingermod.BasicMod.makeID;

public class LoseFocusPower extends AbstractPower {

    public static final String POWER_ID = makeID(LoseFocusPower.class.getSimpleName());
    private static final PowerStrings powerStrings =
            CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseFocusPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        // reuse existing icon there is a strength down texture not sure what
        // there's also the bias icon so maybe make similar
        this.img = new Texture("racoonslingermod/images/powers/focus_down.png");

        try {
            Texture largeTex = new Texture("racoonslingermod/images/powers/focus_down_large.png");
            this.region128 = new TextureAtlas.AtlasRegion(largeTex, 0, 0, 109, 109);
        } catch (Exception e) {
            // If the large PNG doesn’t exist, skip it — won’t crash
            this.region128 = null;
        }

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.owner.isPlayer) {
            this.flash();

            // Apply negative Focus
            addToBot(new ApplyPowerAction(
                    owner,
                    owner,
                    new FocusPower(owner, -this.amount),
                    -this.amount
            ));

            // Remove this power
            addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
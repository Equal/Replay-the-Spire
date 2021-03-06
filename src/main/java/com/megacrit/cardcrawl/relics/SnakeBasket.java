package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;

public class SnakeBasket extends AbstractRelic
{
    public static final String ID = "Basket Of Snakes";
    public static final int COUNT = 5;
    public static final int POISON = 2;
    
    public SnakeBasket() {
        super("Basket Of Snakes", "betaRelic.png", RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }
    
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + SnakeBasket.COUNT + this.DESCRIPTIONS[1] + SnakeBasket.POISON + this.DESCRIPTIONS[2];
    }
    
    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++this.counter;
            if (this.counter == SnakeBasket.COUNT) {
                this.counter = 0;
                this.flash();
                this.pulse = false;
            }
            else if (this.counter == SnakeBasket.COUNT - 1) {
                this.beginPulse();
                this.pulse = true;
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SnakeVenomPower(AbstractDungeon.player, SnakeBasket.POISON), SnakeBasket.POISON, true));
            }
        }
    }
    
    @Override
    public void atBattleStart() {
        if (this.counter == SnakeBasket.COUNT - 1) {
            this.beginPulse();
            this.pulse = true;
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SnakeVenomPower(AbstractDungeon.player, SnakeBasket.POISON), SnakeBasket.POISON, true));
        }
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new SnakeBasket();
    }
}

package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import basemod.abstracts.*;

public class TimeBomb extends CustomCard
{
    public static final String ID = "Time Bomb";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    
    public TimeBomb() {
        super("Time Bomb", TimeBomb.NAME, "cards/replay/replayBetaSkill.png", TimeBomb.COST, TimeBomb.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
		this.exhaust = true;
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
		//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, -1), -1));
		AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(2));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GlassOrb()));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GlassOrb()));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Dark()));
		AbstractDungeon.actionManager.addToBottom(new TimeBombAction(this.magicNumber));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new TimeBomb();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Time Bomb");
        NAME = TimeBomb.cardStrings.NAME;
        DESCRIPTION = TimeBomb.cardStrings.DESCRIPTION;
    }
}

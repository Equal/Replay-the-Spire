package com.megacrit.cardcrawl.cards.red;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.core.*;

public class DemonicInfusion extends CustomCard
{
    public static final String ID = "Demonic Infusion";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = -1;
    
    public DemonicInfusion() {
        super("Demonic Infusion", DemonicInfusion.NAME, "cards/replay/demonicInfusion.png", -1, DemonicInfusion.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.RARE, CardTarget.SELF);
		
		this.exhaust = true;
        this.baseMagicNumber = 0;
		this.magicNumber = this.baseMagicNumber;
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DemonicInfusionAction(AbstractDungeon.player, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new DemonicInfusion();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.upgradeMagicNumber(1);
            this.rawDescription = DemonicInfusion.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Demonic Infusion");
        NAME = DemonicInfusion.cardStrings.NAME;
        DESCRIPTION = DemonicInfusion.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = DemonicInfusion.cardStrings.UPGRADE_DESCRIPTION;
    }
}

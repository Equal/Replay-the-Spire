package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import basemod.abstracts.*;

public class Crystallizer extends CustomCard
{
    public static final String ID = "Crystallizer";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    
    public Crystallizer() {
        super("Crystallizer", Crystallizer.NAME, "cards/replay/replayBetaPower.png", Crystallizer.COST, Crystallizer.DESCRIPTION, CardType.POWER, CardColor.BLUE, CardRarity.RARE, CardTarget.SELF);
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CrystallizerPower(p, 1), 1));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new Crystallizer();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(Crystallizer.COST - 1);
            this.isInnate = true;
            this.rawDescription = Crystallizer.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Crystallizer");
        NAME = Crystallizer.cardStrings.NAME;
        DESCRIPTION = Crystallizer.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Crystallizer.cardStrings.UPGRADE_DESCRIPTION;
    }
}

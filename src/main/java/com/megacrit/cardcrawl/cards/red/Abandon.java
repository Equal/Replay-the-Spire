package com.megacrit.cardcrawl.cards.red;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.unique.AbandonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import basemod.*;
import basemod.abstracts.*;

public class Abandon
  extends CustomCard
{
  public static final String ID = "Abandon";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Abandon");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 0;
  private static final int POOL = 1;
  
  public Abandon()
  {
    super("Abandon", NAME, "cards/replay/abandon.png", 0, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE);
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
    this.isEthereal = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    //AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, this.magicNumber, false, true, false));
	AbstractDungeon.actionManager.addToBottom(new AbandonAction(p, p, this.magicNumber, this.upgraded));
    //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
  }
  
  public AbstractCard makeCopy()
  {
    return new Abandon();
  }
  
  public void triggerOnEndOfPlayerTurn()
  {
    AbstractDungeon.actionManager.addToTop(new ExhaustAllEtherealAction());
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(2);
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}

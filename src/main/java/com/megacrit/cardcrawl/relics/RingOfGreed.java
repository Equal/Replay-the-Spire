package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.rewards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;
import java.util.*;

public class RingOfGreed extends AbstractRelic
{
    public static final String ID = "Ring of Greed";
    private static final int GAIN_GOLD = 50;
    private static final int GAIN_RELICS = 2;
    private static final int GOLD_TRIGGER = 150;
	public static final ArrayList<String> greedlist;
	private int relicsLeft;
	private ArrayList<String> greedpool;
	
    public RingOfGreed() {
        super("Ring of Greed", "cring_greed.png", RelicTier.SPECIAL, LandingSound.FLAT);
		this.counter = GOLD_TRIGGER;
		this.relicsLeft = 0;
    }
    
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + GAIN_GOLD + this.DESCRIPTIONS[1] + GAIN_RELICS + this.DESCRIPTIONS[2] + GOLD_TRIGGER + this.DESCRIPTIONS[3];
    }
    
    public void onPlayerGainGold(int amount) {
		if (AbstractDungeon.player.hasRelic("Ectoplasm")) {
			return;
		}
        this.flash();
        this.counter -= amount;
		while (this.counter <= 0) {
			this.counter += GOLD_TRIGGER;
			final AbstractCard greedCurse = AbstractDungeon.getCard(AbstractCard.CardRarity.CURSE);
			UnlockTracker.markCardAsSeen(greedCurse.cardID);
			AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(greedCurse, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, false));
		}
    }
	
    @Override
    public void onEquip() {
		this.counter = GOLD_TRIGGER;
		AbstractDungeon.player.gainGold(GAIN_GOLD);
		this.greedpool = new ArrayList<String>();
		for (String key : RingOfGreed.greedlist) {
			if (AbstractDungeon.commonRelicPool.contains(key) ||
			AbstractDungeon.uncommonRelicPool.contains(key) ||
			AbstractDungeon.rareRelicPool.contains(key) ||
			AbstractDungeon.shopRelicPool.contains(key)) {
				this.greedpool.add(key);
			}
		}
		if (!AbstractDungeon.id.equals("Exordium") && !AbstractDungeon.player.hasRelic(GoldenIdol.ID)) {
			this.greedpool.add(GoldenIdol.ID);
		}
		Collections.shuffle(this.greedpool);
		this.greedpool.add(TinyHouse.ID);
		this.greedpool.add(RedCirclet.ID);
		this.relicsLeft = GAIN_RELICS;
    }
    

    @Override
    public void update() {
        super.update();
        if (this.relicsLeft > 0 && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            while (this.relicsLeft > 0) {
            	String gimme = this.greedpool.remove(0);
            	AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(RelicLibrary.getRelic(gimme).makeCopy()));
            	this.relicsLeft--;
            	Iterator<String> s = AbstractDungeon.commonRelicPool.iterator();
                while (s.hasNext()) {
                    final String derp = s.next();
                    if (derp.equals(gimme)) {
                        s.remove();
                        break;
                    }
                }
                s = AbstractDungeon.uncommonRelicPool.iterator();
                while (s.hasNext()) {
                    final String derp = s.next();
                    if (derp.equals(gimme)) {
                        s.remove();
                        break;
                    }
                }
                s = AbstractDungeon.rareRelicPool.iterator();
                while (s.hasNext()) {
                    final String derp = s.next();
                    if (derp.equals(gimme)) {
                        s.remove();
                        break;
                    }
                }
                s = AbstractDungeon.bossRelicPool.iterator();
                while (s.hasNext()) {
                    final String derp = s.next();
                    if (derp.equals(gimme)) {
                        s.remove();
                        break;
                    }
                }
                s = AbstractDungeon.shopRelicPool.iterator();
                while (s.hasNext()) {
                    final String derp = s.next();
                    if (derp.equals(gimme)) {
                        s.remove();
                        break;
                    }
                }
            }
            AbstractDungeon.combatRewardScreen.positionRewards();
            AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25f;
        }
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new RingOfGreed();
    }
	
    static {
		greedlist = new ArrayList<String>();
		greedlist.add(TinyChest.ID);
		greedlist.add(SmilingMask.ID);
		greedlist.add(Courier.ID);
		greedlist.add(OldCoin.ID);
		greedlist.add(MembershipCard.ID);
		greedlist.add(MealTicket.ID);
		greedlist.add(MawBank.ID);
		greedlist.add(SsserpentHead.ID);
		greedlist.add(TagBag.ID);
		greedlist.add(Bandana.ID);
		greedlist.add(GoldenEgg.ID);
	}
}

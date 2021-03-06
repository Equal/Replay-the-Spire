package replayTheSpire.patches;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.GremlinFood;
import com.megacrit.cardcrawl.rooms.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import replayTheSpire.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import java.util.*;
import com.megacrit.cardcrawl.vfx.campfire.*;
import basemod.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.daily.*;
import com.megacrit.cardcrawl.ui.campfire.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.core.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.rooms.CampfireUI", method = "initializeButtons")
public class ReplayCampButtonsPatch
{
    public static void Postfix(final Object meObj) {

		if (AbstractDungeon.player.hasRelic("Chameleon Ring")) {
			final CampfireUI campfire = (CampfireUI)meObj;
			try {
				final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)campfire, (Class)CampfireUI.class, "buttons");
				final ChameleonBrewOption button = new ChameleonBrewOption();
				campfireButtons.add(button);
				float x = 950.f;
				float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
				if (campfireButtons.size() % 2 == 0) {
					x = 1110.0f;
					campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
				}
				campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
			}
			catch (SecurityException | IllegalArgumentException ex2) {
				//final RuntimeException ex;
				//final RuntimeException e = ex;
				//e.printStackTrace();
			}
		}
		if (AbstractDungeon.player.hasRelic("ReplayTheSpireMod:Pickaxe")) {
			final CampfireUI campfire = (CampfireUI)meObj;
			try {
				final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)campfire, (Class)CampfireUI.class, "buttons");
				final PickMineOption button = new PickMineOption();
				campfireButtons.add(button);
				float x = 950.f;
				float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
				if (campfireButtons.size() % 2 == 0) {
					x = 1110.0f;
					campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
				}
				campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
			}
			catch (SecurityException | IllegalArgumentException ex2) {
				//final RuntimeException ex;
				//final RuntimeException e = ex;
				//e.printStackTrace();
			}
		}
		if (AbstractDungeon.player.hasRelic("ReplayTheSpireMod:PocketPolymizer")) {
			final CampfireUI campfire = (CampfireUI)meObj;
			try {
				final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)campfire, (Class)CampfireUI.class, "buttons");
				final PolymerizeTransformOption button = new PolymerizeTransformOption();
				campfireButtons.add(button);
				float x = 950.f;
				float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
				if (campfireButtons.size() % 2 == 0) {
					x = 1110.0f;
					campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
				}
				campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
			}
			catch (SecurityException | IllegalArgumentException ex2) {
				//final RuntimeException ex;
				//final RuntimeException e = ex;
				//e.printStackTrace();
			}
		}
		if (AbstractDungeon.currMapNode == BonfirePatches.bonfireNode) {
			final CampfireUI campfire = (CampfireUI)meObj;
			try {
				final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)campfire, (Class)CampfireUI.class, "buttons");
				
				final ArrayList<AbstractCampfireOption> optionsYo = new ArrayList<AbstractCampfireOption>();
				if (!AbstractDungeon.player.hasRelic("Coffee Dripper")) {
					optionsYo.add(new RestOption());
				}
				if (!DailyMods.negativeMods.get("Midas") && !AbstractDungeon.player.hasRelic("Fusion Hammer")) {
					optionsYo.add(new SmithOption(AbstractDungeon.player.masterDeck.getUpgradableCards().size() > 0));
				}
				if (!AbstractDungeon.player.hasRelic("Peace Pipe") ) {
					optionsYo.add(new TokeOption(true));
				}
				if (!AbstractDungeon.player.hasRelic("Shovel")) {
					optionsYo.add(new DigOption());
				}
				if (!AbstractDungeon.player.hasRelic("Chameleon Ring")) {
					optionsYo.add(new ChameleonBrewOption());
				}
				if (!AbstractDungeon.player.hasRelic("ReplayTheSpireMod:Pickaxe")) {
					optionsYo.add(new PickMineOption());
				}
				if (!AbstractDungeon.player.hasRelic("ReplayTheSpireMod:PocketPolymizer")) {
					optionsYo.add(new PolymerizeTransformOption());
				}
				if (!AbstractDungeon.player.hasRelic(GremlinFood.ID)) {
					optionsYo.add(new BonfireMultitaskOption());
				}
				if (!AbstractDungeon.eventList.isEmpty()) {
					optionsYo.add(new BonfireExploreOption());
				}
				
				Collections.shuffle(optionsYo);
				boolean foundrest = false;
				boolean foundsmith = false;
				optionsYo.add(new BonfireMultitaskOption());
				optionsYo.add(new SmithOption(AbstractDungeon.player.masterDeck.getUpgradableCards().size() > 0));
				optionsYo.add(new RestOption());
				
				for (AbstractCampfireOption o : campfireButtons) {
					if (o instanceof RestOption) {
						optionsYo.get(0).setPosition(o.hb.cX, o.hb.cY);
						campfireButtons.set(campfireButtons.indexOf(o), optionsYo.get(0));
						foundrest = true;
					}
					if (o instanceof SmithOption) {
						optionsYo.get(1).setPosition(o.hb.cX, o.hb.cY);
						campfireButtons.set(campfireButtons.indexOf(o), optionsYo.get(1));
						foundsmith = true;
					}
				}
				
				if (!foundrest) {
					campfireButtons.add(optionsYo.get(0));
					float x = 950.f;
					float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
					if (campfireButtons.size() % 2 == 0) {
						x = 1110.0f;
						campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
					}
					campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
				}
				if (!foundsmith) {
					campfireButtons.add(optionsYo.get(1));
					float x = 950.f;
					float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
					if (campfireButtons.size() % 2 == 0) {
						x = 1110.0f;
						campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
					}
					campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
				}
				
				campfireButtons.add(optionsYo.get(2));
				float x = 950.f;
				float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
				if (campfireButtons.size() % 2 == 0) {
					x = 1110.0f;
					campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
				}
				campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
			}
			catch (SecurityException | IllegalArgumentException ex2) {
				//final RuntimeException ex;
				//final RuntimeException e = ex;
				//e.printStackTrace();
			}
		}
		if (AbstractDungeon.player.hasRelic("Dead Branch")) {
			final CampfireUI campfire = (CampfireUI)meObj;
			try {
				final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)campfire, (Class)CampfireUI.class, "buttons");
				final BranchBurnOption button = new BranchBurnOption();
				campfireButtons.add(button);
				float x = 950.f;
				float y = 990.0f - (270.0f * (float)((campfireButtons.size() + 1) / 2));
				if (campfireButtons.size() % 2 == 0) {
					x = 1110.0f;
					campfireButtons.get(campfireButtons.size() - 2).setPosition(800.0f * Settings.scale, y * Settings.scale);
				}
				campfireButtons.get(campfireButtons.size() - 1).setPosition(x * Settings.scale, y * Settings.scale);
			}
			catch (SecurityException | IllegalArgumentException ex2) {
				//final RuntimeException ex;
				//final RuntimeException e = ex;
				//e.printStackTrace();
			}
		}
    }
}
package replayTheSpire.patches;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.audio.*;
import com.megacrit.cardcrawl.helpers.*;
import replayTheSpire.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.*;
import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.monsters.thetop.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.replay.*;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.metrics.*;
import com.megacrit.cardcrawl.scenes.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.vfx.scene.*;
import com.megacrit.cardcrawl.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.rooms.*;

public class ReplayCityScenePatch {
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.scenes.TheCityScene", method = "randomizeScene")
	public static class CitySceneRandomizePatch {
		public static void Postfix(TheCityScene __instance) {
			if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom && AbstractDungeon.getCurrRoom().monsters.getMonster("PondfishBoss") != null) {//AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss && 
				ReplayTheSpireMod.renderFishFG = true;
			}
			else {
				ReplayTheSpireMod.renderFishFG = false;
			}
		}
	}
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.scenes.TheCityScene", method = "renderCombatRoomFg")
	public static class CitySceneFGPatch {
		public static void Prefix(TheCityScene __instance, final SpriteBatch sb) {
			if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom && ReplayTheSpireMod.renderFishFG) {
				sb.draw(ReplayTheSpireMod.fishFG.getTexture(), ReplayTheSpireMod.fishFG.offsetX * Settings.scale, ReplayTheSpireMod.fishFG.offsetY * Settings.scale + AbstractDungeon.sceneOffsetY, 0.0f, 0.0f, ReplayTheSpireMod.fishFG.packedWidth, ReplayTheSpireMod.fishFG.packedHeight, Settings.scale, Settings.scale, 0.0f, ReplayTheSpireMod.fishFG.getRegionX(), ReplayTheSpireMod.fishFG.getRegionY(), ReplayTheSpireMod.fishFG.getRegionWidth(), ReplayTheSpireMod.fishFG.getRegionHeight(), false, false);
			}
			//__instance.renderAtlasRegionIf(sb, ReplayCityScenePatch.fishFG, ReplayCityScenePatch.renderFishFG);
		}
	}
	
}
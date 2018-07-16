package dangeon.model.map.field.random.bossmap;

import java.awt.Point;

import main.res.BGM;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.朱鷺子;
import dangeon.model.object.creature.player.Player;

public class BossMap_Tokiko extends BossMap {
	private static final long serialVersionUID = 1L;

	// @Override
	// public Point getEntrancePoint() {
	// return MassCreater.getCreatureIp();
	// }

	public BossMap_Tokiko(Base_Map_Random bmr) {
		super(bmr, 3, new Point(20, 16));
	}

	@Override
	public BGM getBGM() {
		return BGM.izanagi;
	}

	@Override
	protected Base_Enemy getBoss() {
		return new 朱鷺子(MassCreater.getCreatureIp(), 4);
	}

	@Override
	public int getDefaultEnemyNumber() {
		return 4;
	}

	@Override
	public String getMapName() {
		return "始まりの自由帳";
	}

	// @Override
	// public Image_MapTip getMapTip() {
	// return Image_MapTip.スキマ;
	// }

	@Override
	protected Base_Artifact getStair() {
		String str;
		str = "最深層から帰還した";
		if (Player.me.flag_no_item) {
			str = "持ち込みなしで" + str;
		}
		階段戻り a = new 階段戻り(MassCreater.getStairsIP(), str, BMR) {
			private static final long serialVersionUID = 1L;

			@Override
			public String[] getSlection() {
				return new String[] { "ダンジョンから帰還する", "やめる" };
			}

			@Override
			public void move() {
				BMR.getStoryManager_ClearFlag().saveThisFinished();
				super.move();
			};
		};
		return a;
	}

	@Override
	protected void message() {
		Message.set("「うッわーーーーッ！！」@");
		Message.set("「わ…わたしの【本を読む程度の能力】…」@");
		Message.set("「この能力で、ただ落ちていた本を読んでいただけなのにィ～～」@");
		Message.set("「私は結局退治される運命なのね…」@");
	}

}

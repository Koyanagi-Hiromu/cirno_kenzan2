package dangeon.model.map.field.special.map;

import java.awt.Point;
import java.util.AbstractList;

import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.FixedMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.合成NPC;
import dangeon.model.object.creature.npc.合成説明NPC;
import dangeon.model.object.creature.npc.second.NPCパチェ_inスキマ;

public class GouseiMap extends FixedMap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Base_Map_Random BM;

	public GouseiMap(Base_Map_Random bm) {
		super("gousei");
		BM = bm;
	}

	@Override
	public BGM getBGM() {
		return BGM.recess;
	}

	@Override
	public String getClassName() {
		return "ダンジョンのスキマ";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(19, 14);
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.danjon_no_sukima;
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.スキマ;
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		getObjectList_random();
		String[] msg = null;
		if (BM instanceof Base_Map_Random) {
			int[] arr = BM.getGouseiFloor();
			if (arr != null) {
				for (int i : arr) {
					if (i > MapList.getFloor()) {
						String m[] = {
								"次のスキマは" + (半角全角コンバーター.半角To全角数字(i))
										+ "階の次につないでおくわ@", "頑張りなさいチルノ" };
						msg = m;
						break;
					}
				}
			}
		}
		if (msg == null) {
			String m[] = { "これがこのダンジョンで私ができる最後のサポートよ@", "カードも杖も惜しまず使うのよ？" };
			msg = m;
		}
		add(new Abstract_NPC(CHARA_IMAGE.八雲紫, "八雲紫", 21, 13, false, true, msg)
				.setDirection(DIRECTION.LEFT));
		add(new Stairs(new Point(21, 14), BM));
		return super.getObjectList();
	}

	private void getObjectList_random() {
		add(new NPCパチェ_inスキマ(17, 12).setDirection(DIRECTION.DOWN));
		add(new 合成説明NPC(18, 12, true).setDirection(DIRECTION.DOWN));
		add(new 人形(new Point(18, 14), 1));
		add(new 合成NPC(new Point(19, 12), CHARA_IMAGE.にとり, "河城にとり", "よ！"));
	}

	@Override
	public Base_Map_Random getParentRandomMap() {
		return BM;
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}

	@Override
	public boolean isDungeon() {
		return true;
	}
}

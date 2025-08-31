package dangeon.model.map.field;

import java.awt.Point;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.table.SpecialMonsterHouse;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.二撃必殺;
import dangeon.model.map.field.random.second.持ち上げダンジョン;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import main.util.Show;

public abstract class Base_Map implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public final String FILE_NAME;
	public final int RANDOM_LENGTH;
	protected final ArrayList<Base_MapObject> list_object = new ArrayList<Base_MapObject>();
	public final int SPECIAL_FLOOR = 5;
	public int SPECIAL_NUMBER = -1;
	public SpecialMonsterHouse SMH = null;

	protected final int SMH_NORMAL = 12;

	protected Base_Map(String field_name, int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/map/");
		sb.append(field_name);
		sb.append("/");
		FILE_NAME = sb.toString();
		RANDOM_LENGTH = length;
	}

	protected Base_Map(String field_name, String map_name) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/map/");
		sb.append(field_name);
		sb.append("/");
		sb.append(map_name);
		sb.append(".map");
		FILE_NAME = sb.toString();
		RANDOM_LENGTH = 0;
	}

	protected void add(Base_MapObject... os) {
		if (RANDOM_LENGTH > 0) {
			Show.showCriticalErrorMessageDialog("randomマップに固定オブジェクトを配置しようとしました");
			return;
		}
		for (Base_MapObject o : os) {
			list_object.add(o);
		}
	}

	public Base_Scene createOwnMenuScene(KeyHolder kH, Base_View cURRENT_VIEW) {
		return null;
	}

	public int defaultItemNumber() {
		return 0;
	}

	public Base_Map_Random getBaseMapRandom() {
		return this instanceof Base_Map_Random ? (Base_Map_Random) this : null;
	}

	public int getBelongingsMax() {
		return Belongings.MAX;
	}

	public BGM getBGM() {
		return BGM.to_kou_chiruno;
	}

	public String getClassName() {
		return getClass().getSimpleName();
	}

	public String getCSVName() {
		StringBuilder sb = new StringBuilder();
		sb.append("csv/");
		sb.append(getClassName());
		sb.append(".csv");
		return sb.toString();
	}

	/**
	 * 0:通常サイズ 1:ビッグサイズ -1:スモールサイズ
	 * 
	 * @return
	 */
	public int getEnemySize() {
		return 0;
	}

	public abstract Point getEntrancePoint();

	public String getFileName() {
		return FILE_NAME;
	}

	public int getHealRate() {
		if (isSupressHeal()) {
			return 60;
		} else {
			return Player.me.getMAX_HP();
		}
	}

	protected Image_Dungeon_Name getImageDungeonName() {
		return null;
	}

	public final ListIterator<Base_MapObject> getIterator_ObjectList() {
		return getObjectList().listIterator();
	}

	public String getMapName() {
		return getTitle();
	}

	public int getMapNo() {
		Image_Dungeon_Name idn = getImageDungeonName();
		if (idn == null) {
			return 0;
		} else {
			return idn.NO;
		}
	}

	public Image_MapTip getMapTip() {
		return Image_MapTip.地霊殿;
	}

	public int getMaxFloor() {
		return -99;
	}

	public int getMonsterHouse() {
		return 0;
	}

	public Base_Map getNextSpecialField() {
		HashMap<Integer, Base_Map> hash = getSpecialFloor();
		if (hash == null) {
			return null;
		} else {
			return hash.get(MapList.getFloor()
					- (MapList.isNextFloorUpOrDown() ? 0 : 1));
		}
	}

	protected AbstractList<Base_MapObject> getObjectList() {
		return list_object;
	}

	public Base_Map_Random getParentRandomMap() {
		return null;
	}

	public Base_Map_Random getRandomMap() {
		return (Base_Map_Random) ((this instanceof Base_Map_Random) ? this
				: getParentRandomMap());
	}

	public int getSaisenParcent() {
		return 0;
	}

	public int getShopParcent() {
		return 0;
	}

	public int getSMH_EnemyLV() {
		return new R().nextInt(3) + 1;
	}

	/**
	 * @return 存在するなら12%くらい
	 */
	public int getSMH_Percent() {
		return 0;
	}

	public HashMap<Integer, Base_Map> getSpecialFloor() {
		return null;
	}

	public String getTitle() {
		return getClassName();
	}

	public int getTrapDefaultValue() {
		return 0;
	}

	public boolean is2GEKI() {
		return this instanceof 二撃必殺;
	}

	public boolean isAllEnchantDungeon() {
		return 印邪.isEffectiveAllSimbols();
	}

	protected boolean isBGMDemandedToPlay() {
		return BGM.isBGMStatusStopped();
	}

	public boolean isDangerous() {
		return false;
	}

	public boolean isDiggable() {
		return isHaraheru();
	}

	public boolean isDungeon() {
		return false;
	}

	public boolean isEnchantedBGM_NoPlay() {
		return false;
	}

	public boolean isEnemyEnchantDungeon() {
		return this instanceof 持ち上げダンジョン;
	}

	public boolean isForcedToMakeEnemy() {
		return false;
	}

	public abstract boolean isHaraheru();
	public boolean isHaraheranai() { return false; }

	public boolean isInsane() {
		return false;
	}

	public boolean isItemDrop() {
		return true;
	}

	public abstract boolean isLightful();

	public boolean isLvUpedEnemy() {
		return false;
	}

	public boolean isMiniMapAvaible() {
		return true;
	}

	public SpecialMonsterHouse isMonsterHouse() {
		return null;
	}

	public final boolean isNextForbbitenSave() {
		int max = getMaxFloor();
		return max == -99 || MapList.getFloor() == max || isNextSpecial();
	}

	public boolean isNextSpecial() {
		return getNextSpecialField() != null;
	}

	public boolean isRandomField() {
		return RANDOM_LENGTH > 0;
	}

	public boolean isStarsNextFloor() {
		return true;
	}

	public boolean isSupressHeal() {
		return EnchantSpecial
				.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.頑)
				|| PresentField.get().is2GEKI();
	}

	public boolean isTouShiDungeon() {
		return false;
	}

	public boolean isTrapMasterDungeon() {
		return false;
	}

	public boolean isWarningEnemy() {
		return false;
	}

	public final void playBGM() {
		if (isBGMDemandedToPlay()) {
			BGM bgm = getBGM();
			if (bgm != null) {
				bgm.play();
			}
		}
	}

}

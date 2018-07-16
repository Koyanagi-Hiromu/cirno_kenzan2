package dangeon.model.map.field.special.map;

import java.awt.Point;
import java.util.AbstractList;
import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.DIRECTION;
import main.util.FrameShaker;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.FixedMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public abstract class BossMap extends FixedMap {
	public class BOSS {
		public final Base_Enemy creature;
		public final ArrayList<String[]> conv;
		public final BossMap map;

		public BOSS(Base_Enemy creature, ArrayList<String[]> conv, BossMap map) {
			this.creature = creature;
			this.conv = conv;
			this.map = map;
		}
	}

	protected Base_Enemy boss;

	private static final long serialVersionUID = 1L;
	public final Base_Map_Random BMR;

	public final Point entrance;

	public BossMap(Base_Map_Random bmr, int index, Point entrance) {
		super("battle" + index);
		BMR = bmr;
		this.entrance = entrance;
	}

	public void appearStair() {
		ItemFall.itemFall(getStair());
	}

	@Override
	public int defaultItemNumber() {
		return 3;
	}

	public void end() {
		message();
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set("どこかに階段が現れたようだ…");
				appearStair();
			}
		});

	}

	@Override
	public abstract BGM getBGM();

	protected abstract Base_Enemy getBoss();

	@Override
	public String getClassName() {
		return "";
	}

	public int getDefaultEnemyNumber() {
		return new R().nextInt(4) + 8;
	}

	@Override
	public Point getEntrancePoint() {
		return entrance;
	}

	@Override
	public String getFileName() {
		return super.getFileName();
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.danjon_no_sukima;
	}

	@Override
	public String getMapName() {
		return "決戦の場";
	}

	@Override
	public Image_MapTip getMapTip() {
		return BMR.getMapTip();
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		return super.getObjectList();
	}

	@Override
	public Base_Map_Random getParentRandomMap() {
		return BMR;
	}

	protected Base_Artifact getStair() {
		return (new 階段戻り(MassCreater.getStairsIP(),
				Player.me.flag_no_item ? "持ち込みなしで最深層まで辿り着き帰還した" : "最深層から帰還した",
				BMR));
	}

	@Override
	public int getTrapDefaultValue() {
		return 5;
	}

	public void initBossMap(MassCreater massCreater) {
		Player.me.direction = DIRECTION.UP;
		boss = getBoss().setFlagWarning();
		TurnSystemController.setBoss(new BOSS(boss, null, this));
		MapList.nextFloor();
		massCreater.initBossMap(getDefaultEnemyNumber(), BMR);
		SE.GOGOGO.play();
		FrameShaker.doneNormaly();
		Message.set("注意せよ！　この階層は", boss.getColoredName(), "によって守られている！");
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}

	@Override
	public boolean isDiggable() {
		return false;
	}

	@Override
	public final boolean isDungeon() {
		return true;
	}

	@Override
	public final boolean isHaraheru() {
		return true;
	}

	@Override
	public boolean isMiniMapAvaible() {
		return true;
	}

	protected abstract void message();
}

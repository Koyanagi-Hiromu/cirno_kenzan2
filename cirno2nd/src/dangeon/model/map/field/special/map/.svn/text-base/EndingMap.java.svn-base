package dangeon.model.map.field.special.map;

import java.awt.Point;
import java.util.AbstractList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.博麗中結界;
import dangeon.model.map.field.random.慧音の最終問題;
import dangeon.model.map.field.random.最強への道;
import dangeon.model.map.field.special.FixedMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.artifact.item.pot.分裂の瓶;
import dangeon.model.object.artifact.item.pot.印の瓶;
import dangeon.model.object.artifact.item.scrool.罠師の書;
import dangeon.model.object.creature.player.Player;

public class EndingMap extends FixedMap {
	private class BossStairs extends Stairs {
		private static final long serialVersionUID = 1L;

		public BossStairs(Point p) {
			super(p);
		}

		@Override
		public String[] getSlection() {
			return new String[] { "BOSS戦へ進む", "まだ" };
		}

		@Override
		public void move() {
			SE.SYSTEM_STAIR_STEP.play();
			BMR.goToBossMap();
		}

		@Override
		public boolean walkOnAction() {
			new ConvEvent("ただならぬ気配がする…") {
				@Override
				protected Book getContent1() {
					return new Book("BOSS戦へ進む") {

						@Override
						protected void work() {
							move();
						}
					};
				}

				@Override
				protected Book getContent2() {
					return new Book("まだ") {
						@Override
						protected void work() {
						}
					};
				}
			};
			return false;
		}

	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public final Base_Map_Random BMR;

	public EndingMap(Base_Map_Random bmr) {
		super("secret");
		BMR = bmr;
	}

	@Override
	public BGM getBGM() {
		BGM.waitUntilFadeOut();
		return null;
	}

	@Override
	public String getClassName() {
		return "";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(19, 14);
	}

	protected Point getExitPoint() {
		return new Point(19, 10);
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
		return "ダンジョンの最深層";
	}

	@Override
	public Image_MapTip getMapTip() {
		return BMR.getMapTip();
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		Player.me.direction = DIRECTION.UP;
		boolean flag = false;
		Point p = new Point(19, 12);
		Base_Artifact a = null;
		if (BMR instanceof 最強への道) {
			a = new 罠師の書(p);
		} else if (BMR instanceof 慧音の最終問題) {
			a = new 分裂の瓶(new Point(p.x - 1, p.y));
			MapList.addArtifact(a);
			a = new 分裂の瓶(new Point(p.x + 1, p.y));
		} else if (BMR instanceof 博麗中結界) {
			flag = Player.me.flag_no_item;
			a = new 印の瓶(p);
		} else {
			flag = Player.me.flag_no_item;
		}
		if (a != null) {
			MapList.addArtifact(a);
		}
		add(getStair(flag));
		return super.getObjectList();
	}

	@Override
	public Base_Map_Random getParentRandomMap() {
		return BMR;
	}

	protected Base_MapObject getStair(boolean flag_no_item) {
		if (BMR.isBossMap()) {
			return new BossStairs(getExitPoint());
		} else {
			return new 階段戻り(getExitPoint(),
					flag_no_item ? "持ち込みなしで最深層まで辿り着き帰還した" : "最深層から帰還した", BMR);
		}
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}
}

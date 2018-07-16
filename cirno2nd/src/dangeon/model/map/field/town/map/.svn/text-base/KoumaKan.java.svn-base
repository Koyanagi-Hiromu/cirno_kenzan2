package dangeon.model.map.field.town.map;

import java.awt.Color;
import java.awt.Point;
import java.util.AbstractList;

import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.town.Base_TownMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.HiddenDevice;
import dangeon.model.object.artifact.device.HiddenMovePoint;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.dungeonNpc.NPC運命のワルツ;
import dangeon.model.object.creature.npc.second.NPCフラン;
import dangeon.model.object.creature.npc.second.NPCレミリア;
import dangeon.model.object.creature.npc.second.NPC十六夜咲夜;
import dangeon.model.object.creature.npc.second.NPC小悪魔;
import dangeon.model.object.creature.player.Player;

public class KoumaKan extends Base_TownMap {
	public class Dark extends HiddenDevice {
		private static final long serialVersionUID = 1L;

		private int count = 0;

		public Dark(Point p) {
			super(p);
		}

		@Override
		public boolean walkOnAction() {
			Message.set("◆封印されている◆");
			if (StoryManager.運命のワルツEvent_1.hasFinished()) {
				if (count == 0) {
					Message.set("（切り裂いて・・・）");
				} else if (count == 1) {
					Message.set("（闇を・・・）");
				} else {
					Message.set("（”剣”で闇を・・・）");
					count = -1;
				}
				count++;
			}
			SE.SYSTEM_CURSE.play();
			Player.me.setMassPoint_WalkLike(new Point(28, 10), 2);
			CONDITION.conditionAllClear(Player.me);
			MassCreater.setSpot(true);
			return super.walkOnAction();
		}
	}

	private class Heikan extends HiddenDevice {
		private static final long serialVersionUID = 1L;

		public Heikan(Point p) {
			super(p);
		}

		@Override
		public boolean walkOnAction() {
			ConvEvent cne = new ConvEvent() {
				@Override
				public void workAfterPush() {
					Player.me.direction = DIRECTION.DOWN;
					Player.me.setMassPoint_WalkLike(new Point(17, 9), 1);
				}
			};
			new Conversation(Image_LargeCharacter.パチュリー, cne,
					Color.GRAY.toString(), "※しばらく図書館は休館します※");
			return super.walkOnAction();
		}
	}

	private static final long serialVersionUID = 1L;

	public KoumaKan() {
		super("koumakan");
	}

	@Override
	public BGM getBGM() {
		return BGM.remi;
	}

	@Override
	public String getClassName() {
		return "紅魔館";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(17, 15);
	}

	@Override
	public Image_MapTip getMapTip() {
		Player.me.direction = DIRECTION.UP;
		return Image_MapTip.紅魔館;
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		add(new HiddenMovePoint(17, 15, new FairyPlace(), new Point(17, 4),
				DIRECTION.DOWN));
		add(new NPCフラン(new Point(22, 9)));
		add(new NPCレミリア(new Point(23, 9)));
		add(new NPC十六夜咲夜(new Point(25, 10)));
		if (StoryManager.運命のワルツEvent_1.hasFinished()) {
			add(new NPC運命のワルツ(new Point(31, 7)));
		} else {
			add(new Dark(new Point(31, 9)));
			add(new Dark(new Point(31, 8)));
		}
		if (!StoryManager.運命のワルツok.hasFinished()) {
			add(new Dark(new Point(30, 10)));
			add(new Dark(new Point(31, 10)));
			add(new Dark(new Point(31, 7)));
		}
		add(new NPC小悪魔(new Point(18, 9)));
		if (StoryManager.七曜の魔導ok.hasFinished()) {
		} else {
			add(new Heikan(new Point(17, 8)));
			add(new Heikan(new Point(17, 7)));
			add(new Heikan(new Point(17, 6)));
		}
		add(new Abstract_NPC(CHARA_IMAGE.紅美鈴, "紅美鈴", 16, 13, false,
				"今日はなんの用ですか？@", "⑨冷やかしに来たよ").setDirection(DIRECTION.DOWN_RIGHT));
		return super.getObjectList();
	}

	@Override
	public boolean isLightful() {
		return false;
	}
}

package dangeon.model.map.field.town.map;

import java.awt.Point;
import java.util.AbstractList;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.StoryManager;
import dangeon.model.map.field.town.Base_TownMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.HiddenMovePoint;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.dungeonNpc.NPCミラクルクエスト;
import dangeon.model.object.creature.npc.second.NPCおりん;
import dangeon.model.object.creature.npc.second.NPCパチェ;
import dangeon.model.object.creature.player.Player;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;

public class UnderGroundHouse extends Base_TownMap {
	private static final long serialVersionUID = 1L;

	public UnderGroundHouse() {
		super("UGMap");
	}

	@Override
	public String getClassName() {
		return "地霊殿";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(4, 12);
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.地霊殿;
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		Player.me.direction = DIRECTION.RIGHT;
		// add(new Stairs(new Point(17, 22), 隕石異変.ME).setInVisible());
		// if (StoryManager.逆ヶ島clear.hasFinished()) {
		// boolean b = Config.isPassWardTrue("miracle");
		// if (b)
		add(new NPCミラクルクエスト(new Point(12, 13)));
		// }
		add(new Abstract_NPC(CHARA_IMAGE.パルスィ, "パルスィ", 14, 6, DIRECTION.LEFT,
				"勇儀が地上に行ってしまったわ……@"));
		add(new Abstract_NPC(CHARA_IMAGE.黒谷ヤマメ, "黒谷ヤマメ", 14, 7, DIRECTION.UP,
				"まぁまぁ…@"));
		add(new NPCパチェ(19, 6));
		if (StoryManager.おりん車.hasFinished()) {
			add(new Abstract_NPC(CHARA_IMAGE.古明地さとり, "古明地さとり", 18, 6, false,
					"……@", "なるほど、二撃必殺のコツを聞きたいのね@",
					"二撃必殺は他のダンジョンとは異なり３ターンでＨＰが１回復するわ@",
					"そしてＨＰが回復するタイミングは敵が攻撃した後よ@",
					"だから「椛」や「わかさぎ姫」と戦う時に特に有効な戦略があるわ@", "それは「３ターン目」に隣接しに行くことよ@",
					"先制攻撃を受けるけど次の瞬間ＨＰが１回復するわ@", "覚えておくことね@"));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.古明地さとり, "古明地さとり", 18, 6, false) {
				private static final long serialVersionUID = 1L;

				@Override
				public void message() {
					say("……");
					say("…私は心が読めます");
					say("なるほど、こいしが失礼したわね");
					say("お詫びに暫くおりんを使っていいわ");
					ConvEvent cne = new ConvEvent() {
						@Override
						public void workAfterPush() {
							new BlackOut(new Task() {
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									Player.flag_clear = true;
									SE.FANFARE2.play();
									setTalks(Image_LargeCharacter.ANY);
									talks("おりん車を使えるようになった！");
									talks(true, "話しかけるとマップを移動させてくれるぞ");
								}
							}, new Task() {
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									StoryManager.おりん車.saveThisFinished();
									TaskOnMapObject.reCreateNewMap();
								}
							});
						}
					};
					new Conversation(IMLC, cne, "いじめちゃだめよ？");
				}
			});
		}
		// add(new Abstract_NPC(CHARA_IMAGE.古明地こいし, "古明地こいし", 26, 12, false,
		// "私こいし！@", "ShiftキーやSpaceキーを押すと勝手に敵の方向に振り向くよ！@",
		// "透明の敵にも隣接していれば振り向けるんだ！@", "上を向いてから試しにキーを押してみて！@")
		// .setConditionList(CONDITION.透明, 0));
		add(new Abstract_NPC(CHARA_IMAGE.霊烏路空, "霊烏路空", 18, 12, false,
				"私聞いたことがあるわ@", "鈍足状態の敵はレベルが上っても鈍足状態のままだって@",
				"⑨それじゃあ「うにゅほ戦車」はレベルが上っても鈍足のままってことね？@", "⑨うん・・・@")
				.setDirection(DIRECTION.RIGHT));
		if (StoryManager.おりん車.hasFinished()) {
			add(new HiddenMovePoint(19, 12, new FairyPlace(), new Point(17, 4),
					DIRECTION.DOWN));
			add(new NPCおりん(19, 12).setDirection(DIRECTION.LEFT));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.火焔猫燐, "火焔猫燐", 19, 12, false,
					"さとり様に何かご用かい？").setDirection(DIRECTION.LEFT));
		}
		return super.getObjectList();
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}

	@Override
	public boolean isLightful() {
		return false;
	}
}

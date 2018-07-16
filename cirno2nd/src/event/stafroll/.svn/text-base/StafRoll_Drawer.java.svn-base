package event.stafroll;

import java.awt.Graphics2D;
import java.awt.Point;

import main.Scene;
import main.constant.FR;
import main.res.BGM;
import main.util.BlackOut;
import dangeon.controller.listener.menu.Menu_Result;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.artifact.item.spellcard.イナバのカード;
import dangeon.model.object.artifact.item.spellcard.キスメのカード;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.artifact.item.spellcard.ナズーリンのカード;
import dangeon.model.object.artifact.item.spellcard.パチュリーのカード;
import dangeon.model.object.artifact.item.spellcard.パルスィのカード;
import dangeon.model.object.artifact.item.spellcard.フランドールのカード;
import dangeon.model.object.artifact.item.spellcard.プリズムリバー三姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.ミスティアのカード;
import dangeon.model.object.artifact.item.spellcard.メディスンメランコリーのカード;
import dangeon.model.object.artifact.item.spellcard.リグルのカード;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.レティのカード;
import dangeon.model.object.artifact.item.spellcard.レミリアのカード;
import dangeon.model.object.artifact.item.spellcard.一輪雲山のカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.八坂神奈子のカード;
import dangeon.model.object.artifact.item.spellcard.八意永琳のカード;
import dangeon.model.object.artifact.item.spellcard.八雲紫のカード;
import dangeon.model.object.artifact.item.spellcard.八雲藍のカード;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.artifact.item.spellcard.博麗霊夢のカード;
import dangeon.model.object.artifact.item.spellcard.古明地こいしのカード;
import dangeon.model.object.artifact.item.spellcard.古明地さとりのカード;
import dangeon.model.object.artifact.item.spellcard.四季映姫・ヤマザナドゥのカード;
import dangeon.model.object.artifact.item.spellcard.因幡てゐのカード;
import dangeon.model.object.artifact.item.spellcard.多々良小傘のカード;
import dangeon.model.object.artifact.item.spellcard.寅丸星のカード;
import dangeon.model.object.artifact.item.spellcard.封獣ぬえのカード;
import dangeon.model.object.artifact.item.spellcard.射命丸文のカード;
import dangeon.model.object.artifact.item.spellcard.小野塚小町のカード;
import dangeon.model.object.artifact.item.spellcard.星熊勇儀のカード;
import dangeon.model.object.artifact.item.spellcard.村紗水蜜のカード;
import dangeon.model.object.artifact.item.spellcard.東風谷早苗のカード;
import dangeon.model.object.artifact.item.spellcard.橙のカード;
import dangeon.model.object.artifact.item.spellcard.河城にとりのカード;
import dangeon.model.object.artifact.item.spellcard.洩矢諏訪子のカード;
import dangeon.model.object.artifact.item.spellcard.火焔猫燐のカード;
import dangeon.model.object.artifact.item.spellcard.犬走椛のカード;
import dangeon.model.object.artifact.item.spellcard.秋姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.紅美鈴のカード;
import dangeon.model.object.artifact.item.spellcard.聖白蓮のカード;
import dangeon.model.object.artifact.item.spellcard.蓬莱山輝夜のカード;
import dangeon.model.object.artifact.item.spellcard.藤原妹紅のカード;
import dangeon.model.object.artifact.item.spellcard.西行寺幽々子のカード;
import dangeon.model.object.artifact.item.spellcard.鍵山雛のカード;
import dangeon.model.object.artifact.item.spellcard.霊烏路空のカード;
import dangeon.model.object.artifact.item.spellcard.霧雨魔理沙のカード;
import dangeon.model.object.artifact.item.spellcard.風見幽香のカード;
import dangeon.model.object.artifact.item.spellcard.魂魄妖夢のカード;
import dangeon.model.object.artifact.item.spellcard.黒谷ヤマメのカード;
import event.Base_Drawer;
import event.stafroll.model.Stafroll_Staffs;
import event.stafroll.model.Stafroll_Staffs_ProgramerETC;
import event.stafroll.model.Stafroll_images;

public class StafRoll_Drawer extends Base_Drawer {
	private Stafroll_images sr_im;
	private Stafroll_Staffs sr_st;
	private Stafroll_Staffs_ProgramerETC sr_st_etc;
	private int spell_card_index = 0, etc_index = 0, step_controller = -1;
	private final SpellCard SC[];
	private final String ETC[][];
	private final int PRE_THREAD_SPEED;

	public StafRoll_Drawer() {
		PRE_THREAD_SPEED = FR.THREAD_SLEEP;
		FR.changeSleep(FR.THREAD_SLEEP_FAST);
		Point p = new Point();
		SpellCard sc[] = { new 博麗霊夢のカード(p), new 霧雨魔理沙のカード(p), new ルーミアのカード(p),
				new チルノのカード(p), new 紅美鈴のカード(p), new パチュリーのカード(p),
				new 十六夜咲夜のカード(p), new レミリアのカード(p), new フランドールのカード(p),
				new レティのカード(p), new 橙のカード(p), new アリスのカード(p),
				new プリズムリバー三姉妹のカード(p), new 魂魄妖夢のカード(p), new 西行寺幽々子のカード(p),
				new 八雲藍のカード(p), new 八雲紫のカード(p), new リグルのカード(p),
				new ミスティアのカード(p), new 上白沢慧音のカード(p), new 因幡てゐのカード(p),
				new イナバのカード(p), new 八意永琳のカード(p), new 蓬莱山輝夜のカード(p),
				new 藤原妹紅のカード(p), new メディスンメランコリーのカード(p), new 風見幽香のカード(p),
				new 小野塚小町のカード(p), new 四季映姫・ヤマザナドゥのカード(p), new 秋姉妹のカード(p),
				new 鍵山雛のカード(p), new 河城にとりのカード(p), new 犬走椛のカード(p),
				new 射命丸文のカード(p), new 東風谷早苗のカード(p), new 八坂神奈子のカード(p),
				new 洩矢諏訪子のカード(p), new キスメのカード(p), new 黒谷ヤマメのカード(p),
				new パルスィのカード(p), new 星熊勇儀のカード(p), new 古明地さとりのカード(p),
				new 火焔猫燐のカード(p), new 霊烏路空のカード(p), new 古明地こいしのカード(p),
				new ナズーリンのカード(p), new 多々良小傘のカード(p), new 一輪雲山のカード(p),
				new 村紗水蜜のカード(p), new 寅丸星のカード(p), new 聖白蓮のカード(p),
				new 封獣ぬえのカード(p) };
		SC = sc;
		String[][] etc = {
				{ "マップチップ制作", "Dione", "まるせん" },
				{ "ジャケット制作", "たにたけし", "Azel" },
				{ "エフェクト制作", "かうざー", "シュウ", "ささ和え", "aozane", "めろん22" },
				{ "アイテム／トラップ制作", "鮫妻", "AJIA", "Dione" },
				{ "特殊エネミー制作", "かみねんど" },
				{ "アイコン制作", "かみねんど", "パピジ" },
				{ "ロゴ／サムネイル制作", "まるせん", "竜一" },
				{ "サークルカット制作", "鮫妻" },
				{ "タイトル／エンディング制作", "電気ウサギ" },
				{ "タイトルＢＧＭ", "フテペン", "", "境界の風景",
						"　-The boundary of the world-　" },
				{ "エンディングＢＧＭ", "干瓢", "", "Scent of plume" }, { "効果音制作", "徒桜" },
				{ "プログラム協力", "麩", "ずんだ", "ござる" }, { "動画編集", "Azel", "ひらく" },
				{ "webデザイン", "クロノトカゲ" }, { "動作テスト", "4d2" },
				{ "スペシャルサンクス", "Ａ", "ぬぬき", "TITANS", "SF研究会リリカ団", "長月 師走" },
				{ "プログラム／副代表", "ロッチチ" }, { "運営／プログラム／代表", "ウェレイ" },
				{ "Thank You For Playing", "プレイしてくれてありがとう！" },
				{ "", "～The End～" }, { "", "", "See You Next Dungeon...?" } };
		ETC = etc;
		sr_im = new Stafroll_images();
		sr_st = new Stafroll_Staffs(SC[0]);
		sr_st_etc = new Stafroll_Staffs_ProgramerETC(100, new String[] {
				"不思議の大冒険　チルノ見参！", "スタッフロール" });
	}

	@Override
	public void draw(Graphics2D g) {
		sr_im.draw(g);
		if (step_controller == -1) {
			if (sr_st_etc.draw(g)) {
				step_controller = 0;
			}
		} else if (step_controller == 0) {
			if (sr_st.draw(g)) {
				if (spell_card_index < SC.length - 1) {
					spell_card_index++;
					sr_st = new Stafroll_Staffs(SC[spell_card_index]);
				} else {
					step_controller = 1;
				}
			}
		} else if (step_controller == 1) {
			if (sr_st_etc.draw(g)) {
				if (etc_index < ETC.length - 1) {
					etc_index++;
					sr_st_etc = new Stafroll_Staffs_ProgramerETC(
							etc_index >= ETC.length - 3, ETC[etc_index]);
				} else {
					step_controller = 2;
				}
			}
		} else if (step_controller == 2) {
			step_controller = 3;
			BGM.waitUntilFadeOut_Thread();
			new BlackOut("", new Task() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					FR.changeSleep(PRE_THREAD_SPEED);
					Scene.DANGEON.setScene();
					Menu_Result.setScene();
				}
			});
		}

	}

	public void release() {
		sr_im = null;
	}

}

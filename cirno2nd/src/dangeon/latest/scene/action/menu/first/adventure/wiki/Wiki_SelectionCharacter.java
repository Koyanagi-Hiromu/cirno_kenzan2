package dangeon.latest.scene.action.menu.first.adventure.wiki;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.Exルーミア;
import dangeon.model.object.creature.enemy.きもけーね;
import dangeon.model.object.creature.enemy.にとり;
import dangeon.model.object.creature.enemy.ゆっくり霊夢;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.アリス;
import dangeon.model.object.creature.enemy.チルノ;
import dangeon.model.object.creature.enemy.ナズーリン;
import dangeon.model.object.creature.enemy.パチュリー;
import dangeon.model.object.creature.enemy.パルスィ;
import dangeon.model.object.creature.enemy.ヒソウテンソク;
import dangeon.model.object.creature.enemy.フランドール;
import dangeon.model.object.creature.enemy.ミスティア;
import dangeon.model.object.creature.enemy.メディスン;
import dangeon.model.object.creature.enemy.リグルナイトバグ;
import dangeon.model.object.creature.enemy.リリーホワイト;
import dangeon.model.object.creature.enemy.ルーミア;
import dangeon.model.object.creature.enemy.レティ;
import dangeon.model.object.creature.enemy.レミリア;
import dangeon.model.object.creature.enemy.上白沢慧音;
import dangeon.model.object.creature.enemy.二ッ岩マミゾウ;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.enemy.今泉影狼;
import dangeon.model.object.creature.enemy.伊吹萃香;
import dangeon.model.object.creature.enemy.先代巫女;
import dangeon.model.object.creature.enemy.八坂神奈子;
import dangeon.model.object.creature.enemy.八意永琳;
import dangeon.model.object.creature.enemy.八雲紫;
import dangeon.model.object.creature.enemy.八雲藍;
import dangeon.model.object.creature.enemy.十六夜咲夜;
import dangeon.model.object.creature.enemy.博麗霊夢;
import dangeon.model.object.creature.enemy.古明地こいし;
import dangeon.model.object.creature.enemy.古明地さとり;
import dangeon.model.object.creature.enemy.四季映姫ヤマザナドゥ;
import dangeon.model.object.creature.enemy.因幡てゐ;
import dangeon.model.object.creature.enemy.堀川雷鼓;
import dangeon.model.object.creature.enemy.多々良小傘;
import dangeon.model.object.creature.enemy.大妖精;
import dangeon.model.object.creature.enemy.姫海棠はたて;
import dangeon.model.object.creature.enemy.宮古芳香;
import dangeon.model.object.creature.enemy.寅丸星;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.enemy.射命丸文;
import dangeon.model.object.creature.enemy.小悪魔;
import dangeon.model.object.creature.enemy.小野塚小町;
import dangeon.model.object.creature.enemy.少名針妙丸;
import dangeon.model.object.creature.enemy.幽谷響子;
import dangeon.model.object.creature.enemy.星熊勇儀;
import dangeon.model.object.creature.enemy.朱鷺子;
import dangeon.model.object.creature.enemy.村紗水蜜;
import dangeon.model.object.creature.enemy.東風谷早苗;
import dangeon.model.object.creature.enemy.橙;
import dangeon.model.object.creature.enemy.比那名居天子;
import dangeon.model.object.creature.enemy.永江衣玖;
import dangeon.model.object.creature.enemy.洩矢諏訪子;
import dangeon.model.object.creature.enemy.火焔猫燐;
import dangeon.model.object.creature.enemy.物部布都;
import dangeon.model.object.creature.enemy.犬走椛;
import dangeon.model.object.creature.enemy.着物勇儀;
import dangeon.model.object.creature.enemy.秦こころ;
import dangeon.model.object.creature.enemy.紅美鈴;
import dangeon.model.object.creature.enemy.聖白蓮;
import dangeon.model.object.creature.enemy.蓬莱山輝夜;
import dangeon.model.object.creature.enemy.藤原妹紅;
import dangeon.model.object.creature.enemy.蘇我屠自古;
import dangeon.model.object.creature.enemy.西行寺幽々子;
import dangeon.model.object.creature.enemy.豊聡耳神子;
import dangeon.model.object.creature.enemy.赤蛮奇_体;
import dangeon.model.object.creature.enemy.鈴仙・優曇華院・イナバ;
import dangeon.model.object.creature.enemy.鍵山雛;
import dangeon.model.object.creature.enemy.霊烏路空;
import dangeon.model.object.creature.enemy.霍青娥;
import dangeon.model.object.creature.enemy.霧雨魔理沙;
import dangeon.model.object.creature.enemy.風見幽香;
import dangeon.model.object.creature.enemy.鬼人正邪;
import dangeon.model.object.creature.enemy.魂魄妖夢;
import dangeon.model.object.creature.enemy.魅魔;
import dangeon.model.object.creature.enemy.黄金ゆっくり;
import dangeon.model.object.creature.enemy.黒谷ヤマメ;
import dangeon.util.STAGE;
import main.res.SE;

public class Wiki_SelectionCharacter extends Base_Scene_Menu {
	public final int INDEX;
	public final Base_View BACK;

	public boolean flag_selected;

	public final ArrayList<Base_Enemy> LIST;

	public Wiki_SelectionCharacter(KeyHolder kh, Base_View bv) {
		super(14, 1, kh, new Wiki_SelectionCharacter_View());
		BACK = bv;
		INDEX = 0;
		LIST = new ArrayList<Base_Enemy>();
		initLIST();
	}

	public Wiki_SelectionCharacter(KeyHolder kh, Base_View bv, int i,
			ArrayList<Base_Enemy> list) {
		super(14, 1, kh, new Wiki_SelectionCharacter_View());
		BACK = bv;
		INDEX = i;
		LIST = list;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CANCEL.play();
		flag_selected = false;
		setNextScene(new Wiki_First_Commnad(this));
	}

	@Override
	protected void action_else() {
	}

	private void add(Base_Enemy e) {
		LIST.add(e);
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		STAGE stage = STAGE.values()[INDEX];
		boolean flag_else = stage.ONE_NAME == null;
		ArrayList<Base_Enemy> categorized = new ArrayList<Base_Enemy>();
		for (Base_Enemy e : LIST) {
			if (flag_else) {
				if (e.getCategory().isEmpty())
					categorized.add(e);
			} else if (e.getCategory().contains(stage)) {
				categorized.add(e);
			}
		}
		sort(categorized, stage);
		int i = 0;
		int max = categorized.size();
		for (Base_Enemy e : categorized) {
			i++;
			StringBuilder sb = new StringBuilder();
			if (i < 10)
				sb.append("0");
			sb.append(i);
			sb.append(":");
			setContents(sb.toString(), i, max, e,
					flag_else ? "その他" : stage.name());
		}
	}

	public void initLIST() {
		Point p = new Point();
		add(new ルーミア(p, 1));
		add(new Exルーミア(p, 1));
		add(new 大妖精(p, 1));
		add(new チルノ(p, 1));
		add(new 紅美鈴(p, 1));
		add(new 小悪魔(p, 1));
		add(new パチュリー(p, 1));
		add(new 十六夜咲夜(p, 1));
		add(new レミリア(p, 1));
		add(new フランドール(p, 1));

		add(new レティ(p, 1));
		add(new 橙(p, 1));
		add(new アリス(p, 1));
		add(new リリーホワイト(p, 1));
		add(new 魂魄妖夢(p, 1));
		add(new 西行寺幽々子(p, 1));
		add(new 八雲藍(p, 1));
		add(new 八雲紫(p, 1));

		add(new リグルナイトバグ(p, 1));
		add(new ミスティア(p, 1));
		add(new 上白沢慧音(p, 1));
		add(new きもけーね(p, 1));
		add(new 霧雨魔理沙(p, 1));
		add(new 博麗霊夢(p, 1));
		add(new 因幡てゐ(p, 1));
		add(new 鈴仙・優曇華院・イナバ(p, 1));
		add(new 八意永琳(p, 1));
		add(new 蓬莱山輝夜(p, 1));
		add(new 藤原妹紅(p, 1));

		add(new メディスン(p, 1));
		add(new 射命丸文(p, 1));
		add(new 小野塚小町(p, 1));
		add(new 四季映姫ヤマザナドゥ(p, 1));
		add(new 風見幽香(p, 1));

		add(new 鍵山雛(p, 1));
		add(new にとり(p, 1));
		add(new 犬走椛(p, 1));
		// add(new 射命丸文(p, 1));
		add(new 東風谷早苗(p, 1));
		add(new 八坂神奈子(p, 1));
		add(new 洩矢諏訪子(p, 1));

		add(new 黒谷ヤマメ(p, 1));
		add(new パルスィ(p, 1));
		add(new 星熊勇儀(p, 1));
		add(new 着物勇儀(p, 1));
		add(new 古明地さとり(p, 1));
		add(new 火焔猫燐(p, 1));
		add(new 霊烏路空(p, 1));
		// add(new 東風谷早苗(p, 1));
		add(new 古明地こいし(p, 1));

		add(new ナズーリン(p, 1));
		add(new 多々良小傘(p, 1));
		add(new 村紗水蜜(p, 1));
		add(new 寅丸星(p, 1));
		add(new 聖白蓮(p, 1));
		add(new 封獣ぬえ(p, 1));

		add(new 姫海棠はたて(p, 1));
		add(new 伊吹萃香(p, 1));
		add(new 永江衣玖(p, 1));
		add(new 比那名居天子(p, 1));
		add(new 秦こころ(p, 1));

		// add(new 西行寺幽々子(p, 1));
		add(new 幽谷響子(p, 1));
		// add(new 多々良小傘(p, 1));
		add(new 宮古芳香(p, 1));
		add(new 霍青娥(p, 1));
		add(new 蘇我屠自古(p, 1));
		add(new 物部布都(p, 1));
		add(new 豊聡耳神子(p, 1));
		// add(new 封獣ぬえ(p, 1));
		add(new 二ッ岩マミゾウ(p, 1));

		add(new わかさぎ姫(p, 1));
		add(new 赤蛮奇_体(p, 1));
		// add(new 赤蛮奇_頭(p, 1));
		add(new 今泉影狼(p, 1));
		add(new 鬼人正邪(p, 1));
		add(new 少名針妙丸(p, 1));
		add(new 堀川雷鼓(p, 1));

		add(new 朱鷺子(p, 1));

		add(new 魅魔(p, 1));
		add(new 先代巫女(p, 1));

		add(new ゆっくり霊夢(p, 1));
		add(new 黄金ゆっくり(p, 1));
		add(new 人形(p, 1));
		add(new ヒソウテンソク(p, 1));
	}

	private void setContents(String header, final int i, final int max,
			final Base_Enemy e, final String title) {
		boolean flag_open = false;
		for (int lv = 1; lv <= 4; lv++) {
			if (Config.isExist(e, lv)) {
				flag_open = true;
				break;
			}
		}
		if (flag_open) {
			final Wiki_SelectionCharacter ME = this;
			setContents(header.concat(e.getOriginalName()), new Book() {

				@Override
				protected void work() {
					setNextScene(new Wiki_Second_Commnad(ME, i, max, e, title));
					flag_selected = true;
				}
			});
		} else {
			setDeprecatedContents(header);
		}
	}

	private void setDeprecatedContents(String header) {
		setDeprecatedContents(header.concat("？？？？"), new Book() {

			@Override
			protected void work() {
			}
		});
	}

	private void sort(ArrayList<Base_Enemy> categorized, STAGE stage) {
		if (stage == STAGE.風神録) {
			categorized.add(3, categorized.remove(0));
		} else if (stage == STAGE.地霊殿) {
			categorized.add(6, categorized.remove(0));
		} else if (stage == STAGE.神霊廟) {
			categorized.add(3, categorized.remove(1));
			categorized.add(8, categorized.remove(1));
		} else if (stage == STAGE.輝針城) {
			for (Base_Enemy e : categorized) {
				if (e instanceof わかさぎ姫) {
					((わかさぎ姫) e).resetFlagInWater(false);
				}
			}
		}

	}
}
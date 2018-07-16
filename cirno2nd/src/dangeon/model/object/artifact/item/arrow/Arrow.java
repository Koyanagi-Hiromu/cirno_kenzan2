package dangeon.model.object.artifact.item.arrow;

import java.awt.Point;
import java.util.Random;

import main.res.SE;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;

public abstract class Arrow extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int arrow_rest;

	protected Arrow(Point p, String item_name, boolean natural) {
		super(p, item_name, 0, ITEM_CASE.ARROW);
		if (natural) {
			Random ran = new R();
			if (this instanceof 大砲の弾) {
				setArrowRest(((ran.nextInt(3) + 3)));
			} else if (this instanceof ミニ八卦炉) {
				setArrowRest(((ran.nextInt(3) + 3)));
			} else if (this instanceof 毒ナイフ) {
				setArrowRest(((ran.nextInt(6) + 3)));
			} else if (this instanceof 鉄の矢) {
				setArrowRest(((ran.nextInt(6) + 10)));
			}
		} else
			setArrowRest(1);
	}

	public Arrow(Point p, String item_name, int num) {
		super(p, item_name, 0, ITEM_CASE.ARROW);
		setArrowRest(num);
	}

	@Override
	protected void action() {

	}

	public void addArrowRest(int delt) {
		arrow_rest += delt;
	}

	protected int arrowStr() {
		return 0;
	}

	public Base_Artifact createArrowRest(int delt) {
		arrow_rest += delt;
		return this;
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		// TODO 自動生成されたメソッド・スタブ
		return damage;
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		// TODO 自動生成されたメソッド・スタブ
		return damage;
	}

	@Override
	public Boolean exchange() {
		Arrow arr = Belongings.getArrow(getClass());
		if (arr == null) {
			return false;
		} else {
			if (arr.getArrowRest() == 99) {
				Message.set(getColoredName().concat("はうまくまとめられなかった"));
				return true;
			} else {
				matomeru(arr, getColoredName().concat("を拾ってまとめた"));
				return null;
			}
		}
	}

	@Override
	public void firstMsgAtUsingThis() {
	}

	public int getArrowRest() {
		return arrow_rest;
	}

	public String getExplainationInShortCutSelecting() {
		return "";
	}

	@Override
	public String getLastPackage() {
		return "arrow";
	}

	@Override
	public int getMerchantSoldValue() {
		int i = arrow_rest * base_merchant_value / getMin();
		if (i < 1)
			i = 0;
		return i;
	}

	private int getMin() {
		if (this instanceof 鉄の矢) {
			return 10;
		}
		return 3;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		if (scale() != null && getArrowRest() > 0) {
			sb.append(半角全角コンバーター.半角To全角数字(getArrowRest()));
			sb.append(scale());
			sb.append("の");
		}
		sb.append(getName());
		return sb;
	}

	public abstract Arrow getOne();

	@Override
	protected String getSecondExplain_ByCategory() {
		return "「射撃」でセットして「Ｆ」キーで投げよう。アイテムコマンドから「投げ」を選択すると束ねたものを全て投げる。射撃物は呪われても平気。";
	}

	@Override
	public int getShadow() {
		return 9;
	}

	@Override
	public boolean isAbleToCurse() {
		return false;
	}

	@Override
	public boolean isParmitThisItemFreeze() {
		return false;
	}

	@Override
	public void itemHit(Base_Creature deffece, Base_Creature attack) {
		if (attack instanceof Base_Enemy) {
			Damage.EtoPandE_ArrowAttack(this.getName().concat("を受けて倒れた"),
					this.getName(), attack, deffece);
		} else {
			int str = arrowStr();
			if (getArrowRest() != 1) {
				str = (int) (str * 0.8 * getArrowRest());
			}
			Damage.PtoE_ArrowAttack(this, attack, deffece, str);
		}
	}

	@Override
	public boolean itemPickUp() {
		Arrow arr = Belongings.getArrow(getClass());
		if (arr == null) {
			super.itemPickUp();
		} else {
			if (arr.getArrowRest() == 99)
				Message.set(getColoredName().concat("はうまくまとめられなかった"));
			else
				matomeru(arr, getColoredName().concat("を拾ってまとめた"));
		}
		return false;
	}

	@Override
	public boolean itemUse() {
		DIRECTION d = Player.me.getDirection();
		Base_Artifact a = arrow_rest > 1 ? getOne() : this;
		Player.me.itemThrow(a, d);
		Message.set("チルノは", a.getColoredName(), "を投げた");
		return true;
	}

	private void matomeru(Arrow arr, String str) {
		arr.setArrowRest(arr.getArrowRest() + this.getArrowRest());
		if (arr.getArrowRest() > 99) {
			arr.setArrowRest(99);
		}
		TaskOnMapObject.addTrapRemoveTask(this);
		Message.set(str);
		SE.SYSTEM_PICKUP.play();
	}

	protected abstract String scale();

	public Arrow setArrowRest(int value) {
		arrow_rest = value;
		return this;
	}

	public int takeIt() {
		Arrow arr = Belongings.getArrow(getClass());
		if (arr != null) {
			if (arr.getArrowRest() == 99) {
				Message.set(getColoredName().concat("は既にいっぱいで取り出せない"));
				return -1;
			} else {
				matomeru(arr, getColoredName().concat("を取り出してまとめた"));
				return 1;
			}
		}
		return 0;
	}

}

package dangeon.model.object.artifact.item.arrow;

import java.awt.Image;
import java.awt.Point;

import main.res.BulletImage;
import main.res.CHARA_IMAGE;
import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印時;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.霊烏路空;
import dangeon.util.MapInSelect;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.detail.MainMap;

/**
 * 
 * 
 * @author Administrator
 * 
 */
public class 大砲の弾 extends Arrow {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.ARROW;
	public static final String item_name = "核弾";
	public static final int composition = 0;
	public static final int item_use_str = 40;

	/**
	 * 自然発生ならばtrue 矢を打つなどしてMapに置かれたものはfalse
	 * 
	 * @param p
	 */
	public 大砲の弾(Point p, boolean natural) {
		super(p, item_name, natural);
		IM = Image_Artifact.BOMB;
	}

	public void explode(Point p, final Base_Creature source) {
		final Point mass_point = p.getLocation();
		MainMap.addEffect(new ExplosionEffect(false, mass_point, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				MassCreater.setExplotionMass(mass_point);
				MapInSelect.explotionDamage(mass_point, source,
						"核弾の爆発に巻き込まれて倒れた", itemDamage(source));
			}
		}), true);
	}

	@Override
	public String getExplainationInShortCutSelecting() {
		return "固定４０ダメージの爆発を必ず起こします";
	}

	@Override
	public Image getImage(DIRECTION direction) {
		return BulletImage.fireball.getImage(CHARA_IMAGE
				.getKoma_StaticMethod(3));
	}

	@Override
	public Arrow getOne() {
		return new 大砲の弾(mass_point, false);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "当たった所で爆発し壁も壊してしまう。利用手段は多いが使いこなすには知識と経験を要する。能力を使われる前に爆発するのでにとりや霊夢にも有効である。";
	}

	@Override
	public int getShadow() {
		return 4;
	}

	protected int itemDamage(Base_Creature source) {
		if (source instanceof 霊烏路空)
			switch (source.getLV()) {
			case 1:
				return 20;
			case 2:
				return 30;
			case 3:
				return 40;
			case 4:
				return 50;
			}
		else {
			return 40 * this.getArrowRest();
		}
		return 40;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature target, Base_Creature source) {
	}

	@Override
	public boolean itemUse() {
		印時.throwKnife(this);
		return true;
	}

	@Override
	protected String scale() {
		return "コ";
	}

}
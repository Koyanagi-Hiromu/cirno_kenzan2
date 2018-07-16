package dangeon.model.object.artifact.item.enchantSpecial;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.Exルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.きもけーねのカード;
import dangeon.model.object.artifact.item.spellcard.みすちーのカード;
import dangeon.model.object.artifact.item.spellcard.ミスティアのカード;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ArrayIterator;

/**
 * 特殊合成のチェックおよび、カードのオブジェクトを生成する。
 * 
 * @author rottiti
 * 
 */
public enum SpecialCard {
	// @formatter:off
	Exルーミア(Exルーミアのカード.class, ルーミアのカード.class, ENCHANT_SIMBOL.封印,
			ENCHANT_SIMBOL.祓),
			きもけーね(きもけーねのカード.class,上白沢慧音のカード.class,ENCHANT_SIMBOL.識別,ENCHANT_SIMBOL.識),
			みすちー(みすちーのカード.class,ミスティアのカード.class,ENCHANT_SIMBOL.狸,ENCHANT_SIMBOL.謎),
			;
	public static SpecialCard check(SpellCard sc) {
		for (SpecialCard SC : values()) {
			System.out.println(SC);
			if (!SC.BASE_CARD.equals(sc.getClass())) {
				continue;
			}
			ArrayIterator ai = new ArrayIterator(SC.simboler.length);
			for (ENCHANT_SIMBOL ES : SC.simboler) {
				System.out.println(ES + "と" + SC + SC.simboler.length);
				if (!sc.getListComposition().contains(ES)) {
					System.out.println("ブレイク");
					break;
				}
				if (!ai.isNext()) {
					System.out.println("OK");
					Medal.特殊合成のカードを作った.addCount();
					return SC;
				}
			}
		}
		return null;
	}

	private Class<? extends SpellCard> THIS_CARD;
	private Class<? extends SpellCard> BASE_CARD;
	private ENCHANT_SIMBOL[] simboler;

	// @formatter:on
	private SpecialCard(Class<? extends SpellCard> this_card,
			Class<? extends SpellCard> base_card, ENCHANT_SIMBOL... simbol) {
		THIS_CARD = this_card;
		BASE_CARD = base_card;
		simboler = simbol;
	}

	public Base_Artifact getThisCard() {
		Constructor<?> con;
		try {
			con = THIS_CARD.getConstructor(Point.class);
			Object obj;
			obj = con.newInstance(Player.me.getMassPoint());
			SpellCard sc = (SpellCard) obj;
			sc.addBomb(4);
			return sc.createSpellCard(false, 0);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}

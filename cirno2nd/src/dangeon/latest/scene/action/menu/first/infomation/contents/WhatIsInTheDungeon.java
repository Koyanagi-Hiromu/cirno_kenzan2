package dangeon.latest.scene.action.menu.first.infomation.contents;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;
import dangeon.model.map.field.random.Base_Map_Random;

public class WhatIsInTheDungeon extends Scrool {
	public WhatIsInTheDungeon(KeyHolder kh, Base_View bv, Base_Map_Random map) {
		super(kh, bv, map.getExn());
	}

	@Override
	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, contents);
	}

	@Override
	protected int getFirstIndex() {
		return super.getFirstIndex();
	}

}
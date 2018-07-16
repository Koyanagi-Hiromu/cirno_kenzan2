package dungeon_creater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lister implements ActionListener {
	public enum ACTION {
		EXPORT, CLEAR, IMPORTER, p1, m1, DUNGEON, COPY, PAEST
	}

	static Lister ME = new Lister();

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (whichAction(e.getActionCommand())) {
		case CLEAR:
			Remove.textRemove(e);
			break;
		case EXPORT:
			TableWriter.writer();
			break;
		case IMPORTER:
			Importer.actionImporter();
			break;
		case p1:
			Number.up(1);
			break;
		case m1:
			Number.up(-1);
			break;
		case DUNGEON:
			TableFileReader.tableRead();
			topStatus.ME.reRoad();
			break;
		case COPY:
			Copy.getCopy();
			break;
		case PAEST:
			Copy.addCopy();
			break;
		}
	}

	private ACTION whichAction(String str) {
		for (ACTION a : ACTION.values()) {
			if (a.toString().equals(str)) {
				return a;
			}
		}
		return null;
	}
}

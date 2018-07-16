package debugMode.ListenerDebug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dangeon.model.object.artifact.ArtifactData;
import dangeon.model.object.creature.player.Belongings;

public class ListenerPopupMenuItem implements ActionListener {
	public static ListenerPopupMenuItem ME = new ListenerPopupMenuItem();

	@Override
	public void actionPerformed(ActionEvent e) {
		Belongings.setItems(ArtifactData.getObject(e.getActionCommand()));
	}

}

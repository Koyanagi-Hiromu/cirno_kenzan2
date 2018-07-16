package connection.sv_cl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Second_Firster;
import dangeon.model.config.Config;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.creature.player.Belongings;

public class ConnectionSubFrame extends JFrame {
	private class ComponentHolder {
		private JButton submit_button;
		private JComboBox name_field;
		private JComboBox port_field;
		private JComboBox host_field;
		private JComboBox dungeon_field;

		public void addDungeonField(JComboBox c) {
			this.dungeon_field = c;
		}

		public void addHostField(JComboBox c) {
			this.host_field = c;
		}

		public void addNameField(JComboBox c) {
			this.name_field = c;
		}

		public void addPortField(JComboBox c) {
			this.port_field = c;
		}

		public void addSubmitButton(JButton b1) {
			this.submit_button = b1;
		}

	}

	private static final long serialVersionUID = 1L;

	private static ConnectionSubFrame me;

	public static void createInstance() {
		if (me != null)
			me.dispose();
		me = new ConnectionSubFrame();
	}

	public static void main(String[] args) {
		new ConnectionSubFrame();
	}

	private final ComponentHolder component_holder;

	private String keys[] = { "share_name", "share_host", "share_port" };

	private boolean flag_server_slected = true;

	private ConnectionSubFrame() {
		setResizable(false);
		component_holder = new ComponentHolder();
		setLayout(new FlowLayout());
		setButtons();
		setTitle("シェアウィンドウ");
		int w = 250, h = 300;
		if (Second_Firster.ME != null) {
			setBounds(Second_Firster.ME.getX() - w / 2,
					Second_Firster.ME.getY(), w, h);
		} else {
			setBounds(0, 0, w, h);
		}
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JComboBox addButtonWithLabel(JPanel p, String string,
			String... default_items) {
		JPanel sub = new JPanel();
		int hgap = 10;
		sub.setLayout(new BorderLayout(hgap, 0));
		JLabel l = new JLabel(string);
		JComboBox box = new JComboBox();
		box.setEditable(true);
		for (String s : default_items) {
			box.addItem(s);
		}
		sub.add(l, BorderLayout.WEST);
		sub.add(box, BorderLayout.CENTER);
		p.add(sub);
		return box;
	}

	private Component createRadioButtonGroup() {
		JPanel sub = new JPanel();
		setBorder(sub, "選択");
		JRadioButton radio1 = new JRadioButton("Server", true);
		JRadioButton radio2 = new JRadioButton("Client", false);
		ChangeListener server_radio_button_listener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JRadioButton radio1 = (JRadioButton) e.getSource();

				swichRadioButton(radio1.isSelected());
			}
		};
		radio1.addChangeListener(server_radio_button_listener);
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		sub.add(radio1);
		sub.add(radio2);
		return sub;
	}

	private Component createSubmitButton() {
		JButton b1 = new JButton("接続");
		component_holder.addSubmitButton(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		return b1;
	}

	private Component createTextFields() {
		JPanel p = new JPanel();
		component_holder.addNameField(addButtonWithLabel(p, "NAME",
				getDefault(keys[0])));
		component_holder.addHostField(addButtonWithLabel(p, "HOST",
				getDefault(keys[1])));
		component_holder.host_field.setEnabled(false);
		component_holder.addPortField(addButtonWithLabel(p, "PORT",
				getDefault(keys[2])));
		component_holder.addDungeonField(addButtonWithLabel(p, "TO... ",
				getDefaultDungeon()));
		component_holder.dungeon_field.setEditable(false);
		return p;
	}

	private String[] getDefault(String key) {
		Object val = Config.getValue(key);
		if (val == null)
			return new String[] { "" };
		else {
			return val.toString().split(",");
		}
	}

	private String[] getDefaultDungeon() {
		return new DungeonConverter().getDefaultDungeonName();
	}

	public String getDungeon() {
		return component_holder.dungeon_field.getSelectedItem().toString();
	}

	@Override
	public String getName() {
		return component_holder.name_field.getSelectedItem().toString();
	}

	private void saveItems() {
		JComboBox[] boxes = { component_holder.name_field,
				component_holder.host_field, component_holder.port_field };
		for (int i = 0; i < boxes.length; i++) {
			JComboBox box = boxes[i];
			StringBuilder sb = new StringBuilder();
			String selected = box.getSelectedItem().toString();
			sb.append(selected);
			ComboBoxModel model = box.getModel();
			int size = model.getSize();
			for (int j = 0; j < size; j++) {
				Object element = model.getElementAt(j);
				if (!selected.equals(element.toString())) {
					sb.append(",");
					sb.append(element.toString());
				}
			}
			Config.saveValue(keys[i], sb.toString());
		}
	}

	private void setBorder(JPanel sub, String title) {
		TitledBorder border = new TitledBorder(title);
		sub.setBorder(border);
	}

	private void setButtons() {
		setLayout(new BorderLayout());
		add(createRadioButtonGroup(), BorderLayout.NORTH);
		add(createTextFields(), BorderLayout.CENTER);
		add(createSubmitButton(), BorderLayout.SOUTH);
	}

	private void submit() {
		if (!PresentField.get().getClass().equals(FairyPlace.class)) {
			JOptionPane.showMessageDialog(this, "妖精の踊り場から接続して下さい");
			return;
		}
		if (!flag_server_slected) {
			if (!Belongings.isEmpty()) {
				JOptionPane.showMessageDialog(this, "アイテムは持ち込めません");
				return;
			}
		} else {
			Base_Map_Random map = new DungeonConverter().inflate(getDungeon());
			if (map.ITEM_MAX != null && Belongings.getSize() > map.ITEM_MAX) {
				JOptionPane.showMessageDialog(this, "アイテム持ち込み上限数を越しています");
				return;
			}
		}
		String name = component_holder.name_field.getSelectedItem().toString();
		String host = flag_server_slected ? "0" : component_holder.host_field
				.getSelectedItem().toString();
		String port = component_holder.port_field.getSelectedItem().toString();
		String[] arr = { name, host, port };
		for (String string : arr) {
			if (string == null || string.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "入力項目が足りません");
				return;
			}
		}
		int port_int = 0;
		try {
			port_int = Integer.parseInt(port);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "PORTには数値を入力して下さい");
			return;
		}
		if (port_int < 1024 || port_int > 65535) {
			JOptionPane.showMessageDialog(this, "1024 ～ 65535 の整数値を指定してください.");
			return;
		}
		try {
			if (flag_server_slected) {
				new Server(this, port_int);
			} else {
				new Server(this, host, port_int);
			}
			saveItems();
			dispose();
		} catch (SocketTimeoutException e) {
			JOptionPane.showMessageDialog(this, "接続がタイムアウトしました.");
			e.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "ポートの確保に失敗しました.別の値をお試し下さい");
			e1.printStackTrace();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "予期しないエラーが発生しました");
			e2.printStackTrace();
		}

	}

	private void swichRadioButton(boolean server_selected) {
		flag_server_slected = server_selected;
		component_holder.host_field.setEnabled(!server_selected);
		component_holder.dungeon_field.setEnabled(server_selected);
	}

}

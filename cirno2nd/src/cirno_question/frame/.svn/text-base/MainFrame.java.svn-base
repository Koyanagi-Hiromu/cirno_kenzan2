package cirno_question.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import cirno_question.CheckListener;
import cirno_question.MainListener;
import cirno_question.ObjectExplain;
import cirno_question.QS;
import cirno_question.ObjectNames.Category;
import cirno_question.ObjectNames.Enemy;
import cirno_question.ObjectNames.Item;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.StringIsChangedToObject;

public class MainFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 2922701383104817983L;

	public static MainFrame ME = new MainFrame();

	public static int frame_x = 800;
	public static int frame_y = 750;
	/**
	 * オブジェクト選択領域
	 */
	public JPanel p = new JPanel();
	public JPanel object_p = new JPanel();
	public JPanel category_p = new JPanel();
	public JPanel ob_detail_p = new JPanel();
	public JPanel check_p = new JPanel();
	public JPanel detail_p = new JPanel();
	public JPanel explain_p = new JPanel();
	private JLabel ob3 = new JLabel("");

	public JComboBox lv = new JComboBox(Enemy.level);

	public JComboBox con = new JComboBox(Enemy.condition);

	public JTextField forge = new JTextField(4);

	public JCheckBox jc = new JCheckBox("呪いの有無");

	public JLabel jl = new JLabel("修正値および容量：");

	public JComboBox disc_one = new JComboBox(Item.disc);

	public JComboBox disc_two = new JComboBox(Item.disc);
	public JCheckBox eien = new JCheckBox();
	public JCheckBox kouma = new JCheckBox();
	public JCheckBox kaei = new JCheckBox();
	public JCheckBox huuzin = new JCheckBox();
	public JCheckBox tirei = new JCheckBox();
	public JCheckBox youyou = new JCheckBox();
	public JCheckBox seiren = new JCheckBox();
	JLabel ei_l = new JLabel("永");
	JLabel kou_l = new JLabel("紅");
	JLabel you_l = new JLabel("妖");
	JLabel huu_l = new JLabel("風");
	JLabel ti_l = new JLabel("地");
	JLabel sei_l = new JLabel("星");

	JLabel hana_l = new JLabel("花");

	public void frameCreate() {
		setTitle("オブジェクト選択ツール");
		setSize(MainFrame.frame_x, MainFrame.frame_y);
		setResizable(false);
		// setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void MainFrameStarter() {
		setCompornent();
		frameCreate();
	}

	public void setCategoryPanel() {
		FlowLayout layout = new FlowLayout(10);
		layout.setAlignment(FlowLayout.CENTER);
		layout.setHgap(0);
		layout.setVgap(0);
		category_p.setLayout(layout);
		int size_x = CompornentSize.CATEGORY_P_X / Category.category.length;
		int size_y = CompornentSize.CATEGORY_P_Y;
		for (String str : Category.category) {
			JButton _jb = new JButton(str);
			setCompornentSize(_jb, size_x - 1, size_y - 2);
			_jb.addActionListener(MainListener.ME);
			_jb.setActionCommand("category");
			_jb.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
			_jb.setMargin(new Insets(0, 0, 0, 0));
			category_p.add(_jb);
		}

	}

	private void setCompornent() {
		SpringLayout layout = new SpringLayout();
		p.setLayout(layout);
		setFirstLayout(layout, 0, 0, category_p, p);
		setLayout(layout, 0, 0, object_p, category_p);
		setLayout(layout, 0, 0, ob_detail_p, object_p);
		setLayout(layout, 0, 0, explain_p, ob_detail_p);
		category_p.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		explain_p.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		ob_detail_p.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		disc_one.addActionListener(MainListener.ME);
		disc_two.addActionListener(MainListener.ME);
		lv.addActionListener(MainListener.ME);
		con.addActionListener(MainListener.ME);
		jc.addActionListener(MainListener.ME);
		forge.addActionListener(MainListener.ME);
		setCompornentSize(category_p, CompornentSize.CATEGORY_P_X,
				CompornentSize.CATEGORY_P_Y);
		setCompornentSize(object_p, CompornentSize.OBJECT_P_X,
				CompornentSize.OBJECT_P_Y);
		setCompornentSize(ob_detail_p, CompornentSize.DETAIL_P_X,
				CompornentSize.DETAIL_P_Y);
		setCompornentSize(explain_p, CompornentSize.EXPLAIN_P_X,
				CompornentSize.EXPLAIN_P_Y);
		object_p.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		setCategoryPanel();
		setObjectPanel();
		setFirstObjectDetail();
		setObjectDetail();
		forge.setText("0");
		p.add(category_p);
		p.add(object_p);
		p.add(ob_detail_p);
		p.add(explain_p);
		add(p);
	}

	private void setCompornentSize(JComponent jc, int _x, int _y) {
		jc.setMaximumSize(new Dimension(_x, _y));
		jc.setPreferredSize(new Dimension(_x, _y));
	}

	public void setExplanText(ArrayList<String> list) {
		explain_p.removeAll();
		explain_p.repaint();
		explain_p.setLayout(new BoxLayout(explain_p, BoxLayout.Y_AXIS));
		explain_p.add(ob3);
		int y = 0;
		for (String str : list) {
			System.out.println(str);
			if (y == 0) {
				ob3.setText(str);
				y++;
				System.out.println("y == 0");
				continue;
			}
			JLabel jl = new JLabel(str);
			jl.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
			explain_p.add(jl);
			y++;
		}
		explain_p.revalidate();

		// ob3.setText(str);
	}

	private void setFirstLayout(SpringLayout sl, int i, int i1, JComponent j,
			JComponent j1) {
		sl.putConstraint(SpringLayout.NORTH, j, i, SpringLayout.NORTH, j1);
		sl.putConstraint(SpringLayout.WEST, j, i1, SpringLayout.WEST, p);
		sl.putConstraint(SpringLayout.EAST, j, i1, SpringLayout.EAST, p);
	}

	public void setFirstObjectDetail() {
		eien.addActionListener(CheckListener.ME);
		kaei.addActionListener(CheckListener.ME);
		kouma.addActionListener(CheckListener.ME);
		huuzin.addActionListener(CheckListener.ME);
		tirei.addActionListener(CheckListener.ME);
		youyou.addActionListener(CheckListener.ME);
		seiren.addActionListener(CheckListener.ME);
		eien.setActionCommand("永");
		kouma.setActionCommand("紅");
		youyou.setActionCommand("妖");
		huuzin.setActionCommand("風");
		tirei.setActionCommand("地");
		seiren.setActionCommand("星");
		kaei.setActionCommand("花");
	}

	private void setLayout(SpringLayout sl, int i, int i1, JComponent j,
			JComponent j1) {
		sl.putConstraint(SpringLayout.NORTH, j, i, SpringLayout.SOUTH, j1);
		sl.putConstraint(SpringLayout.WEST, j, i1, SpringLayout.WEST, p);
		sl.putConstraint(SpringLayout.EAST, j, i1, SpringLayout.EAST, p);
	}

	public void setObjectDetail() {
		GridLayout lay = new GridLayout(1, 2);
		ob_detail_p.setLayout(lay);
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 10, 5);
		FlowLayout layout2 = new FlowLayout(FlowLayout.LEFT, 0, 8);
		detail_p.setLayout(layout);
		check_p.setLayout(layout2);
		if (QS.category_position.matches("エネミー")) {
			JLabel lb = new JLabel("LV: ");
			JLabel lb1 = new JLabel("状態: ");
			detail_p.add(lb);
			detail_p.add(lv);
			detail_p.add(lb1);
			detail_p.add(con);
			check_p.add(kou_l);
			check_p.add(kouma);
			check_p.add(you_l);
			check_p.add(youyou);
			check_p.add(ei_l);
			check_p.add(eien);
			check_p.add(hana_l);
			check_p.add(kaei);
			check_p.add(huu_l);
			check_p.add(huuzin);
			check_p.add(ti_l);
			check_p.add(tirei);
			check_p.add(sei_l);
			check_p.add(seiren);
		} else if (QS.category_position.matches("トラップ")) {
		} else if (QS.category_position.matches("カード")) {
			forge.setText("0");
			forge.setMaximumSize(new Dimension(40, 20));
			forge.setPreferredSize(new Dimension(40, 20));
			JLabel lb = new JLabel("");
			detail_p.add(lb);
			detail_p.add(jl);
			detail_p.add(forge);
			detail_p.add(jc);
			check_p.add(kou_l);
			check_p.add(kouma);
			check_p.add(you_l);
			check_p.add(youyou);
			check_p.add(ei_l);
			check_p.add(eien);
			check_p.add(hana_l);
			check_p.add(kaei);
			check_p.add(huu_l);
			check_p.add(huuzin);
			check_p.add(ti_l);
			check_p.add(tirei);
			check_p.add(sei_l);
			check_p.add(seiren);
		} else if (QS.category_position.matches("ディスク")) {
			JLabel lb = new JLabel("");
			detail_p.add(lb);
			detail_p.add(disc_one);
			detail_p.add(disc_two);
			detail_p.add(jc);
		} else {
			forge.setText("0");
			forge.setMaximumSize(new Dimension(40, 20));
			forge.setPreferredSize(new Dimension(40, 20));
			JLabel lb = new JLabel("");
			detail_p.add(lb);
			detail_p.add(jl);
			detail_p.add(forge);
			detail_p.add(jc);
		}
		ob_detail_p.add(detail_p);
		ob_detail_p.add(check_p);
	}

	public void setObjectPanel() {
		// GridBagLayout gl = new GridBagLayout();
		// GridBagConstraints gbc = new GridBagConstraints();
		// gbc.fill = GridBagConstraints.BOTH;
		FlowLayout layout = new FlowLayout(4, 2, 1);
		layout.setAlignment(FlowLayout.LEFT);
		object_p.setLayout(layout);
		for (String str : Category.getNames(QS.category_position)) {
			if (QS.category_position == "エネミー") {
				if (!QS.ENEMY_CHECK_LIST.contains(str)
						&& !QS.ENEMY_CHECK_LIST.isEmpty()) {
					continue;
				}
				Base_Enemy em = StringIsChangedToObject.returnBaseEnemy(str, 1,
						true);
				JButton jb = new JButton(str);
				jb.setMargin(new Insets(0, 0, 0, 0));
				jb.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11));
				jb.addActionListener(MainListener.ME);
				jb.setActionCommand("object");
				setCompornentSize(jb, 120, 20);
				object_p.add(jb);
				JTextField jf1 = new JTextField(ObjectExplain.oneLineEnemyText(
						em, 1));
				JTextField jf2 = new JTextField(ObjectExplain.oneLineEnemyText(
						em, 2));
				JTextField jf3 = new JTextField(ObjectExplain.oneLineEnemyText(
						em, 3));
				jf1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				jf2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				jf3.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				setCompornentSize(jf1, 86, 20);
				setCompornentSize(jf2, 86, 20);
				setCompornentSize(jf3, 86, 20);
				object_p.add(jf1);
				object_p.add(jf2);
				object_p.add(jf3);
				continue;
			}
			if (QS.category_position == "カード") {
				if (!QS.CARD_CHECK_LIST.contains(str)
						&& !QS.CARD_CHECK_LIST.isEmpty()) {
					continue;
				}
			}
			JButton jb = new JButton(str);
			jb.setMargin(new Insets(0, 0, 0, 0));
			jb.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11));
			jb.addActionListener(MainListener.ME);
			jb.setActionCommand("object");
			setCompornentSize(jb, 120, 20);
			object_p.add(jb);
			if (QS.category_position == "ディスク") {
				JTextField jf1 = new JTextField("指定したアイテムを強化するぞ");
				jf1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				setCompornentSize(jf1, 262, 20);
				object_p.add(jf1);
			} else if (QS.category_position == "トラップ") {
				JTextField jf1 = new JTextField(ObjectExplain.oneLineItemText(
						StringIsChangedToObject.returnBaseTrap(str, true), 1));
				jf1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				setCompornentSize(jf1, 262, 20);
				object_p.add(jf1);
			} else {
				JTextField jf1 = new JTextField(
						ObjectExplain.oneLineItemText(
								StringIsChangedToObject.returnBaseItem(
										str,
										Category.getCategoryNames(QS.category_position),
										true), 1));
				jf1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
				setCompornentSize(jf1, 262, 20);
				object_p.add(jf1);
			}
		}

		// String[] names = Category.getNames(QuestionStatus.category_position);
		// int count = 0;
		// int y = 0;
		// for (String str : names) {
		// gbc.gridx = count;
		// gbc.gridy = y;
		// // gbc.weightx = 0.1;
		// // gbc.weighty = 0.1;
		// JButton jb = new JButton(str);
		// jb.addActionListener(MainListener.ME);
		// jb.setActionCommand("object");
		// jb.setSize(new Dimension(10, 10));
		// jb.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		// gl.setConstraints(jb, gbc);
		// object_p.add(jb);
		// count++;
		// if (count == 4) {
		// count = 0;
		// y++;
		// }
		// }
	}
}

package connection.sv_cl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import main.constant.FR;
import main.thread.MainThread;

public class CommunicationSubFrame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static CommunicationSubFrame me;

	public static CommunicationSubFrame create(int x, int y) {
		if (me != null)
			me.dispose();
		me = new CommunicationSubFrame(x, y);
		return me;
	}

	public static void main(String[] args) {
		new CommunicationSubFrame(0, 0);
	}

	private int w, h;

	// private void setButtons3() {
	// JPanel panel_lv0 = new JPanel();
	// panel_lv0.setLayout(new OverlayLayout(panel_lv0));
	// panel_lv0.add(createPanelLv2());
	// panel_lv0.add(createLabel());
	// add(panel_lv0);
	// }
	private JPanel panel_lv2;;

	private JLabel label;

	private JButton button;

	private String identifiler = "@@";

	private final int myself = 0, opponent = 1, system = 2;

	private String[] names = { "自分", "相手", "＊＊" };

	private Color[] colors = { Color.WHITE, Color.YELLOW, Color.GREEN };

	private JTextField input_field;

	private JScrollPane scrollPane;

	// private void setButtons2() {
	// JPanel panel_lv0 = new JPanel();
	// panel_lv0.setLayout(new OverlayLayout(panel_lv0));
	// JPanel panel_lv1 = new JPanel();
	// // {
	// // private static final long serialVersionUID = 1L;
	// //
	// // @Override
	// // public void paint(Graphics g) {
	// // if (back_groud_image != null) {
	// // g.drawImage(back_groud_image, 0, 0, null);
	// // back_groud_image = null;
	// // }
	// // g.setColor(Color.RED);
	// // g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
	// // super.paint(g);
	// // }
	// // };
	// JLabel label = new JLabel("aa");
	// label.setBackground(Color.RED);
	// panel_lv1.add(label, BorderLayout.CENTER);
	// JPanel panel_lv2 = new JPanel();
	// panel_lv2.setLayout(new BorderLayout());
	// JPanel p1 = createPanel();
	// p1.add(createList(), BorderLayout.CENTER);
	// p1.add(createInputField(), BorderLayout.SOUTH);
	// JPanel p2 = createPanel();
	// p2.add(createSubmitButton(), BorderLayout.SOUTH);
	// panel_lv2.add(p1, BorderLayout.CENTER);
	// panel_lv2.add(p2, BorderLayout.EAST);
	// panel_lv0.add(panel_lv1);
	// panel_lv1.add(panel_lv2);
	// add(panel_lv0);
	// }

	private Color scroll_pain_color = new Color(0, 0, 0, 200);

	JTextPane text_pane = new JTextPane();

	private DefaultStyledDocument document;

	private boolean flag_end = false;

	private CommunicationSubFrame(int x, int y) {
		// setLayout(new BorderLayout());
		// setButtons();
		setResizable(false);
		setTitle("シェアウィンドウ");
		setVisible(true);
		Insets i = getInsets();
		int insets_w = i.left + i.right;
		int insets_h = +i.bottom + i.top;
		w = FR.SCREEN_WIDTH;
		h = FR.SCREEN_HEIGHT + 20;
		setBounds(x, y, w + insets_w, h + insets_h);
		setButtons();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// new Thread() {
		// public void run() {
		// CommunicationSubFrame.this.doLayout();
		// CommunicationSubFrame.this.setButtons();
		// };
		// }.start();
		new Thread(this).start();
	}

	private void addMouseListener(Component layered_pane) {
		MouseListener l = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panel_lv2.setVisible(false);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_lv2.setVisible(true);
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseExited(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseExited(e);
			}
		};
		layered_pane.addMouseListener(l);
		text_pane.addMouseListener(l);
	}

	private void appendDisplay(String msg, int who) {
		if (msg != null) {
			SimpleAttributeSet attribute = new SimpleAttributeSet();
			attribute.addAttribute(StyleConstants.Foreground, colors[who]);
			try {
				msg = names[who] + "：" + msg + "\n";
				document.insertString(document.getLength(), msg, attribute);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			JViewport view = scrollPane.getViewport();
			view.setViewPosition(new Point(0, text_pane.getHeight()));
			repaint();
		}
	}

	private Component createInputField() {
		input_field = new JTextField();
		input_field.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					submit();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		return input_field;
	}

	private Component createLabel() {
		// JPanel sub_panel = new JPanel();
		label = new JLabel();
		// label.setBackground(Color.black);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		// BufferedImage im = MainThread.ME.getScreenShot();
		// FileReadSupporter
		// .readPNGImage("otog/image/music_bg01.png");
		// setBackground(im);
		// sub_panel.add(label, BorderLayout.CENTER);
		// return sub_panel;
		label.setBounds(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);

		return label;
	}

	private Component createList() {
		text_pane.setBackground(new Color(0, 0, 0, 0));
		text_pane.setOpaque(true);
		text_pane.setEditable(false);
		scrollPane = new JScrollPane(text_pane);
		document = new DefaultStyledDocument();
		text_pane.setDocument(document);
		scrollPane.setBackground(scroll_pain_color);
		return scrollPane;
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		return panel;
	}

	private Component createPanelLv2() {
		panel_lv2 = new JPanel();
		panel_lv2.setOpaque(false);
		panel_lv2.setLayout(new BorderLayout());
		JPanel sub_panel_center = createPanel();
		sub_panel_center.setOpaque(false);
		sub_panel_center.add(createList(), BorderLayout.CENTER);
		sub_panel_center.add(createInputField(), BorderLayout.SOUTH);
		JPanel sub_panel_east = createPanel();
		sub_panel_east.setOpaque(false);
		sub_panel_east.add(createSubmitButton(), BorderLayout.SOUTH);
		panel_lv2.add(sub_panel_center, BorderLayout.CENTER);
		panel_lv2.add(sub_panel_east, BorderLayout.EAST);
		panel_lv2.setBounds(0, 0, w, h);
		return panel_lv2;
	}

	private Component createSubmitButton() {
		button = new JButton("送信");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		return button;
	}

	@Override
	public void dispose() {
		me = null;
		super.dispose();
	}

	public void end() {
		flag_end = true;
		dispose();
	}

	public void recieve(String msg) {
		if (msg.startsWith(identifiler)) {
			appendDisplay(msg.substring(identifiler.length()), opponent);
		} else {
			appendDisplay(msg, system);
		}

		// if (msg != null) {
		//
		// SimpleAttributeSet attribute = new SimpleAttributeSet();
		// attribute.addAttribute(StyleConstants.Foreground, Color.RED);
		// try {
		// document.insertString(document.getLength(), msg + "\n",
		// attribute);
		// } catch (BadLocationException e) {
		// e.printStackTrace();
		// }
		// display_area.append("\n");
		// repaint();
		// }
	}

	@Override
	public void run() {
		while (!flag_end) {
			repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private void screenshot() {
		String first = identifiler + "（スクリーンショット送信）";
		BufferedImage im = MainThread.ME.getScreenShot();
		String end = identifiler + "（送信完了）";
		// setBackground(im);
		appendDisplay(first.substring(identifiler.length()), myself);
		SocketHolder.submit(first, new ImagenPerso(im), end);
		appendDisplay(end.substring(identifiler.length()), myself);
	}

	public void setBackground(BufferedImage image) {
		ImageIcon icon = new ImageIcon(image);
		label.setIcon(icon);
	}

	private void setButtons() {
		JLayeredPane layered_pane = new JLayeredPane();
		setContentPane(layered_pane);
		layered_pane.add(createPanelLv2(), JLayeredPane.DEFAULT_LAYER);
		layered_pane.add(createLabel(), JLayeredPane.DEFAULT_LAYER);
		addMouseListener(layered_pane);
	}

	private void submit() {
		String msg = input_field.getText();
		if (msg == null || msg.isEmpty()) {
			screenshot();
		} else {
			input_field.setText("");
			SocketHolder.submit(identifiler + msg);
			appendDisplay(msg, myself);
		}
	}

	public void warning(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

}

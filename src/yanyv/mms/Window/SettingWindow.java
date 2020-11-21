package yanyv.mms.Window;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import yanyv.mms.view.DateChooser;
import yanyv.mms.vo.Match;
import yanyv.mms.vo.MatchMode;
import yanyv.mms.web.MatchWeb;
import yanyv.mms.web.QueryWeb;

public class SettingWindow extends JFrame {

	private int count = 2;

	private static int windowWidth = 300;
	private static int windowHeight = 350;

	private int widthDValue = 150;

	boolean applyed = false;

	JFrame main = this;
	JPanel mainPane;

	JLabel name;
	JTextField nameIn;

	JLabel num;
	JLabel numShow;
	JButton sub, add;

	JLabel fuhuo;
	JCheckBox fuhuoBox;

	JLabel web;
	JCheckBox webBox;

	JButton apply;

	// web views

	JLabel startDate;
	JLabel deadLine;
	JLabel startLabel;
	JLabel deadLabel;
	JLabel mode;

	DateChooser startChooser;
	DateChooser deadChooser;

	JComboBox<MatchMode> modes;

	boolean numclicked = false;
	int numstart = 0;
	int newnum = 2;

	InputWindow input;

	public SettingWindow(JFrame mainWindow) {

		this.setTitle("��ʼ����");
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(mainWindow);
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				if (!applyed)
					mainWindow.setVisible(true);
			}

		});

		input = new InputWindow(this);

		mainPane = new JPanel();
		mainPane.setLayout(null);

		name = new JLabel("��������");
		nameIn = new JTextField();

		num = new JLabel("��������");
		numShow = new JLabel("" + count);

		Font font = new Font("����", Font.BOLD, 22);

		name.setFont(font);
		nameIn.setFont(font);

		num.setFont(font);
		numShow.setFont(font);

		nameIn.setHorizontalAlignment(JTextField.CENTER);
		numShow.setHorizontalAlignment(JLabel.CENTER);

		name.setSize(100, 30);
		nameIn.setSize(100, 30);
		name.setLocation(40, 40);
		nameIn.setLocation(150, 40);

		num.setSize(100, 30);
		numShow.setSize(40, 30);
		num.setLocation(40, 90);
		numShow.setLocation(180, 90);

		sub = new JButton("��");
		add = new JButton("��");

		sub.setSize(20, 20);
		add.setSize(20, 20);
		sub.setLocation(155, 95);
		add.setLocation(225, 95);

		sub.setMargin(new Insets(0, 0, 0, 0));
		add.setMargin(new Insets(0, 0, 0, 0));

		sub.setEnabled(false);

		fuhuo = new JLabel("������");
		fuhuo.setSize(100, 30);
		fuhuo.setLocation(40, 140);
		fuhuo.setFont(font);

		fuhuoBox = new JCheckBox("off");
		fuhuoBox.setSize(100, 30);
		fuhuoBox.setLocation(190, 140);
		fuhuoBox.setFont(font);

		web = new JLabel("������");
		web.setSize(100, 30);
		web.setLocation(40, 190);
		web.setFont(font);

		webBox = new JCheckBox("off");
		webBox.setSize(100, 30);
		webBox.setLocation(190, 190);
		webBox.setFont(font);

		apply = new JButton("ȷ��");
		apply.setSize(200, 40);
		apply.setLocation(40, 250);
		apply.setFont(font);

		// web view

		startDate = new JLabel("��������");
		startDate.setSize(100, 30);
		startDate.setLocation(40, 78);
		startDate.setFont(font);
		startDate.setVisible(false);

		startLabel = new JLabel("����ѡ������");
		startLabel.setSize(nameIn.getSize().width + widthDValue / 2, nameIn.getSize().height);
		startLabel.setLocation(nameIn.getLocation().x + widthDValue / 2, 78);
		startLabel.setFont(font);
		startLabel.setVisible(false);

		deadLine = new JLabel("������ֹ");
		deadLine.setSize(100, 30);
		deadLine.setLocation(40, 116);
		deadLine.setFont(font);
		deadLine.setVisible(false);

		deadLabel = new JLabel("����ѡ������");
		deadLabel.setSize(nameIn.getSize().width + widthDValue / 2, nameIn.getSize().height);
		deadLabel.setLocation(nameIn.getLocation().x + widthDValue / 2, 116);
		deadLabel.setFont(font);
		deadLabel.setVisible(false);

		startChooser = DateChooser.getInstance("yyyy��MM��dd��");
		deadChooser = DateChooser.getInstance("yyyy��MM��dd��");

		startChooser.register(startLabel);
		deadChooser.register(deadLabel);

		mode = new JLabel("����");
		mode.setSize(100, 30);
		mode.setLocation(40, 154);
		mode.setFont(font);
		mode.setVisible(false);

		modes = new JComboBox<MatchMode>();
		modes.setSize(nameIn.getSize().width + widthDValue / 2, nameIn.getSize().height);
		modes.setLocation(nameIn.getLocation().x + widthDValue / 2, 154);
		modes.setFont(font);
		modes.setVisible(false);

		mainPane.add(name);
		mainPane.add(nameIn);
		mainPane.add(num);
		mainPane.add(numShow);
		mainPane.add(sub);
		mainPane.add(add);
		mainPane.add(fuhuo);
		mainPane.add(fuhuoBox);
		mainPane.add(web);
		mainPane.add(webBox);
		mainPane.add(apply);

		mainPane.add(startDate);
		mainPane.add(deadLine);
		mainPane.add(startLabel);
		mainPane.add(deadLabel);
		mainPane.add(mode);
		mainPane.add(modes);

		addListener();
		init();

		this.setContentPane(mainPane);

	}

	private void init() {
		if (MainWindow.logined) {
			webBox.setEnabled(true);
			addModes();
		} else {
			webBox.setEnabled(false);
		}

	}

	private void addListener() {
		sub.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (count > 2)
					count--;
				if (count == 2)
					sub.setEnabled(false);
				numShow.setText("" + count);
			}

		});

		add.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				count++;
				sub.setEnabled(true);
				numShow.setText("" + count);
			}

		});

		numShow.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				main.setCursor(Cursor.W_RESIZE_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!numclicked)
					main.setCursor(Cursor.DEFAULT_CURSOR);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				numclicked = true;
				numstart = e.getX();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				numclicked = false;
				count = newnum;
				if (count > 2)
					sub.setEnabled(true);
				if (count == 2)
					sub.setEnabled(false);
				main.setCursor(Cursor.DEFAULT_CURSOR);
			}

		});

		numShow.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				newnum = count + (e.getX() - numstart) / 10;
				if (newnum < 2)
					newnum = 2;
				if (count >= 2) {
					numShow.setText("" + newnum);
				}
			}

		});

		fuhuoBox.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (fuhuoBox.isSelected()) {
					fuhuoBox.setText("on");
				} else {
					fuhuoBox.setText("off");
				}
			}

		});

		webBox.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!MainWindow.logined) {
					JOptionPane.showMessageDialog(null, "���ȵ�¼��������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (webBox.isSelected()) {
					webOn();
				} else {
					webOff();
				}
			}

		});

		apply.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				applyed = true;

				if (!webBox.isSelected()) {
					input.setCount(count);
					input.setFuhuo(fuhuoBox.isSelected());
				} else {
					Date startDate = startChooser.getDate();
					Date deadDate = deadChooser.getDate();
					Date now = new Date();

					if (now.after(deadDate)) {
						JOptionPane.showMessageDialog(null, "��ֹ�������ڵ�ǰʱ��", "����", JOptionPane.WARNING_MESSAGE);
					} else if (now.after(startDate)) {
						JOptionPane.showMessageDialog(null, "��ʼ�������ڵ�ǰʱ��", "����", JOptionPane.WARNING_MESSAGE);
					} else if (deadDate.before(startDate)) {
						Calendar startCal = Calendar.getInstance();
						Calendar deadCal = Calendar.getInstance();
						startCal.setTime(startDate);
						deadCal.setTime(deadDate);
						startCal.set(Calendar.HOUR_OF_DAY, 0);
						deadCal.set(Calendar.HOUR_OF_DAY, 0);
						startCal.set(Calendar.MINUTE, 0);
						deadCal.set(Calendar.MINUTE, 0);
						startCal.set(Calendar.SECOND, 0);
						deadCal.set(Calendar.SECOND, 0);
						startDate = startCal.getTime();
						deadDate = deadCal.getTime();

						Match mat = new Match();
						mat.setName(nameIn.getText());
						mat.setCreaterUid(MainWindow.acc.getUid());
						mat.setModeId(((MatchMode) modes.getSelectedItem()).getId());
						mat.setStartDate(startDate);
						mat.setDeadline(deadDate);
						try {
							JSONObject result = MatchWeb.addMatchs(mat);
							input.setMid(result.getString("mid"));
							System.out.println(result);

						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "�����쳣, ����ʧ��", "����", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "��ֹ�������ڿ�ʼ����", "����", JOptionPane.WARNING_MESSAGE);
					}

				}

				// ��¼�������Ա��Ϣҳ��
				input.setVisible(true);
				input.setName(nameIn.getText());
				input.setWeb(webBox.isSelected());

				main.setVisible(false);
			}

		});

	}

	private void webOn() {
		this.setTitle("��ģʽ-��������");
		apply.setText("����");

		webBox.setText("on");
		num.setVisible(false);
		sub.setVisible(false);
		add.setVisible(false);
		numShow.setVisible(false);
		fuhuo.setVisible(false);
		fuhuoBox.setVisible(false);

		this.setSize(this.getSize().width + widthDValue, this.getSize().height);

		nameIn.setLocation(nameIn.getLocation().x + widthDValue / 2, nameIn.getLocation().y);
		nameIn.setSize(nameIn.getSize().width + widthDValue / 2, nameIn.getSize().height);
		webBox.setLocation(webBox.getLocation().x + widthDValue, webBox.getLocation().y);
		apply.setSize(apply.getSize().width + widthDValue, apply.getSize().height);

		startDate.setVisible(true);
		deadLine.setVisible(true);
		startLabel.setVisible(true);
		deadLabel.setVisible(true);
		mode.setVisible(true);
		modes.setVisible(true);
	}

	private void webOff() {
		this.setTitle("��ʼ����");
		apply.setText("ȷ��");

		webBox.setText("off");
		num.setVisible(true);
		sub.setVisible(true);
		add.setVisible(true);
		numShow.setVisible(true);
		fuhuo.setVisible(true);
		fuhuoBox.setVisible(true);

		this.setSize(this.getSize().width - widthDValue, this.getSize().height);

		nameIn.setLocation(nameIn.getLocation().x - widthDValue / 2, nameIn.getLocation().y);
		nameIn.setSize(nameIn.getSize().width - widthDValue / 2, nameIn.getSize().height);
		webBox.setLocation(webBox.getLocation().x - widthDValue, webBox.getLocation().y);
		apply.setSize(apply.getSize().width - widthDValue, apply.getSize().height);

		startDate.setVisible(false);
		deadLine.setVisible(false);
		startLabel.setVisible(false);
		deadLabel.setVisible(false);
		mode.setVisible(false);
		modes.setVisible(false);

	}

	private void addModes() {
		try {
			List<MatchMode> modes = QueryWeb.queryAllMatchMode();
			for (MatchMode m : modes) {
				this.modes.addItem(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�����쳣, ��ȡ�����б�ʧ��", "����", JOptionPane.WARNING_MESSAGE);
		}

	}

}

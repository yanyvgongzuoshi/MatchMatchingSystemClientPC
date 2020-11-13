package yanyv.mms.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import yanyv.mms.manager.JSONManager;
import yanyv.mms.manager.SaveManager;
import yanyv.mms.vo.Match;
import yanyv.mms.web.QueryWeb;

public class ConWindow extends JFrame {

	ArrayList<File> fileList;

	private static int windowWidth = 500;
	private static int windowHeight = 300;

	boolean applyed = false;
	private boolean isWeb = false;

	String info = "";

	JFrame main = this;
	JPanel mainPane;

	JScrollPane listPane;

	JLabel title;
	JLabel fuhuo;
	JLabel finish;
	JLabel date;
	JLabel online;
	
	JLabel mode;
	JLabel startDate;
	JLabel endDate;
	JLabel deadline;
	JLabel state;
	JLabel size;
	JLabel createDate;
	JLabel modeInfo;
	JLabel startDateInfo;
	JLabel endDateInfo;
	JLabel deadlineInfo;
	JLabel stateInfo;
	JLabel sizeInfo;
	JLabel createDateInfo;

	JButton open;
	JButton del;

	DefaultListModel<Match> listModel;
	JList<Match> list;

	public ConWindow(JFrame mainWindow) {

		this.setTitle("��ʷ��¼");
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				if (!applyed) {
					mainWindow.setVisible(true);
				}
			}

		});

		Font font = new Font("����", Font.BOLD, 15);

		mainPane = new JPanel();
		mainPane.setLayout(null);

		listModel = new DefaultListModel<Match>();
		list = new JList<Match>(listModel);
		list.setSize(300, 2000);
		list.setFont(font);

		initList();

		listPane = new JScrollPane(list);
		listPane.setSize(230, 180);
		listPane.setLocation(45, 25);
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		Font titleFont = new Font("����", Font.BOLD, 20);
		title = new JLabel("δѡ��", JLabel.CENTER);
		title.setSize(180, 25);
		title.setLocation(285, 25);
		title.setFont(titleFont);
		title.setOpaque(true);
		// title.setBackground(Color.BLUE);

		Font infoFont = new Font("����", Font.BOLD, 15);
		fuhuo = new JLabel("��������", JLabel.LEFT);
		fuhuo.setSize(180, 25);
		fuhuo.setLocation(285, 60);
		fuhuo.setFont(infoFont);
		fuhuo.setOpaque(true);
		// fuhuo.setBackground(Color.RED);

		finish = new JLabel("�ѽ�����", JLabel.LEFT);
		finish.setSize(180, 25);
		finish.setLocation(285, 95);
		finish.setFont(infoFont);
		finish.setOpaque(true);
		// finish.setBackground(Color.GREEN);

		date = new JLabel("��  �ڣ�", JLabel.LEFT);
		date.setSize(180, 25);
		date.setLocation(285, 130);
		date.setFont(infoFont);
		date.setOpaque(true);
		// date.setBackground(Color.PINK);

		online = new JLabel("�ƴ洢��", JLabel.LEFT);
		online.setSize(180, 25);
		online.setLocation(285, 165);
		online.setFont(infoFont);
		online.setOpaque(true);
		// online.setBackground(Color.GRAY);
		
		mode = new JLabel("��    �ƣ�", JLabel.LEFT);
		mode.setSize(85, 20);
		mode.setLocation(285, 60);
		mode.setFont(infoFont);
		mode.setOpaque(true);
		mode.setBackground(Color.LIGHT_GRAY);
		
		startDate = new JLabel("�������ڣ�", JLabel.LEFT);
		startDate.setSize(85, 20);
		startDate.setLocation(285, 77);
		startDate.setFont(infoFont);
		startDate.setOpaque(true);
		
		endDate = new JLabel("�������ڣ�", JLabel.LEFT);
		endDate.setSize(85, 20);
		endDate.setLocation(285, 95);
		endDate.setFont(infoFont);
		endDate.setOpaque(true);
		
		deadline = new JLabel("������ֹ��", JLabel.LEFT);
		deadline.setSize(85, 20);
		deadline.setLocation(285, 112);
		deadline.setFont(infoFont);
		deadline.setOpaque(true);
		
		state = new JLabel("״    ̬��", JLabel.LEFT);
		state.setSize(85, 20);
		state.setLocation(285, 130);
		state.setFont(infoFont);
		state.setOpaque(true);
		
		size = new JLabel("����������", JLabel.LEFT);
		size.setSize(85, 20);
		size.setLocation(285, 147);
		size.setFont(infoFont);
		size.setOpaque(true);
		
		createDate = new JLabel("�������ڣ�", JLabel.LEFT);
		createDate.setSize(85, 20);
		createDate.setLocation(285, 165);
		createDate.setFont(infoFont);
		createDate.setOpaque(true);
		
		modeInfo = new JLabel("ceshi", JLabel.LEFT);
		modeInfo.setSize(95, 20);
		modeInfo.setLocation(370, 60);
		modeInfo.setFont(infoFont);
		modeInfo.setOpaque(true);
		modeInfo.setBackground(Color.BLUE);
		
		startDateInfo = new JLabel("", JLabel.LEFT);
		startDateInfo.setSize(95, 20);
		startDateInfo.setLocation(370, 77);
		startDateInfo.setFont(infoFont);
		startDateInfo.setOpaque(true);
		
		endDateInfo = new JLabel("", JLabel.LEFT);
		endDateInfo.setSize(95, 20);
		endDateInfo.setLocation(370, 95);
		endDateInfo.setFont(infoFont);
		endDateInfo.setOpaque(true);
		
		deadlineInfo = new JLabel("", JLabel.LEFT);
		deadlineInfo.setSize(95, 20);
		deadlineInfo.setLocation(370, 112);
		deadlineInfo.setFont(infoFont);
		deadlineInfo.setOpaque(true);
		
		stateInfo = new JLabel("", JLabel.LEFT);
		stateInfo.setSize(95, 20);
		stateInfo.setLocation(370, 130);
		stateInfo.setFont(infoFont);
		stateInfo.setOpaque(true);
		
		sizeInfo = new JLabel("", JLabel.LEFT);
		sizeInfo.setSize(95, 20);
		sizeInfo.setLocation(370, 147);
		sizeInfo.setFont(infoFont);
		sizeInfo.setOpaque(true);
		
		createDateInfo = new JLabel("", JLabel.LEFT);
		createDateInfo.setSize(95, 20);
		createDateInfo.setLocation(370, 165);
		createDateInfo.setFont(infoFont);
		createDateInfo.setOpaque(true);
		
		mode.setVisible(false);
		startDate.setVisible(false);
		endDate.setVisible(false);
		deadline.setVisible(false);
		state.setVisible(false);
		size.setVisible(false);
		createDate.setVisible(false);
		modeInfo.setVisible(false);
		startDateInfo.setVisible(false);
		endDateInfo.setVisible(false);
		deadlineInfo.setVisible(false);
		stateInfo.setVisible(false);
		sizeInfo.setVisible(false);
		createDateInfo.setVisible(false);

		open = new JButton("��");
		open.setSize(80, 30);
		open.setLocation(300, 210);
		open.setFont(titleFont);

		del = new JButton("ɾ��");
		del.setSize(80, 30);
		del.setLocation(390, 210);
		del.setFont(titleFont);

		setListener();

		mainPane.add(listPane);
		mainPane.add(title);
		mainPane.add(fuhuo);
		mainPane.add(finish);
		mainPane.add(date);
		mainPane.add(online);
		mainPane.add(open);
		mainPane.add(del);
		
		mainPane.add(mode);
		mainPane.add(startDate);
		mainPane.add(endDate);
		mainPane.add(deadline);
		mainPane.add(state);
		mainPane.add(size);
		mainPane.add(createDate);
		mainPane.add(modeInfo);
		mainPane.add(startDateInfo);
		mainPane.add(endDateInfo);
		mainPane.add(deadlineInfo);
		mainPane.add(stateInfo);
		mainPane.add(sizeInfo);
		mainPane.add(createDateInfo);

		this.setContentPane(mainPane);
	}

	private void setListener() {
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				showInfo();
			}

		});
		
		open.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				applyed = true;
				if(list.getSelectedValue().isWeb()) {
					
				} else {
					MatchWindow match = new MatchWindow();
					
					main.setVisible(false);
					match.setVisible(true);
					
					match.open(info);
					
				}
			}
			
		});
		
		del.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.getSelectedValue().isWeb()) {
					
				} else {
					SaveManager.del(list.getSelectedValue().toString());
				}
				initList();
			}
			
		});
	}

	protected void showInfo() {
		if (list.getSelectedValue().isWeb()) {
			if(!this.isWeb) {
				webOn();
			}
		} else {
			if(this.isWeb) {
				webOff();
			}
			
			String name = list.getSelectedValue().toString();

			info = SaveManager.getInfo(name);

			JSONManager jm = new JSONManager(info);
			title.setText(jm.getName());
			if (jm.getFuhuo()) {
				fuhuo.setText("����������");
			} else {
				fuhuo.setText("����������");
			}
			if (jm.getFinish()) {
				finish.setText("�ѽ�������");
			} else {
				finish.setText("�ѽ�������");
			}
			date.setText("��  �ڣ�" + jm.getDate());
			if (jm.getOnline()) {
				online.setText("�ƴ洢��������");
			} else {
				online.setText("�ƴ洢��δ����");
			}

		}

	}

	private void webOff() {
		fuhuo.setVisible(true);
		finish.setVisible(true);
		date.setVisible(true);
		online.setVisible(true);
		
		mode.setVisible(false);
		startDate.setVisible(false);
		endDate.setVisible(false);
		deadline.setVisible(false);
		state.setVisible(false);
		size.setVisible(false);
		createDate.setVisible(false);
		modeInfo.setVisible(false);
		startDateInfo.setVisible(false);
		endDateInfo.setVisible(false);
		deadlineInfo.setVisible(false);
		stateInfo.setVisible(false);
		sizeInfo.setVisible(false);
		createDateInfo.setVisible(false);
	}

	private void webOn() {
		fuhuo.setVisible(false);
		finish.setVisible(false);
		date.setVisible(false);
		online.setVisible(false);
		
		mode.setVisible(true);
		startDate.setVisible(true);
		endDate.setVisible(true);
		deadline.setVisible(true);
		state.setVisible(true);
		size.setVisible(true);
		createDate.setVisible(true);
		modeInfo.setVisible(true);
		startDateInfo.setVisible(true);
		endDateInfo.setVisible(true);
		deadlineInfo.setVisible(true);
		stateInfo.setVisible(true);
		sizeInfo.setVisible(true);
		createDateInfo.setVisible(true);
	}

	private void initList() {
		listModel.removeAllElements();
		fileList = SaveManager.getFileList();

		for (File f : fileList) {
			Match m = new Match();
			m.setWeb(false);
			m.setMatchfile(f);
			listModel.addElement(m);
		}

		if (MainWindow.logined) {
			try {
				List<Match> matchList = QueryWeb.queryMatchsByUid(MainWindow.acc.getUid());

				for (Match m : matchList) {
					m.setWeb(true);
					listModel.addElement(m);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "�����쳣����ȡ�����б�ʧ��", "����", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}

package yanyv.mms.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONObject;

import yanyv.mms.manager.JSONManager;
import yanyv.mms.manager.SaveManager;
import yanyv.mms.vo.Match;

public class ConWindow extends JFrame {

	ArrayList<File> fileList;

	private static int windowWidth = 500;
	private static int windowHeight = 300;

	boolean applyed = false;
	
	String info = "";

	JFrame main = this;
	JPanel mainPane;

	JScrollPane listPane;

	JLabel title;
	JLabel fuhuo;
	JLabel finish;
	JLabel date;
	JLabel online;
	
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
		//title.setBackground(Color.BLUE);
		
		Font infoFont = new Font("����", Font.BOLD, 15);
		fuhuo = new JLabel("��������", JLabel.LEFT);
		fuhuo.setSize(180, 25);
		fuhuo.setLocation(285, 60);
		fuhuo.setFont(infoFont);
		fuhuo.setOpaque(true);
		//fuhuo.setBackground(Color.RED);
		
		finish = new JLabel("�ѽ�����", JLabel.LEFT);
		finish.setSize(180, 25);
		finish.setLocation(285, 95);
		finish.setFont(infoFont);
		finish.setOpaque(true);
		//finish.setBackground(Color.GREEN);
		
		date = new JLabel("��  �ڣ�", JLabel.LEFT);
		date.setSize(180, 25);
		date.setLocation(285, 130);
		date.setFont(infoFont);
		date.setOpaque(true);
		//date.setBackground(Color.PINK);
		
		online = new JLabel("�ƴ洢��", JLabel.LEFT);
		online.setSize(180, 25);
		online.setLocation(285, 165);
		online.setFont(infoFont);
		online.setOpaque(true);
		//online.setBackground(Color.GRAY);
		
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
				MatchWindow match = new MatchWindow();
				
				main.setVisible(false);
				match.setVisible(true);
				match.open(info);
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
		String name = list.getSelectedValue().toString();
		info = SaveManager.getInfo(name);
		JSONManager jm = new JSONManager(info);
		title.setText(jm.getName());
		if(jm.getFuhuo()) {
			fuhuo.setText("����������");
		} else {
			fuhuo.setText("����������");
		}
		if(jm.getFinish()) {
			finish.setText("�ѽ�������");
		} else {
			finish.setText("�ѽ�������");
		}
		date.setText("��  �ڣ�" + jm.getDate());
		if(jm.getOnline()) {
			online.setText("�ƴ洢��������");
		} else {
			online.setText("�ƴ洢��δ����");
		}
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
	}
	
}

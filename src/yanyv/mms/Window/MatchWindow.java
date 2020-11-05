package yanyv.mms.Window;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.json.JSONArray;

import yanyv.mms.manager.JSONManager;
import yanyv.mms.manager.SaveManager;
import yanyv.mms.view.Round;

public class MatchWindow extends JFrame {
	
	ArrayList<String> list;
	
	private String name = "";
	private boolean fuhuo = false;
	private int pressX = 0, pressY = 0;
	
	private static int windowWidth = 1080;
	private static int windowHeight = 720;
	
	private Font font = new Font("����",Font.BOLD,28);

	JFrame main = this;												// ������
	JMenuBar menuBar;												// �˵���
	JMenu fileMenu;
	JMenu editMenu;
	JMenu viewMenu;
	JMenu aboutMenu;
	JMenuItem newMenuItem;
	JMenuItem openMenuItem;
	JMenuItem settingMenuItem;
	JMenuItem exitMenuItem;
	JMenuItem copyMenuItem;
	JMenuItem pasteMenuItem;
	JPanel mainPane;
	JScrollPane js;
	
	Round r;
	
	public MatchWindow() {

		this.setTitle("������������ Ver " + MainWindow.ver);
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// ���ݱ���
			}
			
		});
		
		mainPane = new JPanel();
		mainPane.setLayout(null);
		
		js = new JScrollPane(mainPane);
		js.setSize(this.getSize());

		js.setVerticalScrollBarPolicy(   
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(   
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		this.setContentPane(js);

		menuBarInit();
		// ��Ӳ˵���
		this.setJMenuBar(menuBar);
		
	}
	
	private void menuBarInit() {
		menuBar = new JMenuBar();
		// ��ʼ��һ���˵�
		fileMenuInit();
		editMenuInit();
		viewMenuInit();
		aboutMenuInit();
		// һ���˵���ӵ��˵���
        menuBar.add(fileMenu);
        //menuBar.add(editMenu);
        //menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
	}
	
	private void fileMenuInit() {
		fileMenu = new JMenu("�ļ�");
		// ��ʼ���Ӳ˵�
		newMenuItemInit();
		openMenuItemInit();
		settingMenuItemInit();
		exitMenuItemInit();
		// �Ӳ˵���ӵ�һ���˵�
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
        fileMenu.addSeparator();														// ���һ���ָ���
		fileMenu.add(settingMenuItem);
        fileMenu.add(exitMenuItem);
	}
	
	private void editMenuInit() {
		editMenu = new JMenu("�༭");
		// ��ʼ���Ӳ˵�
		copyMenuItemInit();
		pasteMenuItemInit();
		// �Ӳ˵���ӵ�һ���˵�
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
	}
	
	private void viewMenuInit() {
		viewMenu = new JMenu("��ͼ");
	}
	
	private void aboutMenuInit() {
		aboutMenu = new JMenu("����");
		aboutMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("����  �����");
                JOptionPane.showMessageDialog(null, "<html><body>���ߣ�����ƽ��<br>��λ������������ѧ<body></html>", "������",JOptionPane.WARNING_MESSAGE);  
            }
        });
	}
	
	private void newMenuItemInit() {
		newMenuItem = new JMenuItem("�½�");
		// ���� "�½�" �Ӳ˵�������ļ�����
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�½�  �����");
                SettingWindow set = new SettingWindow(main);
                main.setVisible(false);
                set.setVisible(true);
            }
        });
	}

	private void openMenuItemInit() {
		openMenuItem = new JMenuItem("��");
        // ���� "��" �Ӳ˵�������ļ�����
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("��  �����");
                ConWindow con = new ConWindow(main);
                main.setVisible(false);
                con.setVisible(true);
            }
        });
	}
	
	private void settingMenuItemInit() {
		settingMenuItem = new JMenuItem("����");
        // ���� "����" �Ӳ˵�������ļ�����
        settingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("����  �����");
                JOptionPane.showMessageDialog(null, "�ù��ܿ�����", "����",JOptionPane.WARNING_MESSAGE);  
            }
        });
	}
	
	private void exitMenuItemInit() {
		exitMenuItem = new JMenuItem("���沢�˳�");
        // ���� "�˳�" �Ӳ˵�������ļ�����
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�˳�  �����");
                save();
                System.exit(0);
            }
        });
	}
	
	private void copyMenuItemInit() {
		copyMenuItem = new JMenuItem("����");
	}
	
	private void pasteMenuItemInit() {
		pasteMenuItem = new JMenuItem("ճ��");
	}
	

	public void setArray(ArrayList<String> list) {
		this.list = list;
		
		init();
	}

	private void init() {

        /*Collections.shuffle(list);
		
        Match m;
        
		for(int i = 0; i < list.size(); i += 2) {
			if(i == list.size() - 1) {
				m = new Match(new Person(list.get(i)));
			} else {
				m = new Match(new Person(list.get(i)), new Person(list.get(i + 1)));
			}
			m.setLocation(round * 150 + 20, top);
			m.addin(mainPane);
			top += 140;
		}*/
		
		r = new Round(0, list);
		r.setJs(js);
		mainPane.add(r);
		mainPane.setPreferredSize(new Dimension(420, list.size() * 80));
		js.getHorizontalScrollBar().setValue(js.getHorizontalScrollBar().getMaximum());
		
		addListener();
	}
	
	private void addListener() {
		
		js.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				pressX = e.getX() + js.getHorizontalScrollBar().getValue();
				pressY = e.getY() + js.getVerticalScrollBar().getValue();
				System.out.println("press");
				
				main.setCursor(Cursor.MOVE_CURSOR);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				main.setCursor(Cursor.DEFAULT_CURSOR);
			}
			
		});
		
		js.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = pressX - e.getX(), y = pressY - e.getY();
				js.getHorizontalScrollBar().setValue(x);
				js.getVerticalScrollBar().setValue(y);
			}
			
		});
	}
	
	public void setName(String name) {
		this.name = name;
		this.setTitle(name + " ������������ Ver " + MainWindow.ver);
	}
	
	public void setFuhuo(boolean fuhuo) {
		this.fuhuo = fuhuo;
		r.setFuhuo();
	}
	
	public boolean getFuhuo() {
		return fuhuo;
	}
	
	public void save() {
		JSONManager jm = new JSONManager();
		jm.setName(name);
		jm.setFuhuo(fuhuo);
		jm.setDate();
		
		jm.setOnline(false);
		
		Round i = r;
		for(; i.getNext() != null; i = i.getNext()) {
			jm.addRound(i.save());
		}
		jm.addRound(i.save());
		
		if(i.getM().size() == 1 && i.getM().get(0).isFinish()) {
			jm.setFinish(true);
		} else {
			jm.setFinish(false);
		}
		
		SaveManager.save(jm);
	}
	
	public void open(String info) {
		JSONManager jm = new JSONManager(info);
		this.setName(jm.getName());
		JSONArray rounds = jm.getRounds();
		mainPane.setPreferredSize(new Dimension(420, rounds.getJSONObject(0).getJSONArray("matchs").length() * 2 * 100));
		Round f = new Round();
		r = f;
		for(int i = 0; i < rounds.length(); i++) {
			System.out.println(rounds.get(i));
			f.setNext(new Round(i, rounds.getJSONObject(i)));
			f.getNext().setJs(js);
			mainPane.add(f.getNext());
			f.getNext().refresh();
			f = f.getNext();
		}
		r = r.getNext();
		js.getHorizontalScrollBar().setValue(js.getHorizontalScrollBar().getMaximum());
		this.setFuhuo(jm.getFuhuo());
		this.addListener();
		
	}
}

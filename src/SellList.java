
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SellList extends JFrame implements ActionListener {
	// �гο� ���� ����
	private JPanel big1 = new JPanel();
	private JPanel big2 = new JPanel();
	private JPanel big3 = new JPanel();

	private JPanel Text1 = new JPanel();
	private JPanel Text2 = new JPanel();
	private JPanel Text3 = new JPanel();
	private JPanel Status1 = new JPanel();
	private JPanel Status2 = new JPanel();
	private JPanel Status3 = new JPanel();
	private JPanel menuTemp = new JPanel();
	private JPanel todayTemp = new JPanel();

	// 3��° �г��� ��ư�� ���� ����
	private JButton Menu_pos = new JButton("POS");
	private JButton Menu_price = new JButton("����");
	private JButton Menu_sales = new JButton("�޴����Ǹ�");
	private JButton Menu_list = new JButton("�ǸŸ���Ʈ");

	// �󺧿� ���� ����
	private JLabel TxtList = new JLabel("���� �ǸŸ���Ʈ");
	private JLabel TxtSales = new JLabel("�޴��� �Ǹŷ�");
	private JLabel TxtToday = new JLabel("���� �� �Ǹŷ� : ");
	private JLabel Todaysell = new JLabel("");
	String header1[] = { "�̸�", "�µ�", "������", "��", "����" };
	String contents1[][];

	private ArrayList<String> sellList;
	private ArrayList<ArrayList<String>> sellListB;


	String header2[] = { "�̸�", "�Ǹ��ܼ�" };
	String contents2[][];

	private DefaultTableModel model = new DefaultTableModel(contents1, header1);
	private JTable t1 = new JTable(model);
	private JScrollPane sp1 = new JScrollPane(t1);


	private DefaultTableModel model2 = new DefaultTableModel(contents2, header2);
	private JTable t2 = new JTable(model2);
	private JScrollPane sp2 = new JScrollPane(t2);

	public SellList() {
		setSize(550, 500);
		setTitle("�ǸŸ���Ʈ �� �Ǹŷ�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container song = getContentPane();
		// ��ư�� �۾� ũ�� ����
		Menu_pos.setFont(new Font("", Font.BOLD, 16));
		Menu_price.setFont(new Font("", Font.BOLD, 16));
		Menu_sales.setFont(new Font("", Font.BOLD, 16));
		Menu_list.setFont(new Font("", Font.BOLD, 16));
		// ���� �۾� ũ�� ����
		TxtList.setFont(new Font("", Font.BOLD, 20));
		TxtSales.setFont(new Font("", Font.BOLD, 20));
		TxtToday.setFont(new Font("", Font.BOLD, 20));
		Todaysell.setFont(new Font("", Font.BOLD, 20));

		song.setLayout(new GridLayout(3, 1));

		// ù ��° �ǸŸ���Ʈ
		big1.setLayout(new BorderLayout());
		big1.add(TxtList, BorderLayout.NORTH);// ��
		big1.add(sp1, BorderLayout.CENTER);// ��ũ����
		sp1.setPreferredSize(new Dimension(200, 200));

		// �� ��° �׸� �Ǹ�
		big2.setLayout(new BorderLayout());
		big2.add(TxtSales, BorderLayout.NORTH);// ��
		big2.add(sp2, BorderLayout.CENTER);// ��ũ����
		sp2.setPreferredSize(new Dimension(300, 100));

		// �� ��° �Ǹűݾ� �� ��ư
		big3.setLayout(new BorderLayout());

		todayTemp.add(TxtToday);
		todayTemp.add(Todaysell);
		big3.add(todayTemp, BorderLayout.CENTER);

		
		menuTemp.add(Menu_price);
		Menu_price.addActionListener(this);
		Menu_price.setEnabled(false);
		menuTemp.add(Menu_sales);
		Menu_sales.addActionListener(this);
		menuTemp.add(Menu_list);
		Menu_list.addActionListener(this);
		big3.add(menuTemp, BorderLayout.SOUTH);

		song.add(big1);
		song.add(big2);
		song.add(big3);
		

		setVisible(true);
	}

	// '�� ����Ʈ'���� '�̸��� ��� ����Ʈ'�� �̸����� ������ ī��Ʈ
	public void nameCount() {

		for (int i = 0; i < DN.dN.size(); i++) {
			if (DN.dN.get(i).equals("�Ƹ޸�ī��")) {
				DC.americano++;
			} else if (DN.dN.get(i).equals("ī���")) {
				DC.cafeLatte++;
			} else if (DN.dN.get(i).equals("ī���ī")) {
				DC.cafeMoca++;
			} else if (DN.dN.get(i).equals("����Ǫġ��")) {
				DC.frappuccino++;
			} else if (DN.dN.get(i).equals("ī��Ḷ���߶�")) {
				DC.caramelMacchiato++;
			} else if (DN.dN.get(i).equals("����������")) {
				DC.espresso++;
			} else if (DN.dN.get(i).equals("ȭ��Ʈ��ī")) {
				DC.whiteMoca++;
			} else if (DN.dN.get(i).equals("�ٴҶ��")) {
				DC.vanillaLatte++;
			} else if (DN.dN.get(i).equals("�����̵�")) {
				DC.lemonade++;
			} else if (DN.dN.get(i).equals("���⽺����")) {
				DC.smoothie++;
			} else if (DN.dN.get(i).equals("���̽�Ƽ")) {
				DC.iceTea++;
			} else if (DN.dN.get(i).equals("���ھ�")) {
				DC.cocoa++;
			}
		}
	}

	// ArrayList�� ��������(Dndc)�� ������ �̸��� ������ ���� >> (�Ƹ޸�ī��/2)����
	public void strSet() {

		if (DC.americano > 0) {
			Dndc.arrDndc.add("�Ƹ޸�ī��/" + DC.americano);

		}
		if (DC.cafeLatte > 0) {
			Dndc.arrDndc.add("ī���/" + DC.cafeLatte);

		}
		if (DC.cafeMoca > 0) {
			Dndc.arrDndc.add("ī���ī/" + DC.cafeMoca);

		}
		if (DC.frappuccino > 0) {
			Dndc.arrDndc.add("����Ǫġ��/" + DC.frappuccino);

		}
		if (DC.caramelMacchiato > 0) {
			Dndc.arrDndc.add("ī��Ḷ���߶�/" + DC.caramelMacchiato);

		}
		if (DC.espresso > 0) {
			Dndc.arrDndc.add("����������/" + DC.espresso);

		}
		if (DC.whiteMoca > 0) {
			Dndc.arrDndc.add("ȭ��Ʈ��ī/" + DC.whiteMoca);

		}
		if (DC.vanillaLatte > 0) {
			Dndc.arrDndc.add("�ٴҶ��/" + DC.vanillaLatte);

		}
		if (DC.lemonade > 0) {
			Dndc.arrDndc.add("�����̵�/" + DC.lemonade);

		}
		if (DC.smoothie > 0) {
			Dndc.arrDndc.add("���⽺����/" + DC.smoothie);

		}
		if (DC.iceTea > 0) {
			Dndc.arrDndc.add("���̽�Ƽ/" + DC.iceTea);

		}
		if (DC.cocoa > 0) {
			Dndc.arrDndc.add("���ھ�/" + DC.cocoa);

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// �ǸŸ���Ʈ ��ư
		if (e.getSource() == Menu_list) {
			JFileChooser fs = new JFileChooser();
			fs.setDialogTitle("Open A File");
			fs.setFileFilter(new FileTypeFilter(".txt", "Data File"));
			int result = fs.showOpenDialog(null);

			model.setNumRows(0);
			model2.setNumRows(0);

			sellList = new ArrayList<String>();
			sellListB = new ArrayList<ArrayList<String>>();

			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					File fi = fs.getSelectedFile();
					BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
					String line = "";
					String s = "";
					try {
						while ((s = br.readLine()) != null) {
							line += s;
							sellList.add(line);
							sellListB.add(sellList);
							line = "";
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (int i = 0; i < sellList.size(); i++) {
						String[] rowTemp;
						rowTemp = sellList.get(i).split("/");
						model.addRow(rowTemp);
						// �޴��� �Ǹŷ����� �޴��̸��� �������� ���� ���������� ����
						DN.dN.add(rowTemp[0]);
					}


					// �ӽ÷� ���̺� �ֹ� ��� ��Ÿ���� �ϱ�
					Todaysell.setText(line);
					//
					if (br != null) {
						try {
							br.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			Menu_price.setEnabled(true);
		}
		// ���� ��ư
		if (e.getSource() == Menu_price) {
			for (int i = 0; i < sellList.size(); i++) {
				Object nameValue = t1.getValueAt(i, 4);
				int nameV = Integer.parseInt(nameValue.toString());
				ReSum.sum += nameV;
				String Receipt_sum = String.valueOf(ReSum.sum);

				Todaysell.setText(Receipt_sum + "�� �Դϴ�.");
			}
			ReSum.sum = 0;
		}
		// �޴��� �Ǹ� ��ư
		if (e.getSource() == Menu_sales) {


			// �̸��� ���� �� ��, �� ���Ằ ����(�������� DC)�� ����
			nameCount();
			// ArrayList�� ��������(Dndc)�� ������ �̸��� ������ ���� >> (�Ƹ޸�ī��/2)����
			strSet();

			for (int i = 0; i < Dndc.arrDndc.size(); i++) {
				String arrTemp[];

				arrTemp = Dndc.arrDndc.get(i).split("/");
				model2.addRow(arrTemp);
			}

			DN.dN.clear();
			Dndc.arrDndc.clear();
			DC.americano =0;
			DC.cafeLatte =0;
			DC.cafeMoca =0;
			DC.frappuccino =0;
			DC.caramelMacchiato=0;
			DC.espresso=0;
			DC.whiteMoca=0;
			DC.vanillaLatte=0;
			DC.lemonade=0;
			DC.smoothie=0;
			DC.iceTea=0;
			DC.cocoa = 0;
		}

	}

}

//DrinkName
class DN {
	public static ArrayList<String> dN = new ArrayList<String>();
}

class ReSum {
	static int sum = 0;
}

//DrinkCount
class DC {
	public static int americano, cafeLatte, cafeMoca, frappuccino, caramelMacchiato, espresso, whiteMoca, vanillaLatte,
			lemonade, smoothie, iceTea, cocoa = 0;
}

class Dndc {
	public static String dndc;
	public static ArrayList<String> arrDndc = new ArrayList<String>();
}
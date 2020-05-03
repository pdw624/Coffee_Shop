
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Material extends JFrame implements ActionListener {

	SellList sl;

	// ��Ȱ��ȭ,Ȱ��ȭ ��ư
	boolean materialRun = true;
	boolean orderRun = false;

	// �гο� ���� ���� (1,2,3��° ��ư�� ���� �г�)
	private JPanel Inven_Status1 = new JPanel();
	private JPanel Inven_Status2 = new JPanel();
	private JPanel Inven_Status3 = new JPanel();

	// �гο� ���� ����
	private JPanel Inven_name1 = new JPanel();
	private JPanel Inven_name2 = new JPanel();
	private JPanel Inven_name3 = new JPanel();

	// 2��° ��ư�� ���� ����
	private JButton Btn_Beans = new JButton("����");
	private JButton Btn_Milk = new JButton("����");
	private JButton Btn_Syrup = new JButton("�÷�");
	private JButton Btn_Lemon = new JButton("����");
	private JButton Btn_Caramel = new JButton("ī���");
	private JButton Btn_Straw = new JButton("����");
	private JButton Btn_Choco = new JButton("����");
	private JButton Btn_PeachPowder = new JButton("�����ư���");

	private JButton[] Btn_material = new JButton[8];

	// 3��° ��ư�� ���� ����
	private JButton Inven_Order = new JButton("�ֹ�");
	private JButton Inven_sell = new JButton("�Ǹ���Ȳ");
	private JButton Inven_Reset = new JButton("�ʱ�ȭ");

	// �󺧿� ���� ����
	private JLabel TxtInven = new JLabel("�����Ȳ");
	private JLabel TxtOrder = new JLabel("��ǰ�ֹ�");
	private JLabel TxtQuantity = new JLabel("�ֹ� ���� �Է� :");

	// �ؽ�Ʈ�ʵ忡 ���� ����
	private JTextField QuantityField = new JTextField("0");

	String header[] = { "����", "����", "�÷�", "����", "ī���", "����", "����", "�����ư���" };

	
	Object[][] contents = {
			{ Mat.BeanQ, Mat.MilkQ, Mat.SyrupQ, Mat.LemonQ, Mat.CaramelQ, Mat.StrawQ, Mat.ChocoQ, Mat.PeachQ } };
	private JTable table = new JTable(contents, header);
	private JScrollPane scrollpane = new JScrollPane(table);

	public Material() {
		setTitle("�����Ȳ �� �ֹ�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container jang = getContentPane();

		TxtInven.setFont(new Font("", Font.BOLD, 20));
		TxtOrder.setFont(new Font("", Font.BOLD, 20));
		Btn_Beans.setFont(new Font("", Font.BOLD, 16));
		Btn_Milk.setFont(new Font("", Font.BOLD, 16));
		Btn_Syrup.setFont(new Font("", Font.BOLD, 16));
		Btn_Lemon.setFont(new Font("", Font.BOLD, 16));
		Btn_Caramel.setFont(new Font("", Font.BOLD, 16));
		Btn_Straw.setFont(new Font("", Font.BOLD, 16));
		Btn_Choco.setFont(new Font("", Font.BOLD, 16));
		Btn_PeachPowder.setFont(new Font("", Font.BOLD, 16));
		Inven_Order.setFont(new Font("", Font.BOLD, 16));
		Inven_Order.setPreferredSize(new Dimension(100, 40));
		TxtQuantity.setFont(new Font("", Font.BOLD, 20));
		Inven_sell.setFont(new Font("", Font.BOLD, 16));
		Inven_sell.setPreferredSize(new Dimension(100, 40));
		Inven_Reset.setFont(new Font("", Font.BOLD, 16));
		Inven_Reset.setPreferredSize(new Dimension(100, 40));

		jang.setLayout(new GridLayout(6, 1));

		Inven_Status1.setLayout(new GridLayout(1, 1));

		Inven_Status2.setLayout(new GridLayout(2, 4, 5, 5));



		// ----gridLayout �� �������� ������ ��� ���ĵǾ� ���´�.

		Inven_name3.setLayout(new GridLayout(2, 2));

		// �迭�ȿ� ��ư �Է�
		Btn_material[0] = Btn_Beans;
		Btn_material[1] = Btn_Milk;
		Btn_material[2] = Btn_Syrup;
		Btn_material[3] = Btn_Lemon;
		Btn_material[4] = Btn_Caramel;
		Btn_material[5] = Btn_Straw;
		Btn_material[6] = Btn_Choco;
		Btn_material[7] = Btn_PeachPowder;

		Inven_Order.setEnabled(orderRun);

		// �г� �Է�
		jang.add(Inven_name1); // �����Ȳ ��
		jang.add(Inven_Status1); // �����Ȳ
		jang.add(Inven_name2); // ��ǰ �ֹ� ��
		jang.add(Inven_Status2); // ��ǰ �ֹ�
		jang.add(Inven_name3); // �ֹ������Է� ��
		jang.add(Inven_Status3); // �����Ȳ �޴���ư

		// �гο� ���� ���� �Է�
		Inven_name1.add(TxtInven);
		Inven_Status1.add(scrollpane);
		Inven_name2.add(TxtOrder);
		Inven_Status2.add(Btn_Beans);
		Btn_Beans.addActionListener(this);
		Inven_Status2.add(Btn_Milk);
		Btn_Milk.addActionListener(this);
		Inven_Status2.add(Btn_Syrup);
		Btn_Syrup.addActionListener(this);
		Inven_Status2.add(Btn_Lemon);
		Btn_Lemon.addActionListener(this);
		Inven_Status2.add(Btn_Caramel);
		Btn_Caramel.addActionListener(this);
		Inven_Status2.add(Btn_Straw);
		Btn_Straw.addActionListener(this);
		Inven_Status2.add(Btn_Choco);
		Btn_Choco.addActionListener(this);
		Inven_Status2.add(Btn_PeachPowder);
		Btn_PeachPowder.addActionListener(this);
		Inven_name3.add(TxtQuantity);
		Inven_name3.add(QuantityField);
		QuantityField.setEnabled(false);
		Inven_Status3.add(Inven_Order);
		Inven_Order.addActionListener(this);
		Inven_Status3.add(Inven_sell);
		Inven_sell.addActionListener(this);
		Inven_Status3.add(Inven_Reset);
		Inven_Reset.addActionListener(this);

		setSize(550, 500);
		setVisible(true);
	}



	// ������ư��Ȱ��/Ȱ�� �Լ�
	public void materialButtonHold() {
		materialRun = !materialRun;
		for (int i = 0; i < 8; i++) {
			Btn_material[i].setEnabled(materialRun);
		}
	}

	// �ֹ���ư��Ȱ��/Ȱ�� �Լ�
	public void OrderButtonHold() {
		orderRun = !orderRun;
		Inven_Order.setEnabled(orderRun);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int BeanQCount = 0, MilkQCount = 0, SyrupQCount = 0, LemonQCount = 0, CaramelQCount = 0, StrawQCount = 0,
				ChocoQCount = 0, PeachQCount = 0;

		if (e.getSource() == Inven_sell) {
			if (sl == null) {
				sl = new SellList();
			} else {
				sl.dispose();
				sl = new SellList();
			}
		}

		// Ŀ�� ���� �Է��ϸ� �ֹ� ���� �Է�
		if (e.getSource() == Btn_Beans) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ���θ� �߰��Ͻðڽ��ϱ�?");
			// ���� �̸� �����ϱ� ���� �ӽ� ����
			Temp.strTemp = Btn_Beans.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Milk) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ������ �߰��Ͻðڽ��ϱ�?");
			// ���� �̸� �����ϱ� ���� �ӽ� ����
			Temp.strTemp = Btn_Milk.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Syrup) {
			TxtQuantity.setText("�ֹ� ���� �Է� : �÷��� �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_Syrup.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Lemon) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ������ �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_Lemon.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Caramel) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ī����� �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_Caramel.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Straw) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ���⸦ �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_Straw.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Choco) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ���ڸ� �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_Choco.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_PeachPowder) {
			TxtQuantity.setText("�ֹ� ���� �Է� : ������ ���縦 �߰��Ͻðڽ��ϱ�?");
			Temp.strTemp = Btn_PeachPowder.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		}

		// �ֹ�
		if (e.getSource() == Inven_Order) {
			
			TxtQuantity.setText("�ֹ� ���� �Է� :");
			QuantityField.setEnabled(false);
			
			BeanQCount = Integer.parseInt(QuantityField.getText());
			MilkQCount = Integer.parseInt(QuantityField.getText());
			SyrupQCount = Integer.parseInt(QuantityField.getText());
			LemonQCount = Integer.parseInt(QuantityField.getText());
			CaramelQCount = Integer.parseInt(QuantityField.getText());
			StrawQCount = Integer.parseInt(QuantityField.getText());
			ChocoQCount = Integer.parseInt(QuantityField.getText());
			PeachQCount = Integer.parseInt(QuantityField.getText());

			if (Temp.strTemp.equals("����")) {
				Mat.BeanQ += BeanQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("����")) {
				Mat.MilkQ += MilkQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("�÷�")) {
				Mat.SyrupQ += SyrupQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("����")) {
				Mat.LemonQ += LemonQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("ī���")) {
				Mat.CaramelQ += CaramelQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("����")) {
				Mat.StrawQ += StrawQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("����")) {
				Mat.ChocoQ += ChocoQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("�����ư���")) {
				Mat.PeachQ += PeachQCount;
				Temp.strTemp = "";
			}

			table.setValueAt(Mat.BeanQ, 0, 0);
			table.setValueAt(Mat.MilkQ, 0, 1);
			table.setValueAt(Mat.SyrupQ, 0, 2);
			table.setValueAt(Mat.LemonQ, 0, 3);
			table.setValueAt(Mat.CaramelQ, 0, 4);
			table.setValueAt(Mat.StrawQ, 0, 5);
			table.setValueAt(Mat.ChocoQ, 0, 6);
			table.setValueAt(Mat.PeachQ, 0, 7);

			materialButtonHold();
			OrderButtonHold();
			QuantityField.setText("0");
		}

		// �ʱ�ȭ ��ư
		if (e.getSource() == Inven_Reset) {
			if (materialRun == false && orderRun == true) {
				TxtQuantity.setText("�ֹ� ���� �Է� :");
				materialButtonHold();
				OrderButtonHold();
				QuantityField.setEnabled(false);
				QuantityField.setText("0");
			}
			table.setValueAt(Mat.BeanQ, 0, 0);
			table.setValueAt(Mat.MilkQ, 0, 1);
			table.setValueAt(Mat.SyrupQ, 0, 2);
			table.setValueAt(Mat.LemonQ, 0, 3);
			table.setValueAt(Mat.CaramelQ, 0, 4);
			table.setValueAt(Mat.StrawQ, 0, 5);
			table.setValueAt(Mat.ChocoQ, 0, 6);
			table.setValueAt(Mat.PeachQ, 0, 7);
		}
	}
}

class Mat {
	public static int BeanQ = 10, MilkQ = 10, SyrupQ = 10, LemonQ = 10, CaramelQ = 10, StrawQ = 10, ChocoQ = 10,
			PeachQ = 10;
}

//��ư ��Ȱ��ȭ �ӽ� ��Ʈ�� ����
class Temp {
	public static String strTemp;
}
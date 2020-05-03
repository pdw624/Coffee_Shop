import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {
	
	SellList sl;
	Material m;

	boolean drinkRun = false;
	boolean h_iRun = false;
	boolean sizeRun = false;
	boolean shotRun = false;
	boolean allRun = true;

	int money = 0;
	int buffCount = 0;

	/* ----------------Ŀ�ٶ� �ΰ��� �г�(���, �ϴ�)------------------ */
	// ��� �޴� ��ư�� �ö� �г�
	private JPanel jp1 = new JPanel();
	// �ϴ� ���ֹ� �� ���� ��ư�� �ö� �г�
	private JPanel jp2 = new JPanel();

	/* ----------------��ܿ� �ʿ��� �޴���------------------ */
	private JButton americano = new JButton("�Ƹ޸�ī��");
	private JButton cafeLatte = new JButton("ī���");
	private JButton cafeMoca = new JButton("ī���ī");
	private JButton frappuccino = new JButton("����Ǫġ��");
	private JButton caramelMacchiato = new JButton("ī��Ḷ���߶�");
	private JButton espresso = new JButton("����������");
	private JButton whiteMoca = new JButton("ȭ��Ʈ��ī");
	private JButton vanillaLatte = new JButton("�ٴҶ��");
	private JButton lemonade = new JButton("�����̵�");
	private JButton smoothie = new JButton("���⽺����");
	private JButton iceTea = new JButton("���̽�Ƽ");
	private JButton cocoa = new JButton("���ھ�");

	private JButton[] drink = new JButton[12];

	/* ----------------�ϴܿ� �ʿ��� �޴���------------------ */

	// �ϴ��� ���ֹ� �гε�
	private JPanel HOTICE = new JPanel();
	private JPanel SIZE = new JPanel();
	private JPanel SHOT = new JPanel();
	private JPanel ORDER = new JPanel();

	// ���� �µ�
	private JLabel h_i = new JLabel("Hot/Ice");

	private JRadioButton hot = new JRadioButton("Hot");
	private JRadioButton ice = new JRadioButton("ice");

	private ButtonGroup h_iG = new ButtonGroup();

	// ���� ������
	JLabel size = new JLabel("Size");

	private JRadioButton small = new JRadioButton("Small");
	private JRadioButton tall = new JRadioButton("Tall");
	private JRadioButton large = new JRadioButton("Large");

	private ButtonGroup sizeG = new ButtonGroup();

	// �� �߰�
	private JLabel shot = new JLabel("Shot");

	private JRadioButton shotY = new JRadioButton("Yes");
	private JRadioButton shotN = new JRadioButton("No");

	private ButtonGroup shotG = new ButtonGroup();

	// �ֹ� ��� ���̺�
	private JLabel order = new JLabel("�ֹ� ��� : ");
	private JLabel realOrder = new JLabel();

	// ���� �� ��Ÿ ó�� ��ư
	private JButton soldList = new JButton("�ǸŸ��");
	private JButton material = new JButton("������");
	private JButton cancel = new JButton("�������");
	private JButton payment = new JButton("����");

	public Main() {
		setTitle("Coffee Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		cp.setBackground(Color.orange);
		cp.setLayout(new GridLayout(2, 1));

		jp1.setLayout(new GridLayout(3, 4, 5, 5));
		jp1.setBackground(Color.pink);
		jp2.setLayout(new GridLayout(6, 1, 5, 5));

		drink[0] = americano;
		drink[1] = cafeLatte;
		drink[2] = cafeMoca;
		drink[3] = frappuccino;
		drink[4] = caramelMacchiato;
		drink[5] = espresso;
		drink[6] = whiteMoca;
		drink[7] = vanillaLatte;
		drink[8] = lemonade;
		drink[9] = smoothie;
		drink[10] = iceTea;
		drink[11] = cocoa;

		cp.add(jp1);
		cp.add(jp2);

		// ���� ���
		jp1.add(americano);
		americano.addActionListener(this);
		jp1.add(cafeLatte);
		cafeLatte.addActionListener(this);
		jp1.add(cafeMoca);
		cafeMoca.addActionListener(this);
		jp1.add(frappuccino);
		frappuccino.addActionListener(this);
		jp1.add(caramelMacchiato);
		caramelMacchiato.addActionListener(this);
		jp1.add(espresso);
		espresso.addActionListener(this);
		jp1.add(whiteMoca);
		whiteMoca.addActionListener(this);
		jp1.add(vanillaLatte);
		vanillaLatte.addActionListener(this);
		jp1.add(lemonade);
		lemonade.addActionListener(this);
		jp1.add(smoothie);
		smoothie.addActionListener(this);
		jp1.add(iceTea);
		iceTea.addActionListener(this);
		jp1.add(cocoa);
		cocoa.addActionListener(this);

		// ���� �µ�

		HOTICE.add(h_i);

		h_iG.add(hot);
		h_iG.add(ice);

		HOTICE.add(hot);
		hot.addActionListener(this);

		HOTICE.add(ice);
		ice.addActionListener(this);

		h_iButtonHold();

		// ���� ������
		SIZE.add(size);

		sizeG.add(small);
		sizeG.add(tall);
		sizeG.add(large);

		SIZE.add(small);
		small.addActionListener(this);

		SIZE.add(tall);
		tall.addActionListener(this);

		SIZE.add(large);
		large.addActionListener(this);

		sizeButtonHold();

		// ���� �� �߰�
		SHOT.add(shot);

		shotG.add(shotY);
		shotG.add(shotN);

		SHOT.add(shotY);
		shotY.addActionListener(this);

		SHOT.add(shotN);
		shotN.addActionListener(this);

		shotButtonHold();

		// ���� ó�� �г�
		ORDER.add(soldList);
		soldList.addActionListener(this);
		ORDER.add(material);
		material.addActionListener(this);
		ORDER.add(cancel);
		cancel.addActionListener(this);
		cancel.setEnabled(false);
		ORDER.add(payment);
		payment.addActionListener(this);
		payment.setEnabled(false);

		// �ϴ� �гο� ���� 3���� ��� �߰�
		jp2.add(HOTICE);
		jp2.add(SIZE);
		jp2.add(SHOT);
		jp2.add(order);
		jp2.add(realOrder);
		jp2.add(ORDER);

		setSize(550, 500);
		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	// �����ư��Ȱ��
	public void drinkButtonHold() {
		for (int i = 0; i < 12; i++) {
			drink[i].setEnabled(drinkRun);
		}

		drinkRun = !drinkRun;
	}

	// �µ���ưȰ��,��Ȱ��
	public void h_iButtonHold() {
		hot.setEnabled(h_iRun);
		ice.setEnabled(h_iRun);
		h_iRun = !h_iRun;
	}

	// �������ưȰ��,��Ȱ��
	public void sizeButtonHold() {
		small.setEnabled(sizeRun);
		tall.setEnabled(sizeRun);
		large.setEnabled(sizeRun);
		sizeRun = !sizeRun;
	}

	// ����ưȰ��,��Ȱ��
	public void shotButtonHold() {

		shotY.setEnabled(shotRun);
		shotN.setEnabled(shotRun);
		shotRun = !shotRun;
	}

	// ����ưȰ��ȭ
	public void allButtonEnable() {

		for (int i = 0; i < 12; i++) {
			drink[i].setEnabled(allRun);
		}
		hot.setEnabled(allRun);
		ice.setEnabled(allRun);
		small.setEnabled(allRun);
		tall.setEnabled(allRun);
		large.setEnabled(allRun);
		shotY.setEnabled(allRun);
		shotN.setEnabled(allRun);

		allRun = !allRun;
	}

	public String setDrinkName(String drinkName) {
		Send.drinkName = realOrder.getText();
		return Send.drinkName;
	}

	public void nullDrinkName(String drinkName) {
		Send.drinkName = "";
	}

	public void matMsg() {
		if (Mat.BeanQ <= 5 || Mat.MilkQ <= 5 || Mat.SyrupQ <= 5 || Mat.LemonQ <= 5 || Mat.CaramelQ <= 5
				|| Mat.StrawQ <= 5 || Mat.ChocoQ <= 5 || Mat.PeachQ <= 5) {
			JOptionPane.showMessageDialog(null, "��ᰡ �� ���� �ʾҽ��ϴ�. ��Ḧ �ֹ����ּ���");
		}
	}

	public void matMinus() {
		String arrTemp[];
		setDrinkName(Send.drinkName);
		arrTemp = Send.drinkName.split("/");
		if (arrTemp[0].equals("�Ƹ޸�ī��")) {
			Mat.BeanQ--;
		} else if (arrTemp[0].equals("ī���")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
		} else if (arrTemp[0].equals("ī���ī")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
			Mat.ChocoQ--;
		} else if (arrTemp[0].equals("����Ǫġ��")) {
			Mat.MilkQ--;
			Mat.ChocoQ--;
		} else if (arrTemp[0].equals("ī��Ḷ���߶�")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.CaramelQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("����������")) {
			Mat.BeanQ--;
		} else if (arrTemp[0].equals("ȭ��Ʈ��ī")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("�ٴҶ��")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("�����̵�")) {
			Mat.LemonQ--;
		} else if (arrTemp[0].equals("���⽺����")) {
			Mat.MilkQ--;
			Mat.StrawQ--;
		} else if (arrTemp[0].equals("���̽�Ƽ")) {
			Mat.PeachQ--;
		} else if (arrTemp[0].equals("���ھ�")) {
			Mat.ChocoQ--;
			Mat.MilkQ--;
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// ���ἱ��
		if (e.getSource() == americano) {
			realOrder.setText("�Ƹ޸�ī��/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 2500;
		} else if (e.getSource() == cafeLatte) {
			realOrder.setText("ī���/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3800;

		} else if (e.getSource() == cafeMoca) {
			realOrder.setText("ī���ī/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3800;

		} else if (e.getSource() == frappuccino) {
			realOrder.setText("����Ǫġ��/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4200;

		} else if (e.getSource() == caramelMacchiato) {
			realOrder.setText("ī��Ḷ���߶�/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3900;

		} else if (e.getSource() == espresso) {
			realOrder.setText("����������/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4100;

		} else if (e.getSource() == whiteMoca) {
			realOrder.setText("ȭ��Ʈ��ī/");
			drinkButtonHold();
			h_iButtonHold();
			money += 3800;
			cancel.setEnabled(true);
		} else if (e.getSource() == vanillaLatte) {
			realOrder.setText("�ٴҶ��/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4100;

		} else if (e.getSource() == lemonade) {
			realOrder.setText("�����̵�/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		} else if (e.getSource() == smoothie) {
			realOrder.setText("���⽺����/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3700;

		} else if (e.getSource() == iceTea) {
			realOrder.setText("���̽�Ƽ/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		} else if (e.getSource() == cocoa) {
			realOrder.setText("���ھ�/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		}

		// �µ� ����
		if (e.getSource() == hot) {
			realOrder.setText(realOrder.getText() + "������ ����/");
			h_iButtonHold();
			sizeButtonHold();
		} else if (e.getSource() == ice) {
			realOrder.setText(realOrder.getText() + "������ ����/");
			h_iButtonHold();
			sizeButtonHold();
		}

		// ũ�� ����
		if (e.getSource() == small) {
			realOrder.setText(realOrder.getText() + "����/");
			sizeButtonHold();
			shotButtonHold();
		} else if (e.getSource() == tall) {
			realOrder.setText(realOrder.getText() + "��/");
			sizeButtonHold();
			shotButtonHold();
			money += 300;
		} else if (e.getSource() == large) {
			realOrder.setText(realOrder.getText() + "����/");
			sizeButtonHold();
			shotButtonHold();
			money += 500;
		}

		// ���߰�
		if (e.getSource() == shotY) {
			realOrder.setText(realOrder.getText() + "�� �߰�/");
			shotButtonHold();
			payment.setEnabled(true);
			money += 500;
			realOrder.setText(realOrder.getText() + money);
			

		} else if (e.getSource() == shotN) {
			realOrder.setText(realOrder.getText() + "�� �߰� ����/");
			shotButtonHold();
			payment.setEnabled(true);
			realOrder.setText(realOrder.getText() + money);
			

		}

		// �ֹ� �ʱ�ȭ
		if (e.getSource() == cancel) {
			realOrder.setText("");
			drinkButtonHold();

			h_iRun = false;
			sizeRun = false;
			shotRun = false;
			h_iButtonHold();
			sizeButtonHold();
			shotButtonHold();

			payment.setEnabled(false);
			cancel.setEnabled(false);

			money = 0;
		}

		// ����
		if (e.getSource() == payment) {

			// ��� ���� �� �˶�
			matMsg();
			// ��� �Ҹ�
			matMinus();
			
			JFileChooser fs = new JFileChooser();
			fs.setDialogTitle("�ֹ� ������");
			fs.setFileFilter(new FileTypeFilter(".txt", "Data File"));
			int result = fs.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String content = realOrder.getText();
				File fi = fs.getSelectedFile();

				try {
					if (buffCount == 0) {
						BufferedWriter bw = new BufferedWriter(new FileWriter(fi + ".txt", true));
						bw.write(content + "\r\n");
						bw.close();
						buffCount++;
					} else {
						BufferedWriter bw = new BufferedWriter(new FileWriter(fi, true));
						bw.write(content + "\r\n");
						bw.close();
					}


				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			drinkButtonHold();
			payment.setEnabled(false);
			realOrder.setText("");
			money = 0;
		}

		// �ǸŸ���Ʈ â
		if (e.getSource() == soldList) {
			if (sl == null) {
				sl = new SellList();
			} else {
				sl.dispose();
				sl = new SellList();
			}
		}

		// ������� â
		if (e.getSource() == material) {
			if (m == null) {
				m = new Material();
			} else {
				m.dispose();
				m = new Material();
			}
		}

	}

}

class Send {
	public static String drinkName = "";
}

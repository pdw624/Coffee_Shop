
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

	// 비활성화,활성화 버튼
	boolean materialRun = true;
	boolean orderRun = false;

	// 패널에 대한 정보 (1,2,3번째 버튼에 대한 패널)
	private JPanel Inven_Status1 = new JPanel();
	private JPanel Inven_Status2 = new JPanel();
	private JPanel Inven_Status3 = new JPanel();

	// 패널에 대한 정보
	private JPanel Inven_name1 = new JPanel();
	private JPanel Inven_name2 = new JPanel();
	private JPanel Inven_name3 = new JPanel();

	// 2번째 버튼에 대한 정보
	private JButton Btn_Beans = new JButton("원두");
	private JButton Btn_Milk = new JButton("우유");
	private JButton Btn_Syrup = new JButton("시럽");
	private JButton Btn_Lemon = new JButton("레몬");
	private JButton Btn_Caramel = new JButton("카라멜");
	private JButton Btn_Straw = new JButton("딸기");
	private JButton Btn_Choco = new JButton("초코");
	private JButton Btn_PeachPowder = new JButton("복숭아가루");

	private JButton[] Btn_material = new JButton[8];

	// 3번째 버튼에 대한 정보
	private JButton Inven_Order = new JButton("주문");
	private JButton Inven_sell = new JButton("판매현황");
	private JButton Inven_Reset = new JButton("초기화");

	// 라벨에 대한 정보
	private JLabel TxtInven = new JLabel("재고현황");
	private JLabel TxtOrder = new JLabel("물품주문");
	private JLabel TxtQuantity = new JLabel("주문 수량 입력 :");

	// 텍스트필드에 대한 정보
	private JTextField QuantityField = new JTextField("0");

	String header[] = { "원두", "우유", "시럽", "레몬", "카라멜", "딸기", "초코", "복숭아가루" };

	
	Object[][] contents = {
			{ Mat.BeanQ, Mat.MilkQ, Mat.SyrupQ, Mat.LemonQ, Mat.CaramelQ, Mat.StrawQ, Mat.ChocoQ, Mat.PeachQ } };
	private JTable table = new JTable(contents, header);
	private JScrollPane scrollpane = new JScrollPane(table);

	public Material() {
		setTitle("재고현황 및 주문");
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



		// ----gridLayout 을 설정하지 않으면 가운데 정렬되어 나온다.

		Inven_name3.setLayout(new GridLayout(2, 2));

		// 배열안에 버튼 입력
		Btn_material[0] = Btn_Beans;
		Btn_material[1] = Btn_Milk;
		Btn_material[2] = Btn_Syrup;
		Btn_material[3] = Btn_Lemon;
		Btn_material[4] = Btn_Caramel;
		Btn_material[5] = Btn_Straw;
		Btn_material[6] = Btn_Choco;
		Btn_material[7] = Btn_PeachPowder;

		Inven_Order.setEnabled(orderRun);

		// 패널 입력
		jang.add(Inven_name1); // 재고현황 라벨
		jang.add(Inven_Status1); // 재고현황
		jang.add(Inven_name2); // 물품 주문 라벨
		jang.add(Inven_Status2); // 물품 주문
		jang.add(Inven_name3); // 주문수량입력 라벨
		jang.add(Inven_Status3); // 재고현황 메뉴버튼

		// 패널에 대한 내용 입력
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



	// 원재료버튼비활성/활성 함수
	public void materialButtonHold() {
		materialRun = !materialRun;
		for (int i = 0; i < 8; i++) {
			Btn_material[i].setEnabled(materialRun);
		}
	}

	// 주문버튼비활성/활성 함수
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

		// 커피 종류 입력하면 주문 수량 입력
		if (e.getSource() == Btn_Beans) {
			TxtQuantity.setText("주문 수량 입력 : 원두를 추가하시겠습니까?");
			// 원료 이름 구분하기 위해 임시 저장
			Temp.strTemp = Btn_Beans.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Milk) {
			TxtQuantity.setText("주문 수량 입력 : 우유를 추가하시겠습니까?");
			// 원료 이름 구분하기 위해 임시 저장
			Temp.strTemp = Btn_Milk.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Syrup) {
			TxtQuantity.setText("주문 수량 입력 : 시럽을 추가하시겠습니까?");
			Temp.strTemp = Btn_Syrup.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Lemon) {
			TxtQuantity.setText("주문 수량 입력 : 레몬을 추가하시겠습니까?");
			Temp.strTemp = Btn_Lemon.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Caramel) {
			TxtQuantity.setText("주문 수량 입력 : 카라멜을 추가하시겠습니까?");
			Temp.strTemp = Btn_Caramel.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Straw) {
			TxtQuantity.setText("주문 수량 입력 : 딸기를 추가하시겠습니까?");
			Temp.strTemp = Btn_Straw.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_Choco) {
			TxtQuantity.setText("주문 수량 입력 : 초코를 추가하시겠습니까?");
			Temp.strTemp = Btn_Choco.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		} else if (e.getSource() == Btn_PeachPowder) {
			TxtQuantity.setText("주문 수량 입력 : 복숭아 가루를 추가하시겠습니까?");
			Temp.strTemp = Btn_PeachPowder.getText();
			materialButtonHold();
			OrderButtonHold();
			QuantityField.setEnabled(true);
		}

		// 주문
		if (e.getSource() == Inven_Order) {
			
			TxtQuantity.setText("주문 수량 입력 :");
			QuantityField.setEnabled(false);
			
			BeanQCount = Integer.parseInt(QuantityField.getText());
			MilkQCount = Integer.parseInt(QuantityField.getText());
			SyrupQCount = Integer.parseInt(QuantityField.getText());
			LemonQCount = Integer.parseInt(QuantityField.getText());
			CaramelQCount = Integer.parseInt(QuantityField.getText());
			StrawQCount = Integer.parseInt(QuantityField.getText());
			ChocoQCount = Integer.parseInt(QuantityField.getText());
			PeachQCount = Integer.parseInt(QuantityField.getText());

			if (Temp.strTemp.equals("원두")) {
				Mat.BeanQ += BeanQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("우유")) {
				Mat.MilkQ += MilkQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("시럽")) {
				Mat.SyrupQ += SyrupQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("레몬")) {
				Mat.LemonQ += LemonQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("카라멜")) {
				Mat.CaramelQ += CaramelQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("딸기")) {
				Mat.StrawQ += StrawQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("초코")) {
				Mat.ChocoQ += ChocoQCount;
				Temp.strTemp = "";
			} else if (Temp.strTemp.equals("복숭아가루")) {
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

		// 초기화 버튼
		if (e.getSource() == Inven_Reset) {
			if (materialRun == false && orderRun == true) {
				TxtQuantity.setText("주문 수량 입력 :");
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

//버튼 비활성화 임시 스트링 공간
class Temp {
	public static String strTemp;
}
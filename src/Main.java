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

	/* ----------------커다란 두가지 패널(상단, 하단)------------------ */
	// 상단 메뉴 버튼이 올라갈 패널
	private JPanel jp1 = new JPanel();
	// 하단 상세주문 및 결제 버튼이 올라갈 패널
	private JPanel jp2 = new JPanel();

	/* ----------------상단에 필요한 메뉴들------------------ */
	private JButton americano = new JButton("아메리카노");
	private JButton cafeLatte = new JButton("카페라떼");
	private JButton cafeMoca = new JButton("카페모카");
	private JButton frappuccino = new JButton("프라푸치노");
	private JButton caramelMacchiato = new JButton("카라멜마끼야또");
	private JButton espresso = new JButton("에스프레소");
	private JButton whiteMoca = new JButton("화이트모카");
	private JButton vanillaLatte = new JButton("바닐라라떼");
	private JButton lemonade = new JButton("레몬에이드");
	private JButton smoothie = new JButton("딸기스무디");
	private JButton iceTea = new JButton("아이스티");
	private JButton cocoa = new JButton("코코아");

	private JButton[] drink = new JButton[12];

	/* ----------------하단에 필요한 메뉴들------------------ */

	// 하단의 상세주문 패널들
	private JPanel HOTICE = new JPanel();
	private JPanel SIZE = new JPanel();
	private JPanel SHOT = new JPanel();
	private JPanel ORDER = new JPanel();

	// 음료 온도
	private JLabel h_i = new JLabel("Hot/Ice");

	private JRadioButton hot = new JRadioButton("Hot");
	private JRadioButton ice = new JRadioButton("ice");

	private ButtonGroup h_iG = new ButtonGroup();

	// 음료 사이즈
	JLabel size = new JLabel("Size");

	private JRadioButton small = new JRadioButton("Small");
	private JRadioButton tall = new JRadioButton("Tall");
	private JRadioButton large = new JRadioButton("Large");

	private ButtonGroup sizeG = new ButtonGroup();

	// 샷 추가
	private JLabel shot = new JLabel("Shot");

	private JRadioButton shotY = new JRadioButton("Yes");
	private JRadioButton shotN = new JRadioButton("No");

	private ButtonGroup shotG = new ButtonGroup();

	// 주문 결과 레이블
	private JLabel order = new JLabel("주문 결과 : ");
	private JLabel realOrder = new JLabel();

	// 결제 및 기타 처리 버튼
	private JButton soldList = new JButton("판매목록");
	private JButton material = new JButton("재료재고");
	private JButton cancel = new JButton("선택취소");
	private JButton payment = new JButton("결제");

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

		// 음료 목록
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

		// 음료 온도

		HOTICE.add(h_i);

		h_iG.add(hot);
		h_iG.add(ice);

		HOTICE.add(hot);
		hot.addActionListener(this);

		HOTICE.add(ice);
		ice.addActionListener(this);

		h_iButtonHold();

		// 음료 사이즈
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

		// 음료 샷 추가
		SHOT.add(shot);

		shotG.add(shotY);
		shotG.add(shotN);

		SHOT.add(shotY);
		shotY.addActionListener(this);

		SHOT.add(shotN);
		shotN.addActionListener(this);

		shotButtonHold();

		// 결제 처리 패널
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

		// 하단 패널에 위의 3가지 목록 추가
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

	// 음료버튼비활성
	public void drinkButtonHold() {
		for (int i = 0; i < 12; i++) {
			drink[i].setEnabled(drinkRun);
		}

		drinkRun = !drinkRun;
	}

	// 온도버튼활성,비활성
	public void h_iButtonHold() {
		hot.setEnabled(h_iRun);
		ice.setEnabled(h_iRun);
		h_iRun = !h_iRun;
	}

	// 사이즈버튼활성,비활성
	public void sizeButtonHold() {
		small.setEnabled(sizeRun);
		tall.setEnabled(sizeRun);
		large.setEnabled(sizeRun);
		sizeRun = !sizeRun;
	}

	// 샷버튼활성,비활성
	public void shotButtonHold() {

		shotY.setEnabled(shotRun);
		shotN.setEnabled(shotRun);
		shotRun = !shotRun;
	}

	// 모든버튼활성화
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
			JOptionPane.showMessageDialog(null, "재료가 얼마 남지 않았습니다. 재료를 주문해주세요");
		}
	}

	public void matMinus() {
		String arrTemp[];
		setDrinkName(Send.drinkName);
		arrTemp = Send.drinkName.split("/");
		if (arrTemp[0].equals("아메리카노")) {
			Mat.BeanQ--;
		} else if (arrTemp[0].equals("카페라떼")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
		} else if (arrTemp[0].equals("카페모카")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
			Mat.ChocoQ--;
		} else if (arrTemp[0].equals("프라푸치노")) {
			Mat.MilkQ--;
			Mat.ChocoQ--;
		} else if (arrTemp[0].equals("카라멜마끼야또")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.CaramelQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("에스프레소")) {
			Mat.BeanQ--;
		} else if (arrTemp[0].equals("화이트모카")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("바닐라라떼")) {
			Mat.BeanQ--;
			Mat.MilkQ--;
			Mat.SyrupQ--;
		} else if (arrTemp[0].equals("레몬에이드")) {
			Mat.LemonQ--;
		} else if (arrTemp[0].equals("딸기스무디")) {
			Mat.MilkQ--;
			Mat.StrawQ--;
		} else if (arrTemp[0].equals("아이스티")) {
			Mat.PeachQ--;
		} else if (arrTemp[0].equals("코코아")) {
			Mat.ChocoQ--;
			Mat.MilkQ--;
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 음료선택
		if (e.getSource() == americano) {
			realOrder.setText("아메리카노/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 2500;
		} else if (e.getSource() == cafeLatte) {
			realOrder.setText("카페라떼/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3800;

		} else if (e.getSource() == cafeMoca) {
			realOrder.setText("카페모카/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3800;

		} else if (e.getSource() == frappuccino) {
			realOrder.setText("프라푸치노/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4200;

		} else if (e.getSource() == caramelMacchiato) {
			realOrder.setText("카라멜마끼야또/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3900;

		} else if (e.getSource() == espresso) {
			realOrder.setText("에스프레소/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4100;

		} else if (e.getSource() == whiteMoca) {
			realOrder.setText("화이트모카/");
			drinkButtonHold();
			h_iButtonHold();
			money += 3800;
			cancel.setEnabled(true);
		} else if (e.getSource() == vanillaLatte) {
			realOrder.setText("바닐라라떼/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 4100;

		} else if (e.getSource() == lemonade) {
			realOrder.setText("레몬에이드/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		} else if (e.getSource() == smoothie) {
			realOrder.setText("딸기스무디/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3700;

		} else if (e.getSource() == iceTea) {
			realOrder.setText("아이스티/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		} else if (e.getSource() == cocoa) {
			realOrder.setText("코코아/");
			drinkButtonHold();
			h_iButtonHold();
			cancel.setEnabled(true);
			money += 3500;

		}

		// 온도 선택
		if (e.getSource() == hot) {
			realOrder.setText(realOrder.getText() + "따뜻한 음료/");
			h_iButtonHold();
			sizeButtonHold();
		} else if (e.getSource() == ice) {
			realOrder.setText(realOrder.getText() + "차가운 음료/");
			h_iButtonHold();
			sizeButtonHold();
		}

		// 크기 선택
		if (e.getSource() == small) {
			realOrder.setText(realOrder.getText() + "스몰/");
			sizeButtonHold();
			shotButtonHold();
		} else if (e.getSource() == tall) {
			realOrder.setText(realOrder.getText() + "톨/");
			sizeButtonHold();
			shotButtonHold();
			money += 300;
		} else if (e.getSource() == large) {
			realOrder.setText(realOrder.getText() + "라지/");
			sizeButtonHold();
			shotButtonHold();
			money += 500;
		}

		// 샷추가
		if (e.getSource() == shotY) {
			realOrder.setText(realOrder.getText() + "샷 추가/");
			shotButtonHold();
			payment.setEnabled(true);
			money += 500;
			realOrder.setText(realOrder.getText() + money);
			

		} else if (e.getSource() == shotN) {
			realOrder.setText(realOrder.getText() + "샷 추가 없음/");
			shotButtonHold();
			payment.setEnabled(true);
			realOrder.setText(realOrder.getText() + money);
			

		}

		// 주문 초기화
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

		// 결제
		if (e.getSource() == payment) {

			// 재료 소진 시 알람
			matMsg();
			// 재료 소모
			matMinus();
			
			JFileChooser fs = new JFileChooser();
			fs.setDialogTitle("주문 영수증");
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

			JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
			drinkButtonHold();
			payment.setEnabled(false);
			realOrder.setText("");
			money = 0;
		}

		// 판매리스트 창
		if (e.getSource() == soldList) {
			if (sl == null) {
				sl = new SellList();
			} else {
				sl.dispose();
				sl = new SellList();
			}
		}

		// 원료재고 창
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

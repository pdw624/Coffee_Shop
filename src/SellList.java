
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
	// 패널에 대한 정보
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

	// 3번째 패널의 버튼에 대한 정보
	private JButton Menu_pos = new JButton("POS");
	private JButton Menu_price = new JButton("매출");
	private JButton Menu_sales = new JButton("메뉴별판매");
	private JButton Menu_list = new JButton("판매리스트");

	// 라벨에 대한 정보
	private JLabel TxtList = new JLabel("오늘 판매리스트");
	private JLabel TxtSales = new JLabel("메뉴별 판매량");
	private JLabel TxtToday = new JLabel("오늘 총 판매량 : ");
	private JLabel Todaysell = new JLabel("");
	String header1[] = { "이름", "온도", "사이즈", "샷", "가격" };
	String contents1[][];

	private ArrayList<String> sellList;
	private ArrayList<ArrayList<String>> sellListB;


	String header2[] = { "이름", "판매잔수" };
	String contents2[][];

	private DefaultTableModel model = new DefaultTableModel(contents1, header1);
	private JTable t1 = new JTable(model);
	private JScrollPane sp1 = new JScrollPane(t1);


	private DefaultTableModel model2 = new DefaultTableModel(contents2, header2);
	private JTable t2 = new JTable(model2);
	private JScrollPane sp2 = new JScrollPane(t2);

	public SellList() {
		setSize(550, 500);
		setTitle("판매리스트 및 판매량");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container song = getContentPane();
		// 버튼의 글씨 크기 변경
		Menu_pos.setFont(new Font("", Font.BOLD, 16));
		Menu_price.setFont(new Font("", Font.BOLD, 16));
		Menu_sales.setFont(new Font("", Font.BOLD, 16));
		Menu_list.setFont(new Font("", Font.BOLD, 16));
		// 라벨의 글씨 크기 변경
		TxtList.setFont(new Font("", Font.BOLD, 20));
		TxtSales.setFont(new Font("", Font.BOLD, 20));
		TxtToday.setFont(new Font("", Font.BOLD, 20));
		Todaysell.setFont(new Font("", Font.BOLD, 20));

		song.setLayout(new GridLayout(3, 1));

		// 첫 번째 판매리스트
		big1.setLayout(new BorderLayout());
		big1.add(TxtList, BorderLayout.NORTH);// 라벨
		big1.add(sp1, BorderLayout.CENTER);// 스크롤팬
		sp1.setPreferredSize(new Dimension(200, 200));

		// 두 번째 항목별 판매
		big2.setLayout(new BorderLayout());
		big2.add(TxtSales, BorderLayout.NORTH);// 라벨
		big2.add(sp2, BorderLayout.CENTER);// 스크롤팬
		sp2.setPreferredSize(new Dimension(300, 100));

		// 세 번째 판매금액 및 버튼
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

	// '총 리스트'에서 '이름만 골라낸 리스트'를 이름별로 개수를 카운트
	public void nameCount() {

		for (int i = 0; i < DN.dN.size(); i++) {
			if (DN.dN.get(i).equals("아메리카노")) {
				DC.americano++;
			} else if (DN.dN.get(i).equals("카페라떼")) {
				DC.cafeLatte++;
			} else if (DN.dN.get(i).equals("카페모카")) {
				DC.cafeMoca++;
			} else if (DN.dN.get(i).equals("프라푸치노")) {
				DC.frappuccino++;
			} else if (DN.dN.get(i).equals("카라멜마끼야또")) {
				DC.caramelMacchiato++;
			} else if (DN.dN.get(i).equals("에스프레소")) {
				DC.espresso++;
			} else if (DN.dN.get(i).equals("화이트모카")) {
				DC.whiteMoca++;
			} else if (DN.dN.get(i).equals("바닐라라떼")) {
				DC.vanillaLatte++;
			} else if (DN.dN.get(i).equals("레몬에이드")) {
				DC.lemonade++;
			} else if (DN.dN.get(i).equals("딸기스무디")) {
				DC.smoothie++;
			} else if (DN.dN.get(i).equals("아이스티")) {
				DC.iceTea++;
			} else if (DN.dN.get(i).equals("코코아")) {
				DC.cocoa++;
			}
		}
	}

	// ArrayList형 전역변수(Dndc)에 음료의 이름과 개수를 저장 >> (아메리카노/2)형식
	public void strSet() {

		if (DC.americano > 0) {
			Dndc.arrDndc.add("아메리카노/" + DC.americano);

		}
		if (DC.cafeLatte > 0) {
			Dndc.arrDndc.add("카페라떼/" + DC.cafeLatte);

		}
		if (DC.cafeMoca > 0) {
			Dndc.arrDndc.add("카페모카/" + DC.cafeMoca);

		}
		if (DC.frappuccino > 0) {
			Dndc.arrDndc.add("프라푸치노/" + DC.frappuccino);

		}
		if (DC.caramelMacchiato > 0) {
			Dndc.arrDndc.add("카라멜마끼야또/" + DC.caramelMacchiato);

		}
		if (DC.espresso > 0) {
			Dndc.arrDndc.add("에스프레소/" + DC.espresso);

		}
		if (DC.whiteMoca > 0) {
			Dndc.arrDndc.add("화이트모카/" + DC.whiteMoca);

		}
		if (DC.vanillaLatte > 0) {
			Dndc.arrDndc.add("바닐라라떼/" + DC.vanillaLatte);

		}
		if (DC.lemonade > 0) {
			Dndc.arrDndc.add("레몬에이드/" + DC.lemonade);

		}
		if (DC.smoothie > 0) {
			Dndc.arrDndc.add("딸기스무디/" + DC.smoothie);

		}
		if (DC.iceTea > 0) {
			Dndc.arrDndc.add("아이스티/" + DC.iceTea);

		}
		if (DC.cocoa > 0) {
			Dndc.arrDndc.add("코코아/" + DC.cocoa);

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 판매리스트 버튼
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
						// 메뉴별 판매량에서 메뉴이름을 가져오기 위해 전역변수에 저장
						DN.dN.add(rowTemp[0]);
					}


					// 임시로 레이블에 주문 목록 나타나게 하기
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
		// 매출 버튼
		if (e.getSource() == Menu_price) {
			for (int i = 0; i < sellList.size(); i++) {
				Object nameValue = t1.getValueAt(i, 4);
				int nameV = Integer.parseInt(nameValue.toString());
				ReSum.sum += nameV;
				String Receipt_sum = String.valueOf(ReSum.sum);

				Todaysell.setText(Receipt_sum + "원 입니다.");
			}
			ReSum.sum = 0;
		}
		// 메뉴별 판매 버튼
		if (e.getSource() == Menu_sales) {


			// 이름별 개수 센 후, 각 음료별 개수(전역변수 DC)에 저장
			nameCount();
			// ArrayList형 전역변수(Dndc)에 음료의 이름과 개수를 저장 >> (아메리카노/2)형식
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
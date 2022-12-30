package bmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BMI_View extends JFrame{
	TextField height_TF = new TextField();
	TextField weight_TF = new TextField();
	JLabel label_result, label_icon;
	
	public BMI_View(){
		add(new PanelAvobe(), "North");
		add(new PanelBelow(),"Center");
		setLocation(500, 150);
		setTitle("BMI");
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	class PanelAvobe extends JPanel {
		
		public PanelAvobe(){
			setBackground(new Color(255, 204,255));
			setLayout(new BorderLayout()); //배치관리자 BorderLayout으로 하고~
			JLabel title_label = new JLabel("체질량지수(BMI)", JLabel.CENTER);
			Font font = new Font("맑은 고딕", Font.BOLD, 25);
			title_label.setFont(font);
			JLabel height_label = new JLabel("신장 : ");
			JLabel weight_label = new JLabel("체중 : ");
			JLabel cm_label = new JLabel("cm");
			JLabel kg_label = new JLabel("kg");
			JButton result_button = new JButton("결과확인");
			
			result_button.addActionListener(button_ActionListener);
			
			height_TF = new TextField(10);
			weight_TF = new TextField(10);
			
			add(title_label,"North");  //맨 위에(North)에 제목 라벨
			JPanel panel_center = new JPanel(new BorderLayout());
			
			JPanel[] panels = new JPanel[3];  //체중, 신장,버튼 패널
			for(int i = 0; i<panels.length; i++) {
				panels[i] = new JPanel();
				panels[i].setBackground(new Color(204, 229,255)); 
			}
			
			panel_center.add(panels[0],"North");   
			panel_center.add(panels[1], "Center");
			
			panels[0].add(weight_label);  //체중 :
			panels[0].add(weight_TF);  //체중 입력칸
			panels[0].add(kg_label);  //kg
			panels[1].add(height_label);  //신장 : 
			panels[1].add(height_TF);  //신장 입력칸
			panels[1].add(cm_label);  //cm
			
			panels[2].add(result_button);  //결과 확인 버튼
			
			add(panel_center,"Center");  //입력칸들을 센터에
			add(panels[2],"South");   //버튼을 아래쪽(South)에
		}
	}//PanelAvobe
	
	class PanelBelow extends JPanel{
		public PanelBelow(){
			setBackground(new Color(204, 204, 255));
			setLayout(new BorderLayout());
			label_result = new JLabel("<html>당신의 체중은 _kg, 신장은 _cm이므로<br>BMI지수는 _㎏/㎡이며, _체중입니다.</html>",JLabel.CENTER); //결과 라벨
			Font font = new Font("맑은 고딕",Font.BOLD,20);
			label_result.setFont(font);
			ImageIcon icon = new ImageIcon("Saved Pictures/bmi.png");
			label_icon = new JLabel(icon,JLabel.CENTER);
			add(label_result,"North");
			add(label_icon,"Center");
		}
	}//PanelBelow
	
	ActionListener button_ActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BMI_VO bvo = new BMI_VO();
			//입력받은 체중,신장 변수에 저장
			double weight = Double.parseDouble(weight_TF.getText());
			double height = Double.parseDouble(height_TF.getText());
			//체중,신장 bmi 설정해줌
			bvo.setCm(height);
			bvo.setKg(weight);
			bvo.setBmi();
			
			String bmi_result = "";  //비만 결과
			ImageIcon icon = null; //비만 결과에 따라 사진이 달라짐
			
			if(bvo.getBmi()<18.5) {
				bmi_result = "저";
				icon = new ImageIcon("Saved Pictures/해골.jpg");
			}else if(bvo.getBmi()<24.9) {
				bmi_result = "정상";
				icon = new ImageIcon("Saved Pictures/정상체중.jpg");
			}else if(bvo.getBmi()<29.9) {
				bmi_result = "과";
				icon = new ImageIcon("Saved Pictures/과체중.jpg");
			}else if(bvo.getBmi()<34.9) {
				bmi_result = "비만";
				icon = new ImageIcon("Saved Pictures/비만.jpg");
			}else {
				bmi_result = "고도비만";
				icon = new ImageIcon("Saved Pictures/고도 비만.jpg");
			}
			label_result.setText("<html>당신의 체중은 "+bvo.getKg()+"㎏, 키는 "+bvo.getCm()+"㎝이므로<br>bmi지수는 "+String.format("%.1f", bvo.getBmi())+"㎏/㎡이며, "+bmi_result+"체중입니다.</html>");
			label_icon.setIcon(icon);
		}
	};
}















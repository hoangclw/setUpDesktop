import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView extends JPanel implements ActionListener {

  public MainView() {
    initApp();
  }

  JPanel jPanel_principal;
  JPanel jPanel_term;
  JPanel jPanel_interest_rate;
  JPanel jPanel_year;
  JPanel jPanel_result;
  JLabel logo = new JLabel("Tính Lãi Suất Kép");
  JLabel jLabel_P;
  JLabel jLabel_t;
  DefaultComboBoxModel<String> termListModel = new DefaultComboBoxModel<>();
  JComboBox<String> termList = new JComboBox<>(termListModel);
  JLabel jLabel_r;
  JLabel jLabel_y;
  JButton jButton_result;

  JTextField jTextField_P;
  JTextField jTextField_t;
  JTextField jTextField_r;
  JTextField jTextField_y;
  JTextField jTextField_result;

  private void initApp() {
    logo.setFont(new Font("Arial", Font.BOLD, 20));
    jPanel_principal = new JPanel(); //1 JTextField chứa P0
    //kì hạn gửi, drop downlist 4 option
    jPanel_term = new JPanel();

    termListModel.addElement("Hàng Năm");
    termListModel.addElement("Hàng Quý");
    termListModel.addElement("Hàng Tháng");
    termListModel.addElement("Hàng Ngày");

    JPanel jPanel_interest_rate = new JPanel(); //r
    JPanel jPanel_year = new JPanel(); //2 JTextField chứa số tiền gửi và thời gian gửi
    JPanel jPanel_result = new JPanel();

    jLabel_P = new JLabel("Tiền ban đầu ");
    jLabel_t = new JLabel("Kỳ hạn gửi     ");
    jLabel_r = new JLabel("Lãi suất (%)   ");
    jLabel_y = new JLabel("Số năm           ");

    jButton_result = new JButton("Tính toán");
    jButton_result.setBackground(Color.GREEN);
    jButton_result.addActionListener(this);

    jTextField_P = new JTextField(15);
//    JTextField jTextField_t = new JTextField(10);
    jTextField_r = new JTextField(15);
    jTextField_y = new JTextField(15);
    jTextField_result = new JTextField(15);
    jTextField_result.setText("0");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    jPanel_principal.add(jLabel_P);
    jPanel_principal.add(jTextField_P);
    jPanel_interest_rate.add(jLabel_r);
    jPanel_interest_rate.add(jTextField_r);
    jPanel_year.add(jLabel_y);
    jPanel_year.add(jTextField_y);
    jPanel_term.add(jLabel_t);
//    jPanel_term.add(jTextField_t);
    jPanel_term.add(termList);
    jPanel_result.add(jButton_result);
    jPanel_result.add(jTextField_result);

    add(logo);
    add(jPanel_principal);
    add(jPanel_interest_rate);
    add(jPanel_year);
    add(jPanel_term);
    add(jPanel_result);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    Long p = Long.parseLong(jTextField_P.getText());
    Double r = Double.parseDouble(jTextField_r.getText());
    Integer y = Integer.parseInt(jTextField_y.getText());
    String t = (String) termList.getSelectedItem();
//    System.out.println("P = " + p + " r = " + r + " y = " + y + " t = " + t);
    int term;
    if (t.equals("Hàng Năm")) {
      term = 1;
      showResult((calculate(p,r,y,term)));
    } else if (t.equals("Hàng Quý")) {
      term = 4;
      showResult((calculate(p,r,y,term)));
    } else if (t.equals("Hàng Tháng")) {
      term = 12;
      showResult((calculate(p,r,y,term)));
    } else if (t.equals("Hàng Ngày")) {
      term = 365;
      showResult((calculate(p,r,y,term)));
    }
  }
  private Long calculate(Long principal, Double rate, Integer year, Integer term){
    return (long) (principal*Math.pow(1+(rate/(100*term)),(term*year)));
  }
  private void showResult(long result){
    jTextField_result.setText(formattingCurrency(result) + " VND");
  }
  private String formattingCurrency(long result){
    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    return numberFormat.format(result);
  }

}

import javax.swing.JFrame;

public class Main extends JFrame {
  public Main(){
    initApp();
  }

  private void initApp(){
    add(new MainView());
    setTitle("Compound Interest");
    setLocationRelativeTo(null);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
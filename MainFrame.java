import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame frame;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.display();
    }

    void display() {
        frame = new JFrame("Graph");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GraphCoord graphCoord = new GraphCoord();
        //regular
        graphCoord.addFunction(x -> x * x * x);
        // //regular 2
        graphCoord.addFunction(x -> x * x + 5);
        // //derratvive
        graphCoord.addFunction(x -> 5 * x * x * x * x + 15 * x * x);
        // //(3x^2 * 1x^2) + (1x^3 * 2x^1)


        

        frame.add(graphCoord);

        frame.setVisible(true);
    }
}

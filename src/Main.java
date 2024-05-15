import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextArea TextInput;

    public Main(
            boolean undecorated,
            int x,
            int y,
            int width,
            int height,
            String value
    ) {
        setTitle("Quote");
        setUndecorated(undecorated);
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 150));
        setSize(width, height);
        setLocationRelativeTo(null);
        if(x != -1 && y != -1) {
            setLocation(x, y);
        }
        setVisible(true);
        setAlwaysOnTop(true);

        TextInput.setText(value);
        TextInput.setEditable(!undecorated);
        TextInput.setFocusable(!undecorated);

        var keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if(keyCode == 27) {
                    dispose();

                    var location = getLocation();
                    var size = getSize();

                    new Main(
                            !undecorated,
                            location.x,
                            location.y,
                            size.width,
                            size.height,
                            TextInput.getText()
                    );
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        addKeyListener(keyListener);
        TextInput.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        new Main(
                false,
                -1,
                -1,
                250,
                150,
                ""
        );
    }
}

package JavaSwing_TicTacToe;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainFrame extends JFrame implements ActionListener {
    JButton exitButton = new JButton(), resetButton = new JButton();
    JButton[] gridButtons = new JButton[9];
    static int playerNum = 1;
    static String playerSymbol = "X";
    static int counter = 0;

    MainFrame(){
        // Create Frame
        setTitle("Tic Tac Toe"); // Set Title
        setSize(new Dimension(600, 600));
        setResizable(false); // Apakah Frame bisa di Resize
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close Frame
        setLayout(new BorderLayout(0, 0));
        setVisible(true);

        // Create Panel
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(10, 50));
        topPanel.setBackground(Color.white);
        topPanel.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(10, 50));
        leftPanel.setBackground(Color.white);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(10, 50));
        rightPanel.setBackground(Color.white);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(10, 10));
        bottomPanel.setBackground(Color.white);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setLayout(new GridLayout(3, 3, 10, 10));

        // Top Menu Box
        exitButton.setText("Exit");
        addToPanel(topPanel, exitButton, Color.red);

        resetButton.setText("Reset");
        addToPanel(topPanel, resetButton, Color.green);

        // 9 Grid Box
        for (int i = 0; i < 9; i++) {
            gridButtons[i] = new JButton();
            addToPanel(centerPanel, gridButtons[i], Color.white);
            gridButtons[i].setBorderPainted(true);
            gridButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            gridButtons[i].setFont(new Font("Arial", Font.BOLD, 70));
        }

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    void addToPanel(JPanel panel, JButton button, Color clr){
        button.setFocusable(false);
        button.setBackground(clr);
        button.setBorderPainted(false);
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color playerColor = Color.BLACK;
        Object source = e.getSource();
        Mechanic.checkWin();

        for (JButton b : gridButtons){
            if (source == b){
                if (playerNum == 1){
                    playerSymbol = "X";
                    playerColor = Color.red;
                    playerNum = 2;
                }
                else if (playerNum == 2){
                    playerSymbol = "O";
                    playerColor = Color.blue;
                    playerNum = 1;
                }

                b.setText(playerSymbol);
                b.setForeground(playerColor);
                Color finalPlayerColor = playerColor;
                b.setUI(new MetalButtonUI(){
                    protected Color getDisabledTextColor() {
                        return finalPlayerColor;
                    }
                });
                b.setEnabled(false);
                counter += 1;

                Mechanic.pinSymbol(Arrays.asList(gridButtons).indexOf(b) + playerSymbol);

            }
            if (Mechanic.checkWin()){
                String winnerText = Mechanic.playerWin + " Win";
                JOptionPane.showMessageDialog(null, winnerText, "Title", JOptionPane.INFORMATION_MESSAGE);
                reset();
                this.dispose();
                new MainFrame();
                break;
            }
        }
        if (counter == 9){
            String winnerText = "TIE";
            JOptionPane.showMessageDialog(null, winnerText, "Title", JOptionPane.INFORMATION_MESSAGE);
            reset();
            this.dispose();
            new MainFrame();
        }

        if (source == resetButton){
            for (JButton bu : gridButtons){
                bu.setText("");
                bu.setEnabled(true);
            }
            reset();
            this.dispose();
            new MainFrame();
        }
        else if (source == exitButton){
            JOptionPane.showMessageDialog(null, "Thanks for Playing", "Message", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }
    static void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Mechanic.table[i][j] = null;
            }
        }
        playerNum = 1;
        counter = 0;
    }
}

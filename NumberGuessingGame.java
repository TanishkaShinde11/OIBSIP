import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {

    private int randomNumber;
    private JTextField guessField;
    private JLabel resultLabel;
    private int attemptsLeft;

    public NumberGuessingGame() {
        // Set up the frame
        super(" Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        randomNumber = generateRandomNumber();
        guessField = new JTextField(10);
        resultLabel = new JLabel("Guess a number between 0 and 100");
        attemptsLeft = 5; 
      
        Font bigFont = new Font("Arial", Font.BOLD, 20);
        guessField.setFont(bigFont);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));

       
        setLayout(new BorderLayout());

     
        JPanel inputPanel = new JPanel();
        JPanel resultPanel = new JPanel();

       
        inputPanel.setLayout(new FlowLayout());
        resultPanel.setLayout(new FlowLayout());

      
        inputPanel.add(new JLabel("Your Guess: "));
        inputPanel.add(guessField);

        
        resultPanel.add(resultLabel);

  add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        
        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 101); // Random number between 0 and 100
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == randomNumber) {
                resultLabel.setText("<html><font color='green'>Congratulations! You guessed the correct number.</font></html>");
                setBackgroundColor(Color.GREEN);
                disableInput();
            } else {
                attemptsLeft--;
                if (attemptsLeft == 0) {
                    resultLabel.setText("<html><font color='red'>Sorry, you're out of attempts. The correct number was " + randomNumber + "</font></html>");
                    setBackgroundColor(Color.RED);
                    disableInput();
                } else {
                    displayHint(userGuess);
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("<html><font color='red'>Invalid input. Please enter a number.</font></html>");
        } finally {
            guessField.setText(""); 
        }
    }

    private void setBackgroundColor(Color color) {
        getContentPane().setBackground(color);
    }

    private void disableInput() {
        guessField.setEnabled(false);
    }

    private void displayHint(int userGuess) {
        if (userGuess < randomNumber) {
            resultLabel.setText("<html><font color='cyan'>Too low! Try again. Attempts left: " + attemptsLeft + "</font></html>");
            setBackgroundColor(Color.CYAN);
        } else {
            resultLabel.setText("<html><font color='orange'>Too high! Try again. Attempts left: " + attemptsLeft + "</font></html>");
            setBackgroundColor(Color.ORANGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}

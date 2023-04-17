import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JRadioButton staffRadioButton;
    private JRadioButton studentRadioButton;
    private JButton selectButton;

    public Main() {
        super("User Selection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Select user type:");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel radioPanel = new JPanel(new GridLayout(2, 1));
        staffRadioButton = new JRadioButton("Staff");
        studentRadioButton = new JRadioButton("Student");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(staffRadioButton);
        radioButtonGroup.add(studentRadioButton);
        radioPanel.add(staffRadioButton);
        radioPanel.add(studentRadioButton);
        add(radioPanel, BorderLayout.CENTER);

        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        add(selectButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (staffRadioButton.isSelected()) {
            LibraryGUI libraryGUI = new LibraryGUI();
        } else if (studentRadioButton.isSelected()) {
            StudentLoginGUI studentLoginGUI = new StudentLoginGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    public static void main(String[] args) {
        new Main();
    }

}

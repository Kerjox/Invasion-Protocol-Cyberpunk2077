import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;


public class Window {

	private JPanel page1;
	private JTextArea textPanel;
	private JTextArea textInput;
	private JTextArea textSolve;
	private JButton doSolveButton;
	private InvasionProtocol panel;
	private String solve;

	public Window() {

		this.solve = "";

		doSolveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textPanel.getText().equals("") && textInput.getText().equals("")) {

					solve = "ERROR: There is no way to do this";

				}else if (textPanel.getText().equals("")){

					solve = "ERROR: There is no panel to solve";

				}else if (textInput.getText().equals("")){

					solve = "ERROR: There is no input";

				}else {

					panel = new InvasionProtocol(textPanel.getText(), textInput.getText());
					solve = panel.getSolve();
				}

				textSolve.setText(solve);
			}
		});
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

		JFrame frame = new JFrame("Invasion Protocol Resolver");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("images/icon.png")));

		frame.setContentPane(new Window().page1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

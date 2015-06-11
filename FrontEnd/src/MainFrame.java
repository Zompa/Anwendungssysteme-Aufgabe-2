import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MainFrame extends JFrame {

	JPanel sidePanel;
	JPanel mainPanel;
	JPanel biddingPanel;
	
	JTextField bidField;

	public static final String[] sampleAuctions = { "Auktion 1",
			"Tolle Auktion", "'); DROP TABLE AUCTIONS", "Paul" };
	String title = "<html><h1>Auktion 1</h1></html>";
	String highestBid = "100";
	String highestBidder = "Paul";
	String description = "<html><p style=\"width:300px\">Das ist ein sehr langer und nicht besonders sinnvoller Text. Das ist ein sehr langer und nicht besonders sinnvoller Text. Das ist ein sehr langer und nicht besonders sinnvoller Text. Das ist ein sehr langer und nicht besonders sinnvoller Text. Das ist ein sehr langer und nicht besonders sinnvoller Text. </p></html>";

	public MainFrame() {
		setLayout(new BorderLayout());

		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		// sidePanel.setPreferredSize(new Dimension(100, 1080));
		getContentPane().add(sidePanel);
		for (String s : sampleAuctions) {
			sidePanel.add(new JButton(s));
		}

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel, BorderLayout.EAST);
		// mainPanel.setPreferredSize(new Dimension(980, 1080));

		//add Title
		JLabel title = new JLabel(this.title);
		mainPanel.add(title);

		// add image
		//TODO
		
		//add AuctionPanel
		biddingPanel = new JPanel();
		biddingPanel.setLayout(new GridLayout(4, 3));
		biddingPanel.add(new JLabel("Höchstgebot:"));
		biddingPanel.add(new JLabel("100"));
		biddingPanel.add(new JLabel(""));
		
		biddingPanel.add(new JLabel("Höchstbieter:"));
		biddingPanel.add(new JLabel("Paul"));
		biddingPanel.add(new JLabel(""));
		
		biddingPanel.add(new JLabel("Bieten:"));
		bidField = new JTextField();
		biddingPanel.add(bidField);
		biddingPanel.add(new JButton("bieten"));

		biddingPanel.add(new JLabel("Ende um 18:21:13"));
		biddingPanel.setPreferredSize(new Dimension(200, 100));
		
		mainPanel.add(biddingPanel);
		//add description
		JLabel description = new JLabel(this.description);
		mainPanel.add(description);
		
		setVisible(true);
		pack();

		addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}
			
			public void windowClosed(WindowEvent arg0) {
				
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}

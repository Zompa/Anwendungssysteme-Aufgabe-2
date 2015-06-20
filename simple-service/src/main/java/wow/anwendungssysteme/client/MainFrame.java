package wow.anwendungssysteme.client;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import wow.anwendungssysteme.auction.Auction;

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
		
		//Button alle Auktionen
		JButton btnAllAuctions = new JButton("alle Auktionen anzeigen");
		btnAllAuctions.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder allAuctionInfo = new StringBuilder();
				
				for (Auction act : ClientFunctions.getAllAuctions()){					
					allAuctionInfo.append(act.toString() + "\n");
				}
				JOptionPane.showMessageDialog(null,
					    allAuctionInfo.toString(),
					    "alle Auktionen",
					    JOptionPane.PLAIN_MESSAGE);				
			}
		});
		sidePanel.add(btnAllAuctions);
		
		JButton btnAllUsers = new JButton("alle User anzeigen");
		sidePanel.add(btnAllUsers);
		/*
		for (String s : sampleAuctions) {
			sidePanel.add(new JButton(s));
		}
		*/

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
		biddingPanel.setPreferredSize( new Dimension(800, 600));
		biddingPanel.setLayout(new GridLayout(5, 3));
		
		biddingPanel.add(new JLabel("Auktions ID:"));
		final JTextField auctionField = new JTextField();
		biddingPanel.add(auctionField);
		
		JButton btnDetails = new JButton("Auktionsdetails anzeigen");
		btnDetails.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(null,
						ClientFunctions.getAuctionDetailsById(Integer.parseInt(auctionField.getText())).toString(),
					    "Auktionsdetails",
					    JOptionPane.PLAIN_MESSAGE);
			}
		});
		biddingPanel.add(new JLabel());
		biddingPanel.add(btnDetails);
		JButton btnHighestBidder = new JButton("Höchstbietenden anzeigen");
		biddingPanel.add(btnHighestBidder);
		JButton btnDeleteAuction = new JButton("Auktion löschen");
		biddingPanel.add(btnDeleteAuction);
		
		mainPanel.add(biddingPanel);
		
		setVisible(true);
		pack();

	
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}

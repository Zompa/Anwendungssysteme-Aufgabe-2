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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.ws.rs.InternalServerErrorException;

import wow.anwendungssysteme.auction.Auction;
import wow.anwendungssysteme.auction.AuctionDetails;
import wow.anwendungssysteme.auction.AuctionListEntry;
import wow.anwendungssysteme.user.User;

public class MainFrame extends JFrame {

	JTabbedPane mainPanel;
	JPanel auctionGetPanel;

	JTextField auctionIdField;
	JTextField titleField;
	JTextField descriptionField;
	JTextField imgUrlField;
	JTextField creatorIdField;
	JTextField endtimeField;
	JTextField bidAuctionIdField;
	JTextField bidValueField;
	JTextField bidBidderField;

	final JTextField auctionField;

	public MainFrame() {
		setLayout(new BorderLayout());

		mainPanel = new JTabbedPane();
		mainPanel.setPreferredSize(new Dimension(800, 600));

		// add AuctionPanel
		auctionGetPanel = new JPanel();
		auctionGetPanel.setLayout(new GridLayout(9, 2));
		JButton btnAllAuctions = new JButton("alle Auktionen anzeigen");
		auctionGetPanel.add(btnAllAuctions);
		auctionGetPanel.add(new JLabel("Auktions ID:"));
		auctionField = new JTextField();
		auctionGetPanel.add(auctionField);
		JButton btnDetails = new JButton("Auktionsdetails anzeigen");
		auctionGetPanel.add(btnDetails);
		JButton btnHighestBidder = new JButton("Höchstbietenden anzeigen");
		auctionGetPanel.add(btnHighestBidder);
		JButton btnDeleteAuction = new JButton("Auktion löschen");
		auctionGetPanel.add(btnDeleteAuction);
		auctionGetPanel.add(new JLabel("Ausgabe: "));
		final JTextArea outputText = new JTextArea();
		auctionGetPanel.add(outputText);

		// Create Auction Panel
		JPanel auctionCreatePanel = new JPanel();
		auctionCreatePanel.setLayout(new GridLayout(9, 1));

		auctionCreatePanel.add(new JLabel("ID (int) "));
		auctionIdField = new JTextField();
		auctionCreatePanel.add(auctionIdField);

		auctionCreatePanel.add(new JLabel("Titel:"));
		titleField = new JTextField();
		auctionCreatePanel.add(titleField);

		auctionCreatePanel.add(new JLabel("Beschreibung:"));
		descriptionField = new JTextField();
		auctionCreatePanel.add(descriptionField);

		auctionCreatePanel.add(new JLabel("Bild URL:"));
		imgUrlField = new JTextField();
		auctionCreatePanel.add(imgUrlField);

		auctionCreatePanel.add(new JLabel("Ersteller (int)"));
		creatorIdField = new JTextField();
		auctionCreatePanel.add(creatorIdField);

		auctionCreatePanel.add(new JLabel("Endzeitpunkt (long)"));
		endtimeField = new JTextField();
		auctionCreatePanel.add(endtimeField);

		JButton btnCreateAuction = new JButton("Auktion erstellen");
		auctionCreatePanel.add(btnCreateAuction);
		
		JButton btnUpdateAuction = new JButton("Auktion ändern");
		auctionCreatePanel.add(btnUpdateAuction);

		// Create Bid Panel
		JPanel BidPanel = new JPanel();
		BidPanel.setLayout(new GridLayout(9, 2));

		BidPanel.add(new JLabel("Auktion (int)"));
		bidAuctionIdField = new JTextField();
		BidPanel.add(bidAuctionIdField);

		BidPanel.add(new JLabel("Bieter ID (int)"));
		bidBidderField = new JTextField();
		BidPanel.add(bidBidderField);

		BidPanel.add(new JLabel("Gebot (int)"));
		bidValueField = new JTextField();
		BidPanel.add(bidValueField);

		JButton btnBid = new JButton("Bieten");
		BidPanel.add(btnBid);

		// Create User Panel
		JPanel UserPanel = new JPanel();
		UserPanel.setLayout(new GridLayout(9, 2));
		
		JButton btnShowUser = new JButton("Alle User anzeigen");
		UserPanel.add(btnShowUser);

		UserPanel.add(new JLabel("Ausgabe"));
		final JTextArea userOutputText = new JTextArea();
		UserPanel.add(userOutputText);
		

		mainPanel.addTab("Auktionen anlegen", auctionCreatePanel);
		mainPanel.addTab("Auktionen abrufen", auctionGetPanel);
		mainPanel.addTab("Bieten", BidPanel);
		mainPanel.addTab("User", UserPanel);
		add(mainPanel);
		setVisible(true);
		pack();

		// Action Listeners
		btnDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					outputText.setText(ClientFunctions.getAuctionDetailsById(
							getAuctionId()).toString());
				} catch (InternalServerErrorException serverEx) {
					showServerError();
				}
			}
		});

		btnHighestBidder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					outputText.setText(ClientFunctions.getHighestBid(
							getAuctionId()).toString());
				} catch (InternalServerErrorException serverEx) {
					showServerError();
				}

			}
		});

		btnDeleteAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientFunctions.deleteAuction(getAuctionId());
			}
		});

		btnAllAuctions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder allAuctionInfo = new StringBuilder();

				for (AuctionListEntry act : ClientFunctions.getAllAuctions()) {
					allAuctionInfo.append(act.toString() + "\n");
				}
				outputText.setText(allAuctionInfo.toString());
			}
		});

		btnCreateAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Auction a = getAuctionData();
				ClientFunctions.createAuction(a);
			}
		});
		
		btnUpdateAuction.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int auctionId = 0;
				long endDate = 0;
				try {
					auctionId = Integer.parseInt(auctionIdField.getText());
					endDate = Long.parseLong(endtimeField.getText());
				} catch (NumberFormatException numEx) {
					JOptionPane
							.showMessageDialog(
									null,
									"Bitte geben Sie eine Ganzzahl ein (ID, Ersteller, Endzeitpunkt).",
									"Fehler", JOptionPane.ERROR_MESSAGE);
				}
				String title = titleField.getText();
				String description = descriptionField.getText();
				String imageURL = imgUrlField.getText();

				ClientFunctions.updateAuction(auctionId, title, description, imageURL, endDate);
			}
		});

		btnBid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int auctionId = 0;
				int bidderId = 0;
				int bidValue = 0;
				try {
					auctionId = Integer.parseInt(bidAuctionIdField.getText());
					bidderId = Integer.parseInt(bidBidderField.getText());
					bidValue = Integer.parseInt(bidValueField.getText());
				} catch (NumberFormatException numEx) {
					JOptionPane
							.showMessageDialog(
									null,
									"Bitte geben Sie Ganzzahl ein (Auktions ID, Bieter, Gebot).",
									"Fehler", JOptionPane.ERROR_MESSAGE);
				}
				ClientFunctions.bid(auctionId, bidderId, bidValue);
			}
		});
		
		btnShowUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				
				for (User us : ClientFunctions.getUsers()) {
					sb.append(us.toString() + "\n");
				}
				userOutputText.setText(sb.toString());
			}
		});

	}

	private Auction getAuctionData() {
		int auctionId = 0;
		int creatorId = 0;
		long endDate = 0;
		try {
			auctionId = Integer.parseInt(auctionIdField.getText());
			creatorId = Integer.parseInt(creatorIdField.getText());
			endDate = Long.parseLong(endtimeField.getText());
		} catch (NumberFormatException numEx) {
			JOptionPane
					.showMessageDialog(
							null,
							"Bitte geben Sie eine Ganzzahl ein (ID, Ersteller, Endzeitpunkt).",
							"Fehler", JOptionPane.ERROR_MESSAGE);
		}
		String title = titleField.getText();
		String description = descriptionField.getText();
		String imageURL = imgUrlField.getText();

		AuctionDetails details = new AuctionDetails(title, description,
				imageURL, endDate);
		Auction auction = new Auction(details, auctionId, creatorId);
		return auction;
	}

	private int getAuctionId() {
		int auctionId = 0;
		try {
			auctionId = Integer.parseInt(auctionField.getText());
		} catch (NumberFormatException numEx) {
			JOptionPane.showMessageDialog(null,
					"Bitte geben Sie eine gültige Auktions ID ein.", "Fehler",
					JOptionPane.ERROR_MESSAGE);
		}
		return auctionId;
	}

	private void showServerError() {
		JOptionPane.showMessageDialog(null,
				"Serverfehler bei der Verarbeitung. Datensatz nicht gefunden",
				"Fehler", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}

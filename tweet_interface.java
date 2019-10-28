package tweet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.TweetsResources;
import twitter4j.conf.ConfigurationBuilder;
public class tweet_interface extends JFrame {

	private JPanel contentPane;
	private JTextField handle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tweet_interface frame = new tweet_interface();
					frame.setVisible(true);

					twitter4j.Twitter twitter = config().getInstance();
					
					List<Status> status=twitter.getHomeTimeline();
					for(Status s:status)
					{
						System.out.println(s.getUser().getName()+"                         "+s.getText());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tweet_interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		handle = new JTextField();
		handle.setBounds(68, 64, 315, 27);
		contentPane.add(handle);
		handle.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Twitter handle:");
		lblNewLabel.setBounds(79, 49, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTweet = new JLabel("Tweet :");
		lblTweet.setBounds(78, 102, 46, 14);
		contentPane.add(lblTweet);
		JLabel lblTweetLayout = new JLabel("Tweet layout");
		lblTweetLayout.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTweetLayout.setHorizontalAlignment(SwingConstants.CENTER);
		lblTweetLayout.setBounds(117, 11, 173, 27);
		contentPane.add(lblTweetLayout);
		final JEditorPane textbox = new JEditorPane();
		textbox.setBounds(68, 116, 315, 65);
		contentPane.add(textbox);
		JButton btnSentTweet = new JButton("Sent Tweet");
		btnSentTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textbox.getText().isEmpty()){
					 JOptionPane.showMessageDialog(null, "Comment atleast once");
				}else{
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
					    Statement stmt = con.createStatement();
					    String sql = "insert into ADH401TDS (TweetBody) Values('"+textbox.getText()+"')";
						   int insert = stmt.executeUpdate(sql);
						   sendDirectMessage(handle.getText(),textbox.getText());
						   
						   
					   JOptionPane.showMessageDialog(null, insert + " Tweet sended");
						
					}catch(Exception e){
						System.out.print(e);
					} 
				}
				
				
				
			}
		});
		btnSentTweet.setBounds(99, 209, 211, 41);
		contentPane.add(btnSentTweet);
		
		
		
		
	}

	
	public String createTweet(String tweet) throws TwitterException {
	    Twitter twitter = config().getInstance();
	    Status status = twitter.updateStatus(tweet);
	    return status.getText();
	}

public static TwitterFactory config()
	
	{
	ConfigurationBuilder configurationBuilder=new ConfigurationBuilder();
	configurationBuilder.setDebugEnabled(true)
	.setOAuthConsumerKey("EMR29XEB0seQd5uER7FeKKDcD")
	.setOAuthConsumerSecret("Zzh5fout3lqGvORsrG4GMPJRPc3GHNJgnLXpClB0J0dSL4B8YH")
	.setOAuthAccessToken("1181925398084554753-lkTq96kHbLgiafWSdGEfKUL3uQ4nUO")
	.setOAuthAccessTokenSecret("fcnxP7i0CSjMjclyWQY5GltZsRG3WYLtChD1EGn3S4Eiu");
		
		
		
		TwitterFactory tf=new TwitterFactory(configurationBuilder.build());
		twitter4j.Twitter twitter=tf.getInstance();
		return tf;
	}
public static String sendDirectMessage(String recipientName, String msg) 
		  throws TwitterException {
		  
		    Twitter twitter = config().getInstance();
		    DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
		    return message.getText();
		}

}

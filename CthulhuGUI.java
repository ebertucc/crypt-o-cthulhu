import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class CthulhuGUI extends JFrame implements ActionListener {

    /**
	 * Uh, this is the GUI for mah Cthulhu Cipher.
	 * Author: Ezra Bertuccelli
	 * 
	 * Where to go from here? Ideally, change the distribution of morphemes and add more of them
	 * to disrupt frequency analysis techniques. Also, add support for numbers and have more complex
	 * word/sentence patterns.
	 */
	private static final long serialVersionUID = -5629053793424333959L;
	private JTextArea inputArea;
    private JTextArea outputArea;
    private CthulhuCipher cipher = new CthulhuCipher();
    
	
	public static void main(String[] args) {
		new CthulhuGUI().setVisible(true);

	}
	
	
	//Much of this was ripped directly from the Caesar Cipher sample GUI
    public CthulhuGUI(){
        setTitle("Crypt o' Cthulhu");
	    setVisible(true);
	    setDefaultCloseOperation(3);
	
	    Container content = getContentPane();
	    GridLayout layout = new GridLayout(3, 0, 0, 10);
	    content.setLayout(layout);
	
	    inputArea = new JTextArea("Do you have a message for the Deep Ones? Or perhaps... one from them?", 12, 40);
	    inputArea.setLineWrap(true);
	    inputArea.setWrapStyleWord(true);
	    inputArea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
	    JScrollPane scroller = new JScrollPane(inputArea);
	    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    content.add(scroller);
	   
	    outputArea = new JTextArea("Behold!",12, 40);
	    outputArea.setLineWrap(true);
	    outputArea.setWrapStyleWord(true);
	    outputArea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
	    JScrollPane scroller2 = new JScrollPane(outputArea);
	    scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    content.add(scroller2);
	   
	    JPanel box1 = new JPanel();
	    box1.setLayout(new FlowLayout());
	    JButton decryptButton = new JButton("Decrypt");
	    JButton encryptButton = new JButton("Encrypt");
	    decryptButton.addActionListener(this);
	    encryptButton.addActionListener(this);
	    box1.add(decryptButton);
	    box1.add(encryptButton);
	    content.add(box1);
	   
	    pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("Encrypt")){
    		encryptText();
		}
		if (e.getActionCommand().equals("Decrypt")){
			decryptText();
		}
	}


	private void decryptText() {
		cipher.setInputText(cipher.makeDecryptable(inputArea.getText()));
		String output = cipher.decrypt(cipher.getInputText());
		
		outputArea.setText(output);
		
	}


	private void encryptText() {
		cipher.setInputText(cipher.makeEncryptable(inputArea.getText()));
		cipher.setCurrentPosition(0);
		String output = "";
		
		while(cipher.getInputText().length() > cipher.getCurrentPosition()){
			output+= cipher.genSentence();
		}
		
		outputArea.setText(output);
		
	}
	
}

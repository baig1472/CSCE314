package Data;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	public static String status = "Bored.";
	public static int primeCount = 0;
	public static int crossCount = 0;
	public static int largestPrimeDigits = 0;
	public static int largestCrossDigits1 = 0;
	public static int largestCrossDigits2 = 0;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	
	
	
	MainWindow(String name, Primes p)
	{
		
		popupGenerateHexagonCrossForLess(p);
	}
	public void changeColor(Component[] comp)
	  {
	    for(int x = 0; x < comp.length; x++)
	    {
	      if(comp[x] instanceof Container) 
	    	  changeColor(((Container)comp[x]).getComponents());
	      try
	      {
	        comp[x].setBackground(Color.ORANGE);
	      }
	      catch(Exception e){}
	    }
	  }

	protected void popupGeneratePrimes(Primes p)
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(80, 0, 0));
		
		//changeColor(dPrimes.getComponents());
		//dPrimes.setModal(true);
		
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setForeground(Color.WHITE);
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setForeground(Color.WHITE);
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		pnlGenerate.setBackground(new Color(80,0,0));
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		p.generatePrimes(start, count);
       		status = "Status: Excited. Primes have been generated.";
       		updateStats(p);
      		dPrimes.dispose();
      		popupGenerateHexagonCrossForLess(p);
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "You failed to type in an integer successfully. You are terrible at math. Shame.";
      		dPrimes.dispose();
      		popupGenerateHexagonCrossForLess(p);
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      	popupGenerateHexagonCrossForLess(p);
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		pnlButtons.setBackground(new Color(80,0,0));
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNotice.setForeground(Color.WHITE);
		pnlStatus.add(lblNotice, gbcPanel);
		pnlStatus.setBackground(new Color(80,0,0));
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}
	
	protected void popupGenerateHexagonCrossForLess(Primes p)
	{
		JDialog dHexagonCrossForLess = new JDialog(this, "Hexagon Cross For Less");
		
		GridBagLayout gridLayout = new GridBagLayout();
		dHexagonCrossForLess.getContentPane().setBackground(new Color(80, 0, 0));
		dHexagonCrossForLess.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,20);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("" + primeCount);
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCount.setForeground(Color.WHITE);
		
		JTextField tfCount = new JTextField("" + Config.def_PrimesFile);
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(40);
		
		
		// changing positions
		
		pnlGenerate.add(tfCount, gbcPanel);
		gbcPanel.gridx = 1;
		pnlGenerate.add(lblCount, gbcPanel);
		
		
		// Generate Primes File
		JLabel primesFile = new JLabel("Primes File");
		primesFile.setForeground(Color.WHITE);
		primesFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		primesFile.setLabelFor(tfCount);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(primesFile, gbcPanel);
		pnlGenerate.setBackground(new Color(80,0,0));
		
		// Generate Load  Button
		
		
		JButton btnLoadPrimes = new JButton("Load");
		btnLoadPrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		dHexagonCrossForLess.dispose();
      		FileAccess.loadPrimes(p,tfCount.getText());
      		updateStats(p);
      		popupGenerateHexagonCrossForLess(p);
      		
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "Failure.";
      	}
      	
      }
    });
		gbcPanel.gridx = 2;
		gbcPanel.gridy = 1;
		pnlGenerate.add(btnLoadPrimes, gbcPanel);
		
		
		// Generate Load  Button
		
		JButton btnSavePrimes = new JButton("Save");
		btnSavePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{	
      		dHexagonCrossForLess.dispose();
      		FileAccess.savePrimes(p,tfCount.getText());
      		updateStats(p);
      		popupGenerateHexagonCrossForLess(p);
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "Failure.";
      	}
      	
      }
    });
		gbcPanel.gridx = 3;
		gbcPanel.gridy = 1;
		pnlGenerate.add(btnSavePrimes, gbcPanel);
		
		dHexagonCrossForLess.add(pnlGenerate, gbcDialog);
		
		
		
		// HEXAGON  FILE PANEL
		
		JPanel pnlGenerate1 = new JPanel();
		pnlGenerate1.setLayout(new GridBagLayout());
		
		JLabel lblCount2 = new JLabel("" + crossCount);
		lblCount2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCount2.setForeground(Color.WHITE);
		
		
		
		JTextField tfCount2 = new JTextField("" + Config.def_CrossesFile);
		lblCount2.setLabelFor(tfCount2);
		tfCount2.setColumns(40);
		
		// changing positions
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlGenerate1.add(tfCount2, gbcPanel);
		gbcPanel.gridx = 1;
		pnlGenerate1.add(lblCount2, gbcPanel);
		
		
		// Generate Hexagon Files
		JLabel HexagonCrossFile = new JLabel("Hexagon Cross File");
		HexagonCrossFile.setForeground(Color.WHITE);	
		HexagonCrossFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		HexagonCrossFile.setLabelFor(tfCount2);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate1.add(HexagonCrossFile, gbcPanel);
		pnlGenerate1.setBackground(new Color(80,0,0));
		
		
		// Generate Load  Button
		
		
		JButton btnLoadHexagons = new JButton("Load");
		btnLoadHexagons.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		dHexagonCrossForLess.dispose();
      		FileAccess.loadCrosses(p,tfCount2.getText());
      		updateStats(p);
      		popupGenerateHexagonCrossForLess(p);
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "Failure.";
      	}
      	
      }
    });
		gbcPanel.gridx = 2;
		gbcPanel.gridy = 1;
		pnlGenerate1.add(btnLoadHexagons, gbcPanel);
		
		
		// Generate Load  Button
		
		JButton btnSaveHexagons = new JButton("Save");
		btnSaveHexagons.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		dHexagonCrossForLess.dispose();
      		FileAccess.saveCrosses(p,tfCount2.getText());
      		updateStats(p);
      		popupGenerateHexagonCrossForLess(p);
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "Failure.";
      	}
      	
      }
    });
		gbcPanel.gridx = 3;
		gbcPanel.gridy = 1;
		pnlGenerate1.add(btnSaveHexagons, gbcPanel);
		
		
		gbcDialog.gridy = 1;
		dHexagonCrossForLess.add(pnlGenerate1, gbcDialog);
		
	
	
		
		//-------- HEXAGON FILE PANEL  ENDS ------
		
		
		
		
		
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		dHexagonCrossForLess.dispose();
      		popupGeneratePrimes(p);
       		
      	}
      	catch(NumberFormatException ex)
      	{
      		status = "You failed to type in an integer successfully. You are terrible at math. Shame.";
      		dHexagonCrossForLess.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Generate Crosses");
		btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dHexagonCrossForLess.dispose();
			p.generateTwinPrimes();
			p.printTwins();
			if(p.twinPrimeCount() > 0)
			{
				
				p.generateHexPrimes();
				status = "Status: Excited. Crosses have been generated.";
				updateStats(p);
			}
			else
				status = "Size of Array Lists is not good.";
			
			popupGenerateHexagonCrossForLess(p);
    	  
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 2;
		pnlButtons.add(btnCancel, gbcPanel);
		pnlButtons.setBackground(new Color(80,0,0));
		
		// Generate message in between
		JLabel Message = new JLabel("<html>The largest prime has " + largestPrimeDigits + " digits. <br> The largest cross has " + largestCrossDigits1 + " and " + largestCrossDigits2 + " digits.</html>");
		Message.setFont(new Font("Tahoma", Font.BOLD, 12));
		Message.setForeground(Color.WHITE);
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 1;
		pnlButtons.add(Message, gbcPanel);
		
		
		gbcDialog.gridy = 2;
		dHexagonCrossForLess.add(pnlButtons, gbcDialog); 
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Status: " + status);
		lblNotice.setForeground(Color.WHITE);
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		pnlStatus.setBackground(new Color(80,0,0));
		
		gbcDialog.gridy = 3;
		dHexagonCrossForLess.add(pnlStatus, gbcDialog);
		
		dHexagonCrossForLess.setSize(1000,400);
		dHexagonCrossForLess.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dHexagonCrossForLess.setVisible(true);		
	}


	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats(Primes p)
	{
		primeCount = p.primeCount();
		crossCount = p.crossesCount();
		if(primeCount > 0)
		{
			largestPrimeDigits = p.sizeofLastPrime();
		}

		if(crossCount > 0)
		{
			Pair<Integer> digitP = p.sizeofLastCross();
	  		largestCrossDigits1 = digitP.left();
	  		largestCrossDigits2 = digitP.right();
		}
			
 	}

}
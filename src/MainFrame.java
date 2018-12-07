import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MainFrame extends JFrame {
	PointerInfo a = MouseInfo.getPointerInfo();
	private JLabel label1 = new JLabel("Score Counter: 0/5");
	JPanel panel=new JPanel();
	JProgressBar pb = new JProgressBar(0,100);//here added
	
public MainFrame(String title) {
	 super(title);
	 setVisible(true);
	 setSize(new Dimension(800, 600));
	 setResizable(false);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 MouseMotion mouse=new MouseMotion();
	 addMouseMotionListener(mouse);
	 panel.setLayout(null);
	 add(panel);
	 panel.add(pb);
	 label1.setBounds(0, 0,120,10);
	 panel.add(label1);
	 start1();
	 start2();

 }

private void start1() {
	  
	SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
	   @Override
	   protected Void doInBackground() throws Exception {
		   while(true) {		
	 			int x = MouseMotion.getPosition();
	 			publish(x);
	 			
		   }		   
	   
	   }
	   @Override
	   protected void process(List<Integer> list) {
		   		int last=list.get(list.size()-1);
		   		pb.setBounds(last, 500, 100, 70);
			    revalidate();
			    repaint();
			     		
		   	 
	   }
	   
	};
	   worker.execute();
}
public void start2() {
	Thread worker2 = new Thread() {
		public void run() {
			int counter=0;
			Random r = new Random();
			int x=r.nextInt(780);//
			int y=30;
			JProgressBar[] array=new JProgressBar[5];
			int[] helpArray=new int[5];
			for(int i=0;i<5;i++) {
				array[i]=new JProgressBar(0,100);
				helpArray[i]=r.nextInt(790)+3;
				array[i].setBounds(helpArray[i],y,10,10);
				panel.add(array[i]); //tu mo¿e byæ b³¹d
			}
			int k=0;
			for(int i=0;i<5;i++) {
				while(y<600 ) {
					try {
						Thread.sleep(70);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y=y+10;
					array[i].setBounds(helpArray[i], y, 10, 10);	
				
					if(MouseMotion.getPosition()<helpArray[i]&& (MouseMotion.getPosition()+100)>(helpArray[i]+10)&&y>500 && k==0) {
						k++;
						counter++;
						label1.setText("Score Counter: "+counter+"/5");
					}
				
				}
				k=0;
				y=30;
			}			
		}
	};
	worker2.start();
}
public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {  
		@Override
		public void run() {
			new MainFrame("SwingWorker Demo");
		}
	});
}

}
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MouseMotion extends JPanel implements MouseMotionListener {
	private static int xPosition=0;
	public static int getPosition() {
		return xPosition;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		xPosition=e.getX();
	}


    
}
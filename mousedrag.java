
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Constructor;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JFrame;


public class mousedrag extends MouseAdapter implements MouseMotionListener {

	private int startx;
	private int starty;
	private JComponent c = null;
	public JLabel frame1;
	public JLabel frame2;
	public JFrame Mainframe;
	//public JLabel frame1 = getframe1();
	//public JLabel frame2 = getframe2();
	//public JFrame Mainframe = getmainframe();

	public void mouseEntered(MouseEvent e) {
		c = (JComponent) e.getSource();
	}
	public void mousePressed(MouseEvent e) {

		//System.out.println("1: " + e.getX() + "  " + e.getY());
		startx = e.getX();
		starty = e.getY();
		/*if(c == frame1){
			Mainframe.getContentPane().setComponentZOrder(frame1,1);
			Mainframe.setComponentZOrder(frame2,0);
		}else{
			Mainframe.setComponentZOrder(frame1,0);
			Mainframe.setComponentZOrder(frame2,1);
		}*/
	}
	public void mouseReleased(MouseEvent e) {
		//System.out.println("2: " + e.getX() + "  " + e.getY());
		//setPosition(e);
	}

	public void mouseDragged(MouseEvent e) {
		//System.out.println("4: " + e.getX() + "  " + e.getY());
		setPosition(e);
	}

	private void setPosition(MouseEvent e) {
		int neux = c.getLocation().x + e.getX() - startx;
		neux = Math.max(neux, 0);
		neux = Math.min(neux, 600);
		int neuy = c.getLocation().y + e.getY() - starty;
		neuy = Math.max(neuy, 0);
		neuy = Math.min(neuy, 400);
		c.setLocation(neux, neuy);
	}

	public void mouseMoved(MouseEvent e) {
	}

}
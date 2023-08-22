import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;

class Ates {
	private int x;
	private int y;
	
	public Ates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}



	public class Oyun extends JPanel implements KeyListener,ActionListener {
	
		private int gecen_sure = 0;
		private int harcanan_ates = 0;
		
		private BufferedImage image;
		
		private Arraylist<Ates> atesler = new ArrayList<Ates>();
		
		private int atesdirY = 1;
		
		private int topX = 0;
			
		private int topdirX = 2;
		
		private int uzayGemisiX = 0;
		
		private int dirUzayX = 20;

		
		
		
		
		public Oyun() {
		
			try {
				image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
			} catch (IOException ex) {
				Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
			}

			
			setBackground(Color.BLACK);
			

		}

		
		
		
		@Override
		public void repaint() {
			// TODO Auto-generated method stub
			super.repaint();
		}



		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	
		@Override
		public void keyTyped(KeyEvent e) {
	
			
		}
	
		@Override
		public void keyPressed(KeyEvent e) {
			
			
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}				
	
	}

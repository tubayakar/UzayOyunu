import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

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
		Timer timer = new Timer(1,this);
		
		private int gecen_sure = 0;
		private int harcanan_ates = 0;
		
		private BufferedImage image;
		
		private ArrayList<Ates> atesler = new ArrayList<Ates>();
		
		private int atesdirY = 1;
		
		private int topX = 0;
			
		private int topdirX = 2;
		
		private int uzayGemisiX = 0;
		
		private int dirUzayX = 20;  //roket tıklayınca 20br sağa git veya sola gitmesi için

		
		
		public boolean kontrolEt() {
			
			for (Ates ates : atesler) {
				
				if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,30,30))) {   //intersects() metoduyla daireyi de rectangle olarak yazabiliriz. İki rectangle birbiriyle çarpmışmışsa true döndür. paint()metodunun içine şunu yaz: true dönerse timerı durdur.harcanan ateşi yazdır ekrana.
					return true; //bir tanesi bile çarpışsa true dönür
				}
			
			}
			return false; //bir tanesi bile çarpışmamışsa false dönür
			
			
		}
		
		
		
		public Oyun() {
		
			try {
				image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
			} catch (IOException ex) {
				Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
			}

			
			setBackground(Color.BLACK);
			
			timer.start();

		}
	

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			
			gecen_sure += 5;
			
			
			
			
			g.setColor(Color.green);     // topun rengi 
			
			g.fillOval(topX, 0, 30, 30); // topun konumu ve büyüklüğü	
			
			g.drawImage(image,uzayGemisiX,460,image.getWidth()/10,image.getHeight()/10,this); //roketin yerini, büyüklüğünü ayarladık
		
			
			
			
			
			
			for (Ates ates : atesler) {
				
				if (ates.getY() < 0) {   // ateşler y ekseninde yukarı doğru frame den çıkarsa ateşi ortadan kaldır
					
					atesler.remove(ates);  
				}
				
			}
			
			g.setColor(Color.blue);
			
			for (Ates ates : atesler) {
				
				g.fillRect(ates.getX(),ates.getY(),10,20);
				
			}
			
			
			
			
			
			if (kontrolEt()) {  // kontrolEt() metodu true dönerse timer'ı durdur.harcanan ateşi yazdır ekrana.
				timer.stop();
				String message = "Kazandınız...\n" +
								 "Harcanan Ateş : "+ harcanan_ates +
								 "\nGeçen Süre: " + gecen_sure /1000.0 + " saniye"; //saniyeye çevirmek için böelim
				
				JOptionPane.showMessageDialog(this, message);  //ekranda mesaj diyalog yayınlayalım
				System.exit(0);  // mesaj yayınlandıktan sonra oyun projem sonlanmış olsun diye yazdık
								
			}
			
			
			
			
		}

		
		@Override
		public void repaint() {
			super.repaint();
		}
	
		
		@Override
		public void keyTyped(KeyEvent e) { 
	
		}
	
		
		@Override
		public void keyPressed(KeyEvent e) { // roket sağa basınca sağa sola basınca sola gitsin diye
			
			
			int c = e.getKeyCode();
			
			if (c == KeyEvent.VK_LEFT) {
				if (uzayGemisiX <= 0) { //roket sola doğru frame den çıkmasın diye sınırlayalım, 0 noktasına gelince dursun
					
					uzayGemisiX = 0;
				}
				else {
					
					uzayGemisiX -= dirUzayX;
				}
				
			}
			else if (c == KeyEvent.VK_RIGHT) {
				
				if (uzayGemisiX >= 720) { //roket sağa doğru frame den çıkmasın diye sınırlayalım, 720 noktasına gelince dursun
					
					uzayGemisiX = 720;
					
				}
				else {
					
					uzayGemisiX += dirUzayX;
					
				}
				
			}
			else if (c == KeyEvent.VK_CONTROL) { //ctrl tuşuna basınca ateş edecek
				
				atesler.add(new Ates(uzayGemisiX+31,450));  //ateşin x ve y düzlemine göre nereden çıkacağını ayarladık
				
				harcanan_ates++; //ctrl ile ateş ettiğim herseferde harcanan ateş bir artsın diye
			}
			
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {  //top sağa sola sürekli hareket etsin diye
		
			
			for (Ates ates : atesler) { //her actionPerform metodu çalıştığında ateşin yukarı doğru y koordinatında yer değiştirmesi için
				
				ates.setY(ates.getY() - atesdirY);
				
			}
			
			
			
			
			topX += topdirX;
			
			if (topX >=750) {
				
				topdirX = -topdirX;
			}
			if (topX <= 0) {
				
				topdirX = -topdirX;
			}
			
			repaint();
			
		}
	
		
		
	}

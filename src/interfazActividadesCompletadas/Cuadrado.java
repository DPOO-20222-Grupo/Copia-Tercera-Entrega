package interfazActividadesCompletadas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cuadrado extends JPanel {

	private int actividadesCompletadas;
	private int actividadesMax;
	
	public Cuadrado (int actPropias, int actMaximas) {
		this.actividadesCompletadas = actPropias;
		if (actMaximas == 0) {
			this.actividadesMax = 1;
		}
		else {
			this.actividadesMax = actMaximas;
		}
		this.setBorder(new LineBorder(Color.black));
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        double intensidad = (double) actividadesCompletadas / actividadesMax;
        Color color = calcularColor(intensidad);

       
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Color calcularColor(double intensidad) {
	    int rojo = (int) ((1 - intensidad) * 255);   
	    int verde = 255;                           
	    int azul = (int) (intensidad * 255 + (1 - intensidad) * 255); 
	    return new Color(rojo, verde, azul);
	}
	
}

package interfazActividadesCompletadas;

import java.awt.Color;
import java.awt.Dimension;
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
		this.setPreferredSize(new Dimension(25, 25));
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        double intensidad = (double) actividadesCompletadas / actividadesMax;
        Color color = calcularColor(intensidad);

       
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public static Color calcularColor(double intensidad) {
		
		Color colorMaximo = new Color(0, 0, 255); // Genera un azul rey
		Color colorMinimo = new Color(255, 255, 255);
		
        int rojo = (int) (colorMinimo.getRed() + intensidad * (colorMaximo.getRed() - colorMinimo.getRed()));
        int verde = (int) (colorMinimo.getGreen() + intensidad * (colorMaximo.getGreen() - colorMinimo.getGreen()));
        int azul = (int) (colorMinimo.getBlue() + intensidad * (colorMaximo.getBlue() - colorMinimo.getBlue()));
		

	    return new Color(rojo, verde, azul);
	}
	
}

package interfazActividadesCompletadas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplicacion.Aplicacion;

@SuppressWarnings("serial")
public class PanelActividadesDiarias extends JPanel {

	private Aplicacion app;	
	private LocalDate fechaInicial;
	private JPanel panelTitulo;
	private JPanel panelMeses;
	private JPanel panelCuadros;
	private JPanel panelLeyenda;
	
	public PanelActividadesDiarias(Aplicacion aplicacion, LocalDate fecha ) {
		super();
		this.app = aplicacion;
		this.fechaInicial = fecha;
		this.panelMeses = new JPanel();
		panelMeses.setLayout(new GridLayout(1, 12, 5, 5));
		
		this.panelCuadros = new JPanel();
		panelCuadros.setLayout(new GridLayout(1,12, 5, 5));
		
		this.setLayout(new GridLayout(4,1, 5, 5));
		
		this.panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTitulo.add(new JLabel("Actividades completadas en el año por estudiantes"));
		
		
		this.panelLeyenda = new JPanel();
		agregarElementosPanelLeyenda(panelLeyenda);
		
		this.add(panelTitulo);
		this.add(panelMeses);
		this.add(panelCuadros);
		this.add(panelLeyenda);
		
		pintarCuadros();
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	}
	
	public void pintarCuadros() {
		
		Map<String, Integer> mapaMeses = app.getActividadesCompletadasAnio(fechaInicial.getYear());
		String mesMaximo = app.getMaximoMes(mapaMeses);
		System.out.println(mesMaximo);
		int maximoActividad = mapaMeses.get(mesMaximo);
		
		for(int i = 1; i<13; i++) {
			
			Month mes = Month.of(i);
			String llave = mes.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
			
			int actividadesMes = mapaMeses.get(llave);
			
			Cuadrado grafico = new Cuadrado(actividadesMes, maximoActividad);
			JLabel etiquetaMes = new JLabel(llave);
			etiquetaMes.setHorizontalAlignment(JLabel.CENTER);
			
			panelMeses.add(etiquetaMes);
			panelCuadros.add(grafico);
			
		}
		
		
		
		
	}
	
	public void agregarElementosPanelLeyenda(JPanel panelLeyenda) {
		
		panelLeyenda.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		panelLeyenda.add(new JLabel ("Menos"));
		
		double numCuadros = 5.0;
		
		for (int i = 0; i<numCuadros; i++) {
			
			
			double intensidad = i/numCuadros;
			JPanel cuadradoColor = new JPanel();
			
			cuadradoColor.setBackground(Cuadrado.calcularColor(intensidad));
			cuadradoColor.setPreferredSize(new Dimension(10,10));
			
			panelLeyenda.add(cuadradoColor);
			
			
		}
		
		panelLeyenda.add(new JLabel ("Más"));
		
	}
	
	
	
}



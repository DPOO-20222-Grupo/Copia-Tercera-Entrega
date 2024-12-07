package interfazActividadesCompletadas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.Aplicacion;

@SuppressWarnings("serial")
public class PanelActividadesDiarias extends JPanel {

	private Aplicacion app;
	private LocalDate fechaInicial;
	private JPanel panelMeses;
	private JPanel panelCuadros;
	
	public PanelActividadesDiarias(Aplicacion aplicacion, LocalDate fecha ) {
		super();
		this.app = aplicacion;
		this.fechaInicial = fecha;
		this.panelMeses = new JPanel();
		panelMeses.setLayout(new GridLayout(1, 12, 5, 5));
		
		this.panelCuadros = new JPanel();
		panelCuadros.setLayout(new GridLayout(1,12, 5, 5));
		
		this.setLayout(new BorderLayout(2,2));
		
		this.add(panelMeses, BorderLayout.NORTH);
		this.add(panelCuadros, BorderLayout.SOUTH);
		
		pintarCuadros();
		
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
			
			panelMeses.add(etiquetaMes);
			panelCuadros.add(grafico);
			
		}
		
		
		
	}
	
	
	
}



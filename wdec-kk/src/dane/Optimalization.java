package dane;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Optimalization {

	private IN in;
	private OUT out;
	private AMPL ampl;

	private Float ryzyko;

	public void setIn(IN in) {
		this.in = in;
	}

	public void setOut(OUT out) {
		this.out = out;
	}

	public void setRyzyko(Float ryzyko) {
		this.ryzyko = ryzyko;
	}

	public Optimalization() {
	

		//buidDataFile();

		ampl = new AMPL();
		
		/*		AMPL a = new AMPL();
		a.solve();
		a.display("cena");
		System.out.println(a.getResult());
		*/
	}

	public void buidDataFile() {
		File file = new File("myData.txt");
		PrintWriter zapis = null;
		try {
			file.createNewFile();
			zapis = new PrintWriter("myData.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		zapis.println("param r := " + ryzyko +";");
		zapis.println("param amortyzacja := " + in.getAmortyzacja() +";");
		zapis.println("param gotowka := " + in.getGotowka() +";");
		zapis.println("param max_kredyt := " + in.getMaxKredyt() +";");
		zapis.println("param max_produkcja := " + in.getMaxProdukcja()+";");
		zapis.println("param k_staly := " + in.getKosztStaly() +";");
		zapis.println("param zadluzenie := " + in.getZadluzenie() +";");

		zapis.close();
	}

	public void solve() {
		ampl.solve();
		// jakies zwrocenie wyniku gdzies?
		// znaczy wrzucenie go do outa??
	}
	
	public OUT getOUT() {
		return out;
	}
	
	public Float display(String s){
		return ampl.display(s);
	}
}

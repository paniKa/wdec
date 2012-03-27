package dane;

public class OUT {

	static int iloscOgraniczenR;
	static int iloscOgraniczenN;
	static int iloscZmiennych = 11;

	/* Zmienne decyzyjne - dane wyjsciowe */
	Float jakosc;
	Float wolumen;
	Float cena;
	Float kredyt;
	Float reklamaTv;
	Float reklamaNet;
	Float reklamaMagazyn;
	Float rata;

	Float zysk;
	Float ryzyko;

	public OUT() {
		setJakosc((float) 0);
		setWolumen((float) 0);
		setCena((float) 0);
		setKredyt((float) 0);
		setReklamaTv((float) 0);
		setReklamaNet((float) 0);
		setReklamaMagazyn((float) 0);
		setRata((float) 0);

		setZysk((float) 0);
		setRyzyko((float) 0);
	}

	public Float getCena() {
		return cena;
	}

	public static int getIloscOgraniczenN() {
		return iloscOgraniczenN;
	}

	public static int getIloscOgraniczenR() {
		return iloscOgraniczenR;
	}

	public static int getIloscZmiennych() {
		return iloscZmiennych;
	}

	public Float getJakosc() {
		return jakosc;
	}

	public Float getKredyt() {
		return kredyt;
	}

	public Float getRata() {
		return rata;
	}

	public Float getReklamaMagazyn() {
		return reklamaMagazyn;
	}

	public Float getReklamaNet() {
		return reklamaNet;
	}

	public Float getReklamaTv() {
		return reklamaTv;
	}

	public Float getRyzyko() {
		return ryzyko;
	}

	public Float getWolumen() {
		return wolumen;
	}

	public Float getZysk() {
		return zysk;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public static void setIloscOgraniczenN(int iloscOgraniczenN) {
		OUT.iloscOgraniczenN = iloscOgraniczenN;
	}

	public static void setIloscOgraniczenR(int iloscOgraniczenR) {
		OUT.iloscOgraniczenR = iloscOgraniczenR;
	}

	public static void setIloscZmiennych(int iloscZmiennych) {
		OUT.iloscZmiennych = iloscZmiennych;
	}

	public void setJakosc(Float jakosc) {
		this.jakosc = jakosc;
	}

	public void setKredyt(Float kredyt) {
		this.kredyt = kredyt;
	}

	public void setRata(Float rata) {
		this.rata = rata;
	}

	public void setReklamaMagazyn(Float reklamaMagazyn) {
		this.reklamaMagazyn = reklamaMagazyn;
	}

	public void setReklamaNet(Float reklamaNet) {
		this.reklamaNet = reklamaNet;
	}

	public void setReklamaTv(Float reklamaTv) {
		this.reklamaTv = reklamaTv;
	}

	public void setRyzyko(Float ryzyko) {
		this.ryzyko = ryzyko;
	}

	public void setWolumen(Float wolumen) {
		this.wolumen = wolumen;
	}

	public void setZysk(Float zysk) {
		this.zysk = zysk;
	}

}

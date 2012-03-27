package dane;

import java.util.ArrayList;
import java.util.HashMap;

public class IN {

	/* Pola przechowujace wartosci danych wejsciowych */

	/* Majatek trwaly firmy */
	Float majatekTrwaly;
	/* Maksymalny kredyt */
	Float maxKredyt;
	/* Ilosc gotowki */
	Float gotowka;
	/* Zadluzenie firmy */
	Float zadluzenie;
	/* Amortyzacja */
	Float amortyzacja;
	/* Koszty stale produktu zwyklego */
	Float kosztStaly;
	/* Koszty stale produktu luksusowego */
	/* Oprocentowanie konta bankowego */
	Float procentKonta;
	/* Oprocentowanie kredytu */
	Float procentKredytu;
	/* Stawka podatku */
	Float podatek;
	/* Niesprzedane produkty skupowane za: (kosztu pordukcji) */
	Float skup;
	/* Maksymalna zdolnosc produkcyjna */
	Float maxProdukcja;

	/* Zbior pktow wyjsciowych */
	HashMap<ArrayList<Float>, ArrayList<Float>> mapaPkt;

	public IN() {
		mapaPkt = new HashMap<ArrayList<Float>, ArrayList<Float>>();
	}

	public IN(Float majatekTrwaly, Float maxKredyt, Float gotowka,
			Float zadluzenie, Float amortyzacja, Float kosztStaly,
			Float procentKonta, Float procentKredytu, Float podatek,
			Float maxProdukcja) {

		mapaPkt = new HashMap<ArrayList<Float>, ArrayList<Float>>();

		this.amortyzacja = amortyzacja;
		this.gotowka = gotowka;
		this.kosztStaly = kosztStaly;
		this.majatekTrwaly = majatekTrwaly;
		this.maxKredyt = maxKredyt;
		this.maxProdukcja = maxProdukcja;
		this.podatek = podatek;
		this.procentKonta = procentKonta;
		this.procentKredytu = procentKredytu;
		this.zadluzenie = zadluzenie;
	}

	/*
	 * Wynik = (przychody_ze_sprzedaży  +  przychody_z_odsprzedaży  – 
	 * koszty_produkcji
	 *  – koszty_reklamy – koszt_amortyzacji + odsetki_z_konta_bankowego
	 *  – spłacana_rata )  (1 - stopa_podatkowa)
	 */
	protected Float liczWynik(Float cena, Float wolumen, Float jakosc,
			Float tv, Float net, Float magazyn, Float kredyt) {
		return (przychodSprzedazy(cena, wolumen)
				- liczCalyKoszt(tv, net, magazyn, wolumen, jakosc) + odsetkiZKonta(
					kredyt, tv, net, magazyn, wolumen, jakosc)) * (1 - podatek);
	}

	private Float liczInnyZysk(Float cena, Float wolumen, Float jakosc,
			Float tv, Float net, Float magazyn, Float kredyt) {
		return (przychodSprzedazy(cena, wolumen)
				- liczCalyKoszt(tv, net, magazyn, wolumen, jakosc) + odsetkiZKonta(
					kredyt, tv, net, magazyn, wolumen, jakosc));
	}

	/* Ryzyko = (...) */
	protected Float liczRyzyko(Float cena, Float jakosc, Float wolumen,
			Float tv, Float net, Float magazyn) {
		return (float) ((wolumen / maxProdukcja) * 0.15 * (cena / jakosc) - (0.9
				* tv + 0.7 * magazyn + 0.4 * net) / 200000);
	}

	private Float liczInneRyzyko(Float cena, Float tv, Float net, Float magazyn) {
		return (float) ((0.015 * cena - (0.9 * tv + 0.7 * magazyn + 0.4 * net) / 200000));
		// + 0.001
		// * ((cena - 5) * (100 / (99 - 5)))
		// + 0.004
		// * (cena - (100 - 5) / 2) * (cena - (100 - 5) / 2));
	}

	private Float liczInneRyzyko2(Float cena, Float tv, Float net, Float magazyn) {
		// -0.01*((-x)-55/2)^2-((-x)-5)*(100/45)
		return  (float) (((-0.01 * ((-cena) - 55 / 2) * ((-cena) - 55 / 2) - ((-cena) - 5)
				* (100 / 45)) / 50 - (0.8 * tv + 0.7 * magazyn + 0.5 * net) / 200000));
	}

	public void generujPkty() {

		mapaPkt.clear();
		
		Float jakosc = (float) 10;

		// while (jakosc <= 100) {

		Float wolumen = (float) 10000;

		// while (wolumen <= maxProdukcja) {

		Float cena = kosztZmienny(jakosc, wolumen) + 1;

		Float koszt = liczCalyKoszt((float) 0, (float) 0, (float) 0, wolumen,
				jakosc);
		Float ryzyko = liczInneRyzyko2(cena, (float) 0, (float) 0, (float) 0);

		Float tv = (float) 0;
		while (tv <= 100000) {
			Float net = (float) 0;
			while (net <= 100000) {
				Float magazyn = (float) 0;
				while (magazyn <= 100000) {
					Float zysk = liczInnyZysk(cena, wolumen, jakosc, tv, net,
							magazyn, maxKredyt);

					while (cena <= 50) {
						// && koszt <= gotowka + 500000
						// && ryzyko <= 1 && zysk >= 0) {

						ryzyko = liczInneRyzyko2(cena, tv, net, magazyn);
						koszt = liczCalyKoszt(tv, net, magazyn, wolumen, jakosc);

						zysk = liczInnyZysk(cena, wolumen, jakosc, tv, net,
								magazyn, maxKredyt);
						//System.out.println("ryzyko, zysk, cena " + ryzyko + " "
						//		+ zysk + " " + cena);
						if (ryzyko <= 1 && zysk >= 0
								&& koszt <= gotowka + maxKredyt) {
					//		System.out.println(zysk);
							ArrayList<Float> key = new ArrayList<Float>();
							ArrayList<Float> value = new ArrayList<Float>();
							key.add(ryzyko);
							key.add(zysk);

							value.add(cena);
							value.add(wolumen);
							value.add(tv);
							value.add(net);
							value.add(magazyn);
							value.add(jakosc);

							mapaPkt.put(key, value);
						}
						cena++;

					}
					magazyn += 1000;
				}
				net += 1000;
			}
			tv += 1000;
			/*
			 * } wolumen += 1000;
			 * 
			 * } jakosc++;
			 */
		}
		
	//	System.out.println("Wygenerowano pktow " + mapaPkt.size());
	}

	Float kosztzm;
	Float koszt;
	Float ryzyko;
	Float zysk;
	
	public Float getKosztZm() {
		return kosztzm;
	}

	public void setKosztZm(Float cena) {
		this.kosztzm = cena;
	}

	public Float getKoszt() {
		return koszt;
	}

	public void setKoszt(Float koszt) {
		this.koszt = koszt;
	}

	public Float getRyzyko() {
		return ryzyko;
	}

	public void setRyzyko(Float ryzyko) {
		this.ryzyko = ryzyko;
	}

	public Float getZysk() {
		return zysk;
	}

	public void setZysk(Float zysk) {
		this.zysk = zysk;
	}

	public void generujPkt(OUT wyjscie) {

		kosztzm = kosztZmienny(wyjscie.getJakosc(), wyjscie.getWolumen()) + 1;
		koszt = liczCalyKoszt(wyjscie.getReklamaTv(), wyjscie.getReklamaNet(), wyjscie.getReklamaMagazyn(), wyjscie.getWolumen(), wyjscie.getJakosc());
		ryzyko = liczInneRyzyko2(wyjscie.getCena(),wyjscie.getReklamaTv(), wyjscie.getReklamaNet(), wyjscie.getReklamaMagazyn());
		zysk = liczInnyZysk(wyjscie.getCena(), wyjscie.getWolumen(), wyjscie.getJakosc(), wyjscie.getReklamaTv(), wyjscie.getReklamaNet(), wyjscie.getReklamaMagazyn(), wyjscie.getKredyt());
	}
	
	private Float liczCalyKoszt(Float tv, Float net, Float magazyn,
			Float wolumen, Float jakosc) {
		return amortyzacja + kosztyReklamy(tv, net, magazyn) + kosztStaly
				+ kosztyZmienne(wolumen, jakosc) +zadluzenie;
	}

	/* Koszt_zmienny = (...) */
	/*
	 * Koszt jednostkowy zmienny jest to koszt wytworzenia produktu, zależy on
	 * nieliniowo od skali produkcji(wolumenu) oraz od jakości produktu.
	 */
	private Float kosztZmienny(Float jakosc, Float wolumen) {
		return (float) (0.15827136 * jakosc - 0.8322906 + 7.9067 * 0.000001
				* wolumen + 7 - 188042 * 7.9067 * 0.000001);
	}

	/*
	 * Koszty reklamy = koszt_reklamy_w_internecie + koszt_reklamy_w_tv +
	 * koszt_reklamy_w_magazynach
	 */
	private Float kosztyReklamy(Float tv, Float net, Float magazyn) {
		return tv + net + magazyn;
	}

	/*
	 * Koszty produkcji = koszty_stałe + koszty_zmienne
	 */
	private Float kosztProdukcji(Float wolumen, Float jakosc) {
		return kosztStaly + kosztyZmienne(wolumen, jakosc);
	}

	/* Przychód ze sprzedaży = cena_sprzedaży * wolumen_sprzedanych_produktów */
	private Float przychodSprzedazy(Float cena, Float sprzedWolumen) {
		return cena * sprzedWolumen;
	}

	/* odsetki = wolna_gotówka_z_bieżącego_etapu × oprocentowanie_konta */
	private Float odsetkiZKonta(Float kredyt, Float tv, Float net,
			Float magazyn, Float wolumen, Float jakosc) {
		return wolnaGotowkaZBiezacegoEtapu(kredyt, tv, net, magazyn, wolumen,
				jakosc) * procentKonta;
	}

	/*
	 * wolna_gotówka = gotówka + zaciągnięty_kredyt_w_danym_etapie –
	 * spłacana_rata_całości_kredytu – koszty_amortyzacji – koszty_reklamy –
	 * koszty_stałe – koszty_zmienne_produktu_normalnego
	 */
	private Float wolnaGotowkaZBiezacegoEtapu(Float kredyt, Float tv,
			Float net, Float magazyn, Float wolumen, Float jakosc) {
		// WTF gotowka??
		return gotowka + kredyt
				- liczCalyKoszt(tv, net, magazyn, wolumen, jakosc);
	}

	/*
	 * koszt_zmienny = wolumen * koszt_jednostkowy_zmienny
	 */
	private Float kosztyZmienne(Float wolumen, Float jakosc) {
		return (float) (wolumen * kosztZmienny(jakosc, wolumen));
	}

	// tu trzeba bedzie zwracac jakas tablice/ciag floatow
	public void generujPunkty() {
		// dla jakiegos przedzialu zmiennych (czyli w jakiejs fajnej petli)
		// licz wynik i ryzyko z podanych wzorow
		// liczWynik(cena, wolumen, jakosc, sprzedWolumen, tv, net, magazyn,
		// kredyt, rata);
		// liczRyzyko(cena, jakosc, wolumen, tv, net, magazyn);
	}

	/* Setters */
	public void setMajatekTrwaly(Float majatek) {
		this.majatekTrwaly = majatek;
	}

	public void setMaxKredyt(Float wartosc) {
		this.maxKredyt = wartosc;
	}

	public void setGotowka(Float wartosc) {
		this.gotowka = wartosc;
	}

	public void setZadluzenie(Float wartosc) {
		this.zadluzenie = wartosc;
	}

	public void setAmortyzacja(Float wartosc) {
		this.amortyzacja = wartosc;
	}

	public void setKosztStaly(Float wartosc) {
		this.kosztStaly = wartosc;
	}

	public void setProcentKonta(Float wartosc) {
		this.procentKonta = wartosc;
	}

	public void setProcentKredytu(Float wartosc) {
		this.procentKredytu = wartosc;
	}

	public void setPodatek(Float wartosc) {
		this.podatek = wartosc;
	}

	public void setSkup(Float wartosc) {
		this.skup = wartosc;
	}

	public void setMaxProdukcja(Float wartosc) {
		this.maxProdukcja = wartosc;
	}

	/* Getters */
	public Float getAmortyzacja() {
		return amortyzacja;
	}

	public Float getGotowka() {
		return gotowka;
	}

	public Float getKosztStaly() {
		return kosztStaly;
	}

	public Float getMajatekTrwaly() {
		return majatekTrwaly;
	}

	public Float getMaxKredyt() {
		return maxKredyt;
	}

	public Float getMaxProdukcja() {
		return maxProdukcja;
	}

	public Float getPodatek() {
		return podatek;
	}

	public Float getProcentKonta() {
		return procentKonta;
	}

	public Float getProcentKredytu() {
		return procentKredytu;
	}

	public Float getSkup() {
		return skup;
	}

	public Float getZadluzenie() {
		return zadluzenie;
	}

	public HashMap<ArrayList<Float>, ArrayList<Float>> getMapaPkt() {
		return mapaPkt;
	}
}

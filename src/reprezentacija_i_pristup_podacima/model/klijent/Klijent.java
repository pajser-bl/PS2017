package reprezentacija_i_pristup_podacima.model.klijent;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.vozila.Vozilo;

public class Klijent {
	private String broj_telefona;
	private String ime;
	private String prezime;
	private Vozilo vozilo;

	public Klijent(String broj_telefona, String ime, String prezime, Vozilo vozilo, Date datum) {
		super();
		this.broj_telefona = broj_telefona;
		this.ime = ime;
		this.prezime = prezime;
		this.vozilo = vozilo;
	}

	public String getBroj_telefona() {
		return broj_telefona;
	}

	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

}

package reprezentacija_i_pristup_podacima.model.korisnici;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.kredencijali.Kredencijali;

public class Korisnik {
	private String ime;
	private String prezime;
	private LocalDateTime datum_rodjenja;
	private int id_korisnika;
	private Kredencijali kredencijali;
	private String strucna_sprema;

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, LocalDateTime datum_rodjenja, int id_korisnika,
			Kredencijali kredencijali, String strucna_sprema) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datum_rodjenja = datum_rodjenja;
		this.id_korisnika = id_korisnika;
		this.kredencijali = kredencijali;
		this.strucna_sprema = strucna_sprema;
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

	public LocalDateTime getDatum_rodjenja() {
		return datum_rodjenja;
	}

	public void setDatum_rodjenja(LocalDateTime datum_rodjenja) {
		this.datum_rodjenja = datum_rodjenja;
	}

	public int getId_korisnika() {
		return id_korisnika;
	}

	public void setId_korisnika(int id_korisnika) {
		this.id_korisnika = id_korisnika;
	}

	public Kredencijali getKredencijali() {
		return kredencijali;
	}

	public void setKredencijali(Kredencijali kredencijali) {
		this.kredencijali = kredencijali;
	}

	public String getStrucna_sprema() {
		return strucna_sprema;
	}

	public void setStrucna_sprema(String strucna_sprema) {
		this.strucna_sprema = strucna_sprema;
	}

}

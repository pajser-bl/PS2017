package reprezentacija_i_pristup_podacima.model.intervencije;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.klijent.Klijent;
import reprezentacija_i_pristup_podacima.model.koordinate.Koordinate;
import reprezentacija_i_pristup_podacima.model.korisnici.Operater;

public class Intervencija {
	
	public boolean zatvorena;
	private Date vrijeme_otvaranja;
	private int id_intervencija;
	private Operater operater_otvorio;
	private Klijent klijent;
	private Koordinate koordinate_intervencije;
	private TerenskiIzvjestaj terenski_izvjestaj;
	
	
	private String napomena;
	private Date vrijeme_zatvaranja;
	private Operater operater_zatvorio;
	public Intervencija(Date vrijeme_otvaranja, int id_intervencija, Operater operater_otvorio, Klijent klijent,
			Koordinate koordinate_intervencije) {
		super();
		this.vrijeme_otvaranja = vrijeme_otvaranja;
		this.id_intervencija = id_intervencija;
		this.operater_otvorio = operater_otvorio;
		this.klijent = klijent;
		this.koordinate_intervencije = koordinate_intervencije;
		this.zatvorena=false;
	}
	public boolean isZatvorena() {
		return zatvorena;
	}
	public void setZatvorena(boolean zatvorena) {
		this.zatvorena = zatvorena;
	}
	public Date getVrijeme_otvaranja() {
		return vrijeme_otvaranja;
	}
	public void setVrijeme_otvaranja(Date vrijeme_otvaranja) {
		this.vrijeme_otvaranja = vrijeme_otvaranja;
	}
	public int getId_intervencija() {
		return id_intervencija;
	}
	public void setId_intervencija(int id_intervencija) {
		this.id_intervencija = id_intervencija;
	}
	public Operater getOperater_otvorio() {
		return operater_otvorio;
	}
	public void setOperater_otvorio(Operater operater_otvorio) {
		this.operater_otvorio = operater_otvorio;
	}
	public Klijent getKlijent() {
		return klijent;
	}
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	public Koordinate getKoordinate_intervencije() {
		return koordinate_intervencije;
	}
	public void setKoordinate_intervencije(Koordinate koordinate_intervencije) {
		this.koordinate_intervencije = koordinate_intervencije;
	}
	public TerenskiIzvjestaj getTerenski_izvjestaj() {
		return terenski_izvjestaj;
	}
	public void setTerenski_izvjestaj(TerenskiIzvjestaj terenski_izvjestaj) {
		this.terenski_izvjestaj = terenski_izvjestaj;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	public Date getVrijeme_zatvaranja() {
		return vrijeme_zatvaranja;
	}
	public void setVrijeme_zatvaranja(Date vrijeme_zatvaranja) {
		this.vrijeme_zatvaranja = vrijeme_zatvaranja;
	}
	public Operater getOperater_zatvorio() {
		return operater_zatvorio;
	}
	public void setOperater_zatvorio(Operater operater_zatvorio) {
		this.operater_zatvorio = operater_zatvorio;
	}
	
	
	
}

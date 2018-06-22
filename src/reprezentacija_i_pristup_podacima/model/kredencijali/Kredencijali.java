package reprezentacija_i_pristup_podacima.model.kredencijali;

public class Kredencijali {
	private String korisnicko_ime;
	private String lozinka;
	
	
	public Kredencijali(String korisnicko_ime, String lozinka) {
		super();
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
	}
	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}
	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
}

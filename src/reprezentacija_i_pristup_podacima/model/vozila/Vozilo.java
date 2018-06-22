package reprezentacija_i_pristup_podacima.model.vozila;

public class Vozilo {
	private String broj_registrarskih_tablica;
	private String marka_vozila;
	
	
	
	public Vozilo(String broj_registrarskih_tablica, String marka_vozila) {
		super();
		this.broj_registrarskih_tablica = broj_registrarskih_tablica;
		this.marka_vozila = marka_vozila;
	}
	
	public String getBroj_registrarskih_tablica() {
		return broj_registrarskih_tablica;
	}
	public void setBroj_registrarskih_tablica(String broj_registrarskih_tablica) {
		this.broj_registrarskih_tablica = broj_registrarskih_tablica;
	}
	public String getMarka_vozila() {
		return marka_vozila;
	}
	public void setMarka_vozila(String marka_vozila) {
		this.marka_vozila = marka_vozila;
	}
	
	
	
	
}

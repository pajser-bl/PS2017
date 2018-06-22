package reprezentacija_i_pristup_podacima.model.koordinate;

public class Koordinate {
	private String geografska_duzina;
	private String geografska_sirina;

	public Koordinate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Koordinate(String geografska_duzina, String geografska_sirina) {
		super();
		this.geografska_duzina = geografska_duzina;
		this.geografska_sirina = geografska_sirina;
	}

	public String getGeografska_sirina() {
		return geografska_sirina;
	}

	public void setGeografska_sirina(String geografska_sirina) {
		this.geografska_sirina = geografska_sirina;
	}

	public String getGeografska_duzina() {
		return geografska_duzina;
	}

	public void setGeografska_duzina(String geografska_duzina) {
		this.geografska_duzina = geografska_duzina;
	}

}

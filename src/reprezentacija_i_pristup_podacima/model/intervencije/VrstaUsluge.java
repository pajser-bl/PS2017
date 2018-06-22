package reprezentacija_i_pristup_podacima.model.intervencije;

public enum VrstaUsluge {
	
	POPRAVKA_NA_LICU_MJESTA("Popravka na licu mjesta"),
	SLEPANJE("Slepanje"),
	OSTALO("Ostalo");
	private String text;
	private VrstaUsluge(String text) {this.text=text;}
	public String getText() {return text;}
}

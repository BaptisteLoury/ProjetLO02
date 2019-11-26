package App;

public class Offre {

	private Cartes recto;
	private Cartes verso;
	private Joueur offrant;
	private boolean offreSuffisante;
	
	public Offre(Cartes recto, Cartes verso , Joueur offrant) {
		this.recto = recto;
		this.verso = verso;
		this.offrant = offrant;
		offreSuffisante = true;
	}
	public Cartes getRecto() {
		return recto;
	}
	public void setRecto(Cartes recto) {
		this.recto = recto;
	}
	public Cartes getVerso() {
		return verso;
	}
	public void setVerso(Cartes verso) {
		this.verso = verso;
	}
	public Joueur getOffrant() {
		return offrant;
	}
	public void setOffrant(Joueur offrant) {
		this.offrant = offrant;
	}
	public boolean isOffreSuffisante() {
		
		return offreSuffisante;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(offrant.getPseudo());
		sb.append(" - Carte recto : ");
		sb.append(recto.toString());
		return sb.toString();
	}
	
}

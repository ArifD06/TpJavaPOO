
import java.util.ArrayList;

class client {
    private final String nom;
    private final String prenom;
    private final String numerodepermisdeconduire;
    private  final int numerodetelephone;
    private final ArrayList<Vehicule> locationencours ;



    public client(String nom, String prenom, String numeroPermis, String numeroTelephone){
        this.nom=nom;
        this.prenom=prenom;
        this.numerodepermisdeconduire=getNumerodepermisdeconduire();
        this.numerodetelephone=getNumerodetelephone();
        this.locationencours=new ArrayList<>();

    }
    public ArrayList<Vehicule>getLocationencours(){
        return locationencours;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getNumerodepermisdeconduire(){
        return numerodepermisdeconduire;
    }
    public int getNumerodetelephone(){
        return numerodetelephone;
    }
    public void ajouterLocation(Vehicule vehicule){
        locationencours.add(vehicule);

    }
    public void supprimerLocation(Vehicule vehicule){
        locationencours.remove(vehicule);
    }

    public String getLocations() {
        return null;
    }

    public void retirerLocation(Vehicule vehicule) {
    }

    public Object getNumeroPermis() {
        return null; }
}








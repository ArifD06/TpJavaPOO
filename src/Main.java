

// conception des classes
abstract class Vehicule {
    String immatriculation,marque, modele, statut;
    int anneedeservice, kilometrage;
    public Vehicule(String immatriculation,String marque,String modele,String statut,int anneedeservice,int kilometrage){

        this.modele=modele;
        this.immatriculation=immatriculation;
        this.marque=marque;
        this.statut=statut;
        this.anneedeservice=anneedeservice;
        this.kilometrage=kilometrage;
    }
    abstract void calculerPrixLocation();



}
class Voiture extends Vehicule implements Louable{
    private boolean loue=false;
    private boolean retourner=false;

    public Voiture(String immatriculation, String marque, String modele, String statut, int anneedeservice, int kilometrage, int nombrePlaces, String typeCarburant) {
        super(immatriculation,marque, modele, statut, anneedeservice, kilometrage);
    }

    @Override
    void calculerPrixLocation() {

    }
    int nombredeplace=4;
    String typedecarburant;

    @Override
    public void louer() {
        if (statut.equals("disponible")){

            System.out.println("La voiture est déja louée");
        }
        else {throw new VehiculeIndisponibleException("Deja louée");

        }




    }

    @Override
    public void retourner() {
        System.out.println("La voiture est disponible");

    }

    static class Camion extends Vehicule implements Louable{
        private boolean loue=false;
        private boolean retourner=false;
        public Camion(String immatriculation, String marque,String modele, String statut, int anneedeservice, int kilometrage,int capacitedechargement,int nombreessieux) {
            super(immatriculation,marque, modele, statut, anneedeservice, kilometrage);
        }

        @Override
        void calculerPrixLocation() {

        }
        int capacitedechargement;
        int nombreessieux;

        @Override
        public void louer() {
            if (statut.equals("Disponible")){
                System.out.println("le camion est loué");
            }
            else
                throw new VehiculeIndisponibleException("Le camion est déja loué");

        }

        @Override
        public void retourner() {
            System.out.println("Le camion est disponible");


        }

    }}


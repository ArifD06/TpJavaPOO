import java.util.ArrayList;

public class ParcAuto{
    private static ArrayList<Vehicule> vehicules = new ArrayList<>();

    public static void ajouterLocation(Voiture voiture) {
        vehicules.add(voiture);
    }


    public static Vehicule rechercherVehicule(String immatriculation) {
        for (Vehicule v : vehicules) {
            if (v.immatriculation.equals(immatriculation)) {
                return v;
            }
        }
        return null;
    }


    public static void ajouterVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    public void ajouterVehicule(Voiture.Camion camion) {
    }


    public static class Gestion{
        private ArrayList<client> clients; // Liste des clients


        public Gestion() {
            clients = new ArrayList<>();
        }


        public void ajouterVehicule(Vehicule vehicule) {
            vehicules.add(vehicule);
        }


        public static ArrayList<Vehicule> getVehiculesDisponibles() {
            ArrayList<Vehicule> disponibles = new ArrayList<>();
            for (Vehicule v : vehicules) {
                if (v.statut.equals("disponible")) {
                    disponibles.add(v);
                }
            }
            return disponibles;
        }


        public Vehicule rechercherVehicule(String immatriculation) {
            for (Vehicule v : vehicules) {
                if (v.immatriculation.equals(immatriculation)) {
                    return v;
                }
            }
            return null;
        }


        public void ajouterClient(client client) {
            clients.add(client);
        }


        public client rechercherClient(String numeroPermis) {
            for (client client : clients) {
                if (client.getNumeroPermis().equals(numeroPermis)) {
                    return client;
                }
            }
            return null;
        }


        public void louerVehicule(client client, Vehicule vehicule) throws VehiculeIndisponibleException {
            if (vehicule instanceof Louable && vehicule.statut.equals("disponible")) {
                ((Louable) vehicule).louer();
                vehicule.statut = "loué";
                client.ajouterLocation(vehicule);
                System.out.println("Véhicule loué avec succès !");
            } else {
                throw new VehiculeIndisponibleException("Véhicule non disponible ou non louable.");
            }
        }


        public void retournerVehicule(client client, Vehicule vehicule) {
            if (client.getLocations().contains(vehicule.toString())) {
                ((Louable) vehicule).retourner();
                vehicule.statut = "disponible";
                client.retirerLocation(vehicule); // Retirer la location pour le client
                System.out.println("Véhicule retourné avec succès !");
            } else {
                System.out.println("Erreur : Le client n'a pas loué ce véhicule.");
            }
        }
    }
}

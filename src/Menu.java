import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static ParcAuto parc = new ParcAuto();
    private static ArrayList<client> clients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();
            traiterChoix(choix);
        } while (choix != 6);
    }

    public static void afficherMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Ajouter un véhicule");
        System.out.println("2. Ajouter un client");
        System.out.println("3. Lister les véhicules disponibles");
        System.out.println("4. Louer un véhicule");
        System.out.println("5. Retourner un véhicule");
        System.out.println("6. Quitter");
        System.out.print("Choisissez une option : ");
    }

    public static void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                ajouterVehicule();
                break;
            case 2:
                ajouterClient();
                break;
            case 3:
                listerVehiculesDisponibles();
                break;
            case 4:
                louerVehicule();
                break;
            case 5:
                retournerVehicule();
                break;
            case 6:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Option invalide.");
        }
    }

    public static void ajouterVehicule() {
        System.out.println("\n--- Ajouter un véhicule ---");
        System.out.print("Type de véhicule (1. Voiture, 2. Camion) : ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Immatriculation : ");
        String immatriculation = scanner.nextLine();
        System.out.print("Marque : ");
        String marque = scanner.nextLine();
        System.out.print("Modèle : ");
        String modele = scanner.nextLine();
        System.out.print("Année de mise en service : ");
        int anneeService = scanner.nextInt();
        System.out.print("Kilométrage : ");
        int kilometrage = scanner.nextInt();

        if (type == 1) {
            System.out.print("Nombre de places : ");
            int nombrePlaces = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Type de carburant (essence, diesel, électrique) : ");
            String typeCarburant = scanner.nextLine();
            Voiture voiture = new Voiture(immatriculation, marque, modele, "disponible", anneeService, kilometrage, nombrePlaces, typeCarburant);
            parc.ajouterVehicule(voiture);
            System.out.println("Voiture ajoutée avec succès !");
        } else if (type == 2) {
            System.out.print("Capacité de chargement (tonnes) : ");
            int capacite = scanner.nextInt();
            System.out.print("Nombre d'essieux : ");
            int essieux = scanner.nextInt();
            scanner.nextLine();
            Voiture.Camion camion = new Voiture.Camion(immatriculation, marque, modele, "disponible", anneeService, kilometrage, capacite, essieux);
            parc.ajouterVehicule(camion);
            System.out.println("Camion ajouté avec succès !");
        } else {
            System.out.println("Type de véhicule invalide.");
        }
    }

    public static void ajouterClient() {
        System.out.println("\n--- Ajouter un client ---");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Numéro de permis : ");
        String numeroPermis = scanner.nextLine();
        System.out.print("Numéro de téléphone : ");
        String numeroTelephone = scanner.nextLine();

        client client = new client(nom, prenom, numeroPermis, numeroTelephone);
        clients.add(client);
        System.out.println("Client ajouté avec succès !");
    }

    public static void listerVehiculesDisponibles() {
        System.out.println("\n--- Véhicules disponibles ---");
        ArrayList<Vehicule> disponibles = ParcAuto.Gestion.getVehiculesDisponibles();
        if (disponibles == null || disponibles.isEmpty()) {
            System.out.println("Aucun véhicule disponible.");
        } else {
            for (Vehicule v : disponibles) {
                System.out.println(v);
            }
        }
    }

    public static client louerVehicule() {
        System.out.println("\n--- Louer un véhicule ---");
        System.out.print("Numéro de permis du client : ");
        String numeroPermis = scanner.nextLine();
        client client = chercherClient(numeroPermis);
        if (client == null) {
            System.out.println("Client introuvable.");

        }
        else {
            return(client);
        }

        System.out.print("Immatriculation du véhicule à louer : ");
        String immatriculation = scanner.nextLine();
        Vehicule vehicule = parc.rechercherVehicule(immatriculation);
        if (vehicule == null) {
            System.out.println("Véhicule introuvable.");
            return client;
        }



        try {
            if (vehicule instanceof Louable) {
                ((Louable) vehicule).louer();
                client.ajouterLocation(vehicule);
                System.out.println("Véhicule loué avec succès !");
            } else {
                System.out.println("Ce véhicule ne peut pas être loué.");
            }
        } catch (VehiculeIndisponibleException e) {
            System.out.println("Erreur : Le véhicule est indisponible.");
        }
        return client;
    }

    public static void retournerVehicule() {
        System.out.println("\n--- Retourner un véhicule ---");
        System.out.print("Numéro de permis du client : ");
        String numeroPermis = scanner.nextLine();
        client client = chercherClient(numeroPermis);
        if (client == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Immatriculation du véhicule à retourner : ");
        String immatriculation = scanner.nextLine();
        Vehicule vehicule = parc.rechercherVehicule(immatriculation);
        if (vehicule == null || !client.getLocations().contains(vehicule.toString())) {
            System.out.println("Le client n'a pas loué ce véhicule.");
            return;
        }

        if (vehicule instanceof Louable) {
            ((Louable) vehicule).retourner();
            client.retirerLocation(vehicule);
            System.out.println("Véhicule retourné avec succès !");
        }
    }

    private static client chercherClient(String numeroPermis) {
        for (client client : clients) {
            if (client != null && client.getNumeroPermis() != null && client.getNumeroPermis().equals(numeroPermis)) {
                return client;
            }
        }
        return null;
    }
}

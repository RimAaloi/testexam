package org.example.testexam.appconsole;

import org.example.testexam.metier.CabinetMetierImpl;
import org.example.testexam.metier.ICabinetMetier;
import org.example.testexam.models.Consultation;
import org.example.testexam.models.Medecin;
import org.example.testexam.models.Patient;
import org.example.testexam.utils.SingletonConnexionDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    static ICabinetMetier metier = new CabinetMetierImpl();
    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gestion des consultations");
            System.out.println("2. Gestion des patients");
            System.out.println("3. Gestion des médecins");
            System.out.println("0. Quitter");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    gererConsultation();
                    break;
                case 2:
                    gererPatient();
                    break;
                case 3:
                    gererMedecin();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix non valide.");
            }
        }
    }

    private static void gererConsultation() {
        while (true) {
            System.out.println("\n=== Gestion des consultations ===");
            System.out.println("1. Ajouter une consultation");
            System.out.println("2. Afficher la liste des consultations");
            System.out.println("3. Supprimer une consultation");
            System.out.println("0. Retour au menu principal");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ajouterConsultation();
                    break;
                case 2:
                    afficherConsultations();
                    break;
                case 3:
                    supprimerConsultation();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix non valide.");
            }
        }
    }

    private static void gererPatient() {
        while (true) {
            System.out.println("\n=== Gestion des patients ===");
            System.out.println("1. Ajouter un patient");
            System.out.println("2. Afficher la liste des patients");
            System.out.println("3. Supprimer un patient");
            System.out.println("4. Rechercher un patient");
            System.out.println("5. Afficher les consultations d’un patient");
            System.out.println("0. Retour au menu principal");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ajouterPatient();
                    break;
                case 2:
                    afficherPatients();
                    break;
                case 3:
                    supprimerPatient();
                    break;
                case 4:
                    rechercherPatient();
                    break;
                case 5:
                    afficherConsultationsDuPatient();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix non valide.");
            }
        }
    }

    private static void gererMedecin() {
        while (true) {
            System.out.println("\n=== Gestion des médecins ===");
            System.out.println("1. Ajouter un médecin");
            System.out.println("2. Afficher la liste des médecins");
            System.out.println("3. Supprimer un médecin");
            System.out.println("4. Afficher les consultations d’un médecin");
            System.out.println("0. Retour au menu principal");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ajouterMedecin();
                    break;
                case 2:
                    afficherMedecins();
                    break;
                case 3:
                    supprimerMedecin();
                    break;
                case 4:
                    afficherConsultationsDuMedecin();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix non valide.");
            }
        }
    }

    private static void ajouterConsultation() {
        try {
            System.out.print("Date de la consultation (yyyy-MM-dd): ");
            Date date = sdf.parse(scanner.nextLine());
            System.out.print("ID du patient: ");
            int idPatient = scanner.nextInt();
            System.out.print("ID du médecin: ");
            int idMedecin = scanner.nextInt();
            scanner.nextLine();

            metier.ajouterConsultation(new Consultation(0, date, new Patient(idPatient), new Medecin(idMedecin)));
            System.out.println("Consultation ajoutée avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void afficherConsultations() {
        List<Consultation> consultations = metier.listeConsultations();
        consultations.forEach(System.out::println);
    }

    private static void supprimerConsultation() {
        System.out.print("ID de la consultation à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        metier.supprimerConsultation(id);
        System.out.println("Consultation supprimée avec succès.");
    }

    private static void ajouterPatient() {
        try {
            System.out.print("Nom: ");
            String nom = scanner.nextLine();
            System.out.print("Prénom: ");
            String prenom = scanner.nextLine();
            System.out.print("CIN: ");
            String cin = scanner.nextLine();
            System.out.print("Téléphone: ");
            String telephone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Date de naissance (yyyy-MM-dd): ");
            Date dateNaissance = sdf.parse(scanner.nextLine());

            metier.ajouterPatient(new Patient(0, nom, prenom, cin, telephone, email, dateNaissance));
            System.out.println("Patient ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void afficherPatients() {
        List<Patient> patients = metier.listePatients();
        patients.forEach(System.out::println);
    }

    private static void supprimerPatient() {
        System.out.print("ID du patient à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        metier.supprimerPatient(id);
        System.out.println("Patient supprimé avec succès.");
    }

    private static void rechercherPatient() {
        System.out.print("Mot clé pour rechercher un patient: ");
        String motCle = scanner.nextLine();
        Patient patient = metier.rechercherPatient(motCle);
        System.out.println(patient != null ? patient : "Aucun patient trouvé.");
    }

    private static void afficherConsultationsDuPatient() {
        System.out.print("ID du patient: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Consultation> consultations = metier.listeConsultationsPatient(id);
        consultations.forEach(System.out::println);
    }

    private static void ajouterMedecin() {
        try {
            System.out.print("Nom: ");
            String nom = scanner.nextLine();
            System.out.print("Prénom: ");
            String prenom = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Téléphone: ");
            String telephone = scanner.nextLine();

            metier.ajouterMedecin(new Medecin(0, nom, prenom, email, telephone));
            System.out.println("Médecin ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void afficherMedecins() {
        List<Medecin> medecins = metier.listeMedecins();
        medecins.forEach(System.out::println);
    }

    private static void supprimerMedecin() {
        System.out.print("ID du médecin à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        metier.supprimerMedecin(id);
        System.out.println("Médecin supprimé avec succès.");
    }

    private static void afficherConsultationsDuMedecin() {
        System.out.print("ID du médecin: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Consultation> consultations = metier.listeConsultationsMedecin(id);
        consultations.forEach(System.out::println);
    }
}

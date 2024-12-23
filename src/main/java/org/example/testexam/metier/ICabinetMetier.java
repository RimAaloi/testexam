package org.example.testexam.metier;

import org.example.testexam.models.Consultation;
import org.example.testexam.models.Medecin;
import org.example.testexam.models.Patient;

import java.util.List;

public interface ICabinetMetier {

    void ajouterPatient(Patient p);
    List<Patient> listePatients();
    void supprimerPatient(int idPatient);
    Patient rechercherPatient(String motCle);
    List<Consultation> listeConsultationsPatient(int idPatient);


    void ajouterMedecin(Medecin m);
    List<Medecin> listeMedecins();
    void supprimerMedecin(int idMedecin);
    List<Consultation> listeConsultationsMedecin(int idMedecin);


    void ajouterConsultation(Consultation c);
    List<Consultation> listeConsultations();
    void supprimerConsultation(int idConsultation);
}

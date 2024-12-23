package org.example.testexam.models;

import java.util.Date;

public class Consultation {
    private int idConsultation;
    private Date dateConsultation;
    private Patient patient;
    private Medecin medecin;

    public Consultation(int idConsultation, Date dateConsultation, Patient patient, Medecin medecin) {
        this.idConsultation = idConsultation;
        this.dateConsultation = dateConsultation;
        this.patient = patient;
        this.medecin = medecin;
    }

    public Consultation() {

    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "idConsultation=" + idConsultation +
                ", dateConsultation=" + dateConsultation +
                ", patient=" + patient +
                ", medecin=" + medecin +
                '}';
    }
}

package org.example.testexam.metier;

import org.example.testexam.models.Consultation;
import org.example.testexam.models.Medecin;
import org.example.testexam.models.Patient;
import org.example.testexam.utils.SingletonConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CabinetMetierImpl implements ICabinetMetier {

    private Connection connection;

    public CabinetMetierImpl() {
        connection = SingletonConnexionDB.getConnexion();
    }

    @Override
    public void ajouterPatient(Patient p) {
        String sql = "INSERT INTO Patient (nom, prenom, cin, telephone, email, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getCin());
            ps.setString(4, p.getTelephone());
            ps.setString(5, p.getEmail());
            ps.setDate(6, new java.sql.Date(p.getDateNaissance().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> listePatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient();
                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setCin(rs.getString("cin"));
                p.setTelephone(rs.getString("telephone"));
                p.setEmail(rs.getString("email"));
                p.setDateNaissance(rs.getDate("date_naissance"));
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void supprimerPatient(int idPatient) {
        String sql = "DELETE FROM Patient WHERE id_patient = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPatient);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient rechercherPatient(String motCle) {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE nom LIKE ? OR prenom LIKE ? OR cin LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ps.setString(3, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                patient.setIdPatient(rs.getInt("id_patient"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setCin(rs.getString("cin"));
                patient.setTelephone(rs.getString("telephone"));
                patient.setEmail(rs.getString("email"));
                patient.setDateNaissance(rs.getDate("date_naissance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Consultation> listeConsultationsPatient(int idPatient) {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM Consultation WHERE id_patient = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPatient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    // Gestion des médecins
    @Override
    public void ajouterMedecin(Medecin m) {
        String sql = "INSERT INTO Medecin (nom, prenom, email, tel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getTel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> listeMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        String sql = "SELECT * FROM Medecin";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Medecin m = new Medecin();
                m.setIdMedecin(rs.getInt("id_medecin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setEmail(rs.getString("email"));
                m.setTel(rs.getString("tel"));
                medecins.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public void supprimerMedecin(int idMedecin) {
        String sql = "DELETE FROM Medecin WHERE id_medecin = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idMedecin);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> listeConsultationsMedecin(int idMedecin) {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM Consultation WHERE id_medecin = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idMedecin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void ajouterConsultation(Consultation c) {
        String sql = "INSERT INTO Consultation (date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(c.getDateConsultation().getTime()));
            ps.setInt(2, c.getPatient().getIdPatient());
            ps.setInt(3, c.getMedecin().getIdMedecin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> listeConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM Consultation";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void supprimerConsultation(int idConsultation) {
        String sql = "DELETE FROM Consultation WHERE id_consultation = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idConsultation);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

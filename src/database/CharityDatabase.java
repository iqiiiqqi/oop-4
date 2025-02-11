package database;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharityDatabase {
    public void insertDonor(Donor donor) throws SQLException {
        String query = "INSERT INTO donors (id, name, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, donor.getId());
            stmt.setString(2, donor.getName());
            stmt.setString(3, donor.getEmail());
            stmt.executeUpdate();
        }
    }

    public void updateDonorEmail(int id, String newEmail) throws SQLException {
        String query = "UPDATE donors SET email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newEmail);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteDonor(int id) throws SQLException {
        String query = "DELETE FROM donors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void listDonors() throws SQLException {
        String query = "SELECT * FROM donors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Donor{id=" + rs.getInt("id") + ", name='" + rs.getString("name") + "', email='" + rs.getString("email") + "'}");
            }
        }
    }

    public void insertDonation(Donation donation) throws SQLException {
        String query = "INSERT INTO donations (id, amount, donor_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, donation.getId());
            stmt.setDouble(2, donation.getAmount());
            stmt.setInt(3, donation.getDonorId());
            stmt.executeUpdate();
        }
    }

    public List<Donor> getAllDonors() throws SQLException {
        List<Donor> donors = new ArrayList<>();
        String query = "SELECT * FROM donors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Donor donor = new Donor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                donors.add(donor);
            }
        }
        return donors;
    }

    public Donor getDonorById(int id) throws SQLException {
        String query = "SELECT * FROM donors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Donor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            } else {
                return null;
            }
        }
    }
}

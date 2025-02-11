import models.*;
import database.CharityDatabase;

public class Main {
    public static void main(String[] args) {
        Donor donor1 = new Donor(1, "John Doe", "john@example.com");
        Donor donor2 = new Donor(2, "Jane Smith", "jane@example.com");

        Donation donation1 = new Donation(1, 1.99, 1);

        System.out.println(donor1);
        System.out.println(donor2);
        System.out.println(donation1);

        if (donor1.getName().equals(donor2.getName())) {
            System.out.println("Donors have the same name.");
        } else {
            System.out.println("Donors have different names.");
        }

        try {
            CharityDatabase db = new CharityDatabase();
            db.insertDonor(donor1);
            db.insertDonor(donor2);
            db.listDonors();

            db.updateDonorEmail(1, "newemail@example.com");
            db.listDonors();

            db.deleteDonor(2);
            db.listDonors();

            db.insertDonation(donation1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

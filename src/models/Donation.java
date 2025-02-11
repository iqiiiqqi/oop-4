package models;

public class Donation {
    private int id;
    private double amount;
    private int donorId;

    public Donation(int id, double amount, int donorId) {
        this.id = id;
        this.amount = amount;
        this.donorId = donorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    @Override
    public String toString() {
        return "Donation{id=" + id + ", amount=" + amount + ", donorId=" + donorId + "}";
    }
}

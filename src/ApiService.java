import static spark.Spark.*;

import com.google.gson.Gson;
import models.Donor;
import database.CharityDatabase;

import java.util.List;

public class ApiService {
    public static void main(String[] args) {
        port(8081);

        CharityDatabase db = new CharityDatabase();
        Gson gson = new Gson();

        get("/donors", (req, res) -> {
            res.type("application/json");
            try {
                List<Donor> donors = db.getAllDonors();
                return donors;
            } catch (Exception e) {
                res.status(500);
                return new ResponseError("Internal Server Error: " + e.getMessage());
            }
        }, gson::toJson);

        get("/donors/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            try {
                Donor donor = db.getDonorById(id);
                if (donor == null) {
                    res.status(404);
                    return new ResponseError("Donor not found");
                }
                return donor;
            } catch (Exception e) {
                res.status(500);
                return new ResponseError("Internal Server Error: " + e.getMessage());
            }
        }, gson::toJson);

        post("/donors", (req, res) -> {
            res.type("application/json");
            Donor donor = gson.fromJson(req.body(), Donor.class);
            try {
                db.insertDonor(donor);
                res.status(201);  // Created
                return donor;
            } catch (Exception e) {
                res.status(500);
                return new ResponseError("Internal Server Error: " + e.getMessage());
            }
        }, gson::toJson);

        put("/donors/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            EmailUpdate emailUpdate = gson.fromJson(req.body(), EmailUpdate.class);
            try {
                db.updateDonorEmail(id, emailUpdate.getEmail());
                Donor updatedDonor = db.getDonorById(id);
                return updatedDonor;
            } catch (Exception e) {
                res.status(500);
                return new ResponseError("Internal Server Error: " + e.getMessage());
            }
        }, gson::toJson);

        delete("/donors/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            try {
                db.deleteDonor(id);
                res.status(204);
                return "";
            } catch (Exception e) {
                res.status(500);
                return new ResponseError("Internal Server Error: " + e.getMessage());
            }
        });
    }

    static class ResponseError {
        private String message;

        public ResponseError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class EmailUpdate {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

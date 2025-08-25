package org.example.medi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class SellerDashboardController {

    @FXML public Button notificationBtn;

    @FXML
    private void handleNotification() {
        User seller = UserManager.getCurrentUser();
        String messages = String.join("\n", seller.getNotifications());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Notifications");
        alert.setHeaderText("New Orders");
        alert.setContentText(messages.isEmpty() ? "No new orders." : messages);
        alert.showAndWait();

        seller.clearNotifications();
        UserManager.saveUserDetails(seller);
    }

    @FXML
    public void handleViewProfile(ActionEvent actionEvent) {
        User currentUser = UserManager.getCurrentUser();
        if (currentUser != null && "Seller".equalsIgnoreCase(currentUser.getRole())) {
            SceneManager.switchTo("profileSeller.fxml", (Node) actionEvent.getSource());
        } else {
            SceneManager.switchTo("profileBuyer.fxml", (Node) actionEvent.getSource());
        }
    }

    @FXML
    public void handleLogout(ActionEvent actionEvent) {
        UserManager.logout();
        SceneManager.switchTo("login.fxml", (Node) actionEvent.getSource());
    }

    @FXML
    public void handleViewOrders(ActionEvent actionEvent) {
        SceneManager.switchTo("seller_orders.fxml", (Node) actionEvent.getSource());
    }

    @FXML
    public void handleUploadProduct(ActionEvent actionEvent) {
        SceneManager.switchTo("plantseller.fxml", (Node) actionEvent.getSource());
    }

}

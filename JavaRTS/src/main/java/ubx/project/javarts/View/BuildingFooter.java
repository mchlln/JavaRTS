package ubx.project.javarts.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.transform.Scale;

public class BuildingFooter extends ScrollPane {

    private HBox container;

    public BuildingFooter() {
        // Initialize container to hold the widgets
        container = new HBox(10); // 10px spacing between widgets
        container.setPadding(new Insets(10));
        container.setAlignment(Pos.CENTER_LEFT);
        container.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/ubx/project/javarts/panel_blue.png").toExternalForm()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        this.setContent(container);
        this.setFitToHeight(true);  // Ensure ScrollPane fits to the full height of its content
        this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); // Enable horizontal scrolling if the content overflows
        this.setVbarPolicy(ScrollBarPolicy.NEVER); // Disable vertical scrolling

        // Set width to be dynamic, we want it to scale with the window size
        this.setPrefWidth(Double.MAX_VALUE);
    }

    public void addWidget(javafx.scene.Node widget) {
        Scale scale = new Scale(0.8, 0.8); // Scale factor 0.5 to reduce to 50%
        widget.getTransforms().add(scale);
        container.getChildren().add(widget);
    }

    public void setBuildingCardHeightFromFooter() {
        // Get the height of the FooterWidget
        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Set the maxHeight of all BuildingCard children to match the FooterWidget height
                for (var child : getChildren()) {
                    if (child instanceof BuildingCard) {
                        ((BuildingCard) child).setMaxHeight(newValue.doubleValue());
                    }
                }
            }
        });
    }
}


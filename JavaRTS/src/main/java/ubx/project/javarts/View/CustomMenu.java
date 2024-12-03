package ubx.project.javarts.View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class CustomMenu extends VBox {
    private VBox container;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem newGameItem;
    private MenuItem loadGameItem;
    private MenuItem saveGameItem;
    private MenuItem exitItem;


    public CustomMenu() {
        this.menu = new Menu("Game");
        this.menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        this.newGameItem = new MenuItem("New Game");
        this.loadGameItem = new MenuItem("Load Game");
        this.saveGameItem = new MenuItem("Save Game");
        this.exitItem = new MenuItem("Exit");


        menu.getItems().addAll(newGameItem, loadGameItem,saveGameItem,exitItem);
        this.getChildren().addAll(menuBar);
    }
}

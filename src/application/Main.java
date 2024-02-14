package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {

	static PlayWithFriend level1 = new PlayWithFriend();
	static LowLevelWithComputer level2 = new LowLevelWithComputer();
	static HighLevelWithComputer level3 = new HighLevelWithComputer();
//	static HighLevelWithComputer2 level33 = new HighLevelWithComputer2();

	Stage stage1 = new Stage();
	
	boolean isfirstt;
	boolean isXx;


	@Override
	public void start(Stage primaryStage) {

		stage1.setTitle("Tik Tak Tok Game");
		stage1.getIcons().add(new Image("icon.jpg"));

		Pane pane = new Pane();

		ImageView backgroung = new ImageView("backgroung.jpg");
		backgroung.setFitWidth(620);
		backgroung.setFitHeight(450);
		backgroung.setOpacity(0.15);
		pane.getChildren().add(backgroung);

		Text title = new Text("Welcome to X/O Game");
		pane.getChildren().add(title);
		title.getStyleClass().add("tit");
		title.setLayoutX(70);
		title.setLayoutY(100);

		// select a way which the user plays it
		// 1 -> low level(randomly) 2 -> high level(min-max) 3 -> with friend

//		//option 1
		Button random = new Button("Low Level");
		pane.getChildren().add(random);
		random.setFont(new Font(25));
		random.setPrefSize(150, 20);
		random.getStyleClass().add("butt");
		random.setLayoutX(230);
		random.setLayoutY(150);

		random.setOnAction(e -> {
			infoScreen(1);
			stage1.close();
		});

		// option 2
		Button highLevel = new Button("High Level");
		pane.getChildren().add(highLevel);
		highLevel.setFont(new Font(25));
		highLevel.setPrefSize(160, 20);
		highLevel.getStyleClass().add("butt");
		highLevel.setLayoutX(225);
		highLevel.setLayoutY(230);

		highLevel.setOnAction(e -> {
			infoScreen(2);
			stage1.close();
		});

		// option 3
		Button withFriend = new Button("With Friend");
		pane.getChildren().add(withFriend);
		withFriend.setFont(new Font(25));
		withFriend.setPrefSize(180, 20);
		withFriend.getStyleClass().add("butt");
		withFriend.setLayoutX(215);
		withFriend.setLayoutY(310);

		withFriend.setOnAction(e -> {
			infoScreen(3);
			// level1.randomPlay(STYLESHEET_CASPIAN, null, 0, false);

			stage1.close();
		});

		Scene scene = new Scene(pane, 620, 450);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage1.setScene(scene);
		// stage.setResizable(false);
		stage1.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void infoScreen(int option) {
		Stage stage = new Stage();
		stage.setTitle("Tik Tak Tok Game");
		stage.getIcons().add(new Image("icon.jpg"));

		Pane pane = new Pane();

		ImageView backgroung = new ImageView("backgroung.jpg");
		backgroung.setFitWidth(620);
		backgroung.setFitHeight(500);
		backgroung.setOpacity(0.15);

		if (option == 1 || option == 2) {
			pane.getChildren().clear();
			pane.getChildren().add(backgroung);

			Label name = new Label("Player Name");
			name.setLayoutX(30);
			name.setLayoutY(30);
			name.getStyleClass().add("lab");
			pane.getChildren().add(name);

			// enter the player name
			TextField name_ = new TextField();
			name_.getStyleClass().add("textField");
			name_.setPrefWidth(200);
			name_.setPrefHeight(30);
			name_.setLayoutX(40);
			name_.setLayoutY(80);
			name_.setPromptText("player name");
			pane.getChildren().add(name_);

			Label firstOne = new Label("Am I the first player?");
			firstOne.setLayoutX(30);
			firstOne.setLayoutY(130);
			firstOne.getStyleClass().add("lab");
			pane.getChildren().add(firstOne);

			CheckBox isfirst = new CheckBox();
			pane.getChildren().add(isfirst);
			isfirst.setStyle("-fx-font-size: 24; -fx-border-width: 2px; -fx-border-color: #FF8C00;");
			isfirst.setLayoutX(40);
			isfirst.setLayoutY(180);

			Label numOfRounds = new Label("Number Of Rounds:");
			numOfRounds.setLayoutX(30);
			numOfRounds.setLayoutY(230);
			numOfRounds.getStyleClass().add("lab");
			pane.getChildren().add(numOfRounds);

			// enter # of rounds
			TextField numOfRounds_ = new TextField();
			numOfRounds_.getStyleClass().add("textField");
			numOfRounds_.setPrefWidth(200);
			numOfRounds_.setPrefHeight(30);
			numOfRounds_.setLayoutX(40);
			numOfRounds_.setLayoutY(280);
			numOfRounds_.setPromptText("# of rounds");
			pane.getChildren().add(numOfRounds_);

			Label xORo = new Label("Do You want to take X?");
			xORo.setLayoutX(30);
			xORo.setLayoutY(330);
			xORo.getStyleClass().add("lab");
			pane.getChildren().add(xORo);

			CheckBox isX = new CheckBox();
			pane.getChildren().add(isX);
			isX.setStyle("-fx-font-size: 24; -fx-border-width: 2px; -fx-border-color: #FF8C00;");
			isX.setLayoutX(40);
			isX.setLayoutY(380);

			Button back = new Button("<Back>");
			pane.getChildren().add(back);
			back.setFont(new Font(17));
			back.setLayoutX(490);
			back.setLayoutY(430);

			// back to first stage:
			back.setOnAction(e -> {
				stage.close();
				stage1.show();
			});

			Button play = new Button("<PLAY>");
			play.getStyleClass().add("play");
			pane.getChildren().add(play);
			play.setFont(new Font(17));
			play.setLayoutX(380);
			play.setLayoutY(430);

			// back to first stage:
			play.setOnAction(e -> {
				if (!name_.getText().trim().isEmpty() && !numOfRounds_.getText().trim().isEmpty()
						&& numOfRounds_.getText().trim().matches("\\d+")) {
					boolean isfirstt = false;
					if (isfirst.isSelected() == true) {
						isfirstt = true;
					}
					boolean isXx = false;
					if (isX.isSelected() == true) {
						isXx = true;
					}

					stage.close();
					if(option == 1)
						level2.lowLevel(name_.getText().trim(), isXx, isfirstt, Integer.parseInt(numOfRounds_.getText().trim()));
					else
						level3.highLeveL(name_.getText().trim(), isXx, isfirstt, Integer.parseInt(numOfRounds_.getText().trim()));
				}

			});
		} else {
			pane.getChildren().clear();
			pane.getChildren().add(backgroung);

			Label myname = new Label("My Name");
			myname.setLayoutX(30);
			myname.setLayoutY(30);
			myname.getStyleClass().add("lab");
			pane.getChildren().add(myname);

			// enter my name
			TextField myname_ = new TextField();
			myname_.getStyleClass().add("textField_1");
			myname_.setPrefWidth(200);
			myname_.setPrefHeight(30);
			myname_.setLayoutX(40);
			myname_.setLayoutY(80);
			myname_.setPromptText("my name");
			pane.getChildren().add(myname_);

			Label friendname = new Label("My Friend Name");
			friendname.setLayoutX(350);
			friendname.setLayoutY(30);
			friendname.getStyleClass().add("lab");
			pane.getChildren().add(friendname);

			// enter my friend name
			TextField friendname_ = new TextField();
			friendname_.getStyleClass().add("textField_2");
			friendname_.setPrefWidth(200);
			friendname_.setPrefHeight(30);
			friendname_.setLayoutX(360);
			friendname_.setLayoutY(80);
			friendname_.setPromptText("my friend name");
			pane.getChildren().add(friendname_);

			Label firstOne = new Label("Am I the first player?");
			firstOne.setLayoutX(30);
			firstOne.setLayoutY(130);
			firstOne.getStyleClass().add("lab");
			pane.getChildren().add(firstOne);

			CheckBox isfirst = new CheckBox();
			pane.getChildren().add(isfirst);
			isfirst.setStyle("-fx-font-size: 24; -fx-border-width: 2px; -fx-border-color: #FF8C00;");
			isfirst.setLayoutX(40);
			isfirst.setLayoutY(180);

			Label numOfRounds = new Label("Number Of Rounds:");
			numOfRounds.setLayoutX(30);
			numOfRounds.setLayoutY(230);
			numOfRounds.getStyleClass().add("lab");
			pane.getChildren().add(numOfRounds);

			// enter # of rounds
			TextField numOfRounds_ = new TextField();
			numOfRounds_.getStyleClass().add("textField");
			numOfRounds_.setPrefWidth(200);
			numOfRounds_.setPrefHeight(30);
			numOfRounds_.setLayoutX(40);
			numOfRounds_.setLayoutY(280);
			numOfRounds_.setPromptText("# of rounds");
			pane.getChildren().add(numOfRounds_);

			Label xORo = new Label("Do You want to take X?");
			xORo.setLayoutX(30);
			xORo.setLayoutY(330);
			xORo.getStyleClass().add("lab");
			pane.getChildren().add(xORo);

			CheckBox isX = new CheckBox();
			pane.getChildren().add(isX);
			isX.setStyle("-fx-font-size: 24; -fx-border-width: 2px; -fx-border-color: #FF8C00;");
			isX.setLayoutX(40);
			isX.setLayoutY(380);

			Button back = new Button("<Back>");
			pane.getChildren().add(back);
			back.setFont(new Font(17));
			back.setLayoutX(490);
			back.setLayoutY(430);

			// back to first stage:
			back.setOnAction(e -> {
				stage.close();
				stage1.show();
			});

			Button play = new Button("<PLAY>");
			play.getStyleClass().add("play");
			pane.getChildren().add(play);
			play.setFont(new Font(17));
			play.setLayoutX(380);
			play.setLayoutY(430);
//boolean isfirstt = false;boolean isXx = false;
			// back to first stage:
			play.setOnAction(e -> {
				if (!friendname_.getText().trim().isEmpty() && !myname_.getText().trim().isEmpty()
						&& !numOfRounds_.getText().trim().isEmpty() && numOfRounds_.getText().trim().matches("\\d+")) {
					
					if (isfirst.isSelected() == true) {
						isfirstt = true;
					}else
						isfirstt = false;
					
					if (isX.isSelected() == true) {
						isXx = true;
					}else
						isXx = false;

					level1.playWithMyFriend(myname_.getText().trim(), friendname_.getText().trim(), isfirstt,
							Integer.parseInt(numOfRounds_.getText().trim()), isXx);

					stage.close();

				}
			});

		}

		Scene scene = new Scene(pane, 620, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		// stage.setResizable(false);
		stage.show();
	}

}

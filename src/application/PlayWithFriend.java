package application;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayWithFriend {

	ArrayList<Character> numbers;

	Button[] buttons;
	Boolean isclick1 = true;

	Text name1 = new Text();
	Text name2 = new Text();
	Text scor1 = new Text("0");
	Text scor2 = new Text("0");
	Text vs = new Text("VS");

	Text numberOfRounds = new Text();

	int count;

	public void playWithMyFriend(String myName, String friendName, Boolean isFirst, int numOFrounds, boolean isX) {
		numberOfRounds.setText("1/" + numOFrounds);
		scor1 = new Text("0");
		scor2 = new Text("0");
		numbers = new ArrayList<>();
		buttons = new Button[9];

		Stage stage = new Stage();
		stage.setTitle("Tik Tak Tok Game");
		stage.getIcons().add(new Image("icon.jpg"));

		Pane pane = new Pane();

		ImageView background = new ImageView(new Image("backgroung.jpg"));
		background.setFitWidth(620);
		background.setFitHeight(500);
		background.setOpacity(0.15);
		pane.getChildren().add(background);

		// show the players information
		if (isFirst) {
			name1.setText(myName);
			name2.setText(friendName);
		}
//		if (isFirst && !isX) {
//			name1.setText(myName+" -> O");
//			name2.setText(friendName+" -> X");
//		}if (!isFirst && isX) {
//			name1.setText(friendName+" -> O");
//			name2.setText(myName+" -> X");
//		} 
		else {
			name1.setText(friendName);
			name2.setText(myName);
		}
		name1.setLayoutX(50);
		name1.setLayoutY(50);
		name1.getStyleClass().add("n1");
		pane.getChildren().add(name1);

		vs.setLayoutX(290);
		vs.setLayoutY(50);
		vs.getStyleClass().add("info");
		pane.getChildren().add(vs);

		name2.setLayoutX(430);
		name2.setLayoutY(50);
		name2.getStyleClass().add("n2");
		pane.getChildren().add(name2);

		scor1.setLayoutX(60);
		scor1.setLayoutY(85);
		scor1.setFont(new Font(22));
		pane.getChildren().add(scor1);

		scor2.setLayoutX(440);
		scor2.setLayoutY(85);
		scor2.setFont(new Font(22));
		pane.getChildren().add(scor2);

		Line line = new Line(20, 100, 600, 100);
		pane.getChildren().add(line);

		Line line1 = new Line(230, 170, 230, 430);
		pane.getChildren().add(line1);
		line1.getStyleClass().add("lin4");

		Line line2 = new Line(380, 170, 380, 430);
		pane.getChildren().add(line2);
		line2.getStyleClass().add("lin4");

		Line line3 = new Line(130, 250, 480, 250);
		pane.getChildren().add(line3);
		line3.getStyleClass().add("lin4");

		Line line4 = new Line(130, 350, 480, 350);
		pane.getChildren().add(line4);
		line4.getStyleClass().add("lin4");

		// result of # of rounds
		numberOfRounds.setLayoutX(570);
		numberOfRounds.setLayoutY(480);
		numberOfRounds.setFont(new Font(22));
		pane.getChildren().add(numberOfRounds);
//_____________________________________________________________________________
		// set the buttons in the pane
		for (int i = 0; i < 9; i++) {
			numbers.add('0');
			buttons[i] = new Button(); // Initialize each button
			buttons[i].setPrefWidth(60);
			buttons[i].setPrefHeight(60);
			buttons[i].getStyleClass().add("xo");

		}

		count = 0;

		for (int i = 0; i < numbers.size(); i++) {
			int t = i;

			buttons[t].setOnAction(e -> {

				if (count < numOFrounds) {
					char result = buttonAction(t, isX, isFirst);
					if (result == 'x') {
						clear(pane);
						if (isFirst && isX) {
							scor1.setText((Integer.parseInt(scor1.getText().trim()) + 1) + " ");
						} else if (!isFirst && isX) {
							scor2.setText((Integer.parseInt(scor2.getText().trim()) + 1) + " ");
						} else if (isFirst && !isX) {
							scor2.setText((Integer.parseInt(scor2.getText().trim()) + 1) + " ");
						} else if (!isFirst && !isX) {
							scor1.setText((Integer.parseInt(scor1.getText().trim()) + 1) + " ");
						}
						System.out.println("X is the winer");

						isclick1 = true;
						count++;
						if (count == numOFrounds)
							theFinalResult('X');
					} else if (result == 'o') {
						clear(pane);
						if (isFirst && isX) {
							scor2.setText((Integer.parseInt(scor2.getText().trim()) + 1) + " ");
						} else if (!isFirst && isX) {
							scor1.setText((Integer.parseInt(scor1.getText().trim()) + 1) + " ");
						} else if (isFirst && !isX) {
							scor1.setText((Integer.parseInt(scor1.getText().trim()) + 1) + " ");
						} else if (!isFirst && !isX) {
							scor2.setText((Integer.parseInt(scor2.getText().trim()) + 1) + " ");
						}

						System.out.println("O is the winer");
						isclick1 = true;
						count++;

						if (count == numOFrounds)
							theFinalResult('O');
					} else if (isFull()) {
						clear(pane);
						isclick1 = true;
						count++;
					}
					if (count < numOFrounds) {
						// display the result of number of rounds
						numberOfRounds.setText((count + 1) + "/" + numOFrounds);
					}
				} else {
					numberOfRounds.setText(numOFrounds + "/" + numOFrounds);
					for (int a = 0; a < 9; a++)
						buttons[a].setDisable(false);

				}
				
				// display the final result and back to the page
				if(isFirst && isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOFrounds/2)
							theFinalResult('x');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOFrounds/2)
							theFinalResult('o');
				}
				else if(isFirst && !isX) {
						if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
								Integer.parseInt(scor1.getText().trim()) > numOFrounds/2)
								theFinalResult('o');
						else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
								Integer.parseInt(scor2.getText().trim()) > numOFrounds/2)
								theFinalResult('x');
				}else if (!isFirst && isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOFrounds/2)
							theFinalResult('o');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOFrounds/2)
							theFinalResult('x');
				}else if(!isFirst && !isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOFrounds/2)
							theFinalResult('x');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOFrounds/2)
							theFinalResult('o');
			}

			});

		}
		Button back = new Button("Back");
		pane.getChildren().add(back);
		back.setLayoutY(460);

		back.setOnAction(e -> {
			stage.close();
			Main m = new Main();
			m.start(stage);
		});

		setButtons(buttons, pane);

		Scene scene = new Scene(pane, 620, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	private void setButtons(Button[] a, Pane b) {
		a[0].setLayoutX(140);
		a[0].setLayoutY(175);
		b.getChildren().add(a[0]);

		a[1].setLayoutX(270);
		a[1].setLayoutY(175);
		b.getChildren().add(a[1]);

		a[2].setLayoutX(410);
		a[2].setLayoutY(175);
		b.getChildren().add(a[2]);

		a[3].setLayoutX(140);
		a[3].setLayoutY(270);
		b.getChildren().add(a[3]);

		a[4].setLayoutX(270);
		a[4].setLayoutY(270);
		b.getChildren().add(a[4]);

		a[5].setLayoutX(410);
		a[5].setLayoutY(270);
		b.getChildren().add(a[5]);

		a[6].setLayoutX(140);
		a[6].setLayoutY(365);
		b.getChildren().add(a[6]);

		a[7].setLayoutX(270);
		a[7].setLayoutY(365);
		b.getChildren().add(a[7]);

		a[8].setLayoutX(410);
		a[8].setLayoutY(365);
		b.getChildren().add(a[8]);
	}

	private char buttonAction(int index, Boolean isX, boolean is1) {
		System.out.println(isclick1 + " hihi");
		if (numbers.get(index) == '0') {
			if (isclick1 && isX && is1) {
				// if the first player has X
				ImageView x = new ImageView(new Image("redx.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.set(index, 'x');
				isclick1 = false;
				return checkWiner();
			} else if (!isclick1 && isX && is1) {
				// the second player has O
				ImageView o = new ImageView(new Image("blueo.png"));
				o.setFitWidth(60);
				o.setFitHeight(60);

				buttons[index].setGraphic(o);
				numbers.set(index, 'o');
				isclick1 = true;
				return checkWiner();

			} else if (isclick1 && !isX && is1) {
				// if the first player has o
				ImageView x = new ImageView(new Image("redo.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.set(index, 'o');
				isclick1 = false;
				return checkWiner();
			} else if (!isclick1 && !isX && is1) {
				// the second player has x
				ImageView o = new ImageView(new Image("bluex.png"));
				o.setFitWidth(60);
				o.setFitHeight(60);

				buttons[index].setGraphic(o);
				numbers.set(index, 'x');
				isclick1 = true;
				return checkWiner();

			}
			if (isclick1 && !isX && !is1) {
				ImageView o = new ImageView(new Image("redx.png"));
				o.setFitWidth(60);
				o.setFitHeight(60);

				buttons[index].setGraphic(o);
				numbers.set(index, 'x');
				isclick1 = false;
				return checkWiner();
			} else if (!isclick1 && !isX && !is1) {
				ImageView x = new ImageView(new Image("blueo.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.set(index, 'o');
				isclick1 = true;
				return checkWiner();
			} else if (isclick1 && isX && !is1) {
				// if the second player has x
				ImageView o = new ImageView(new Image("redo.png"));
				o.setFitWidth(60);
				o.setFitHeight(60);

				buttons[index].setGraphic(o);
				numbers.set(index, 'o');
				isclick1 = false;
				return checkWiner();
			} else if (!isclick1 && isX && !is1) {
				// the first player has o
				ImageView x = new ImageView(new Image("bluex.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.set(index, 'x');
				isclick1 = true;
				return checkWiner();
			}
		}
		return 'f';
	}

	private char checkWiner() {
		if (numbers.get(0) == numbers.get(1) && numbers.get(2) == numbers.get(1))
			return numbers.get(0);
		if (numbers.get(3) == numbers.get(4) && numbers.get(4) == numbers.get(5))
			return numbers.get(3);
		if (numbers.get(6) == numbers.get(7) && numbers.get(7) == numbers.get(8))
			return numbers.get(6);

		if (numbers.get(0) == numbers.get(3) && numbers.get(3) == numbers.get(6))
			return numbers.get(0);
		if (numbers.get(1) == numbers.get(4) && numbers.get(4) == numbers.get(7))
			return numbers.get(1);
		if (numbers.get(2) == numbers.get(5) && numbers.get(5) == numbers.get(8))
			return numbers.get(2);

		if (numbers.get(0) == numbers.get(4) && numbers.get(4) == numbers.get(8))
			return numbers.get(0);
		if (numbers.get(2) == numbers.get(4) && numbers.get(4) == numbers.get(6))
			return numbers.get(2);

		return 'f';
	}

	private boolean isFull() {
		for (char i : numbers) {
			if (i == '0')
				return false;
		}
		return true;
	}

	private void clear(Pane pane) {
		// clear the Tic-Tac-Toe board
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setGraphic(null);
			pane.getChildren().remove(buttons[i]);
			numbers.set(i, '0'); // Reset the numbers list
		}
		setButtons(buttons, pane);
	}

	private void theFinalResult(char c) {
		Stage stage = new Stage();
		Pane pane = new Pane();

		Label l = new Label(c + " won");
		pane.getChildren().add(l);
		l.setLayoutX(50);
		l.setLayoutY(70);
		l.setFont(new Font(18));

		Button ok = new Button("Exit");
		pane.getChildren().add(ok);
		ok.setFont(new Font(20));
		ok.setPrefSize(100, 20);
		ok.getStyleClass().add("butt");
		ok.setLayoutX(50);
		ok.setLayoutY(130);

		ok.setOnAction(e -> {
			stage.close();
		});

		Scene scene = new Scene(pane, 200, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}

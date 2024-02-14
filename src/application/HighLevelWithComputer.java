package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HighLevelWithComputer {

	ArrayList<MyData> numbers;

	Button[] buttons;
	Boolean isclick1 = true;

	TextArea re;

	Text name1 = new Text();
	Text name2 = new Text();
	Text scor1 = new Text("0");
	Text scor2 = new Text("0");
	Text vs = new Text("VS");

	Text numberOfRounds = new Text();

	int count;

	ArrayList<MyData> theEmpty = new ArrayList<>();

	public void highLeveL(String name, boolean isX, boolean isFirst, int numOfRounds) {
		numberOfRounds.setText("1/" + numOfRounds);
		scor1 = new Text("0");
		scor2 = new Text("0");
		numbers = new ArrayList<>();
		buttons = new Button[9];

		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

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
			name1.setText(name);
			name2.setText("Computer");
		} else {
			name1.setText("Computer");
			name2.setText(name);
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
//_____________________________________________________________________________		
		// set the buttons in the pane
		for (int i = 0; i < 9; i++) {
			numbers.add(new MyData(i + 1, '0', false));
			buttons[i] = new Button(); // Initialize each button
			buttons[i].setPrefWidth(63);
			buttons[i].setPrefHeight(60);
			buttons[i].getStyleClass().add("xo");

		}

		setButtons(buttons, pane);

		count = 0;

		for (int i = 0; i < numbers.size(); i++) {
			int t = i;

			buttons[t].setOnAction(e -> {
				if (count < numOfRounds) {
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
						count++;
						if (count == numOfRounds)
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
						count++;
						if (count == numOfRounds)
							theFinalResult('O');
					} else if (isFull()) {
						clear(pane);
						count++;
					}
					if (count < numOfRounds) {
						// display the result of number of rounds
						numberOfRounds.setText((count + 1) + "/" + numOfRounds);
					}
				} else {
					numberOfRounds.setText(numOfRounds + "/" + numOfRounds);
					for (int a = 0; a < 9; a++)
						buttons[a].setDisable(false);
				}
				
				// display the final result and back to the page
				if(isFirst && isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOfRounds/2)
							theFinalResult('x');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOfRounds/2)
							theFinalResult('o');
				}
				else if(isFirst && !isX) {
						if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
								Integer.parseInt(scor1.getText().trim()) > numOfRounds/2)
								theFinalResult('o');
						else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
								Integer.parseInt(scor2.getText().trim()) > numOfRounds/2)
								theFinalResult('x');
				}else if (!isFirst && isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOfRounds/2)
							theFinalResult('o');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOfRounds/2)
							theFinalResult('x');
				}else if(!isFirst && !isX) {
					if(Integer.parseInt(scor1.getText().trim()) > Integer.parseInt(scor2.getText().trim()) && 
							Integer.parseInt(scor1.getText().trim()) > numOfRounds/2)
							theFinalResult('x');
					else if(Integer.parseInt(scor2.getText().trim()) > Integer.parseInt(scor1.getText().trim()) && 
							Integer.parseInt(scor2.getText().trim()) > numOfRounds/2)
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
//
		if (!isFirst && isX) {
			ImageView o = new ImageView(new Image("redo.png"));
			o.setFitWidth(60);
			o.setFitHeight(60);

			theEmpty.clear();
			for (int i = 0; i < 9; i++) {
				if (numbers.get(i).getValue() == '0') {
					theEmpty.add(new MyData(i, numbers.get(i).getValue(), numbers.get(i).isFull()));
				}
			}

			int ind = findIndex(isFirst, isX);

			if (ind != -10) {
				buttons[ind].setGraphic(o);
				numbers.get(ind).setValue('o');
			}
		} else if (!isFirst && !isX) {
			// the computer is first
			ImageView x = new ImageView(new Image("redx.png"));
			x.setFitWidth(60);
			x.setFitHeight(60);

			theEmpty.clear();
			for (int i = 0; i < 9; i++) {
				if (numbers.get(i).getValue() == '0') {
					theEmpty.add(new MyData(i, numbers.get(i).getValue(), numbers.get(i).isFull()));
				}
			}

			int ind = findIndex(isFirst, isX);

			if (ind != -10) {
				buttons[ind].setGraphic(x);
				numbers.get(ind).setValue('x');
			}
		}

		pane.setPrefWidth(620);
		Pane pane2 = new Pane();
		re = new TextArea();
		pane2.getChildren().add(re);
		HBox hh = new HBox();
		hh.getChildren().addAll(pane, pane2);

		Scene scene = new Scene(hh, 1020, 500);
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

		if (numbers.get(index).getValue() == '0') {
			if (isX && is1) {
				// if the first player has X
				ImageView x = new ImageView(new Image("redx.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.get(index).setValue('x');
				char winner = checkWiner();

				if (winner == '0' || winner == 'f') {
					// the second player has O
					ImageView o = new ImageView(new Image("blueo.png"));
					o.setFitWidth(60);
					o.setFitHeight(60);

					int ind = findIndex(is1, isX);
					System.out.println("the new index is ==> " + ind);
					if (ind != -10) {
						buttons[ind].setGraphic(o);
						numbers.get(ind).setValue('o');
						return checkWiner();
					}
				} else
					return winner;
			} else if (!isX && is1) {
				// if the first player has o
				ImageView x = new ImageView(new Image("redo.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.get(index).setValue('o');
				char winner = checkWiner();

				if (winner == '0' || winner == 'f') {
					// the second player has x
					ImageView o = new ImageView(new Image("bluex.png"));
					o.setFitWidth(60);
					o.setFitHeight(60);

					int ind = findIndex(is1, isX);

					if (ind != -10) {
						buttons[ind].setGraphic(o);
						numbers.get(ind).setValue('x');
						return checkWiner();
					}

				} else
					return checkWiner();
			}

			if (!isX && !is1) {
				// the player is second one
				ImageView x = new ImageView(new Image("blueo.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.get(index).setValue('o');
				char winner = checkWiner();

				if (winner == '0' || winner == 'f') {
					// the first player has x
					ImageView o = new ImageView(new Image("redx.png"));
					o.setFitWidth(60);
					o.setFitHeight(60);

					theEmpty.clear();
					for (int i = 0; i < 9; i++) {
						if (numbers.get(i).getValue() == '0') {
							theEmpty.add(new MyData(i, numbers.get(i).getValue(), numbers.get(i).isFull()));
						}
					}

					int ind = findIndex(is1, isX);

					if (ind != -10) {
						buttons[ind].setGraphic(o);
						numbers.get(ind).setValue('x');
						return checkWiner();
					}

				} else
					return checkWiner();
			} else if (isX && !is1) {
				ImageView x = new ImageView(new Image("bluex.png"));
				x.setFitWidth(60);
				x.setFitHeight(60);

				buttons[index].setGraphic(x);
				numbers.get(index).setValue('x');
				char winner = checkWiner();

				if (winner == '0' || winner == 'f') {
					// the second player has x
					ImageView o = new ImageView(new Image("redo.png"));
					o.setFitWidth(60);
					o.setFitHeight(60);

					theEmpty.clear();
					for (int i = 0; i < 9; i++) {
						if (numbers.get(i).getValue() == '0') {
							theEmpty.add(new MyData(i, numbers.get(i).getValue(), numbers.get(i).isFull()));
						}
					}

					int ind = findIndex(is1, isX);

					if (ind != -10) {
						buttons[ind].setGraphic(o);
						numbers.get(ind).setValue('o');
						return checkWiner();
					}

				} else
					return checkWiner();
			}
		}
		return 'f';
	}

	private char checkWiner() {
		if (numbers.get(0).getValue() == numbers.get(1).getValue()
				&& numbers.get(2).getValue() == numbers.get(1).getValue())
			return numbers.get(0).getValue();

		if (numbers.get(3).getValue() == numbers.get(4).getValue()
				&& numbers.get(4).getValue() == numbers.get(5).getValue())
			return numbers.get(3).getValue();

		if (numbers.get(6).getValue() == numbers.get(7).getValue()
				&& numbers.get(7).getValue() == numbers.get(8).getValue())
			return numbers.get(6).getValue();

		if (numbers.get(0).getValue() == numbers.get(3).getValue()
				&& numbers.get(3).getValue() == numbers.get(6).getValue())
			return numbers.get(0).getValue();

		if (numbers.get(1).getValue() == numbers.get(4).getValue()
				&& numbers.get(4).getValue() == numbers.get(7).getValue())
			return numbers.get(1).getValue();

		if (numbers.get(2).getValue() == numbers.get(5).getValue()
				&& numbers.get(5).getValue() == numbers.get(8).getValue())
			return numbers.get(2).getValue();

		if (numbers.get(0).getValue() == numbers.get(4).getValue()
				&& numbers.get(4).getValue() == numbers.get(8).getValue())
			return numbers.get(0).getValue();

		if (numbers.get(2).getValue() == numbers.get(4).getValue()
				&& numbers.get(4).getValue() == numbers.get(6).getValue())
			return numbers.get(2).getValue();

		return 'f';
	}

	private void clear(Pane pane) {
		// clear the Tic-Tac-Toe board
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setGraphic(null);
			pane.getChildren().remove(buttons[i]);
			numbers.get(i).setValue('0'); // Reset the numbers list
		}
		setButtons(buttons, pane);
	}

	private int findIndex(boolean isFirst, boolean isX) {
		int newIndex = 0; // Initialize with an invalid index
		int bestone = Integer.MIN_VALUE;
		if (re != null)
			re.clear();
		for (int i = 0; i < 9; i++) {

			// Check if the button is empty
			if (numbers.get(i).getValue() == '0') {
				if (isFirst && isX) {
					numbers.get(i).setValue('o');
					int minmaxValue = minmxa('o', false, isFirst, isX);
					numbers.get(i).setValue('0'); // Remove the previous value

					// If the current move has a higher value, update the best move and value.
					if (minmaxValue > bestone) {
						newIndex = i;
						bestone = minmaxValue;
					}
					
					System.out.println((i + 1) + ", the result is: " + getResult(minmaxValue));
					re.setText(re.getText() + "\n" + (i + 1) + ", the result is: " + getResult(minmaxValue));
				
				} else if (isFirst && !isX) {
					numbers.get(i).setValue('x');
					int minmaxValue = minmxa('x', false, isFirst, isX);
					numbers.get(i).setValue('0'); // Remove the previous value

					// If the current move has a higher value, update the best move and value.
					if (minmaxValue > bestone) {
						newIndex = i;
						bestone = minmaxValue;
					}
					System.out.println(
							"If computer plays in position " + (i + 1) + ", the result is: " + getResult(minmaxValue));
					re.setText(re.getText() + "\n" + (i + 1) + ", the result is: " + getResult(minmaxValue));

				} else if (!isFirst && isX) {
					numbers.get(i).setValue('o');
					int minmaxValue = minmxa('o', true, isFirst, isX);
					numbers.get(i).setValue('0'); // Remove the previous value

					// If the current move has a higher value, update the best move and value.
					if (minmaxValue > bestone) {
						newIndex = i;
						bestone = minmaxValue;
					}
					System.out.println(
							"If computer plays in position " + (i + 1) + ", the result is: " + getResult(minmaxValue));
					re.setText((i + 1) + ", the result is: " + getResult(minmaxValue));

				} else if (!isFirst && !isX) {
					numbers.get(i).setValue('x');
					int minmaxValue = minmxa('x', true, isFirst, isX);
					numbers.get(i).setValue('0'); // Remove the previous value

					// If the current move has a higher value, update the best move and value.
					if (minmaxValue > bestone) {
						newIndex = i;
						bestone = minmaxValue;
					}
					System.out.println(

							"If computer plays in position " + (i + 1) + ", the result is: " + getResult(minmaxValue));
					if (re != null)
						re.setText(re.getText() + "\n" + (i + 1) + ", the result is: " + getResult(minmaxValue));
				}
			}
		}

		return newIndex;
	}

	private int minmxa(char ch, boolean isMaxValue, boolean isFirst, boolean isX) {
		if (isFirst && isX) {
			char temp = checkWiner();

			if (temp == 'x' || temp == 'o' || isFull()) {
				// If the game is over or a player has won, return the utility value
				if (isX)
					return isCompWiner('o');
				else
					return isCompWiner('x');
			}
			System.out.println("hello world okkk");

			if (isMaxValue) {
				int maxvalue = Integer.MIN_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('o');
						maxvalue = Math.max(maxvalue, minmxa(ch, false, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return maxvalue;
			} else {
				int minvalue = Integer.MAX_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('x');
						minvalue = Math.min(minvalue, minmxa(ch, true, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return minvalue;
			}

		} else if (isFirst && !isX) {
			char temp = checkWiner();

			if (temp == 'x' || temp == 'o' || isFull()) {
				// If the game is over or a player has won, return the utility value
				if (!isX)
					return isCompWiner('x');
				else
					return isCompWiner('o');
			}

			if (isMaxValue) {
				int maxvalue = Integer.MIN_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('x');
						maxvalue = Math.max(maxvalue, minmxa(ch, false, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return maxvalue;
			} else {
				int minvalue = Integer.MAX_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('o');
						minvalue = Math.min(minvalue, minmxa(ch, true, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return minvalue;
			}
		} else if (!isFirst && isX) {
			char temp = checkWiner();

			if (temp == 'x' || temp == 'o' || isFull()) {
				// If the game is over or a player has won, return the utility value
				if (isX)
					return isCompWiner('o');
				else
					return isCompWiner('x');
			}

			if (isMaxValue) {
				int maxvalue = Integer.MIN_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('o');
						maxvalue = Math.max(maxvalue, minmxa(ch, true, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return maxvalue;
			} else {
				int minvalue = Integer.MAX_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('x');
						minvalue = Math.min(minvalue, minmxa(ch, false, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return minvalue;
			}
		} else if (!isFirst && !isX) {
			char temp = checkWiner();

			if (temp == 'x' || temp == 'o' || isFull()) {
				// If the game is over or a player has won, return the utility value
				if (!isX)
					return isCompWiner('x');
				else
					return isCompWiner('o');
			}

			if (isMaxValue) {
				int maxvalue = Integer.MIN_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('x');
						maxvalue = Math.max(maxvalue, minmxa(ch, true, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return maxvalue;
			} else {
				int minvalue = Integer.MAX_VALUE;
				for (int i = 0; i < numbers.size(); i++) {
					if (numbers.get(i).getValue() == '0') {

						numbers.get(i).setValue('o');
						minvalue = Math.min(minvalue, minmxa(ch, false, isFirst, isX));
						numbers.get(i).setValue('0');
					}
				}
				return minvalue;
			}
		}
		return -10;
	}

	private boolean isFull() {
		for (MyData data : numbers) {
			if (data.getValue() == '0') {
				return false; // is not full
			}
		}
		return true; // is full
	}

	private int isCompWiner(char ch) {
		if (ch == 'o') {
			char winner = checkWiner();
			if (winner == 'o') {
				return 1; // 'o' wins
			} else if (winner == 'x') {
				return -1; // 'x' wins
			} else {
				return 0; // It's no winer
			}
		} else {
			char winner = checkWiner();
			if (winner == 'x') {
				return 1; // 'o' wins
			} else if (winner == 'o') {
				return -1; // 'x' wins
			} else {
				return 0; // It's no winer
			}
		}

	}

	private String getResult(int resultValue) {
		if (resultValue == 1) {
			return "Win";
		} else if (resultValue == -1) {
			return "Lose";
		} else {
			return "No winer";
		}
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

package project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class gamecontroller implements Initializable {

	public static int FULL = 21;
	public Card drawcard;
	public int Money;
	public int Bargining;
	public int computer_card;
	public int player_card;
	public int computer_score;
	public int player_score;

	public boolean c_close;
	public boolean u_close;

	public int[] _restnumber = new int[13];
	public ArrayList<Card> computer = new ArrayList<Card>();
	public ArrayList<Card> player = new ArrayList<Card>();
	public ArrayList<Card> Deck = new ArrayList<Card>();
	@FXML
	private TextField MoneyEnter;

	@FXML
	private Text Introduction;

	@FXML
	private Button addcard_but;

	@FXML
	private Button addMoney_but;

	@FXML
	private Text win;

	@FXML
	private Text RestMoney;

	@FXML
	private Button endaction_but;

	@FXML
	private ImageView O1;

	@FXML
	private ImageView O2;

	@FXML
	private ImageView O3;

	@FXML
	private ImageView O4;

	@FXML
	private ImageView O5;

	@FXML
	private ImageView U1;

	@FXML
	private ImageView U2;

	@FXML
	private ImageView U3;

	@FXML
	private ImageView U4;

	@FXML
	private ImageView U5;

	@FXML
	private TextField ComputerPoint;

	@FXML
	private TextField PlayerPoint;

	@FXML
	private Text Bargining_Chip;

	@FXML
	private ImageView O1B;

	public ArrayList<Card> Deck() {
		ArrayList<Card> tmpdeck = new ArrayList<Card>();
		for (int j = 0; j < 7; j++) {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 13; y++) {
					tmpdeck.add(new Card(x, y));
				}
			}
		}
		Collections.shuffle(tmpdeck);
		return tmpdeck;
	}

	public int GetScore(ArrayList<Card> arr) {
		int As = 0;
		int JQK = 0;
		int tmpScore = 0;
		var i = arr.iterator();
		while (i.hasNext()) {
			var val = i.next();
			if ((val.getnumber() + 1) == 1) {
				++As;
			} else if ((val.getnumber() + 1) > 10) {
				++JQK;
			} else {
				tmpScore += (val.getnumber() + 1);
			}
		}
		if (As == 0 && JQK == 0) {
			return tmpScore;
		}
		/* Compute */
		else {
			tmpScore += As * 11;
			tmpScore += JQK * 10;
			if (tmpScore <= FULL) {
				return tmpScore;
			} else {
				for (int i1 = 0; i1 < As + 1; i1++) {
					tmpScore -= i1 * 10;
					if (tmpScore <= FULL) {
						return tmpScore;
					}
				}
				return tmpScore;
			}

		}
	}

	public void Grading() {
		computer_score = GetScore(computer);
		player_score = GetScore(player);
		if (computer_score > FULL && player_score <= FULL) {
			userwin();
		} else if (player_score > FULL && computer_score <= FULL) {
			computerwin();
		} else {
			if (computer_score > player_score) {
				computerwin();
			} else if (computer_score == player_score) {
				tiegame();
			} else if (computer_score < player_score) {
				userwin();
			}
		}
	}

	public void tiegame() {
		Money += Bargining;
		RestMoney.setText("$" + Integer.toString(Money));
		Bargining_Chip.setText("0");
		win.setText("TIE GAME!");
		win.setVisible(true);
		reset();
	}

	public void userwin() {
		int get = 0;
		if (player_card < 5) {
			get = Bargining * 2;
			Money += get;
		} else {
			get = Bargining * 3;
			Money += get;
		}
		win.setText("YOU WIN!\nADD " + Integer.toString(get) + " Dollars");
		win.setVisible(true);
		Bargining_Chip.setText("0");
		RestMoney.setText("$" + Integer.toString(Money));
		reset();
	}

	public void computerwin() {
		int minus = 0;
		if (computer_card < 5) {
			minus = Bargining * 2;
			Money -= minus;
		} else {
			minus = Bargining * 3;
			Money -= minus;
		}
		RestMoney.setText("$" + Integer.toString(Money));
		Bargining_Chip.setText("0");
		win.setText("YOU LOSE!\nMinus " + Integer.toString(minus) + " Dollars");
		win.setVisible(true);
		reset();
	}

	public void reset() {
		player_card = 0;
		computer_card = 0;
		Bargining = 0;
		player.clear();
		computer.clear();
		MoneyEnter.setText(null);
		addMoney_but.setVisible(true);
		c_close = false;
		u_close = false;
	}

	@FXML
	void AddMoney(ActionEvent event) {

		win.setVisible(false);
		O1.setVisible(false);
		O1B.setVisible(false);
		O2.setVisible(false);
		O3.setVisible(false);
		O4.setVisible(false);
		O5.setVisible(false);
		U1.setVisible(false);
		U2.setVisible(false);
		U3.setVisible(false);
		U4.setVisible(false);
		U5.setVisible(false);
		PlayerPoint.setText(null);
		ComputerPoint.setText(null);
		Bargining = Integer.parseInt(MoneyEnter.getText());
		if (Bargining < 0) {
			win.setText("Please Enter Positive chip");
			win.setVisible(true);
		} else if ((Money - Bargining) < 0) {
			win.setText("Money is not enough!");
			win.setVisible(true);
		} else {
			addMoney_but.setVisible(false);
			addcard_but.setVisible(true);
			endaction_but.setVisible(true);
			Money -= Bargining;
			Bargining_Chip.setText("$" + Bargining);
			RestMoney.setText("$" + Money);
			// computer first time
			drawcard = draw(Deck);
			computer.add(drawcard);
			computer_card++;
			Card.GetImage(O1, drawcard);
			Image imageU3;
			try {
				imageU3 = new Image(FileSystems.getDefault()
						.getPath("src", "project", "PNG", "Back.png").toUri().toURL().toString());
				O1B.setImage(imageU3);
				O1B.setVisible(true);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			computer_score = GetScore(computer);

			// user first time
			drawcard = draw(Deck);
			player.add(drawcard);
			player_card++;
			Card.GetImage(U1, drawcard);
			player_score = GetScore(player);

			// computer second time
			drawcard = draw(Deck);
			computer.add(drawcard);
			computer_card++;
			Card.GetImage(O2, drawcard);
			computer_score = GetScore(computer);

			// user second time
			drawcard = draw(Deck);
			player.add(drawcard);
			player_card++;
			Card.GetImage(U2, drawcard);

			player_score = GetScore(player);
			PlayerPoint.setText(Integer.toString(player_score));
			if (player_score == FULL) {
				EndAction(event);
			}
		}
	}

	public Card draw(ArrayList<Card> Deck) {
		Card drawcard = Deck.remove(0);
		_restnumber[drawcard.getnumber()] -= 1;
		return drawcard;
	}

	@FXML
	void addCard(ActionEvent event) {
		if (u_close == false) {
			drawcard = draw(Deck);
			player.add(drawcard);
			player_card++;
			player_score = GetScore(player);
			PlayerPoint.setText(Integer.toString(player_score));
			switch (player_card) {
				case 3:
					Card.GetImage(U3, drawcard);
					break;
				case 4:
					Card.GetImage(U4, drawcard);

					break;
				case 5:
					Card.GetImage(U5, drawcard);
					break;
				default:
					break;
			}
			player_score = GetScore(player);
			PlayerPoint.setText(Integer.toString(player_score));
			if (player_card < 5) {
				if (player_score >= FULL) {
					u_close = true;
					EndAction(event);
				}
			} else if (player_card == 5) {
				if (player_score < FULL) {
					u_close = true;
					userwin();
				} else {
					u_close = true;
					computerwin();
				}
			}
		}
	}

	@FXML
	void EndAction(ActionEvent event) {
		addcard_but.setVisible(false);
		endaction_but.setVisible(false);
		u_close = true;
		// detect end_action and computer draw
		O1B.setVisible(false);
		while (c_close == false) {
			computer_score = GetScore(computer);
			if (computer_card < 5) {
				if (computer_score < FULL) {
					if (computer_score <= player_score) {
						drawcard = draw(Deck);
						computer.add(drawcard);
						computer_card++;
						switch (computer_card) {
							case 3:
								Card.GetImage(O3, drawcard);
								break;
							case 4:
								Card.GetImage(O4, drawcard);
								break;
							case 5:
								Card.GetImage(O5, drawcard);
								break;
							default:
								break;
						}
					} else {
						c_close = true;
					}
				} else {
					c_close = true;
				}
			} else {
				c_close = true;
			}
		}
		computer_score = GetScore(computer);
		ComputerPoint.setText(Integer.toString(computer_score));
		Grading();
		MoneyDetect();
	}

	public int possible(int _rest) {
		int posi = 0;
		int total = 0;
		int possibility;
		if (_rest > 13) {
			_rest = 13;
		}
		for (int i = 0; i < _rest; i++) {
			posi += _restnumber[i];
		}
		for (int j : _restnumber) {
			total += _restnumber[j];
		}
		possibility = (int) (((float) posi / total) * 100);
		return possibility;
	}

	public void exit() {
		try {
			Parent exit = FXMLLoader.load(getClass().getResource("exit.fxml"));
			Scene ExitScene = new Scene(exit);
			ExitScene.getRoot().requestFocus();
			project.currentStage.setScene(ExitScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void MoneyDetect() {
		if (Money <= 0) {
			win.setText("Game Over");
			win.setVisible(true);
			PauseTransition wait = new PauseTransition(Duration.seconds(4));
			wait.setOnFinished((e) -> {
				exit();
			});
			wait.play();
		}
	}

	@FXML
	void ExitAction(ActionEvent event) throws Exception {
		win.setText("You rest " + Money + " dollars");
		win.setVisible(true);
		PauseTransition wait = new PauseTransition(Duration.seconds(4));
		wait.setOnFinished((e) -> {
			exit();
		});
		wait.play();

	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		Money = 10000;
		Bargining = 0;
		computer_card = 0;
		player_card = 0;
		computer_score = 0;
		player_score = 0;
		Arrays.fill(_restnumber, 28);
		Deck = Deck();
		RestMoney.setText("$" + Integer.toString(Money));
		c_close = false;
		u_close = false;
		win.setVisible(false);
		O1.setVisible(false);
		O1B.setVisible(false);
		O2.setVisible(false);
		O3.setVisible(false);
		O4.setVisible(false);
		O5.setVisible(false);
		U1.setVisible(false);
		U2.setVisible(false);
		U3.setVisible(false);
		U4.setVisible(false);
		U5.setVisible(false);
	}
}

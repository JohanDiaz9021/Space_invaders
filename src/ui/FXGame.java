package ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Alien;
import model.AttackShip;
import model.Easy;
import model.Hard;
import model.Level;
import model.SpaceInvader;
import model.Spacecraft;
import model.TypeSpacecraft;
import thread.AlienThread;
import thread.BulletsThread;
import thread.SearchAlienThread;

public class FXGame {
	private Circle currentCircle;
	private double bulletsX;
	private Level lvl;
	private double bulletsY;
	private int shootAliens;
	private Spacecraft ship;
	private double positionBallY;
	private Rectangle currentRec;
	public final static int POSTITIONALIENTX = 79;

	public final static int POSTITIONALIENTY = 62;

	public final static int VELOCITY = 1200;

	public final static int VELOCITYSLOW = 2600;
	private String dificult;
	private int normalMovement;
	private double ballInMoveY;
	private Stage window;
	private boolean verify;
	private boolean knowShoot;
	private double ballInMoveX;
	private double positionBallX;
	private long count;
	private long currentCount;
	private int scores;
	@FXML
	private ImageView imageBackGround;
	private Easy easy;

	private int velocityLevel;
	private Hard hard;
	@FXML
	private ImageView mainShip;

	@FXML
	private Circle circle;

	@FXML
	private Label score;


	@FXML
	private Rectangle bullet;
	private Alien firstAlien;



	@FXML
	void moveShip(KeyEvent event) throws InterruptedException {
		count = System.currentTimeMillis();
		if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) && ship.getPosX() >= 12) {
			ship.moveLeft();

		}
		if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && ship.getPosX() <= 462) {
			ship.moveRight();

		}
		if (ship.getShip() == TypeSpacecraft.ATTACK_SHIP) {

			if ((event.getCode().equals(KeyCode.SPACE)|| event.getCode() == KeyCode.W) && currentCount < count - VELOCITY) {
				currentCount = count;
				Circle circles = new Circle();
				knowShoot = true;
				circles.setLayoutX(positionBallX);
				circles.setLayoutY(positionBallY);
				circles.setRadius(circle.getRadius());
				welcome.getChildren().add(circles);
				moveBall(circles);
			}

		} else if (ship.getShip() == TypeSpacecraft.RECOGNITION_SHIP) {

			if ((event.getCode().equals(KeyCode.SPACE) || event.getCode() == KeyCode.W)
					&& currentCount < count - VELOCITYSLOW) {
				currentCount = count;
				knowShoot = true;
				Circle circles = new Circle();
				circles.setLayoutX(positionBallX);
				circles.setLayoutY(positionBallY);
				circles.setRadius(circle.getRadius());
				welcome.getChildren().add(circles);
				moveBall(circles);
			}
		}

		mainShip.setLayoutX(ship.getPosX());
	}

	
	
	
	@FXML
	private BorderPane welcome;

	private SpaceInvader spaceInvade;

	public FXGame(SpaceInvader spaceInvader, Stage primaryStage, String dificultad, int numberAliens) {
		this.spaceInvade = spaceInvader;
		firstAlien = null;
		dificult = dificultad;
		window = primaryStage;
		velocityLevel = 0;
		normalMovement = 10;
		knowShoot = false;
		ballInMoveX = 0;
		ballInMoveY = 0;
		verify = true;
		scores = 0;
		shootAliens = 0;
		lvl = new Level(numberAliens);
		currentCount = 0;
		count = System.nanoTime();
	}

	public void load() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("game-pane.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		Image image = new Image("images/FondoJuego.jpg");

		imageBackGround.setImage(image);

		Image imageShip = new Image("images/Jugador.png");
		mainShip.setImage(imageShip);
		mainShip.setVisible(true);

		ship = new AttackShip(TypeSpacecraft.ATTACK_SHIP, mainShip.getLayoutX(), VELOCITY);
		ship.setPosY(mainShip.getLayoutY());
		welcome.getChildren().clear();
		welcome.setTop(load);
		circle.setVisible(false);
		bullet.setVisible(false);
		bulletsX = 0;
		bulletsY = 0;
		positionBallX = circle.getLayoutX();
		positionBallY = circle.getLayoutY();

		createMatrix(POSTITIONALIENTX, POSTITIONALIENTY);

		int atackSpeed = 0;

		if (dificult.equals("leyenda") && hard != null) {
			atackSpeed = hard.getAttackSpeed();

		} else {
			atackSpeed = 1500;
		}
		BulletsThread bulletThread = new BulletsThread(this, firstAlien, verify, atackSpeed,spaceInvade.getCuantityAlins());
		bulletThread.start();

		mainShip.setVisible(true);

	}

	public void createMatrix(int x, int y) {

		Image image1 = new Image("images/firstAlien.png");
		Image image2 = new Image("images/secondAlien.png");

		int contX = -79;
		int contY = 62;
		int i = 0;

		if (firstAlien == null) {
			firstAlien = new Alien(x, y, contX, contY, image1, image2);

			if (dificult.equals("novato")) {
				firstAlien.setMove(easy.getMovementSpeed() + velocityLevel);

			} else if (dificult.equals("leyenda")) {
				firstAlien.setMove(hard.getMovementSpeed() + velocityLevel);

			} else {
				firstAlien.setMove(normalMovement + velocityLevel);
			}
			createMatrix(x, y);
			moveAlien(firstAlien);
		} else {
			createMatrix(x, y, contX, contY + 100, image1, image2, firstAlien, i,spaceInvade.getCuantityAlins()-1);
		}
	}

	public void createMatrix(int x, int y, int contX, int contY, Image image1, Image image2, Alien current, int i,int cuantity) {
		
	cuantity--;
		Alien alien = new Alien(POSTITIONALIENTX, POSTITIONALIENTY, contX, contY, image1, image2);

		if (dificult.equals("novato")) {
			alien.setMove(easy.getMovementSpeed() + velocityLevel);

		} else if (dificult.equals("leyenda")) {
			alien.setMove(hard.getMovementSpeed() + velocityLevel);

		} else {
			alien.setMove(normalMovement + velocityLevel);
		}

		if (current != null && current.getDown() == null && i < lvl.getAliens()) {

			moveAlien(alien);
			current.setDown(alien);
			current.getDown().setUp(current);
			if (current.getPrev() != null) {
				current.getDown().setPrev(current.getPrev().getDown());
			}
			
				createMatrix(x, y, contX + 100, contY - 100, image1, image2, current, i,cuantity);
			

		} else if (current != null && current.getNext() == null) {

			if (i < lvl.getAliens() - 1) {
				moveAlien(alien);
				current.setNext(alien);
				current.getNext().setPrev(current);
				
					createMatrix(x, y, contX, contY + 100, image1, image2, current.getNext(), ++i,cuantity);
				
			} else {
				current.setNext(alien);
				Alien alien1 = new Alien(POSTITIONALIENTX, POSTITIONALIENTY, contX, contY, image1, image2);
				current.getNext().setDown(alien1);
				current.getDown().setPrev(current.getPrev().getDown());
			}

		}
	}

	public void moveAlien(Alien alien) {

		ImageView alienImageView = new ImageView();

		alienImageView.setVisible(true);

		alienImageView.setImage(alien.getImageOne());

		welcome.getChildren().add(alienImageView);

		alienImageView.setFitWidth(alien.getX());
		alienImageView.setFitHeight(alien.getY());

		alienImageView.setLayoutX(alien.getPositionX());
		alienImageView.setLayoutY(alien.getPositionY());

		AlienThread thread = new AlienThread(this, alien, alienImageView, verify);

		thread.start();

		window.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				verify = false;
			}
		});
	}

	public void updateAlien(double y, double x, ImageView alienImageView) {
		alienImageView.setLayoutX(x);
		alienImageView.setLayoutY(y);
	}

	public void searchAlien(double x, double y, ImageView image, Alien aliens) {
		SearchAlienThread alien = new SearchAlienThread(this, x, y, ballInMoveX, ballInMoveY, image, verify, knowShoot,
				currentCircle, aliens);
		alien.start();

	}

	public synchronized void validationShip(ImageView alienImageView) throws IOException {

		if ((ship.getPosX() > alienImageView.getLayoutX() - 73 && ship.getPosX() < alienImageView.getLayoutX() + 73)
				&& (ship.getPosY() > alienImageView.getLayoutY() - 60
						&& ship.getPosY() < alienImageView.getLayoutY() + 45)
				&& alienImageView.isVisible()) {

			mainShip.setVisible(false);
			gameOver();
		}
	}

	public void validationPosition(Alien alien, ImageView alienImageView) throws IOException {

		if (alien.getPositionY() >= window.getHeight() - 105 && alienImageView.isVisible()) {

			gameOver();
		}
	}

	public synchronized void validationBullets() throws IOException {

		if ((bulletsX > mainShip.getLayoutX() - 90 && bulletsX < mainShip.getLayoutX() + 90)
				&& (bulletsY > mainShip.getLayoutY() - 32 && bulletsY < mainShip.getLayoutY() + 20)
				&& mainShip.isVisible() && currentRec.isVisible()) {

			currentRec.setVisible(false);
			mainShip.setVisible(false);
			welcome.getChildren().clear();
			gameOver();

		}
	}

	@FXML
	public void gameOver() throws IOException {
		System.out.println("Ganaste");
		verify = false;

		//welcome.getChildren().clear();

		//FXMLLoader loader = new FXMLLoader(getClass().getResource("gameover-pane.fxml"));

		//loader.setController(this);
		//Parent load = loader.load();
		bullet.setVisible(false);
		// Image image = new Image("/images/insertName.png");
		// insertName.setImage(image);
		// Image image2 = new Image("/images/gameOver.png");
		// gameOver.setImage(image2);
		// Image image3 = new Image("/images/backGround.png");
		// backGround.setImage(image3);

		// mainPane.setTop(load);

		// scoreOver.setText(String.valueOf(scores));
	}

	public boolean getVerify() {
		return verify;
	}

	public void setImage(ImageView image, Alien current) {
		image.setVisible(false);
		current.setVisible(false);
	}

	public synchronized void setCircle(Circle circ) {
		circ.setVisible(false);
	}

	public void setScores(int aliens) {
		shootAliens += aliens;
		scores += 5;
		score.setText(String.valueOf(scores));
	}

	public void setLevels() throws IOException {
	int num =spaceInvade.getCuantityAlins()*2;
		if (shootAliens % num  == 0) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
			
			fxmlLoader.setController(game);
			
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.setTitle("Space_invader");
			Image icon = new Image("/images/Title.png");
			window.getIcons().add(icon);
			window.setResizable(false);
			window.show();
			window.load();

		}
	}

	public double getBallInMoveX() {
		return ballInMoveX;
	}

	public double getBallInMoveY() {
		return ballInMoveY;
	}

	public boolean getKnowShoot() {
		return knowShoot;
	}

	public synchronized void moveBalls(Circle circles) {

		new Thread() {
			public void run() {
				while (circles.getLayoutY() > -15 && verify) {
					Platform.runLater(new Thread() {
						public void run() {
							circles.setLayoutY(circles.getLayoutY() - 5);
							currentCircle = circles;
							ballInMoveX = circles.getLayoutX();
							ballInMoveY = circles.getLayoutY();
						}
					});
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {

					}
				}
			}
		}.start();

	}

	public synchronized void selectAlien(Alien current) throws InterruptedException {

		Rectangle rectangles = new Rectangle();
		rectangles.setLayoutX(bullet.getLayoutX());
		rectangles.setLayoutY(bullet.getLayoutY());
		rectangles.setWidth(bullet.getWidth());
		rectangles.setHeight(bullet.getHeight());
		currentRec = rectangles;

		welcome.getChildren().add(rectangles);

		rectangles.setVisible(true);
		moveBullet(rectangles, current);
	}

	public void moveBullet(Rectangle bullets, Alien alien) throws InterruptedException {

		if (alien != null && alien.getVisible()) {

			bullets.setLayoutX(alien.getPositionX() + 40);
			bullets.setLayoutX(alien.getPositionY() - 10);

			bullets.setFill(javafx.scene.paint.Color.BLUE);
			bullets.setLayoutX(alien.getPositionX());
			bullets.setLayoutY(alien.getPositionY() + 30);

			moveRectangles(bullets, alien);
		}

		window.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				verify = false;
			}
		});

	}

	public synchronized void moveRectangles(Rectangle bullets, Alien alien) {

		if (alien.getVisible()) {

			new Thread() {
				public void run() {
					while (bullets.getLayoutY() < window.getHeight() + 20 && verify) {
						Platform.runLater(new Thread() {
							public void run() {
								bullets.setLayoutY((bullets.getLayoutY() + 5));
								bulletsX = bullets.getLayoutX();
								bulletsY = bullets.getLayoutY();
							}
						});
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {

						}
					}
				}
			}.start();
		}

	}
	public void moveBall(Circle circles) throws InterruptedException {
		circles.setVisible(true);
		circles.setFill(javafx.scene.paint.Color.WHITE);
		circles.setLayoutX(ship.getPosX() + 40);

		window.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				verify = false;
			}
		});

		moveBalls(circles);
	}

	

}

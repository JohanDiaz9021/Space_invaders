package model;

import javafx.scene.image.Image;

public class Alien {

	public final static int MAXRIGHT = 150;
	public final static int MAXLEFT = 79;
	
	private int move;
	private double x;
	private double y;
	private double positionX;
	private double positionY;
	private Direction direction;
	private Image imageOne;
	private Image imageTwo;
	private Alien down;
	private Alien up;
	private Alien next;
	private Alien prev;
	private boolean visible;
	
	
	public Alien(double x, double y, double positionX, double positionY, Image imageOne, Image imageTwo) {
		
		this.x = x;
		this.y = y;
		this.positionX = positionX;
		this.positionY = positionY;
		direction = Direction.RIGHT;
		this.imageOne = imageOne;
		this.imageTwo = imageTwo;
		move = 0;
		setVisible(true);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
	
	public void moveAlien() {
		
		if(direction == Direction.LEFT) {
			x -= move;
		}else {
			x += move;
		}
		
		verify();
	}
	
	
	
	public void verify() {
		
		if(x >= MAXRIGHT) {
			positionY += move;
			changeDirection();
		}
		if(x <= MAXLEFT) {
			positionY += move;
			changeDirection();
		}
	}
	
	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	
	
	public void changeDirection() {
		
		if(direction == Direction.LEFT) {
			direction = Direction.RIGHT;
		}else {
			direction = Direction.LEFT;
		}
	}
	
	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public Image getImageOne() {
		return imageOne;
	}

	public Image getImageTwo() {
		return imageTwo;
	}

	public Alien getDown() {
		return down;
	}

	public void setDown(Alien down) {
		this.down = down;
	}

	public Alien getUp() {
		return up;
	}

	public void setUp(Alien up) {
		this.up = up;
	}

	public Alien getNext() {
		return next;
	}

	public void setNext(Alien next) {
	this.next = next;
	}

	public Alien getPrev() {
		return prev;
	}

	public void setPrev(Alien prev) {
		this.prev = prev;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}



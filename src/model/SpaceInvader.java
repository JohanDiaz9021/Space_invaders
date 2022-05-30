package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exceptions.SpaceInNickException;

public class SpaceInvader {
	public final static String SAVE_PLAYERS = "data/dataPlayer.txt";
	private String namePlayerOf ; 
	private int cuantityAlins;
	private Player first;
	public void addPeople(String name,int aliens)  {
		
		setNamePlayerOf(name);
		setCuantityAlins(aliens);
	}
	public int getCuantityAlins() {
		return cuantityAlins;
	}
	public void setCuantityAlins(int cuantityAlins) {
		this.cuantityAlins = cuantityAlins;
	}
	public String getNamePlayerOf() {
		return namePlayerOf;
	}
	public void setNamePlayerOf(String namePlayerOf) {
		this.namePlayerOf = namePlayerOf;
	}
	public void addPlayer(String nick, int score) throws FileNotFoundException, IOException, SpaceInNickException {

		

		Player player = new Player(namePlayerOf, nick,score);

		if(first == null) {
			first = player;
			System.out.println(first.getName());

		}else {
			boolean stop = true;
			Player current = first;

			while(stop) {

				if(player.getScore() < current.getScore()) {

					if(current.getPrev() == null) {
						current.setPrev(player);
						player.setParent(current);
						stop = false;

					}else {
						current = current.getPrev();
					}

				}else {
					if(current.getNext() == null) {
						current.setNext(player);
						player.setParent(current);
						stop = false;
					}
					else {
						current = current.getNext();
					}
				}
			}
		}
		saveData();
	}
	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PLAYERS));
		oos.writeObject(first);

		oos.close();
	}
	public boolean loadData() {
		System.out.println("entra a load data");
		boolean verify = true;

		File players = new File(SAVE_PLAYERS);

		if(players.exists()){

			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(players));
				first = (Player) ois.readObject();

				ois.close();
			}catch(ClassNotFoundException | IOException r) {
				verify = false;
			}
		}
		return verify;
	}
	private Player firstBest;
	public ArrayList<Player> searchPodium() {

		boolean stop = false;
		Player player = null;
		int cont = 2;
		Player current = null;
		int verify = 0;
		
		ArrayList<Player> newlist = new ArrayList<>();

		ArrayList<Player> listPlayer = toArrayList();

		if(!listPlayer.isEmpty() && listPlayer.get(listPlayer.size()-1) != null) {
			firstBest = new Player(listPlayer.get(listPlayer.size()-1).getName(), listPlayer.get(listPlayer.size()-1).getNick(), listPlayer.get(listPlayer.size()-1).getScore());
			current = firstBest;
			newlist.add(firstBest);
		}

		while(cont <= 5 && !listPlayer.isEmpty()) {

			stop = true;

			while(stop  && listPlayer.size() > (cont-1) && listPlayer.get(listPlayer.size()-cont) != null && verify != cont-1) {
				
				player = new Player(listPlayer.get(listPlayer.size()-cont).getName(), listPlayer.get(listPlayer.size()-cont).getNick(), listPlayer.get(listPlayer.size()-cont).getScore());

				verify = cont-1;
				
				newlist.add(player);
				
				if(compareTwoPlayers(player, current) < 0) {

					if(current.getPrev() == null) {
						current.setPrev(player);
						player.setParent(current);
						stop = false;

					}else {
						current = current.getPrev();
					}

				}else {
					if(current.getNext() == null) {
						current.setNext(player);
						player.setParent(current);
						stop = false;
					}
					else {
						current = current.getNext();
					}
				}
			}
			
			cont++;
		}

		return newlist;
	}
	public ArrayList<Player> toArrayList() {

		ArrayList<Player> arrayPlayer = new ArrayList<>();
		
		if(first != null) {
			Player current = first;

			searchPlayers(current, arrayPlayer);

		}
		return arrayPlayer;
	}
	public int compareTwoPlayers(Player playerOne, Player playerTwo) {

		int verify = 0;

		if(playerOne.getScore() > playerTwo.getScore()) {
			verify = 1;
		}else if(playerOne.getScore() < playerTwo.getScore()) {
			verify = -1;
		}
		

		return verify;
	}
	public void searchPlayers(Player player, ArrayList<Player> arrayPlayer) {

		if(player != null) {
			searchPlayers(player.getPrev(),arrayPlayer);
			arrayPlayer.add(player);
			searchPlayers(player.getNext(),arrayPlayer);
		}
	}
}

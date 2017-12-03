package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;
import logic.Inventary;
import logic.Player;

public class MyWorld extends World {
	
	Vector2f where2draw;
	protected Image background;
	public Camera camera;
	protected boolean showInvent = false;
	protected Inventary invent;
	public Player player;
	public int hours = 12;
	public int minutes = 0;
	public int sec = 0;
	private int day = 0;
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public MyWorld(int id, Player player) {
		super(id);
		try {
			this.player = new Player(0,0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		invent = player.invent;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		if(camera!=null)camera.draw(container, g);
		if(background!=null)background.draw(0, 0);
		if(showInvent)invent.render(container, g);
		if(hours<=12) g.drawString(days[day]+" "+hours+":"+minutes+" am", player.x+140, player.y-240);
		else g.drawString(days[day]+" "+ (hours-12)+":"+minutes+" pm", player.x+140, player.y-240);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key==Input.KEY_TAB) showInvent = !showInvent; 
		player.invent.keyPressed(key);
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		sec++;
		
		if(sec>=60) {
			minutes++;
			sec=0;
		}
		if(minutes>=60) {
			hours++;
			minutes=0;
		}
		if(hours>=24){
			hours=0;
			day++;
		}
		if(day>6) day = 0;
		player.invent.update(container, delta);
	}
	
}
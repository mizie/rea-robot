package com.rea.toyrobot.simulator;



public class Robot {
	public static final String DIRECTION_NORTH = "NORTH";
	public static final String DIRECTION_EAST = "EAST";
	public static final String DIRECTION_WEST = "WEST";
	public static final String DIRECTION_SOUTH = "SOUTH";
	
	
	
	protected int x;
	protected int y;
	protected String facing;
	protected Board theBoard;

	public Robot(Board theBoard) {
		this.x = -1;
		this.y = -1;
		this.facing = null;
		this.theBoard = theBoard;

	}

	public boolean isPlace() {
		if (this.x >= 0 && this.y >= 0) {
			return true;
		}
		return false;
	}
	
	public boolean isValidCommand(String command){
		if ("".equals(command)){
			return false;
		}
		switch (command){
			case Robot.DIRECTION_NORTH :
				return true;
			case Robot.DIRECTION_EAST :
				return true;
			case Robot.DIRECTION_SOUTH :
				return true;
			case Robot.DIRECTION_WEST :
				return true;
		}
		return false;
	}

	public boolean place(int x, int y, String facing) {
		
		if (theBoard.canPlace(x, y)) {
			this.x = x;
			this.y = y;
			this.facing = facing;
			return true;
		}

		return false;
	}

	public boolean move() {
		
		if (!isPlace()){
			return false;
		}
		
		if (theBoard.canMove(x, y, facing)) {
			if (this.facing.equals(DIRECTION_NORTH)) {
				y++;
				return true;
			} else if (this.facing.equals(DIRECTION_EAST)) {
				x++;
				return true;
			} else if (this.facing.equals(DIRECTION_WEST)) {
				x--;
				return true;
			} else if (this.facing.equals(DIRECTION_SOUTH)) {
				y--;
				return true;
			}
		}
		return false;
	}

	public void rotateLeft() {
		
		if (!isPlace()){
			return;
		}
		
		if (this.facing.equals(DIRECTION_NORTH)) {
			this.facing = DIRECTION_WEST;
		} else if (this.facing.equals(DIRECTION_EAST)) {
			this.facing = DIRECTION_NORTH;
		} else if (this.facing.equals(DIRECTION_SOUTH)) {
			this.facing = DIRECTION_EAST;
		} else if (this.facing.equals(DIRECTION_WEST)) {
			this.facing = DIRECTION_SOUTH;
		}
	}

	public void rotateRight() {
		if (!isPlace()){
			return;
		}
		if (this.facing.equals(DIRECTION_NORTH)) {
			this.facing = DIRECTION_EAST;
		} else if (this.facing.equals(DIRECTION_EAST)) {
			this.facing = DIRECTION_SOUTH;
		} else if (this.facing.equals(DIRECTION_SOUTH)) {
			this.facing = DIRECTION_WEST;
		} else if (this.facing.equals(DIRECTION_WEST)) {
			this.facing = DIRECTION_NORTH;
		}
	}

	public String report() {
		return "x=" + x + ", y=" + y + ",facing=" + facing;
	}

}

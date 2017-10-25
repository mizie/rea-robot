package com.rea.toyrobot.simulator;

public class Board {

	private int row; //y
	private int column; //x
	
	
	public Board(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param facing
	 * @return
	 */
	public boolean canMove(int x,int y, String facing){
		if (facing.equals(Robot.DIRECTION_NORTH)){
			if (y == this.row){
				return false;
			}
		  return true;
		} else if (facing.equals(Robot.DIRECTION_EAST)){
			if (x == this.column){
				return false;
			}
			return true;
		} else if (facing.equals(Robot.DIRECTION_SOUTH)){
			if (y== 0){
				return false;
			}
			return true;
		} else if (facing.equals(Robot.DIRECTION_WEST)){
			if (x== 0){
				return false;
			}
			return true;
		}
		throw new IllegalArgumentException();
	}
	
	public boolean canPlace(int x,int y){
		if (x<0){
			return false;
		} 
		if (y<0){
			return false;
		}
		if (x>this.column){
			return false;
		}
		if (y> this.row){
			return false;
		}
		
		return true;
	}
	
}

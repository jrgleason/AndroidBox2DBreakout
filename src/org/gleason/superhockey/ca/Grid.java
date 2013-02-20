package org.gleason.superhockey.ca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Grid {
	// private List<Cell> cells = new ArrayList<Cell>();
	private List<List<Cell>> grid = new ArrayList<List<Cell>>();
	private Vector2 location;

	public Grid(int length, int height, boolean isRandom) {
		// -1 since itteration is 0 based
		Random random = new Random();
		for (int y = 0; y < height; y++) {
			List<Cell> cells = new ArrayList<Cell>();
			for (int x = 0; x < length; x++) {
				Cell currentCell = new Cell();
				currentCell.setState(true);
				if(isRandom){
					currentCell.setState(random.nextBoolean());
				}
				cells.add(currentCell);
			}
			grid.add(cells);
		}
	}

	public void itterateGeneration() {

//		for (int y = 1; y < grid.size(); y++) {
//			List<Cell> cells = grid.get(y);
//			Boolean[] newValues = new Boolean[cells.size()];
//			for (int x = 1; x < cells.size(); x++) {
//				List<Cell> neighborhood = new ArrayList<Cell>();
//				// lower neighbor
//				neighborhood.add(grid.get(y + 1).get(x));
//				// Upper neighbor
//				neighborhood.add(grid.get(y - 1).get(x));
//				// right neighbor
//				neighborhood.add(grid.get(y).get(x + 1));
//				// left neighbor
//				neighborhood.add(grid.get(y).get(x + 1));
//				// lower-right neighbor
//				neighborhood.add(grid.get(y + 1).get(x + 1));
//				// lower-right neighbor
//				neighborhood.add(grid.get(y + 1).get(x + 1));
//				// lower-left neighbor
//				neighborhood.add(grid.get(y + 1).get(x - 1));
//				// Top Left Neighbor
//				neighborhood.add(grid.get(y - 1).get(x - 1));
//				// Top Right Neighbor
//				neighborhood.add(grid.get(y - 1).get(x + 1));
//				grid.get(y).get(x).setState(applyRules(neighborhood));
//			}
//		}
	}

	private boolean applyRules(List<Cell> cells) {
		int livingCells = 0;
		for (Cell c : cells) {
			livingCells = livingCells + checkCell(c);
		}
		if (livingCells > 3) {
			return false;
		} else {
			return true;
		}

	}

	private int checkCell(Cell c) {
		return c.isState() ? 1 : 0;
	}

	// Removing 1D
	// private boolean runRulesOnState(Cell current, Cell other) {
	// int val;
	// if (other.isState() && current.isState())
	// val = edgeruleset[0];
	// else if (other.isState() && !current.isState())
	// val = edgeruleset[1];
	// else if (!other.isState() && current.isState())
	// val = edgeruleset[2];
	// else if (!other.isState() && !current.isState())
	// val = edgeruleset[3];
	// else
	// val = 0;
	// if (val == 0) {
	// return false;
	// } else {
	// return true;
	// }
	// }
	//
	// private boolean runRulesOnState(Cell current, Cell left, Cell right) {
	// int val;
	// if (left.isState() && current.isState() && right.isState())
	// val = ruleset[0];
	// else if (left.isState() && current.isState() && !right.isState())
	// val = ruleset[1];
	// else if (left.isState() && !current.isState() && right.isState())
	// val = ruleset[2];
	// else if (left.isState() && !current.isState() && !right.isState())
	// val = ruleset[3];
	// else if (!left.isState() && current.isState() && right.isState())
	// val = ruleset[4];
	// else if (!left.isState() && current.isState() && !right.isState())
	// val = ruleset[5];
	// else if (!left.isState() && !current.isState() && right.isState())
	// val = ruleset[6];
	// else if (!left.isState() && !current.isState() && !right.isState())
	// val = ruleset[7];
	// else
	// val = 0;
	// boolean test = val == 1;
	// return test;
	// }
	//
	// // int[] ruleset = {0,1,0,1,1,0,1,0};
	// int[] ruleset = { 1, 1, 1, 1, 1, 1, 1, 0 };
	// int[] edgeruleset = { 0, 0, 0, 0 };

	public float getGridWidth() {
		float totalLength = 0;
		int count = 0;
		for (List<Cell> cells : grid) {
			for (Cell c : cells) {
				totalLength = totalLength + (float) c.getWidth();
				// Need midpoint for location not end
				if (count == 0 || count == (cells.size() - 1)) {
					totalLength = totalLength - (float) (c.getWidth() / 2);
				}
				count++;
			}
		}
		return totalLength;
	}
	
	public float getGridHeight() {
		float totalLength = 0;
		int count = 0;
		for (List<Cell> cells : grid) {
			if(cells.size()>0){
				Cell c = cells.get(0);
				totalLength = totalLength + (float) c.getHeight();
				// Need midpoint for location not end
				if (count == 0 || count == (cells.size() - 1)) {
					totalLength = totalLength - (float) (c.getHeight() / 2);
				}
				count++;
			}
		}
		return totalLength;
	}

//	/**
//	 * @return the cells
//	 */
//	public List<Cell> getCells() {
//		return cells;
//	}
//
//	/**
//	 * @param cells
//	 *            the cells to set
//	 */
//	public void setCells(List<Cell> cells) {
//		this.cells = cells;
//	}

	/**
	 * @return the location
	 */
	public Vector2 getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Vector2 location) {
		this.location = location;
	}

	/**
	 * @return the grid
	 */
	public List<List<Cell>> getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 *            the grid to set
	 */
	public void setGrid(List<List<Cell>> grid) {
		this.grid = grid;
	}
}

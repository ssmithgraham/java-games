package snake;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

public class snake implements DrawListener {
//Review 'extends'/'super'
	private Draw draw;
	private snakeFood yummy;
	public int direction;
	public int x;
	public int y;
	public int size;

	public snake() {
		draw = new Draw();
		draw.setCanvasSize(500, 500);
		draw.setXscale(0, 500);
		draw.setYscale(0, 500);
		size = 10;

		draw.addListener(this);
		draw.startUpdate();

		this.x = 250;
		this.y = 250;
		this.direction = 1;

		draw.enableDoubleBuffering();
	}

	@Override
	public void keyPressed(int arg0) {

	}

	@Override
	public void keyReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(char key) {

		if (key == 'w' || key == 'W') {
			if (direction == 2) {
				direction = 2;
			} else {
				direction = 1;
			}
		}
		if (key == 's' || key == 'S') {
			if (direction == 1) {
				direction = 1;
			} else {
				direction = 2;
			}
		}
		if (key == 'd' || key == 'D') {
			if (direction == 4) {
				direction = 4;
			} else {
				direction = 3;
			}
		}
		if (key == 'a' || key == 'A') {
			if (direction == 3) {
				direction = 3;
			} else {
				direction = 4;
			}
		}
	}


	@Override
	public void mouseClicked(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	public void grow() {

	}

	@Override
	public void update() {
		draw.pause(100);

		if (direction == 1) { // Up
			y += size * 2;
		}
		if (direction == 2) { // Down
			y -= size * 2;
		}
		if (direction == 3) { // right
			x += size * 2;
		}
		if (direction == 4) { // left
			x -= size * 2;
		}

		draw.clear();
		draw.filledRectangle(this.x, this.y, size, size);
	//	yummy.spawnFood();
		// snakeList.draw();
		draw.show();

	}

	public class snakeFood {

		private double x;
		private double y;
		// private Draw draw;

		public snakeFood() {
			// draw = new Draw();

			x = Math.random()*500;
			y = Math.random()*500;
			System.out.println(x + " " + y);
		//	draw.filledCircle(x, y, 10.0);
		}

		public void spawnFood() {
			draw.filledCircle(x, y, 10.0);
		}
	}

}

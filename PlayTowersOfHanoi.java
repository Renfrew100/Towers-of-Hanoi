import java.util.Scanner;

public class PlayTowersOfHanoi {
	public static void main (String[] args ) {
		//% Ask user how many disks (up to 6)
		Scanner obj=new Scanner (System.in); 
		System.out.println("How many disks to add to the game?");
		int n =obj.nextInt(); 
		System.out.println("Demo (D) or play game (P)?");
		Scanner s=new Scanner (System.in);
		String play=s.nextLine();

		//create new instance of towersofHanoi, show what's inside the rods
		TowersOfHanoi game = new TowersOfHanoi(n);
		//System.out.println(game.toString());
		game.showTowerStates();

		if (play.equals("D")) { //for a demo call solveGame method 
			game.solveGame();
		}

		else if (play.equals("P")) {//play the game
			//lost the game after a maximum of 2^n-1 legal moves 
			int maxMoves= (int)Math.pow(2, n)-1;
			//ask the user for input until reached number of maximum moves
			for (int i=0; i<maxMoves; i++) {
				System.out.println("How many disks would you like to move(max 6)? Enter 0 for solution");
				int m=obj.nextInt(); 
				System.out.println("From what tower to what tower(0,1,2)?");
				System.out.println("Tower a:");
				int a=obj.nextInt();
				System.out.println("Tower b:");
				int b=obj.nextInt(); 
				if (m==1) { //only need to move one disk
					game.move(a,b);
				}
				else if (m==0) {
					game.solveGame();
				} 
				else if (m>1) {//more than 1 disk need more parameters, use polymorphic method (uses intermediary)
					int c;
					if (a==0 && b==1) {
						c=2;}
					else if (a==0 && b==2) {
						c=1;}
					else {
						c=0;}
					game.move(m,a,b,c);
				}
				//show where all the towers are at
				game.showTowerStates();
			}
			//check if player has won or not
			//if player hasn't won then show the solution
			if (game.wonGame()==true) {
				System.out.println("Congratulations, you won the game!");
			}
			else {
				System.out.println("Solving the towers of Hanoi game with steps");
				game.solveGame(); 
			}

		}

	}
}

public class TowersOfHanoi {
	
	private ArrayStack<Integer>[] rods; //array of ArrayStacks rods.  
	private int n;
	//constructor that takes capacity n
	public TowersOfHanoi (int n) {
		this.n=n;
		rods =new ArrayStack [3];
		//initialising the 3 ArrayStacks, first ArrayStack has capacity n
		rods[0]=new ArrayStack(n);
		rods[1]=new ArrayStack(n);
		rods[2]=new ArrayStack(n);
		//pusing the disks into the first array stack rods[0]
		for (int i=1; i<=n; i++) {
			rods[0].push(new Integer(n+1-i));
		}  
	}
	/*
	//display what's inside the rods
	public String toString() {
		return ("Rod contents- Tower 1: "+rods[0].toString()+", Tower 2: "+rods[1].toString()+", Tower 3: "+rods[2].toString());
	}*/
	//Legal move method, checks if the players move is legal or not ad returns boolean
	//%%%
	public Boolean legalMove(int a, int b) {//moving from rod a to rod b
		//have to check if you're moving from rod with something in it
		if (rods[a].isEmpty()) {
			return false;
		} 
		if (rods[b].isEmpty()) {
			return true; 
		}
		//check if sendign to a rod that's full, return false if it's false
		if (rods[b].isFull()) {
			return false;
		}
		//%%legal move if rod a value less than rod b value 
		return (rods[a].peek()<rods[b].peek()); 
	}
	public  Boolean move(int a, int b){
		//check if move is legal 
		if (legalMove(a,b)==true) {
			//move the disk by popping rods[a] and pushing value to rods[b]
			rods[b].push(rods[a].pop());
			System.out.println("Disk "+rods[b].peek()+" moved from rod "+a+" to rod "+b);
			return true;
		}
		System.out.println("Sorry that's not a legal move.");
		return false; 
	}
	//%% moves m discs from tower a to tower b using tower c as an intermediate storage, prints out movements as they appear
	//illegal moves: use a doesn t have m discs, rod b has exceeded capacity or you are trying to store a large disc on 
	//top of a smaller one
	//It returns false if the moves are not legal
	//solves the game from the initial state where all discs are stored in rods[0].
	public Boolean move (int m,int a, int b, int c) {
		if(m>rods[a].size()) {
			return false; 
		}
		//calculate number of moves required
		int numMoves = (int) Math.pow(2,n)-1; 
		//%%make the moves and display them all when making them 

		//if n is odd
		if (!(n%2==0)) {
			for (int i=1; i<numMoves+1; i++) {
				if(i%3==1) {
					if (legalMove(a,b)) {
						move(a,b);
					}
					else if (legalMove(b,a)) {
						move(b,a);
					}
				}
				if (i%3==2) {
					if (legalMove(a,c)) {
						move(a,c);
					}
					else if (legalMove(c,a)) {
						move(c,a); 
					}
				}
				if (i%3==0) {
					if (legalMove(b,c)) {
						move(b,c);
					}
					else if (legalMove(c,b)) {
						move(c,b);
					}
				}
			}
		}
		//if n is even 
		else if (n%2==0) {
			for (int i=1; i<numMoves+1;i++) {
				if(i%3==1) {
					if (legalMove(a,c)) {
						move(a,c);
					}
					else if (legalMove(c,a)) {
						move(c,a);
					}
				}
				if (i%3==2) {
					if (legalMove(a,b)) {
						move(a,b);
					}
					else if (legalMove(b,a)) {
						move(b,a);
					}
				}
				if (i%3==0) {
					if (legalMove(c,b)) {
						move(c,b);
					}
					else if (legalMove(b,c)) {
						move(b,c);
					}
				}
			}
		}
		showTowerStates();
		return true; 
	}
	public void showTowerStates() {
		for (int i=0; i<3; i++) {
			if (! rods[i].isEmpty()) {
				//toString method prints out the contents of the ArrayStack for each rod
				System.out.println("Tower"+(i+1)+": "+rods[i].toString());
			}
			else {
				System.out.println("Tower"+(i+1)+": empty;");
			}	 
		}
	}
	//solves the game for the player by resetting 
	public void solveGame() {
		rods[0].clear();
		rods[1].clear();
		rods[2].clear();
		//pushing in to the initial rod, same like initial state
		for (int i=1; i<n+1; i++) {
			rods[0].push(new Integer (n+1-i)); 
		}
		move(n,0,2,1); 
	}
	//check if player has won the game
	public Boolean wonGame() {
		Stack <Integer>check= new ArrayStack(n);
		//filling a test check stack with the disks in the correct order 
		for (int i=0; i<n; i++) {
			check.push(i);
		}
		//game is succesffully solved if the last rod has the disks in the correct order
		return (rods[2].equals(check));
	}

}
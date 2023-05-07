
public class First_Come_First_Served {
	
	
	// method for calculating waitTime and turnaroundTime of CPU burst cycle
	public static void calculateWaitTurnaround(int taskNumber, int []burstTimes, int []turnaroundTimes, int []WaitingTimes) {
		
        int currentTime = 0;  // current value set to 0 so the scheduling algorithm starts at 0
        
        for(int i = 0; i < taskNumber; i++) {  // the for loop will iterate over each of the taskNumber to get the wait and turnaround time
        	
            turnaroundTimes[i] = currentTime;  // turnaroundTimes stores the values of the current times
            currentTime += burstTimes[i];  // current time is incremented by the burstTimes
            WaitingTimes[i] = currentTime;  // the current task finishes and wait for the next task in the for loop until
        }  	
	}
	
	
	// method for calculating averageWait Time
	public static double averageWaitTime(int []waitingTimes, int totalTask) {
		
		int sumWaitTime = 0; 
		
		for(int i = 0; i < waitingTimes.length; i++) {  // for loop will iterate over all the values in the waitTimes array
			sumWaitTime = sumWaitTime + waitingTimes[i];  // sumWaitTimes add all the values in the waitTImes array one by one
		}
		
		double averageWaitTime = (double) sumWaitTime / totalTask;  // getting the average by dividing the sumWaitTime by taskNumber
		
		return averageWaitTime; 
	}
	
	
	// method for calculating averageTurnaround Time
	public static double averageTurnaroundTime(int []turnaroundTime, int taskNumber) {
		
        int sumTurnAroundTime = 0;
		
		for (int i = 0; i < turnaroundTime.length; i++) {  // for loop will iterate over all the values in the turnaroundTime array
			sumTurnAroundTime = sumTurnAroundTime + turnaroundTime[i];  // sumTurnAroundTime add all the values in the turnaroundTime array one by one
		}
		
		double averageTurnaroundTime = (double) sumTurnAroundTime / taskNumber;  // getting the average by dividing the sumWaitTime by taskNumber
		
		return averageTurnaroundTime;
	}
	
    
    public static void main(String[] args) {
                
        
        int taskNumberTotal = 5;  // number of task is 5
        
        
        // There is no place holder for arrive time because all the task arrive at the same time 
        int[] burstTimes = {20,25,25,15,10};  // burstTimes array hold the value of the CPU burst in millisecond 
        int[] waitingTimes = new int[taskNumberTotal];  // after calculateWaitTurnaround is called waitingTimes will be filled with the appropriate values
        int[] turnaroundTimes = new int[taskNumberTotal];  // after calculateWaitTurnaround is called turnaroundTimes will be filled with the appropriate values
        
        
        // calling on the calculate method
        calculateWaitTurnaround(taskNumberTotal, burstTimes, waitingTimes, turnaroundTimes);
        
        
        // name of the scheduling algorithm
        System.out.print("FCFS:\n");
        
        
        // printing the result in a gantt chart
        for(int i = 0; i < taskNumberTotal; i++) {
            System.out.print("T" + (i+1) + " " + "[" + waitingTimes[i] + " - " + turnaroundTimes[i] + "]");
            if (i < taskNumberTotal - 1) {  // once the result reaches the last entry, then the last comma will not show
                System.out.print(", ");
            }
        }
        
        
        // calling on averageWaitTime method
        System.out.println("\nAvg. waiting time: " + averageWaitTime(waitingTimes, taskNumberTotal));
        		    
        
        // calling on averageTurnaroundTime method
        System.out.println("Avg. turnaround time: " + averageTurnaroundTime(turnaroundTimes, taskNumberTotal));        
    }
}
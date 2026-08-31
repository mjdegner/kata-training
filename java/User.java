public class User {
    public int rank = -8;
    public int progress = 0;

    public User() {}

    public User(int rank, int progress) {
        this.rank = rank;
        this.progress = progress;
    }


    public void incProgress(int level) {

        // Completing an activity ranked higher than the current user's rank will accelerate the rank progression. 
        // The greater the difference between rankings the more the progression will be increased. 
        // The formula is 10 * d * d where d equals the difference in ranking between the activity and the user.
        if(level > this.rank){

            int d = Math.abs(this.rank - level);
            int p = 10 * d * d;
 
            System.out.println("Completing an activity ranked higher than the current user's rank will accelerate the rank progression.");
            System.out.println("Rank diff: " + d);
            System.out.println("Progress to add: " + p);

            addProgress(p);
        }
        // Completing an activity that is ranked the same as that of the user's will be worth 3 points
        else if(level == this.rank) {
            System.out.println("Completing an activity that is ranked the same as that of the user's will be worth 3 points");
            addProgress(3);
        }
        // Completing an activity that is ranked one ranking lower than the user's will be worth 1 point
        else if(level == this.rank - 1) {
            System.out.println("Completing an activity that is ranked one ranking lower than the user's will be worth 1 point");
            addProgress(1);
        }
        // Any activities completed that are ranking 2 levels or more lower than the user's ranking will be ignored
        else System.out.println("Any activities completed that are ranking 2 levels or more lower than the user's ranking will be ignored");

        // If a user ranked -8 completes an activity ranked -7 they will receive 10 progress
        // If a user ranked -8 completes an activity ranked -6 they will receive 40 progress
        // If a user ranked -8 completes an activity ranked -5 they will receive 90 progress
        // If a user ranked -8 completes an activity ranked -4 they will receive 160 progress, resulting in the user being upgraded to rank -7 and having earned 60 progress towards their next rank
        // If a user ranked -1 completes an activity ranked 1 they will receive 10 progress (remember, zero rank is ignored)
    }

    private void addProgress(int p) {

        int newProgress = this.progress + p;

        if(newProgress >= 100 && rank != 8) {
            int r = newProgress % 100;
            int n = newProgress / 100;
            this.progress = r;
            this.rank += n;
            System.out.println("User ranked up to " + this.rank + " with progress " + this.progress);
        } else {
            this.progress = newProgress;
            System.out.println("Progress was added: " + p + " new progress is: " + newProgress);
        }

        if(rank == 0) rank++;
    }

}

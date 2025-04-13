import java.util.*;

public class ElectionSystem {
    private static final List<String> POSSIBLE_CANDIDATES = Arrays.asList(
            "Bohdana", "Ivanna", "Anna", "Alisa", "Andriy",
            "Danylo", "Angelina", "Dmytro", "Vova", "Vasya"
    );

    public static void main(String[] args) {
        Election election = new Election();
        Random random = new Random();


        int numCandidates = random.nextInt(8) + 3;
        LinkedList<String> candidates = new LinkedList<>();


        List<String> shuffledCandidates = new ArrayList<>(POSSIBLE_CANDIDATES);
        Collections.shuffle(shuffledCandidates);
        for (int i = 0; i < numCandidates; i++) {
            candidates.add(shuffledCandidates.get(i));
        }


        int p = random.nextInt(16) + 5;

        election.initializeCandidates(candidates);
        election.setElectorateVotes(p);

        System.out.println("Number of candidates: " + numCandidates);
        System.out.println("Candidates: " + candidates);
        System.out.println("Number of electorate votes: " + p);


        for (int i = 0; i < p; i++) {
            election.castRandomVote();
        }

        System.out.println("Top 3 candidates after " + p + " votes: " + election.getTopKCandidates(3));


        String candidateToRig = candidates.get(random.nextInt(candidates.size()));
        election.rigElection(candidateToRig);
        System.out.println("Top 3 candidates after rigging the election for " + candidateToRig + ": " + election.getTopKCandidates(3));


        System.out.println("Audit of the election:");
        election.auditElection();
    }
}
import java.util.*;

public class Election {
 private List<String> candidates;
 private Map<String, Integer> votes;
 private int p;

 public void initializeCandidates(LinkedList<String> candidates) {
  this.candidates = new ArrayList<>(candidates);
  this.votes = new HashMap<>();
  for (String candidate : candidates) {
   votes.put(candidate, 0);
  }
 }

 public void castVote(String candidate) {
  if (votes.containsKey(candidate)) {
   votes.put(candidate, votes.get(candidate) + 1);
  }
 }

 public void castRandomVote() {
  Random random = new Random();
  int index = random.nextInt(candidates.size());
  String candidate = candidates.get(index);
  castVote(candidate);
 }

 public void rigElection(String candidate) {
  int currentVotes = votes.get(candidate);
  int sumOthers = 0;
  int maxOther = 0;
  for (String c : candidates) {
   if (!c.equals(candidate)) {
    int v = votes.get(c);
    sumOthers += v;
    if (v > maxOther) {
     maxOther = v;
    }
   }
  }
  int required = maxOther + 1;
  int remaining = p - required;
  if (remaining >= 0 && sumOthers >= remaining) {
   votes.put(candidate, required);
   int sum = required;
   for (String c : candidates) {
    if (!c.equals(candidate)) {
     int adjust = Math.min(votes.get(c), remaining);
     votes.put(c, adjust);
     remaining -= adjust;
     sum += adjust;
     if (remaining <= 0) break;
    }
   }
   votes.put(candidate, p - (sum - required));
  } else {
   votes.put(candidate, p);
   for (String c : candidates) {
    if (!c.equals(candidate)) {
     votes.put(c, 0);
    }
   }
  }
 }

 public List<String> getTopKCandidates(int k) {
  // Create a max-heap based on vote counts using
  PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
   @Override
   public int compare(String a, String b) {
    // Sort in descending order of votes
    return votes.get(b) - votes.get(a);
   }
  });

  // Add all candidates to the heap (O(m) time via heapify)
  maxHeap.addAll(votes.keySet());

  // Extract the top k candidates (O(k log m) time)
  List<String> topCandidates = new ArrayList<>();
  for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
   topCandidates.add(maxHeap.poll());
  }

  return topCandidates;
 }

 public void auditElection() {

  List<Map.Entry<String, Integer>> entries = new ArrayList<>(votes.entrySet());


  Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
   @Override
   public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
    return b.getValue().compareTo(a.getValue());  // Descending order
   }
  });


  for (Map.Entry<String, Integer> entry : entries) {
   System.out.println(entry.getKey() + " - " + entry.getValue());
  }
 }

 public void setElectorateVotes(int p) {
  this.p = p;
 }
}

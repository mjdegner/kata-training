import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class Lift {

  public static void main(String[] args) {
    System.out.println("Kata Training");
    
  }

  enum Direction {
    UP, DOWN;

    Direction switchDirection() {
        return this == UP ? DOWN : UP;
    }

    boolean isSameDirection(int from, int to) {
        return this == UP ? to > from : to < from;
    }
  } 

  record Person(int fromFloor, int toFloor) {}

  public static int[] theLift(final int[][] queues, final int capacity) {
    List<Integer> stops = new ArrayList<>(List.of(0)) ;
    List<Person> lift = new ArrayList<>(capacity);
    List<Queue<Person>> floors = initFloors(queues);

    int floor = 0;
    Direction direction = Direction.UP;
    TreeSet<Integer> nextStops = new TreeSet<>();

    while (!isFinished(lift, floors)) {

      // 1. leave and enter lift
      boolean stopped = leaveAndEnterLift(floor, lift, floors, capacity, direction);

      // 2 register stop
      if (stopped && stops.get(stops.size() - 1) != floor) stops.add(floor);

      // 3. calc new stops
      nextStops.clear();
      addStopsFromLift(lift, nextStops);
      addStopsFromQueue(floor, floors, nextStops, direction);

      // 4. change directions
      if(nextStops.isEmpty()) {
        floor = direction == Direction.UP ? floors.size() : 0;
        direction = direction.switchDirection();
        continue;
      }

      // 5. move
      floor = direction == Direction.UP ? nextStops.pollFirst() : nextStops.pollLast();
    }

    //add last stop
    if(stops.get(stops.size()-1) != 0) stops.add(0);

    return stops.stream().mapToInt(Integer::intValue).toArray();
  }

  private static boolean isFinished(List<Person> lift, List<Queue<Person>> floors) {
    if(!lift.isEmpty()) return false;
    if(floors.stream().mapToInt(Queue::size).sum() > 0) return false;

    return true;
  }

  private static void addStopsFromLift(List<Person> lift, TreeSet<Integer> nextStops) {
    for (Person p : lift) nextStops.add(p.toFloor());
  }

  private static void addStopsFromQueue(int currentFloor, List<Queue<Person>> floors, TreeSet<Integer> nextStops, Direction direction) {
    for (Queue<Person> floor : floors) {
      for (Person p : floor) {
        if (direction.isSameDirection(p.fromFloor(), p.toFloor()) && direction.isSameDirection(currentFloor, p.fromFloor())) {
          nextStops.add(p.fromFloor());
        }
      }
    }
  }

  private static boolean leaveAndEnterLift(int floor, List<Person> lift, List<Queue<Person>> floors, int capacity, Direction direction) {
    return leaveLift(floor, lift) | enterLift(floor, lift, floors, capacity, direction);
  }

  private static boolean leaveLift(int currentFloor, List<Person> lift) {
    return lift.removeIf(n -> n.toFloor() == currentFloor);
  }

  private static boolean enterLift(int currentFloor, List<Person> lift, List<Queue<Person>> floors, int capacity, Direction direction) {
    if(currentFloor >= floors.size()) return false;
    
    boolean someoneEntered = false;
    Queue<Person> queue = floors.get(currentFloor);
    Iterator<Person> it = queue.iterator();

    while (it.hasNext()) {
      Person p = it.next();

      if (!direction.isSameDirection(currentFloor, p.toFloor())) continue;
      
      someoneEntered = true;
      
      if(lift.size() < capacity){
        lift.add(p);
        it.remove();
      }
    }

    return someoneEntered;
  }

  private static List<Queue<Person>> initFloors(int[][] queues) {
    List<Queue<Person>> res = new ArrayList<>();

    for (int floor = 0; floor < queues.length; floor++) {
      Queue<Person> f = new ArrayDeque<>();
      for (int target : queues[floor]) f.add(new Person(floor, target));
      res.add(f);
    }

    return res;
  }

}


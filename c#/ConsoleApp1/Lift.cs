using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Linq;

class Lift
{

  static void Main(string[] args)
  {
    
  } 

  public enum Direction {
      Up,
      Down
  }

  public static class DirectionHelper {
      public static Direction Switch(Direction d)
          => d == Direction.Up ? Direction.Down : Direction.Up;

      public static bool SameDirection(Direction d, int from, int to)
          => d == Direction.Up ? to > from : to < from;
  }

  public record Person(int FromFloor, int ToFloor);


    public static int[] TheLift(int[][] queues, int capacity) {
        var stops = new List<int> { 0 };
        var lift = new List<Person>(capacity);
        var floors = InitFloors(queues);

        int floor = 0;
        var direction = Direction.Up;

        var nextStops = new SortedSet<int>();

        while (!Finished(lift, floors))
        {
            if (HandleStop(floor, lift, floors, capacity, direction) && stops[^1] != floor)
                stops.Add(floor);

            nextStops.Clear();

            AddLiftStops(lift, nextStops);
            AddQueueStops(floor, floors, nextStops, direction);

            if (nextStops.Count == 0)
            {
                floor = direction == Direction.Up ? floors.Count : 0;
                direction = DirectionHelper.Switch(direction);
                continue;
            }

            floor = direction == Direction.Up
                ? PopMin(nextStops)
                : PopMax(nextStops);
        }

        if (stops[^1] != 0)
            stops.Add(0);

        return stops.ToArray();
    }

    static bool Finished(List<Person> lift, List<Queue<Person>> floors)
        => lift.Count == 0 && floors.All(q => q.Count == 0);

    static void AddLiftStops(IEnumerable<Person> lift, ISet<int> stops) {
        foreach (var p in lift) stops.Add(p.ToFloor);
    }

    static void AddQueueStops(int currentFloor, IEnumerable<Queue<Person>> floors, ISet<int> stops, Direction direction) {
        foreach (var queue in floors)
        foreach (var p in queue)
            if (DirectionHelper.SameDirection(direction, p.FromFloor, p.ToFloor) &&
                DirectionHelper.SameDirection(direction, currentFloor, p.FromFloor))
                stops.Add(p.FromFloor);
    }

    static bool HandleStop(int floor, List<Person> lift, List<Queue<Person>> floors, int capacity, Direction direction)
        => LeaveLift(floor, lift) | EnterLift(floor, lift, floors, capacity, direction);

    static bool LeaveLift(int floor, List<Person> lift)
        => lift.RemoveAll(p => p.ToFloor == floor) > 0;

    static bool EnterLift(int floor, List<Person> lift, List<Queue<Person>> floors, int capacity, Direction direction) {
        if (floor >= floors.Count)return false;

        bool entered = false;
        var queue = floors[floor];
        int count = queue.Count;

        for (int i = 0; i < count; i++)
        {
            var person = queue.Dequeue();

            if (!DirectionHelper.SameDirection(direction, floor, person.ToFloor))
            {
                queue.Enqueue(person);
                continue;
            }

            entered = true;

            if (lift.Count < capacity)
                lift.Add(person);
            else
                queue.Enqueue(person);
        }

        return entered;
    }

    static List<Queue<Person>> InitFloors(int[][] queues)
    => queues
        .Select((targets, floor) =>
            new Queue<Person>(targets.Select(t => new Person(floor, t))))
        .ToList();

    static int PopMin(SortedSet<int> set) {
    var v = set.Min;
    set.Remove(v);
    return v;
    }

    static int PopMax(SortedSet<int> set) {
    var v = set.Max;
    set.Remove(v);
    return v;
    }

}
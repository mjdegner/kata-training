using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Program
{
  static void Main(string[] args)
  {
    Console.WriteLine("C# Test");

  }

  public static int[,] Spiralize(int n) {
    int[,] result = new int[n, n];

    int top = 0;
    int bottom = n - 1;
    int left = 0;
    int right = n - 1;

    while (top <= bottom && left <= right)
    {
      for (int i = left; i <= right; i++) result[top, i] = 1;

      if (top > bottom) break;
      for (int i = top; i < bottom; i++) result[i, right] = 1;
      
      if(left % 2 == 1) left++;

      if (right < left) break;
      for (int i = right; i > left; i--) result[bottom, i] = 1;

      if (bottom < top+1) break;
      for (int i = bottom; i > top+1; i--) result[i, left] = 1;

      top+=2;
      bottom-=2;
      left++;
      right-=2;
    }
    return result;
  }

  private static void PrintSprial(int[,] s){
    Console.WriteLine("---");
    for (int i = 0; i < s.GetLength(0); i++)
    {
      for (int j = 0; j < s.GetLength(1); j++) Console.Write(s[i, j]); 
      Console.WriteLine("");
    }
    Console.WriteLine("---");
  }

  public static int[] Snail(int[][] array)
  {
    if(array == null || array.Length <= 0) return [];
    int n = array.Length;
    int[] result = new int[n*n];
    int topAndLeft = 0;
    int bottomAndRight = array.Length - 1;

    for (int item = 0; item < result.Length;)
    {
      for (int right = topAndLeft; right <= bottomAndRight; right++) result[item++] = array[topAndLeft][right];
      for (int down = topAndLeft + 1; down <= bottomAndRight; down++) result[item++] = array[down][bottomAndRight];
      for (int left = bottomAndRight - 1; left >= topAndLeft; left--) result[item++] = array[bottomAndRight][left];
      bottomAndRight--;
      for (int up = bottomAndRight; up >= topAndLeft + 1; up--) result[item++] = array[up][topAndLeft];
      topAndLeft++;
    }
    return result;
  }

  public static string Justify(string str, int len)
  {
    var words = str.Split(" ");
    List<string> strings = [];
    var tempLine = new List<string>();
    var tempLength = 0;

    foreach (var word in words)
    {
      if (word.Length + tempLength > len)
      {
        strings.Add(JustifyLine(tempLine, len));
        tempLine.Clear();
        tempLength = 0;
      }

      tempLine.Add(word);
      tempLength += word.Length + 1;
    }
    if (tempLine.Count > 0) strings.Add(string.Join(" ", tempLine));
    return string.Join("\n", strings);
  }

  public static string JustifyLine(List<string> words, int len)
  {
    if (words.Count == 1) return words[0];  

    var gaps = words.Count - 1;
    var whitespaces = len - words.Sum(w => w.Length);
    int spacesPerGap = whitespaces / gaps;
    int extraGap = whitespaces % gaps;

    var result = new StringBuilder();
    for (int i = 0; i < words.Count; i++)
    {
      result.Append(words[i]);
      if (i < gaps)
      {
        int spacesToAdd = spacesPerGap + (i < extraGap ? 1 : 0);
        result.Append(' ', spacesToAdd);
      }
    }
    return result.ToString();
  }

  public static List<int> TreeByLevels(Node node)
  {
    List<int> res = [];
    Queue<Node> nodes = new();
    if(node == null) return res;

    nodes.Enqueue(node);
    while(nodes.Count > 0)
    {
      Node n = nodes.Dequeue();

      res.Add(n.value);

      if(n.left != null) nodes.Enqueue(n.left);
      if(n.right != null) nodes.Enqueue(n.right);
    }

    return res;
  }

  public static int Score(int[] dices) {

    Dictionary<int,int> singleScore = new() { { 1, 100 }, { 5, 50 } };
    Dictionary<int,int> tripleScore  = new() { { 1, 1000 }, { 2, 200 }, { 3, 300 }, { 4, 400 }, { 5, 500 }, { 6, 600 } };

    int result = 0;
    foreach (var dice in dices.Distinct())
    {
      var count = dices.Count(item => item.Equals(dice));
      result += count / 3 * tripleScore.GetValueOrDefault(dice) + count % 3 * singleScore.GetValueOrDefault(dice);
    }
    return result;
  }

  //Game of Life

  public static int[,] GetGeneration(int[,] cells, int generation)
  {
    //null check
    if (cells == null || cells.Length <= 0) return new int[0, 0];

    for (int gen = 0; gen < generation; gen++)
    {
      System.Console.WriteLine("Before Expand");
      printCells(cells);
      // expand universe
      cells = Expand(cells);
      int rows = cells.GetLength(0);
      int columns = cells.GetLength(1);
      // new gen
      int[,] newGen = new int[rows, columns];
      for (int y = 0; y < rows; y++)
      {
        for (int x = 0; x < columns; x++)
        {
          // count neighbours
          int neighbours = CountNeighbours(y, x, cells);
          // Any live cell with fewer than two live neighbours dies
          // Any live cell with more than three live neighbours dies
          if (neighbours < 2 || neighbours > 3) newGen[y, x] = 0;
          // Any live cell with two live neighbours lives on to the next generation.
          if (neighbours == 2) newGen[y, x] = cells[y, x];
          // Any dead cell with exactly three live neighbours becomes a live cell.
          if (neighbours == 3) newGen[y, x] = 1;
        }
      }
      //trim universe
      cells = Trim(newGen);
    }

    return cells;
  }

  public static int CountNeighbours(int y, int x, int[,] cells)
  {
    int counter = 0;
    for (int i = -1; i < 2; i++)
    {
      for (int j = -1; j < 2; j++)
      {
        if (y + i >= 0 && y + i < cells.GetLength(0) &&
           x + j >= 0 && x + j < cells.GetLength(1) &&
           !(i == 0 && j == 0))
          counter += cells[y + i, x + j];
      }
    }
    return counter;
  }

  public static int[,] Expand(int[,] cells)
  {
    int[,] newCells = new int[cells.GetLength(0) + 2, cells.GetLength(1) + 2];
    for (int y = 0; y < cells.GetLength(0); y++)
    {
      for (int x = 0; x < cells.GetLength(1); x++)
      {
        newCells[y + 1, x + 1] = cells[y, x];
      }
    }
    return newCells;
  }

  public static int[,] Trim(int[,] cells)
  {
    //borders
    int top = -1, bottom = -1, left = -1, right = -1;
    for (int y = 0; y < cells.GetLength(0); y++)
    {
      for (int x = 0; x < cells.GetLength(1); x++)
      {
        if (cells[y, x] == 1)
        {
          if (top == -1) top = y;
          if (left == -1 || left > x) left = x;
          if (bottom == -1 || bottom < y) bottom = y;
          if (right == -1 || right < x) right = x;
        }
      }
    }

    int[,] trimed = new int[bottom - top + 1, right - left + 1];
    for (int y = 0; y < trimed.GetLength(0); y++)
    {
      for (int x = 0; x < trimed.GetLength(1); x++)
      {
        trimed[y, x] = cells[y + top, x + left];
      }
    }
    return trimed;
  }

  public static void printCells(int[,] cells)
  {
    for (int y = 0; y < cells.GetLength(0); y++)
    {
      for (int x = 0; x < cells.GetLength(1); x++)
      {
        System.Console.Write(cells[y, x]);
      }
      System.Console.WriteLine("");
    }
  }

}

public class Node {
    public Node left;
    public Node right;
    public int value;
    
    public Node(Node l, Node r, int v) {
      left = l;
      right = r;
      value = v;
    }
}

//
// selection
//
// Defines a module called "selection" with a main method
// that runs selection sort.  To compile the code enter
//
//    scalac selection.scala
//
// To run the code enter something like
//
//    scala selection 45 2 13 89 7 17 1
// 
// and it will sort the numbers on that command line.
//
object selection {
  
  //
  // indexMin
  //
  // Returns the index of the minimum element in the given
  // array, amongst those in the index range [left..right].
  //
  def indexMin(a:Array[Int],left:Int,right:Int):Int = {
    var minValue = a(left);
    var minIndex = left; 
    for (i <- (left+1) to right) {
      if (a(i) < minValue) {
	minValue = a(i);
	minIndex = i;
      }
    }
    return minIndex;
  }

  //
  // swap
  //
  // Swaps the array elements at index i and j.
  //
  def swap(a:Array[Int],i:Int,j:Int) {
    val temp = a(i);
    a(i) = a(j);
    a(j) = temp;
  }

  //
  // selectionSort
  //
  // Sorts the given array using selectionSort.
  //
  def selectionSort(a:Array[Int]) {
    var left = 0;
    val right = a.length-1;

    for (i <- left until right) {
      val j = indexMin(a,i,right);
      if (i != j) {
	swap(a,i,j);
      }
    }
  }

  //
  // output
  //
  // Outputs the contents of an array on one line.
  //
  def output(a:Array[Int]) {
    for (v <- a) {
      print(v + " ");
    }
    println();
  }

  //
  // main
  //
  // Tests selection sort.
  //
  def main(args:Array[String]) {

    // Builds an array with the given command-line arguments.
    val ints:Array[Int] = new Array[Int](args.length);
    for (i <- 0 until args.length) {
      ints(i) = args(i).toInt;
    }

    // Outputs that array.
    print("BEFORE: ");
    output(ints);

    // Sorts that array using selection sort.
    selectionSort(ints);

    // Outputs the sorted array.
    print("AFTER:  ");
    output(ints);
  }
}

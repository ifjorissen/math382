//  Isabella Jorissen
//  isjoriss
//  Lab 0: Bubble Sort
//  1.31.14


// bubble sort
//
// Defines a module called "bubble" with a main method
// that runs bubble sort.  To compile the code enter
//
//    scalac bubble.scala
//
// To run the code enter something like
//
//    scala bubble 45 2 13 89 7 17 1
// 
// and it will sort the numbers on that command line.
//
object bubble {

  // bubbleSort is a function that steps through the array
  // and checks the value of the integer at its current index,
  // and the value of the integer at the index previous to it
  // and swaps them if the int at the previous index is
  // greater than the int at the current index
  // We continue iterating through the array until we reach a pass where
  // there are no swaps made, when this happens the list is sorted.
  // We accomplish this with a swap boolean.

  def bubbleSort(a:Array[Int]) {
    var doneSwap = true;
    //iterate through length of array if we aren't done
    //swapping when necesssary
    while (doneSwap) {
      doneSwap = false; //(re)set value of doneSwap
      for (i <- 1 until a.length) {
        if (a(i-1) > a(i)){
          swap(a, (i-1), i);
          doneSwap = true; //set doneSwap true, we have made a swap
        };
      }
    }
  }

  // Jim's function
  // A function that swaps two elements located at index i and j, 
  // in a given array.
  //
  def swap(a:Array[Int],i:Int,j:Int) {
    val temp = a(i);
    a(i) = a(j);
    a(j) = temp;
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
  // Tests bubble sort.
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

    // Sorts that array using bubble sort.
    bubbleSort(ints);

    // Outputs the sorted array.
    print("AFTER:  ");
    output(ints);
  }
}

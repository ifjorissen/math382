//  Isabella Jorissen
//  isjoriss
//  Lab 1: Bubble Sort
//  2.7.14


// Iterative merge sort
// Defines a module called "merge_i" with a main method
// that runs an interative merge sort on a given array of ints.  To compile the code enter
//
//    scalac merge_i.scala
//
// To run the code enter something like
//
//    scala merge_i 45 2 13 89 7 17 1
// 
// and it will sort the numbers on that command line.
//
object merge_i {

  // bubbleSort is a function that steps through the array
  // and checks the value of the integer at its current index,
  // and the value of the integer at the index previous to it
  // and swaps them if the int at the previous index is
  // greater than the int at the current index
  // We continue iterating through the array until we reach a pass where
  // there are no swaps made, when this happens the list is sorted.
  // We accomplish this with a swap boolean.

  def mergeBottomUp(a:Array[Int]):Array[Int] = {
    var src_array = a;
    var sorted_array = new Array[Int](a.length);
    var blk = 1; //beginning block size
    //go through array with slices of size 1 and 
    //"grouping" togther the sorted bits into pairs until 
    // there's only one group left (the last loop through, then
    // will occur when blk is the size of half the array)
    while (blk < (a.length + 1)){
      var start = 0;
      var middle = 0;
      var end = 0;
      var pos = blk;
      //start at blk size 1 and then after the merge we want to 
      //move down the "next" value of blk's elements in the array
      //from the array a, start at the first group of size blk (starts at j-blk ends at j) and 
      // merge with the adjacent group of the same size (starts at j ends at j+i)
      // then move the start point forward by 2blks
      while (pos < (a.length)){
        start = pos - blk;
        middle = pos;
        end = pos + blk;

        sorted_array = sortPart(src_array, start, middle, end, sorted_array);
        pos += blk*2;

        // the following code is for debugging purposes
        //print("blk: " + blk + " pos: " + pos + " start: " + start + " middle: " + middle +  " end: " + end);
      }
      blk *= 2; //double block size 
      src_array = sorted_array; //we don't need the old array anymore
    } 
    return sorted_array;
  }

  def sortPart(a:Array[Int], start:Int, middle:Int, end:Int, current_sort:Array[Int]):Array[Int] = {
    //merge the two "sub arrays" and put them in the destination array
    //note middle = length of current array
    var left = start;
    var right = middle;
    for (i <- start until end){
      //the following is for debugging what's going on
      //print("left: " + left + " right: " + right + " end: " + end);
      if(right < a.length){
        if ((left <= middle) && (right <= end)){
          if (a(left) <= a(right)){
            current_sort(i) =  a(left);
            left += 1;
          }
          else {
            current_sort(i) = a(right);
            right +=1;
          }
        }
      }
      //when right = a.length 
      else {
        current_sort(end-1) = a(left);
      }
    }
    //debug (output current state of sorted array)
    output(current_sort); 
    return current_sort;
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
  // Tests merge_i sort.
  //
  def main(args:Array[String]) {

    // Builds an array with the given command-line arguments.
    val ints:Array[Int] = new Array[Int](args.length);
    var final_sort = ints; //will hold the final array

    for (i <- 0 until args.length) {
      ints(i) = args(i).toInt;
    }

    // Outputs that array.
    print("BEFORE: ");
    output(ints);

    // Sorts that array using bubble sort.
    final_sort = mergeBottomUp(ints);

    // Outputs the sorted array.
    print("AFTER:  ");
    output(final_sort);
  }
}

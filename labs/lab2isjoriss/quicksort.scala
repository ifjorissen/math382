object quick {

  // swap
  //
  // Performs a swap of the values at two positions in an array.
  //
  def swap(a:Array[Int],i:Int,j:Int):Unit = {
    val temp = a(i);
    a(i) = a(j);
    a(j) = temp;
  }

  // partition
  //
  // Using a "pivot" value v of a[iv], this rearranges 
  // the array's values so that a[p..i-1] are less 
  // than or equal to v and so that a[i..r] are greater
  // than v.  The order of elements within each half is
  // arbitrary.
  //
  def partition(iv:Int,a:Array[Int],l:Int,r:Int):Int = {
    val v:Int = a(iv);
    swap(a,iv,r);
    var i:Int = l;
    for (j <- l to r) {
      if (a(j) <= v) {
        swap(a,i,j);
        i = i+1;
      }
    }
    return i-1;
  }

  // quicksort
  //
  // Sorts an array a[p..r] using quicksort, with the provided
  // pivot selection procedure choosePivot.
  //
  def quicksort(a:Array[Int], p:Int, r:Int, choosePivot:(Array[Int],Int,Int)=>Int):Unit = {
    if (p < r) {
      val i:Int = choosePivot(a,p,r);
      val q:Int = partition(i,a,p,r);
      quicksort(a,p,q-1,choosePivot);
      quicksort(a,q+1,r,choosePivot);
    }
  }

  // PIVOT CHOICE METHODS
  //
  // left - always choose the leftmost element
  // median of three - choose the median of the left, middle, and right
  // random - pick an index at random
  //
  def leftPivot(a:Array[Int], p:Int, r:Int):Int = {
    return p;
  }
  //
  def medianOf3Pivot(a:Array[Int], p:Int, r:Int):Int = {
    val q:Int = (p + r)/2;
    if (a(p) < a(q) && a(q) < a(r)) return q; 
    if (a(q) < a(p) && a(p) < a(r)) return p; 
    return r; 
  }
  //
  def randomPivot(a:Array[Int], p:Int, r:Int):Int = {
    return random(p,r);
  }
  
  // random
  //
  // Selects a value within the set {from,..,to} uniformly
  // at random.
  //
  def random(from:Int,to:Int):Int = {
    val size = to - from + 1;
    return from + (math.random * size).toInt;
  }
    
  //
  // permutes
  //
  // Randomly permutes the given array's elements.
  //
  def permute(a:Array[Int]) {
    val n = a.length;
    for (i <- 0 until (n-1)) {
      val j = random(i,n-1);
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

    val randomize = if (args(0).equals("random")) true else false;

    // Create a new array of the appropriate size.
    val n = if (randomize) args(1).toInt else args.length;
    val ints:Array[Int] = new Array[Int](n);
      
    // Fill in the array's values.
    if (randomize) {
      // Builds an array with a random permutation.
      for (i <- 0 until n) {
	ints(i) = i+1;
      }
      permute(ints);
    } else {
      // Build an array with the given command-line arguments.
      for (i <- 0 until n) {
	ints(i) = args(i).toInt;
      }
    }

    // Output that array.
    print("BEFORE: ");
    output(ints);

    // Sort that array using selection sort.
    val T = time(quicksort(ints,0,n-1,randomPivot));

    // Output the sorted array.
    print("AFTER:  ");
    output(ints);

    println("TIME: "+T+" msecs");
  }

  //
  // time
  //
  // times a block of code
  //
  def time(f: => Unit) = {
    val s = System.currentTimeMillis
    f
    System.currentTimeMillis - s;
  }
}
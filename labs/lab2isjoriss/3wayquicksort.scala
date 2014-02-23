// Isabella Jorissen
// Math 382 Lab 2
// 2.23.14

//See README for a detailed description of quick3, quicksort.

object quick3 {

	def quick3sort(a:Array[Int], p:Int, r:Int, choosePivot:(Array[Int],Int,Int)=>Int):Unit = {
		if (p < r){
		  //declare two pivots
		  val i:Int = choosePivot(a,p,r);
		  val j:Int = choosePivot(a,p,r);
		  println("Pivot vals (i,j): " + i + " " + j)

		  //determine whic pivot is smaller, call threePartition accordingly
		  if (a(i) <= a(j)){
		  	// i is smaller pivot
		  	val q:Int = threePartition(i,j,a,p,r)._1;
		  	val s:Int = threePartition(i,j,a,p,r)._2;
		  	println("PartVal1(q,s): " + q + " " + s)
		  	//Recursive call
		  	quick3sort(a,p,q-1,choosePivot);
		  	quick3sort(a,q+1,s-1,choosePivot);
		  	quick3sort(a,s+1,r, choosePivot)
		  }
		  else{
		  	//j is smaller pivot
		  	val q:Int = threePartition(j,i,a,p,r)._1;
		  	val s:Int = threePartition(j,i,a,p,r)._2;
		  	println("PartVal2(q,s): " + q + " " + s)
		  	//Recursive call
		  	quick3sort(a,p,q-1,choosePivot);
		  	quick3sort(a,q+1,s-1,choosePivot);
		  	quick3sort(a,s+1,r, choosePivot)
		  }
		}
	}
	/*
      threePartition takes in the two pivot values, the array, and the
      start / stop values of the array, l and r The partitions,
      then, run from l to i-1, i to j-1 and j to r. 
      returns a two-tuple consisting of the new range values for the 
      recursive call
	*/
	def threePartition(iv:Int, jv:Int, a:Array[Int], l:Int, r:Int):(Int, Int) = {
		// set pl (left pivot) to smaller pivot
		// set pr (right pivot) to larger pivot
		val pl:Int = a(iv);
		val pr:Int = a(jv);
		//move left pivot to the (far right) end of the partition
		//move right pivot to the end of the current array
		swap(a,iv,jv-1);
		swap(a,jv,r);

		var i:Int = l;
		var j:Int = jv;

		//move all elements (in whole arra) less than smaller pivot 
		//to left. Pivot placement is moved to jv-1, not r.
		for (k <- l to r){
			if(a(k) <= pl){
				swap(a,i,k);
				i = i + 1;
			}
		}
		//swap elements aroung right pivot
		for (m <- j to r){
			if(a(m) <= pr){
				swap(a,j,m);
				j = j + 1;
			}
		}
		return (i-1, j-1)
	}

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

	/*
	 _________________________________________________________
	 Everything below this is Jim's code from quicksort.scala.
	 _________________________________________________________
	*/

	//performs a swap of the values at two positions in an array.
  def swap(a:Array[Int],i:Int,j:Int):Unit = {
    val temp = a(i);
    a(i) = a(j);
    a(j) = temp;
  }

  // PIVOT CHOICE METHODS
  //
  // left - always choose the leftmost element
  // median of three - choose the median of the left, middle, and right
  // random - pick an index at random
  
  def leftPivot(a:Array[Int], p:Int, r:Int):Int = {
    return p;
  }
  
  def medianOf3Pivot(a:Array[Int], p:Int, r:Int):Int = {
    val q:Int = (p + r)/2;
    if (a(p) < a(q) && a(q) < a(r)) return q; 
    if (a(q) < a(p) && a(p) < a(r)) return p; 
    return r; 
  }
  
  def randomPivot(a:Array[Int], p:Int, r:Int):Int = {
    return random(p,r);
  }
  
  // Selects a value within the set {from,..,to} uniformly
  // at random.
  def random(from:Int,to:Int):Int = {
    val size = to - from + 1;
    return from + (math.random * size).toInt;
  }

  // Randomly permutes the given array's elements.
  def permute(a:Array[Int]) {
    val n = a.length;
    for (i <- 0 until (n-1)) {
      val j = random(i,n-1);
      if (i != j) {
				swap(a,i,j);
      }
    }
  }

  // Outputs the contents of an array on one line.
  def output(a:Array[Int]) {
    for (v <- a) {
      print(v + " ");
    }
    println();
  }

  // Tests quick3 sort.
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
    val T = time(quick3sort(ints,0,n-1,randomPivot));

    // Output the sorted array.
    print("AFTER:  ");
    output(ints);

    println("TIME: "+T+" msecs");
  } 

  // times a block of code
  def time(f: => Unit) = {
    val s = System.currentTimeMillis
    f
    System.currentTimeMillis - s;
  }
}
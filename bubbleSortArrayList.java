/* Method to bubble sorting an ArrayList of integer based on the main class (Archive)
* Archive class has an attribute called copies that refers as Copies of the book
*/
public static void copiesBubbleSort(ArrayList < Archive > books, File file) {

  // Creating a local array of integer
  ArrayList < Integer > bubbleCopies = new ArrayList < > ();

  // Creation of local variable that has to be used to contain the copies attribute of a determinate Object
  int copiesVal;

  // For loop to get every copies attribute of every Object, saving into copiesVal and putting it into the local ArrayList 
  for (int i = 0; i < books.size(); i++) {
    copiesVal = books.get(i).copies;
    bubbleCopies.add(copiesVal);
  }

  // Print of the list before sorting
  System.out.println(bubbleCopies);
  
  boolean sorted = false;
  // Loop to keep the algorithm going before every values are sorted correctly 
  while (!sorted) {

    sorted = true;
    // Double loop to get the "first" value to check and the "consequential" value
    for (int j = 0; j <= bubbleCopies.size(); j++) {
      for (int k = j + 1; k <= bubbleCopies.size() - 1; k++) {
        // Check if the first value returned is greater then the consequential 
        if (bubbleCopies.get(j) > bubbleCopies.get(k)) {
          sorted = false;
          // If it's greater then swap based on index of the List
          Collections.swap(bubbleCopies, j, k);
        }
      }
    }
  }
  // Print of the bubble sorted list 
  System.out.println(bubbleCopies);
}

public class PrimeNumbers {
  public static void main(String[] args) {
    
    int a[][]={
      {1,2,3},
      {4,5,6},
      {7,8,9},
      {10,11,12},
      {13,14,15},
      {16,17,18},
      {19,20,21},
    };

    for(int i=0;i<a.length;i++){
      for(int j=0;j<a[i].length;j++){
        if(isPrime(a[i][j])){
          System.out.print(a[i][j]+" ");
        }
      }
      System.out.println();
    }


  }
  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i++) {
      if(n%i==0){
        return false;
      }
    }
    return true;
  }
  
}

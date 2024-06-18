public class MultiplicationOfTwoMatrixes {
  public static void main(String[] args) {
    int a[][]={
      {2,4,7},
      {1,6,0}
      };
  
  int b[][]={
      {1,7},
      {3,8},
      {4,9}
      };
    int c[][]= new int[a.length][b[0].length];

    for(int i=0;i<a.length;i++) {
      for(int j=0;j<b[i].length;j++){
        for(int k=0;k<a[0].length;k++) {
          c[i][j] +=a[i][k]*b[k][j];
        }
      }
    }

    for(int i=0;i<c.length;i++) {
      for(int j=0;j<b[i].length;j++){
        System.out.print(c[i][j]+" ");
      }
      System.out.println();
  }
}
  
}

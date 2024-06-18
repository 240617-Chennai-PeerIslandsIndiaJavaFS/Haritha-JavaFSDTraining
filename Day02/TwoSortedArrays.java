public class TwoSortedArrays {
  public static void main(String[] args) {
    int a[] = {2,4,6,7,10};
    int b[] = {1,3,5,9};

    int c[] = new int[a.length+b.length];

    int i=0,j=0,k=0;
    while(i<a.length && j<b.length) {
      if(a[i]<=b[j]) {
        c[k++]=a[i++];
      }else{
        c[k++]=b[j++];
      }
    }
    while(i<a.length) {
      c[k++]=a[i++];
    }while(j<b.length) {
      c[k++]=b[j++];
    }

    for(i=0;i<c.length;i++) {
      System.out.print(c[i] + " ");
    }


  }
  
}

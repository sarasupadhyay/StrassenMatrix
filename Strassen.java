import java.util.Scanner;
class matrix1
{
    int matrix[][];
    matrix1(int n)
    {
        matrix=new int[n][n];
    }
    matrix1()
    {
        int r,c;
        Scanner sa=new Scanner(System.in);
        System.out.println("Enter the number of rows : ");
        r=sa.nextInt();
        System.out.println("Enter the number of column : ");
        c=sa.nextInt();
        matrix=new int[r][c];
    }
    void addzero()
    {
        int r=matrix.length;
        int c=matrix[0].length;
        int big;
        if(r>=c)
        big=r;
        
        
        big=c;
        int k=1;
        while(k<big)
        k*=2;
        int mat[][]=new int[k][k];
        for(int i=0;i<k;i++)
        {
            for(int j=0;j<k;j++)
            {
                if(i<r)
                {
                    if(j<c)
                    mat[i][j]=matrix[i][j];
                    else
                    mat[i][j]=0;
                }
                else
                mat[i][j]=0;
            }
        }
        matrix=mat;
    }
    void enterMatrix()
    {
        Scanner sa = new Scanner(System.in);
        System.out.println("Enetr the value of ");
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                System.out.println("a"+(i+1)+""+(j+1)+" : ");
                matrix[i][j]=sa.nextInt();
            }
        }
    }
    void displayMatrix()
    {
        System.out.println("The matrix are: ");
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
class Strassen
{
    int[][] sub(int[][] A, int[][] B)
    {
        int n=A.length;
        int[][] C= new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                C[i][j]=A[i][j]-B[i][j];
            }
        }
        return C;

    }
    int[][] add(int[][] A, int[][] B)
    {
        int n=A.length;
        int[][] C= new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                C[i][j]=A[i][j]+B[i][j];
            }
        }
        return C;
    }
    void split(int[][] m1 , int[][] c , int i,int j)
    {
        int i2=i;
        for(int i1=0;i1<c.length;i1++)
        {
            int j2=j;
            for(int j1=0;j1<c.length;j1++)
            {
            c[i1][j1]=m1[i2][j2];
            j2++;
            }
            i2++;
        }
       
        }
    void join(int[][] m1 , int[][] c , int i,int j)
    {
        for(int i1=0,i2=i;i1<m1.length;i1++,i2++)
        {
            for(int j1=0,j2=j;j1<m1.length;j1++,j2++)
            c[i2][j2]=m1[i1][j1];
        }
    }
    
    int[][] multiply(int[][] A, int[][] B)
    {
        int n=A.length;

        int[][] mat=new int [n][n];
        if(n==1)
        {
            mat[0][0]=A[0][0]*B[0][0];
          
        }
        else
        {
            int a11[][] =new int[n/2][n/2];
            int a12[][] =new int[n/2][n/2];
            int a21[][] =new int[n/2][n/2];
            int a22[][] =new int[n/2][n/2];

            int b11[][] =new int[n/2][n/2];
            int b12[][] =new int[n/2][n/2];
            int b21[][] =new int[n/2][n/2];
            int b22[][] =new int[n/2][n/2];
            
            split(A,a11,0,0);
            split(A,a12,0,n/2);
            split(A,a21,n/2,0);
            split(A,a22,n/2,n/2);

            split(B,b11,0,0);
            split(B,b12,0,n/2);
            split(B,b21,n/2,0);
            split(B,b22,n/2,n/2);

            int p1[][]=multiply(add(a11,a22),add(b11,b22));
            int p2[][]=multiply(add(a21,a22),b11);
            int p3[][]=multiply(a11 ,sub(b12,b22));
            int p4[][]=multiply(a22,sub(b21,b11));
            int p5[][]=multiply(add(a11,a12),b22);
            int p6[][]=multiply(sub(a21,a11),add(b11 ,b12));
            int p7[][]=multiply(sub(a12,a22),add(b21,b22));

            int c11[][]=add(sub(add(p1,p4),p5),p7);
            int c12[][]=add(p3,p5);
            int c21[][]=add(p2,p4);
            int c22[][]=add(sub(add(p1,p3),p2),p6);

            join(c11,mat,0,0);
            join(c12,mat,0,n/2);
            join(c21,mat,n/2,0);
            join(c22,mat,n/2,n/2);
        }
        return mat;
    }
    public static void main(String args[])
    {
        Scanner sa=new Scanner(System.in);
        System.out.println("Enter first matrix ");
        matrix1 m1=new matrix1();
        m1.enterMatrix();
        System.out.println("Enter second matrix ");
        matrix1 m2=new matrix1();
        m2.enterMatrix();
        System.out.println(" first matrix ");
        m1.displayMatrix();
        System.out.println("Second matrix ");
        m2.displayMatrix();
        m1.addzero();
        m2.addzero();
        System.out.println(" first matrix after adding 0 ");
        m1.displayMatrix();
        System.out.println("Second matrix after addding 0 ");
        m2.displayMatrix();
if(m1.matrix.length==m2.matrix.length && m1.matrix[0].length==m2.matrix[0].length)
{
         matrix1 m3=new matrix1(m1.matrix.length);
        Strassen s=new Strassen();
        m3.matrix=s.multiply(m1.matrix,m2.matrix);
         System.out.println("Multiplied matrix ");
         m3.displayMatrix();
        }
        else
     System.out.println("size in compatiable");
    }
    }
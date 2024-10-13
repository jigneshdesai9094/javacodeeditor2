class p1
{
    public static void add(final int arr[],final int x)
    {
         arr[2]=50;
         arr[3]=90;
         arr = new int[4];
         x=30;
    }
    public static void main(String args[])
    {
        int arr[]=new int[5];
        arr[0]=10;
        int x=20;
        add(arr,x);
        for(int i:arr)
        {
            System.out.println(i);
        }
        
    }
}
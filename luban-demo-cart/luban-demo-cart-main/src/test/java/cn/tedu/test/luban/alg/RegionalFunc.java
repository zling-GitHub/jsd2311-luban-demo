package cn.tedu.test.luban.alg;

public class RegionalFunc {
    public static void main(String[] args) {
        //ABC三个实例胜出的次数
        Integer countA=0;
        Integer countB=0;
        Integer countC=0;
        for(int i = 0;i<1000;i++){
            Integer N=6;
            Integer result=i%N;//[0,5]
            if (result==0||result==1||result==2){
                countA++;
            }else if (result==3||result==4){
                countB++;
            }else{
                countC++;
            }
        }
        System.out.println(countA+":"+countB+":"+countC);
    }
}

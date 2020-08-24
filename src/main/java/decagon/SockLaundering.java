package decagon;

import java.util.*;

import static java.lang.Math.min;

public class SockLaundering {

    public static void main(String[] args) {
        System.out.println(solution(2, new int[] {1,2,1,1}, new int[]{1,4,3,2,4}));
        System.out.println(solution(0, new int[] {1,2,1,1}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,1,3}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,2,3}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{0,1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{0,1,2,1,1,3,8}, new int[]{1,4,3,2,4}));
        System.out.println(solution(2, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(1, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(3, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));

    }

    static int solution(int K, int[] C, int[] D) {
        int res = 0;
        int[] clean = new int[51];
        int[] dirty = new int[51];

        for(int c : C)
            clean[c]++;
        for(int d : D)
            dirty[d]++;

        for(int i= 1; i<51; ++i)
        {
            res += clean[i]/2;
            if(clean[i]%2!=0 && K>0 && dirty[i]>0)
            {
                res++;
                K--;
                dirty[i]--;
            }
        }

        for(int i=1; K>1 && i<51; ++i)
        {
            if(dirty[i] >=2)
            {
                dirty[i] = min(dirty[i]/2, K/2);
                res += dirty[i];
                K -= 2*dirty[i];
            }
        }
        return res;
    }



}

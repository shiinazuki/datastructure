package com.iori.algorithm.backracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>N皇后 - 回溯</h3>
 */
public class NQueenLeetcode51 {
    public static void main(String[] args) {
        int n = 4;
        //true false false false
        boolean[] ca = new boolean[n]; //记录列冲突
        //i+ j
        boolean[] cb = new boolean[2 * n - 1]; //左斜线冲突
        //n - 1 - (i - j)
        boolean[] cc = new boolean[2 * n - 1]; //右斜线冲突

        char[][] table = new char[n][n]; // '.' 'Q'

        for (char[] t : table) {
            Arrays.fill(t, '.');
        }


        dfs(0, n, table, ca, cb, cc);


    }

    /**
     * @param i     处理第几行
     * @param n     数组的维度
     * @param table 棋盘数组
     * @param ca    记录列冲突数组
     * @param cb    左斜线冲突
     * @param cc    右斜线冲突
     */
    public static void dfs(int i, int n, char[][] table,
                           boolean[] ca,
                           boolean[] cb,
                           boolean[] cc) {
        if (i == n) { // 找到解
            return;
        }

        for (int j = 0; j < n; j++) {
            if (ca[j] || cb[i + j] || cc[n - 1 - (i - j)]) {
                continue;
            }

            table[i][j] = 'Q';
            ca[j] = cb[i + j] = cc[n - 1 - (i - j)] = true;
            dfs(i + 1, n, table, ca, cb, cc);
            table[i][j] = '.';
            ca[j] = cb[i + j] = cc[n - 1 - (i - j)] = false;
        }
    }


}


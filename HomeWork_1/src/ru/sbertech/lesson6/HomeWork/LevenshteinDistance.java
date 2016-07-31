package ru.sbertech.lesson6.HomeWork;

public class LevenshteinDistance implements IDistance{
    @Override
    public int CalculateDistance(String S1, String S2){
        if (S1.equals(S2))
            return 0;

        int MinLen = Math.min(S1.length(), S2.length());
        int MaxLen = Math.max(S1.length(), S2.length());
        if (MinLen == 0 && MaxLen != 0)
            return MaxLen;

        //найдем длины общих префиксов и суффиксов
        int prefix = 0;
        int suffix = 0;
        while (prefix < MinLen && S1.charAt(prefix) == S2.charAt(prefix))
            ++prefix;
        while (suffix < MinLen && S1.charAt(S1.length() - 1 - suffix) == S2.charAt(S2.length() - 1 - suffix))
            ++suffix;

        if (prefix == MinLen || suffix == MinLen || (prefix + suffix >= MinLen))
            //одна строка является префиксом или суффиксом другой
            return MaxLen - MinLen;
        else{
            int S1Len = S1.length() - prefix - suffix;
            int S2Len = S2.length() - prefix - suffix;

            //инициализируем матрицу расстояний
            int[][] D = new int[S1Len + 1][S2Len + 1];
            D[0][0] = 0;
            for (int j = 1; j <= S2Len; ++j)
                D[0][j] = D[0][j - 1] + 1;

            //вычисляеим матрицу расстояний
            for (int i = 1; i <= S1Len; ++i)
            {
                D[i][0] = D[i - 1][0] + 1;
                for (int j = 1; j <= S2Len; ++j)
                    D[i][j] = Math.min(D[i - 1][j] + 1,
                                       Math.min(D[i][j - 1] + 1,
                                                D[i - 1][j - 1] + (S1.charAt(i - 1 + prefix) == S2.charAt(j - 1 + prefix) ? 0 : 1)
                                               )
                                      );
            }
            return D[S1Len][S2Len];
        }
    }
}

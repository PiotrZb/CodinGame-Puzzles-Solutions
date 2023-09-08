using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;

class Solution
{
    static void Main(string[] args)
    {
        int L = int.Parse(Console.ReadLine());
        int H = int.Parse(Console.ReadLine());
        string T = Console.ReadLine().ToUpper();
        string [] ROWS = new string[H];
        for (int i = 0; i < H; i++){
            ROWS[i] = Console.ReadLine();
        }

        string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?";
        string [] answer = new string[H];

        foreach(char c in T){
            int index = chars.IndexOf(c);
            if(index == -1){index = 26;}

            for (int i = 0; i < H; i++){
                answer[i] += ROWS[i].Substring(index * L,L);
            }
        }

        foreach(string line in answer){Console.WriteLine(line);}
    }
}
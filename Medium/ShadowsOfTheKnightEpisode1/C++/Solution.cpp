#include <iostream>

using namespace std;

int main()
{
    int W;
    int H;
    cin >> W >> H; cin.ignore();
    int N;
    cin >> N; cin.ignore();
    int X0;
    int Y0;
    cin >> X0 >> Y0; cin.ignore();
    int x1 = 0;
    int y1 = 0;
    int x2 = W - 1;
    int y2 = H - 1;

    while (1) {
        string bombDir;
        cin >> bombDir; cin.ignore();

        if(bombDir == "D" || bombDir == "DR" || bombDir == "DL"){y1 = Y0 + 1;}
        if(bombDir == "R" || bombDir == "DR" || bombDir == "UR"){x1 = X0 + 1;}
        if(bombDir == "U" || bombDir == "UR" || bombDir == "UL"){y2 = Y0 - 1;}
        if(bombDir == "L" || bombDir == "DL" || bombDir == "UL"){x2 = X0 - 1;}

        X0 = x1 + (x2 - x1) / 2;
        Y0 = y1 + (y2 - y1) / 2;

        cout<<X0<<" "<<Y0<<endl;
    }
}
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int surfaceN;
    cin >> surfaceN; cin.ignore();
    for (int i = 0; i < surfaceN; i++) {
        int landX;
        int landY;
        cin >> landX >> landY; cin.ignore();
    }

    while (1) {
        int X;
        int Y;
        int hSpeed;
        int vSpeed;
        int fuel;
        int rotate;
        int power;
        cin >> X >> Y >> hSpeed >> vSpeed >> fuel >> rotate >> power; cin.ignore();
 
        if(vSpeed <= -40){cout<<"0 4"<<endl;}
        else{cout<<"0 0"<<endl;}
    }
}
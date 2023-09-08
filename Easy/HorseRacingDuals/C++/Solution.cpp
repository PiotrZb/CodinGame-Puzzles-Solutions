#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int N;
    cin >> N; cin.ignore();
    vector<int> pi;
    for (int i = 0; i < N; i++) {
        int Pi;
        cin >> Pi; cin.ignore();
        pi.emplace_back(Pi);
    }
    
    sort(pi.begin(),pi.end());

    int output = abs(pi[0] - pi[1]);

    for(size_t i = 1; i<pi.size()-1; i++){
        int x = abs(pi[i] - pi[i+1]);
        if (x < output){output = x;}
    }

    cout << output << endl;
}
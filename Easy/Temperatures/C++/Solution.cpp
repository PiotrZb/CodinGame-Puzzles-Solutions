#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int n;
    int output;
    cin >> n; cin.ignore();
    if(n == 0){cout<<0<<endl;}
    else{
    vector<int> temp_u;
    vector<int> temp_l;
    for (int i = 0; i < n; i++) {
        int t;
        cin >> t; cin.ignore();
        if(t < 0){temp_l.emplace_back(t);}
        else{temp_u.emplace_back(t);}
    }
    sort(temp_l.begin(),temp_l.end());
    sort(temp_u.begin(),temp_u.end());
    if(temp_l.empty()){
        output = temp_u[0];
    }
    else{
        if(temp_u.empty()){
            output = temp_l[temp_l.size()-1];
        }
        else{
            if(abs(temp_l[temp_l.size()-1]) < temp_u[0]){
        output = temp_l[temp_l.size()-1];
    }
    else{
        output = temp_u[0];
    }
        }
    }

    cout << output << endl;
    }
}
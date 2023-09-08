#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int main()
{
    int N;
    cin >> N; cin.ignore();
    int Q;
    cin >> Q; cin.ignore();
    map<string,string> ext_mt;

    for (int i = 0; i < N; i++) {
        string EXT;
        string MT;
        cin >> EXT >> MT; cin.ignore();
        transform(EXT.begin(), EXT.end(), EXT.begin(), ::tolower);
        ext_mt[EXT] = MT;
    }

    string output;

    for (int i = 0; i < Q; i++) {
        output = "UNKNOWN";
        string FNAME;
        getline(cin, FNAME);
        transform(FNAME.begin(), FNAME.end(), FNAME.begin(), ::tolower);
        int d = FNAME.find_last_of("."); 
        if (d >= 0){
            FNAME = FNAME.substr(d+1);
            auto it = ext_mt.find(FNAME);
            if(it != ext_mt.end()){
               output = it->second;
            }
        }
        cout<<output<<endl;
    }
    
}
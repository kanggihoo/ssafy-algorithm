#include <iostream>
#include <vector>

using namespace std;

int findMaxNum(int N){
  int max_num = 0;
  while(N >0){
    max_num = max(max_num, N%10);
    N /= 10;
  }
  return max_num;
}


int main(){
  int N; 
  cin >> N;
  

  int ans = 0;
  while(N > 0){
    int num = findMaxNum(N);
    N -= num;
    ans++;
  }

  cout << ans << "\n";
  return 0;
}

